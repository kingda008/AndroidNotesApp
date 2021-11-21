package com.baoge.hilt.hilt_isolation.interface_inject;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class TestInterfaceModule {
    @Binds
    public abstract TestInterface bindTestClass(TestClass testClass);

}
