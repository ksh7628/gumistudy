# Jenkins

## Jenkins란?
- 소프트웨어 개발 시 지속적으로 통합 서비스를 제공하는 툴  ➔ **CI 툴**
    - CI(Continuous Integration) : 개발자가 공유 버전 제어 저장소에서 팀의 코드를 컴파일할 수 있도록 함으로써 빌드주기 비효율성을 줄이기 위한 프로세스(자신이 담당해서 하고 있는 부분의 소스코드를 정기적으로 형상관리 시스템에 submit하면 submit된 **소스코드들을 정기적으로 통합**하는 것)
- Build를 자동화해주는 툴
    - Build : 서버에 올릴 수 있는 상태로 만드는 것(서버에 올려서 사용자가 사용할 수 있게 하는 것은 배포)
- 정기적인 빌드에서 한발 나아가 서브버전, Git과 같은 버전관리시스템과 연동해 소스의 커밋을 감지하면 **자동적으로 자동화 테스트가 포함된 빌드**가 작동되도록 설정
- 즉, 빌드/테스트/배포 등(CI/CD 파이프라인) 모든 것을 자동화해주는 서버!

> CI 시스템 구축을 위한 핵심 요소
> - CI Server : 빌드 프로세스를 관리하는 서버 (ex. Jenkins)
> - SCM : 소스코드 형상관리 시스템(ex. Git)
> - build Tool : 컴파일, 테스트, 정적 분석 등을 실시해 동작 가능한 소프트웨어를 생성하는 도구 (ex. Maven, Gradle)
> - Test Tool : 작성된 테스트코드에 따라 자동으로 테스트를 수행하는 도구 (ex. JUnit)

## Jenkins의 이점
- 프로젝트 표준 컴파일 환경에서의 컴파일 오류 검출
- 자동화 테스트 수행
- 결합 테스트 환경에 대한 배포작업 등!
- 대쉬보드 제공 : 여러가지 배포 작업의 상황 모니터링


## Reference
- https://helloworld-88.tistory.com/50
- https://krksap.tistory.com/1377
- https://ict-nroo.tistory.com/31
- www.youtube.com/watch?v=JPDKLgX5bRg + https://jw910911.tistory.com/81