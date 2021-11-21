package com.baoge.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baoge.baselib.LogUtil;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    HttpObject httpObject;
    @Inject
    HttpObject httpObject2;
    @Inject
    DatabaseObject databaseObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DaggerMyComponent 这个是动态生成的
//        DaggerMyComponent.create().injectMainActivity(this);

        //IMPTT 下面这个方法更加可配，各种参数可以自己控制 功能和上面的代码一致
        DaggerMyComponent.builder().httpModule(new HttpModule()).databaseModule(new DatabaseModule())
                .build().injectMainActivity(this);


        LogUtil.d("httpObject "+httpObject.hashCode());
        LogUtil.d("httpObject2 "+httpObject2.hashCode());
        LogUtil.d("databaseObject "+databaseObject.hashCode());

        ((Button)findViewById(R.id.sec)).setOnClickListener(this);
        ((Button)findViewById(R.id.three)).setOnClickListener(this);
        ((Button)findViewById(R.id.four)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sec:
                startActivity(new Intent(MainActivity.this, SecActivity.class));
                break;
            case R.id.three:
                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
                break;
            case R.id.four:
                startActivity(new Intent(MainActivity.this, FourActivity.class));
                break;
        }
    }
}