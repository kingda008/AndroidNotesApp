package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton2;

import dagger.Module;
import dagger.Provides;

/**
 * 用来提供对象的
 */

@Module
public class FourModule2 {


    @Provides
    public FourObject2 providerFourObject2(){
        return new FourObject2();
    }

}
