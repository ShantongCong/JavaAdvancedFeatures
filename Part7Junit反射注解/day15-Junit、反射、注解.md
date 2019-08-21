

# day15 - Junit、反射、注解

## 主要内容

- Junit单元测试
- 反射
- 注解

## 学习目标

- [ ] 能够使用Junit进行单元测试

- [ ] 能够通过反射技术获取Class字节码对象

  1. Class.forName(全类名)
  2. 类名.class
  3. 对象.getClass()

- [ ] 能够通过反射技术获取构造方法对象，并创建对象

  1. Class 中存在 getConstructor( 参数)
  2. newInstance

- [ ] 能够通过反射获取成员方法对象，并且调用方法

  1. getMethd :  可以获取本类及父类中的public方法
  2. getDeclaredMethod : 只能获取本类的所有包含private的方法

- [ ] 能够自定义注解

  ```
  @Target 指定位置
  @Retention 指定生命周期
  public @interface 注解名{
    String vlaue()；
  }
  ```

- [ ] 能够使用自定义注解

  1. 可以在根元注解指定的位置，在指定位置上注解

# 第1章 Junit单元测试

测试分类：
- 黑盒测试：不需要写代码，给输入值，看程序是否能够输出期望的值。
- 白盒测试：需要写代码的。关注程序具体的执行流程。

![](img/测试分类.bmp)

​	Junit是一个Java语言的单元测试框架，属于白盒测试，简单理解为可以用于取代java的main方法。
​	Junit属于第三方工具，需要导入jar包后使用。

###  Junit测试使用步骤

1. 编写测试类，简单理解Junit可以用于取代java的main方法

   说明：如果以前想让一个方法运行必须在main方法中调用该方法。![1556078373316](assets/1556078373316.png)

2. 在测试类JunitDemo01方法上添加注解 @Test（说明：关于什么是注解，下面会详细讲解） 

  ![1556078462690](assets/1556078462690.png) 

3. @Test修饰的方法要求：public void 方法名() {…} ，没有参数。

   ==**说明：单元测试的方法必须是public修饰，void表示没有返回值，没有参数列表，否则就会不满足单元测试要求，报异常**==。

   ![1556078542161](assets/1556078542161.png) 

4. 添加Idea中集成的Junit库，鼠标放在“@Test”处，使用快捷键“alt+Enter”，点击“Add Junit …”
  ![1556078626138](assets/1556078626138.png)

  ![1556078637906](assets/1556078637906.png) 

5. 使用：选中方法右键，执行当前方法；选中类名右键，执行类中所有方法（方法必须标记@Test）

  ![1556078709856](assets/1556078709856.png) 

  绿条: 正常运行
  红条: 出现问题,异常了

  

  

  注意：写测试方法要注意，只能是公共的无参无返回值的方法

  ```java
  @Test
  public void 方法名(){
    
  }
  ```

  ![1564880210922](assets/1564880210922.png) 

### 常用Junit注解

1. `@Test`，用于修饰需要执行的方法

2. `@Before`，测试方法前执行的方法

3. `@After`，测试方法后执行的方法

   ```java
   public class JunitDemo02 {
   	@Test
   	public void myTest(){
   		System.out.println("测试 test");
   	}
   	
   	@Before
   	public void myBefore(){
   		System.out.println("方法前");
   	}
   	
   	@After
   	public void myAfter(){
   		System.out.println("方法后");
   	}
   }
   ```

   ![1556078289643](assets/1556078289643.png) 



# 第2章 反射：框架设计的灵魂

## 2.1 反射概述

反射是一种机制/功能，利用该机制/功能可以在程序运行过程中==对类进行解剖==并操作类中的构造方法，成员方法，成员属性。

### 2.1.1 反射的使用场景

1. 开发工具中写代码时的提示

   开发工具之所能够把该对象的方法和属性展示出来就使用利用了反射机制对该对象所有类进行了==解剖获取到了类中的所有方法和属性信息==，这是反射在IDE中的一个使用场景。

   ![1556079606412](assets/1556079606412.png) 

2.  各种框架的设计【SSM=Spring+SpringMVC+MyBatis】

   ![1556080052414](assets/1556080052414.png)

   框架：半成品软件。可以在框架的基础上进行软件开发，简化编码

### 2.1.2 反射的好处

1. 可以在程序运行过程中，操作这些对象。
2. 可以解耦，提高程序的可扩展性。

## 2.2 类的加载

我们要使用反射，那么就得研究类的对象是如何产生，一个class字节码文件是如何加载到内存中的，这是使用反射机制解剖类的前提。

当程序要使用某个类时，如果该类还未被加载到内存中，则系统会通过加载，连接，初始化三步来实现对这个类进行初始化。

1. **加载** 
  
  1. 就是指将class文件读入内存，并为之创建一个Class对象。
  2. 任何类被使用时系统都会建立一个Class对象
  
2. **连接**
  
  1. 验证是否有正确的内部结构，并和其他类协调一致
  2. 准备负责为类的静态成员分配内存，并设置默认初始化值
  3. 解析将类的二进制数据中的符号引用替换为直接引用
  
3. **初始化** 

   在类的初始化阶段，虚拟机负责对类进行初始化，主要就是对类变量【静态变量】进行初始化。
   在 Java 类中对类变量指定初始值有两种方式：
   ​	① 声明类变量时指定初始值；
   ​	② 使用静态代码块为类变量指定初始值。

![1556088494865](assets/1556088494865.png)

![1564883240989](assets/1564883240989.png) 

当一个类完成加载后就会产生一个该类型的Class对象，我们要使用反射技术，就得使用该Class对象。如何获取该类型的Class对象呢？请看下节内容。

## 2.3 获取Class对象的方式

| 获取class对象          | 作用                  | 应用场景         |
| --------------------| -------------------- | ------------------ |
| Class.forName("全类名") | 通过指定的字符串路径获取     | 多用于配置文件，将类名定义在配置文件中。读取文件，加载类 |
| 类名.class              | 通过类名的属性class获取      | 多用于参数的传递                                         |
| 对象.getClass()         | 通过对象的getClass()方法获取 | 多用于对象的获取字节码的方式                             |

==注意：同一个字节码文件(*.class)在一次程序运行过程中，只会被加载一次，不论通过哪一种方式获取的Class对象都是同一个。==

```java
@Test
public void testClass() throws ClassNotFoundException {
    //1.Class.forName("全类名")
  	//全类名路径：含有包名的全类名
    Class<?> cls1 = Class.forName("com.itheima.demo01Reflect.User");
    //2.类名.class
    Class<User> cls2 = User.class;
    //3.对象.getClass()
    Class<? extends User> cls3 = new User().getClass();

    //类只加载一次，内存中只会存在一份User 文件
    System.out.println(cls1 == cls2); // true
    System.out.println(cls1 == cls3); // true
}
```

## 2.4 获取Class对象信息

### 2.4.1 获取类名信息

我们已经获取了Class对象了，接下来就介绍几个Class类中常用的方法了。

1. `String getSimpleName()`; 获得简单类名，只是类名，没有包		
2. `String getName();` 获取完整类名，包含包名+类名	
3. `T newInstance() ;`创建此 Class 对象所表示的类的一个新实例。要求：==类必须有public的无参数构造方法==【过时的方法，不建议使用】

```java
@Test
public void testClass2() throws IllegalAccessException, InstantiationException {
    //获取User类的Class对象
    Class<User> cls = User.class;
    //1. String getSimpleName(); 获得简单类名，只是类名，没有包
    String simpleName = cls.getSimpleName();
    System.out.println("simpleName = " + simpleName); //User
    //2. String getName(); 获取完整类名，包含包名+类名
    String name = cls.getName();
    System.out.println("name = " + name);// com.itheima.demo01Reflect.User
}
```

测试结果：

![1556090042762](assets/1556090042762.png)



一开始在阐述反射概念的时候，我们说到利用反射可以在程序运行过程中对类进行解剖并操作里面的成员。而一般常操作的成员：构造方法，成员方法，成员属性，那么接下来看看怎么利用反射来操作这些成员以及操作这些成员能干什么，先来看看怎么操作构造方法。

### 2.4.2 获取类中构造器

要通过反射操作类的构造方法，我们需要先知道一个Constructor类。Constructor是构造方法类，类中的每一个构造方法都是Constructor的对象，通过Constructor对象可以实例化对象。

#### Constructor类概述

Constructor是构造方法类，类中的每一个构造方法都是Constructor的对象，通过Constructor对象可以实例化对象。

![1556092262925](assets/1556092262925.png)

#### Class中获取Constructor的方法

1. `Constructor[] getConstructors()` 

    获取所有的public修饰的构造方法

2. `Constructor getConstructor(Class... parameterTypes)`  

    根据参数类型获取构造方法对象，只能获得public修饰的构造方法。如果不存在对应的构造方法，则会抛出 java.lang.NoSuchMethodException 异常。
    
    ```java
    参数是可变参数,调用此方法时,可以不写参数,获取的空参构造可以写参数,给定的参数必须是Class对象
    比如:
    参数	类名(String name,int age)
    调用此方法: String.class,int.class 
    ```
    
    例如，一已知存在User类如下：
    
    ```java
    public class User {
        int age 
        String name;
    
        public User(){}
    
        public User(String name,int age) {
            this.name = name;
            this.age = age;
        }
    }
    ```
    
    1. 获取所有public构造方法
    
    2. 获取public修饰的空参构造方法对象
    
    3. 获取public修饰的第一个参数是String类型,第二个参数是int类型的构造方法对象
    
       ```java
       @Test
       public void testConstructor() throws NoSuchMethodException {
           Class<User> cls = User.class;
           //1. 获取所有的public构造方法
           Constructor<?>[] cons = cls.getConstructors();
           for (Constructor<?> con : cons) {
               System.out.println("con = " + con);
           }
           System.out.println("========");
           //2. 获取public修饰的空参构造方法对象
           Constructor<User> con1 = cls.getConstructor();
           System.out.println("con1 = " + con1);
           System.out.println("========");
           //3. 获取public修饰的第一个参数是String类型,第二个参数是int类型的构造方法对象
           Constructor<User> con2 = cls.getConstructor(String.class, int.class);
           System.out.println("con2 = " + con2);
       }
       ```
    
       运行如下：
    
       ![1556093210198](assets/1556093210198.png)

#### Constructor类中常用方法

1. T newInstance(Object... initargs)   根据指定参数创建对象。
2. T newInstance()  空参构造方法创建对象。

```java
@Test
public void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<User> cls = User.class;

    //1. 获取public修饰的空参构造方法对象
    Constructor<User> con1 = cls.getConstructor();
    User u1 = con1.newInstance();
    System.out.println("name="+u1.name+":age="+u1.age);

    System.out.println("========");
    //2. 获取public修饰的第一个参数是String类型,第二个参数是int类型的构造方法对象
    Constructor<User> con2 = cls.getConstructor(String.class, int.class);
    User u2 = con2.newInstance("小强", 10);
    System.out.println("name="+u2.name+":age="+u2.age);

}
```

运行结果如下：

![1556093748861](assets/1556093748861.png) 



### 2.4.3 获取类中方法

Method是方法类，类中的每一个方法都是Method的对象，通过Method对象可以调用方法。

![1556094406370](assets/1556094406370.png) 

#### Class类获取Method相关方法

```java
1. Method[] getMethods()
获取所有的public修饰的成员方法，包括父类中

2. Method[] getDeclaredMethods()
获取当前类中所有的方法，包含私有的，不包括父类中

3. Method getMethod("方法名", 方法的参数类型... 类型)
根据方法名和参数类型获得一个方法对象，只能是获取public修饰的

4. Method getDeclaredMethod("方法名", 方法的参数类型... 类型)
根据方法名和参数类型获得一个方法对象，包括private修饰的

```

#### Method类中常用方法

```java
1. Object invoke(Object obj, Object... args)
根据参数args调用对象obj的该成员方法
如果obj=null，则表示该方法是静态方法

2. void setAccessible(true)
暴力反射，设置为可以直接调用私有修饰的成员方法【对于私有方法使用前需要调用该方法】
```

> 编写代码演示

存在User类：

```java
User.java
/**
 * 用户 JavaBean
 */
public class User {
    //项目开发中，字段和属性名相同
    private int id;//编号
    private String name;//姓名
    private double sal;//薪水

    public User(){}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSal() {
        return sal;
    }
    public void setSal(double sal) {
        this.sal = sal;
    }
}

```

```java
/**
 * 获取Class对象的Method信息
 */
public class Demo04 {

    /**
     * 演示Method[] getMethods()
     * JUnit单元测试
     */
    @Test
    public void test1() {
        Class clazz = User.class;
        Method[] ms = clazz.getMethods();
        for(Method m:ms){
            System.out.println(m);
        }
    }

    /**
     * 演示Method[] getDeclaredMethods()
     */
    @Test
    public void test2(){
        Class clazz = User.class;
        Method[] ms = clazz.getDeclaredMethods();
        for(Method m:ms){
            System.out.println(m);
        }
    }

    /**
     * 演示Method getMethod("方法名", 方法的参数类型... 类型)
     */
    @Test
    public void test3() throws Exception{
        Class clazz = User.class;
        //参数一：方法名
        //参数二：方法名中参数的Class类型，如果无的话，书写null
        Method m = clazz.getMethod("set",null);
        System.out.println(m);
    }

    /**
     * 演示Method getDeclaredMethod("方法名", 方法的参数类型... 类型)
     */
    @Test
    public void test4() throws Exception{
        Class clazz = User.class;
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("show",null);
        System.out.println(m);
    }

    /**
     * 演示Object invoke(Object obj, Object... args)
     */
    @Test
    public void test5() throws Exception{
        Class clazz = User.class;
        Object obj = clazz.newInstance();
        //参数一：方法名
        //参数二：方法名中参数的Class类型，如果无的话，书写null
        Method m = clazz.getMethod("set",null);
                //调用方法
        //参数一：对象
        //参数二：实际参数，如果无的话，书写null
        m.invoke(obj,null);
    }

    /**
     * 演示void setAccessible(true)
     */
    @Test
    public void test6()throws Exception{
        Class clazz = User.class;
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("show",null);
        //暴力反射
        m.setAccessible(true);
        m.invoke(obj,null);
    }

```



### 2.4.4 获取类中属性

#### Field类概述

Field是属性类，类中的每一个属性都是Field的对象，通过Field对象可以给对应的属性赋值和取值。

![1556095819688](assets/1556095819688.png)  

####  Class类中与Field相关方法

```java
1. Field[] getFields()
获取所有的包括父类中public修饰的属性对象，返回数组

2. Field[] getDeclaredFields()
获取所有本类属性对象，包括private修饰的，返回数组

3. Field getField(String name)
根据属性名获得属性对象，只能获取public修饰的

4. Field getDeclaredField(String name)
根据属性名获得属性对象，包括private修饰的
```



#### Filed类中的方法

```java
1. Object get(Object obj) 
  返回指定对象上此 Field 表示的字段的值。 
  
2. void set(Object obj, Object value) 
  将指定对象变量上此 Field 对象表示的字段设置为指定的新值。 
3. void setAccessible(true);
	暴力反射，设置为可以直接访问私有类型的属性
```

例如：已知类User，通过反射的方式获取属性

```java
public class User {

    //项目开发中，字段和属性名相同

    private int id;//编号
    private String name;//姓名
    private double sal;//薪水

    public User(){}
		//省略 getter/setter方法
}
```

```java
/**
 * 获取Class对象的Field信息
 */
public class Demo05 {

    /**
     * 演示Field[] getFields()
     */
    @Test
    public void test1() throws Exception{
        Class clazz = User.class;
        Field[] fs = clazz.getFields();
        for (Field f : fs) {
            System.out.println(f);
        }
    }

    /**
     * 演示Field[] getDeclaredFields()
     */
    @Test
    public void test2()throws Exception{
        Class clazz = User.class;
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            System.out.println(f);
        }
    }

    /**
     * 演示Field getField(String name)
     */
    @Test
    public void test3()throws Exception{
        Class clazz = User.class;
        //获取某个Field对象
        //参数一：字段名
        Field f = clazz.getField("id");
        System.out.println(f);
    }

    /**
     * 演示Field getDeclaredField(String name)
     */
    @Test
    public void test4()throws Exception{
        Class clazz = User.class;
        Field f = clazz.getDeclaredField("sal");
        System.out.println(f);
    }

    /**
     * 演示set/get()
     */
    @Test
    public void test5()throws Exception{
        Class clazz = User.class;
        Object obj = clazz.newInstance();
        //获取某个Field对象
        //参数一：字段名
        Field f = clazz.getField("id");
        //为id字段设置值
        //参数一：对象
        //参数二：实际值
        f.setInt(obj,2018);
        //从id字段中获取值
        System.out.println(f.getInt(obj));
    }

    /**
     * 演示void setAccessible(true)
     */
    @Test
    public void test6()throws Exception{
        Class clazz = User.class;
        Object obj = clazz.newInstance();
        //f指向私有成员变量
        Field f = clazz.getDeclaredField("sal");
        //暴力反射
        f.setAccessible(true);
        f.setDouble(obj,5555.55);
        System.out.println(f.getDouble(obj));

    }
}

```



## 3 反射案例

需求：写一个"框架"，不能改变该类的任何代码的前提下，可以帮我们创建任意类的对象，并且执行其中任意方法

实现：

1. 配置文件
2. 反射

步骤：

1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
2. 在程序中加载读取配置文件
3. 使用反射技术来加载类文件进内存
4. 创建对象
5. 执行方法

> 编写代码演示

pro.properties

```java
className=com.itheima.domain.Student
methodName=sleep
```

Student.java

```java
package com.itheima.domain;

public class Student {
    public void sleep(){
        System.out.println("sleep...");
    }
}
```

RefectTest.java

```java
public class ReflectTest {
    @Test 
    public void test() throws Exception {
        //可以创建任意类的对象，可以执行任意方法
        //前提：不能改变该类的任何代码。可以创建任意类的对象，可以执行任意方法

        //1.加载配置文件
        //1.1创建Properties对象
        Properties pro = new Properties();
        //1.2加载配置文件，转换为一个集合
        InputStream is = new FileInputStream("pro.properties");
        pro.load(is);

        //2.获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3.加载该类进内存
        Class cls = Class.forName(className);
        //4.创建对象
        Object obj = cls.newInstance();
        //5.获取方法对象
        Method method = cls.getMethod(methodName);
        //6.执行方法
        method.invoke(obj);
    }
}
```



# 第3章 注解

## 4.1 注解概述

### 注解是什么

注解（Annotation）相当于一种标记，在程序中加入注解就等于为程序打上某种标记，以后，javac编译器、开发工具和其他程序可以通过反射来了解你的类及各种元素上有无标记，看你的程序有什么标记，就去干相应的事，标记可以加在包、类，属性、方法，方法的参数以及局部变量上定义。

### 注解的应用场景

1. 在代码中辅助说明代码的功能或者限定语法格式

   ```
   例如：
   重写方法时使用 @Override 限定子类方法格式
   函数式接口使用 @FunctionnalInterface 限定接口格式
   单元测试时用来标记方法使用 @Test
   表示方法已经过时 @Deprecated
   ```

2. 在框架中配置数据

   现在多数框架都基于注解进行配置【后面又例子说明】



## 4.2 自定义注解

### 定义格式

```java
public @interface 注解名称{
	注解成员
}
```

- 注解本质上就是一个接口，该接口默认继承Annotation接口

```java
public interface MyAnno extends java.lang.annotation.Annotation {}
```

当然要注意的是，不是说接口继承了Annotation就是注解了。注解一定要按照格式定义。

### 注解的成员定义

1. **注解成员的作用**

   可以用来保存注解中需要保存得值

2. **注解成员定义格式**

   其实就是接口中定义一个==无参有返回值类型得抽象方法==，特别的是可以给成员指定一个默认值。如下：

   - **格式1：数据类型 属性名();**
   - **格式2：数据类型 属性名() default 默认值;**

   例如：

   ```java
   public @interface StudentInfo {
     String name(); // 姓名
     int age() default 18; // 年龄
     String gender() default "男"; // 性别
   } 
   ```

3. **成员支持的类型**

   1. 八种基本数据类型（**int,float,boolean,byte,double,char,long,short**)
   2. String类型，Class类型，Enum枚举类型，注解类型
   3. 以上所有类型的对应的一维数组

   

## 4.3 使用自定义注解

### 使用注解

定义好注解后，我们可以将注解作用在类，成员变量及方法上。

使用格式:

```
@注解名(属性名 = 属性值, 属性名 = 属性值, ...)
```

例如：

```java
@StudentInfo(name = "李四", age = 20, gender = "女")//注解作用在类上
public class Demo01Student {

    @StudentInfo(name = "李四", age = 20, gender = "女")//注解作用在成员变量上
    Student stu;

    @StudentInfo(name = "李四", age = 20, gender = "女")//注解作用在方法上
    public void showStudent(Student student) {
        System.out.println("student = " + student);

        Student stu;
    }

}
```

==**使用注意事项**==

- 如果属性有默认值，则使用注解的时候，这个属性可以不用赋值。
- 如果属性没有默认值，那么在使用注解时一定要给属性赋值。 

### 特殊属性value

1. **当注解中只有一个属性且名称是value，在使用注解时给value属性赋值可以直接给属性值，无论value是单值元素还是数组类型。**

```java
// 定义注解Book
public @interface Book {
    // 书名
    String value();
}

// 使用注解Book
public class BookShelf {
    @Book("西游记")
    public void showBook(){

    }
}
或
public class BookShelf {
    @Book(value="西游记")
    public void showBook(){

    }
}
```

2.**如果注解中除了value属性还有其他属性，且至少有一个属性没有默认值，则在使用注解给属性赋值时，value属性名不能省略。**

```java
// 定义注解Book
public @interface Book {
    // 书名
    String value();
    // 价格
    double price() default 100;
    // 多位作者
    String[] authors();
}

// 使用Book注解：正确方式
@Book(value="红楼梦",authors = "曹雪芹")
public class BookShelf {
  // 使用Book注解：正确方式
    @Book(value="西游记",authors = {"吴承恩","白求恩"})
    public void showBook(){

    }
}
// 使用Book注解：错误方式
public class BookShelf {
    @Book("西游记",authors = {"吴承恩","白求恩"})
    public void showBook(){
    }
}
// 此时value属性名不能省略了。
```

## 4.4 元注解

### 元注解概述

用来定义注解的注解，用来说明自定义注解的在类中出现的位置和生命周期

### 常用元注解

#### @Target

```java
作用：定义该注解用在哪个位置，如果不写，默认是类中任何地方都可以使用。

可选的参数值在枚举类ElemenetType中包括：
TYPE： 用在类,接口上
FIELD：用在成员变量上
METHOD： 用在方法上
PARAMETER：用在参数上
CONSTRUCTOR：用在构造方法上
LOCAL_VARIABLE：用在局部变量上
```

#### @Retention

```java
作用：定义该注解的生命周期

可选的参数值在枚举类型RetentionPolicy中包括：
SOURCE：注解只存在于Java源代码中，编译生成的字节码文件中就不存在了。
CLASS：注解存在于Java源代码、编译以后的字节码文件中，运行的时候内存中没
有，这个是默认值。
RUNTIME：注解存在于Java源代码中、编译以后的字节码文件中、运行时内存中，
程序可以通过反射获取该注解

```

例如:

自定义一个注解

```java
@Retention(value = RetentionPolicy.RUNTIME) // 注解声明周期到运算时
@Target(value = ElementType.METHOD) //只能用于方法
public @interface Book {
    int id() default 10;//图书编号

    String author() default "赵君";//作者

    double price() default 11.11;//单价
}
```

使用该注解时，只能在本：

![1556367165141](assets/1556367165141.png) 

## 4.5 解析注解

通过Java技术，在程序运行时，获取注解中所有属性的过程则称为解析注解。

### 与注解解析相关的接口

Anontation：所有注解类型的公共接口，类似所有类的父类是Object。

AnnotatedElement接口：定义了与注解解析相关的方法，常用方法以下四个：

```java
boolean isAnnotationPresent(Class annotationClass)判断当前对象是否有指定的注解，有则返回true，否则返回false。
T getAnnotation(Class<T> annotationClass)获得当前对象上指定的注解对象。
Annotation[] getAnnotations()获得当前对象及其从父类上继承的所有的注解对象。
Annotation[] getDeclaredAnnotations()获得当前对象上所有的注解对象，不包括父类的。

```

AnnotatedElement接口是Class, Method，Constructor，Filed等的父接口，所以我们在判断这些对象是否存在注解时，可以使用通用方法。



![1556368731115](assets/1556368731115.png) 



### 获取注解数据的原理

注解作用在那个成员上，就通过反射获得该成员的对象来得到它的注解。

如注解作用在方法上，就通过方法(Method)对象得到它的注解

```java
// 得到方法对象
Method method = clazz.getDeclaredMethod("方法名");
// 根据注解名得到方法上的注解对象
Book book = method.getAnnotation(Book.class);

```

如注解作用在类上，就通过Class对象得到它的注解

```java
// 获得Class对象
Class c = 类名.class;
// 根据注解的Class获得使用在类上的注解对象
Book book = c.getAnnotation(Book.class);

```

【练习】

```java
/**
 * 解析注解Book
 */
public class Demo03 {

    /**
     * 在程序运行时，获取注解Book的所有属性值
     */
    @Book
    public void test1() throws Exception{
        //@Book->test1()->Class

        //获取Demo03类的Class对象
        Class clazz = Demo03.class;
        //获取Class对象的Method对象
        Method m = clazz.getMethod("test1",null);
        //获取Method对象中的@Book注解
        //参数一：注解类型的Class
        //返回值：注解
        Book book = m.getAnnotation(Book.class);
        //获取Book注解里面的所有属性值
        System.out.println(book.id());
        System.out.println(book.author());
        System.out.println(book.price());
    }


    public static void main(String[] args) throws Exception{
        Demo03 test = new Demo03();
        test.test1();
    }

}
```



## 注解案例

使用反射获取注解的数据 需求说明 

1. 定义注解BookInfo，要求如下：
   - 包含属性：String value()   书名 
   1. 包含属性：double price()  价格，默认值为 100 
   2. 包含属性：String[] authors() 多位作者 
   3. 限制注解使用的位置：类和成员方法上 
   4. 指定注解的有效范围：RUNTIME 
2. 定义BookStore类，在类和成员方法上使用BookInfo注解 
3. 定义TestAnnotation测试类获取BookInfo注解上的数据 

按照需求实现

- BookInfo

```java

@Target({ElementType.METHOD, ElementType.TYPE}) 
//支持用在方法上和类型上
@Retention(RetentionPolicy.RUNTIME)// 生命周期到运行时
public @interface BookInfo {
    String value();         // 书名
    double price() default 100;   // 价格
    String[] authors();      // 作者
}
```

- BookStore

```java
@BookInfo(value = "红楼梦", authors = "曹雪芹", price = 998)
public class BookStore {
    @BookInfo(value = "西游记", authors = "吴承恩")
    public void buyBook() {
    }
}
```

- TestAnnotation

```java

public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException {
        //获取Class对象
        Class<BookStore> cls = BookStore.class;
        boolean result = cls.isAnnotationPresent(BookInfo.class);
        if (result) {
            BookInfo bookInfo = cls.getAnnotation(BookInfo.class);
            showBookInfo(bookInfo);
        }

        System.out.println("========");

        //获取方法
        Method buyBook = cls.getDeclaredMethod("buyBook");
        if (buyBook.isAnnotationPresent(BookInfo.class)) {
            BookInfo bookInfo = buyBook.getAnnotation(BookInfo.class);

            showBookInfo(bookInfo);
        }

    }

    private static void showBookInfo(BookInfo bookInfo) {
        String value = bookInfo.value();
        String[] authors = bookInfo.authors();
        double price = bookInfo.price();

        System.out.println("value = " + value);
        System.out.println("price = " + price);
        System.out.println("authors = " + Arrays.toString(authors));
    }
}	
```

​	![1556373974919](assets/1556373974919.png) 