# 1.REST의 개념
> REST(Representational State Transfer)  
> 
> HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미한다.

<br/>
<br/>

# 2. REST의 장단점

- 장점
  - HTTP 프로토콜의 인프라를 그대로 사용하므로 REST API 사용을 위한 별도의 인프라를 구출할 필요가 없다.
  - HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능하다.
  - Hypermedia API의 기본을 충실히 지키면서 범용성을 보장한다.
  - REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도하는 바를 쉽게 파악할 수 있다.
  - 서버와 클라이언트의 역할을 명확하게 분리한다.

- 단점
  - 표준이 존재하지 않는다.
  - 사용할 수 있는 메소드가 4가지 밖에 없다.
  - 브라우저를 통해 테스트할 일이 많은 서비스라면 쉽게 고칠 수 있는 URL보다 Header 값이 왠지 더 어렵게 느껴진다.
  - 구형 브라우저가 아직 제대로 지원해주지 못하는 부분이 존재한다.

<br/>
<br/>

# 3. REST의 구성

```
1. 자원(Resource): URI
  모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재한다.
  자원을 구별하는 ID는 ‘/groups/:group_id’와 같은 HTTP URI다.
  Client는 URI를 이용해서 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청한다.

2. 행위(Verb): HTTP Method
  HTTP 프로토콜의 Method를 사용한다.
  HTTP 프로토콜은 GET, POST, PUT, DELETE 와 같은 메서드를 제공한다.

3. 표현(Representation of Resource)
  Client가 자원의 상태(정보)에 대한 조작을 요청하면 Server는 이에 적절한 응답(Representation)을 보낸다.
  REST에서 하나의 자원은 JSON, XML, TEXT, RSS 등 여러 형태의 Representation으로 나타내어 질 수 있다.
  JSON 혹은 XML를 통해 데이터를 주고 받는 것이 일반적이다.
```

<br/>

>Q) URI 과 URL의 차이점은?   
URL은 Uniform Resource Locator로 인터넷 상 자원의 위치를 의미합니다. 자원의 위치라는 것은 결국 어떤 파일의 위치를 의미합니다. 반면에 URI는 Uniform Resource Identifier로 인터넷 상의 자원을 식별하기 위한 문자열의 구성으로, URI는 URL을 포함하게 됩니다. 그러므로 URI가 보다 포괄적인 범위라고 할 수 있습니다.


<br/>
<br/>

# 4. REST 특징

``` javascript
  1. Uniform Interface(일관된 인터페이스)
  Uniform Interface란, Resource(URI)에 대한 요청을 통일되고, 한정적으로 수행하는 아키텍처 스타일을 의미합니다.
  이것은 요청을 하는 Client가 플랫폼(Android, Ios, Jsp 등) 에 무관하며, 특정 언어나 기술에 종속받지 않는 특징을 의미합니다.
  이러한 특징 덕분에 Rest API는 HTTP를 사용하는 모든 플랫폼에서 요청가능하며, Loosely Coupling(느슨한 결함) 형태를 갖게 되었습니다.
 
  2. Stateless(무상태성)
  서버는 각각의 요청을 별개의 것으로 인식하고 처리해야하며, 이전 요청이 다음 요청에 연관되어서는 안됩니다.
  그래서 Rest API는 세션정보나 쿠키정보를 활용하여 작업을 위한 상태정보를 저장 및 관리하지 않습니다.
  이러한 무상태성때문에 Rest API는 서비스의 자유도가 높으며, 서버에서 불필요한 정보를 관리하지 않으므로 구현이 단순합니다.
  이러한 무상태성은 서버의 처리방식에 일관성을 부여하고, 서버의 부담을 줄이기 위함입니다.
 

  3. Cacheable(캐시 가능)
  Rest API는 결국 HTTP라는 기존의 웹표준을 그대로 사용하기 때문에, 웹의 기존 인프라를 그대로 활용할 수 있습니다.
  그러므로 Rest API에서도 캐싱 기능을 적용할 수 있는데, HTTP 프로토콜 표준에서 사용하는 Last-Modified Tag 또는 E-Tag를 이용하여 캐싱을 구현할 수 있고,
  이것은 대량의 요청을 효울척으로 처리할 수 있게 도와줍니다.
 

  4. Client-Server Architecture (서버-클라이언트 구조)
  Rest API에서 자원을 가지고 있는 쪽이 서버, 자원을 요청하는 쪽이 클라이언트에 해당합니다.
  서버는 API를 제공하며, 클라이언트는 사용자 인증, Context(세션, 로그인 정보) 등을 직접 관리하는 등 역할을 확실히 구분시킴으로써 서로 간의 의존성을 줄입니다.
 

  5. Self-Descriptiveness(자체 표현)
  Rest API는 요청 메세지만 보고도 이를 쉽게 이해할 수 있는 자체 표현 구조로 되어있습니다.
  아래와 같은 JSON 형태의 Rest 메세지는 http://localhost:8080/board로 게시글의 제목, 내용을 전달하고 있음을 손쉽게 이해할 수 있습니다.
  또한 board라는 데이터를 추가(POST)하는 요청임을 파악할 수 있습니다.
 
  HTTP POST , http://localhost:8080/board
  {
    "board":{
      "title":"제목",
      "content":"내용"
    }
  }

  6. Layered System(계층 구조)
  Rest API의 서버는 다중 계층으로 구성될 수 있으며 보안, 로드 밸런싱, 암호화 등을 위한 계층을 추가하여 구조를 변경할 수 있습니다.
  또한 Proxy, Gateway와 같은 네트워크 기반의 중간매체를 사용할 수 있게 해줍니다. 하지만 클라이언트는 서버와 직접 통신하는지,
  중간 서버와 통신하는지 알 수 없습니다.
```

<br/>
<br/>

# 5. REST API란

- API(Application Programming Interface)란
    * 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 하는 것
- REST API의 정의
    * REST 기반으로 서비스 API를 구현한 것
    * 최근 OpenAPI(누구나 사용할 수 있도록 공개된 API: 구글 맵, 공공 데이터 등), 마이크로 서비스(하나의 큰 애플리케이션을 여러 개의 작은 애플리케이션으로 쪼개어 변경과 조합이 가능하도록 만든 아키텍처) 등을 제공하는 업체 대부분은 REST API를 제공한다.


<br/>
<br/>

# 6. REST API 디자인 가이드

>REST API 설계 시 가장 중요한 항목은 다음의 2가지로 요약할 수 있습니다.  
첫 번째, URI는 정보의 자원을 표현해야 한다.  
두 번째, 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현한다.

## 6-1 REST API 디자인 기본 규칙
- URI는 정보의 자원의 표현해야 한다.
  * resource는 동사보다는 명사를, 대문자보다는 소문자를 사용한다.
  * resource의 도큐먼트 이름으로는 단수 명사를 사용해야 한다.
  * resource의 컬렉션 이름으로는 복수 명사를 사용해야 한다.
  * resource의 스토어 이름으로는 복수 명사를 사용해야 한다.
  * Ex) GET /Member/1 -> GET /members/1
   
  <br/>

- 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE 등)로 표현
  1. URI에 HTTP Method가 들어가면 안된다.
       * Ex) GET /members/delete/1 -> DELETE /members/1
  2. URI에 행위에 대한 동사 표현이 들어가면 안된다.(즉, CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.)
       * Ex) GET /members/show/1 -> GET /members/1
       * Ex) GET /members/insert/2 -> POST /members/2
  3. 경로 부분 중 변하는 부분은 유일한 값으로 대체한다.(즉, :id는 하나의 특정 resource를 나타내는 고유값이다.)
       * Ex) student를 생성하는 route: POST /students
       * Ex) id=12인 student를 삭제하는 route: DELETE /students/12
  
<br/>

## 6-2 REST API 설계 규칙
1. 슬래시 구분자(/ )는 계층 관계를 나타내는데 사용한다.
     - Ex) http://restapi.example.com/houses/apartments
2. URI 마지막 문자로 슬래시(/ )를 포함하지 않는다.
     - URI에 포함되는 모든 글자는 리소스의 유일한 식별자로 사용되어야 하며 URI가 다르다는 것은 리소스가 다르다는 것이고, 역으로 리소스가 다르면 URI도 달라져야 한다.
     - REST API는 분명한 URI를 만들어 통신을 해야 하기 때문에 혼동을 주지 않도록 URI 경로의 마지막에는 슬래시(/)를 사용하지 않는다.
     - Ex) http://restapi.example.com/houses/apartments/ (X)
3. 하이픈(- )은 URI 가독성을 높이는데 사용
     - 불가피하게 긴 URI경로를 사용하게 된다면 하이픈을 사용해 가독성을 높인다.
4. 밑줄(_ )은 URI에 사용하지 않는다.
     - 밑줄은 보기 어렵거나 밑줄 때문에 문자가 가려지기도 하므로 가독성을 위해 밑줄은 사용하지 않는다.
5. URI 경로에는 소문자가 적합하다.
     - URI 경로에 대문자 사용은 피하도록 한다.
     - RFC 3986(URI 문법 형식)은 URI 스키마와 호스트를 제외하고는 대소문자를 구별하도록 규정하기 때문
6. 파일확장자는 URI에 포함하지 않는다.
     - REST API에서는 메시지 바디 내용의 포맷을 나타내기 위한 파일 확장자를 URI 안에 포함시키지 않는다.
     - Accept header를 사용한다.
     - Ex) http://restapi.example.com/members/soccer/345/photo.jpg (X)
     - Ex) GET / members/soccer/345/photo HTTP/1.1 Host: restapi.example.com Accept: image/jpg (O)
7. 리소스 간에는 연관 관계가 있는 경우
     - /리소스명/리소스 ID/관계가 있는 다른 리소스명
     - Ex) GET : /users/{userid}/devices (일반적으로 소유 ‘has’의 관계를 표현할 때)

<br/>

>참고 HTTP 응답 상태 코드  
>1xx : 전송 프로토콜 수준의 정보 교환  
>2xx : 클라어인트 요청이 성공적으로 수행됨  
>3xx : 클라이언트는 요청을 완료하기 위해 추가적인 행동을 취해야 함  
>4xx : 클라이언트의 잘못된 요청  
>5xx : 서버쪽 오류로 인한 상태코드

<br/>
<br/>

# 7. RESTful
1. RESTful이란?
   - RESTful은 일반적으로 REST라는 아키텍처를 구현하는 웹 서비스를 나타내기 위해 사용되는 용어이다.
       - ‘REST API’를 제공하는 웹 서비스를 ‘RESTful’하다고 할 수 있다.
   - RESTful은 REST를 REST답게 쓰기 위한 방법으로, 누군가가 공식적으로 발표한 것이 아니다.
       - 즉, REST 원리를 따르는 시스템은 RESTful이란 용어로 지칭된다.
  
2. RESTful의 목적
   - 이해하기 쉽고 사용하기 쉬운 REST API를 만드는 것
   - RESTful한 API를 구현하는 근본적인 목적이 성능 향상에 있는 것이 아니라 일관적인 컨벤션을 통한 API의 이해도 및 호환성을 높이는 것이 주 동기이니, 성능이 중요한 상황에서는 굳이 RESTful한 API를 구현할 필요는 없다.

<br/>
<hr/>

참고자료
- https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
- https://meetup.toast.com/posts/92
- https://mangkyu.tistory.com/46