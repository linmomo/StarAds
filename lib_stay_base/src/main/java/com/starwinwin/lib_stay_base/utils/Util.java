package com.starwinwin.lib_stay_base.utils;

import android.content.Context;
import com.starwinwin.lib_stay_base.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * 常用自定义工具类
 */

public class Util {

    private static long lastClickTime;

    /**
     * 判断是否为连击
     *
     * @return boolean
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static void setPtr(Context context, PtrFrameLayout ptr) {

        // header
        final MaterialHeader header = new MaterialHeader(context);
        int[] colors = context.getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, SizeUtils.dp2px(context, 15), 0, SizeUtils.dp2px(context, 10));
        header.setPtrFrameLayout(ptr);

        //阻尼系数:默认: 1.7f，越大，感觉下拉时越吃力
        ptr.setResistance(1.7f);
        //触发刷新时移动的位置比例:默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作
        ptr.setRatioOfHeaderHeightToRefresh(1.2f);
        //回弹延时：默认 200ms，回弹到刷新高度所用时间
        ptr.setDurationToClose(200);
        //头部回弹时间:默认1000ms
        ptr.setDurationToCloseHeader(1000);
        //下拉刷新 / 释放刷新:默认为释放刷新
        ptr.setPullToRefresh(false);
        //刷新是保持头部:默认值 true.
        ptr.setKeepHeaderWhenRefresh(true);
    }

    /**
     * 经纬度测距
     *
     * @param jingdu1
     * @param weidu1
     * @param jingdu2
     * @param weidu2
     * @return 两个坐标的距离
     */
    public static double distance(double jingdu1, double weidu1,
                                  double jingdu2, double weidu2) {
        double a, b, R;
        R = 6378137; // 地球半径
        weidu1 = weidu1 * Math.PI / 180.0;
        weidu2 = weidu2 * Math.PI / 180.0;
        a = weidu1 - weidu2;
        b = (jingdu1 - jingdu2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(weidu1) * Math.cos(weidu2) * sb2 * sb2));
        return d;
    }
}
