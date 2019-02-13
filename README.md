# jeecg-spring-boot

> �� JEECG ΢�ƿ��ٿ���ƽ̨����Ϊ Spring Boot ��Ŀ��

## ���첽��

1. ��`pom.xml`���϶� Spring Boot �ļ̳У�
   ```xml
   <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.0.4.RELEASE</version>
       <relativePath/>
   </parent>
   ```
2. ��`dependencies`����ϣ�
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
3. ɾ��ԭ����`spring`ϵ��������`hibernate`�����������Ҳ��ζ��Ҫ����Ŀ���`hibernate`������`hibernate4`������`hibernate5`��  
4. `plugins`���`maven-compiler-plugin`��`jdk`Ҫ��Ϊ`1.8`�������������ⲿtomcat�����ģ�ԭ�ȵ�tomcat���ֱ��ɾ�����ɣ�����Ҫ��tomcat8��
5. ��`resources`�����`webapp`Ŀ¼�������ų��������ļ���������Щ�ļ����ظ������`classpath`�
   ```xml
   <resource>
       <directory>${pom.basedir}/src/main/webapp</directory>
       <excludes>
           <exclude>**/**</exclude>
       </excludes>
   </resource>
   ```
6. ��ʱ���Գ��Ը���`maven`��������Ŀ�����һЩ�����汾�ı�����ı���
7. ��`src/main/java/org/jeecgframework`���½�`JeecgApplication.java` `ServletInitializer.java` `config/WebConfiguration.java`����`src/main/resources`���½�`application.yml`��ɾ��`web.xml`��
8. ��`JeecgApplication.java`�н���Ŀԭ����spring�����ļ��������������Ҫ��`@SpringBootApplication`���ų���һЩjar�����Զ������á�
9. `WebConfiguration.java`��`application.yml`��������ʵ��ԭ��`web.xml`���`servlet` `filter` `listener`�����á�
10. ����û�����Ϳ���ʹ��tomcat������Ŀ�ˣ��������������Ҳ�����Դ�ļ��Ĵ���������`spring-mvc.xml`�������`mvc:resources`��ӳ����Դ�ļ���Ȼ���ڵ��ýӿ�ʱ������ÿ��`jQuery.ajax`�������˴�˫���ŵ����ݵ�������������ֽ�ԭ��`spring-mvc.xml`���`RequestMappingHandlerAdapter`�����滻Ϊ�ˣ�
    ```xml
    <mvc:annotation-driven>
        <mvc:message-converters>
            <ref bean="stringHttpMessageConverter" /><!-- �Ƚ���stringת�� -->
            <ref bean="mappingJacksonHttpMessageConverter" /><!-- jsonת���� -->
        </mvc:message-converters>
    </mvc:annotation-driven>
    ```
