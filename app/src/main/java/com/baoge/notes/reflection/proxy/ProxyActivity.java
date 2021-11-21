package com.baoge.notes.reflection.proxy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.baoge.baselib.LogUtil;
import com.baoge.notes.R;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 定义： 给某个对象提供一个代理对象， 并由代理对象控制对于原对象的访问， 即客
 * 户不直接操控原对象， 而是通过代理对象间接地操控原对象。
 *
 *
 * 静态代理和动态代理
 * 根据加载被代理类的时机不同，将代理分为静态代理和动态代理。
 * 编译时就确定了被代理的类是哪一个，那么就可以直接使用静态代理；
 *
 * 运行时才确定被代理的类是哪个，那么可以使用类动态代理。代理类是在运行时生成的。
 * 也就是说 Java 编译完之后并没有实际的 class 文件， 而是在运行时动态生成的类字节码， 并加载到JVM中。
 *
 *
 * 动态类有诸多好处。
 * ①不需要为(RealSubject )写一个形式上完全一样的封装类， 假如主题接口
 * （ Subject） 中的方法很多， 为每一个接口写一个代理方法也很麻烦。 如果接口有变
 * 动， 则目标对象和代理类都要修改， 不利于系统维护；
 * ②使用一些动态代理的生成方法甚至可以在运行时制定代理类的执行逻辑， 从而大大提升系统的灵活性
 *
 *
 * Proxy.newProxyInstance 原理：
 * 1，里面生成了一个class
 * 2，加载生成的class，反射方式实例化
 * 3，调用方法会转到代理处理器中处理
 * 因为有反射，所以不适合循环；因为效率略低
 *
 */
public class ProxyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);


        ((Button) findViewById(R.id.static_proxy)).setOnClickListener(this);
        ((Button) findViewById(R.id.dynamic_proxy)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.static_proxy: {
                /**
                 * 静态代理（传统代理模）的实现方式比较暴力直接，
                 * 需要将所有被代理类的所有方法都写一遍，
                 * 并且一个个的手动转发过去。有点累
                 *
                 */
                RealSubject realSubject = new RealSubject();
                StaticProxySubject proxySubject = new StaticProxySubject(realSubject);
                proxySubject.say("你好");
                proxySubject.getContent("中国");
            }
            break;

            case R.id.dynamic_proxy: {
                /**
                 * 主要涉及两个类， 这两个类都是java.lang.reflect包下的类， 内部主要通过反射来实
                 * 现的
                 *
                 *
                 */
                //被代理类
                Subject realSubject = new RealSubject();
                //我们要代理哪个类，就将该对象传进去，最后是通过该被代理对象来调用其方法
                SubjectInvocationHandler subjectInvocationHandler = new SubjectInvocationHandler(realSubject);
                        //生成代理类
                /**
                 * Proxy提供了如下两个方法来创建动态代理类和动态代理实例。
                 *  getProxyClass newProxyInstance
                 *
                 * loader：类加载器（哪个类加载器来加载这个代理类到JVM的方法区）。一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
                 * interfaces：表明这个代理类需要实现哪些接口；一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
                 * 一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上。

                 */


//                Subject subject = (Subject) Proxy.newProxyInstance(subjectInvocationHandler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(),subjectInvocationHandler);

                Subject subject = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(),subjectInvocationHandler);

                LogUtil.d("proxy "+subject.getClass().getName());
                LogUtil.d("proxy super "+subject.getClass().getSuperclass().getName());
                LogUtil.d("interface "+subject.getClass().getInterfaces()[0].getName());

                subject.say("nihao");
                LogUtil.d(subject.getContent("keshi"));


                /**
                 * 与静态代理相比，动态代理具有如下的优点：
                 *
                 * 代理转发的过程自动化了，实现自动化搬砖；
                 * 代理类的代码逻辑和具体业务逻辑解耦，与业务无关；
                 * 首先来看看 $Proxy0 这东西，这个东西就是真正的代理类对象，我们定义SubjectInvocationHandler类则是用于添加对代理类的功能扩展！
                 * 而 $Proxy0类继承java.lang.reflect.Proxy类 并实现Subject接口 ，因此它的类声明方式如下：
                 *
                 * 同时我们一定要记住，通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，
                 * 也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以$开头，proxy为中，最后一个数字表示对象的标号。
                 *
                 * 如果想添加功能可以：
                 *
                 * @Override
                 * public Object invoke(Object object, Method method, Object[] args) throws Throwable {
                 *     if (method.getName().equals("sayGoodBye")) {//在调用sayGoodBye方法的时候 对返回值进行处理
                 *         int result = (int) method.invoke(subject, args);
                 *         return result + 10;
                 *     } else {//其他方法一律不处理
                 *         return method.invoke(subject, args);
                 *     }
                 * }
                 */
            }
            break;
        }
    }


    /**
     * 1、在java的动态代理机制中，有两个重要的类或接口
     * 一个是 InvocationHandler(Interface)
     * 另一个则是Proxy(Class)
     * 这一个类和接口是实现我们动态代理所必须用到的
     * <p>
     * proxy：指代生成的代理对象；
     * method：指代的是我们所要调用真实对象的某个方法的Method对象；
     * args：指代的是调用真实对象某个方法时接受的参数；
     * 每一个代理实类例的invocation handler都要实现InvocationHandler这个接口。
     * 并且每个代理类的实例都关联到了一个handler，当我们通过代理对象调用一个方法的时候，
     * 这个方法的调用就会被转发为由InvocationHandler这个接口的invoke 方法来进行调用
     *
     *
     * 定义一个InvocationHandler， 相当于一个代理处理器
     * SubjectInvocationHandler并不是真正的代理类，而是用于定义代理类需要扩展、
     * 增强那些方法功能的类。在invoke函数中，对代理对象的所有方法的调用都被转发至该
     * 函数处理。在这里可以灵活的自定义各种你能想到的逻辑。
     *
     */
    private class SubjectInvocationHandler implements InvocationHandler {
        private Object subject;

        public SubjectInvocationHandler(Object object) {
            subject = object;
        }

        /**
         * invoke方法时在代理对象调用任何一个方法时都会被调用。
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //在代理真实对象前我们可以添加一些自己的操作
            LogUtil.d("invoke    " );
            LogUtil.d("invoke  method " + method);
            //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
            Object result = method.invoke(subject, args);
            LogUtil.d("after Method invoke");
            return result;
        }
    }

}