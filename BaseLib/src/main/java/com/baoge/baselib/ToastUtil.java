package com.baoge.baselib;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {
    private static Application app;

    public static void init(Application application) {
        app = application;
    }

    public static void show(String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(app, content, Toast.LENGTH_LONG).show();
        }
    }
}
