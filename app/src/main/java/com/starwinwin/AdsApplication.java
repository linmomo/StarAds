package com.starwinwin;

import android.app.Application;

import com.starwinwin.constant.AppConstant;

import java.io.File;

/**
 * AdsApplication
 */

public class AdsApplication extends Application {

    private static AdsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        makeAppFolder();
    }

    // 获取本appContext
    public static AdsApplication getInstance() {
        return mInstance;
    }

    /**
     * 生成app的根文件夹
     * */
    private void makeAppFolder() {
        File file = new File(AppConstant.APPDIR);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        File file1 = new File(AppConstant.DIR_IMAGE);
        if (!file1.isDirectory()) {
            file1.mkdirs();
        }
        File file2 = new File(AppConstant.DIR_VIDEO);
        if (!file2.isDirectory()) {
            file2.mkdirs();
        }

    }
}
