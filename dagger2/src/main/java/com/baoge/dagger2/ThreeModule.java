package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 用来提供对象的
 */

@Module
public class ThreeModule {

    @Singleton1
    @Provides
    public ThreeObject providerThreeObject(){
        return new ThreeObject();
    }

}
