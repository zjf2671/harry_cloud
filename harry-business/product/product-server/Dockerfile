FROM java:8

MAINTAINER harry.zhang

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /product-server

WORKDIR /product-server

EXPOSE 8001

ADD ./target/product-server.jar ./

CMD sleep 30;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar product-server.jar