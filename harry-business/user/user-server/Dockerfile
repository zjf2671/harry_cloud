FROM java:8

MAINTAINER harry.zhang

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom" envir="dev"

RUN mkdir -p /user-server

WORKDIR /user-server

EXPOSE 8800

COPY target/user-server.jar ./

#CMD sleep 30;java $JAVA_OPTS -jar user-server.jar
ENTRYPOINT sleep 30;java $JAVA_OPTS -jar user-server.jar --spring.profiles.active=$envir