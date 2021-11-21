package com.baoge.notes;

import android.app.Application;

import com.baoge.baselib.ToastUtil;

public class MyApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ToastUtil.init(this);
    }
}
