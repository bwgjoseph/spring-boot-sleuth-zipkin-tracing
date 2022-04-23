# Spring Boot Sleuth Zipkin Tracing

This project showcase how to do distributed tracing using [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth) together with [Zipkin](https://zipkin.io/)

## Initialize project

[via start.spring.io](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.6.7&packaging=jar&jvmVersion=17&groupId=com.bwgjoseph&artifactId=spring-boot-sleuth-zipkin-tracing&name=spring-boot-sleuth-zipkin-tracing&description=Spring%20Boot%20Sleuth%20Zipkin%20Tracing&packageName=com.bwgjoseph.spring-boot-sleuth-zipkin-tracing&dependencies=devtools,lombok,configuration-processor,web,cloud-starter-sleuth,actuator,cloud-starter-zipkin)

## Setup Zipkin

See `/docker/docker-compose.yml`

- Navigate to `/docker`
- Run `docker-compose up -d`
- Go to `http://localhost:9411`

## Start Application

When application is first started, you will notice something extra on the log itself

```log
2022-04-23 15:29:24.301  INFO [,,] 24612 --- [  restartedMain] SpringBootSleuthZipkinTracingApplication : Started SpringBootSleuthZipkinTracingApplication in 6.692 seconds (JVM running for 113.005)
```

Noticed the `[,,]` after `INFO`? That is added by `spring-cloud-sleuth` to display `[application name,trace id, span id]`. In order for `application name` to be shown, we need to add `spring.application.name` in `application.properties`

```properties
spring.application.name=sbszipkintracing
```

And when you restart the application, the log will be display as

```log
2022-04-23 15:30:52.204  INFO [sbszipkintracing,,] 24612 --- [  restartedMain] SpringBootSleuthZipkinTracingApplication : Started SpringBootSleuthZipkinTracingApplication in 5.236 seconds (JVM running for 200.909)
```

But because we haven't triggered any request yet, hence, there will not be any `trace/span id`

## Add Controller

In order to see the `trace/span id` in action, let's create [PostController](/src/main/java/com/bwgjoseph/springbootsleuthzipkintracing/PostController.java) to allow for `REST API` call. Once created, we can trigger a `GET` call via

```sh
curl -k localhost:8080/post
```

And the log will now display as

```log
2022-04-23 15:37:55.043  INFO [sbszipkintracing,4695b333247539ae,4695b333247539ae] 24612 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet
   : Completed initialization in 1 ms
```

Where we will now see `[application name, trace id, span id]`

## View in Zipkin

Let's see how it looks like in the [UI](http://localhost:9411/zipkin/)

![see](./resource/zipkin-trace-1.gif)

Isn't it awesome? It only takes us a few setup and configuration and everything is nicely integrated

## Tracing with RestTemplate

In order to ensure it work with [RestTemplate](https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/html/howto.html#how-to-make-components-work), we have to ensure that we do not create a new instance of `RestTemplate`, otherwise, it won't be able to [inject the interceptor](https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/html/integrations.html#sleuth-http-client-rest-template-integration) to `RestTemplate`

In this example, we will make a external API call to [pokeapi](https://pokeapi.co/) using [PokemonAPI](/src/main/java/com/bwgjoseph/springbootsleuthzipkintracing/external/PokemonAPI.java) class which will fetch information regarding a given pokemon in [PostController](/src/main/java/com/bwgjoseph/springbootsleuthzipkintracing/post/PostController.java)

Notice that we now have 3 span which include the 2 API call we made. If we look closely, the child span include the parent span information like such

```log
// RestController GET POST
Span ID: aa7e691be20e5898
Parent ID: none

// RestTemplate GET ditto
Span ID: c68f6cabbe9a2aa6
Parent ID: aa7e691be20e5898

// RestTemplate GET clefairy
Span ID: 961995d442547873
Parent ID: aa7e691be20e5898
```

![see](./resource/zipkin-trace-2.gif)