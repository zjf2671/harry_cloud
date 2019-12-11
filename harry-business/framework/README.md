**1、说明：**

     本项目为模板项目，后续创建微服务相关项目以本项目为模板进行创建 -----此为独立项目
     
**2、框架：**

     Springboot2.1.4.RELEASE + SpringCloud Greenwich.RELEASE + mybatis-plus3.0.6
     支持sqlserver  oracle  mysql等数据库只需要改动少量的配置（以下用例为mysql）
**3、项目目录结构：**

       |-framework
     
          |-framework-api
     
               |-src
     
                  |-main
     
                       |-java
     
                           |-com.zjf.framework.api     -------->用于创建暴露给其它系统调用api接口主目录
     
                               |-gen     ---------->api分支目录
     
          |-framework-common
     
              |-src
     
                   |-main
     
                        |-java
     
                             |-com.zjf.framework.common    ---------->common主目录
          
                                  |-gen   ------>业务目录
                                  
                                      |-input    ------>入参DTO目录
         
                                      |-output    ------>出参DTO目录
     
          |-framework-server
     
              |-src
     
                   |-main
     
                        |-java
     
                            |-com.zjf    ---------->server主目录
                                 
                                 |-client  ---------> 此处由提供接口的人提供api jar 包，然后继承具体接口类,用于本项目调用
                                 
                                 |-common   -------->共用主目录
     
                                     |-config   -------->定义初始化配置目录
     
                                     |-constants   -------->定义枚举目录
     
                                     |-exception   ------>定义全局异常目录
     
                                     |-utils   -------->定义工具目录
     
                                 |-modules   -------->业务模块主目录
     
                                      |-gen   -------->具体业务模块目录
     
                                           |-controller
     
                                                |-manager   -------->直接网关路由过来的请求目录
     
                                                |-server    -------->api的具体实现，即为feign的服务接口目录
     
                                           |-dao         -------->dao
     
                                           |-entity         -------->对应数据库表
     
                                           |-service          -------->业务对象，事物全都放在这里进行
     
                                           |-vo     -------->面向前端对象

**4、准备工作：**

    1)在hosts文件最底下配置如下映射(windows目录地址 C:\Windows\System32\drivers\etc )
    
        192.168.0.195       zjf-mysql-dev
        192.168.0.16        zjf-redis-dev
        192.168.0.183       zjf-config-dev
        192.168.0.183       zjf-eureka-dev
        192.168.0.183       zjf-zipkin-dev
    
    2) 使用本地项目隔离方案只须启动你要开发的服务，其它的服务都走开发环境的地址(本地不需要再单独启动eureka服务了)
        如下：
            在bootstrap.yml中打开eureka.instance.metadata-map.version: harry ===》(harry随便自定义,需要保证你本地的和别人本地的值不一样就行)
            
            然后前端想调试你的本地接口，直接访问开发环境网关地址，并且只需要在调试的接口header中定义参数为：zjf-version="上面你定义的harry"
        
        注：以上方案目前只支持在开发环境中使用
        
**5、一键生成目录结构：**
    
    1）配置-Dmodule.dir=framework-server（module项目目录）到-VM Options中
    
    2）执行/test/java/com/zjf/NewCodeGenerator.java 中main函数，按console提示输入
    
    3）执行完成后会直接生成dao,serive,controller,mapper,vo到对应的目录中，完成基本的增删改查
    
    4）启动FrameworkServerApplication应用
    
    5）访问http://localhost:8888/doc.html直接在swagger中可以进行调试


