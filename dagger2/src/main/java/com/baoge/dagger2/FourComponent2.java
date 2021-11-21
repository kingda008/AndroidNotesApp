package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton2;

import dagger.Component;
import dagger.Subcomponent;

/**
 * 组件，用来注入对象
 */

@Subcomponent(modules = {  FourModule2.class})
public interface FourComponent2 {

     void injectFourActivity(FourActivity fourActivity );


}
