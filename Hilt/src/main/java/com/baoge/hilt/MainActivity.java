package com.baoge.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baoge.baselib.ToastUtil;
import com.baoge.hilt.hilt_isolation.HiltActivity;
import com.baoge.hilt.proxy_Isolation.httpprocessor.HttpCallback;
import com.baoge.hilt.proxy_Isolation.httpprocessor.HttpHelper;
import com.baoge.hilt.proxy_Isolation.httpprocessor.bean.ResponceData;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((Button) findViewById(R.id.proxy)).setOnClickListener(this);

        ((Button) findViewById(R.id.hilt)).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.proxy: {
                String url = "https://v.juhe.cn/historyWeather/citys";
                HashMap<String, Object> params = new HashMap<>();
                //https://v.juhe.cn/historyWeather/citys?&province_id=2&key=bb52107206585ab074f5e59a8c73875b
                params.put("province_id", "2");
                params.put("key", "bb52107206585ab074f5e59a8c73875b");
                HttpHelper.obtain().post(url, params, new HttpCallback<ResponceData>() {
                    @Override
                    public void onSuccess(ResponceData objResult) {
                        ToastUtil.show(objResult.getResultcode());
                    }

                });


            }
            break;
            case R.id.hilt:
                startActivity(new Intent(MainActivity.this, HiltActivity.class));
                break;
        }

    }
}