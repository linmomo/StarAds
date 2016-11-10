package com.starwinwin.lib_stay_base.coustomview;

import android.view.View;

public abstract class TitleBarOnNoDoubleClickListener implements View.OnClickListener {
    private int mThrottleFirstTime = 600;
    private long mLastClickTime = 0;

    public TitleBarOnNoDoubleClickListener() {
    }

    public TitleBarOnNoDoubleClickListener(int throttleFirstTime) {
        mThrottleFirstTime = throttleFirstTime;
    }

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastClickTime > mThrottleFirstTime) {
            mLastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);
}