**1、框架：**

     Springboot2.1.4.RELEASE + SpringCloud Greenwich.RELEASE + mybatis-plus3.0.6

**2、项目目录结构：**

     -user

     |-user-api

          |-src

             |-main

                  |-java

                      |-com.zy.api     -------->用于创建暴露给其它系统调用api接口主目录

                          |-user     ---------->api分支目录

     |-user-common

         |-src

              |-main

                   |-java

                        |-com.zy.common    ---------->common主目录

                             |-input    ------>入参DTO目录

                             |-output    ------>出参DTO目录

     |-user-server

         |-src

              |-main

                   |-java

                       |-com.zy    ---------->server主目录

                            |-common   -------->共用主目录

                                |-config   -------->定义初始化配置目录

                                |-constants   -------->定义枚举目录

                                |-exception   ------>定义全局异常目录

                                |-utils   -------->定义工具目录

                            |-modules   -------->业务模块主目录

                                 |-user   -------->具体业务模块目录

                                      |-controller

                                           |-manager   -------->直接网关路由过来的请求目录

                                           |-server    -------->api的具体实现，即为feign的服务接口目录

                                      |-dao         -------->dao

                                      |-entity         -------->对应数据库表

                                      |-service          -------->业务对象，事物全都放在这里进行

                                      |-vo     -------->面向前端对象





