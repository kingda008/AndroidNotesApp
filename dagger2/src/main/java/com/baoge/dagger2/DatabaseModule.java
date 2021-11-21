package com.baoge.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * 用来提供对象的
 */
@Module
public class DatabaseModule {
    @Provides
    public DatabaseObject providerDatabaseObject(){
        return new DatabaseObject();
    }
}
