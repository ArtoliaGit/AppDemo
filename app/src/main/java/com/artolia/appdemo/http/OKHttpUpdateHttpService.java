package com.artolia.appdemo.http;

import android.support.annotation.NonNull;

import com.xuexiang.xupdate.proxy.IUpdateHttpService;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpUpdateHttpService implements IUpdateHttpService {

    OkHttpClient client = new OkHttpClient();

    public OKHttpUpdateHttpService() {

    }

    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, Object> params, @NonNull Callback callBack) {
        Request request = new Request.Builder()
                .url("")
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, Object> params, @NonNull Callback callBack) {

    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull DownloadCallback callback) {

    }
}
