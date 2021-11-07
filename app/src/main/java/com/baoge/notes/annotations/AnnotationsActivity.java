package com.baoge.notes.annotations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.baoge.annotations.BindView;
import com.baoge.notes.R;
import com.baoge.notes.ToastUtil;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class AnnotationsActivity extends Activity {
    @BindView(R.id.txt)
    TextView txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_annotations);
        //IMPTT 放在setContentView 后才有效
        InjectUtils.injectClick(this);
//        txt.setText("aaaa");
    }


    public void setDrableId(@DrawableRes long id){

    }

    /**
     * 会解析这个注解，并在注解解析器中执行逻辑，让他在某种设定的逻辑情况下执行doClickSomeThing
     *
     * imptt 一般框架会把注解和待执行方法的名字设置的相对贴合，此处只做演示用，不用太纠结
     */
    @BgOnClick(R.id.test1)
    public void doClickSomeThing(View view){
        switch (view.getId()){
            case R.id.test1:
                ToastUtil.show("test1");
                break;
        }
    }

}
