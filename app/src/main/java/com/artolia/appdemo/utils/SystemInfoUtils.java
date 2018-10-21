package com.artolia.appdemo.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * 系统信息工具类
 *
 * @author artolia
 */
public class SystemInfoUtils {

    private SystemInfoUtils() {}

    public static String getUserAgent(Context context, String appId) {
        String userAgent = appId + //应用名称
                CommonConsts.SEMICOLON +
                AppTools.getVersionName(context) + //App版本
                CommonConsts.SEMICOLON +
                CommonConsts.SourceType + //平台
                CommonConsts.SEMICOLON +
                SystemInfoUtils.getOSVersionName() + //OS版本
                CommonConsts.SEMICOLON +
                SystemInfoUtils.getOSVersionDisplayName() + //OS显示版本
                CommonConsts.SEMICOLON +
                SystemInfoUtils.getBrandName() + //品牌厂商
                CommonConsts.SEMICOLON +
                SystemInfoUtils.getModelName() + //设备
                CommonConsts.SEMICOLON +
                DensityUtils.getPhoneSize(context) + //分辨率
                CommonConsts.SEMICOLON +
                SystemInfoUtils.getAppSource(context, CommonConsts.APP_SOURCE) + //分发渠道
                CommonConsts.SEMICOLON +
                SystemInfoUtils.getNetType(context) + //网络类型
                CommonConsts.SEMICOLON;

        /**
         * User-Agent
         * 格式：
         * 应用名称；应用版本；平台；os版本；os版本名称；厂商；机型；分辨率(宽*高)；安装渠道；网络；
         * 示例：
         * HET;2.2.0;Android;4.2.2;N7100XXUEMI6BYTuifei;samsung;GT-I9300;480*800;360;WIFI;
         */
        return userAgent;
    }

    /**
     * 获取渠道，用于打包
     *
     * @param context
     * @param metaName
     * @return
     */
    public static String getAppSource(Context context, String metaName) {
        String result = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo.metaData == null) {
                return "Android";
            }
            result = applicationInfo.metaData.getString(metaName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static String getNetType(final Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//		networkInfo.getDetailedState();//获取详细状态。
//		networkInfo.getExtraInfo();//获取附加信息。
//		networkInfo.getReason();//获取连接失败的原因。
//		networkInfo.getType();//获取网络类型(一般为移动或Wi-Fi)。
//		networkInfo.getTypeName();//获取网络类型名称(一般取值“WIFI”或“MOBILE”)。
//		networkInfo.isAvailable();//判断该网络是否可用。
//		networkInfo.isConnected();//判断是否已经连接。
//		networkInfo.isConnectedOrConnecting();//：判断是否已经连接或正在连接。
//		networkInfo.isFailover();//：判断是否连接失败。
//		networkInfo.isRoaming();//：判断是否漫游
        return networkInfo == null ? "" : networkInfo.getTypeName();
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取设备制造商名称
     *
     * @return 制造商名称
     */
    public static String getManufacturerName() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备名称
     *
     * @return 设备名称
     */
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * 获取产品名称
     *
     * @return 产品名称
     */
    public static String getProductName() {
        return Build.PRODUCT;
    }

    /**
     * 获取品牌名称
     *
     * @return 品牌名称
     */
    public static String getBrandName() {
        return Build.BRAND;
    }

    /**
     * 获取操作系统版本号
     *
     * @return 版本号
     */
    public static int getOSVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取操作系统版本名
     *
     * @return 操作系统版本名
     */
    public static String getOSVersionName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取操作系统版本显示名
     *
     * @return 版本显示名
     */
    public static String getOSVersionDisplayName() {
        return Build.DISPLAY;
    }

    /**
     * 获取主机地址
     *
     * @return 主机地址
     */
    public static String getHost() {
        return Build.HOST;
    }

    public final class CommonConsts {
        /**
         * 设备分类
         */
        public static final String SourceType = "Android";

        /**
         * 空格字符
         */
        public static final String SPACE = " ";

        /**
         * 逗号
         */
        public static final String COMMA = ",";

        /**
         * 句点
         */
        public static final String PERIOD = ".";

        /**
         * 左引号
         */
        public static final String LEFT_QUOTES = "'";

        /**
         * 右引号
         */
        public static final String RIGHT_QUOTES = "'";

        /**
         * 左圆括号.
         */
        public static final String LEFT_PARENTHESIS = "(";

        /**
         * 右圆括号.
         */
        public static final String RIGHT_PARENTHESIS = ")";

        /**
         * 左方括号.
         */
        public static final String LEFT_SQUARE_BRACKET = "[";

        /**
         * 右方括号.
         */
        public static final String RIGHT_SQUARE_BRACKET = "]";

        /**
         * 换行符
         */
        public static final String LINE_BREAK = "\r\n";

        /**
         * 换行符
         */
        public static final String LINE_BREAK_SHORT = "\n";

        /**
         * 行
         */
        public static final String LINE_ZH_CN = "行";

        /**
         * 问号.
         */
        public static final String QUESTION_MARK = "?";

        /**
         * 符号&.
         */
        public static final String AMPERSAND = "&";

        /**
         * 等于号
         */
        public static final String EQUAL = "=";

        /**
         * 分号.
         */
        public static final String SEMICOLON = ";";

        /**
         * App渠道对应的meta名字
         */
        public static final String APP_SOURCE = "AppSource";

        /**
         * 私有的构造方法
         */
        private CommonConsts() {
        }
    }
}
