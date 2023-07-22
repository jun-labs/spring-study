## Hibernate Naming


Hibernate는 두 개의 다른 명명 전략 을 사용 하여 객체 모델에서 이름을 상응하는 데이터베이스 이름으로 매핑한다. 물리적 구현과 암시 적 전략 구현의 정규화 된 클래스 이름은 각각 spring.jpa.hibernate.naming.physical-strategyand spring.jpa.hibernate.naming.implicit-strategy속성 을 설정하여 구성 할 수 있습니다 . 또는 응용 프로그램 컨텍스트에서 ImplicitNamingStrategy또는 PhysicalNamingStrategyBean을 사용할 수있는 경우 Hibernate는이를 사용하도록 자동으로 구성됩니다.



기본적으로 스프링 부트는 물리적 이름 지정 전략을 사용하여 구성합니다 SpringPhysicalNamingStrategy. 이 구현은 Hibernate 4와 같은 테이블 구조를 제공한다 : 모든 도트는 밑줄로 대체되고 낙타의 대문자는 밑줄로 대체된다. 기본적으로 모든 테이블 이름은 소문자로 생성되지만 스키마에 필요하면 해당 플래그를 무시할 수 있습니다.

하이버네이트는 기본적으로 lowerCarmelCase를 `lower_snake_case`로 변환합니다.



방법은 간단합니다. 아래와 같이 .yml을 설정해줍니다.

````yaml
spring:
  datasource:
    driver-class-name: ${DRIVER_CLASS_NAME}
    url: ${URL}
    username: ${USERNAME}
    password: ${PASSWORD}
  jpa:
    hibernate.dialect: org.hibernate.spatial.dialect.mysql.MySQL8SpatialDialect
    hibernate:
      # 네이밍 전략지정
      naming:
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
````

> 스프링 부트는 기본적으로 physical 네이밍 전략을 SpringPhysicalNamingStrategy로 설정합니다.
