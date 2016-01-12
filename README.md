spring-boot-start-togglz
===================================
Spring boot starter Togglz。

### 如何使用

* 在Spring Boot项目的pom.xml中添加以下依赖:
```xml
          <dependency>
                     <groupId>com.mvnsearch.spring.boot</groupId>
                     <artifactId>spring-boot-starter-togglz</artifactId>
                     <version>1.0.0-SNAPSHOT</version>
          </dependency>
```
* 接下来就是创建对应的Feature Enum,代码如下: 

```java
      package org.mvnsearch.featuretoggle;
      
      import org.togglz.core.Feature;
      import org.togglz.core.annotation.Label;
      import org.togglz.core.context.FeatureContext;
      
      public enum MyFeatures implements Feature {
      
          @Label("First Feature")
          FEATURE_ONE,
      
          @Label("Second Feature")
          FEATURE_TWO;
      
          public boolean isActive() {
              return FeatureContext.getFeatureManager().isActive(this);
          }
      
      }

```
* 在Spring Boot的application.properties文件中添加togglz对应的配置，如下:
```properties                    
          spring.togglz.feature-enum=org.mvnsearch.featuretoggle.MyFeatures
          spring.togglz.features.FEATURE_ONE.enabled=true
```
* 在应用中调用Feature进行判断: 

```groovy
      MyFeatures.FEATURE_ONE.isActive()
```

### spring-boot-start-togglz 提供的服务

* org.togglz.core.manager.FeatureManager: feature manager

### mybatis endpoint
提供togglz运行期的参数,主要是开关列表

### 参考文档

* Togglz: http://www.togglz.org/
