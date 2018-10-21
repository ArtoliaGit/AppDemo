package com.artolia.appdemo.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.net.InetAddress;
import java.util.List;
import java.util.Locale;

/**
 * 应用工具类
 *
 * @author artolia
 */
public class AppTools {

    private AppTools() {}

    /**
     * 界面跳转
     *
     * @param context 当前环境
     * @param forwardActivity 跳转的界面
     */
    public static void startForwardActivity(Activity context, Class<?> forwardActivity) {
        startForwardActivity(context, forwardActivity, false);
    }

    public static void startForwardActivity(Activity context, Class<?> forwardActivity,
                                            Boolean isFinish) {
        Intent intent = new Intent(context, forwardActivity);
        context.startActivity(intent);
        if (isFinish) {
            context.finish();
        }
    }

    public static void startForwardActivity(Activity context, Class<?> forwardActivity,
                                            Bundle bundle, Boolean isFinish, int animin, int animout) {
        Intent intent = new Intent(context, forwardActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (isFinish) {
            context.finish();
        }
        try {
            context.overridePendingTransition(animin, animout);
        } catch (Exception e) {
           e.printStackTrace();
        }

    }

    public static void startForwardActivity(Activity context, Class<?> forwardActivity,
                                            Bundle bundle, Boolean isFinish) {
        Intent intent = new Intent(context, forwardActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (isFinish) {
            context.finish();
        }
    }

    public static void startForwardActivity(Activity context, Class<?> forwardActivity,
                                            int requestCode, Bundle bundle, Boolean isFinish) {
        Intent intent = new Intent(context, forwardActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivityForResult(intent, requestCode);
        if (isFinish) {
            context.finish();
        }
    }

    public static void startForwardActivity(Activity context, Class<?> forwardActivity,
                                            int requestCode, Bundle bundle, Boolean isFinish,
                                            int animin, int animout) {
        Intent intent = new Intent(context, forwardActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivityForResult(intent, requestCode);
        if (isFinish) {
            context.finish();
        }
        try {
            context.overridePendingTransition(animin, animout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前程序的版本名称
     *
     * @param context 环境
     * @return 版本名称
     */
    public static String getVersionName(Context context) {
        String version;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            version = "";
        }
        return version;
    }

    /**
     * 获取当前程序的版本号
     *
     * @param context 环境
     * @return 版本号
     */
    public static long getVersionCode(Context context) {
        long versionCode;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.getLongVersionCode();
        } catch (Exception e) {
            e.printStackTrace();
            versionCode = 999L;
        }
        return versionCode;
    }

    /**
     * 获取物理地址
     *
     * @param context 环境
     * @return mac
     */
    public static String getLongMacAddress(Activity context) {
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        return mac;
    }

    /**
     * 获取ip地址
     *
     * @param context 环境
     * @return ip
     */
    public static String getLocalIpAddress(Activity context) {
        try {
            WifiManager wifi;
            wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            int ipAddress = info.getIpAddress();
            String Ipv4Address = InetAddress
                    .getByName(
                            String.format(Locale.US, "%d.%d.%d.%d", (ipAddress & 0xff),
                                    (ipAddress >> 8 & 0xff),
                                    (ipAddress >> 16 & 0xff),
                                    (ipAddress >> 24 & 0xff))
                    ).getHostAddress();
            return Ipv4Address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取栈顶activity
     *
     * @param context 环境
     * @return 栈顶activity
     */
    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            return runningTaskInfos.get(0).topActivity.getClassName();
        } else {
            return "";
        }
    }

    /**
     * 判断activity是否在当前栈顶
     *
     * @param context 当前环境
     * @param className 类名
     * @return 判断
     */
    public static boolean isTopActivity(Context context, String className) {
        final String topActivity = getTopActivity(context);
        return className.equals(topActivity);
    }

    /**
     * 设置activity全屏显示
     * @param activity activity引用
     * @param isFull true为全屏，false为非全屏
     */
    public static void setFullScreen(Activity activity, boolean isFull) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (isFull) {
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(params);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            params.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(params);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 隐藏系统标题栏
     * @param activity activity对象
     */
    public static void hideTitleBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 设置activity的显示方向为垂直方向
     * @param activity activity对象
     */
    public static void setScreenVertical(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 设置activity的显示方向为水平方向
     * @param activity activity对象
     */
    public static void setScreenHorizontal(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 使UI适配输入法
     * @param activity activity对象
     */
    public static void adjustSoftInput(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * 安装指定apk文件
     *
     * @param activity activity对象
     * @param apkFile apk文件对象
     */
    public static void install(Activity activity, File apkFile) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    /**
     * 默认隐藏软键盘
     *
     * @param activity activity对象
     */
    public static void hideSoftInput(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * 多种隐藏软键盘方法的一种
     *
     * @param activity activity对象
     * @param token binder
     */
    public static void hideSoftInput(Activity activity, IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标对比，来判断是否隐藏软键盘
     * @param v view
     * @param event 点击事件
     * @return true隐藏
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    /**
     * 判断apk包是否已经安装
     *
     * @param context 环境
     * @param packageName 包名
     * @return true已经安装
     */
    public static boolean isPackageExists(Context context, String packageName) {
        if (null == packageName || "".equals(packageName)) {
            throw new IllegalArgumentException("包名不能为空");
        }
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return null != info;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取应用程序名称
     *
     * @param context 环境
     * @return 应用名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在activity中获取metadata
     *
     * @param activity activity对象
     * @param key 键
     * @return metadata
     */
    public static Object getMetaDataAsObject(Activity activity, String key) {
        ActivityInfo info = getActivtyInfo(activity);
        return info == null ? null : info.metaData.get(key);
    }

    /**
     * 在activity中获取metadata
     *
     * @param activity activity对象
     * @param key 键
     * @return metadata
     */
    public static String getMetaDataAsString(Activity activity, String key) {
        ActivityInfo info = getActivtyInfo(activity);
        return info == null ? null : info.metaData.getString(key);
    }

    /**
     * 在activity中获取metadata
     *
     * @param activity activity对象
     * @param key 键
     * @return metadata
     */
    public static int getMetaDataAsInt(Activity activity, String key) {
        ActivityInfo info = getActivtyInfo(activity);
        return info == null ? null : info.metaData.getInt(key);
    }

    /**
     * 在activity中获取metadata
     *
     * @param activity activity对象
     * @param key 键
     * @return metadata
     */
    public static boolean getMetaDataAsBoolean(Activity activity, String key) {
        ActivityInfo info = getActivtyInfo(activity);
        return info == null ? null : info.metaData.getBoolean(key);
    }

    private static ActivityInfo getActivtyInfo(Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        try {
            return packageManager.getActivityInfo(activity.getComponentName(),
                    PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
