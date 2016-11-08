package com.starwinwin.lib_stay_base.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.starwinwin.lib_stay_base.R;

/**
 * 自定义Toast
 */

public class ToastUtil {

    private static Toast mToast;
    private static TextView tv_content;

    /**
     * 自定义Toast
     *
     * @param String   message
     * @param duration
     */
    private static void showCustomToast(Context context, CharSequence message, int duration) {
        if (mToast == null) {
            mToast = new Toast(context);
            View v = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
            tv_content = (TextView) v.findViewById(R.id.toast_content);
            mToast.setDuration(duration);
            // mToast.setGravity(Gravity.BOTTOM, 0, 400);
            mToast.setGravity(Gravity.BOTTOM, 0, 500);
            mToast.setView(v);
            tv_content.setText(message);
        } else {
            tv_content.setText(message);
        }
        mToast.show();
    }

    /**
     * 长时间显示自定义Toast
     *
     * @param message
     */
    public static void customLong(Context context, CharSequence message) {
        showCustomToast(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示自定义Toast
     *
     * @param message
     */
    public static void customLong(Context context, int message) {
        showCustomToast(context, context.getString(message), Toast.LENGTH_LONG);
    }

    /**
     * 短时间显示自定义Toast
     *
     * @param message
     */
    public static void customShort(Context context, CharSequence message) {
        showCustomToast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示自定义Toast
     *
     * @param message
     */
    public static void customShort(Context context, int message) {
        showCustomToast(context, context.getString(message), Toast.LENGTH_SHORT);
    }

    /**
     * 自定义时间显示自定义Toast
     *
     * @param message
     */
    public static void customShow(Context context, CharSequence message, int duration) {
        showCustomToast(context, message, duration);
    }


    /**
     * 自定义时间显示自定义Toast
     *
     * @param message
     */
    public static void customShow(Context context, int message, int duration) {
        showCustomToast(context, context.getString(message), duration);
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        Toast.makeText(context, message, duration).show();
    }
}
