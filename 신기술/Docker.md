# Docker

![docker](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FBLfQp%2FbtqGcghEDxD%2F9HkIUeswHNtIp64QFXSHZk%2Fimg.jpg)

## Docker란?

> 도커(docker)는 리눅스의 응용 프로그램들을 소프트웨어 컨테이너 안에 배치시키는 일을 자동화하는 오픈 소스 프로젝트

### 컨테이너

![container](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FHvbbi%2FbtqF9CMJAQT%2FB94p6CuvKvOCePNrrGqVBk%2Fimg.jpg)

규격화되지 않은 물품들을 운송하게 되면 정리도 힘들고 공간 낭비가 심하고 노동비도 많이 들어 비효율적

컨테이너와 같이 규격화된 상자 안에 물건을 담아 배송하면 대량의 물건을 안전하고 효율적으로 배송이 가능

도커에서 사용하는 컨테이너도 이와 같은 개념!


>**즉, 도커란 다양한 프로그램, 다양한 운영체제 및 실행환경 등을 컨테이너로 추상화하여 동일한 인터페이스를 제공하여, 배포 및 관리를 단순화 시켜주는 것!!**


## VM(Virtual Machine) vs Docker

### VM
- 기존 사용하는 OS(Window라고 가정) 외 다른 OS(Linux라고 가정)를 사용하려면 해당 Linux 전체를 가상화하여 사용
- 기존 OS(Window)와 다른 OS(Linux)는 완전히 독립적으로 존재
- 그런데 다른 OS(Linux) 사용을 위해 기존 OS(Window)가 사용되기 때문에 속도가 엄청나게 느려지고, 독립적인 OS이기 때문에 설치 용량 또한 매우 큼

### Docker
- VM을 사용하지 않고 도커 엔진(Docker Engine)을 사용하여 동작
- 가상화가 아닌 프로세스 격리 방법
- 별도의 OS 설치가 필요하지 않게 되어, 성능적인 개선과 동시에 메모리 용량 또한 작아짐
- 또한, 컨테이너 실행에 필요한 파일, 설정값들을 담는 이미지를 사용하기 때문에 새로운 서버가 생겨도 이것저것 설치할 필요 없이 이미지만을 다운받아 컨테이너를 생성하면 됨

![vmdocker](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdJN045%2FbtqGbOTevZ7%2Fdok3jbKoIv8wwZbSJ2Nf9k%2Fimg.jpg)


## 도커를 쓰는 이유

### ▶ 간단한 예시

깃랩(gitlab)을 설치하기 위한 공식 문서의 안내

우분투 설치
```
sudo apt-get update
sudo apt-get install -y curl openssh-server ca-certificates
sudo apt-get install -y postfix
curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ee/script.deb.sh | sudo bash
sudo EXTERNAL_URL="http://gitlab.example.com" apt-get install gitlab-ee
```

CentOS 설치
```
sudo yum install -y curl policycoreutils-python openssh-server
sudo systemctl enable sshd
sudo systemctl start sshd
sudo firewall-cmd --permanent --add-service=http
sudo systemctl reload firewalld
sudo yum install postfix
sudo systemctl enable postfix
sudo systemctl start postfix
curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ee/script.rpm.sh | sudo bash
sudo EXTERNAL_URL="http://gitlab.example.com" yum install -y gitlab-ee
```

두 설치 방법은 딱 봐도 많이 다르고 모두 익히기 어렵다. (또한, 다른 OS들도 각각 설치 방법이 다를 것임)

컨테이너 도구인 도커가 설치되어 있다면 어느 환경이든 상관 없이 다음 명령어를 사용하여 깃랩 실행이 가능

```
$ docker run --detach \
    --hostname gitlab.example.com \
    --publish 443:443 --publish 80:80 --publish 22:22 \
    --name gitlab \
    --restart always \
    --volume /srv/gitlab/config:/etc/gitlab \
    --volume /srv/gitlab/logs:/var/log/gitlab \
    --volume /srv/gitlab/data:/var/opt/gitlab \
    gitlab/gitlab-ce:latest
```

### ▶ Snowflake Servers

똑같은 일을 하는 두 서버가 있다 하더라도, A 서버는 한 달 전에 구성했고 B 서버는 이제 막 구성했다면, 운영체제부터 컴파일러, 설치된 패키지까지 완벽하게 같기는 쉽지 않음<br>
=> "둘이 똑같은데 왜 A는 잘 되고 B는 안되지?" 라는 상황이 벌어질 수 있는 것

이렇게 서로 모양이 다른 서버들이 존재하는 상황을 **눈송이 서버(Snowflakes Servers)**라고도 한다.

> A 서버와 B 서버에 ImageMagick 이라는 도구를 설치한다고 가정 <br>
> A 서버는 지난 달 서버를 구성하며 이미지매직을 설치했고, B 서버는 이제 막 구성한 상황이라 새로 이를 설치하였는데, A 서버에서 장애가 발생했다. 이런 상황에서 장애 발생 원인은 대략 다음과 같이 추측이 가능<br>
>- 웹 서비스에서 이미지매직 최신 버전에 새로 추가된 기능을 사용
>- 웹 서비스의 업데이트 부분 코드에 버그 존재
>- 이미지매직의 버전이 다름
>- 이미지매직이 의존하는 라이브러리 버전이 다름
> 장애를 빨리 찾으려면 A 서버를 잘 아는 사람이 필요한데, A 서버를 구성했던 사람이 퇴사해서 옆에 없다. 그럼 개발자는 A 서버가 구성되고 운영되었던 모든 과정을 파악해야만 하는데, 이는 아주 아주 아주 힘든 과정!

### ▶ 서버를 코드로 구성하고 관리

도커파일에서 사용하는 도커파일(Dockerfile)이 바로 앞서 이야기한 **서버 운영 기록을 코드화**한 것

```
# Nginx 서버를 구성하는 도커 파일
FROM debian:stretch-slim

RUN apt-get update \
    && apt-get install -y \
    imagemagick  
```

이 도커 파일로 도커 이미지를 만들 수 있음. 

도커 파일이 **서버 운영 기록**이라면, 도커 이미지는 운영 기록을 **실행할 시점**이라고도 할 수 있음

![img](https://d2uleea4buiacg.cloudfront.net/files/fda/fda75bc98a7a5f0dcd4a546e5454f15a7ef42972be5220b58045509b428a39d0.m.png)

이것이 바로 도커가 여느 서버 구성 도구와 가장 다른 부분인데, 다른 도구들은 모두 도구를 실행하는 시점에 서버의 상태가 결정되는 데 비해, 도커는 작업자가 그 시점을 미리 정해둘 수 있음. 덕분에 서버를 항상 똑같은 상태로 만들 수 있는 것.

도커를 처음 접한 분들이 불편하게 여기는 부분 중 하나가 바로 도커 파일인데, 명령어가 어려워서라기보다는 한 번 작성해서 이미지 빌드까지 성공하는 경우가 드물다보니, 수정해서 다시 빌드하는 과정을 반복해야 하기 때문. 게다가 빌드하는 시간이 점점 길어지는데 그러다보면 마치 코드 작성 후 컴파일하는 시간처럼 느껴지기도 한다.

그런데 이 불편함을 다르게 바라보면 오히려 도커만의 장점이 될 수도 있음.

### ▶ 테스트 주도 개발의 관점에서 도커파일 바라보기

소프트웨어 작성에 도움이 되는 기법 중 하나인 테스트 주도 개발 (Test Driven Development, TDD)

1. TDD에서는 먼저 테스트를 작성
2. 테스트에 실패
3. 코드를 작성/수정
4. 테스트 성공
5. 코드 리팩터링
6. 다시 1번으로

![TDD](https://d2uleea4buiacg.cloudfront.net/files/cdf/cdf3ae0a6ca574c036ba35e3957f100d7d77bb6e4eed91ccb065838441188266.m.png)

도커 파일
1. 도커 파일 작성
2. 도커 이미지 생성 실패
3. 도커 파일 작성/수정
4. 도커 이미지 생성 성공
5. 필요 없는 부분 삭제, 합칠 수 있는 부분 합치기 (리팩토링)
6. 다시 1번으로

![TDDDocker](https://d2uleea4buiacg.cloudfront.net/files/548/54855bc2f1cbd3143832027cbbe7e651cee964e8ac83a9d86e93b75c06bccb06.m.png)

무엇인가를 개발할 때 **미리 실패해보는 일은 생각보다 중요!!**<br>
미리 겪은 장애는 약간의 기다림과 귀찮음 뿐이지만, 나중에 겪는 장애는 큰 결함으로 다가올 수 있기 때문


### ▶ 결론

1. 서버 제작 과정에 견고함과 유연성을 더해준다.
2. 다른 이가 만든 서버를 소프트웨어 사용하듯 가져다 쓸 수 있다.
3. 여러 대에 배포할 수 있는 확장성이 있다.

서버 코드화를 통해 얻는 장점으로 위와 같은 점들이 있고, 이를 통해 다양한 작업환경에서의 배포가 자유로워 질 수 있고, 빠른 배포가 가능!

<hr>

## References
- https://www.44bits.io/ko/post/why-should-i-use-docker-container
- https://hanhyx.tistory.com/27
- https://lion-king.tistory.com/8
- https://captcha.tistory.com/46