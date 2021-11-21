package com.baoge.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baoge.baselib.LogUtil;

import javax.inject.Inject;

/**
 * 这里介绍多个component的情况
 */
public class ThreeActivity extends AppCompatActivity {

//    @Inject
//    HttpObject httpObject;
//    @Inject
//    HttpObject httpObject2;
//    @Inject
//    DatabaseObject databaseObject;
    @Inject
    ThreeObject2 threeObject2;
    @Inject
    ThreeObject  threeObject ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        /**
         * //ERROR 直接会报错，因为不能把多个component 注入到同一个activity；需要改造成依赖
         *
         * imptt 还有一个重要的店，不同的conponent如果是单例模式，那么单例的主机不能一致
         * 所以需要定义不同名称的单例注解（虽然里面都一样）
         *
         */
//        MyApplication.getMyComponent().injectThreeActivity(this);
////        MyApplication.getThreeComponent().injectThreeActivity(this);
//
//
//        LogUtil.d("httpObject "+httpObject.hashCode());
//        LogUtil.d("httpObject2 "+httpObject2.hashCode());
//        LogUtil.d("databaseObject "+databaseObject.hashCode());
//        LogUtil.d("threeObject2 "+threeObject2.hashCode());
//        LogUtil.d("threeObject "+threeObject.hashCode());


        MyApplication.getThreeComponent().injectThreeActivity(this);


        LogUtil.d("threeObject2 "+threeObject2.hashCode());
        LogUtil.d("threeObject "+threeObject.hashCode());

    }
}