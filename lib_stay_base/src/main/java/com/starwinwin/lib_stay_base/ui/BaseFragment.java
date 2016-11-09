package com.starwinwin.lib_stay_base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.starwinwin.lib_stay_base.R;
import com.starwinwin.lib_stay_base.dialog.WaitDialog;
import com.starwinwin.lib_stay_base.utils.SPUtils;
import com.starwinwin.lib_stay_base.utils.ToastUtil;

/**
 * 基类片段
 */
public abstract class BaseFragment extends Fragment implements IBaseView, View.OnClickListener {

    protected String TAG = getClass().getSimpleName();
    protected Activity mActivity;
    protected Context mContext;
    protected SPUtils spUtils;
    private WaitDialog mWaitDialog;
    private boolean mIsVisible;

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

    @Override
    public void onPause() {
        mIsVisible = false;
        hideProgress();
        super.onPause();
    }

    @Override
    public void onResume() {
        mIsVisible = true;
        super.onResume();
    }

    //初始化控件
    public abstract View initViews(View view, Bundle savedInstanceState);

    // 初始化数据, 可以不实现
    public void initData() {

    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (mIsVisible) {
            if (mWaitDialog == null) {
                mWaitDialog = new WaitDialog(mContext, R.style.WaitDialog);
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
