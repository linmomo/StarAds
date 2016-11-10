package com.starwinwin.lib_stay_base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.starwinwin.lib_stay_base.R;
import com.starwinwin.lib_stay_base.dialog.WaitDialog;
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
    private SystemBarTintManager tintManager;
    private WaitDialog mWaitDialog;
    private boolean mIsVisible;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
        this.mContext = this;
        spUtils = new SPUtils(this);
        initBeforeData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        // 透明状态栏，4.4+生效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        tintManager = new SystemBarTintManager(this);
        setStatusBar(true, true, R.color.colorPrimaryDark);
        //视图可见，可显示弹框
        mIsVisible = true;
        initView();
        setData();
    }


    /**
     * 初始化数据，无关view，setContentView之前
     */
    protected void initBeforeData() {
    }

    /**
     * 设置数据
     */
    protected void setData() {
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    @Override
    protected void onPause() {
        mIsVisible = false;
        hideProgress();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mIsVisible = true;
        super.onResume();
    }

    /**
     * @param isFit       是否留出状态栏位置
     * @param isTint      是否着色
     * @param statusColor 状态栏颜色
     */
    public void setStatusBar(boolean isFit, boolean isTint, int statusColor) {
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            parentView.setFitsSystemWindows(isFit);
        }
        if (tintManager != null) {
            tintManager.setStatusBarTintEnabled(isTint);
            tintManager.setStatusBarTintResource(statusColor);//状态背景色，可传drawable资源
        }
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (mIsVisible) {
            if (mWaitDialog == null) {
                mWaitDialog = new WaitDialog(this, R.style.WaitDialog);
            }
            mWaitDialog.setMessage(message);
            mWaitDialog.setCancelable(flag);
            mWaitDialog.show();
        }
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true, getString(R.string.loading));
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, getString(R.string.loading));
    }

    @Override
    public void hideProgress() {
        if (mIsVisible && mWaitDialog != null) {
            try {
                mWaitDialog.dismiss();
                mWaitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
