package com.baoge.hilt;

import android.app.Application;

import com.baoge.baselib.BaseApplication;
import com.baoge.hilt.annotation.BindOkhttp;
import com.baoge.hilt.hilt_isolation.IHiltHttpProcessor;
import com.baoge.hilt.proxy_Isolation.httpprocessor.HttpHelper;
import com.baoge.hilt.proxy_Isolation.httpprocessor.OkHttpProcessor;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends BaseApplication {

    //根据这个限定符指定
    @BindOkhttp
    @Inject
    IHiltHttpProcessor iHiltHttpProcessor;
    @Override
    public void onCreate() {
        super.onCreate();
//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new XUtilsProcessor(this));
        HttpHelper.init(new OkHttpProcessor());

        //HILT用法


    }

    public IHiltHttpProcessor getiHiltHttpProcessor(){
        return iHiltHttpProcessor;
    }
}
