FROM java:8

MAINTAINER harry.zhang

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom" envir="dev"

RUN mkdir -p /order-server

WORKDIR /order-server

EXPOSE 8800

COPY target/order-server.jar ./

#CMD sleep 30;java $JAVA_OPTS -jar order-server.jar --spring.profiles.active=$envir
ENTRYPOINT sleep 30;java $JAVA_OPTS -jar order-server.jar --spring.profiles.active=$envir