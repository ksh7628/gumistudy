# Nginx

## 목차

- [1. Nginx란](#1-nginx란)
- [2. Nginx의 흐름](#2-nginx의-흐름)
- [3. Nginx의 구조](#3-nginx의-구조)
- [4. Apache vs Nginx](#4-apache-vs-nginx)
- [5. Reference](#5-reference)

<hr>

## 1. Nginx란

![](https://media.vlpt.us/images/wijihoon123/post/74a515e9-e59d-4534-be9c-32ba441d66f9/nginx123.png)

- **동시접속 처리에 특화**된 웹 서버 프로그램(경량 웹 서버)
- 클리이언트로부터 요청을 받았을 때 요청에 맞는 정적 파일을 응답해주는 HTTP Web Server로 활용되기도 하고, Reverse Proxy Server로 활용하여 **WAS 서버의 부하를 줄일 수 있는 로드 밸런서**로 활용되기도 함
- Apache보다 동작이 단순하고 전달자 역할만 하기 때문에 동시접속 처리에 특화

<br>

**로드 밸런서(Load Balancer)**

하나의 인터넷 서비스가 발생하는 트래픽이 많을 때 여러 대의 서버가 분산처리하여 서버의 로드율 증가, 부하량, 속도저하 등을 고려하여 적절히 분산처리하여 해결해주는 서비스

<br>

> 동시접속자(약 700명) 이상이라면 서버를 증설하거나 Nginx 환경을 권장한다고 한다. 지금은 아파치가 시장 점유율이 압도적(?)이지만, 아마존웹서비스(AWS) 상에서는 시장 점유율 44%에 달할정도로 가볍고, 성능이 좋은 엔진이라고 한다.

<hr>

## 2. Nginx의 흐름

![](https://media.vlpt.us/images/wijihoon123/post/f7a48e26-ba2f-46eb-a7f6-c0b67e99f03a/nginx.png)

- Nginx는 *Event-Driven 구조*로 동작하기 때문에 한 개 또는 고정된 프로세스만 생성하여 사용
- 비동기 방식으로 요청들을 병렬화하여 처리할 수 있음
- 새로운 요청이 들어오더라도 새로운 프로세스와 쓰레드를 생성하지 않기 때문에 프로세스와 쓰레드 생성 비용이 존재하지 않고 적은 자원으로도 효율적인 운용이 가능

<br>

> **Event-Driven**
>
> - 분산 아키텍처 환경에서 상호 간 결합도를 낮추기 위해 비동기 방식으로 메세지를 전달하는 아키텍처 패턴
> - 컴퓨터 용어로는 키보드, 마우스를 동작하여 발생되는 일, 터치스크린을 터치하여 발생되는 일을 뜻함

<hr>

## 3. Nginx의 구조

![](https://media.vlpt.us/images/wijihoon123/post/3467a69b-25c0-49e1-ad76-51ee4143c49c/nginx111.png)

- Nginx는 하나의 Master Process와 다수의 Worker Process로 구성되어 실행
- Master Process는 설정 파일을 읽고, 유효성 검사 및 Worker Process를 관리
- 모든 요청은 Worker Process에서 처리
- Ngnix는 이벤트 기반 모델을 사용하고, Worker Process 사이에 요청을 효율적으로 분배하기 위해 OS에 의존적인 메커니즘을 사용
- Worker Process의 개수는 설정 파일에서 정의되며, 정의된 프로세스 개수와 사용 가능한 CPU 코어 숫자에 맞게 자동으로 조정됨

<hr>

## 4. Apache vs Nginx

> **Apache**
>
> - 월드와이드 **웹 서버 소프트웨어**
> - Apache 재단에서 만든 HTTP 서버이며 HTTP 아파치 서버라고도 불림
> - 리눅스나 윈도우 등 거의 모든 운영체제에서 사용할 수 있으며 구축이 쉽고, 다양한 추가기능을 가지고 있음

**Apache의 한계**

- 클라이언트 접속마다 프로세스 혹은 쓰레드를 생성하는 구조
- 아주 많은 클라이언트로부터 동시접속 요청이 들어온다면 CPU와 메모리 사용이 증가하고 추가적인 프로세스 및 쓰레드 생성비용이 드는 등 대용량 요청에서 한계를 보임
- Apache 서버의 프로세스가 blocking 될 때 요청을 처리하지 못하고 처리가 완료될 때까지 대기상태에 있는데, 이는 Keep Alive(접속대기)로 해결이 가능하지만, 효율이 떨어짐

**Nginx의 경우**

- 프로그램 흐름이 이벤트에 의해 결정
- 한 개 또는 고정된 프로세스만 생성하고, 그 내부에서 비동기로 효율적인 방식으로 task를 처리
- Apache와 달리 **동시접속자 수가 많아져도 추가적인 생성비용이 들지 않음**

**정리**

- Apache는 Nginx에 비해 모듈이 다양
- Apache의 안정성, 확장성, 호환성을 장점으로 들자면, Nginx는 성능이 우세하다는 장점이 있음
- 각 웹서버마다의 장단점을 가지고 있으므로 사용에 있어서 정답은 없음
- 상황과 비용에 따라, 혹은 안정성이나 효율성에 따라 적합한 웹서버를 사용하는 것이 좋음

<hr>

## 5. Reference

- https://whatisthenext.tistory.com/123
- https://velog.io/@wijihoon123/Nginx%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80
- https://velog.io/@ksso730/Nginx-Apache-%EB%B9%84%EA%B5%90
