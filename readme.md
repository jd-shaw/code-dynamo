# CodeDynamo 学习项目

## 项目
项目计划采用Springboot、sa-token、jpa等框架搭建初始项目，项目参考了诸多开源项目，所以前期代码略显凌乱，在满足能用的前提下，逐步优化代码的代码的风格与功能点。

## 规划
本项目是一个自我学习的项目，所以会时不时的修改，不存在稳定版。只是想有一个项目可以随时在上面做一些技术验证，或者是学习。后续慢慢加入一些Spring cloud等功能。

## 项目结构
```lua
code-dynamo
    ├── dynamo-app 项目启动
    ├── dynamo-commons
    │   ├── dynamo-commons-cache -项目缓存
    │   ├── dynamo-commons-exception-handler -项目异常处理
    │   ├── dynamo-commons-jackson -jackson工具
    │   ├── dynamo-commons-redis -redis
    │   ├── dynamo-commons-spring -spring二次开发的工具、Aop
    │   ├── dynamo-commons-swagger -Swagger文档
    │   ├── dynamo-commons-utils -工具类
    │   ├── pom.xml
    │   └── readme.md
    ├── dynamo-datasource
    │   ├── dynamo-datasource-mongodb -MongoDB
    │   ├── dynamo-datasource-mysql -MYSQL
    │   ├── pom.xml
    │   └── readme.md
    ├── dynamo-kernel
    │   ├── dynamo-kernel-auth -授权
    │   ├── dynamo-kernel-commons -核心工具
    │   ├── pom.xml
    │   └── readme.md
    ├── dynamo-services
    │   ├── dynamo-services-api -Api接口
    │   ├── dynamo-services-dataperm -数据加密
    │   ├── dynamo-services-file -文件
    │   ├── dynamo-services-iam -身份识别与访问管理
    │   ├── dynamo-services-message -系统消息
    │   ├── dynamo-services-quartz -定时任务
    │   ├── dynamo-services-sys -系统核心功能
    │   ├── pom.xml
    │   └── readme.md
    ├── pom.xml
    └── readme.md
```

## 问题
1. swagger打开出现 `Knife4j文档请求异常`、`Failed to load API definition.`
看看后台出现如下错误，原因是：我的jackson 版本不一致。。。
不知道哪个引用到了2.13.5版本，但是我的jackson-core包的版本是2.15.0的
```java
java.lang.ClassNotFoundException: com.fasterxml.jackson.core.StreamReadConstraints
	at java.net.URLClassLoader.findClass(URLClassLoader.java:387)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:355)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
	at com.fasterxml.jackson.databind.util.TokenBuffer.<init>(TokenBuffer.java:64)
	at com.fasterxml.jackson.databind.SerializerProvider.bufferForValueConversion(SerializerProvider.java:491)
	at com.fasterxml.jackson.databind.ObjectMapper._convert(ObjectMapper.java:4516)
	at com.fasterxml.jackson.databind.ObjectMapper.convertValue(ObjectMapper.java:4475)
	at io.swagger.v3.core.util.ModelDeserializer.deserialize(ModelDeserializer.java:85)
	at io.swagger.v3.core.util.ModelDeserializer.deserialize(ModelDeserializer.java:33)
	at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4825)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3772)
```

2. 出现 `Not a managed type: class com.shaw.file.entity.UpdateFileInfo` 错误
需要在启动项加上注解
```java
@EntityScan
@EnableJpaRepositories
```
同时在entity上增加注解
```java
@Entity
```
