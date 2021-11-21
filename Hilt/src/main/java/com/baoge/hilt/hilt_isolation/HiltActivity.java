package com.baoge.hilt.hilt_isolation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baoge.baselib.LogUtil;
import com.baoge.hilt.MyApplication;
import com.baoge.hilt.R;
import com.baoge.hilt.hilt_isolation.interface_inject.TestInterface;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HiltActivity extends AppCompatActivity {

    @Inject
    HttpObject httpObject;
    @Inject
    HttpObject httpObject2;


    @Inject
    TestInterface testInterface;


    IHiltHttpProcessor iHiltHttpProcessor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);

        LogUtil.d("httpObject "+httpObject.hashCode());
        LogUtil.d("httpObject2 "+httpObject2.hashCode());
        iHiltHttpProcessor = ((MyApplication)getApplication()).getiHiltHttpProcessor();

//        iHiltHttpProcessor.post();
        testInterface.method();

    }
}