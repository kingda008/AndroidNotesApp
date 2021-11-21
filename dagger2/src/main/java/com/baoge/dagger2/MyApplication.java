package com.baoge.dagger2;

import android.app.Application;

public class MyApplication  extends Application {
    private static MyComponent myComponent;
    private static ThreeComponent threeComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.create();
//        myComponent = DaggerMyComponent.builder().httpModule(new HttpModule()).build();
        threeComponent = DaggerThreeComponent.builder().threeModule(new ThreeModule()).threeComponent2(DaggerThreeComponent2.create()).build();
    }

    public static MyComponent getMyComponent(){
        return myComponent;
    }
    public static ThreeComponent getThreeComponent(){
        return threeComponent;
    }
}
