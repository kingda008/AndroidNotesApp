package com.baoge.annotation_compiler;

import com.baoge.annotations.BindView;
import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

//这个是注册，像系统注册；类似于四大组件，这样javac执行的时候就会知道
@AutoService(Process.class)
public class AnnotationCompiler extends AbstractProcessor {


    //1 支持的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
//        return super.getSupportedSourceVersion();

    //选择支持的最新版本

    return SourceVersion.latestSupported();

    }
    //2 能用来处理哪些注解


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        //添加自定义的注解
        types.add(BindView.class.getCanonicalName());
        //处理系统的也可以
//        types.add(Override.class.getCanonicalName());
        return types;
//        return super.getSupportedAnnotationTypes();
    }
    //3 ,定义一个用来生成APT目录下面文件的对象
    Filer filer;

    /**
     * 注解的处理都在这个方法中处理
     * javac编译过程中会用到这个方法；通过这个方法处理可以生产代码
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,"baozaolaoge:"+set);

        return false;
    }
}