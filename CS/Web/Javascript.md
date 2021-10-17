# 자바스크립트

[1. Jquery](#jquery)

[2. 부트스트랩](#부트스트랩)

[3. Ajax](#ajax)

[4. Json](#json)

[5. 동기, 비동기](#동기-비동기)

[6. 콜백함수](#콜백함수)

[7. 이벤트 루프](#이벤트-루프)

[8. DOM, this, arrow function](#dom-this-arrow-function)

[9. 클로저, closure](#클로저-closure)

[10. 호이스팅, Hoisting](#호이스팅-hoisting)



##### JQuery

자바스크립트의 라이브러리로 자바스크립트를 조금 더 편리하게 사용하기 위한 언어

달러 표시를 이용해서 코드가 간결해진다.

##### 부트스트랩

CSS를 다루는 프레임워크

##### Ajax

자바스크립트의 라이브러리 중 하나.

Asynchronous Javascript and XML (비동기식 자바스크립트와 xml)

전체 페이지를 새로 고치지 않고 페이지의 일부만을 위한 데이터를 로드하는 기법 >> 자바스크립트를 이용한 비동기 통신, 클라이언트와 서버간에 Json 또는 xml 형태의 데이터를 주고받는 기술로써 자원과 시간을 아낄 수 있다.

##### Json

자바스크립트의 객체를 만드는 표현식으로 key, value로 이루어져있다.

##### 동기, 비동기

동기: A업무와 B업무가 있다면 A를 수행하는 중에는 B를 수행하지 못하는 것을 의미한다. 설계가 간단하고 직관적이지만 시스템 전체적인 효율이 저하된다.

비동기: A를 수행하면서 B를 수행하는 것을 의미한다. 동기 방식보다 설계가 복잡하지만 자원을 효율적으로 사용할 수 있다.

##### 콜백함수

어떤 특정 함수가 실행을 마친 뒤에 실행되는 함수

사용하는 이유 중 하나는 비동기 데이터를 처리하기 위함이다. 다른 함수의 실행이 끝날 때까지 특정 코드가 실행되지 않게 기다려주는 방법이다.

##### 이벤트 루프

자바스크립트는 싱글 스레드로 그  자체로는 비동기 작업을 할 수 없지만, 웹 브라우저의 도움을 받아서 구현이 가능하다. 이벤트 루프는 웹 브라우저에서 사용되는 기능 중 하나이다.

##### DOM, this, arrow function

Document of Model 의 약자

arrow function: ES6에서 새로 도입된 개념으로 기존 함수선언과 this 스코프와 호출 방식에 차이가 있다.

- this: 기존의 function에서 this의 탐색 범위가 함수의 블록 안인 반면에 arrow function에서 this는 일반적인 인자/변수와 동일하게 취급된다. 따라서 this의 스코프를 바꾸고 싶지 않을 때 유용합니다.

  ```
  // Arrow Function방식으로 호출할 때
  function objFunction() {
    console.log('Inside `objFunction`:', this.foo);
    return {
      foo: 25,
      bar: () => console.log('Inside `bar`:', this.foo),
    };
  }
  
  objFunction.call({foo: 13}).bar(); // objFunction의 `this`를 오버라이딩
  ```

  호출 결과

  ```
  Inside `objFunction`: 13 // 처음에 인자로 전달한 값을 받음
  Inside `bar`: 13 // Arrow Function에서 this는 일반 인자로 전달되었기 때문에 이미 값이 13로 지정
  ```

- 호출 방식: 어떤 함수가 new로 선언될 수 있다면 constructable하다고 하는데 arrow function은 constructable하지 않습니다. >> 호출만 가능하기 때문에 함수의 인자로 사용하기 편하다.

##### 클로저, closure

내부함수에서 외부함수의 지역변수를 사용할 때 외부함수의 lexical environment와 함께  bundled되는 것

```
let funcArr =[]

for(var i = 0; i <5; i++) {
 ( (_) => {
   var c = i * 2;
   funcArr.push( (_) => console.log(c) )
 })()
}

funcArr.forEach( f => f() )
```

호출결과

```
0, 2, 4, 6, 8
//즉시실행함수로 클로져로 만들면서 외부 함수의 lexical environment(여기에서 c=i*2) 참조하고 있기 때문에 우리가 예상한대로 출력하게 된다.
```

*참고!*  for문과 var를 쓸 때는 조심해야한다. >> let, const를 사용하는 지금에는 발생하지 않는 문제이지만 var는 블럭스코프가 아닌 함수스코프이기때문에 for문 밖에서도 접근 가능하다.

#####  호이스팅, Hoisting

인터프리터가 변수와 메모리 공간을 선언 전에 미리 할당하는 것을 의미합니다. 주로 변수의 선언과 초기화를 분리한 후, 선언만 코드의 최상단으로 옮기는 것으로 말합니다.



## 자바 관련 개인 공부

**반응형 웹의 장단점**

- 장점
  1. 화면 크기나 해상도에 상관 없이 웹사이트를 잘 보여준다.
  2. 어느 기기, 어떤 접속 환경에서도 URL이 같다.
  3. 관리가 편하다.
  4. 최신 웹 표준을 따른다.
  5. 트래픽 관리가 용이하다.
- 단점
  1. 브라우저와 호환성에 문제가 생길 수 있다.
  2. 디자인의 자유도가 떨어지고 100%  맞춤 디자인이 어렵다.
  3. 성능 문제가 있을 수 있다.(로딩 속소, 이미지 리사이징)
  4. 콘텐츠가 많고 유동적인 경우 작업량이 많아질 수 있다.
  5. IE8 이하에서 사용 불가하다.

- 기업입장에서 반응형 웹의 장점: 콘텐츠가 자주 바뀌지 않는 기업의 홈페이지의 경우 관리가 편리하며 한가지 URL로 유입되기 때문에 트래픽 관리에 좋다.

**String vs StringBuffer vs StringBuilder**

1. String 

   new 연산자를 통해 생성되면 인스턴스 메모리 공간이 절대 변하지 않으므로 + 또는 concat과 같은 연산 시 메모리의 내용이 변하는 것이 아니라 새로운 String 인스턴스가 생성된다. >> 문자열 연산이 많은 경우 성능이 떨어진다 / 불변하기 때문에 조회가 빠르고 멀티스레드 환경에서 동기화를 신경 쓸 필요가 없다.

2. StringBuffer

   한 번만 만들고 메모리의 값을 변경시켜서 문자열을 변경한다. 멀티스레드 환경에서 synchronized 키워드가 가능하므로 동기화가 가능하다.

3. StringBuilder

   StringBuffer와 동일하게 문자열을 변경할 수 있지만 동기화를 지원하지 않기 때문에 멀티 스레드 환경에서는 적합하지 않다.

> Synchronized, 현재 데이터를 사용하고 있는 해당 스레드를 제외하고 나머지 스레드들은 데이터에 접근 할 수 없도록 막는 개념

​	

**접근제한자**

1. public:  모든 클래스 접근 가능
2. protected: 같은 패키지, 해당 클래스를 상속받은 외부 클래스 접근 가능
3. defualt: 같은 패키지에서만 접근 가능
4. private: 같은 클래스에서만 접근 가능

## Reference

https://minchoi0912.tistory.com/93

반응형 웹 관련, https://nun3004.tistory.com/30

클로저 관련, https://velog.io/@proshy/JS%ED%81%B4%EB%A1%9C%EC%A0%B8closure%EC%99%80-%ED%81%B4%EB%A1%9C%EC%A0%B8%EC%9D%98-%EC%82%AC%EC%9A%A9-%EC%98%88%EC%A0%9C

변수 스코프 관련, https://velog.io/@kimtaeeeny/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-%EB%B3%80%EC%88%98var-let-const%EC%99%80-%EC%8A%A4%EC%BD%94%ED%94%84function-vs-block-FE-study6
