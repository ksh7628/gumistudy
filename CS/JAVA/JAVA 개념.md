# JAVA
사람이 이해하기 쉬운 **JAVA**는 고급 언어<hr/>
## JAVA의 장점
1. 플랫폼에 영향을 받지 않으므로 다양한 환경에서 사용
   - write once, run anywhere : 여러 플랫폼에서 실행할 수 있음
  ![이미지](https://s3.ap-northeast-2.amazonaws.com/opentutorials-user-file/module/516/1847.gif "자바 컴파일")
2. 객체지향 언어
   - 유지 보수가 쉽고 확장성이 좋음
3. 안정적인 프로그램
   - **가비지 컬렉터**로 효율적인 메모리 관리 및 **포인터**를 사용하지 않아 메모리를 직접 제어함으로써 생기는 오류 없음
4. 풍부한 기능을 제공하는 오픈소스
   - 자바는 오픈소스이고 자바를 활용한 오픈소스 많이 개발되어 있음(JDK)

_!! 컴파일 언어 vs 인터프리터 언어 !!_
- 인터프리터 언어 : 코드를 한줄한줄 읽어가며 명령을 처리
  - 속도는 느리지만 명령을 입력하면 바로 수정할 수 있다는 장점
- 컴파일 언어 : 코드를 모두 기계어로 변환한 후 기계(JVM같은 가상 머신)에 넣고 기계어 코드를 실행
  - 번역하는 과정에서 시간이 소요되지만 런타임 시에는 빠르게 실행 가능

_JAVA는 인터프리터이면서 컴파일 언어!!_
- 인터프리터 방식 : javac 명령어로 바이트코드로 **컴파일**하고 인터프리터가 **한줄씩** 읽으면서 실행 
<HR/>

## JAVA의 변수
1. 변수 이름 정하기
   - 영문자나 숫자, 특수문자 중 $,_만 사용 가능
   - 숫자로 시작할 수 없음
   - 이미 사용중인 예약어는 사용할 수 없음
   - 카멜 표기법(numberOfStudent)
2. 기본 자료형
   
||정수형|문자형|실수형|논리형|수의 범위
|---|---|---|---|---|--|
|1바이트|**byte**|-|-|boolean|-2^7~2^7-1
|2바이트|**short**|char|-|-|-2^15~2^15-1
|4바이트|**int**|-|float|-|-2^31~2^31-1(약 21억)
|8바이트|**long**|-|double|-|-2^63~2^63-1
``` java
byte b1 = 127;
short s1 = 10;
int i1 = 1234567890;
long l1 = 1234567890L;  // 뒤에 L을 붙여줘야 한다
```
3. 상수
   - 상수 : 항상 변하지 않는 값
``` java
final double PI = 3.14;   // final 예약어를 붙인다(사용하기 전에 초기화 필수)
```
4. 형변환
   - 정수와 실수를 더한다고 할 때 하나의 자료형으로 통일한 후 연산
   - 묵시적 형변환(자동 형변환)
     - 바이트 크기가 작은 자료형에서 큰 자료형으로 대입
     - 덜 정밀한 자료형에서 더 정밀한 자료형으로 대입
     - 연산 중 자동 변환
    
``` java
byte bNum = 10;
int iNum = bNum;

int iNum2 = 20;
float fNum = iNum2;

int iNum = 20;
float fNum = iNum;
double dNum;
dNum = fNum + iNum; // int형이 float형 -> float형이 double형
```
   - 명시적 형변환
     - 바이트 크기가 큰 자료형에서 작은 자료형으로 대입
     - 더 정밀한 자료형에서 덜 정밀한 자료형으로 대입
     - 연산 중 자동 변환
``` java
int iNum = 10;
byte bNum = (byte)iNum;   // 자료 손실 발생

double dNum = 3.14;
int iNum2 = (int)dNum;    // 정수 부분만 대입해서 '3' 출력

double dNum1 = 1.2;
float fNum2 = 0.9F;
int iNum3 = (int)dNum1 + (int)fNum2;  // 각각 형변환 되어 더해짐
int iNum4 = (int)(dNum1 + fNum2);     // 합이 먼저 계산된 후 형변환됨
// iNum3와 iNum4는 어떻게 출력될까요? ^.^
```
## 자바의 연산자
1. 증가/감소 연산자
   
|연산자|기능|연산 예
|---|---|---|
|++|항의 값에 1을 더함|val=++num  /  val = num++
|--|항의 값에 1을 뺌|val=--num  /  val = num--

![이미지](https://cremazer.github.io/assets/img/java/20140919/20171207_001_java-Types-of-Operators.jpg "연산자 우선순위")

_제어문이나 조건문은 생략했습니다_<hr/>
## 클래스와 객체1
#### 객체 지향 프로그래밍(Object-Oriented Programming)은 객체를 기반으로 하는 프로그램 -> 객체 간 협력을 프로그래밍하는 것
- 클래스 : 객체의 속성과 기능을 정의

![이미지](https://i.imgur.com/CS6GIgr.png "클래스")
```java
public class Cat{
  String name;      // 멤버 변수
  String breeds;    // 멤버 변수
  double weight;    // 멤버 변수
  
  void claw(){      // 멤버 함수
    System.out.pritnln("할퀴기");
  }
  void meow(){      // 멤버 함수
    System.out.pritnln("야옹");
  }
}
```
- 인스턴스 : 실제로 사용할 수 있도록 생성된 클래스(메모리 공간을 할당받았다)
```java
Cat cat = new Cat();    // 클래스형 변수 이름 = new 생성자;

cat.name="뚱땅이";    // 도트 연산자를 사용해 멤버 변수 혹은 메서드 사용 가능
cat.claw();      
```
- 생성자 : 클래스를 처음 만들 때 멤버 변수나 상수 초기화
  - 디폴트 생성자 : 생성자가 없는 클래스는 **컴파일러가 자동으로 생성**

_!생성자를 직접 구현하면 디폴트 생성자 추가하지 않음!_

## 정보 은닉
- private으로 선언한 클래스는 멤버 변수를 사용할 수 있도록 public 메서드를 제공해야 함 -> get(), set()
![이미지](https://t1.daumcdn.net/cfile/tistory/2605FE4357D3B86301 "접근 제어자")
```java
public class Cat{
  String name;      // 멤버 변수
  String breeds;    // 멤버 변수
  double weight;    // 멤버 변수

  public setName(String name){
    this.name = name;
  }
  public getName(){
    return name;
  }
  
  void claw(){      // 멤버 함수
    System.out.pritnln("할퀴기");
  }
  void meow(){      // 멤버 함수
    System.out.pritnln("야옹");
  }
}   
```

## 클래스와 객체2
### this : 생성된 인스턴스를 스스로를 가리키는 예약어
### static 변수 : 클래스에서 공통으로 사용하는 변수
- 클래스 내부에 선언하지만, 인스턴스가 생성될 때마다 새로 생성되는 변수가 아닌 메모리에 올라갔을 때 **딱 한 번 메모리 공간이 할당** -> 모든 인스턴스가 공유
```java
public class Student{
  private static int serialNun = 1000;
  ...
}

public static void main(){
  Student studentLee = new Student();
  studentLee.serialNum++;
  Student studentSon = new Student();

  System.out.println(studentLee.serialNum);
  System.out.println(studentSon.serialNum);
}
```
_싱글톤 패턴으로 클래스 구현_
```java
public class Company{
  private Company() {}  // 1단계 : private 생성자

  private static Company instance = new Company();    // 2단계 : 유일한 인스턴스 생성

  public static Company getInstance(){
    return instance;
  }   // 3단계 : 외부에서 참조할 수 있도록 public 메서드 만들기
}

public static void main(){
  Company company = Company.getInstance();    // 4단계 : 사용
}
```

## 배열
### 자료형이 같은 자료 여러 개를 한번에 관리
```java
int[] arr = new int[5];
int arr2[] = new int[5];
```
- 배열 초기화
  - 자료형에 따라 자동 초기화
  - int[] studentId = {102, 101, 103};
- 객체 배열
```java
Book[] library = new Book[5];
library[0] = new Book("태백산맥", "조정래");
// ...
```
_배열 복사_
```java
int[] array1 = {10,20,30,40,50};
int[] array2 = {1,2,3,4,5};

System.arraycopy(array1, 0, array2, 1, 4);
// array1의 0번째부터 array2의 1번부터 4개에 복사해라
// 반복문을 통해 값을 복사하는 것보다 arraycopy를 쓰는 것이 퍼포먼스가 훨씬 좋다!
```
_깊은 복사_
```java
Book[] book1 = new Book[5];
Book[] book2 = new Book[5];
book1[0] = new Book("태백산맥", "조정래");

book2[0] = new Book();    // 직접 인스턴스를 만들고 그 값을 복사해야 함
book2[0].setBookName(book1[0].getBookName());
```

<hr/>

# 참고자료
```
Do it! 자바프로그래밍 입문 (이지스 퍼블리싱)
```
