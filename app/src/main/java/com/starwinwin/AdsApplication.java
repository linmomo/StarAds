package com.starwinwin;

import android.app.Application;

/**
 * AdsApplication
 */

public class AdsApplication extends Application {
    private static AdsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
