package com.baoge.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 组件，用来注入对象
 * <p>
 * dependencies 组合
 */
@Singleton
@Component(modules = {HttpModule.class, DatabaseModule.class})
public interface MyComponent {
    //这里不能直接用多态，不能用父类
    void injectMainActivity(MainActivity mainActivity);

    void injectSecActivity(SecActivity secActivity);


}
