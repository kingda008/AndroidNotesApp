package com.baoge.notes.generic_type;

import android.app.Activity;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.baoge.notes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 *
 * 1，泛型类
 * 2，泛型方法
 * 3，泛型构造
 * 4，通配符 ？
 */
public class GenericTypeActivity extends Activity {
    private String TAG = "generic";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        test();
    }

    public void test() {

        /**
         1，没有泛型，对象放入集合时，集合不会记住此对象的类型，再次取出时对象变成了object
         2，强转容易出现java.lang.ClassCastException
         */
        List noGenericList = new ArrayList();
        noGenericList.add("hello");
        //TODO 需要强转
        System.out.println((String) noGenericList.get(0));


        List<String> genericList = new ArrayList<String>();
        genericList.add("hello");
        //TODO 不需要强转
        System.out.println(genericList.get(0));
        /**
         * 可以把类型参数看作时使用参数化类型时指定的类型的一个占位符
         */

        /**
         *   IMPTT  泛型好处(1,类型安全；2，消除强转，增加可读性；3，为较大优化带来可能性)
         */


        //IMPTT 使用时只需要指定K,V具体类型，就可以存放不同数据类型
        Container<String, String> c1 = new Container<String, String>("aa", "bb");
        Container<String, Integer> c2 = new Container<String, Integer>("aa", 33);

        //也可以自动推导类型

        Container<String, String> c3 = new Container<>("aa", "bb");
        Container<String, Integer> c4 = new Container("aa", 33);


        /**
         * IMPTT  泛型类或泛型接口中的泛型参数可以在方法中使用，也可以独立在方法中定义方法参数。
         * 格式：修饰符<T,S> 返回值类型 方法名 （形参列表）{方法体}
         * 类和接口定义的泛型可以在整个类使用；方法中定义的类型的泛型只能在方法中使用
         */

        Log.i(TAG,"返回："+out(212));
        Log.i(TAG,"返回："+out("你好"));


        /**
         * imptt 泛型构造器；
         */
        //显式
        Container<String, String> c5 = new Container<String,String>("aa", "bb");
        //隐式
        Container<String, Integer> c6 = new Container("aa", 33);


        /**
         * imptt 通配符？
         */
        //这样不会报错
        List<?> l1 = new ArrayList<>();
        for(int i=0;i<l1.size();i++){
            Log.i(TAG,"LIST? "+l1.get(i));
        }

        /**
         * 但是这个会报错  l1.add(1);
         * 因为 不知道l1的类型  ，无法添加对象
         */


        //imptt 上限通配符
        List<? extends Shape> shapes = new ArrayList<>();
        //imptt 下限通配符
        List<? super  Shape> shapes2 = new ArrayList<>();

        /**
         *        imptt 类型擦除
         *         对于Java来说， 它们依然被
         *         当成同一类处理， 在内存中也只占用一块内存空间。 从Java泛型这一概念提出的目
         *         的来看， 其只是作用于代码编译阶段， 在编译过程中， 对于正确检验泛型结果后，
         *         会将泛型的相关信息擦出， 也就是说， 成功编译过后的class文件中是不包含任何泛
         *         型信息的。 泛型信息不会进入到运行时阶段。
         *         在静态方法、 静态初始化块或者静态变量的声明和初始化中不允许使用类型形参。
         *         由于系统中并不会真正生成泛型类， 所以instanceof运算符后不能使用泛型类。
         */


    }

    public <T> T out(T t){
        Log.i(TAG,"OUT :"+t);
        return t;
    }

    //IMPTT (1)  泛型类和接口
    public class Container<K, V> {
        private K key;
        private V value;


        public Container(K k, V v) {
            key = k;
            value = v;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


    /**
     * IMPTT 继承的类不能直接 Container<K, V>  ；必须要指定类型；或者不写参数，系统会把K,V形参当作Object处理
     */

    public class Container2 extends Container<String,Integer>{

        public Container2(String s, Integer integer) {
            super(s, integer);
        }
    }
}


