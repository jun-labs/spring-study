## API Gateway

API Gateway로 요청이 오면 이를 뒷단 서버로 전달해 줘야 하는데요, 이에 대해 살펴보겠습니다. 방법은 크게 `yml파일 설정`과 `코드 설정` 두 가지가 있습니다. 

<br/><br/><br/><br/>

## yml 파일 설정

우선 yml로 설정하는 방법에 대해 살펴보겠습니다. 아래와 같이 `routes`에 뒷단 서버의 정보를 입력해주면 되는데요, `uri`와 `predicates`에 정보를 기입해주면 됩니다.  

````yaml
server:
  port: ${PORT}

spring:
  main:
    web-application-type: reactive
  application:
    name: ${NAME}
  cloud:
    gateway:
      routes:
        - id: ${ID}
          uri: ${URI}
          predicates:
            - Path=/api/**
        - id: ${ID}
          uri: ${URI}
          predicates:
            - Path=/api/**
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false

locations:
  first: ${FIRST}
  second: ${FIRST}
````


<br/><br/><br/><br/><br/><br/><br/><br/>

## 코드 설정

두 번째는 코드로 이를 설정하는 방법입니다. 아래와 같이 `RouteLocatorBuilder`를 받아서 어떤 경로로 들어왔을 때 이를 라우팅 시켜줄 지를 명시해주면 됩니다. 

```kotlin
@Configuration
class FilterConfiguration(
    @Value("\${locations.first}") private val firstServer: String,
    @Value("\${locations.second}") private val secondServer: String
) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes().route { r ->
            r.path("/api/clients/**").filters { f ->
                f.addRequestHeader("first", "first")
                    .addResponseHeader("first", "first")
            }.uri(firstServer)
        }
            .route { r ->
                r.path("/api/clients/**").filters { f ->
                    f.addRequestHeader("second", "second")
                        .addResponseHeader("second", "second")
                }.uri(secondServer)
            }
            .build()
    }
}
```
