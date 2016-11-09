package com.starwinwin.constant;

import android.os.Environment;

/**
 * 全局常量
 */
public class AppConstant {
    /**
     * 本应用的文件图片都放到这个路径
     */
    public static final String APPDIR = Environment.getExternalStorageDirectory() + "/starads/";
    //图片
    public static final String DIR_IMAGE = APPDIR + "images/";
    //视频
    public static final String DIR_VIDEO = APPDIR + "video/";
    // SD卡中的临时文件夹路径
    public static final String DIR_TEMP = APPDIR + "temp";
}
