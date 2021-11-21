package com.baoge.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 用来提供对象的
 */

@Module
public class HttpModule {
    //这里加了单例 clas上也要加 ，Component 也要加
    @Singleton
    @Provides
    public HttpObject providerHttpObject(){
        return new HttpObject();
    }
    @Provides
    public Object providerOtherObject(){
        return new  Object();

    }
}
