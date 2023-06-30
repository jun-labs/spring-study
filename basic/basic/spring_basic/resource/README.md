## 스프링


## 1. 등장 배경

2000년대 초반, [EJB(Enterprise Java Bean)](https://en.wikipedia.org/wiki/Jakarta_Enterprise_Beans)는 자바 진영의 표준 기술이었습니다. 이는
인스턴스 엔티티 빈(Entity Bean), 설정에 의한 트랜잭션 처리, 분산 처리 등 고급 기술을 지원했기 때문에 많은 분야에서 사용되었습니다. EJB로 인해 개발자들은 비즈니스 로직에 집중할 수 있었지만 하나의
기능을 구현하기 위해 EJB 기술에 대한 의존도가 높았고, 프레임워크가 무겁고 느리며 복잡/난해했기 때문에 시간이 지남에 따라 개발자들이 외면하는 기술이 되어버렸습니다.

<br/>

2000년 9월, 마틴 파울러(Martin Fowler)와 그와 동료들의 컨퍼런스를
준비하다 [POJO(Plain Old Java Object)](https://martinfowler.com/bliki/POJO.html)라는 용어가 탄생했습니다. POJO는 순수 자바 코드로만 이루어진 객체를
가리키며, EJB와 같은 복잡/제한적인 기술보다 순수 자바 코드를 통해 비즈니스 로직을 구현하는 편이 낫다고 생각했기 때문입니다. 이는 조금씩 개발자들 사이에서 공감대를 얻으며 확산되기 시작했습니다.

<br/>

이후 로드 존슨은 그의 저서에서 EJB의 문제점을 지적하는 한편 EJB 없이 순수 자바만으로도 좋은 객체지향 애플리케이션을 개발할 수 있다는 것을 보여주었습니다. 그는 3만여 줄의 코드를 세상에 공개했는데, 이
코드는 현재 스프링의 축을 이루는 핵심 기능의 모체가 되었습니다. 해당 책의 내용을 보면 현재 스프링의 근간을 이루고 있는 BeanFactory, ApplicationContext 등 지금도 스프링의 핵심이 되는
코드들이 담겨 있습니다. 이후 [유겐 휠로(Juergen Hoeller)](https://spring.io/team/jhoeller)와 얀 키로프(Yann Caroff)가 로드 존슨에게 책을 기반으로 한 오픈소스
프로젝트를 제안하며 2004년 마침내 스프링 프레임워크가 탄생하게 되었습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

아래는 스프링 어원의 유래입니다. 요약하면 EJB에 종속되던 겨울이 끝나고 봄이 왔다는 의미에서 붙여졌습니다.

> Whatever happened next, the framework needed a name. In the book it was referred to as the “Interface21 framework” (at
> that point it used com.interface21 package names), but that was not a name to inspire a community. Fortunately Yann
> stepped up with a suggestion: “Spring”. His reasoning was association with nature (having noticed that I'd trekked to
> Everest Base Camp in 2000); and the fact that Spring represented a fresh start after the “winter” of traditional J2EE.
> We recognized the simplicity and elegance of this name, and quickly agreed on it.

<br/><br/><br/><br/><br/><br/><br/><br/>

## 2. [Release Note](https://github.com/spring-projects/spring-framework/releases)

스프링의 Release Note. 수십년 간 여러 버전을 거쳐 현재(2023) SpringFramework 6.0, SpringBoot 3.0이 Release 되었습니다.

&nbsp;&nbsp; - 2003년: SpringFramework 1.0 Release - XML <br/>
&nbsp;&nbsp; - 2006년: SpringFramework 2.0 Release - XML 편의 기능 지원 <br/>
&nbsp;&nbsp; - 2009년: SpringFramework 3.0 Release - 자바 코드로 설정 <br/>
&nbsp;&nbsp; - 2013년: SpringFramework 4.0 Release - 자바 8 <br/>
&nbsp;&nbsp; - 2014년: SpringBoot 1.0 Release <br/>
&nbsp;&nbsp; - 2017년: SpringFramework 5.0 Release, SpringBoot 2.0 Release, Reactive 지원 <br/>
&nbsp;&nbsp; - 2023년: SpringFramework 6.0 Release, SpringBoot 3.0 Release<br/>

![image](./resource/images/spring_stack.png)

<br/><br/><br/><br/><br/><br/><br/><br/>

## 3. 스프링과 스프링 부트

[스프링](https://github.com/spring-projects/spring-framework)은 매우 거대한 프로젝트입니다. 스프링 프레임워크는 기능이 많은 만큼 환경설정이 복잡한데, 개발자들이 이에
대해 어려움을 느끼며 이를 해결하기 위해 스프링 부트가 등장했습니다. 스프링 부트는 `스프링 프레임워크를 사용하기 위한 설정의 많은 부분을 자동화`하여 사용자가 편하게 스프링을 사용할 수 있도록 돕습니다. 즉 설정,
의존성 관리 등을 신경 쓸 필요 없이 바로 개발을 시작할 수 있게 도와주는데 이것이 스프링과 스프링부트의 핵심 차이점입니다.

<br/><br/><br/><br/>

### 3-1. SpringFramework

&nbsp;&nbsp; - 핵심 기술: 스프링 DI 컨테이너, AOP, 이벤트, 기타 <br/>
&nbsp;&nbsp; - 웹 기술: 스프링 MVC, 스프링 WebFlux <br/>
&nbsp;&nbsp; - 데이터 접근 기술: 트랜잭션, JDBC, ORM/XML 지원 <br/>
&nbsp;&nbsp; - 기술 통합: 캐시, 이메일, 원격접근, 스케줄링 <br/>
&nbsp;&nbsp; - 테스트: 스프링 기반 테스트 지원 <br/>
&nbsp;&nbsp; - 언어: 코틀린, 그루비 <br/>

> 최근에는 스프링 부트를 통해 스프링 프레임워크의 기술들을 편리하게 사용할 수 있게 되었습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

### 3-2. SpringBoot

&nbsp;&nbsp; - 스프링을 편리하게 사용할 수 있도록 지원 <br/>
&nbsp;&nbsp; - 단독으로 실행할 수 있는 스프링 애플리케이션을 쉽게 생성 <br/>
&nbsp;&nbsp; - Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨 <br/>
&nbsp;&nbsp; - 손쉬운 빌드 구성을 위한 starter 종속성 제공 <br/>
&nbsp;&nbsp; - 스프링과 외부 라이브러리 자동 구성 <br/>
&nbsp;&nbsp; - 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공 <br/>
&nbsp;&nbsp; - 관례에 의한 간결한 설정 <br/>

<br/><br/><br/><br/><br/><br/><br/><br/>

[스프링 부트 공식문서](https://spring.io/projects/spring-boot)를 보면 아래와 같이 정의되어 있습니다.

> Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".


<br/><br/><br/><br/><br/><br/><br/><br/>

즉 아래와 같은 설정들을 자동화해서 스프링을 잘 `사용`할 수 있게 도와주는 것입니다.

&nbsp;&nbsp; - Embed 내장 Tomcat 사용 <br/>
&nbsp;&nbsp; - SpringBoot Starter을 통한 dependency 자동화(XML설정 X) <br/>
&nbsp;&nbsp; - .jar를 통해 손쉽게 배포가 가능

> 이 외에도 수 많은 편의성이 존재하는데요, 이에 대해서는 직접 학습해보실 것을 권장드립니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

위에 언급한 SpringBoot Starter란 간편하게 의존성을 관리할 수 있도록 도와주는데, 예를 들어 우리가 JPA를 사용하기 위해서는 prom.xml이나
build.gradle에 `spring-boot-starter-data-jpa` 의존성만 추가해주면 스프링부트가 그에 필요한 라이브러리들을 자동으로 받아옵니다.

```groovy
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

### 3-3. 스프링 핵심

스프링은 자바 언어 기반의 구조이며, 자바는 객체 지향 기반의 언어입니다. 즉 스프링은 객체 지향 언어로서의 자바 언어의 특징을 잘 살려줄 수 있는 구조입니다.

&nbsp;&nbsp; - 스프링은 자바 언어 기반의 프레임 워크 <br/>
&nbsp;&nbsp; - 스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크 <br/>
&nbsp;&nbsp; - 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크 <br/>

> POJO의 흐름대로 순수한 자바의 모양대로 프로그래밍을 할 수 있도록 도와주는 툴


<br/><br/><br/><br/><br/><br/><br/><br/>

스프링은 일반적으로 3가지의 표현 중 하나로 사용되는데 그는 아래와 같습니다.

&nbsp;&nbsp; - 스프링 DI 컨테이너 기술 <br/>
&nbsp;&nbsp; - 스프링 프레임워크 <br/>
&nbsp;&nbsp; - 스프링 생태계 <br/>


<br/><br/><br/><br/><br/><br/><br/><br/>

## 4. 프레임워크 vs 라이브러리

프레임워크가 내가 작성한 코드를 프레임워크가 제어하고 대신 실행해줍니다. 즉 주도권이 프레임워크(ex. JUnit)에 있습니다. 반면 라이브러리는 개발자가 작성한 코드를 직접 제어하며, 제어의흐름이 개발자에게 있습니다.

![image](./resource/images/library.png)

> 자바 main 메서드 같은 경우 개발자가 필요한 것들을 new를 통해 직접 호출합니다.

<br/><br/><br/><br/><br/><br/><br/><br/>


## 5. DI와 정적 클래스 의존 관계

객체를 생성, 관리하며 의존관계를 연결해주는 것을 IOC 컨테이너 또는 DI 컨테이너라고 합니다. 이때 정적 의존관계는 애플리케이션을 실행하지 않아도 분석할 수 있는데, 클래스가 사용하는 `import`만 보고 의존관계를 파악할 수 있기 때문입니다. 반면 애플리케이션을 실행했을 때만 알 수 있는 런타임 의존관계도 있습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

### 5-1. 스프링 컨테이너

일반적으로 `ApplicationContext` 를 스프링 컨테이너라 합니다. 이는 인터페이스로 XML, 어노테이션기반의 자바 설정으로 만들 수 있습니다. 스프링 컨테이너를 부를 때 BeanFactory , ApplicationContext로 구분해 이야기합니다. BeanFactory 기능을 모두 상속받아서 제공합니다. 빈을 관리하고 검색하는 기능은 BeanFactory도 제공하지만, 이 둘의 차이점은 국제화 기능, 환경 변수, 애플리케이션 이벤트, 편리한 리소스 조회 등과 같은 다양한 기능을 주는 것입니다.

```java
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
		MessageSource, ApplicationEventPublisher, ResourcePatternResolver {

	@Nullable
	String getId();

	String getApplicationName();

	String getDisplayName();

	long getStartupDate();

	@Nullable
	ApplicationContext getParent();

	AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;

}
```

<br/><br/><br/><br/><br/><br/><br/>

### 5-2. BeanFactory

스프링 컨테이너의 최상위 인터페이스로 스프링 빈을 관리하고 조회하는 역할을 담당합니다. 이는 getBean 메서드를 제공하며 ApplicationContext는 BeanFactory의 메서드가 제공하는 기능입니다.

````java
public class BeanFactory implements ObjectFactory {

    private static final StringManager sm = StringManager.getManager(BeanFactory.class);
    private final Log log = LogFactory.getLog(BeanFactory.class); // Not static

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?,?> environment)
            throws NamingException {

        if (obj instanceof ResourceRef) {

            try {
                Reference ref = (Reference) obj;
                String beanClassName = ref.getClassName();
                Class<?> beanClass = null;
                ClassLoader tcl = Thread.currentThread().getContextClassLoader();
                try {
                    if (tcl != null) {
                        beanClass = tcl.loadClass(beanClassName);
                    } else {
                        beanClass = Class.forName(beanClassName);
                    }
                } catch(ClassNotFoundException cnfe) {
                    NamingException ne = new NamingException(sm.getString("beanFactory.classNotFound", beanClassName));
                    ne.initCause(cnfe);
                    throw ne;
                }

                BeanInfo bi = Introspector.getBeanInfo(beanClass);
                PropertyDescriptor[] pda = bi.getPropertyDescriptors();

                Object bean = beanClass.getConstructor().newInstance();

                // Look for the removed forceString option
                RefAddr ra = ref.get("forceString");
                if (ra != null) {
                    log.warn(sm.getString("beanFactory.noForceString"));
                }

                Enumeration<RefAddr> e = ref.getAll();
                String value;

                while (e.hasMoreElements()) {

                    ra = e.nextElement();
                    String propName = ra.getType();

                    if (propName.equals(Constants.FACTORY) ||
                        propName.equals("scope") || propName.equals("auth") ||
                        propName.equals("forceString") ||
                        propName.equals("singleton")) {
                        continue;
                    }

                    value = (String)ra.getContent();

                    Object[] valueArray = new Object[1];

                    int i = 0;
                    for (i = 0; i < pda.length; i++) {

                        if (pda[i].getName().equals(propName)) {

                            Class<?> propType = pda[i].getPropertyType();
                            Method setProp = pda[i].getWriteMethod();

                            if (propType.equals(String.class)) {
                                valueArray[0] = value;
                            } else if (propType.equals(Character.class) || propType.equals(char.class)) {
                                valueArray[0] = Character.valueOf(value.charAt(0));
                            } else if (propType.equals(Byte.class) || propType.equals(byte.class)) {
                                valueArray[0] = Byte.valueOf(value);
                            } else if (propType.equals(Short.class) || propType.equals(short.class)) {
                                valueArray[0] = Short.valueOf(value);
                            } else if (propType.equals(Integer.class) || propType.equals(int.class)) {
                                valueArray[0] = Integer.valueOf(value);
                            } else if (propType.equals(Long.class) || propType.equals(long.class)) {
                                valueArray[0] = Long.valueOf(value);
                            } else if (propType.equals(Float.class) || propType.equals(float.class)) {
                                valueArray[0] = Float.valueOf(value);
                            } else if (propType.equals(Double.class) || propType.equals(double.class)) {
                                valueArray[0] = Double.valueOf(value);
                            } else if (propType.equals(Boolean.class) || propType.equals(boolean.class)) {
                                valueArray[0] = Boolean.valueOf(value);
                            } else if (setProp != null) {
                                // This is a Tomcat specific extension and is not part of the
                                // Java Bean specification.
                                String setterName = setProp.getName();
                                try {
                                    setProp = bean.getClass().getMethod(setterName, String.class);
                                    valueArray[0] = value;
                                } catch (NoSuchMethodException nsme) {
                                    throw new NamingException(sm.getString(
                                            "beanFactory.noStringConversion", propName, propType.getName()));
                                }
                            } else {
                                throw new NamingException(sm.getString(
                                        "beanFactory.noStringConversion", propName, propType.getName()));
                            }

                            if (setProp != null) {
                                setProp.invoke(bean, valueArray);
                            } else {
                                throw new NamingException(sm.getString("beanFactory.readOnlyProperty", propName));
                            }

                            break;
                        }
                    }

                    if (i == pda.length) {
                        throw new NamingException(sm.getString("beanFactory.noSetMethod", propName));
                    }
                }

                return bean;

            } catch (java.beans.IntrospectionException ie) {
                NamingException ne = new NamingException(ie.getMessage());
                ne.setRootCause(ie);
                throw ne;
            } catch (java.lang.ReflectiveOperationException e) {
                Throwable cause = e.getCause();
                if (cause instanceof ThreadDeath) {
                    throw (ThreadDeath) cause;
                }
                if (cause instanceof VirtualMachineError) {
                    throw (VirtualMachineError) cause;
                }
                NamingException ne = new NamingException(e.getMessage());
                ne.setRootCause(e);
                throw ne;
            }

        } else {
            return null;
        }
    }
}
````

<br/><br/><br/><br/><br/><br/><br/>


### 5-3. BeanDefinition

`스프링 빈 설정 메타 정보`. 역할과 구현을 개념적으로 나눈 것입니다. XML을 읽어서 BeanDefinition을 만들 수도 있습니다. 이를 설정 메타정보라 하며 @Bean 당 하나가 생성되며 스프링은 이 어노테이션을 기반으로 컨테이너를 생성합니다.

````java
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

	int ROLE_APPLICATION = 0;

	int ROLE_SUPPORT = 1;

	int ROLE_INFRASTRUCTURE = 2;

	void setParentName(@Nullable String parentName);

	@Nullable
	String getParentName();

	void setBeanClassName(@Nullable String beanClassName);

	@Nullable
	String getBeanClassName();

	void setScope(@Nullable String scope);

	@Nullable
	String getScope();

	void setLazyInit(boolean lazyInit);

	boolean isLazyInit();

	void setDependsOn(@Nullable String... dependsOn);

	@Nullable
	String[] getDependsOn();

	void setAutowireCandidate(boolean autowireCandidate);

	boolean isAutowireCandidate();

	void setPrimary(boolean primary);

	boolean isPrimary();

	void setFactoryBeanName(@Nullable String factoryBeanName);

	@Nullable
	String getFactoryBeanName();

	void setFactoryMethodName(@Nullable String factoryMethodName);

	@Nullable
	String getFactoryMethodName();

	ConstructorArgumentValues getConstructorArgumentValues();

	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	MutablePropertyValues getPropertyValues();

	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}

	void setInitMethodName(@Nullable String initMethodName);

	@Nullable
	String getInitMethodName();

	void setDestroyMethodName(@Nullable String destroyMethodName);

	@Nullable
	String getDestroyMethodName();

	void setRole(int role);

	int getRole();

	void setDescription(@Nullable String description);

	@Nullable
	String getDescription();

	ResolvableType getResolvableType();

	boolean isSingleton();

	boolean isPrototype();

	boolean isAbstract();

	@Nullable
	String getResourceDescription();

	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}
````

> 스프링 컨테이너는 이것이 자바 코드인지, XML인지 몰라도 됩니다. 만약 XML로 만든다면 GenericXMLApplicationContext의 XMLBeanDefinitionReader로 빈을 생성합니다.

<br/><br/><br/><br/><br/><br/><br/>


이는 아래와 같은 정보를 담고 있습니다.

&nbsp;&nbsp; - BeanClassName: 생성할 빈의 클래스 명 <br/>
&nbsp;&nbsp; - factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름 <br/>
&nbsp;&nbsp; - factoryMethodName: 빈을 생성할 팩토리 메서드 지정 <br/>
&nbsp;&nbsp; - Scope: 싱글톤(기본값) <br/>
&nbsp;&nbsp; - lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아닌 실제 빈을 사용할 때 까지 최대한 생
성을 지연처리 하는지 여부 <br/>
&nbsp;&nbsp; - InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명 <br/>
&nbsp;&nbsp; - DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명 <br/>
&nbsp;&nbsp; - Constructor arguments, Properties: 의존관계 주입에서 사용 <br/>

<br/><br/><br/><br/><br/><br/><br/><br/>

## 6. 싱글톤 컨테이너와 의존관계 자동 주입

`스프링이 빈을 관리하는 방법`과 `의존관계를 주입하는 법`, 또 `이 때 발생할 수 있는 문제점`에 대해 살펴보겠습니다.

<br/><br/><br/><br/>

## 6-1. 싱글톤 컨테이너
스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서 객체 인스턴스를 싱글톤으로 관리하며 이처럼 싱글톤 객체를 생성/관리하는 기능을 `싱글톤 레지스트리`라 합니다. 스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있습니다.

> 따라서 DIP, OCP, 테스트, private 생성자, 복잡한 로직 등으로 부터 자유롭게 싱글톤을 사용할 수 있습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

스프링 컨테이너에 빈을 등록하기 위해서는 @Configuration을 사용할 수 있는데요, 아래와 같이 @Configuration을 사용하면 내부에 있는 빈들을 스프링 컨테이너에 등록할 수 있습니다.

```kotlin
@Configuration
class AppConfig {

    /**
     *  빈으로 등록된 타입을 보고 결정한다.
     *  즉 MemberServiceImpl이 아닌 MemberService를 보고 결정.
     * */
    @Bean
    fun memberService(): MemberService {
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        return MemoryMemberRepository()
    }

    @Bean
    fun orderService(): OrderService {
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    @Bean
    fun discountPolicy(): DiscountPolicy {
        return FixDiscountPolicy()
    }
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

이때 아래와 같이 사용하더라도 싱글톤이 깨지지 않습니다.

```kotlin
@Configuration
class AppConfig {

    @Bean
    fun memberService(): MemberService {
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        return MemoryMemberRepository()
    }

    // 싱글톤이 깨지지 않습니다.
    @Bean
    fun orderService(): OrderService {
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

}
 ```

<br/><br/><br/><br/><br/><br/><br/><br/>

즉 아래와 같이 테스트해도 같은 객체가 나옵니다.

```kotlin
@DisplayName("Configuration Singleton 테스트")
class ConfigurationSingletonTest {

    @Test
    @DisplayName("스프링 컨테이너에서 관리되는 빈은 싱글톤이다.")
    fun singleton_test() {
        val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        val orderService = applicationContext.getBean("orderService", OrderServiceImpl::class.java)
        val memberService = applicationContext.getBean("memberService", MemberServiceImpl::class.java)

        val memberRepositoryA = orderService.memberRepository
        val memberRepositoryB = memberService.memberRepository

        assertEquals(memberRepositoryA, memberRepositoryB)
    }
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

이는 스프링이 CGLIB를 활용해 관리하기 때문인데, 이는 바이트코드를 조작하며 자세한 과정까지 알 필요는 없기 때문에 간략히 살펴보겠습니다.

```kotlin
@Test
fun cglib_print_test() {
    val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    val appConfig = applicationContext.getBean(AppConfig::class.java)
    println(appConfig)
}
```

> project.study.spring.common.configuration.AppConfig$$SpringCGLIB$$0@6a933be2. 실제 등록되는 빈은 해당 이름으로 들어가고 이는 CGLIB~ 로 들어가게 됩니다.


<br/><br/><br/><br/><br/><br/><br/><br/>

즉 수도코드로 보면 아래와 같습니다.

```kotlin
@Bean
fun memberRepository(): MemberRepository {
    if (memberRepository != null) {
        return memberRepository
    } else {
        return MemberRepository()
    }
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

그런데 만약 아래와 같이 @Configuration을 제거하면 실제 내가 등록한 객체가 조회되게 됩니다.

```kotlin
// @Configuration: 주석 제거 시 실제 객체가 조회  
class AppConfig {

    private val log = LoggerFactory.getLogger(this::class.java)
    
        ......
    
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

### 6-2. ComponentScan

컴포넌트 스캔을 사용하면 @Component가 붙은 클래스를 자동으로 등록합니다. 이때 제외할 필터나 어노테이션을 지정할 수 있습니다. 이때 `스프링 빈의 기본 이름은 클래스명을 사용`하며 `맨 앞글자만 소문자를 사용`합니다. 만약 스프링 빈의 이름을 직접 지정하려면 이름을 부여하면 됩니다.

````kotlin
@Configuration
@ComponentScan(
    excludeFilters = [Filter(type = FilterType.ANNOTATION, classes = [Configuration::class])],
    basePackages = ["project.study.spring.core"]
)
class AutoAppConfig {
}
````


<br/><br/><br/><br/><br/><br/><br/><br/>

FilterType에는 총 다섯 가지 종류가 있는데요, 이에 대해서는 한 번 살펴볼 것을 권장드립니다.

````kotlin
public enum FilterType {

	/**
	 * Filter candidates marked with a given annotation.
	 * @see org.springframework.core.type.filter.AnnotationTypeFilter
	 */
	ANNOTATION,

	/**
	 * Filter candidates assignable to a given type.
	 * @see org.springframework.core.type.filter.AssignableTypeFilter
	 */
	ASSIGNABLE_TYPE,

	/**
	 * Filter candidates matching a given AspectJ type pattern expression.
	 * @see org.springframework.core.type.filter.AspectJTypeFilter
	 */
	ASPECTJ,

	/**
	 * Filter candidates matching a given regex pattern.
	 * @see org.springframework.core.type.filter.RegexPatternTypeFilter
	 */
	REGEX,

	/** Filter candidates using a given custom
	 * {@link org.springframework.core.type.filter.TypeFilter} implementation.
	 */
	CUSTOM

}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

@ComponentScan을 사용하면 @Autowired를 사용해 빈을 주입할 수 있습니다. 우선은 `타입`을 찾아 이를 조회하고 `이름`을 통해서도 이를 주입할 수 있습니다.

````kotlin
@Component
class MemberServiceImpl(
    @Autowired val memberRepository: MemberRepository  // @Autowired
) : MemberService {

    override fun save(member: Member): Member {
        return memberRepository.save(member)
    }

    override fun findById(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

### 6-3. 중복 빈 충돌

조회 대상 빈이 2개 이상일 때 충돌이 발생할 수 있는데요, 아래와 같이 동일한 빈이 두 개 이상 있다면 `NoUniqueBeanDefinitionException` 오류가 발생하게 됩니다. 이에 대한 해결 방법을 살펴보겠습니다.

```kotlin
@DisplayName("동일 빈 조회 테스트")
class SameBeanFindTest {

    private val applicationContext = AnnotationConfigApplicationContext(SameBeanConfiguration::class.java)

    @Test
    @DisplayName("동일한 타입의 빈이 두 개 이상 있다면 NoUniqueBeanDefinitionException이 발생한다.")
    fun same_type_bean_over_two_test() {
        assertThatThrownBy { applicationContext.getBean(MemberRepository::class.java) }
            .isExactlyInstanceOf(NoUniqueBeanDefinitionException::class.java)
            .hasMessage(message)
    }

    @Test
    @DisplayName("동일한 타입의 빈이 두 개 이상 있더라도 빈 이름을 명시하면 오류가 발생하지 않는다.")
    fun bean_type_definition_test() {
        val bean = applicationContext.getBean("memberRepository", MemberRepository::class.java)

        assertNotNull(bean)
    }

    @Test
    @DisplayName("특정 타입으로 빈을 조회할 수 있다.")
    fun bean_search_by_type() {
        val beans = applicationContext.getBeansOfType(MemberRepository::class.java)

        assertEquals(2, beans.size)
    }

    @Configuration
    class SameBeanConfiguration {
        @Bean
        fun memberRepository(): MemberRepository {
            return MemoryMemberRepository()
        }

        @Bean
        fun memberRepositoryV2(): MemberRepository {
            return MemoryMemberRepository()
        }
    }

    companion object {
        private const val message =
            "No qualifying bean of type 'project.study.inflearn.core.domain.member.persistence.MemberRepository' available: expected single matching bean but found 2: memberRepository,memberRepository2"
    }
}
```

&nbsp;&nbsp; - @Autowired 필드 명 매칭 <br/>
&nbsp;&nbsp; - @Qualifier @Qualifier끼리 매칭 빈 이름 매칭 <br/>
&nbsp;&nbsp; - @Primary 사용 <br/>
&nbsp;&nbsp; - @Autowired 필드 명 매칭 <br/>
&nbsp;&nbsp; - @Autowired 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭 <br/>

<br/><br/><br/><br/><br/><br/><br/><br/>

### 6-3-1. @Qualifier / @Primary

우선 @Qualifier, @Primary를 사용하는 방법이 있습니다. 일반적으로는 아래와 같이 동일한 타입의 빈이 여러개 있을 경우 오류가 발생하게 됩니다.

```kotlin
@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    @Qualifier("rateDiscountPolicy") private val discountPolicy: DiscountPolicy
) : OrderService {

    override fun createOrder(
        memberId: Long,
        itemName: String,
        price: BigDecimal
    ): Order {
        val findMember = memberRepository.findById(memberId)!!
        val discountPrice = rateDiscountPolicy.discount(findMember, price)
        return Order(memberId, itemName, price, discountPrice)
    }
}
```

![image](./resource/images/fail.png)

<br/><br/><br/><br/><br/><br/><br/><br/><br/>

물론 이를 하위 타입을 구체적으로 지정해서 해결할 수도 있지만 하위 타입으로 지정하는 것은 DIP를 위배하고 유연성이 떨어집니다. 또한  이름만 다르고 완전히 똑같은 타입의 스프링 빈이 2개 있을 때 해결이 안됩니다.

````kotlin
@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val rateDiscountPolicy: DiscountPolicy
) : OrderService {

    override fun createOrder(
        memberId: Long,
        itemName: String,
        price: BigDecimal
    ): Order {
        val findMember = memberRepository.findById(memberId)!!
        val discountPrice = rateDiscountPolicy.discount(findMember, price)
        return Order(memberId, itemName, price, discountPrice)
    }
}
````

![image](./resource/images/success.png)

> 스프링 빈을 수동 등록해서 문제를 해결해도 되지만 이 외에도 의존 관계 자동 주입에서 해결하는 여러 방법이 있습니다. 필드 명 매칭은 먼저 타입 매칭을 시도 하고 그 결과에 여러 빈이 있을 때 추가로 동작하는 기능입니다.


<br/><br/><br/><br/><br/><br/><br/><br/>

### @Qualifier 사용
@Qualifier는 `추가 구분자를 붙여주는 방법`인데 주입시 `추가적인 방법을 제공`하는 것이지 `빈 이름을 변 경하는 것은 아닙니다.

````kotlin
@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    @Qualifier(value = "rateDiscountPolicy") private val discountPolicy: DiscountPolicy
) : OrderService {

    override fun createOrder(
        memberId: Long,
        itemName: String,
        price: BigDecimal
    ): Order {
        val findMember = memberRepository.findById(memberId)!!
        val discountPrice = discountPolicy.discount(findMember, price)
        return Order(memberId, itemName, price, discountPrice)
    }
}
````

![image](./resource/success2.png)

> @Qualifier로 주입할 때 @Qualifier("mainDiscountPolicy")를 찾지 못 하면 mainDiscountPolicy라는 이름을 가진 스프링 빈을 추가로 찾습니다. 하지만 가급적 @Qualifier는 @Qualifier를 찾는 용도로만 사용하는 것이 좋습니다.



<br/><br/><br/><br/><br/><br/><br/><br/>


### @Primary
@Primary는 우선순위를 정하는 방법입니다. @Autowired 시에 여러 빈이 매칭되면 @Primary 가 우선권을 가지게 됩니다.

````kotlin
@Primary
@Component
class RateDiscountPolicy : DiscountPolicy {

    override fun discount(
        member: Member,
        price: BigDecimal
    ): BigDecimal {
        if (member.grade == Grade.VIP) {
            return price.multiply(DISCOUNT_PERCENT)
        }
        return BigDecimal.ZERO
    }

    companion object {
        private val DISCOUNT_PERCENT = BigDecimal.valueOf(0.9)
    }
}
````

> 예를 들어 메인 데이터베이스와 서브 데이터베이스 커넥션을 가져오는 것에서 사용할 수 있습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

### 6-3-2. @Primary, @Qualifier 활용

메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 @Primary를 적용해서 조회하는 곳에서 @Qualifier 지정 없이
편리하게 조회하고, 서브 데이터베이스 커넥션 빈을 획득할 때는 @Qualifier를 지정해서 명시적으로 얻는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있습니다. 물론 이때 메인 데이터베이스의 스프링 빈을 등록할 때 @Qualifier를 지정해 주는 것은 상관없습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>


@Primary는 기본값처럼 동작하며 @Qualifier는 상세하게 동작한다. 이런 경우 어떤 것이 스프링은 자동보다는 수동이, 넓은 범위의 선택권보다는 좁은 범위의 선택권이 우선순위가 높습니다. 따라서 여기서도 @Qualifier 가 우선권이 높습니다.

```kotlin
@Component
@MainDiscountPolicy
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val discountPolicy: DiscountPolicy
) : OrderService {

    override fun createOrder(
        memberId: Long,
        itemName: String,
        price: BigDecimal
    ): Order {
        val findMember = memberRepository.findById(memberId)!!
        val discountPrice = discountPolicy.discount(findMember, price)
        return Order(memberId, itemName, price, discountPrice)
    }
}
```

> 애노테이션에는 상속이라는 개념이 없습니다. 여러 어노테이션을 모아서 사용하는 것은 스프링이 지원 해주는 기능으로 @Qualifier 뿐 아니라 다른 어노테이션들도 함께 조합해 사용할 수 있습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

### 6-3-2. 기준

자동/수동의 올바른 실무 운영 기준은 편리한 자동 기능을 기본으로 사용하는 것입니다. 스프링이 나오고 시간이 갈수록 점점 자동을 선호하는 추세기 때문입니다. 현재 스프링은 @Component뿐만 아니라 @Controller , @Service , @Repository처럼 계층에 맞추어 일반적인 애플리케이션 로직을 자동으로 스캔할 수 있도록 지원하고 있습니다.

> 최근 스프링 부트는 컴포넌트 스캔을 기본으로 사용하며 스프링 부트의 다양한 스프링 빈들도 조건이 맞으면 자동으로 등록하도록 설계했습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

물론 수동 빈 등록을 사용해야 할 때도 있는데요, 그 전에 애플리케이션은 크게 업무 로직과 기술 지원 로직으로 나누어져 있으며 빈을 등록할 때도 케이스를 나누어야 합니다. 업무 로직 빈은 웹을 지원하는 컨트롤러, 핵심 비즈니스 로직이 있는 서비스, 데이터 계층의 로직을 처리하는 리포지토리로 이는 보통 비즈니스 요구사항을 개발할 때 추가되거나 변경됩니다. 반면
기술 지원 빈은 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용됩니다. 즉 데이터베이스 연결이나, 공
통 로그 처리처럼 업무 로직을 지원하기 위한 하부 기술이나 공통 기술들입니다.

<br/>

업무 로직은 숫자도 매우 많고, 한번 개발하면 컨트롤러, 서비스, 리포지토리 처럼 어느 정도 유사한 패
턴이 있는데 이런 경우 자동 기능을 적극 사용하는 것이 좋습니다. 이를 통해 어떤 곳에서 문제가 발생
했는지 명확하게 파악하기 쉬워지기 때문입니다. 반면 기술 지원 로직은 업무 로직과 비교해서 그 수가 매우 적고 보통 애플리케이션 전반에 걸쳐서 광범위하게 영향을 미칩니다. 업무 로직은 문제가 발생했을 때 어디가 문제인지 명확하게 잘 드러나지만, 기술 지원
로직은 적용이 잘 되고 있는지 아닌지조차 파악하기 어려운 경우가 많으므로 이런 기술 지원 로직들은
가급적 수동 빈 등록을 사용해서 명확하게 드러내는 것이 좋습니다.

> 정리해보면 업무 로직과 관련된 빈은 자동 빈 등록을 사용하는 것이 좋으며, 기술 지원 빈은 수동으로 등록하는 것이 좋습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>


## 6-4. 옵션 처리

주입할 스프링 빈이 없어도 동작해야 할 때가 있습니다. 하지만 @Autowired만 사용하면 required 옵션의 기본값이 true 로 되어 있어 자동 주입 대상이 없으면 오류가 발생합니다. 자동 주입 대상을 옵션으로 처리하는 방법은 다음과 같습니다.

```kotlin
class AutowiredTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var applicationContext: AnnotationConfigApplicationContext

    @Test
    fun autowired() {
        applicationContext = AnnotationConfigApplicationContext(TestBean::class.java)
    }


    class TestBean {
        private val log = LoggerFactory.getLogger(this::class.java)

        /**
         *  Member는 @Bean이 아니기 때문에
         *  @Autowired(required = false)를 설정해야 합니다.
         *  우선 이는 호출 자체가 되지 않습니다.
         * */
        @Autowired(required = false)
        fun noBeanA(member: Member) {
            log.info("NoBeanA: $member")
        }

        // Null이면 Null이 출력됩니다.
        @Autowired
        fun noBeanB(@Nullable member: Member?) {
            log.info("NoBeanB: $member")
        }

        @Autowired
        fun noBeanC(optionalMember: Optional<Member>) {
            log.info("NoBeanB: $optionalMember")
        }
    }
}
```

<br/><br/><br/><br/><br/><br/><br/><br/>

이는 아래와 같이 로그가 출력됩니다. 즉 정리해보면 아래와 같습니다.

```kotlin
21:03:46.440 [main] INFO project.study.spring.test.AutowiredTest$TestBean -- NoBeanB: null
21:03:46.441 [main] INFO project.study.spring.test.AutowiredTest$TestBean -- NoBeanB: Optional.empty
```
&nbsp;&nbsp; - @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 X <br/>
&nbsp;&nbsp; - org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력. <br/>
&nbsp;&nbsp; - Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력. <br/>

}

<br/><br/><br/><br/><br/><br/><br/><br/>

## 7. 의존성 주입
의존관계 주입은 크게 4가지 방법이 있습니다. 이에 대해 하나씩 살펴보겠습니다.

&nbsp;&nbsp; - 생성자 주입 <br/>
&nbsp;&nbsp; - 수정자 주입(setter 주입) <br/>
&nbsp;&nbsp; - 필드 주입 <br/>
&nbsp;&nbsp; - 일반 메서드 주입 <br/>

<br/><br/><br/><br/><br/><br/><br/><br/>

### 6-3-1. 생성자 주입
생성자를 통해서 의존 관계를 주입 받는 방법입니다.

```kotlin
@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val discountPolicy: DiscountPolicy
) : OrderService {

    override fun createOrder(
        memberId: Long,
        itemName: String,
        price: BigDecimal
    ): Order {
        val findMember = memberRepository.findById(memberId)!!
        val discountPrice = discountPolicy.discount(findMember, price)
        return Order(memberId, itemName, price, discountPrice)
    }
}
```

> 생성자가 한 개만 있으면 @Autowired를 생략해도 자동 주입 됩니다. 이는 스프링 빈에만 해당합니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

이는 아래와 같은 특징을 가지고 있습니다.

&nbsp;&nbsp; - 생성자 호출시점에 딱 1번만 호출되는 것이 보장됩니다. <br/>
&nbsp;&nbsp; - 불변, 필수 의존관계에 사용됩니다. <br/>

<br/><br/><br/><br/><br/><br/><br/><br/>

### 6-3-2. 수정자 주입(setter 주입)

수정자 메서드를 통해서 의존관계를 주입하는 방법입니다.

```kotlin
class NetworkClient {
    private var url: String? = null

    // setter 주입 
    fun setUrl(url: String) {
        this.url = url
    }
}
```

> 참고: 자바빈 프로퍼티, 자바에서는 과거부터 필드의 값을 직접 변경하지 않고, setXxx, getXxx 라는 메서드를 통해서 값을 읽거나 수정하는 규칙을 만들었는데, 그것이 자바빈 프로퍼티 규약입니다.


<br/><br/><br/><br/><br/><br/><br/><br/>

이는 아래와 같은 특징을 가지고 있습니다.

&nbsp;&nbsp; - 선택, 변경 가능성이 있는 의존관계에 사용 <br/>
&nbsp;&nbsp; - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법입니다. <br/>

> 이 외에도 `필드 주입`, `일반 메서드 주입` 등이 있는데 이에 대해서는 생략하겠습니다

<br/><br/><br/><br/><br/><br/><br/><br/>

## 7. 빈 생명주기 콜백 시작

데이터베이스 커넥션 풀, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고 종료 시점에 연결을 모두 종료하는 작업을 진행하려면 객체의 초기화와 종료 작업이 필요합니다. 이를 위해
스프링의 `빈 라이프사이클`을 살펴보겠습니다.

<br/><br/><br/><br/>

### 7-1. 라이프사이클

스프링 빈은 다음과 같은 라이프사이클을 가집니다. 즉 스프링 빈은 객체를 생성하고 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료되는 것입니다.

> 객체 생성 > 의존관계 주입

<br/><br/><br/><br/><br/><br/><br/><br/>

따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 합니다. 이 과정에서 스프링은 의존관계 주입이 완료되면 빈에게 콜백 메서드를 통해 초기화 시점을 알려주며, 컨테이너가 종료되기 직전에는 소멸 콜백을 알려 줍니다. 따라서 안전하게 종
료 작업을 진행할 수 있습니다.

> 생성자는 필수 정보를 받고 메모리를 할당해 객체를 생성하는 책임을 집니다. 반면 초기화는 이렇게 생성된 값들을 활용해 외부 커넥션을 연결하는 등 무거운 동작을 수행합니다. 따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것보다는 객체를 생성하는 부분과 초기화하는 부분
> 을 명확하게 나누는 것이 유지보수 관점에서 좋습니다. 물론 초기화 작업이 내부 값들만 약간 변경하는 정도로 단
> 순한 경우에는 생성자에서 한 번에 다 처리하는 게 더 나을 수 있습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

조금 더 자세히 살펴보면 아래와 같습니다. 이때 싱글톤 빈들은 스프링 컨테이너가 종료될 때 싱글톤 빈들도 함께 종료되며 스프링 컨테이너가 종료되기 직전에 소멸전 콜백이 일어납니다. 하지만 생애 주기가 짧은 빈들은 더 일찍 종료되기도 합니다.

&nbsp;&nbsp; - 스프링 컨테이너 생성<br/>
&nbsp;&nbsp; - 스프링 빈 생성 의존관계 주입 <br/>
&nbsp;&nbsp; - 초기화 콜백 사용 <br/>
&nbsp;&nbsp; - 소멸전 콜백 스프링<br/>
&nbsp;&nbsp; - 종료 <br/>

<br/><br/><br/><br/><br/><br/><br/><br/>


### 7-2. 초기화/종료 방법

빈을 초기화/종료 시키는 방법에 대해 살펴보겠습니다.

<br/><br/><br/><br/>

### 7-2-1. InitializingBean / DisposableBean

인터페이스를 사용하는 초기화 방법. 이는 스프링에 종속적인 구현이 되며 현재는 다음의 더 나은 방법들이 있어 거의 사용하지 않습니다.

```kotlin
class NetworkClientV1 : InitializingBean, DisposableBean {

    private val log = LoggerFactory.getLogger(this::class.java)
    private var url: String? = null

    init {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("URL: $url")
        // connect()
        // call()
    }

    fun registerUrl(url: String) {
        this.url = url
    }

    private fun connect() {
        log.info("Connect")
    }

    private fun call() {
        log.info("Call")
    }

    fun close() {
        log.info("Close")
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    override fun afterPropertiesSet() {
        log.info("INIT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("URL: $url")
        connect()
        call()
    }

    override fun destroy() {
        close()
    }
}
```


<br/><br/><br/><br/><br/><br/><br/><br/>

### 7-2-2. @Bean(initMethod = "$init", destroyMethod = "$destroy")

@Bean 어노테이션 내부에 초기화/종료 메서드를 명시해줍니다.

````kotlin
@Configuration
class LifeCycleConfiguration {
     @Bean(initMethod = "init", destroyMethod = "destroy")
    fun networkClient(): NetworkClient {
        val client = NetworkClient()
        client.registerUrl("http://hello-spring.dev")
        return client
    }
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

### 7-2-3. @PostConstruct / @PreDestroy

`javax.annotation.PostConstruct`로 스프링에 종속적인 기술이 아닌 `JSR 자바 표준 어노테이션` 입니다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작하며 컴포넌트 스캔과도 잘 어울립니다. 단
외부 라이브러리에는 적용하지 못하기 때문에 외부 라이브러리를 초기화/종료해야 하면 @Bean의 기능을 사용하도록 합니다.


```kotlin
class NetworkClient {

    private val log = LoggerFactory.getLogger(this::class.java)
    private var url: String? = null

    init {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("URL: $url")
        // connect()
        // call()
    }

    fun registerUrl(url: String) {
        this.url = url
    }

    private fun connect() {
        log.info("Connect")
    }

    private fun call() {
        log.info("Call")
    }

    fun close() {
        log.info("Close")
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    @PostConstruct
    fun init() {
        log.info("INIT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("URL: $url")
        connect()
        call()
    }

    @PreDestroy
    fun destroy() {
        close()
    }
}
```


<br/><br/><br/><br/><br/><br/><br/><br/>

## 8. 빈 스코프(Bean Scope)

스프링에서의 `스코프`는 `빈이 존재할 수 있는 범위`를 뜻합니다. 이는 아래와 같은 다양한 스코프를 지원합니다.

&nbsp;&nbsp; - 싱글톤: 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프. <br/>
&nbsp;&nbsp; - 프로토타입: 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프. <br/>
&nbsp;&nbsp; - 웹 관련 스코프 <br/>



<br/><br/><br/><br/><br/><br/><br/><br/>

빈 스코프는 아래와 같이 지정할 수 있습니다.
```kotlin
@Scope("prototype")
@Component
class CustomBean
```

<br/><br/><br/><br/><br/><br/><br/><br/>

### 8-1. 프로토타입 스코프

프로토타입 스코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환합니다.
스프링 컨테이너는 프로토타입 빈을 생성하고 의존관계 주입/초기화까지만 처리하며 이후는 프로토타입 빈을 관리하지 않습니다.
이를 관리하는 책임은 프로토타입 빈을 받은 클라이언트에 있습니다.

&nbsp;&nbsp; - 스프링 컨테이너에 요청할 때 마다 새로 생성됩니다. <br/>
&nbsp;&nbsp; - 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여. <br/>

> 따라서 @PreDestroy 같은 종료 메서드가 호출되지 않습니다.

<br/><br/><br/><br/><br/><br/><br/><br/>

따라서 아래와 같이 beanA와 beanB는 다른 객체입니다.

````kotlin
class PrototypeTest {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    fun prototype_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(PrototypeBeanClass::class.java)

        val beanA = applicationContext.getBean(PrototypeBeanClass::class.java)
        val beanB = applicationContext.getBean(PrototypeBeanClass::class.java)

        assertThat(beanA).isNotSameAs(beanB)

        beanA.destroy()
        beanB.destroy()
    }
}
````


<br/><br/><br/><br/><br/><br/><br/><br/>

여러 빈에서 같은 프로토타입 빈을 주입 받으면 주입 받는 시점에 각각 새로운 프로토타입 빈이 생성됩니다. 하지만 사용할 때마다 새로 생성되는 것은 아닙니다. 이를 함께 사용하기 위해서는 Provider로 문제를 해결할 수 있습니다. 또한 아래와 같이 매번 새로운 빈을 생성할 수도 있습니다.

```kotlin
class ProviderTest {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    fun prototype_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(PrototypeBeanClass::class.java)

        val bean = applicationContext.getBean(PrototypeBeanClass::class.java)

        val prototypeA = bean.prototypeBean
        prototypeA.increase()

        val prototypeB = bean.prototypeBean
        prototypeB.increase()

        assertThat(prototypeA).isNotSameAs(prototypeB)
        assertEquals(1, prototypeA.count)
        assertEquals(1, prototypeB.count)

        prototypeB.destroy()
        prototypeA.destroy()
        bean.destroy()
    }

    @Scope("prototype")
    class PrototypeBeanClass {

        // 스프링에 의존적인 기술
        @Autowired
        private lateinit var provider: ObjectProvider<PrototypeBeanClass>

        val prototypeBean: PrototypeBeanClass
            get() = provider.`object`

        private val log = LoggerFactory.getLogger(this::class.java)

        private var _count = 0

        val count: Int
            get() = _count

        fun increase() {
            this._count++
        }

        @PostConstruct
        fun init() {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            log.info("INIT: $this")
            log.info("---------------------------------------------------------------")
        }

        @PreDestroy
        fun destroy() {
            log.info("---------------------------------------------------------------")
            log.info("DESTROY: $this")
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        }
    }
}
```

> 의존관계를 외부에서 주입(DI) 받는 것이 아닌 직접 필요한 의존관계를 찾는 것을 Dependency Lookup (DL) 의존관계 조회(탐색)라 합니다. 그런데 이렇게 스프링의 애플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워집니다.


<br/><br/><br/><br/><br/><br/><br/><br/>

### 8-2. ObjectFactory, ObjectProvider

`ObjectProvider`는 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공합니다. prototypeBeanProvider.getObject( ) 을 통해서 `항상 새로운 프로토타입 빈이 생성`되는 것을 확인할 수 있습니다.

````kotlin
class ProviderTest {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    fun prototype_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(PrototypeBeanClass::class.java)

        val bean = applicationContext.getBean(PrototypeBeanClass::class.java)

        val prototypeA = bean.prototypeBean
        prototypeA.increase()

        val prototypeB = bean.prototypeBean
        prototypeB.increase()

        assertThat(prototypeA).isNotSameAs(prototypeB)
        assertEquals(1, prototypeA.count)
        assertEquals(1, prototypeB.count)

        prototypeB.destroy()
        prototypeA.destroy()
        bean.destroy()
    }

    @Scope("prototype")
    class PrototypeBeanClass {

        // 스프링에 의존적인 기술
        @Autowired
        private lateinit var provider: ObjectProvider<PrototypeBeanClass>

        val prototypeBean: PrototypeBeanClass
            get() = provider.`object`

        private val log = LoggerFactory.getLogger(this::class.java)

        private var _count = 0

        val count: Int
            get() = _count

        fun increase() {
            this._count++
        }

        @PostConstruct
        fun init() {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            log.info("INIT: $this")
            log.info("---------------------------------------------------------------")
        }

        @PreDestroy
        fun destroy() {
            log.info("---------------------------------------------------------------")
            log.info("DESTROY: $this")
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        }
    }
}
````

> 과거에는 ObjectFactory 가 있었는데, 여기에 편의 기능을 추가해서 ObjectProvider 가 만들어졌습니다.


&nbsp;&nbsp; - ObjectFactory: 기능이 단순, 별도의 라이브러리 필요 없음, 스프링에 의존 <br/>
&nbsp;&nbsp; - ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요 <br/>
없음, 스프링에 의존

<br/><br/><br/><br/>


<br/><br/><br/><br/><br/><br/><br/><br/>

### 8-3. JSR-330 Provider
`javax.inject.Provider`라는 JSR-330 자바 표준을 사용해 싱글톤 빈을 주입할 수 있습니다. 스프링 부트 3.0은 `jakarta.inject.Provider`를 사용합니다. 이를 사용하기 위해서는 아래 의존성을 추가해 줍니다.

````groovy
    implementation("javax.inject:javax.inject:1")
````

<br/><br/><br/><br/>

실행해 보면 provider.get( ) 을 통해 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있습니다. provider의 get() 을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환합니다.

````kotlin
class ProviderTest {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    fun prototype_bean_test() {
        val applicationContext = AnnotationConfigApplicationContext(PrototypeBeanClass::class.java)

        val bean = applicationContext.getBean(PrototypeBeanClass::class.java)

        val prototypeA = bean.prototypeBean
        prototypeA.increase()

        val prototypeB = bean.prototypeBean
        prototypeB.increase()

        assertThat(prototypeA).isNotSameAs(prototypeB)
        assertEquals(1, prototypeA.count)
        assertEquals(1, prototypeB.count)

        prototypeB.destroy()
        prototypeA.destroy()
        bean.destroy()
    }

    @Scope("prototype")
    class PrototypeBeanClass {

        // 스프링에 의존적인 기술
        @Autowired
        private lateinit var provider: ObjectProvider<PrototypeBeanClass>

        val prototypeBean: PrototypeBeanClass
            get() = provider.`object`

        private val log = LoggerFactory.getLogger(this::class.java)

        private var _count = 0

        val count: Int
            get() = _count

        fun increase() {
            this._count++
        }

        @PostConstruct
        fun init() {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            log.info("INIT: $this")
            log.info("---------------------------------------------------------------")
        }

        @PreDestroy
        fun destroy() {
            log.info("---------------------------------------------------------------")
            log.info("DESTROY: $this")
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        }
    }
}
````

<br/><br/><br/><br/><br/><br/><br/><br/>

Provider는 지금 딱 필요한 DL 정도의 기능만 제공하며 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬워집니다. 

&nbsp;&nbsp; - 별도의 라이브러리가 필요하다. <br/>
&nbsp;&nbsp; - 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다.<br/>

> 싱글톤 빈으로 대부분의 문제를 해결할 수 있기 때문에 프로토타입 빈을 직접적으로 사용하는 일은 매우 드물다. ObjectProvider , JSR330 Provider 등은 프로토타입 뿐만 아니라 DL이 필요한 경우는 언제든지 사용 할 수 있다.

<br/><br/><br/><br/><br/><br/><br/><br/>

<br/><br/><br/><br/><br/><br/><br/><br/>


## 8-5. 웹 스코프

웹 스코프는 웹 환경에서만 동작합니다. 이는 프로토타입과 다르게 스프링이 해당 스코프의 종료 시점까지 관리되며 종료 메서드가 호출됩니다.

&nbsp;&nbsp; - request: HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈
인스턴스가 생성되고, 관리된다. <br/>
&nbsp;&nbsp; - session: HTTP Session과 동일한 생명주기를 가지는 스코프<br/>
&nbsp;&nbsp; - application: 서블릿 컨텍스트(ServletContext)와 동일한 생명주기를 가지는 스코프 <br/>
&nbsp;&nbsp; - websocket: 웹 소켓과 동일한 생명주기를 가지는 스코프 <br/>


