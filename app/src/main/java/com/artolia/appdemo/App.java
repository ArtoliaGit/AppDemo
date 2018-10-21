package com.artolia.appdemo;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.artolia.appdemo.constant.AppConstant;
import com.artolia.appdemo.interceptor.CustomSignInterceptor;
import com.artolia.appdemo.utils.SystemInfoUtils;
import com.artolia.appdemo.utils.ToastUtils;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;
import com.xuexiang.xupdate.utils.UpdateUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.utils.HttpLog;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author artolia
 */
public class App extends Application {

    private static Application app = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initEasyHttp() {
        //默认初始化,必须调用
        EasyHttp.init(this);

        String url = "";

        //全局设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.put("User-Agent", SystemInfoUtils.getUserAgent(this, AppConstant.APPID));
        //设置请求参数
        HttpParams params = new HttpParams();
        params.put("appId", AppConstant.APPID);
        EasyHttp.getInstance()
                .debug("RxEasyHttp", true)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3) //默认网络不好重连3次
                .setRetryDelay(500) //每次延迟500ms重连
                .setRetryIncreaseDelay(500) //每次延时叠加500ms
                .setBaseUrl(url)
                .setCacheDiskConverter(new SerializableDiskConverter()) //默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024) //设置缓存大小为50M
                .setCacheVersion(1) //缓存版本为1
                .setHostnameVerifier(new UnSafeHostnameVerifier(url)) //全局访问规则
                .setCertificates() //信任所有证书
//                .addConverterFactory(GsonConverterFactory.create(gson)) //Gson转化
                .addCommonHeaders(headers) //设置全局公共请求头
                .addCommonParams(params) //设置全局公共参数
                .addInterceptor(new CustomSignInterceptor()); //添加参数签名拦截器
    }

    private void initUpdate() {
//        XUpdate.get()
//                .isWifiOnly(true)
//                .isGet(true)
//                .isAutoMode(false)
//                .param("versionCode", UpdateUtils.getVersionCode(this))
//                .param("appKey", getPackageName())
//                .setOnUpdateFailureListener(new OnUpdateFailureListener() {
//                    @Override
//                    public void onFailure(UpdateError error) {
//                        ToastUtils.toast(App.this, error.toString());
//                    }
//                })
//                .setIUpdateHttpService(new OK);
    }

    public class UnSafeHostnameVerifier implements HostnameVerifier {
        private String host;

        public UnSafeHostnameVerifier(String host) {
            this.host = host;
            HttpLog.i("########## UnSafeHostnameVerifier " + host);
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            HttpLog.i("############# verify " + hostname + " " + this.host);
            if (this.host == null || "".equals(this.host) || !this.host.contains(hostname)) {
                return false;
            }
            return true;
        }
    }

    public static Context getAppContext() {
        if (app == null) {
            return null;
        }
        return app.getApplicationContext();
    }
}
