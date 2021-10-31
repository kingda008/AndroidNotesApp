package com.baoge.notes.annotations;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.baoge.annotation_compiler.BindView;
import com.baoge.notes.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class AnnotationsActivity extends Activity {
    @BindView(R.id.txt)
    TextView txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotations);
        txt.setText("aaaa");
    }


    public void setDrableId(@DrawableRes long id){

    }
}
