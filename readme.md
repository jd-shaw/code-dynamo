# CodeDynamo 学习项目

## 项目规划
项目计划采用Springboot、sa-token、jpa hibernate等框架搭建初始项目

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
