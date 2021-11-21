package com.baoge.notes.annotations;



import com.baoge.baselib.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class OnclickListenerInvocationHandler implements InvocationHandler {
    private Object context;
    private Method activityMethod;

    public OnclickListenerInvocationHandler(Object context ,Method method){
        this.context = context;
        this.activityMethod = method;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * InjectUtil中View.OnclickListener中的Onclick执行的时候 跳到这里。
         * 这里替换成传进来的 activityMethod执行。
         */
        LogUtil.d("代理前"+method.getName()+","+activityMethod.getName());
        //methos method ;  activityMethod :doClickSomeThing
        //IMPTt 这里应该使用activityMethod 而不是method
        Object object =  activityMethod.invoke(context,args);
        LogUtil.d("代理后");
        return object;
    }
}
