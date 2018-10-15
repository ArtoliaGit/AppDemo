package com.artolia.appdemo.utils;

import android.os.Looper;
import android.widget.Toast;

/**
 * @author artolia
 */
public final class ToastUtils {

    private static Toast mToast = null;

    /**
     * 显示toast在主线程中
     *
     * @param text
     * @param duration
     */
    public static void toast(final String text, final int duration) {
        if (isMainLooper()) {
            showToast(text, duration);
        } else {

        }
    }

    /**
     * 显示toast在主线程中
     *
     * @param text
     */
    public static void toast(String text) {
        toast(text, Toast.LENGTH_SHORT);
    }

    private static void showToast(String text, int duration) {
    }

    /**
     * 是否是主线程
     *
     * @return
     */
    private static boolean isMainLooper() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
