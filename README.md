spring-boot-start-togglz
===================================
Spring boot starter Togglz。

### 如何使用

* 在Spring Boot项目的pom.xml中添加以下依赖:
```xml
          <dependency>
                     <groupId>com.mvnsearch.spring.boot</groupId>
                     <artifactId>spring-boot-starter-mybatis</artifactId>
                     <version>1.0.0-SNAPSHOT</version>
          </dependency>
```
* 在Spring Boot的application.properties文件中添加togglz对应的配置，如下:
```properties                    
          spring.togglz.feature-enum=org.mvnsearch.featuretoggle.MyFeatures
          spring.togglz.features.FEATURE_ONE=true
```

### spring-boot-start-mybatis提供的服务

* org.togglz.core.manager.FeatureManager: feature manager

### mybatis endpoint
提供togglz运行期的参数,主要是开关列表

### 参考文档

* Togglz: http://www.togglz.org/
