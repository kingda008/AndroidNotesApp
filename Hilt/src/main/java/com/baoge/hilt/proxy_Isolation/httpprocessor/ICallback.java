package com.baoge.hilt.proxy_Isolation.httpprocessor;

/**
 * 回调接口
 */
public interface ICallback {
    //inputstream 还是 string，还是object 还是泛型T
    /**
     * imptt 使用所有都能用的String .为什么使用泛型呢？
     * 提供给程序员使用泛型；将String转成泛型 再转成对应对象
     *
     *
     */

    /**
     * 后面
     * @param result
     */
    void onSuccess(String result);
    void onFailure(String error);
}
