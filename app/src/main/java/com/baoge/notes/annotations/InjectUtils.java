package com.baoge.notes.annotations;

import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
                //为啥不能直接用findviewbyid ，而要反射？
                //除非强转成activity或者Fragemnt等，否则无法调用，但是不建议强转，所以反射？
                /**
                 * //IMPTT 没有使用动态代理，不够抽象，每个方法都要写，过程也没有隐藏


                 try {
                 Method findViewByIdMethod = cls.getMethod("findViewById", int.class);

                 View view = (View) findViewByIdMethod.invoke(context, id);
                 if(view!=null){

                 //TODO 没有用动态代理，配合反射实现关联
                 view.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v) {
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
                 */
                //imptt 使用动态代理  这里第二个参数为什么可以这样传？
                /**
                 * IMPTT 仔细看为什么可以这么写
                 *
                 *
                 */


//                  注释方式2
//
//
//                for (int id : values) {
//                    try {
//                        Method findViewByIdMethod = cls.getMethod("findViewById", int.class);
//                        View view = (View) findViewByIdMethod.invoke(context, id);
//                        /**
//                         * 第二个参数表示我们要代理的接口；注意并不是接口里面的方法；当这个接口中的方法被执行的时候就会进入到handler中处理
//                         *
//                         */
//                        Object proxy = Proxy.newProxyInstance(context.getClass().getClassLoader(), new Class[]{View.OnClickListener.class}, new OnclickListenerInvocationHandler(context, method));
//
//                        /**
//                         * 还记得动态代理三步曲吗
//                         *
//                         * proxy 通过动态代理的方式创建了（存在于内存中）一个实现了View.OnClickListener的子类；然后加载
//                         * 实例化这个子类。赋值给这里的proxy
//                         */
//                        view.setOnClickListener((View.OnClickListener) proxy);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                    }
//                }


            }


            /**
             * 问题：其他方法比如longclick还有更多的方法来了，我们都一个个写一套？
             *      细节怎么隐藏
             *      如何升级架构？
             *
             *      view.onclick view.onlongclick 有没有公共部分可以抽出来？
             *
             *      事件3要素：1，set 设置监听；2，onclickLis事件类型；3，回调方法
             *
             *      注解虽然没有继承，但是注解的注解；把3要素抽出来
             */


            /**
             * 不需要onclick onLongClick 一个个获取
             * 可以拿到所有注解，再判断里面有没有EventBase
             */
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //注解类型
                /**
                 * getClass获取到的是一个代理类，通过该代理类，可以获取到类上注解的属性
                 * annotationType获取到的是注解本身，通过该接口可以实现获取注解上的注解，
                 *
                 */
                Class<?> annotationType = annotation.annotationType();
                //注解类型里面有eventbase
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase != null) {
                    String listenerSet = eventBase.listenerSetter();
                    Class<?> listenerType = eventBase.listenerType();
                    String callMethod = eventBase.callbackMethod();

                    try {
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int[] values = (int[]) valueMethod.invoke(annotation);
                        //IMPTT 这两行代码就相当于上面的  int[] values = bgOnClick.value();  因为在这里架构层就不引入 BgOnClick 这种 ；

                        for (int id : values) {

                            Method findViewByIdMethod = cls.getMethod("findViewById", int.class);
                            View view = (View) findViewByIdMethod.invoke(context, id);


                            Object proxy = Proxy.newProxyInstance(context.getClass().getClassLoader(), new Class[]{listenerType}, new OnclickListenerInvocationHandler(context, method));


                           Method setMethod =  view.getClass().getMethod(listenerSet,listenerType);
//                            view.setOnClickListener((View.OnClickListener) proxy);
                            setMethod.invoke(view,proxy);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}
