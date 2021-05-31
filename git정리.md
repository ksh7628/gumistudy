# Git
### 목차
[1. Git?](#git?)<br>
[2. Git 구조](#git-구조)<br>
[3. 용어 및 명령어](#용어-및-명령어)<br>
[4. 대표적인 Git 저장소](#대표적인-git-저장소)<br>
<hr>

### Git?<br>
분산 버전 관리 시스템이라 한다. 프로젝트와 같은 협업을 처음 할때 깃을 사용하지 않았다면<br>
1차수정_201102.zip -> 2차수정_201103.zip -> 최종_201105.zip -> 진짜최종_201106.zip -> 진짜진짜최종_201107.zip -> ...<br>
이런식으로 버전을 계속해서 수정하고 zip이나 txt파일형태로 주고 받은 기억이 있을것이다.<br>
깃을 사용하면 이러한 번거로운 버전 관리를 효율적으로 관리할 수 있게 된다.<br>
<hr>

### Git 구조
![Git 구조](http://pismute.github.io/whygitisbetter/images/local-remote.png "Git 구조")<br>
(사진 출처: http://pismute.github.io/whygitisbetter/images/local-remote.png)<br>
- working directory : 수정할 파일들이 있는 디렉토리
- staging area : git directory에 있으며, 단순한 파일이고 곧 commit할 파일에 대한 정보를 저장한다.
- local repo : 개인 PC에 설정된 git repository
- remote repo : remote repository를 운용하는 서버에 있는 git repository(대표적으로 github, gitlab 등)
<hr>

### 용어 및 명령어<br>
- 저장소(Repository): 프로젝트가 거주할 수 있는 디렉터리나 저장 공간. "repo"로 줄여서 사용한다. 사용자의 컴퓨터 안의 로컬 폴더가 될 수도 있고,
깃허브나 다른 온라인 호스트의 저장 공간이 될 수도 있다.
- 커밋(Commit): 커밋을 하면 해당 시점의 사용자의 저장소의 스냅샷을 찍어, 프로젝트를 이전의 어떠한 상태로든 재평가하거나 복원할 수 있는 체크포인트를 가질 수 있다.
- 브랜치(Branch): 브랜치란 독립적으로 어떤 작업을 진행하기 위한 개념으로, 필요에 의해 만들어지는 각각의 브랜치는 다른 브랜치의 영향을 받지 않기 때문에, 여러 작업을 동시에 진행할 수 있다.
- 체크아웃(Checkout): 사용자가 사용할 브랜치를 지정하는 것을 의미한다.
<br>

```
$ git init
```
깃 저장소를 초기화한다. git init 명령어를 사용해야 추가적인 git 기능들을 이용할 수 있다.

```
$ git config

// 사용자 등록, global: 전역 옵션
git config --global user.name "홍길동"
git config --global user.email "hong@gildog.com"

// 사용자 삭제
git config --unset --global user.name
git config --unset --global user.email

// configure list
git config --list
```
configure 설정으로 사용자 이름이나 이메일 설정, 삭제 및 리스트 확인이 가능하다.

```
$ git help
```
git 주요 명령어 확인이 가능하다.

```
$ git add
```
working directory 상의 변경 내용을 staging area에 추가하기 위해서 사용하는 명령어이다.<br>
-a 옵션을 주면 커밋에 파일의 변경 사항을 한번에 모두 포함한다.

```
$ git commit -m "commit message"
```
git의 핵심 명령어로써, 커밋을 생성한다.(실제 변경사항 확정)

```
$ git branch // 브랜치 목록
$ git branch 'branch_name' // 브랜치 생성
$ git branch -d 'branch_name' // 브랜치 삭제
```
git branch와 관련된 내용

```
$ git push // 원격 저장소에 올리기
$ git push -u origin master // 지역 저장소의 브랜치를 원격 저장소의 마스터 브랜치와 연결 (한번만 하면 됨)
```
commit 된 파일들을 원격 저장소에 업로드

```
$ git clone [repo_address]
```
git pull이랑 비슷하지만, 클라이언트 상에 아무것도 없을 때 서버의 프로젝트를 내려받는 명령어이다.

```
$ git pull
```
다른 사람이 코드를 업데이트했거나, commit 했을 때(Github를 통해서도 간단한 commit가능) 그 내용을 클라이언트로 내려받는 명령어이다.

<hr>

### 대표적인 Git 저장소
1. Github: https://github.com/<br>
2. GitLab: https://gitlab.com/<br>

#### github vs gitlab <br>
공통적으로 깃 저장소지만, 깃허브는 유료 계정이 아닌 이상 모든 코드를 공유해야 하는 오픈 소스 프로젝트로 진행되므로<br>
누구나 깃허브에 푸쉬한 코드를 볼 수 있다.<br>
깃랩은 비공개 저장소를 참여 인원 수에 관계없이 무제한으로 생성 가능하며 비용을 추가로 내면 기술지원도 받을 수 있다.<br>
<hr>

### 참조 사이트
- https://namu.wiki/w/Git
- https://goddaehee.tistory.com/91
- https://uxgjs.tistory.com/182
- https://velog.io/@bathingape/2019-12-26-2012-%EC%9E%91%EC%84%B1%EB%90%A8-pvk4mmgw5z
