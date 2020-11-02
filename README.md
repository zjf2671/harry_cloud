# cloud-server
    基础服务支撑
# harry-common
    共用模块
# harry-busniess
    业务服务模块
    
# maven 集成docker构建方式

    一条命令实现打包及推送镜像
    mvn clean package -Ptest -Ddocker.namespace=harrytest -Dimages.version=1.0.0-SNAPSHOT docker:build docker:push
    
    说明：
        -Ptest 代表环境
        -Ddocker.namespace 代表私库命名空间
        -Dimages.version 镜像版本号
        docker:build 构建镜像
        docker:push 推送镜像到私库
