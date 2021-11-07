package com.baoge.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baoge.notes.annotations.AnnotationsActivity;
import com.baoge.notes.generic_type.GenericTypeActivity;
import com.baoge.notes.reflection.ReflectionActivity;
import com.baoge.notes.reflection.proxy.ProxyActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.proxy)).setOnClickListener(this);
        ((Button)findViewById(R.id.generic)).setOnClickListener(this);
        ((Button)findViewById(R.id.reflection)).setOnClickListener(this);
        ((Button)findViewById(R.id.annotation)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.proxy:

                startActivity(new Intent(MainActivity.this, ProxyActivity.class));
                break;
            case R.id.generic:

                startActivity(new Intent(MainActivity.this, GenericTypeActivity.class));
                break;

            case R.id.reflection:

                startActivity(new Intent(MainActivity.this, ReflectionActivity.class));
                break;
            case R.id.annotation:

                startActivity(new Intent(MainActivity.this, AnnotationsActivity.class));
                break;

        }
    }
}