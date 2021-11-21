package com.baoge.hilt.hilt_isolation;

import com.baoge.hilt.proxy_Isolation.httpprocessor.ICallback;

import java.util.Map;

public interface IHiltHttpProcessor {
    void post(String url, Map<String , Object> params, ICallback callback);
    void get(String url, Map<String,Object> params,ICallback callback);
}
