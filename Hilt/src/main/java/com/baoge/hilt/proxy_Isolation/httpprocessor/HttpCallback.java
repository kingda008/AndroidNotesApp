package com.baoge.hilt.proxy_Isolation.httpprocessor;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 加一个泛型 ，方便后期的使用
 */
public abstract class HttpCallback<Result> implements ICallback {
    @Override
    public void onSuccess(String result) {
        // IMPTT 1 ,得到调用者会用什么样的bean接受数据
        Class<?> clz = analysisClassInfo(this);
        //imptt 2,string转成javabean
        Gson gson = new Gson();
        Result objectResult = (Result) gson.fromJson(result,clz);
        //3 交给程序员 业务处理

        onSuccess(objectResult);
    }

    public abstract void onSuccess(Result objResult);

    /**
     * 通过该方法得到输入参数的实际类型
     *
     * @param object
     * @return
     */
    private Class<?> analysisClassInfo(Object object) {
        //返回一个类型对象，这个对象可以得到原始类，参数化，数组，类型变量，基本数据类型等
        Type type = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        //这里的params是个数组对应的HttpCallback尖括号中可以传递多个类型
        return (Class<?>) params[0];
    }

    @Override
    public void onFailure(String error) {

    }
}
