package com.artolia.appdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.artolia.appdemo.R;

/**
 * activity公共类
 *
 * @author artolia
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initArgs();
        initViews();
        initListeners();
    }

    @Override
    protected void onStop() {
        if (isFinishing()) {
            onRelease();
        }
        super.onStop();
    }

    /**
     * 获取布局资源id
     *
     * @return 返回布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化参数
     */
    protected abstract void initArgs();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化监听
     */
    protected abstract void initListeners();

    /**
     * 资源释放
     */
    protected abstract void onRelease();

}
