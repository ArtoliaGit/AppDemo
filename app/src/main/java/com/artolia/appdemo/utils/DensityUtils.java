package com.artolia.appdemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 常用单位转换辅助类
 *
 * @author artolia
 */
public class DensityUtils {

    private DensityUtils() {
        throw new UnsupportedOperationException("不能实例化");
    }

    public static int dip2px(Context context, float dpVal) {
        return dp2px(context, dpVal);
    }

    public DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * dp转px
     *
     * @param context 当前上下文
     * @param dpVal dp
     * @return px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context 当前上下文
     * @param spVal sp
     * @return px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context 当前上下文
     * @param pxVal px
     * @return dp
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxVal / scale;
    }

    /**
     * px转sp
     *
     * @param context 当前上下文
     * @param pxVal px
     * @return sp
     */
    public static float px2sp(Context context, float pxVal) {
        return pxVal / context.getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 获取屏幕分辨率
     *
     * @param context 当前上下文
     * @return 分辨率
     */
    public static String getPhoneSize(final Context context) {
        DisplayMetrics dm;
        dm = context.getResources().getDisplayMetrics();

        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        int screenWidth = dm.widthPixels; //屏幕宽
        int screenHeight = dm.heightPixels; //屏幕高

        return screenWidth + "*" + screenHeight;
    }
}
