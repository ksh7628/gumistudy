# Spring Architecture DI

## 목차

[1. Spring](#1-spring)<br>
[2. IoC & Container](#2-ioc--container)<br>
[3. 의존성 주입 (DI)](#3-의존성-주입-di)

<hr>

## 1. Spring

### Spring Framework 란?

자바 플랫폼을 위한 오픈소스 애플리케이션 프레임워크이다.<br>
엔터프라이즈(매우 큰 규모의 환경) 급 애플리케이션을 만들기 위한 모든 기능을 종합적으로 제공하는 경량화 된 솔루션이다.<br>
객체의 생성 및 소멸, 라이프 사이클을 관리하며 언제든 Spring 컨테이너로부터 필요한 객체를 가져와서 사용가능하다.

### Spring 삼각형

![Spring-Triangle](https://media.vlpt.us/images/ljinsk3/post/a7b18214-c03e-4816-9b78-5f93f3d52489/image.png)<br>
스프링의 핵심 기능(PSA, IoC, AOP)를 스프링 삼각형이라고 한다.<br>
스프링은 POJO(Plain Old Java Object)기반으로 모든 기능을 작성할 수 있다. POJO란 특정 환경이나 기술에 종속적이지 않은 객체지향 원리에 충실한 자바객체로, 테스트하기 용이하며, 객체지향 설계를 자유롭게 적용할 수 있는 특징이 있다.

- PSA(Portable Service Abstraction)는 환경과 세부기술의 변경과 관계없이 일관된 방식으로 기술에 접근할 수 있게 해주는 설계원칙이다. ex) Spring Trasaction Manager, Spring Cache Manager
- IoC(Inversion of Control) / DI(Dependency Injection)는 의존성 주입 기능을 의미한다. 유연하게 확장 가능한 객체를 만들어 두고 객체 간의 의존관계는 스프링이 직접 주입해주기 때문에 객체간 결합도(의존도)를 낮출 수 있다.
- AOP(Aspect Oriented Programming)는 비즈니스 로직과 관계가 적은 트랜잭션, 로깅, 예외처리 등의 분리를 통해서 소프트웨어의 모듈성을 향상시키고 공통 모듈을 여러 코드에 쉽게 적용이 가능한 프로그래밍 방법이다.

### Spring Framework의 특징

- 경량화 된 컨테이너로써 자바 객체를 담고 직접 관리
- POJO 지원
- IoC, DI, AOP 지원
- 트랜잭션 관리 프레임워크
- MVC 패턴
- 다양한 API에 대한 연동 지원

### Spring Framework Module

![Spring-Framework-Module](https://t1.daumcdn.net/cfile/tistory/99A523405B9E2C1510)

- Spring Core : 스프링 프레임워크의 핵심 기능을 제공 하며, BeanFactory 기반의 Bean 클래스들을 제어할 수 있는 기능 지원
- Spring Context : Spring Core에서 지원하는 기능 외 추가적인 기능과 좀 더 쉬운 개발이 가능하도록 지원
- Spring AOP : 설정 관리 기능을 통해 AOP 기능을 Spring Framework와 직접 통합
- Spring DAO : JDBC 기반하의 DAO 개발을 좀 더 쉽고, 일관된 방법으로 개발하는 것이 가능하도록 지원
- Spring ORM : 여러 ORM(Object Relation Mapping) Framework에 플러그인 되어 ORM 프레임워크(JDO, Hibernate, iBatis)와 쉽게 통합 가능
- Spring Web : Web Application 개발에 필요한 Web Application Context, Multipart Request 등의 기능을 지원하고 Struts, Webwork와 같은 프레임워크의 통합을 지원하는 부분 담당
- Spring Web MVC : 스프링 프레임워크에서 자체적으로 MVC 프레임워크를 지원하고 있으며, Velocity, Excel, PDF와 같은 다양한 UI 기술들을 사용하기 위한 API 제공

## 2. IoC & Container

### IoC(Inversion of Control, 제어의 역행)

![IoC-Container](https://t1.daumcdn.net/cfile/tistory/2434D33454E702B835)

- DL(Dependency Lookup) : JNDI(Java Naming and Directory Interface) 같은 저장소에 의하여 관리되고 있는 bean을 개발자들이 직접 컨테이너에서 제공하는 API를 이용하여 lookup하는 것을 말한다.
- DI(Dependency Injection) : 각 클래스 사이에 필요로 하는 의존관계를 bean 설정정보를 바탕으로 컨테이너가 자동으로 연결해주는 것을 말한다.

### IoC 개념

객체 간의 결합도를 떨어트릴수 있다. 만일 객체간 결합도가 높다면 해당 클래스가 유지보수 될 때 그 클래스와 결합된 다른 클래스도 같이 유지보수 되어야 할 가능성이 높다.

### Container

- Container란? : 객체의 라이프사이클을 담당하고 라이프사이클을 기본으로 애플리케이션 사용에 필요한 주요 기능을 제공한다.
- Container 기능 : 라이프사이클 관리, Dependency 객체 제공, Thread 관리, 기타 애플리케이션 실행에 필요한 환경 등을 제공한다.
- Container의 필요성 : 비즈니스 로직 외에 부가적인 기능들에 대해서는 독립적으로 관리되도록 하고 서비스 look up 이나 Configuration에 대한 일관성을 갖게 하며 서비스 객체를 사용하기 위해 각각 Factory 또는 싱글톤 패턴을 직접 구현하지 않아도 된다.

### IoC Container

오브젝트의 생성, 관계설정, 사용, 제거 등의 작업을 애플리케이션 코드 대신 독립된 컨테이너가 담당한다. 빈 팩토리(Bean Factory), 애플리케이션 컨텍스트(Application Context) 라고도 불리며 스프링의 컨테이너를 IoC 컨테이너라고 부르기도 한다.

### Spring DI Container

Spring DI Container가 관리하는 객체를 빈(Bean)이라 하고, 이 빈들의 라이프사이클을 관리하는 의미로 빈팩토리(BeanFactory)라 한다. BeanFactory에 여러 가지 컨테이너 기능을 추가하여 Application Context라 한다.(상속 관계)

![BeanFactory-and-Application-Context](https://lh4.googleusercontent.com/L503ZbZNJl6u1HFDyJf-ZQtthPpNOfvm97eg7_ufQHOrwgPqkbnADOqJKv3rx2IFE06XI3fLFZH1I-6_rauWmmO9YNfG8GbgnghTIyQ_EnnXMyxJdbOulDJRUbBbMxGga0Dav8c2)
<br><br>
pom.xml

```xml
...
<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.6.RELEASE</version>
</dependency>
...
```

<br>applicationContext.xml

```xml
...
<bean id="boil" class="BoilService" scope="singleton"></bean>
<bean id="fry" class="FryService" scope="singleton"></bean>
...
```

applicationContext.xml에 bean 등록(scope를 명시해주지 않을 경우 스프링 빈은 기본적으로 싱글톤 scope를 가짐)<br><br>

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;// pom.xml에 추가된 Spring Context를 import

class CookService {}
class BoilService extends CookService {
    BoilService() {
        System.out.println("BoilService 객체 생성 완료!!");
    }
}
class FryService extends CookService {
    FryService() {
        System.out.println("FryService 객체 생성 완료!!");
    }
}

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext();// 어플리케이션 객체 생성
        CookService boilSvc = context.getBean("boil", BoilService.class);// boil라는 id를 가진 bean 객체를 BoilService 타입으로 만들어라.
        CookService frySvc = context.getBean("fry", FryService.class);// fry라는 id를 가진 bean 객체를 FryService 타입으로 만들어라.
    }
}
```

각 Service의 라이프사이클을 관리하는 Spring Container를 사용하여 객체 간의 강한 결합을 Assembler(Spring Container)를 통해 결합도를 낮춘다.<br><br>

출력 결과

```
BoilService 객체 생성 완료!!
FryService 객체 생성 완료!!
```

### Spring DI 용어 정리

- 빈(Bean) : 스프링이 IoC 방식으로 관리하는 오브젝트를 말하는 것으로 직접 생성과 제어를 담당하는 오브젝트만을 Bean이라고 한다.
- 빈 팩토리(BeanFactory) : 스프링이 IoC를 담당하는 핵심 컨테이너로 Bean을 등록, 생성, 조회, 반환하는 기능을 담당하며 일반적으로 BeanFactory를 바로 사용하지 않고 ApplicationContext를 이용한다.
- 애플리케이션 컨텍스트(ApplicationContext) : BeanFactory를 확장한 IoC 컨테이너로 기본적인 기능은 BeanFactory와 동일하지만 스프링이 제공하는 각종 부가 서비스를 추가로 제공한다.
- 설정정보/설정 메타정보(configuration metadata) : 스프링의 설정정보란 ApplicationContext 또는 BeanFactory가 IoC를 적용하기 위해 사용하는 메타정보를 말하는 것으로 설정정보는 IoC 컨테이너에 의해 관리되는 Bean 객체를 생성하고 구성할 때 사용된다.
- 스프링 프레임워크 : IoC 컨테이너, ApplicationContext를 포함해서 스프링이 제공하는 모든 기능을 통틀어 말할 때 사용한다.

## 3. 의존성 주입 (DI)

### 빈 생성범위

- 싱글톤 빈(Singleton Bean) : 스프링 빈은 default로 싱글톤으로 만들어지나 항상 새로운 인스턴스를 반환하게 만들고 싶은 경우 scope를 prototype으로 설정해야 한다.
- 프로토타입 빈(Prototype Bean) : 컨테이너에 빈을 요청할 때마다 새로운 인스턴스를 생성한다.
- 리퀘스트 빈(Request Bean) : HTTP Request별로 새로운 인스턴스를 생성한다.
- 세션 빈(Session Bean) : HTTP Session별로 새로운 인스턴스를 생성한다.

### XML

XML은 단순하며 사용하기 쉽고 가장 많이 사용하는 방식으로 XML문서 형태로 빈의 설정 메타 정보를 기술한다. \<bean>태그를 통해 세밀한 제어가 가능하다.

```xml
...
<bean id="boil" class="BoilService" scope="prototype">
    <property name="boilDao" ref="boilDao" />
</bean>
<bean id="fry" class="FryService"></bean>
...
```

### Annotation

어플리케이션의 규모가 커지고 빈의 개수가 계속 늘어날 경우 XML 파일을 관리하는 것이 번거로운데 빈으로 사용될 클래스에 @Autowired 라는 annotation을 통해 자동으로 빈 등록이 가능하다.(빈 스캐닝)

```java
@Component
public class BoilServiceImpl implements BoilService {
    @Autowired
    private BoilDao boilDao;

    @Override
    public int useBoil(BoilDto boilDto) {
        return boilDao.useBoil(boilDto);
    }
}
```

<br>
Annotation으로 빈을 설정 할 경우 반드시 component-scan을 설정 해야 한다.

```xml
...
<context:component-scan base-package="test.mytest.*" />
...
```

<br>

### Stereotype annotation 종류

빈 자동등록에 사용할 수 있는 annotation으로 여러가지인 이유는 계층별로 빈의 특성이나 종류를 구분하고 특정 계층의 빈에 부가기능을 부여하며 AOP Pointcut 표현식을 사용하면 특정 annotation이 달린 클래스만 설정이 가능하다.

- @Repository : Data Access Layer의 DAO나 Repository 클래스에 사용하며 AOP의 적용 대상을 선정하기 위해 사용한다.
- @Service : Service Layer의 클래스에 사용한다.
- @Controller : Presentation Layer의 MVC Controller에 사용하며 Spring Web Servlet에 의해 웹 요청을 처리하는 컨트롤러 빈으로 설정한다.
- @Component : 기타 Layer 구분을 적용하기 어려운 일반적인 경우에 설정한다.

### Spring Bean 의존 관계설정

### 1. Constructor

객체 또는 값을 생성자를 통해 주입하며 \<constructor-arg> : \<bean>의 하위태그로 설정한 bean 객체 또는 값을 생성자를 통해 주입하도록 설정한다.<br><br>
Person.java

```java
public class Person {
    private String name;
    private int age;
    private String address;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
```

applicationContext.xml

```xml
...
<bean id="person1" name="p1" class="Person">
    <constructor-arg value="홍길동" />
    <constructor-arg value="30" />
</bean>
<bean id="person2" class="Person">
    <constructor-arg value="강호동" />
    <constructor-arg value="20" />
    <constructor-arg name="address" value="서울시 강남구" />
</bean>
...
```

<br>

### 2. Property

```java
private PersonDao personDao;

public void setPersonDao(PersonDao personDao) {
    this.personDao = personDao;
}
```

```xml
<bean id="dao" class="PersonDao">
<bean id="person" class="Person">
    <property name="age" value="11" ref="dao"/> property를 이용해서 DI를 한다.
</bean>
```

### 3. Collection

<br>TestMap.java

```java
...
private Map map;

public void setMap(Map map) {
    this.map = map;
}
...
```

```xml
<bean id="person" class="Person" />
<bean id="tmap" class="TestMap">
    <property name="myMap">
        <map>
            <entry key="myname" value="KSH">
            <entry key="ps" value-ref="person">
        </map>
    </property>
</bean>
```

### 4. Annotation

@Resource(Spring 2.5 이상) : 멤버 변수, setter 메서드<br>
@Autowired(Spring 2.5 이상) : 멤버 변수, setter 메서드, 생성자, 일반 메서드<br>
@Inject(Spring 3.0 이상, 추가 라이브러리 필요) : 멤버 변수, setter 메서드, 생성자, 일반 메서드<br>

AnimalServiceImpl.java

```java
...
@Resource
private BirdDao birdDao;
@Autowired
private CatDao catDao;
@Inject
private DogDao dogDao;

@Autowired
public PersonServiceImpl(@Qualifier("bdao") BirdDao birdDao,
                         @Qualifier("cdao") CatDao catDao,
                         @Qualifier("ddao") DogDao dogDao) {
    super();
    this.birdDao = birdDao;
    this.catDao = catDao;
    this.dogDao = dogDao;
}
...
```

<br>

### Spring Bean 생명 주기

![Spring-Bean-Life-Cycle](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdmnhXN%2FbtqX32t3MjC%2FqPbnLLuhie6k90Ka1zo72K%2Fimg.jpg)

빈 생성 -> 의존성 주입 -> init method 실행 -> 빈 사용 -> destroy method 실행 -> 빈 소멸

## Reference

- https://khj93.tistory.com/entry/Spring-Spring-Framework%EB%9E%80-%EA%B8%B0%EB%B3%B8-%EA%B0%9C%EB%85%90-%ED%95%B5%EC%8B%AC-%EC%A0%95%EB%A6%AC
- https://velog.io/@ljinsk3/Spring%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0
- https://goddaehee.tistory.com/156
- https://js2prince.tistory.com/entry/Spring-IOC-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EB%9E%80
- http://jinioh88.blogspot.com/2018/08/spring-di-1.html
- https://feco.tistory.com/12
- https://haruhiism.tistory.com/186
