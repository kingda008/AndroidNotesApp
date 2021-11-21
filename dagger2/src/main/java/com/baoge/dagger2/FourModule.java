package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton1;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * 用来提供对象的
 */

@Module
public class FourModule {
    private String content;

    public FourModule(String content){
        this.content = content;
    }

    @Provides
    public FourObject providerFourObject(){

        return new FourObject();
    }

    @Provides
    public A providerA(){
        return new A();
    }
    @Provides
    public B providerB(A a){
        return new B(a);
    }

    @Named("user1")
    @Provides
    public User providerUser1( ){
        return new User("zhangsan","11");
    }
    @Named("user2")
    @Provides
    public User providerUser2( ){
        return new User("lisi","22");
    }
}
