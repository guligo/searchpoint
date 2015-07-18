# Searchpoint

![alt tag](https://raw.githubusercontent.com/guligo/docker-images/master/searchpoint/searchpoint.png)

## About

Searchpoint is web-app for providing price comparison service to its end-users. If you know what for instance [PriceRunner](http://www.pricerunner.com) is then you most definitely know what does term "price comparison service" mean. At the moment when project got started market was full of similar services, nevertheless this project was an attempt to introduce better one.

## Key technologies

Java, JSP, Spring, Tomcat, MySQL, HTML + JS + CSS, Maven

## Deployment instructions

Introduce yourself to [Docker](https://www.docker.com) platform if you're not familiar with it already and use commands below to start up container:

```
docker pull guligo/searchpoint:latest
docker run -t -p 8080:8080 guligo/searchpoint:latest sh start.sh
```

Check out following URL once commands above have been executed:

```
http://<your host>:8080/searchpoint
```

Use test[at]test.test / test as credentials.

In case you're interested in building and deploying the project outside of Docker image, then follow [Dockerfile](https://github.com/guligo/docker-images/blob/master/searchpoint/Dockerfile) and make conclusions on how to do it yourself.
