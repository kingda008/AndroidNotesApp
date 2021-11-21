package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton1;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 组件，用来注入对象
 */
@Singleton1
@Component(modules = {ThreeModule.class },dependencies = {ThreeComponent2.class})
public interface ThreeComponent {

    void injectThreeActivity(ThreeActivity threeActivity );

}
