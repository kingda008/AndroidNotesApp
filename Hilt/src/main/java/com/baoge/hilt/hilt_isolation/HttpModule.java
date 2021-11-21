package com.baoge.hilt.hilt_isolation;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.scopes.ActivityScoped;

//这个module 就被装进去了

@InstallIn(ActivityComponent.class)
@Module
public class HttpModule {
    //IMPTT ActivityScoped activity作用于和上面的ActivityComponent是对应的；如果没有对应的话
    //IMPTT 就会报错  比如application全局的话 要用 @InstallIn(ApplicationComponent.class) &     @Singleton


    @ActivityScoped
    @Provides
    public HttpObject getHttpObject(){
        return new HttpObject();
    }
}
