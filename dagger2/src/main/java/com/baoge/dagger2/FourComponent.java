package com.baoge.dagger2;

import com.baoge.dagger2.scope.Singleton1;

import dagger.Component;



@Component(modules = {FourModule.class } )
public interface FourComponent {

   FourComponent2 getFourComponent2();

}
