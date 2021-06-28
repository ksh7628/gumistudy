# MyBatis

## 목차

[1. ORM 프레임워크](#1-orm-프레임워크)<br>
[2. MyBatis](#2-mybatis)<br>
[3. MyBatis와 Spring 연동하기](#3-mybatis와-spring-연동하기)

<hr>

## 1. ORM 프레임워크

### ORM(Object Relational Mapping)이란?
객체와 관계형 데이터베이스의 데이터를 자동으로 매핑해주는 것
- 객체 지향 프로그래밍은 __클래스__ 사용, 관계형 데이터베이스는 __테이블__ 사용
- 객체 모델과 관계형 모델 간 불일치가 존재
- ORM을 통해 객체 간의 관계를 바탕으로 SQL을 자동으로 생성하여 불일치 해결

### ORM 장단점
▶ 장점
 - 객체 지향적인 코드로 더 직관적이고 비즈니스 로직에 더 집중할 수 있게 도와줌
    + ORM을 이용하면 SQL Query가 아닌 직관적인 코드(메서드)로 데이터를 조작할 수 있어 개발자가 객체 모델로 프로그래밍하는 데 집중할 수 있도록 도와준다.
    + 선언문, 할당, 종료 같은 부수적인 코드가 없거나 급격히 줄어든다.
    + 각종 객체에 대한 코드를 별도로 작성하기 때문에 코드의 가독성을 올려준다.
    + SQL의 절차적이고 순차적인 접근이 아닌 객체 지향적인 접근으로 인해 생산성이 증가한다.

<br>

 - 재사용 및 유지보수의 편리성 증가
    + ORM은 독립적으로 작성되어있고, 해당 객체들을 재활용 할 수 있다.
    + 때문에 모델에서 가공된 데이터를 컨트롤러에 의해 뷰와 합쳐지는 형태로 디자인 패턴을 견고하게 다지는데 유리하다.
    + 매핑정보가 명확하여, ERD를 보는 것에 대한 의존도를 낮출 수 있다.

<br>

 - DBMS에 대한 종속성이 줄어듦
    + 객체 간의 관계를 바탕으로 SQL을 자동으로 생성하기 때문에 RDBMS의 데이터 구조와 Java의 객체지향 모델 사이의 간격을 좁힐 수 있다.
    + 대부분 ORM 솔루션은 DB에 종속적이지 않다.
    + 종속적이지 않다는것은 구현 방법 뿐만아니라 많은 솔루션에서 자료형 타입까지 유효하다.
    + 프로그래머는 Object에 집중함으로 극단적으로 DBMS를 교체하는 거대한 작업에도 비교적 적은 리스크와 시간이 소요된다.
    + 또한 자바에서 가공할경우 equals, hashCode의 오버라이드 같은 자바의 기능을 이용할 수 있고, 간결하고 빠른 가공이 가능하다.


▶ 단점
 - 완벽한 ORM 으로만 서비스를 구현하기가 어려움
    + 사용하기는 편하지만 설계는 매우 신중하게 해야한다.
    + 프로젝트의 복잡성이 커질경우 난이도 또한 올라갈 수 있다.
    + 잘못 구현된 경우에 속도 저하 및 심각할 경우 일관성이 무너지는 문제점이 생길 수 있다.
    + 일부 자주 사용되는 대형 쿼리는 속도를 위해 SP를 쓰는등 별도의 튜닝이 필요한 경우가 있다.

<br>

 - 프로시저가 많은 시스템에선 ORM의 객체 지향적인 장점을 활용하기 어려움<br>

### Java ORM 종류
- JPA
- Hibernate
- MyBatis

### JPA, MyBatis 간단 비교
- JPA 장점
  + SQL 명령을 구현하지 않아도 되기 때문에, DBMS 제품을 교체하더라도 소스코드를 수정할 필요가 없음
  + 자동으로 처리되는 부분이 많아서, 구현할 소스코드의 양이 상대적으로 적음
  + 계형 데이터베이스가 아니더라도 적용할 수 있음

<br>

- JPA 단점
  + 복잡한 조회 명령을 구현해야 할 때, 익숙한 SQL 문장으로 구현할 수가 없고 JPA의 고급기능을 공부해야 함

<br>

- MyBatis 장점
  + 익숙한 SQL 명령으로 구현할 수 있기 때문에 고급기능을 따로 공부하지 않아도 됨
  + 조회 결과를 복잡한 객체 구조로 변환해야 할 때 myBatis의 ResultMap 기능을 이용할 수 있어서 편리함

<br>

- MyBatis 단점
  + 구현할 소스코드의 양이 상대적으로 많고, 관계형 데이터베이스에만 적용할 수 있음
  + DBMS 제품을 교체하면 소스코드에서 SQL 문장을 수정해야 함

<br>

<hr>

## 2. MyBatis

### MyBatis 란?
Java Object와 SQL문 사이의 자동 매핑 기능을 지원하는 ORM Framwork<br>

개요
- SQL을 별도의 파일로 분리해서 관리
- Object - SQL 사이의 parameter mapping 작업을 자동으로 해줌
- MyBatis는 Hibernate나 JPA처럼 새로운 DB 프로그래밍 패러다임을 익혀야 하는 부담이 없이, 개발자가 익숙한 SQL을 그대로 이용하면서 JDBC 코드 작성의 불편함을 제거, 도메인 객체나 VO(Value Object.. Dto?) 객체를 중심으로 개발이 가능

특징
- 쉬운 접근성과 코드의 간결함
  + 가장 간단한 persistence framework
  + XML형태로 서술된 JDBC 코드라 생각해도 될 만큼 JDBC의 모든 기능을 대부분 제공
  + 복잡한 JDBC 코드를 걷어내며 깔끔한 소스코드를 유지
  + 수동적인 parameter설정과 Query 결과에 대한 mapping 구문을 제거

  <br>

- SQL문과 프로그래밍 코드의 분리
  + SQL에 변경이 있을 때마다 자바 코드를 수정하거나 컴파일 하지 않아도 됨
  + SQL 작성과 관리 또는 검토를 DBA와 같은 개발자가 아닌 다른 사람에게 맡길 수 있음

  <br>

- 다양한 프로그래밍 언어로 구현 가능
  + Java, C#, .NET, Ruby ...

### MyBatis와 MyBatis-Spring의 주요 Component

- MyBatis와 MyBatis-Spring을 사용한 DB Access Architecture<br>
![MyBatis-DBA-ccess-Architecture](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fq7AcY%2FbtqGVwciHUU%2F3kQ4N8rJKGMcxLBG5dbB90%2Fimg.png)

  JDBC만을 사용한다면 Application Modules에서 Mapper 단계를 생략하고 바로 JDBC Interface를 호출하게 됨<br>
  따라서 Mapper 단계는 JDBC API를 감싸서 개발자가 편리하도록 매핑해주는 기능이다.

- MyBatis와 MyBatis-Spring을 사용한 Data Access Layer<br>
![MyBatis-Data-Access-Layer](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdG8VsY%2FbtqGTnGZ5L8%2FWrxVQDxQjJeC9adZfXIQ41%2Fimg.png)

  Data Access Layer에서 MyBatis 사용

- MyBatis의 주요 Component<br>
![MyBatis-Component](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fkxm3y%2FbtqG9AmoINy%2FHJ1FY97boFffhwBkUjYQr0%2Fimg.jpg)


|파일|설명|
|---|---|
|MyBatis 설정파일<br>(sqlMapConfig.xml)|데이터베이스의 접속 주소 정보나 Mapping 파일의 경로 등의 고정된 환경정보를 설정|
|SqlSession FactoryBuilder|Mybatis 설정파일을 바탕으로 SqlSessionFactory를 생성|
|SqlSessionFactory|SqlSession을 생성|
|SqlSession|핵심적인 역할을 하는 클래스로서 SQL실행이나 트랜잭션 관리를 실행<br>SqlSession 오브젝트는 Thread-Safe하지 않으므로 thread마다 필요에 따라 생성|
|mapping 파일<br>(member.xml)|SQL문 작성|


- MyBatis-Spring의 주요 Component<br>
![MyBatis-Spring-Component](https://media.vlpt.us/images/changyeonyoo/post/3d9362bb-f4ec-49f0-9cfe-393aa59359d5/9919C9455C5D2DAF09.png)

|파일|설명|
|---|---|
|MyBatis 설정파일<br>(SqlMapConfig.xml)|Dto 객체의 정보를 설정|
|SqlSessionFactoryBean|MyBatis 설정 파일을 바탕으로 SqlSessionFactory를 생성<br>Spring Bean으로 등록해야 함|
|SqlSessionTemplate|핵심적인 역할을 하는 클래스로서 SQL 실행, Transaction 관리를 실행<br>SqlSession interface를 구현하며 Thread-safe 함<br>Spring Bean으로 등록해야 함|
|mapping 파일<br>(member.xml)|SQL문 작성|
|Spring Bean 설정파일<br>(beans.xml)|SqlSessionFactoryBean을 Bean 등록할 때 DataSource 정보와 MyBatis Config 파일정보, Mapping 파일의 정보를 함께 설정<br>SqlSessionTemplate을 Bean으로 등록|

<br>
<hr>

## 3. MyBatis와 Spring 연동하기
#### 1. 개요
- MyBatis 를 Standalone 형태로 사용하는 경우, SqlSessionFactory 객체를 직접 사용
- 스프링을 사용하는 경우, 스프링 컨테이너에 MyBatis 관련 빈을 등록하여 MyBatis를 사용
- 또한 스프링에서 제공하는 트랜잭션 기능을 사용하면 손쉽게 트랜잭션 처리
- MyBatis를 스프링과 연동하기 위해서는 MyBatis에서 제공하는 Spring 연동 라이브러리가 필요

Maven인 경우 다음과 같이 스프링 연동 라이브러리 의존관계 설정
```
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>2.0.3</version>
</dependency>
```

#### 2. DataSource 설정
- 스프링을 사용하는 경우, 스프링에서 데이터 소스를 관리하므로 MyBatis 설정 파일에서는 일부 설정을 생략
- 스프링 환경 설정파일(application-context.xml)에 데이터소스를 설정
- 데이터소스는 dataSource 아이디를 가진 빈으로 데이터베이스 연결정보를 가진 객체
- MyBatis와 스프링을 연동하면 데이터베이스 설정과 트랜잭션 처리는 스프링에서 관리

```
<bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
  <property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
  <property name="url" value="jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC&amp;useUniCode=yes&amp;characterEncoding=UTF-8"/>
  <property name="username" value="ssafy"/>
  <property name="password" value="ssafy"/>
</bean>
```

#### 3. 트랜잭션 관리자 설정
- transactionManager 아이디를 가진 빈은 트랜잭션을 관리하는 객체
- MyBatis는 JDBC를 그대로 사용하기 때문에 DataSourceTransactionManager 타입의 빈을 사용
- tx:annotation-driven 요소는 트랜잭션 관리방법을 어노테이션으로 선언하도록 설정
- 스프링은 메소드나 클래스에 @Transactional이 선언되어 있으면, AOP를 통해 트랜잭션을 처리

트랜잭션 관리자 설정
```
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <property name="dataSource" ref="dataSource" />
</bean>
```

어노테이션 기반 트랜잭션 설정
```
<tx:annotation-driven transaction-manager="transactionManager"/>
```

#### 4. SqlSessionFactoryBean 설정
- MyBatis 애플리케이션은 SqlSessionFactory를 중심으로 수행
- 스프링에서 SqlSessionFactory 객체를 생성하기 위해서는 SqlSessionFactoryBean을 빈으로 등록해야 함
- SqlSessionFactoryBean을 빈으로 등록할 때, 사용할 데이터소스와 mybatis 설정파일 정보가 필요

SqlSessionFactoryBean 설정
```
<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"/>
  <property name="configLocation" value="classpath:com/cafe/config/mybatis/mybatis-config.xml" />
  <property name="mapperLocations"> // 여기는 생략하고 다음 단계의 내용으로도 가능
    <list>
      <value> ... </value>
      <value> ... </value>
      <value> ... </value>
    </list>
  </property>
</bean>
```

#### 5. mapper 빈 등록
- Mapper 인터페이스를 사용하기 위해서 스캐너를 사용하여 자동으로 등록하거나, 직접 빈으로 등록
- mapperScannerConfigurer을 설정하면, Mapper 인터페이스를 자동으로 검색하여 빈으로 등록
  + basePackage로 패키지를 설정하면, 해당 패키지 하위의 모든 매퍼 인터페이스가 자동으로 등록
- MapperFactoryBean 클래스는 매퍼 인터페이스를 직접 등록할 때 사용

[방법1] mapper scanner 사용
```
<bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
  <property name="basePackage" value="com.ssafy.edu.mybatis.mapper" />
</bean>
```


[방법2] mapper interface 직접 등록
```
<bean id="authorMapper" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
  <property name="mapperInterface" value="com.ssafy.edu.mybatis.mapper.AuthorMapper" />
  <property name="sqlSessionFactory" ref="sqlSessionFactory" />
<bean>
```

#### 6. 데이터 접근 객체 구현
- 데이터 접근 객체는 특정 기술을 사용하여 데이터 저장소에 접근하는 방식을 구현한 객체
- @Repository 는 데이터 접근 객체를 빈으로 등록하기 위해 사용하는 스프링에서 제공하는 어노테이션
- @Autowired 어노테이션을 통해, 사용하려는 Mapper 인터페이스를 데이터 접근 객체와 의존관계를 설정

<hr>

## Reference
- https://gmlwjd9405.github.io/2019/02/01/orm.html
- https://devlog-wjdrbs96.tistory.com/200
- https://velog.io/@changyeonyoo/Mybatis%EB%9E%80-%EC%9E%A5%EC%A0%90-%ED%8A%B9%EC%A7%95-%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8