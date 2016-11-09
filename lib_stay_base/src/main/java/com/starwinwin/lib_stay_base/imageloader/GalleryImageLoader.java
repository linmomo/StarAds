package com.starwinwin.lib_stay_base.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;

import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * galleryfinal图片加载类
 */

public class GalleryImageLoader implements cn.finalteam.galleryfinal.ImageLoader {

    private int tagkey = 123456;

    @Override
    public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {

        Glide.with(activity)                             //配置上下文
                .load("file://" + path)                  //设置图片路径
                .override(width, height)
                .error(defaultDrawable)           //设置错误图片
                .placeholder(defaultDrawable)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不缓存到Sd卡
                .skipMemoryCache(true)
                .into(new ImageViewTarget<GlideDrawable>(imageView) {
                    @Override
                    protected void setResource(GlideDrawable resource) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void setRequest(Request request) {
                        imageView.setTag(tagkey, request);
                    }

                    @Override
                    public Request getRequest() {
                        return (Request) imageView.getTag(tagkey);
                    }
                });
    }

    @Override
    public void clearMemoryCache(Context context) {
        ImageLoaderFactory.getLoader().cleanAll(context);
    }
}
