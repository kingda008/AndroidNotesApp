package com.baoge.hilt.hilt_isolation;

import android.os.Handler;
import android.os.Looper;

import com.baoge.hilt.proxy_Isolation.httpprocessor.ICallback;
import com.baoge.hilt.proxy_Isolation.httpprocessor.IHttpProcessor;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HiltOkHttpProcessor implements IHttpProcessor {

    private OkHttpClient mOkHttpClient;
    private Handler myHandler;

    @Inject
    public HiltOkHttpProcessor(){
        mOkHttpClient=new OkHttpClient();
        myHandler=new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(String url, Map<String, Object> params,final ICallback callback) {
        final RequestBody requestBody=appendBody(params);
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result=response.body().string();
                if(response.isSuccessful()){
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                }else{
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(result);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure("onFailure");
                    }
                });
            }

        });
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {

    }

    private RequestBody appendBody(Map<String, Object> params){
        FormBody.Builder body=new FormBody.Builder();
        if(params==null || params.isEmpty()){
            return body.build();
        }
        for(Map.Entry<String, Object> entry:params.entrySet()){
            body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }

}
