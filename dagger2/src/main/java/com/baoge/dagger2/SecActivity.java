package com.baoge.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baoge.baselib.LogUtil;

import javax.inject.Inject;

public class SecActivity extends AppCompatActivity {

    @Inject
    HttpObject httpObject;
    @Inject
    HttpObject httpObject2;
    @Inject
    DatabaseObject databaseObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        DaggerMyComponent.builder().httpModule(new HttpModule()).databaseModule(new DatabaseModule())
                .build().injectSecActivity(this);

        //IMPTT 为啥和mianactivity中的不一致？ 不是单例吗
        /**
         *   public static Builder builder() {
         *     return new Builder();
         *   }
         *
         *   每次都是new 的新对象；所以DaggerMyComponent都是不同的。
         *   所以commpent里面的东西都是不同的，所以只是局部单例
         *
         *   如果要创建全局单例可以把 component build 放到application中
         *
         *
         */

        LogUtil.d("httpObject "+httpObject.hashCode());
        LogUtil.d("httpObject2 "+httpObject2.hashCode());
        LogUtil.d("databaseObject "+databaseObject.hashCode());


    }
}