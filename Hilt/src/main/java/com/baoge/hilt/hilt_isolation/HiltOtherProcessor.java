package com.baoge.hilt.hilt_isolation;

import com.baoge.hilt.proxy_Isolation.httpprocessor.ICallback;
import com.baoge.hilt.proxy_Isolation.httpprocessor.IHttpProcessor;

import java.util.Map;

public class HiltOtherProcessor implements IHttpProcessor {
    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        //.....
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {

    }
}
