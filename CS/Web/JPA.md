# JPA & QueryDSL

[1. JPA](#jpa란) <br/>
[1-1. JPA 적용](#jpa-적용) <br/>
[2. QueryDSL](#querydsl이란) <br/>
[2-1. QueryDSL 적용](#querydsl-적용) <br/>
[3. 내가 정리하고 싶은 용어](#용어-정리)

## JPA란
- Java ORM의 종류
- Java Persistence API(ORM 기술의 표준) : 자바에 있는 데이터를 영구히 기록할 수 있는 환경을 제공하는 API(**CRUD** 처리를 위한 공통 인터페이스 제공)
- 인터페이스이기 때문에 Hibernate, OpenJPA 등이 JPA를 구현함
- 공통 메소드는 JPA가 제공하는 **JpaRepository** 인터페이스에 delete, deleteAll, findById 등등이 있음

## JPA 적용
1) 엔티티 작성

![image](https://user-images.githubusercontent.com/69454805/137611474-51d320d1-f18f-4429-afee-8087b53bb41e.png)

2) CRUD가 가능하도록 **JpaRepository** 상속받기

![image](https://user-images.githubusercontent.com/69454805/137611626-5248a760-e57a-48b4-984e-7185f97371a4.png)

> JpaRepository와 JpaSpecificationExecutor <br/>
> Repository는 CRUD 메소드 제공, Specification은 동적인 쿼리 작성할 때 사용 <br/>
> 즉, 쿼리의 **조건**을 Spec으로 작성해 Repository 메소드에 적용할 수 있게 해줌 (밑에 예시 참고)

3) ServiceImpl에서 UserRepository 사용
 - save : 새로운 엔티티는 저장하고, 이미 있는 엔티티는 수정 <br/>
![image](https://user-images.githubusercontent.com/69454805/137612238-4d327852-bef4-4b32-86a3-9405fb99640f.png)
 - findAll : 모든 엔티티 조회(sort나 pageable 조건을 파라미터로 사용할 수도 있음) <br/>
 ![image](https://user-images.githubusercontent.com/69454805/137612270-4b137c5a-9225-4a2a-be74-9eb11b6abf63.png)
 - 2에서 정의한 함수<br/>
 ![image](https://user-images.githubusercontent.com/69454805/137612282-a58d3ae0-0360-4a5a-9f1c-073828196ed1.png)
 - delete : 엔티티 하나 삭제<br/>
 ![image](https://user-images.githubusercontent.com/69454805/137612290-a10f0e9e-9508-444f-88d2-beb604baee1b.png)

 ### JpaSpecificationExecutor 적용
1) Spec 작성
- like대신 equal도 가능
- User 객체의 userId 필드가 검색조건으로 입력받은 매개변수 userId를 포함(일치)하는지 확인하고 해당하는 User 객체를 반환
![image](https://user-images.githubusercontent.com/69454805/137616228-a588e0ba-d5fe-41d2-85a6-066f81c74fd1.png)

2) Repository 매개변수에 적용

![image](https://user-images.githubusercontent.com/69454805/137616340-d5090dc6-0c8c-4f84-8250-5060e96af407.png)


## QueryDSL이란
- **SQL을 코드**로 작성할 수 있도록 도와주는 빌더 API : 문법 오류를 **컴파일 단계**에서 잡아줄 수 있음
- JPA에서 동적쿼리를 사용하는 방법 중 하나
- type-safe하게 개발할 수 있게 해 주는 프레임워크(오픈소스 프로젝트)
- JPQL의 단점 보완

> JPQL : 객체지향 쿼리 <br/>
> SQL을 추상화한 JPA의 객체지향 쿼리 <br/>
> 테이블이 아닌 엔티티 객체를 대상으로 쿼리 <br/>
> 테이블 이름이 아닌 엔티티 이름 사용 <br/>
> 문제점 : String으로 쿼리 작성 -> 로직 시행 전까지 작동여부 확인불가(**실행 시점**에서 오류 발견)
![image](https://user-images.githubusercontent.com/69454805/137617293-24e780e6-000c-4448-8d0e-6796c8ed9c0d.png)

## QueryDSL 적용
1) Q클래스 생성
- '@Entity' 어노테이션을 읽어 필요한 Q클래스 파일 만듦
- Q클래스와 같이 static한 클래스를 만들게 되면, 객체를 생성할 필요없이 static이므로 property에 바로 접근하여 사용할 수 있게 됨 -> 해당 Entity 클래스의 property에 바로 접근 가능
![image](https://user-images.githubusercontent.com/69454805/137618013-d111cc88-6fbc-4a06-b606-14c6b503a257.png)
![image](https://user-images.githubusercontent.com/69454805/137618026-0c3ee5da-acbc-4f90-9001-ad15f8429032.png)

2) QueryDSL Repository 생성
- fetchOne vs fetch : fetchOne은 단건 조회시 사용, fetch는 조회 대상이 여러 건(컬렉션 list로 반환)
- optional : java.util의 클래스로, optional 객체 사용 시 예상치 못한 **NullPointerException** 예외를 제공되는 메소드로 간단히 회피할 수 있음 -> 복잡한 조건문 없이도 null값으로 인해 발생하는 예외 처리 가능
    - null이 될 가능성이 있다면 ofNullable 메소드 사용 : 명시된 값이 null이 아니면 명시된 값을 가지는 Optional 객체를 반환하며, 명시된 값이 null이라면 비어있는 Optional 객체 반환
    - empty : 아무런 값도 가지지 않는 비어있는 Optional 객체 반환

![image](https://user-images.githubusercontent.com/69454805/137618082-eba0af9a-efe5-48d5-b42e-edd5b4e47b46.png)

3) ServiceImpl에서 사용

 ![image](https://user-images.githubusercontent.com/69454805/137612282-a58d3ae0-0360-4a5a-9f1c-073828196ed1.png)

 ## 용어 정리
1) CORS(Cross-Origin Resource Sharing)
- 특정 헤더를 통해서 브라우저에게 한 출처(origin, URL의 프로토콜/도메인/포트)에서 실행되고 있는 웹 애플리케이션이 다른 출처(cross-origin)에 원하는 리소스를 접근할 수 있는 권한이 있는지 없는지 알려주는 매커니즘 : **웹페이지의 제한된 자원을 외부 도메인에서 접근을 허용해주는 메커니즘**
- @CrosOrigin 어노테이션으로 CORS 설정 가능
2) 크로스사이트스크립트(XSS)
- 웹 애플리케이션에서 일어나는 취약점으로 관리자 아닌 권한이 없는 사용자가 웹사이트에 **스크립트를 삽입**하는 공격 기법
![image](https://user-images.githubusercontent.com/69454805/137620081-b3d081b3-cdce-48f0-b70f-0b6e2e163019.png)
- 공격 종류
    - Reflected XSS : 취약점이 존재하는 페이지를 미리 탐색한 후, 스크립트가 포함된 URL을 공격 대상자에게 노출시키는 방법으로 공격 수행 -> 사용자가 URL을 클릭할 경우, 스크립트가 포함된 URL을 통해 Request를 전송하고, 스크립트를 포함한 Response 전송
![image](https://user-images.githubusercontent.com/69454805/137620249-8c5b6593-58fa-437c-b673-a134b3553842.png)
    - Stored XSS : 게시판에 스크립트를 삽입하는 공격 방식 -> 공격자는 게시판에 스크립트를 삽입한 후 공격 대상자가 해당 게시글을 클릭하도록 유도함 -> 게시글의 URL을 사용자에게 노출하고 게시글을 확인함으로써 공격 수행
![image](https://user-images.githubusercontent.com/69454805/137620287-30fdc017-0f05-4a57-bf82-61723fe80b12.png)
    - DOM Based XSS : 악의적인 스크립트가 포함된 URL을 사용자가 요청하게 되어 브라우저를 해석하는 단계에서 발생하는 공격(서버 측에서 탐지 어려움) -> 클라이언트 측 코드가 원래 의도와 다르게 실행
![image](https://user-images.githubusercontent.com/69454805/137620349-12a3c2e4-7c9b-4840-a182-64d2d77026ef.png)
- 대응 방안
    - 방화벽 사용
    - 입출력 값 검증
    - XSS 방어 라이브러리 사용
3) SSO(Single Sign On)
- 여러 개의 사이트에서 한 번의 로그인으로 여러가지 다른 사이트들을 자동적으로 접속하여 이용하는 방법 : 중앙화된 세션 및 사용자 인증 서비스
- 하나의 사용자 정보를 기반으로 여러 시스템을 하나의 통합 인증을 사용하게 하는 것
4) SQLInjection
- 보안상의 취약점을 이용해, 임의의 sql문을 주입하고 실행하게 하여 데이터베이스가 비정상적인 동작을 하도록 조작하는 행위(공격자가 원하는 DB의 중요한 정보를 가져오는 해킹 기법)
- 공격 종류
    - Error based : 'OR 1=1 --'로 WHERE절을 모두 참으로 만들고, 뒤 구문을 모두 주석 처리해 테이블에 있는 모든 정보 조회 가능
![image](https://user-images.githubusercontent.com/69454805/137620670-1aeab244-bdf1-4bee-b36e-5c3ea332798d.png)
    - Union based : union 키워드를 통해 결과를 통합해 하나의 테이블을 보여주게 함(컬럼수와 데이터형이 같아야 한다는 조건)
![image](https://user-images.githubusercontent.com/69454805/137620770-1b4661bd-9041-4901-96e1-17333fae6a66.png)    
- 대응 방안
    - 입력값 검증
    - PreparedStatement 사용
    - 방화벽 사용
5) preparedStatement와 statement 차이
- Statement : 1. 단일로 사용될 때 빠른 속도를 지닙니다. / 2. 쿼리에 인자를 부여할 수 없습니다. / 3. 매번 컴파일을 수행해야 합니다.
![image](https://user-images.githubusercontent.com/69454805/137620898-1ced50f9-71fd-4bb4-94a8-e1d288eafb3e.png)
- PreparedStatement : 1. 쿼리에 인자를 부여할 수 있습니다. / 2. 처음 프리컴파일 된 후, 이후에는 컴파일을 수행하지 않습니다. / 3. 여러번 수행될 때 빠른 속도를 지닙니다.
![image](https://user-images.githubusercontent.com/69454805/137620906-ac4a20b0-b565-4fe5-a590-7847800b93e3.png)
- 동일한 쿼리를 반복적으로 수행할 때 pstmt가 캐시에 담아 재사용하기 때문에 속도면에서 좋은 것으로 알고 있습니다. 보안적인 측면에서 statement는 사용자의 입력값에 따라 쿼리문의 형태가 바뀔 수 있어 보안에 취약합니다. 하지만 pstmt는 ?로 처리한 부분에 입력값을 넣기 때문에 쿼리문의 형태가 바뀌지않아 보안성을 높일 수 있습니다.

[Reference]
- https://4rgos.tistory.com/1
- https://noirstar.tistory.com/266
- https://noirstar.tistory.com/264