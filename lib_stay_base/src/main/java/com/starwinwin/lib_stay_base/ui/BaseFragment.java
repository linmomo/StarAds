package com.starwinwin.lib_stay_base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.starwinwin.lib_stay_base.utils.SPUtils;
import com.starwinwin.lib_stay_base.utils.ToastUtil;

/**
 * 基类片段
 */
public abstract class BaseFragment extends Fragment implements IBaseView, View.OnClickListener{

    protected String TAG = getClass().getSimpleName();
    public Activity mActivity;
    public Context mContext;
    protected SPUtils spUtils;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
        this.mContext = context;
        spUtils = new SPUtils(mContext);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view, savedInstanceState);
    }

    //依附的activity创建完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //初始化控件
    public abstract View initViews(View view, Bundle savedInstanceState);

    // 初始化数据, 可以不实现
    public void initData() {

    }

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
