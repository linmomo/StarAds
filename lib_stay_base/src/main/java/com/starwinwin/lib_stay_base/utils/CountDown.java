package com.starwinwin.lib_stay_base.utils;

import android.os.Handler;
import android.widget.TextView;

/**
 * 倒计时工具类
 */

public class CountDown implements Runnable {
    private TextView textView;
    private String countDownText;
    private String defaultText;
    private int remindSecond = 60;
    private Handler handler;
    private OnCountDownListener countDownListener;

    /**
     * 初始化
     * @param textView 设置的TextView
     * @param countDownText 默认显示内容，如：%d秒
     * @param remindSecond 倒计时时长
     */
    public CountDown(TextView textView, String countDownText, int remindSecond) {
        this.textView = textView;
        this.countDownText = countDownText;
        this.remindSecond = remindSecond;
        defaultText = textView.getText().toString();
        handler = new Handler();
    }

    /**
     * 开始倒计时
     */
    public void start() {
        handler.removeCallbacks(this);
        textView.setClickable(false);
        handler.post(this);
        if (countDownListener != null) {
            countDownListener.onStart();
        }
    }

    /**
     * 结束
     */
    private void stop() {
        handler.removeCallbacks(this);
        textView.setClickable(true);
        textView.setText(defaultText);
        if (countDownListener != null) {
            countDownListener.onStop();
        }
    }

    @Override
    public void run() {
        if (remindSecond > 0) {
            textView.setClickable(false);
            textView.setText(String.format(countDownText, remindSecond));
            remindSecond--;
            if (countDownListener != null) {
                countDownListener.onUpdate(remindSecond);
            }
            handler.postDelayed(this, 1000);
        } else {
            stop();
        }

    }

    public void setCountDownListener(OnCountDownListener countDownListener) {
        this.countDownListener = countDownListener;
    }

    /**
     * 回调
     */
    public interface OnCountDownListener {
        void onStart();

        void onStop();

        void onUpdate(int remindSecond);
    }
}
