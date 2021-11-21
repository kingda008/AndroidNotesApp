package com.baoge.notes.reflection.proxy;


import com.baoge.baselib.LogUtil;

/**
 * 代理类。  必须要实现  “被代理类”  相同的接口；
 */
public class StaticProxySubject implements Subject{

    private Subject subject;

    public StaticProxySubject(Subject subject){
        this.subject = subject;
    }
    @Override
    public void say(String content) {
        LogUtil.d("代理类 除了实现代理类的功能；还可以丰富操作");
        subject.say(content);
    }

    @Override
    public String getContent(String content) {
        LogUtil.d("代理类 除了实现代理类的功能；还可以丰富操作");
        return subject.getContent(content);
    }
}
