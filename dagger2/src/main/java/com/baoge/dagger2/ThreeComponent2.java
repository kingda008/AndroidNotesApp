package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 组件，用来注入对象
 */
@Singleton2
@Component(modules = {  ThreeModule2.class})
public interface ThreeComponent2 {
    /**
     * IMPTT 组合依赖关系不能用这种语法
     */
//    void injectThreeActivity(ThreeActivity threeActivity );

    //这里是module中的方法 ；不是module 而是object


    ThreeObject2 providerThreeObjecst2();
}
