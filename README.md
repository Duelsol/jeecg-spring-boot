# jeecg-spring-boot

> 将 JEECG 微云快速开发平台改造为 Spring Boot 项目。

## 改造步骤

1. 在`pom.xml`加上对 Spring Boot 的继承：
   ```xml
   <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.0.4.RELEASE</version>
       <relativePath/>
   </parent>
   ```
2. 在`dependencies`里加上：
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
       <exclusions>
           <exclusion>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-tomcat</artifactId>
           </exclusion>
           <exclusion>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-logging</artifactId>
           </exclusion>
       </exclusions>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-test</artifactId>
       <scope>test</scope>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-log4j2</artifactId>
   </dependency>
   <dependency>
       <groupId>org.apache.tomcat.embed</groupId>
       <artifactId>tomcat-embed-jasper</artifactId>
       <scope>provided</scope>
   </dependency>
   ```
3. 删除原来的`spring`系列依赖和`hibernate`相关依赖，这也意味着要将项目里的`hibernate`引用由`hibernate4`升级到`hibernate5`。  
4. `plugins`里的`maven-compiler-plugin`的`jdk`要改为`1.8`，由于我是用外部tomcat启动的，原先的tomcat插件直接删除即可，并且要用tomcat8。
5. 在`resources`里加上`webapp`目录，但是排除掉所有文件，否则这些文件会重复打包到`classpath`里：
   ```xml
   <resource>
       <directory>${pom.basedir}/src/main/webapp</directory>
       <excludes>
           <exclude>**/**</exclude>
       </excludes>
   </resource>
   ```
6. 此时可以尝试更新`maven`并编译项目，解决一些依赖版本改变引起的报错。
7. 在`src/main/java/org/jeecgframework`下新建`JeecgApplication.java` `ServletInitializer.java` `config/WebConfiguration.java`，在`src/main/resources`下新建`application.yml`，删除`web.xml`。
8. 在`JeecgApplication.java`中将项目原来的spring配置文件导入进来，并且要在`@SpringBootApplication`里排除掉一些jar包的自动化配置。
9. `WebConfiguration.java`和`application.yml`则是用来实现原本`web.xml`里的`servlet` `filter` `listener`等配置。
10. 编译没问题后就可以使用tomcat启动项目了，我在启动后报了找不到资源文件的错误，所以在`spring-mvc.xml`里加上了`mvc:resources`来映射资源文件。然后在调用接口时，发现每个`jQuery.ajax`都返回了带双引号的数据导致浏览器报错，又将原来`spring-mvc.xml`里的`RequestMappingHandlerAdapter`配置替换为了：
    ```xml
    <mvc:annotation-driven>
        <mvc:message-converters>
            <ref bean="stringHttpMessageConverter" /><!-- 先进行string转换 -->
            <ref bean="mappingJacksonHttpMessageConverter" /><!-- json转换器 -->
        </mvc:message-converters>
    </mvc:annotation-driven>
    ```
