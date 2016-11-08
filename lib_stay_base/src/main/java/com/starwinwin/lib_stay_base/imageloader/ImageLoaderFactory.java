package com.starwinwin.lib_stay_base.imageloader;

/**
 * 图片加载工厂
 */
public class ImageLoaderFactory {

    private static volatile ImageLoader sInstance;

    private ImageLoaderFactory() {

    }

    /**
     * 获取图片加载器
     *
     * @return
     */
    public static ImageLoader getLoader() {
        if (sInstance == null) {
            synchronized (ImageLoaderFactory.class) {
                if (sInstance == null) {
                    sInstance = new GlideImageLoader();
                }
            }
        }
        return sInstance;
    }
}
