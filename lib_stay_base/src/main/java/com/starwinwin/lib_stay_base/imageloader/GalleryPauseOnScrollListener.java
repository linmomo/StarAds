package com.starwinwin.lib_stay_base.imageloader;

import com.bumptech.glide.Glide;

import cn.finalteam.galleryfinal.PauseOnScrollListener;

/**
 * Glide滚动监听
 */

public class GalleryPauseOnScrollListener extends PauseOnScrollListener {

    public GalleryPauseOnScrollListener(boolean pauseOnScroll, boolean pauseOnFling) {
        super(pauseOnScroll, pauseOnFling);
    }

    @Override
    public void resume() {
        if (getActivity() != null) {
            Glide.with(getActivity()).resumeRequests();
        }
    }

    @Override
    public void pause() {
        if (getActivity() != null) {
            Glide.with(getActivity()).pauseRequests();
        }
    }
}
