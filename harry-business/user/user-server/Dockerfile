FROM java:8

MAINTAINER harry.zhang

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN mkdir -p /user-server

WORKDIR /eureka

EXPOSE 8800

COPY target/eureka.jar ./

CMD sleep 30;java $JAVA_OPTS -jar user-server.jar