package com.baoge.notes.reflection.proxy;


import com.baoge.baselib.LogUtil;

/**
 * 这个是被代理类，实现了接口的所有方法
 */
public class RealSubject implements Subject{
    @Override
    public void say(String content) {
        LogUtil.d("say "+content);
    }

    @Override
    public String getContent(String content) {
        LogUtil.d("getContent "+content);
        return "getContent:"+content;
    }
}
