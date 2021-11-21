package com.baoge.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baoge.baselib.LogUtil;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import dagger.Lazy;
import dagger.Provides;

public class FourActivity extends AppCompatActivity {
    @Inject
    public FourObject fourObject;
    @Inject
    public FourObject2 fourObject2;


    @Inject
    public A a;

    @Inject
    public B b;

    //IMPTT 根据这个来区分不同方法

    @Named("user1")
    @Inject
    public User user1;
    @Named("user2")
    @Inject
    public User user2;


    @Inject
    Lazy<User> userLazy;
    @Inject
    Provider<User> userProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        //注意使用的区别
        //IMPTT 通过这种方式实现动态传参
        DaggerFourComponent.builder().fourModule(new FourModule("hello")).build().getFourComponent2().injectFourActivity(this);

        LogUtil.d("fourObject "+fourObject.hashCode());

        LogUtil.d("fourObject2 "+fourObject2.hashCode());


        /**
         * 框架会自动把A 注入到B
         */


        LogUtil.d("a "+a.hashCode());

        LogUtil.d("b "+b.hashCode());

        LogUtil.d("USER1 "+user1.getName());

        LogUtil.d("USER2 "+user2.getName());


        /**
         * 这两种都是懒加载；rebuild的时候不会生成代码（inject的时候不会自动get()），
         *
         * lazy 的打印是一致的
         * provide 的打印是不一致的；
         */

        LogUtil.d("  "+userLazy.get().hashCode());
        LogUtil.d("  "+userLazy.get().hashCode());

        LogUtil.d("  "+userProvider.get().hashCode());
        LogUtil.d("  "+userProvider.get().hashCode());
    }
}