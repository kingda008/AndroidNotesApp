package com.baoge.hilt.proxy_Isolation.httpprocessor;

import java.util.Map;

public interface IHttpProcessor {
    void post(String url, Map<String , Object> params,ICallback callback);
    void get(String url, Map<String,Object> params,ICallback callback);
}
