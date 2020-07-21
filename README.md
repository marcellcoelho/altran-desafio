# Altran Desafio

Projeto desafio da empresa Altran.

## Requisitos

Para criar e executar o aplicativo, você precisa:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Executando o aplicativo localmente

Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe `br.com.altran.desafio.DesafioApplication` na sua IDE.


Como alternativa, você pode usar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) da seguinte forma:

```shell
mvn spring-boot:run
```

## Acesso ao Swagger

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## Acesso ao console do H2

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

```shell
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:~/desafio
User Name: admin
Password: admin
```