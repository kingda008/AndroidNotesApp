# 动态代理

#### 根据加载被代理类的时机不同，将代理分为静态代理和动态代理。

- 编译时就确定了被代理的类是哪一个，那么就可以直接使用静态代理；
- 运行时才确定被代理的类是哪个，那么可以使用类动态代理。



**总结： 静态代理（传统代理模）的实现方式比较暴力直接，需要将所有被代理类的所有方法都写一遍，并且一个个的手动转发过去。有点累**

动态代理
1、在java的动态代理机制中，有两个重要的类或接口
一个是 InvocationHandler(Interface)
另一个则是Proxy(Class)
这一个类和接口是实现我们动态代理所必须用到的
2、

```java
 InvocationHandler
public interface InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```

proxy：指代生成的代理对象；
method：指代的是我们所要调用真实对象的某个方法的Method对象；
args：指代的是调用真实对象某个方法时接受的参数；
每一个代理实类例的invocation handler都要实现InvocationHandler这个接口。并且每个代理类的实例都关联到了一个handler，当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由InvocationHandler这个接口的invoke 方法来进行调用

3、 Proxy这个类的 newProxyInstance 这个方法
JDK动态代理需要借助接口来实现，如果我们要代理的对象功能没有抽成任何接口，那么我们就无法通过JDK动态代理的方式来实现。

```java
public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
```

loader：一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
interfaces：一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上。 





与静态代理相比，动态代理具有如下的优点：

代理转发的过程自动化了，实现自动化搬砖；
代理类的代码逻辑和具体业务逻辑解耦，与业务无关；
首先来看看 $Proxy0 这东西，这个东西就是真正的代理类对象，我们定义SubjectInvocationHandler类则是用于添加对代理类的功能扩展！而 $Proxy0类继承java.lang.reflect.Proxy类 并实现Subject接口 ，因此它的类声明方式如下：

同时我们一定要记住，通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以$开头，proxy为中，最后一个数字表示对象的标号。



原理： 

![image-20211103194217078](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211103194217078.png)

寻找或生成一个代理类

![image-20211103194531963](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211103194531963.png)

![image-20211103194559240](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211103194559240.png)



![image-20211103194711157](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211103194711157.png)







![image-20211103194738724](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211103194738724.png)

![image-20211103194747804](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211103194747804.png)





# dagger2



![image-20211109190113902](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211109190113902.png)

module:用于提供对象
component:用于组织module并进行注入



















# HILT

 ：Inversion of Control 的缩写，“控制反转”

是原来由程序代码中主动获取的资源，转变由第三方获取并使原来的代码被动接收的方式，以达到解耦的效果，称为控制反转



![image-20211110191318250](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211110191318250.png)



以前是通过隔离层来预防实现框架的替换。

![image-20211110192257519](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211110192257519-16365433814551.png)









Hilt就是Android团队联系了Dagger2团队，一起开发出来的一个专门面向Android的依赖注入框架。
相比于Dagger2，Hilt最明显的特征就是：1. 简单。2. 提供了Android专属的API。

无需编写大量的Component代码Scope也会与Component自动绑定预定义绑定，例如 Application与Activity预定义的限定符，例如@ApplicationContext与@ActivityContext

注意：Hilt注入的字段是不可以声明成private



Hilt 提供了以下组件：

![image-20211111101824946](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211111101824946.png)



如果需要使用 FragmentComponent 中定义的绑定并且视图是 Fragment 的一部分，应将 @WithFragmentBindings 注释和 @AndroidEntryPoint 一起使用。

组件生命周期：

![image-20211111102111968](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211111102111968.png)

同时还提供了相应的作用域：

![image-20211111102127004](IOC-HILT%E5%8E%9F%E7%90%86.assets/image-20211111102127004.png)

实现有使用gradle插件做字节码插桩：


C:\Users\Administrator\.gradle\caches\modules-2\files-2.1\com.google.dagger\hilt-android-gradle-plugin\2.28-alpha\eb33a043b2bbdc7cdee3c851d0f8532bfd3645a5\hilt-android-gradle-plugin-2.28-alpha-



