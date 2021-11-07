package com.baoge.notes.annotations;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectUtils {
    public static void injectClick(Object context) {
        //IMPTT 1：便利所有method，找到含有注解的方法
        Class<?> cls = context.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            BgOnClick bgOnClick = method.getAnnotation(BgOnClick.class);
            if (bgOnClick != null) {
                //imptt 2：获取id，拿到view
                int[] values = bgOnClick.value();
                for (int id : values) {
                    //为啥不能直接用findviewbyid ，而要反射？

                    try {
                        Method findViewByIdMethod = cls.getMethod("findViewById", int.class);

                        View view = (View) findViewByIdMethod.invoke(context, id);
                        if(view!=null){

                            //TODO 没有用动态代理，配合反射实现关联
                            view.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    try {
                                        //IMPTT 3：关联定义的方法 BgOnClick
                                        method.invoke(context,v);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
    }
}
