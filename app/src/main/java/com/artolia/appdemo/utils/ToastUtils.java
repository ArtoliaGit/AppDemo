package com.artolia.appdemo.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.artolia.appdemo.R;

/**
 * @author artolia
 */
public final class ToastUtils {

    private static Toast mToast = null;

    /**
     * 主线程handler
     */
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());

    /**
     * 显示toast在主线程中
     *
     * @param text
     * @param duration
     */
    public static void toast(final Context context, final String text, final int duration) {
        if (isMainLooper()) {
            showToast(context, text, duration);
        } else {
            sMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    showToast(context, text, duration);
                }
            });
        }
    }

    /**
     * 显示toast在主线程中
     *
     * @param text
     */
    public static void toast(final Context context, final String text) {
        toast(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 是否是主线程
     *
     * @return
     */
    private static boolean isMainLooper() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 显示单一的toast
     *
     * @param text
     * @param duration
     */
    private static void showToast(Context context, String text, int duration) {
        if (mToast == null) {
            mToast = makeText(context, text, duration);
        } else {
            ((TextView) mToast.getView().findViewById(R.id.tv_info)).setText(text);
        }
        mToast.show();
    }

    /**
     * 构建toast
     *
     * @param context
     * @param msg
     * @param duration
     * @return
     */
    private static Toast makeText(Context context, String msg, int duration) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        Toast toast = new Toast(context);
        toast.setView(view);
        TextView tv = view.findViewById(R.id.tv_info);
        tv.setText(msg);
        toast.setDuration(duration);
        return toast;
    }

    /**
     * 取消toast显示
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
