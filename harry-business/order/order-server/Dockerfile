FROM java:8

MAINTAINER harry.zhang

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /order-server

WORKDIR /order-server

EXPOSE 8001

ADD ./target/order-server.jar ./

CMD sleep 30;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar order-server.jar