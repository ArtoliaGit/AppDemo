package com.artolia.appdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artolia.appdemo.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }
}
