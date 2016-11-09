package com.starwinwin;

import android.app.Application;

import com.starwinwin.constant.AppConstant;
import com.starwinwin.lib_data.http.OkGo;
import com.starwinwin.lib_stay_base.imageloader.GalleryImageLoader;
import com.starwinwin.lib_stay_base.imageloader.GalleryPauseOnScrollListener;
import com.starwinwin.lib_stay_base.log.KLog;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

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
        //初始化日志，也可不初始化，
        //第二个参数为全局tag，设置后全局日志使用该tag，最高优先级
        KLog.init(true, "lin");
        //初始化gallery
        initGalleryFinal();
        //初始化OkHttp
        OkGo.init(this);
        try {
            OkGo.getInstance()
                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取本appContext
    public static AdsApplication getInstance() {
        return mInstance;
    }

    /**
     * 初始化galleryFinal配置
     */
    private void initGalleryFinal() {
        //主题配置
//		ThemeConfig themeConfig = ThemeConfig.CYAN;
        //功能配置
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setMutiSelectMaxSize(5)
                .setEnableEdit(false)//开启编辑功能
                .setEnableCrop(false)//开启裁剪功能
                .setEnableRotate(false)//开启旋转功能
                .setEnableCamera(true)//开启相机功能
                .setEnablePreview(false)//是否预览
                .build();
        //核心功能配置
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), new GalleryImageLoader(), ThemeConfig.DARK)
                .setTakePhotoFolder(new File(AppConstant.DIR_TEMP))
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(new GalleryPauseOnScrollListener(false, true))
                .build();
        //初始化
        GalleryFinal.init(coreConfig);
    }

    /**
     * 生成app的根文件夹
     */
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
