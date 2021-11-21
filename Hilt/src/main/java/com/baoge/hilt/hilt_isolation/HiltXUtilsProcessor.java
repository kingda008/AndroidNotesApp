package com.baoge.hilt.hilt_isolation;

import android.app.Application;

import com.baoge.hilt.MyApplication;
import com.baoge.hilt.proxy_Isolation.httpprocessor.ICallback;
import com.baoge.hilt.proxy_Isolation.httpprocessor.IHttpProcessor;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

import javax.inject.Inject;

public class HiltXUtilsProcessor implements IHttpProcessor {

    //imptt直接用父类
    //public HiltXUtilsProcessor(MyApplication app){
    @Inject
    public HiltXUtilsProcessor(Application app){
        x.Ext.init((MyApplication)app);
    }

    @Override
    public void post(String url, Map<String, Object> params,final ICallback callback) {
        RequestParams requestParams=new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {

    }
}
