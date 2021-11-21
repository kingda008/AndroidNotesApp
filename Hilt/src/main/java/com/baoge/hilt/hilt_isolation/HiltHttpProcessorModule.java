package com.baoge.hilt.hilt_isolation;

import com.baoge.hilt.annotation.BindOkhttp;
import com.baoge.hilt.annotation.BindVolley;
import com.baoge.hilt.annotation.BindXUtils;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class HiltHttpProcessorModule {
    //  @BindOkhttp 通过不同的自定义限定符来区分
    @BindOkhttp
    @Binds
    @Singleton
    abstract IHiltHttpProcessor bindOkhttp(HiltOkHttpProcessor hiltOkHttpProcessor);

    @BindVolley
    @Binds
    @Singleton
    abstract IHiltHttpProcessor bindVolly(HiltVolleyProcessor hiltVolleyProcessor);



    @BindXUtils
    @Binds
    @Singleton
    abstract IHiltHttpProcessor bindXUtils(HiltXUtilsProcessor xUtilsProcessor);

}
