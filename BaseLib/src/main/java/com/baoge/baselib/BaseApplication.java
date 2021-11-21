package com.baoge.baselib;

import android.app.Application;

public class BaseApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
    }
}
