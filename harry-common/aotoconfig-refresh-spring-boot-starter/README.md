![](<doc/image/config-image.jpg>)

# 使用说明

```
目前拉取有两种方式，根据系统的情况进行选择，手动拉取和定时自动拉取
 a、手动拉去方式：http://serverA:8801/actuator/refresh 

 b、自动拉取方式：在相应的服务引入jar, 在yml中开启spring.cloud.config.refreshInterval :  300 #去掉此参数不会定时拉配置服务的数据 单位秒

 c、在不重启动业务应用服务器的情况下以上两种方式都需要在对应的需要刷新的配置上加入@RefreshScope注解才能及时生效

<!--用于自动拉取配置服务的jar包-->
<dependency>
   <groupId>com.zjf.common.aotoconfig</groupId>
   <artifactId>aotoconfig-refresh-spring-boot-starter</artifactId>
   <version>1.0.0-SNAPSHOT</version>
</dependency>
```

