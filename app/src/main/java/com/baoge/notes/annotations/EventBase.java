package com.baoge.notes.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)

public @interface EventBase {

    /**
     * 要素1 设置事件监听的方法
     * @return
     */
    String listenerSetter();

    /**
     * 要素2 事件监听的类型
     * @return
     */
    Class <?> listenerType();

    /**
     * 要素3 事件被处罚之后，执行的回调方法的名称
     * @return
     */
    String callbackMethod();


}
