Dagger2 是基于 Java 注解来实现依赖注入的，那么在正式使用之前我们需要先了解下 Dagger2 中的注解。Dagger2 使用过程中我们通常接触到的注解主要包括：@Inject, @Module, @Provides, @Component, @Qulifier, @Scope, @Singleten。



## @Inject 有两个作用：

一是用来标记需要依赖的变量，以此告诉 Dagger2 为它提供依赖；

二是用来标记构造函数，Dagger2 通过@Inject 注解可以在需要这个类实例的时候来找到这个构造函数并把相关实例构造出来，以此来为被@Inject 标记了的变量提供依赖；

 

## @Module 用于标注提供依赖的类。

你可能会有点困惑，上面不是提到用@Inject 标记构造函数就可以提供依赖了么，为什么还需要@Module？

比如，很多时候我们需要提供依赖的构造函数是第三方库的，我们没法给它加上@Inject 注解；又比如，需要被注入的依赖类提供的构造函数是带参数的，那么他的参数又怎么来呢？

@Module 正是帮我们解决这些问题的。参考 demo：3.3 节案例 B。

## @Provides：

@Provides 用于标注 Module 所标注的类中的方法，该方法在需要提供依赖时被调用，从而把预先提供好的对象当做依赖给标注了@Inject 的变量赋值；

 

##  @Component：

@Component 用于标注接口，是依赖需求方和依赖提供方之间的桥梁。被 Component 标注的接口在编译时会生成该接口的实现类（如果@Component 标注的接口为 CarComponent，则编译期生成的实现类为 DaggerCarComponent）,我们通过调用这个实现类的方法完成注入；

## @Qulifier 

用于自定义注解，也就是说@Qulifier 就如同 Java 提供的几种基本元注解一样用来标记注解类。我们在使用@Module 来标注提供依赖的方法时，方法名我们是可以随便定义的（虽然我们定义方法名一般以 provide 开头，但这并不是强制的，只是为了增加可读性而已）。

那么 Dagger2 怎么知道这个方法是为谁提供依赖呢？答案就是返回值的类型，Dagger2 根据返回值的类型来决定为哪个被@Inject 标记了的变量赋值。但是问题来了，一旦有多个一样的返回类型 Dagger2 就懵逼了。@Qulifier 的存在正式为了解决这个问题，我们使用@Qulifier 来定义自己的注解，然后通过自定义的注解去标注提供依赖的方法和依赖需求方（也就是被@Inject 标注的变量），这样 Dagger2 就知道为谁提供依赖了。一个更为精简的定义：当类型不足以鉴别一个依赖的时候，我们就可以使用这个注解标示；

 

## @Scope：

@Scope 同样用于自定义注解，我能可以通过@Scope 自定义的注解来限定注解作用域，实现单例（分局部和全局）；

@Scope 需要 Component 和依赖提供者配合才能起作用，对于@Scope 注解的依赖，Component 会持有第一次创建的依赖，后面注入时都会复用这个依赖的实例，实质上@Scope 的目的就是为了让生成的依赖实例的生命周期与 Component 绑定

如果 Component 重建了，持有的@Scope 的依赖也会重建，所以为了维护局部单例需要自己维护 Component 的生命周期。

## @Singleton：

@Singleton 其实就是一个通过@Scope 定义的注解，我们一般通过它来实现全局单例。但实际上它并不能提前全局单例，是否能提供全局单例还要取决于对应的 Component 是否为一个全局对象。 







```java
public static Builder builder() {
  return new Builder();
}
```

```java
public static final class Builder {
  private HttpModule httpModule;

  private DatabaseModule databaseModule;

  private Builder() {
  }

  public Builder httpModule(HttpModule httpModule) {
    this.httpModule = Preconditions.checkNotNull(httpModule);
    return this;
  }

  public Builder databaseModule(DatabaseModule databaseModule) {
    this.databaseModule = Preconditions.checkNotNull(databaseModule);
    return this;
  }

  public MyComponent build() {
    if (httpModule == null) {
      this.httpModule = new HttpModule();
    }
    if (databaseModule == null) {
      this.databaseModule = new DatabaseModule();
    }
    return new DaggerMyComponent(httpModule, databaseModule);
  }
}
```

```java
private DaggerMyComponent(HttpModule httpModuleParam, DatabaseModule databaseModuleParam) {
  this.httpModule = httpModuleParam;
  this.databaseModule = databaseModuleParam;
}
```

```java
private MainActivity injectMainActivity2(MainActivity instance) {
  MainActivity_MembersInjector.injectHttpObject(instance, HttpModule_ProviderHttpObjectFactory.providerHttpObject(httpModule));
  MainActivity_MembersInjector.injectDatabaseObject(instance, DatabaseModule_ProviderDatabaseObjectFactory.providerDatabaseObject(databaseModule));
  return instance;
}

//先看provide  这里就到了我们自己定义的providerHttpObject方法；
 public static HttpObject providerHttpObject(HttpModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providerHttpObject());
  }

	// inject 把上一步生成的对象赋值给MainActivity 中的对象。过程是比较简单，就看这些代码是怎么生成的
 @InjectedFieldSignature("com.baoge.dagger2.MainActivity.httpObject")
  public static void injectHttpObject(MainActivity instance, HttpObject httpObject) {
    instance.httpObject = httpObject;
  }
```





### 单例模式源码分析

```java
build最后一步，进入构造方法
    

  private DaggerMyComponent(HttpModule httpModuleParam, DatabaseModule databaseModuleParam) {
    this.databaseModule = databaseModuleParam;
    initialize(httpModuleParam, databaseModuleParam);
  }


  private void initialize(final HttpModule httpModuleParam,
      final DatabaseModule databaseModuleParam) {
      
      
      //写了单例之后多了个   DoubleCheck.provider !!!!
      
      
    this.providerHttpObjectProvider = DoubleCheck.provider(HttpModule_ProviderHttpObjectFactory.create(httpModuleParam));
  }



/**
*  工厂类，create 只是赋值一下modulue；get()方法的时候通过module调用方法创建object
**/
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class HttpModule_ProviderHttpObjectFactory implements Factory<HttpObject> {
  private final HttpModule module;

  public HttpModule_ProviderHttpObjectFactory(HttpModule module) {
    this.module = module;
  }

  @Override
  public HttpObject get() {
    return providerHttpObject(module);
  }

  public static HttpModule_ProviderHttpObjectFactory create(HttpModule module) {
    return new HttpModule_ProviderHttpObjectFactory(module);
  }

  public static HttpObject providerHttpObject(HttpModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providerHttpObject());
  }
}


 DoubleCheck.provider 到这里了
     首次进来肯定不会是DoubleCheck ， 后面就直接是，返回了
     不是的话，会组成一个包装类DoubleCheck 返回
     后面执行get的时候就会调用DoubleCheck的get方法了
     
     
  public static <P extends Provider<T>, T> Provider<T> provider(P delegate) {
    checkNotNull(delegate);
    if (delegate instanceof DoubleCheck) {
      /* This should be a rare case, but if we have a scoped @Binds that delegates to a scoped
       * binding, we shouldn't cache the value again. */
      return delegate;
    }
    return new DoubleCheck<T>(delegate);
  }
	//双重锁机制 保证单例

  public T get() {
    Object result = instance;
    if (result == UNINITIALIZED) {
      synchronized (this) {
        result = instance;
        if (result == UNINITIALIZED) {
          result = provider.get();
          instance = reentrantCheck(instance, result);
          /* Null out the reference to the provider. We are never going to need it again, so we
           * can make it eligible for GC. */
          provider = null;
        }
      }
    }
    return (T) result;
  }

dependencies

```



### dependencies 组合的方式

### @Subcomponent   组合的方式

### 单例SCOPE使用方式





