package com.baoge.notes.reflection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.baoge.baselib.LogUtil;
import com.baoge.notes.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;


/**
 * IMPTT 功能
 * 1，运行时 判断任意一个对象所属的类
 * 2，运行时 构造任意一个类的对象
 * 3，运行时 判断任意一个类所具有的成员变量和方法
 * 4，运行时 调用任意一个对象的方法
 * 5，生成动态地理
 * <p>
 * <p>
 * TODO 怎么防止被反射？？？
 * <p>
 * <p>
 * IMPTT 应用场景
 * 1，逆向代码，例如反编译
 * 2，与朱姐相结合的框架，例如Retrofit
 * 3，单纯的反射机制应用框架，例如 EventBus
 * 4，动态生成类框架， 例如Gson
 */
public class ReflectionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);


        ((Button) findViewById(R.id.reflection)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reflection:
                LogUtil.d("reflection");
                reflectGeneric();
                getClassInfoByReflect();
                break;
        }
    }

    /**
     * 通过反射查看类信息
     */
    private void getClassInfoByReflect() {
        //imptt 获取类信息 *******************************************************
        //第一种方式 通过Class类的静态方法 forname
        try {
            Class<?> class1 = Class.forName("com.baoge.notes.reflection.ReflectionTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //第二种方式：通过class的属性
        Class<?> class2 = ReflectionTest.class;

        //第三种方式：通过getClass方法
        ReflectionTest reflectionTest = new ReflectionTest();
        Class<?> class3 = reflectionTest.getClass();

        //imptt 获取属性 *******************************************************
        //所有属性
        Field[] allFields = class2.getDeclaredFields();

        //public属性
        Field[] publicFields = class2.getFields();

        //获取指定属性
        try {
            Field name = class2.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            Field age = class2.getDeclaredField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            Field school = class2.getDeclaredField("school");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //获取指定public属性

        try {
            Field name = class2.getField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            //error 会报错
            Field age = class2.getField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            //error 会报错
            Field school = class2.getField("school");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        //imptt 获取方法 *******************************************************
        //获取对象所有声明的方法
        Method[] methods = class2.getDeclaredMethods();

        //获取所有public 方法，“包括父类方法”
        Method[] publicMethods = class2.getMethods();
        //获取对应类，带指定形参列表的方法
        try {
            Method setName = class2.getMethod("setName", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            Method setName2 = class2.getDeclaredMethod("setName2", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            Method setName2 = class2.getDeclaredMethod("setName2", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //获取对应类，带指定形参列表的方法  public 方法

        try {
            Method setName = class2.getMethod("setName", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            //error 会报错
            Method setName2 = class2.getMethod("setName2", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //imptt 获取class对象的构造函数 *******************************************************
        //获取所有构造函数
        Constructor<?>[] allConstructors = class2.getDeclaredConstructors();
        //获取所有public构造函数
        Constructor<?>[] publicConstructors = class2.getConstructors();
        //获取指定构造函数
        try {
            Constructor<?>  constructor  = class2.getDeclaredConstructor(String.class,int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            Constructor<?>  constructor  = class2.getDeclaredConstructor(String.class );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //获取指定构造函数 public
        try {
            Constructor<?>  constructor  = class2.getConstructor(String.class,int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            //ERROR 不是public构造方法，异常
            Constructor<?>  constructor  = class2.getConstructor(String.class );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //imptt 其他方法 *******************************************************
        //获取所有注解
        Annotation[] annotations = (Annotation[]) class2.getAnnotations();
        //获取指定注解
        Annotation annotation = (Annotation) class2.getAnnotation(Deprecated.class);
        //获取对象的直接超类的type
        Type genericSuperClass = class2.getGenericSuperclass();
        //获取class对象的所有接口的type集合
        Type[] interfaceTypes = class2.getGenericInterfaces();

        //imptt 获取class对象的信息 *******************************************************
        //是否是基础类型
        boolean isPrimitive = class2.isPrimitive();
        //是否是集合类
        boolean isArray = class2.isArray();
        //是否是注解类
        boolean isAnnotation = class2.isAnnotation();
        //是否是接口类
        boolean isInterface = class2.isInterface();
        //是否是枚举类
        boolean isEnum = class2.isEnum();
        //是否是匿名内部类
        boolean isAnonymousClass = class2.isAnonymousClass();
        //是否被某个注解类修饰
        boolean isAnnotationPresent = class2.isAnnotationPresent(Deprecated.class);
        //获取class名字，包含报名路径
        String className = class2.getName();
        //获取包名信息
        Package aPackage = class2.getPackage();
        //类名信息
        String simpleName = class2.getSimpleName();
        //获取class访问权限
        int modifiers = class2.getModifiers();
        //内部类
        Class<?>[] declaredClasses = class2.getDeclaredClasses();
        //外部类
        Class<?> declaringClass = class2.getDeclaringClass();


        //imptt 通过反射生成并操作对象 *******************************************************
        /**
         * 使用Class对象的newInstance()方法来创建，使用的是默认构造器
         *
         * 使用Class获取指定的Constartor对象，再调用Constructor对象的newInstance()
         * 则可以通过指定构造器创建对象
         */


        //第一种方式
        try {
            Object obj1 = class2.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        //第二种方式
        try {
            Constructor<?> constructor = class2.getDeclaredConstructor(String.class,int.class);
            try {
                Object obj2 = constructor.newInstance("helloi",3);

                //imptt 调用方法 ****************
                /**
                 * Class getMethods获取所有方法；getmethod获取指定方法
                 * invoke 调用方法；参数为，对象和方法对应的参数
                 */


                Method setNameMethod = class2.getDeclaredMethod("setName",String.class);
                /**
                 * 调用invoke方法时，Java会要求程序必须要具备调用该方法的权限；如果需要调用private方法
                 * 需要   setNameMethod.setAccessible(true); t
                 * true 表示取消检查，false表示开启访问权限检查
                 */
                setNameMethod.setAccessible(true);
                setNameMethod.invoke(obj2,"zhangsan");

                Method getNameMethod = class2.getDeclaredMethod("getName");
                getNameMethod.setAccessible(true);
                LogUtil.d((String)getNameMethod.invoke(obj2));


                /**
                 *      IMPTT 访问成员变量值  ****************************
                 *
                 *   getFields() 或者 getField() 获得成员数组或指定成员
                 *   Filed提供了两组方法来获取和设置成员变量值 ；
                 *   getXXX setXXX 获取，设置对象的值 （这里要求是8中基本类型）
                 *   如果是引用类型则需要把 3个XXX 去掉
                 *
                 */

                try {
                    Field name = class2.getField("name");
                    name.set(obj2,"lisi");
                    LogUtil.d((String) name.get(obj2));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    /**
     * 泛型和反射结合
     */
    private void reflectGeneric(){
        //  需要强转，有风险
        Date date = (Date) ObjectFactory.getInstance2("java.util.Date");

        //和泛型搭配,不需要强转
        date = ObjectFactory.getInstance(Date.class);

        //imptt 使用反射来获取泛型信息

        /**
         * 通过指定类对应的 Class 对象， 可以获得该类里包含的所有 Field， 不管该 Field 是
         * 使用 private 修饰， 还是使用 public 修饰。 获得了 Field 对象后， 就可以很容易地
         * 获得该 Field 的数据类型， 即使用如下代码即可获得指定 Field 的类型。
         * // 获取 Field 对象 f 的类型
         * Class<?> a = f.getType();
         *
         * 但这种方式只对普通类型的 Field 有效。 如果该 Field 的类型是有泛型限制的类
         * 型， 如 Map<String, Integer> 类型， 则不能准确地得到该 Field 的泛型参数。
         * 为了获得指定 Field 的泛型类型， 应先使用如下方法来获取指定 Field 的类型。
         * // 获得 Field 实例的泛型类型
         * Type type = f.getGenericType();
         * 然后将 Type 对象强制类型转换为 ParameterizedType 对象， ParameterizedType
         * 代表被参数化的类型， 也就是增加了泛型限制的类型。 ParameterizedType 类提供
         * 了如下两个方法。
         * getRawType()： 返回没有泛型信息的原始类型。
         * getActualTypeArguments()： 返回泛型参数的类型
         *
         *
         * Type 也是 java.lang.reflect 包下的一个接口， 该接口代表所有类型的公共高级接
         * 口， Class 是 Type 接口的实现类。 Type 包括原始类型、 参数化类型、 数组类型、
         * 类型变量和基本类型等。
         * 
         *
         */

        Class<ReflectionTest> cls = ReflectionTest.class;

        try {
            Field name = cls.getField("name");
            //直接getType只对基础类型有效
            LogUtil.d("name type :"+name.getType());
            Field score = cls.getDeclaredField("score");
            //为啥这个也能获取到？？
            LogUtil.d("score type :"+score.getType());

            //获得泛型类型
            Type genericType = score.getGenericType();
            if(genericType instanceof ParameterizedType){
                //强转
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                LogUtil.d("原始类型："+parameterizedType.getRawType());
                //返回泛型类型的泛型超参数
                Type[] aTypes = parameterizedType.getActualTypeArguments();
                for(int i=0;i<aTypes.length;i++){
                    LogUtil.d("参数类型："+aTypes[i]);
                }
            }else{
                LogUtil.d("获取泛型类型出错");
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}