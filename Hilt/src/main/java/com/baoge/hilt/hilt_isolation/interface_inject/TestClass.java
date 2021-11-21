package com.baoge.hilt.hilt_isolation.interface_inject;

import com.baoge.baselib.LogUtil;

import javax.inject.Inject;

public class TestClass implements TestInterface {

    @Inject
    TestClass() {
    }

    @Override
    public void method() {
        LogUtil.d("test class method");
    }
}
