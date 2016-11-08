package com.starwinwin.lib_stay_base.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

/**
 * 图片加载接口
 */
public interface ImageLoader {

    void loadUrlImage(ImageView view, String imageUrl);

    void loadLocalImage(ImageView view, File file);

    void loadCircleImage(ImageView view, String imageUrl);

    void loadRoundImage(ImageView view, String imageUrl);

    void loadBitmap(Context context, Object url, SimpleTarget<Bitmap> listener);
}
