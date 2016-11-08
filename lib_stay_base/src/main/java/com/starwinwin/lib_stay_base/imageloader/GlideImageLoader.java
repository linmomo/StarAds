package com.starwinwin.lib_stay_base.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.signature.StringSignature;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide具体加载类
 */
public class GlideImageLoader implements ImageLoader {
    //默认配置
    public static GlideImageLoadConfig defConfig = new GlideImageLoadConfig.Builder().build();

    @Override
    public void loadUrlImage(ImageView view, String imageUrl) {
        GlideImageLoadConfig loadConfig = new GlideImageLoadConfig.Builder()
//                .setPlaceHolderResId(0)
//                .setErrorResId(0)
                .setCrossFade(true)
                .build();
        load(view.getContext(), view, imageUrl, loadConfig, null);
    }

    @Override
    public void loadLocalImage(ImageView view, File file) {
        GlideImageLoadConfig loadConfig = new GlideImageLoadConfig.Builder()
//                .setPlaceHolderResId(0)
//                .setErrorResId(0)
                .setCrossFade(true)
                .setSkipMemoryCache(true)//跳过内存缓存
                .setDiskCacheStrategy(GlideImageLoadConfig.DiskCache.NONE)//跳过磁盘缓存
                .build();
        load(view.getContext(), view, file, loadConfig, null);
    }

    @Override
    public void loadCircleImage(ImageView view, String imageUrl) {
        GlideImageLoadConfig loadConfig = new GlideImageLoadConfig.Builder()
                .setCrossFade(true)
                .setCropCircle(true)
                .setDiskCacheStrategy(GlideImageLoadConfig.DiskCache.RESULT)
                .build();
        load(view.getContext(), view, imageUrl, loadConfig, null);
    }

    @Override
    public void loadRoundImage(ImageView view, String imageUrl) {
        GlideImageLoadConfig loadConfig = new GlideImageLoadConfig.Builder()
//                .setPlaceHolderResId(0)
//                .setErrorResId(0)
                .setCrossFade(true)
                .setRoundedCorners(true)
                .setCornerSize(5)
                .setDiskCacheStrategy(GlideImageLoadConfig.DiskCache.RESULT)
                .build();
        load(view.getContext(), view, imageUrl, loadConfig, null);
    }

    /**
     * 加载String类型的资源
     * SD卡资源："file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"<p/>
     * assets资源："file:///android_asset/f003.gif"<p/>
     * raw资源："Android.resource://com.frank.glide/raw/raw_1"或"android.resource://com.frank.glide/raw/"+R.raw.raw_1<p/>
     * drawable资源："android.resource://com.frank.glide/drawable/news"或load"android.resource://com.frank.glide/drawable/"+R.drawable.news<p/>
     * ContentProvider资源："content://media/external/images/media/139469"<p/>
     * http资源："http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg"<p/>
     * https资源："https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp"<p/>
     *
     * @param view
     * @param objUrl
     * @param config
     * @param listener
     */
    private static void load(Context context, ImageView view, Object objUrl, GlideImageLoadConfig config, final RequestListener listener) {
        if (null == objUrl) {
            throw new IllegalArgumentException("资源地址为null");
        } else if (null == config) {
            config = defConfig;
        }
        try {
            GenericRequestBuilder builder = null;
            if (config.isAsGif()) {//gif类型
                GifRequestBuilder request = Glide.with(context).load(objUrl).asGif();
                if (config.getCropType() == GlideImageLoadConfig.CENTER_CROP) {
                    request.centerCrop();
                } else if (config.getCropType() == GlideImageLoadConfig.FIT_CENTER) {
                    request.fitCenter();
                }
                builder = request;
            } else if (config.isAsBitmap()) {  //bitmap 类型
                BitmapRequestBuilder request = Glide.with(context).load(objUrl).asBitmap();
                if (config.getCropType() == GlideImageLoadConfig.CENTER_CROP) {
                    request.centerCrop();
                } else if (config.getCropType() == GlideImageLoadConfig.FIT_CENTER) {
                    request.fitCenter();
                }
                builder = request;
            } else if (config.isCrossFade()) { // 渐入渐出动画
                DrawableRequestBuilder request = Glide.with(context).load(objUrl).crossFade();
                if (config.getCropType() == GlideImageLoadConfig.CENTER_CROP) {
                    request.centerCrop();
                } else if (config.getCropType() == GlideImageLoadConfig.FIT_CENTER) {
                    request.fitCenter();
                }
                builder = request;
            }
            //transform bitmap
            if (config.isRoundedCorners()) {
                builder.transform(new RoundedCornersTransformation(context, config.getCornersSize(), config.getCornersSize()));
            } else if (config.isCropCircle()) {
                builder.transform(new CropCircleTransformation(context));
            } else if (config.isGrayscale()) {
                builder.transform(new GrayscaleTransformation(context));
            } else if (config.isBlur()) {
                builder.transform(new BlurTransformation(context, config.getBlurSize(), config.getBlurSize()));
            } else if (config.isRotate()) {
                builder.transform(new RotateTransformation(context, config.getRotateDegree()));
            }
            //缓存设置
            builder.diskCacheStrategy(config.getDiskCacheStrategy().getStrategy()).
                    skipMemoryCache(config.isSkipMemoryCache()).
                    priority(config.getPrioriy().getPriority());
            //不需要渐显动画
//            builder.dontAnimate();
            if (null != config.getTag()) {
                builder.signature(new StringSignature(config.getTag()));
            } else {
                builder.signature(new StringSignature(objUrl.toString()));
            }
            if (null != config.getAnimator()) {
                builder.animate(config.getAnimator());
            } else if (null != config.getAnimResId()) {
                builder.animate(config.getAnimResId());
            }
            if (config.getThumbnail() > 0.0f) {
                builder.thumbnail(config.getThumbnail());
            }
            if (null != config.getErrorResId()) {
                builder.error(config.getErrorResId());
            }
            if (null != config.getPlaceHolderResId()) {
                builder.placeholder(config.getPlaceHolderResId());
            }
            if (null != config.getSize()) {
                builder.override(config.getSize().getWidth(), config.getSize().getHeight());
            }
            if (null != listener) {
//                setListener(builder, listener);
                builder.listener(listener);
            }
            if (null != config.getThumbnailUrl()) {
                BitmapRequestBuilder thumbnailRequest = Glide.with(context).load(config.getThumbnailUrl()).asBitmap();
                builder.thumbnail(thumbnailRequest).into(view);
            } else {
                setTargetView(builder, config, view);
            }
        } catch (Exception e) {
            view.setImageResource(config.getErrorResId());
        }
    }

    private static void setTargetView(GenericRequestBuilder request, GlideImageLoadConfig config, ImageView view) {
        //set targetView
        if (null != config.getSimpleTarget()) {
            request.into(config.getSimpleTarget());
        } else if (null != config.getViewTarget()) {
            request.into(config.getViewTarget());
        } else if (null != config.getNotificationTarget()) {
            request.into(config.getNotificationTarget());
        } else if (null != config.getAppWidgetTarget()) {
            request.into(config.getAppWidgetTarget());
        } else {
            request.into(view);
        }
    }

    /**
     * 加载bitmap
     *
     * @param context
     * @param url
     * @param listener
     */
    @Override
    public void loadBitmap(Context context, Object url, SimpleTarget<Bitmap> listener) {
        if (url != null) {
            Glide.with(context).
                    load(url).
                    asBitmap().
                    diskCacheStrategy(DiskCacheStrategy.NONE).
                    dontAnimate().
                    into(listener);
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务。
     */
    public static void cancelAllTasks(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 恢复所有任务
     */
    public static void resumeAllTasks(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public static void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).start();
    }

    /**
     * 清除所有缓存
     *
     * @param context
     */
    public static void cleanAll(Context context) {
        clearDiskCache(context);
        Glide.get(context).clearMemory();
    }

//    /**
//     * 获取缓存大小
//     *
//     * @param context
//     * @return
//     */
//    public static synchronized long getDiskCacheSize(Context context) {
//        long size = 0L;
//        File cacheDir = PathUtils.getDiskCacheDir(context, CacheConfig.IMG_DIR);
//
//        if (cacheDir != null && cacheDir.exists()) {
//            File[] files = cacheDir.listFiles();
//            if (files != null) {
//                File[] arr$ = files;
//                int len$ = files.length;
//
//                for (int i$ = 0; i$ < len$; ++i$) {
//                    File imageCache = arr$[i$];
//                    if (imageCache.isFile()) {
//                        size += imageCache.length();
//                    }
//                }
//            }
//        }
//
//        return size;
//    }

    //    public static void clearTarget(Context context, String uri) {
//        if (SimpleGlideModule.cache != null && uri != null) {
//            SimpleGlideModule.cache.delete(new StringSignature(uri));
//            Glide.get(context).clearMemory();
//        }
//    }
    public static void clearTarget(View view) {
        Glide.clear(view);
    }

//    public static File getTarget(Context context, String uri) {
//        return SimpleGlideModule.cache != null && uri != null ? SimpleGlideModule.cache.get(new StringSignature(uri)) : null;
//    }
}
