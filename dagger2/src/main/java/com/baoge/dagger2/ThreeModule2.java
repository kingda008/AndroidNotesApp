package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 用来提供对象的
 */

@Module
public class ThreeModule2 {

    @Singleton2
    @Provides
    public ThreeObject2 providerThreeObject2(){
        return new ThreeObject2();
    }

}
