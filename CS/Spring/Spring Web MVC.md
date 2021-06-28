# Spring Web MVC

## 목차
[1. MVC Pattern](#1-mvc-pattern)<br>
[2. Spring MVC](#2-spring-mvc)<br>
[3. MyBatis](#3-mybatis)<br>
<hr/>

### 1. MVC Pattern
### 1.1 **MVC Pattern**이란
* 디자인패턴 중 하나
* Model, View, Controller의 합성어 (어플리케이션의 확장을 위해)
* 컴포넌트의 변경이 다른 영역 컴포넌트에 영향을 미치지 않음(유지보수 용이)
* 컴포넌트 간의 결합성이 낮아 프로그램 수정 용이(확장성 뛰어남)
<img src="https://mblogthumb-phinf.pstatic.net/MjAxNzAzMjVfMjUw/MDAxNDkwNDM4NzI4MTIy.4ZtITJJKJW_Nj1gKST0BhKMAzqmMaYIj9PobYJMFD4Ig.xTHT-0qyRKXsA4nZ2xKPNeCxeU2-tLIc-4oyrWq5WBgg.PNG.jhc9639/mvc_role_diagram.png?type=w800">

### 1.2 Model, View, Controller
- Model
  - 상태 쿼리에 대한 응답
  - 어플리케이션 상태의 캡슐화
  - 어플리케이션의 기능 표현
  - **백그라운드에서 동작하는 로직 처리**
  - 실제 구현에는 데이터베이스와 연동을 위한 DAO와 데이터 구조를 표현하는 DTO로 구성
<img src="https://user-images.githubusercontent.com/69454805/123514459-1e9aca80-d6ce-11eb-978f-5bc3e007e69c.PNG">
<img src="https://user-images.githubusercontent.com/69454805/123514819-bd73f680-d6cf-11eb-96c5-58320b8e2781.PNG">

- View
  - 모델을 화면에 시각적으로 표현
  - 모델에게 업데이트 요청
  - 사용자의 입력을 컨트롤러에 전달
  - 컨트롤러가 view를 선택하도록 허용
<img src="https://user-images.githubusercontent.com/69454805/123514932-4f7bff00-d6d0-11eb-9990-fb0498f5e5fd.PNG">
- Controller
  - 어플리케이션의 행위 정의
  - 사용자 액션을 모델 업데이트와 매핑
  - 응답에 대한 view 선택
  <img src="https://user-images.githubusercontent.com/69454805/123514977-9669f480-d6d0-11eb-8cc6-165f30480160.PNG">

### 1.3 장점과 단점
#### 1.3.1 장점
- 화면과 비즈니스 로직을 분리해 작업 가능
- 영역별 개발로 인하여 확장성 뛰어남
- 표준화된 코드를 사용하므로 공동작업이 용이하고 유지보수성 좋음
  
#### 1.3.2 단점
- 개발과정이 복잡해 초기 개발속도가 늦음
- 초보자가 이해하고 개발하기에 다소 어려움

### 1.4 Model 1 vs Model 2
#### 1.4.1. Model 1
> 비즈니스 로직 영역(Controller)에 View를 같이 구현하는 방식으로, 사용자의 요청을 JSP가 전부 다 처리한다. 웹 브라우저 사용자의 요청을 받은 JSP는 자바빈이나 서비스 클래스를 사용하여 웹브라우저가 요청한 작업을 처리하고 그 결과를 출력해 준다.
> 
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F6SIUm%2FbtqzYCsgeQE%2FIYkMcagYXWwMf7NSbRFJu0%2Fimg.png">
<img src="https://user-images.githubusercontent.com/69454805/123515634-25780c00-d6d3-11eb-900d-e1753c03df4b.PNG">

#### 1.4.2. Model 2
> 비즈니스 로직 영역(Controller)과 View 영역이 분리되어있는 구현 방식으로, 웹브라우저 사용자의 요청을 Servlet이 받는다. Servlet은 요청을 View로 보여줄 것인지, Model로 보내줄 것인지 정하여 전송한다. View 페이지는 사용자에게 보여주는 역할만 담당하고 실질적인 기능의 부분은 Model에서 담당한다.

<img src="https://user-images.githubusercontent.com/69454805/123515703-70921f00-d6d3-11eb-9e33-0f262ba26ece.PNG">

<hr>

### 2. Spring MVC
### 2.1 Spring MVC란
* Spring 프레임워크에서 제공하는 웹 모듈
* Front Controller 패턴에 Spring의 의존성 주입을 이용해 컴포넌트들의 생명주기를 관리할 수 있는 컨트롤러 중심의 웹 MVC 프레임워크
* Spring을 기반으로 하고 있기 때문에 Spring이 제공하는 트랜잭션 처리나 DI 및 AOP 등을 손쉽게 사용할 수 있음

### 2.2 Spring MVC 구성요소
![spring mvc](https://user-images.githubusercontent.com/69454805/123517843-3f6b1c00-d6de-11eb-9ade-c665c66b8ba7.PNG)
#### 2.2.1 **DispatcherServlet**
- Front Controller의 역할 (모든 클라이언트의 요청을 받음)
- 컨트롤러에게 클라이언트의 요청을 전달하고, 컨트롤러가 리턴한 결과값을 View에게 전달하여 알맞은 응답 생성
#### 2.2.2 HandlerMapping
- 클라이언트의 요청 URL을 어떤 컨트롤러가 처리할지 결정
- URL과 요청 정보를 기준으로 어떤 핸들러 객체를 사용할지 결정하는 객체(핸들로 실행 전에 전처리, 후처리로 실행해야 할 **Interceptor** 목록 결정)
#### 2.2.3 Controller
- 클라이언트의 요청을 처리한 뒤, Model을 호출하고 그 결과를 DispatcherServlet에 알려줌
#### 2.2.4 ViewResolver
- Controller가 리턴한 뷰 이름을 기반으로 컨트롤러의 처리 결과를 보여줄 View 결정
#### 2.2.5 View
- 컨트롤러의 처리 결과를 보여줄 응답 화면 생성
  
> 참고!
<br>
프론트 컨트롤러라는 서블릿이 컨트롤러 앞에 수문장 역할을 하면서 컨트롤러의 공통영역을 처리
<img src="https://user-images.githubusercontent.com/69454805/123517798-f61acc80-d6dd-11eb-914a-d7f4d4050235.PNG">

### 2.3 Controller
- @Controller와 @RequestMapping 선언 -> method 단위의 매핑 가능
![controller](https://user-images.githubusercontent.com/69454805/123518690-ff0d9d00-d6e1-11eb-9815-c841340c4ba1.PNG)

### 2.4 View
- 컨트롤러에서는 처리 결과를 보여줄 View 이름이나 객체를 리턴하고, DispatcherServlet은 View이름이나 View 객체를 이용하여 view를 생성

### 2.5 Model
- View에 전달하는 데이터
> Model vs ModelAndView
> <br>
Model은 데이터만 저장, ModelAndView는 데이터와 이동하고자 하는 **View Page**를 같이 저장한다

```
model.addAttribute("name", "김수빈");

return "index";

// index.jsp
${name}
```

```
mav.addObject("name", "김수빈");
mav.setViewName("index");

return mav;
```

### 2.6 Interceptor
- 컨트롤러가 요청을 처리하기 전/후 처리
- 실제 비즈니스 로직(로깅, 정보 수집)과는 분리되어 처리해야하는 기능들을 넣고 싶을 때 유용
- 여러 개 설정할 수 있지만 순서 주의!
![interceptor](https://user-images.githubusercontent.com/69454805/123533941-5cdacd00-d754-11eb-87d8-3fba2d6b968d.PNG)

### 3. MyBatis
### 3.1 MyBatis란
- Java Object와 SQL문 사이의 **자동 매핑** 기능을 지원하는 ORM 프레임워크

>참고! <br>
> ORM(Object-Relational-Mapping)은 데이터베이스와 객체지향 프로그래밍 언어간의 호환되지 않는 데이터를 변환하는 프로그래밍 기법으로, <br>
> 장점은 객체지향적인 코드로 인해 더 직관적이고, 재사용 및 유지보수성이 증가. DBMS에 대한 종속성이 줄어든다.<br>
> 단점은 완벽한 ORM으로만 서비스를 구현하기 힘들어 속도나 생산성이 낮아질 수 있다.

![mybatis](https://user-images.githubusercontent.com/69454805/123535781-1770cc80-d761-11eb-93cd-8075644bebab.PNG)

![xml](https://user-images.githubusercontent.com/69454805/123535825-7d5d5400-d761-11eb-9bd5-dd16fa5c756f.PNG)


Reference
- https://wooaoe.tistory.com/15
- https://yeonyeon.tistory.com/103
- https://hongku.tistory.com/115