FROM java:8

MAINTAINER harry.zhang

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom" envir="dev"

RUN mkdir -p /product-server

WORKDIR /product-server

EXPOSE 8800

COPY target/product-server.jar ./

#CMD sleep 30;java $JAVA_OPTS -jar product-server.jar --spring.profiles.active=$envir
ENTRYPOINT sleep 30;java $JAVA_OPTS -jar product-server.jar --spring.profiles.active=$envir