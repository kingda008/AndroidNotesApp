package com.baoge.hilt.hilt_isolation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baoge.baselib.LogUtil;
import com.baoge.hilt.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SecActivity extends AppCompatActivity {

    @Inject
    HttpObject httpObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        LogUtil.d("httpObject "+httpObject.hashCode());

    }
}