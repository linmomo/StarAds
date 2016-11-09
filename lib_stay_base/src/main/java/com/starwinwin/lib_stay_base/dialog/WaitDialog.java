package com.starwinwin.lib_stay_base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.starwinwin.lib_stay_base.R;
import com.starwinwin.lib_stay_base.coustomview.loading.Loading;

/**
 * 等待对话框
 */
public class WaitDialog extends Dialog {

    private Loading loading;
    private TextView tvmessage;

    public WaitDialog(Context context) {
        super(context);
        init(context);
    }

    public WaitDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected WaitDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.wait_dialog, null);
        loading = (Loading) view.findViewById(R.id.loading);
        tvmessage = (TextView) view.findViewById(R.id.tv_message);
        setContentView(view);
    }

    public void setMessage(int message) {
        tvmessage.setText(message);
    }

    public void setMessage(String message) {
        tvmessage.setText(message);
    }
}
