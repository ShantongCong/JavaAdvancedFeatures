# 类加载器、动态代理、XML

# 学习目标

1. 知道如何获取类加载器
2. 能够理解代理模式的原理
3. 能够说出 XML 的作用
4. 了解 XML 的组成元素
5. 能够说出有哪些 XML约束技术
6. 能够说出解析 XML 文档 DOM方式原理
7. 能够说出Dom4j常用的类
8. **能够通过Dom4j得到文档对象(重点)**
9. **能够读取Dom树上的元素对象(重点)**

    



# 学习内容

## 1. 类加载器

### 目标

1. 知道类加载器的作用
2. 能够获取类加载器
3. 能够使用类加载加载类



### 类加载器的作用

**==类加载器（class loader）用来加载 Java 类到 Java 虚拟机中==**Java 虚拟机使用 Java 类的方式如下：Java 源程序（.java 文件）在经过 Java 编译器编译之后就被转换成 Java 字节代码（.class 文件）。类加载器负责读取 Java 字节代码，并转换成 `java.lang.Class`类的一个实例。每个这样的实例用来表示一个 Java 类

基本上所有的类加载器都是 `java.lang.ClassLoader`类的一个实例。



### 获取类加载器对象的两种方式

1. 通过Class对象获取类加载器，Class类中存在以下方法可以获取类加载器。

    ```java
    public ClassLoader getClassLoader() : 返回系统类加载器 
    例如：ClassLoader classLoader = Student.class.getClassLoader();
    ```

2. 通过ClassLoader类的静态方法来获取类加载器

    ```java
    public static ClassLoader getSystemClassLoader() ： 返回系统类加载器 
    例如: ClassLoader loader = ClassLoader.getSystemClassLoader();
    ```


```java
/*
类加载器的获取：
 */
public class Demo01 {
    public static void main(String[] args) {
        //1.通过Class对象进行获取类加载器
        ClassLoader c1 = Demo01.class.getClassLoader();
        //2.ClassLoader的静态方法：getSystemClassLoader
        ClassLoader c2 = ClassLoader.getSystemClassLoader();

        System.out.println(c1 == c2);//true
        //以上两种获取类加载器对象的方式是一样的
    }
}
```

### 类加载器的使用

ClassLoader加载类的方法

```java
public Class<?> loadClass(String name)：根据类的全路径名，加载该类获取字节码Class对象
```



我们可以使用类加载器加载一个已知路径的类，获取器字节码Class对象。如下

在工程中创建一个类： `com.itheima.Student` ,通过类加载器加载该类并通过字节码对象获取

```java
public class Demo01 {
    public static void main(String[] args) throws Exception {
        //获取系统类加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        //加载类 com.itheima.Student
        Class<?> aClass =  loader.loadClass("com.itheima.Student");
        //获取构造方法
        Constructor<?> cons = aClass.getConstructor(int.class, String.class);
        //使用构造方法实例化对象
        Student stu = (Student) cons.newInstance(19, "迪丽热巴");
        //打印结果
        System.out.println(stu.toString());
    }
}
```

### 小结

1. 知道类加载器的作用：将字节码文件加载到内存中

2. 能够获取类加载器：

    1. 通过Class对象获取
    2. 通过ClassLoader的静态方法

3. 能够使用类加载加载类

    ```java
    Class<?> loadClass(String name)
    ```

    



## 2. 代理设计模式

定义：对其他对象提供一种代理以控制对这个对象的访问。

特征：代理类与委托类有同样的接口，代理类主要负责为委托类预处理消息、过滤消息、把消息转发给委托类，以及事后处理消息等

按照代理的创建时期，代理类可以分为两种：

1. 静态代理：由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。 
2. 动态代理：在程序运行时，运用反射机制动态创建而成。 

### 静态代理

#### 目标

1. 能够理解静态代理实现步骤

#### 案例

静态代理方式实现经纪人代理明星案例

接口

```java
/*
 * 描述明星的接口
 */
public interface Star {
	//综艺节目
	public abstract void liveShow(double money);
}	
```

被代理类的接口

```java
/*
 * 被代理类型 目标业务对象 明星王宝强
 */
public class WangBaoQiang implements Star{
	//参加综艺节目
	public void liveShow(double money) {
		System.out.println("宝强参加《奔跑吧，兄弟》赚了"+money+"元");
	}

}
```

代理类实现接口

```java
/*
 * 代理对象 宋哲 经纪人
 */
public class SongZhe implements Star {
	// 持有被代理对象
	private WangBaoQiang wbq ;

	public SongZhe(WangBaoQiang wbq) {
		this.wbq = wbq;
	}

	public void liveShow(double money) {
		// 对money变量进行判断，修改访问参数
		if (money < 500000) {
			System.out.println("滚，穷屌丝，一边玩泥巴去");
			return;
		}
		System.out.println("宋哲抽取了" + money * 0.2 + "元代理费用");
		wbq.liveShow(money * 0.8);
	}
 
}
```

测试类

```java
/*
 * 代理模式演示
 */
public class ProxyDemo1 {
	public static void main(String[] args) {
    //被代理类对象
    WangBaoQiang bq=new WangBaoQiang();
	  //代理类对象【委托类对象】
		SongZhe sz = new SongZhe(bq);
   //调用方法
		sz.liveShow(1000000);
	}
}
```



#### 小结

代理类和被代理类有什么特点？

有共同的接口



### 动态代理

在实际开发过程中往往我们自己不会去创建代理类而是通过JDK提供的`Proxy`类在程序运行时，运用反射机制动态创建而成，这就是我们所谓的动态代理。

#### 目标

能够动态创建代理对象

#### 相关类及接口学习

##### Proxy类

 `java.lang.reflect.Proxy`类提供了用于创建动态代理类和实例的静态方法，它还是由这些方法创建的所有动态代理类的超类。 

```java
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 生成代理对像
```

方法说明

- 返回值==：该方法返回就是动态生成的代理对象==
- 参数列表说明：
    1. ClassLoader loader - 定义代理类的类加载器
    2. Class<?>[] interfaces - 代理类要实现的接口列表，==要求与被代理类的接口一样==。
    3. InvocationHandler h - 就是具体实现代理逻辑的接口



##### InvocationHandler接口

`java.lang.reflect.InvocationHandler`是代对象的实际处理代理逻辑的接口，具体代理实现逻辑在其invoke方法中，如下

```java
public Object invoke(Object proxy, Method method, Object[] args) 
在代理对象上调用的所有方法最终都会到这里来执行，我们可以根据method得到方法名，判断选择我们需要的方法进行处理即可。 
```

方法说明

1. 返回值：方法被代理后执行的结果。
2. 参数列表：
    1. proxy - 就是代理对象
    2. method - 代理对象调用的方法
    3. args - 代理类调用方法传入参数值的对象数组，如果接口方法不使用参数，则为 null。基本类型的参数被包装在适当基本包装器类（如 java.lang.Integer 或 java.lang.Boolean ）的实例中。



#### 动态代理实践

还是宋哲代理王宝强案例

步骤分析：

1. 先存在被代理对象，所以先创建王宝强对象
2. 要使用动态代理，需要把动态中三个参数准备好：
    1. 类加载器
    2. 接口类列表
    3. 调用处理器
3. 创建代理对象
4. 使用代理对象调用方法测试效果

【代码实现】

```java
public class ProxyDemo {

    public static void main(String[] args) {
        //创建被代理对象，要被内部类使用，所以要final修饰
        final WangBaoQiang baoQiang = new WangBaoQiang();
        //准备生产动态代理的函数的三个参数
        //1.获取类加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        //2.获取被代理对象的接口
        Class[] interfaces = baoQiang.getClass().getInterfaces();
        //3.调用处理器 修改代理对象拦截被代理对象的变量
        InvocationHandler h = new InvocationHandler() {
            // 代理类中的任何方法，其实内部都是执行了处理器中的invoke方法
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                if ("liveShow".equals(name)) {
                    //liveShow函数参数只有一个获取第一个
                    double money = (double) args[0];
                    // 对money变量进行判断，修改访问参数
                    if (money < 500000) {
                        System.out.println("滚，穷屌丝，一边玩泥巴去");
                        return null;
                    }
                    System.out.println("宋哲抽取了" + money * 0.2 + "元代理费用");
                    //执行LiuYan类中的函数
                    return method.invoke(baoQiang, money * 0.8);
                }
               
                return method.invoke(baoQiang, args);
            }
        };
        //获得代理对象，songZhe表示动态获取的代理对象
        Star songZhe = (Star) Proxy.newProxyInstance(loader, interfaces, h);
       
        //使用代理对象调用函数
        songZhe.liveShow(1000000);
      
    }
}
```

#### 知识小结

1. 动态创建代理对象的是什么类，用什么方法，方法参数有哪些？

    Proxy：代理

    方法

    ```java
    Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler handler)
    ```

    loader: 类加载器

    interfaces：代理类和被代理类的共同接口的Class是对象

    handler：具体处理代理逻辑的地方

2. 描述InvocationHandler接口其方法invoke有何作用？

    InvocationHandler： 调用处理器

    ```java
    Object invoke(Object proxy, Method method,Object[] args){
      判断方法是否是需要代理的，如果是需要代理的方法，就书写代理逻辑
    }
    ```

    

## 3.XML概述

### 目标

1. 什么是XML
2. XML与HTML的区别
3. 编写第1个XML文件

### 什么是XML

1. 英文：eXtensible Markup Language 可扩展的标记语言，由各种标记(标签，元素)组成。
2. HTML：Hyper Text Markup Language 超文本标记语言，由各种标记组成。每个标记都有自己的功能和属性，功能和属性是固定的。HTML在前端课程很快将会学习到
3. 可扩展：所有的标签都是自定义的，可以随意扩展的。如：\<abc/>，<姓名>
4. 标记语言：整个文档由各种标记组成。

### XML作用

1) 数据存储得载体：不同的计算机语言之间，不同的操作系统之间，不同的数据库之间，进行数据传输。            ![1552221268464](img/1552221268464.png)                                       

2)  配置文件：在后期我们主要用于各种框架的配置文件。即将学习到数据库有其配置文件c3p0-config.xml

![1552221310087](img/1552221310087.png)

### XML与HTML的差异

| **区别**     | **HTML**                       | **XML**              |
| ------------ | ------------------------------ | -------------------- |
| **功能**     | 用于在浏览器中显示数据         | 描述和存储数据       |
| **大小写**   | 不区分                         | 严格区分大小写       |
| **语法严谨** | 不严谨，浏览器可以纠错         | 标签必须严格配对     |
| **可扩展性** | 不可以扩展，每个标签都是固定的 | 所有的标签都是扩展的 |



###案例：编写第1个XML文件

#### 需求

编写xml文档，用于描述人员信息，person代表一个人员，id是人员的属性代表人员编号。人员信息包括name姓名、age年龄、sex性别信息。

#### 效果

![1552352318788](img/1552352318788.png) 

#### 步骤

1. 选择当前项目鼠标右键新建

![1564926539131](img/1564926539131.png)   

2. 编写person.xml文件

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!--描述一个人-->
   <person id="1">
       <name>张三</name>
       <age>18</age>
       <sex>男</sex>
   </person>
   ```

3. 通过浏览器解析XML的内容

   ![1552221534649](img/1552221534649.png)  

- 注：XML以后通过Java来进行解析，很少直接在浏览器上显示。

### 小结

1. 什么是XML？ 可扩展标签语言
2. 主要有哪两个作用？
   1. 数据交换
   2. 配置文件



##4.XML的组成：声明和元素

### 目标

1. XML中7种组成部分
2. XML文档中的声明
3. XML文档中的元素

### XML由七种组成元素构成：

1. 声明
2. 元素(标签)
3. 属性
4. 注释
5. 实体字符
6. CDATA 字符数据区
7. 处理指令

### 文档声明

#### 语法

![1552222163638](img/1552222163638.png)  



![1552352524671](img/1552352524671.png) 

#### 声明的三种属性

| **文档声明的三个属性** | **说明**                                                     |
| ---------------------- | ------------------------------------------------------------ |
| **version**            | 指定XML文件使用的版本，取值是1.0                             |
| **encoding**           | 当前XML文件使用的编码(字符集)                                |
| **standalone**         | 指定当前这个XML文件是否是一个独立的文件，省略的，默认是独立文件。 |

#### 版本说明

​	W3C在1988年2月发布1.0版本，2004年2月又发布1.1版本，因为1.1版本不能向下兼容1.0版本，所以1.1没有人用。在2004年2月W3C又发布了1.0版本的第三版。我们学习的还是1.0版本。

### 元素

![1552222307721](img/1552222307721.png)

![1552352828511](img/1552352828511.png)

![1552352880848](img/1552352880848.png)

![1552352959286](img/1552352959286.png)

### 代码

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<persons>
    <!--描述一个人-->
    <person id="1">
        <name>张三</name>
        <age>18</age>
        <sex>男</sex>
    </person>

    <person id="2">
        <name>李四</name>
        <age>19</age>
        <sex>女</sex>
    </person>
</persons>
```

### 小结

1. 声明有哪两个常用的属性？ version encoding
2. 一个XML有几个根元素？ 1个
3. XML标签命名不能有什么符号？  空格，冒号



##5.XML的组成：属性、注释和实体字符

### 目标

1. XML文档中属性
2. XML文档中的注释
3. XML文档中实体字符

### 属性的语法

![1552225389171](img/1552225389171.png)

![1552353173877](img/1552353173877.png)

### 注释

Ctrl+/:注释整行

Ctrl+Shift+/:局部注释

![1564927502577](assets/1564927502577.png) 

### 实体字符

因为很多符号已经被文档结构所使用，所以在元素体或属性值中想使用这些符号就必须使用实体字符

![1552353386585](img/1552353386585.png)

- 语法：

![1552225605462](img/1552225605462.png)

### 应用案例

#### 需求

我们即将学习数据库，以此为例。有一个\<sql>标签，中间包含sql语句，查询所有年龄小于20岁的学生。

#### 效果

![1552353485579](img/1552353485579.png)

#### 错误写法

直接写小于号 < 会报错，与标签的<冲突。

```
<sql>
    <!--使用实体字符-->
    select * from user where age < 20
</sql>
```

#### 正确写法

需要借助实体字符来完成

```xml
<sql>
    <!--使用实体字符-->
    select * from user where age &lt; 20
</sql>
```

### 小结

1. 属性必须出现在标签哪个位置，同一个标签是否可以有同名的属性？

2. 为什么要有实体字符？

    < > 这是语法上的特殊符号，主体内容中不能字节写。借助实体字符

## 6. XML的组成：字符数据区和处理指令

### 目标

1. 字符数据区CDATA (Character Data)
2. 处理指令

### 为什么要字符数据区

​	如果大量使用实体字符，会导致XML可读性降低。另一种解决方案：可以使用字符数据区包裹这些字符，只要在字符数据区中的内容，XML解析器会纯文本进行解析。

![1552225954936](img/1552225954936.png)

==CD：快速构建字符数据区==

### 处理指令

![1552226032459](img/1552226032459.png)

 了解即可，不做演示

#### person.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<persons>
    <!--描述一个人-->
    <person id="1" vip="true">
        <name>张三</name>
        <age>18</age>
        <sex>男</sex>
    </person>

    <person id="2">
        <name>李四</name>
        <age>19</age>
        <sex>女</sex>
    </person>

    <sql>
        <!--使用实体字符-->
        select * from user where age &lt; 20
    </sql>

    <sql>
        <![CDATA[
            select * from user where age < 20
        ]]>
    </sql>
</persons>
```

### 小结

XML哪7个组成部分？

1. 声明
2. 元素
3. 属性
4. 注释
5. 实体字符
6. CDATA字符数据区
7. 处理指令



一个格式良好的XML有以下特点

1. 必须以XML声明开头
2. 必须拥有唯一的根元素
3. 开始标签必须与结束标签相匹配
4. 元素对大小写敏感
5. 所有的元素必须关闭
6. 所有的元素必须正确地嵌套
7. 特殊字符必须使用实体字符或使用字符数据区



## 7. XML约束：DTD约束

### 目标

1. XML有哪两种约束
2. 了解DTD约束

### XML为什么要有约束

  	因为XML文件的标签和属性可以随意扩展，有时我们必须要限制每个文档有哪些元素，每个元素有哪些子元素，每个元素有哪些属性，属性的值是什么类型等。从而保证XML文档格式和数据的正确性，有效性。

### XML的两种约束

1. DTD约束
2. Schema约束

### DTD约束的概念

1. DTD: Document Type Definiation 文档类型定义
2. 作用：纯文本文件，指定了XML约束规则。

| **导入**DTD文件的两种格式                             | **说明**                               |
| ----------------------------------------------------- | -------------------------------------- |
| **<!DOCTYPE** **根元素 SYSTEM "DTD文件">**            | 系统DTD文件，通常个人或公司内部使用    |
| **<!DOCTYPE** **根元素 PUBLIC "文件描述" "DTD文件">** | 公有的DTD文件，在互联网上广泛使用的DTD |

如：hibernate框架的导入方式

![1552227668343](img/1552227668343.png)

### DTD使用案例

步骤1：新建bookshelf.dtd文件，选择项目鼠标右键“NEW->File",文件名为“bookshelf.dtd”

步骤2：bookshelf.dtd文件内容如下

```dtd
<!ELEMENT 书架 (书+)>
<!ELEMENT 书 (书名,作者,售价)>
<!ELEMENT 书名 (#PCDATA)>
<!ELEMENT 作者 (#PCDATA)>
<!ELEMENT 售价 (#PCDATA)>
```

 步骤3：新建books.xml，代码如下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE 书架 SYSTEM "bookshelf.dtd">
<书架>
    <书>
        <书名>JavaEE SSM</书名>
        <作者>NewBoy</作者>
        <售价>30</售价>
    </书>
    <书>
        <书名>人鬼情喂鸟</书名>
        <作者>李四</作者>
        <售价>300</售价>
    </书>
</书架>
```

步骤4：idea开发工具books.xml的dtd约束验证不通过的效果

![1552355976703](img/1552355976703.png)

### DTD学习要求

​	在企业实际开发中，很少自己编写DTD约束文档，我们后期只需通过框架提供的DTD约束文档编写出相应的XML配置文档。

### 小结

1. DTD是一个什么文件？ XML的约束文件
2. 它的作用是什么？ 约束XML文件格式是否正确



##8. XML约束：Schema约束

### 目标

了解Schema约束

### 为什么要有Schema约束

DTD的不足：

1. 不能验证数据类型
2. 因为DTD是一个文本文件，本身不能验证是否正确。

![1552302664354](img/1552302664354.png)

约束文件扩展名(XML Schema Definition)XML模式定义：xsd 

约束文件本身也是XML文件，所以也有根元素，根元素的名字叫：schema

### 模式文档和实例文档

模式文档：制定约束的XML文档(类似于：类)。

实例文档：被约束的XML文档(类似于：对象)。

![1552311128282](img/1552311128282.png)

### 命名空间

##### 问：如果在Java中如果使用同名的类，如何避免冲突？比如：Date类，在java.util和java.sql包中都有

```java
package com.itheima;

import java.sql.Date;

public class Demo1 {

    public static void main(String[] args) {
        //sql的类，Date日期
        Date d1 = new Date(0);
        System.out.println("d1 = " + d1);
        //utils中类，Date
        java.util.Date d2 = new java.util.Date(0);
        System.out.println("d2 = " + d2);
    }

}
打印结果:
d1 = 1970-01-01
d2 = Thu Jan 01 08:00:00 CST 1970
```

##### 如何不同的xsd约束文件出现同名的标签，如何避免冲突？

使用命名空间，在XML中使用URL地址来命名

![1552303625212](img/1552303625212.png)

1. 一个XML文件可以有多个约束文件
2. targetNamespace用来指定默认的命名空间
3. 默认命名空间，前面不需要指定前缀，直接使用<标签名>
4. 一个XML文档中只能有一个默认的命名空间
5. 其它命名空间中的标签，使用\<前缀:标签名>来引用

### Schema演示案例

1. 步骤1：新建schema约束文件bookshelf.xsd，对售价约束数据类型，代码如下：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://www.itcast.cn"
           elementFormDefault="qualified">
    <xs:element name='书架'>
        <xs:complexType>
            <xs:sequence maxOccurs='unbounded'>
                <xs:element name='书'>
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name='书名' type='xs:string'/>
                            <xs:element name='作者' type='xs:string'/>
                            <xs:element name='售价' type='xs:double'/>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
```

2. 步骤2：新建books2.xml使用schema约束文件

bookshelf.xsd

```xml
<根元素 xmlns="命名空间" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xsi:schemaLocation="命名空间 xsd约束文件名">
</根元素>
```

book2.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<书架 xmlns="http://www.itcast.cn" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://www.itcast.cn bookshelf.xsd">
    <书>
        <书名>JavaEE</书名>
        <作者>张三</作者>
        <售价>39</售价>
    </书>
    <书>
        <书名>西游记</书名>
        <作者>吴承恩</作者>
        <售价>3000</售价>
    </书>
</书架>
```

3. 步骤3：违反约束，开发工具提示效果

   ![1552357355473](img/1552357355473.png)

### Schema学习要求

​	虽然schema功能比dtd强大，但是编写要比DTD复杂，同样以后我们在企业开发中也很少会自己编写schema文件。我们只需要借助开发工具，在现有的xsd约束下，写出正确的xml文件即可。

### 小结

1. XML有哪两种约束？ 
   1. DTD
   2. Schema
2. Schema约束相比DTD有哪些优点？
   1. 本身是一个XML文件
   2. 验证数据类型
   3. 一个XML文件可以有多个xsd约束文件，要使用前缀来引用。



##9. XML的两种解析方式

### 目标

了解XML的解析方式有哪两种

### 什么是XML的解析

​	使用Java语言去解析XML文件，读取XML中的元素，属性，文本等数据。

### 解析方式和解析器

#### 两种解析方式

1. DOM  Document Object Model 文档对象模型

- 优点：将整个XML文件加载到内存中，生成一棵DOM树。随意访问树上任何一个节点，可以修改和删除节点，程序开发比较方便。
- 缺点：占内存，如果XML文件很大，可能出现内存溢出。

![1552305143011](img/1552305143011.png)

2. SAX

- 优点：事件驱动型解析方式，读取一行就解析一行，释放内存。理论上可以解析任意大小的XML文件。
- 缺点：使用过的元素不能再访问了，不能修改元素，只能查找。

### Java中DOM解析开发包

![1552305195234](img/1552305195234.png)

### 小结

有哪两种XML的解析方式：

1. DOM
2. SAX



##10. XML文档生成DOM树原理

### 目标

​	XML文件在内存中生成的DOM树是什么样子的

### 介绍

​	XML DOM 和 HTML DOM一样，XML DOM 将整个XML文档加载到内存，生成一个DOM树，并获得一个Document对象，通过Document对象就可以对DOM进行操作。以下面books.xml文档为例。

### XML文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<books>
    <book id="0001">
        <name>JavaWeb开发教程</name>
        <author>张孝祥</author>
        <sale>100.00元</sale>
    </book>
    <book id="0002">
        <name>三国演义</name>
        <author>罗贯中</author>
        <sale>100.00元</sale>
    </book>
</books>
```

### 生成的DOM树

![1552305350183](img/1552305350183.png)

​	由于DOM方式解析XML文档所有都是节点Node，所有节点又都被封装到Document对象中，所以解析的重点就是获取Document对象。

### DOM4j中DOM树的接口

| **组成**      | **说明**                            |
| ------------- | ----------------------------------- |
| **Document**  | 当前解析的XML文档对象               |
| **Node**      | XML中节点，它是其它所有对象的父接口 |
| **Element**   | 代表一个元素(标签)                  |
| **Attribute** | 代表一个属性                        |
| **Text**      | 代表标签中文本                      |

### 小结

DOM的解析原理：

将整个XML文件加载到内存中，生成一棵DOM树，下面是根节点。

通过Document对象来访问整个DOM树



##11. dom4j: 获取Document对象和根元素

### 目标

1. 如何得到Document对象
2. 如何通过Document得到根元素

### 导入dom4j的步骤

1. 去官网下载 zip 包。<http://www.dom4j.org/>【资料中已经下载好了，直接使用】
   ![1552305595501](img/1552305595501.png) 
2. 在项目中创建一个文件夹：lib

3. 将dom4j-2.1.1.jar文件复制到 lib 文件夹

4. 在jar文件上点右键，选择 Add as Library -> 点击OK

5. 在类中导包使用

### 得到Document对象

#### 步骤

文件Contact.xml放在工程跟目录下

1. 创建一个SAXReader对象，用于读取 xml 文件。
2. 从项目根路径加载xml文件，得到输入流对象
3. 通过 SAXReader对象的read(InputStream in )方法，从输入流中读取，生成文档对象。

#### 代码

```java
package com.itheima;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 1. 得到文档对象
 2. 得到根元素
 */
public class Demo3Document {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        //1. 创建类: 读取XML文件
        SAXReader reader = new SAXReader();
        InputStream in = new FileInputStream("Contacts.xml");
        //2.通过reader来读取xml, 生成了一个document对象
        Document document = reader.read(in);
        //3. 输出文档
        System.out.println(document);
        //4. 得到文档以后，通过文档得到根元素
        Element rootElement = document.getRootElement();
        System.out.println(rootElement);
    }

}
```

### Document常用方法

![1552305787000](img/1552305787000.png)

### 小结

1. 如何得到Document对象
   1. 创建SaxReader对象
   2. 从项目根路径下得到InputStream
   3. 通过SaxReader来读取输入流，==得到Document对象==
   4. 通过Document对象得到根元素
2. 调用哪个方法得到根元素：==getRootElement()==



##12. dom4j: 元素对象Element

### 目标

使用元素对象的方法

### 什么是Element

就是元素对象，即标签

### Element常用方法

![1552306199435](img/1552306199435.png)

### 元素使用的案例

#### 需求

元素方法的使用，得到contact下所有的子元素，并且输出元素名

#### 步骤

1. 得到SAXReader对象
2. 得到Document对象
3. 得到根元素
4. 得到contact元素
5. 得到contact下所有的子元素，并输出子元素的名字

#### 代码

```java
package com.itheima;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 元素方法的使用
 */
public class Demo3Element {

    public static void main(String[] args) throws DocumentException {
        //1. 得到SAXReader对象
        SAXReader reader = new SAXReader();
        InputStream in = new FileInputStream("Contacts.xml");
        //2. 得到Document对象
        Document document = reader.read(in);
        //3. 得到根元素
        Element rootElement = document.getRootElement();
        //4. 得到contact元素
        Element contact = rootElement.element("contact");
        //System.out.println(contact);
        //5. 得到contact下所有的子元素，并输出子元素的名字
        List<Element> elements = contact.elements();
        for (Element element : elements) {
            //只输出标签名
            System.out.println(element.getName());
        }
    }

}

```

### 小结

| **方法名**                                 | **功能说明**                           |
| ------------------------------------------ | -------------------------------------- |
| **String getName()**                       | 得到标签名字                           |
| **Element element(String   name)**         | 得到当前元素下指定名字的子元素         |
| **List\<Element>   elements()**            | 得到当前元素下所有子元素               |
| **List\<Element>   elements(String name)** | 得到当前元素下指定名字的子元素返回集合 |



## 13. dom4j: 属性对象Attribute

### 目标

使用属性对象的方法

### 什么是Attribute对象

代表一个标签的属性，如：id="1"

### Element中与属性相关方法

==得到属性的前提是：先得到元素==

![1552306341986](img/1552306341986.png)

### Attribute常用方法

![1552306372013](img/1552306372013.png)

### 得到属性值的两种方式

1. 先得到Attribute对象，再通过Attribute对象得到属性的值
2. 通过方法attributeValue()直接得到属性值



### 属性的案例

#### 需求

1. 使用2种方式，得到contact上id属性值
2. 在contact元素上添加一个vip的属性值为true/false，得到contact上所有的属性名和属性值

#### 效果

![1552306491790](img/1552306491790.png)

#### 步骤

1. 创建SAXReader
2. 调用read 方法，读取 xml 文件
3. 得到根元素
4. 获得第1个contact元素对象
5. 通过方式1：得到contact上id属性值
6. 通过方式2：得到contact上id属性值
7. 得到contact上所有的属性名和属性值

#### 代码

```java
package com.itheima;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Demo4Attribute {

    public static void main(String[] args) throws DocumentException {
        //1. 创建SAXReader
        SAXReader reader = new SAXReader();
        //2. 调用read 方法，读取 xml 文件
        Document document = reader.read( new FileInputStream("Contacts.xml"));
        //3. 得到根元素
        Element rootElement = document.getRootElement();
        //4. 获得第1个contact元素对象
        Element contact = rootElement.element("contact");
        //5. 通过方式1：得到contact上id属性值
        Attribute idAttr = contact.attribute("id");
        String idValue = idAttr.getValue();
        System.out.println(idValue);
        //6. 通过方式2：得到contact上id属性值
        String id = contact.attributeValue("id");
        System.out.println(id);
        //7. 得到contact上所有的属性名和属性值
        List<Attribute> attributes = contact.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性名：" + attribute.getName());
            System.out.println("属性值：" + attribute.getValue());
        }
    }
}
```

### 小结

要获取属性，先要获取元素

| **Element中的方法**                      | **功能说明**               |
| ---------------------------------------- | -------------------------- |
| **Attribute attribute(String   name)**   | 通过属性名得到属性对象     |
| **String   attributeValue(String name)** | 通过属性名直接得到属性值   |
| **List\<Attribute>   attributes()**      | 得到当前元素所有的属性对象 |



##14. dom4j: 获取文本内容

### 目标

通过元素对象得到文本字符串

### 文本内容

==得到文本元素的前提：先得到元素对象==

- **注：空格、换行、制表符：也是属于文本的一部分，所以在解析xml文件的时候，格式化XML文件要注意。** 

### 元素中得到文本有关的方法

![1552306706868](img/1552306706868.png)

### 案例：对文本的操作

#### 需求

得到第1个contact中的name元素，输出name元素的文本。分别通过下面三个方法得到：

1. 通过getText()方法得到
2. 直接得到元素的内容
3. 得到去掉空格的文本

#### 步骤

1. 得到Document对象
2. 得到根元素
3. 得到contact标签
4. 得到name标签中的文本
5. 得到name标签
6. 再得到name中的文本

#### 代码

```java
package com.itheima;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 得到文本内容
 */
public class Demo5Text {

    public static void main(String[] args) throws DocumentException {
        //1. 创建SAXReader
        SAXReader reader = new SAXReader();
        //2. 调用read 方法，读取 xml 文件
        Document document = reader.read( new FileInputStream("Contacts.xml"));
        //3. 得到根元素
        Element rootElement = document.getRootElement();
        //得到contact标签
        Element contact = rootElement.element("contact");
        //得到name标签中的文本
        Element name = contact.element("name");
        System.out.println(name.getText());
        //得到子元素的文本
        System.out.println(contact.elementText("name"));
        //得到子元素的文本并且去掉前后空格
        System.out.println(contact.elementTextTrim("name"));
    }

}
```

### 小结

| **Element**元素中的方法            | **说明**                       |
| ---------------------------------- | ------------------------------ |
| **String getText()**               | 得到元素中文本                 |
| **String elementText(元素名)**     | 得到子元素中文本               |
| **String elementTextTrim(元素名)** | 得到子元素中文本，去掉先后空格 |



##15. 案例：XML解析案例

### 目标

​	利用 Dom4j 的知识，将Contact.xml 文件中的联系人数据封装成List集合，其中每个元素是实体类Contact。打印输出 List 中的每个元素。

### 运行效果

![1552307147703](img/1552307147703.png)

### 数据准备

#### Contact.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<contactList>
    <contact id="1">
        <name>潘金莲</name>
        <gender>女</gender>
        <email>panpan@itcast.cn</email>
    </contact>
    <contact id="2">
        <name>武松</name>
        <gender>男</gender>
        <email>wusong@itcast.cn</email>
    </contact>
    <contact id="3">
        <name>武大狼</name>
        <gender>男</gender>
        <email>wuda@itcast.cn</email>
    </contact>
</contactList>
```

#### Contact.java实体类

```java
package com.itheima.entity;

/**
 * 联系人实体类
 */
public class Contact {
    private int id;
    private String name;
    private String gender;
    private String email;

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //getter/setter
}
```

### 步骤

1. 创建集合对象用于封装所有的联系人
2. 获得文档对象
3. 获取根元素
4. 获得所有的contact元素
5. 遍历contact元素集合
6. 每个contact创建一个联系人对象
7. 获得id属性值，封装到id属性中
8. 得到contact下的子元素的文本封装其它的属性
9. 将联系人对象添加到集合中
10. 遍历输出每一个联系人对象

### 代码

```java
package com.itheima;

import com.itheima.entity.Contact;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class Demo6Contact {

    public static void main(String[] args) throws DocumentException {
        //1. 得到SaxReader
        SAXReader reader = new SAXReader();
        //2. 得到文档对象
         Document document = reader.read( new FileInputStream("Contacts.xml"));
        //3. 得到根元素
        Element rootElement = document.getRootElement();
        //4. 创建集合
        List<Contact> list = new ArrayList<>();
        //5. 得到所有联系人
        List<Element> contactElements = rootElement.elements();
        //每个就是一个联系人
        for (Element contactElement : contactElements) {
            //创建一个联系人
            Contact contact = new Contact();
            //System.out.println(contactElement.getName());  //打印标签名
            //得到id属性值，将字符串转成int类型
            int id = Integer.parseInt(contactElement.attributeValue("id"));
            contact.setId(id);
            //得到子元素中文本
            contact.setName(contactElement.elementText("name"));
            contact.setGender(contactElement.elementText("gender"));
            contact.setEmail(contactElement.elementText("email"));
            //将当前对象添加到集合中
            list.add(contact);
        }
        //打印集合中所有的联系人
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }

}
```



### 小结

| 案例中使用到的方法        | 作用                       |
| ------------------------- | -------------------------- |
| List\<Element> elements() | 得到当前元素所有子元素     |
| String attributeValue()   | 得到当前元素指定的属性值   |
| String elementTextTrim()  | 得到子元素的文本，去掉空格 |





# 学习总结

1. 能够说出 XML 的作用

   1. 数据交换
   2. 配置文件

2. 了解 XML 的组成元素

   1. 声明
   2. 元素
   3. 属性
   4. 注释
   5. 实体字符
   6. CDATA字符数据区
   7. 处理指令

3. 能够说出有哪些 XML约束技术

   1. DTD
   2. Schema
      1. 支持数据类型
      2. 本身xsd也是一个XML文件
      3. 一个XML可以有多个xsd约束文件

4. 能够说出解析 XML 文档 DOM方式原理

   ![1552305350183](img/1552305350183.png)

5. 能够说出Dom4j常用的类

   | **组成**      | **说明** |
   | ------------- | -------- |
   | **Document**  | 文档对象 |
   | **Node**      | 节点     |
   | **Element**   | 元素     |
   | **Attribute** | 属性     |
   | **Text**      | 文本     |

6. 能够通过Dom4j得到文档对象

   ```java
   SaxReader
   得到输入流
   通过SaxReader读取输入流
   ```

7. 能够读取Dom树上的元素对象

   | **Document方法名**             | **功能说明** |
   | ------------------------------ | ------------ |
   | **Element   getRootElement()** | 得到根元素   |

   | Element方法名                              | **功能说明**             |
   | ------------------------------------------ | ------------------------ |
   | **String getName()**                       | 得到元素的标签名         |
   | **Element element(String   name)**         | 通过元素名字得到元素对象 |
   | **List\<Element>   elements()**            | 得到所有的子元素         |
   | **List\<Element>   elements(String name)** | 得到指定名字的所有子元素 |

   

