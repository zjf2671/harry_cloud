###**幂等插件**

**逻辑描述**

    1、前端带上参数 globalIdempotentId="全局唯一id" （可以带在header上，也可以带在url后面）
    后台会以此id标识唯一，多次请求只会一次
    
    2、前端不带参数
    后台会生成一个唯一id，支持除了GET, HEAD, PATCH, DELETE, OPTIONS, TRACE的请求
    会通过后台算法得出一个唯一id,多次请求只会一次
    
    3、以上两种情况都适用于短时间幂等的限制，及短时间并发，可以去设置幂等有效时间，默认10秒
    以上通用方案能解决大部分幂等问题，如果是一些特殊的幂等要求另外在自己业务块进行处理，用来加强幂等
    
    4、幂等统一异常类IdempotentException

**使用说明**

>引入依赖

    <dependency>
        <groupId>com.zjf.common.idempotent</groupId>
        <artifactId>idempotent-spring-boot-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>

>注意:目前只支持引过common-core的系统

1、注解方式代码片段

```java
@Service
@Slf4j
public class GenTest1ServiceImpl {
   /**
    * 其中 @Idempotent 中的参数可以不用填写
    * 每个参数的意义请参考源代码，有详细的注释
    * @return
    */
    @Idempotent(spelKey = "#key", lockExpireTime = 300, storageExpireTime = 300, idempotentHandler = "idempotentHandler")
    @Override
    public boolean testAop(String key) {
         log.info("=======================正常进入被切方法");
         return true;
    }
    
    public boolean idempotentHandler(String key, IdempotentException idempotentException){
         log.info("idempotentHandler: 触发幂等限制时调用同类中的方法进行后续处理执行了");
         return true;
	}
}
```
2、编程方式代码片段

 ```java
@Service
public class GenTest1ServiceImpl {
    @Autowired
    private DistributedIdempotent distributedIdempotent;
    /**
     * 幂等代码测试有返回值
     * @return
     */
    @Override
    public void testCode(String key) {
         String result = distributedIdempotent.execute(key, 300, 300, () -> {
               log.info("进来了。。。。" + Thread.currentThread().getName());
               return "success";
          }, () -> {
               log.info("重复了。。。。" + Thread.currentThread().getName());
               throw new IdempotentException("重复了");
          });
    
    }

     /**
      * 幂等代码测试没有返回值
      * @return
      */
     @Override
     public void testCode2(String key) {
          distributedIdempotent.execute(key,300,300, ()->{
               log.info("进来了。。。。"+Thread.currentThread().getName());
          },()->{
               log.info("重复了。。。。"+Thread.currentThread().getName());
               throw new IdempotentException("重复了");
          });
     
     }
}    
 ```