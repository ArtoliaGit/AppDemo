package com.artolia.appdemo;

import android.app.Application;
import android.widget.Toast;

import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;
import com.xuexiang.xupdate.utils.UpdateUtils;

/**
 * @author artolia
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initUpdate() {
        XUpdate.get()
                .isWifiOnly(true)
                .isGet(true)
                .isAutoMode(false)
                .param("versionCode", UpdateUtils.getVersionCode(this))
                .param("appKey", getPackageName())
                .setOnUpdateFailureListener(new OnUpdateFailureListener() {
                    @Override
                    public void onFailure(UpdateError error) {
//                        Toast
                    }
                });
    }
}
