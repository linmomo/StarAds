package com.starwinwin.lib_stay_base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.starwinwin.lib_stay_base.R;
import com.starwinwin.lib_stay_base.utils.SPUtils;
import com.starwinwin.lib_stay_base.utils.SystemBarTintManager;
import com.starwinwin.lib_stay_base.utils.ToastUtil;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView, View.OnClickListener {

    protected String TAG = getClass().getSimpleName();
    protected Activity mActivity;
    protected Context mContext;
    protected SPUtils spUtils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
        this.mContext = this;
        spUtils = new SPUtils(this);
        initData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        // 状态栏沉浸，4.4+生效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);//状态背景色，可传drawable资源
        }
        initView();
    }

    /**
     * 初始化页面数据,无关view, 可以不实现
     */
    protected void initData() {
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    @Override
    public void showProgress(boolean flag, String message) {

    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showProgress(boolean flag) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(int resId) {
        ToastUtil.customShort(mContext, resId);
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.customShort(mContext, msg);
    }

    @Override
    public void close() {

    }

    @Override
    public void onClick(View v) {

    }
}
