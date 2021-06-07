# 다형성(Polymorphism)

## 다형성이란?

<br>

<pre>
다형성(polymorphism)이란 하나의 객체가 여러 가지 타입을 가질 수 있는 것을 의미합니다.

자바에서는 이러한 다형성을 부모 클래스 타입의 참조 변수로 자식 클래스 타입의 인스턴스를 참조할 수 있도록 하여 구현하고 있습니다.

다형성은 상속, 추상화와 더불어 객체 지향 프로그래밍을 구성하는 중요한 특징 중 하나입니다.
</pre>

<br>

## 다형성을 구현하는 방법
<pre>
다형성을 구현하는 방법에는 여러 가지가 있을 수 있지만, 대표적으로 알려져있는 “오버로딩”, “오버라이딩” 2가지 이다.
</pre>

<br>


## 오버로딩(Overloading)

<br>

```
자바의 PrintStream.class에 정의되어 있는 println이라는 함수는 다음과 같이 매개변수만 다른 여러 개의 메소드가 정의되어 있다.

매개변수로 배열을 넣을 때, 문자열을 넣을 때, 그리고 객체를 넣을 때 모두 println이라는 메소드 시그니처를 호출하여 원하는 내용을 출력하는 기능을 수행한다.    

오버로딩은 여러 종류의 타입을 받아들여 결국엔 같은 기능을 하도록 만들기 위한 작업이다. 이 역시 메소드를 동적으로 호출할 수 있으니 다형성이라고 할 수 있다.

하지만 메소드를 오버로딩하는 경우 요구사항이 변경되었을 때 모든 메소드에서 수정이 수반되므로 필요한 경우에만 적절히 고려하여 사용하는 것이 좋을 듯 하다. 보통 생성자 오버로딩을 많이 사용한다.
``` 

<br>

``` java
public class PrintStream {
	...
	public void println() {
		this.newLine();
	}

	public void println(boolean x) {
  		synchronized(this) {
      	this.print(x);
      	this.newLine();
  		}
	}

	public void println(char x) {
    	synchronized(this) {
        	this.print(x);
        	this.newLine();
    	}
	}

	public void println(int x) {
    	synchronized(this) {
        	this.print(x);
        	this.newLine();
    	}
	}
	...
}
```

<br>

## 오버라이딩(Overriding)

<br>

```
오버로딩과 이름이 비슷해 헷갈려하는 개발자들도 있을 것이다.
오버라이딩은 상위 클래스의 메서드를 하위 클래스에서 재정의하는 것을 말한다.
따라서 여기서는 상속의 개념이 추가된다.
아래 예시로 보인 추상 클래스 Figure에는 하위 클래스에서 오버라이드 해야 할 메소드가 정의되어 있다.
```

<br>

``` java
public abstract class Figure {
    protected int dot;
    protected int area;

    public Figure(final int dot, final int area) {
        this.dot = dot;
        this.area = area;
    }

    public abstract void display();

	  // getter
}

public class Triangle extends Figure {
    public Triangle(final int dot, final int area) {
        super(dot, area);
    }

    @Override
    public void display() {
        System.out.printf("넓이가 %d인 삼각형입니다.", area);
    }
}
```

<br>

> 만약 사각형 객체를 추가하고 싶다면, 같은 방식으로 Figure을 상속받되 메소드 부분에서 사각형에 맞는 display 메소드를 구현해주면 된다.
> 
> 이렇게 하면 추후 도형 객체가 추가되더라도 도형 객체가 실제로 사용되는 비즈니스 로직의 변경을 최소화할 수 있다.

<br>

``` java
public static void main(String[] args) {
    Figure figure = new Triangle(3, 10); // 도형 객체 추가 또는 변경 시 이 부분만 수정

    for (int i = 0; i < figure.getDot(); i++) {
        figure.display();
    }
}
```



``` java
//다형성을 사용하지 않고 도형객체를 추가하는 로직
public static void main1(String[] args) {
    int dot = SCANNER.nextInt();

    if (dot == 3) {
        Triangle triangle = new Triangle(3, 10);
        for (int i = 0; i < triangle.getDot(); i++) {
            triangle.display();
        }
    } else if(dot == 4) {
        Rectangle rectangle = new Rectangle(4, 20);
        for (int i = 0; i < rectangle.getDot(); i++) {
            rectangle.display();
        }
    }
	  ....

}
```

<br>

```
* 조상타입의 참조변수로 자손타입의 인스턴스를 참조할 수 있지만,
  반대로 자손타입의 참조변수로, 조상타입의 인스턴스를 참조할 수는 없다.

* 클래스는 상속을 통해 확장될 수는 있어도 축소될 수는 없으므로,
  자식 클래스에서 사용할 수 있는 멤버의 개수가 언제나 부모 클래스와 같거나 많게 됩니다.

* 타입변환
  1. 서로 상속 관계에 있는 클래스 사이에만 타입 변환을 할 수 있습니다.
  2. 자식 클래스 타입에서 부모 클래스 타입으로의 타입 변환은 생략할 수 있습니다.
  3. 하지만 부모 클래스 타입에서 자식 클래스 타입으로의 타입 변환은 반드시 명시해야 합니다.
```

``` java
class Parent { ... }

class Child extends Parent { ... }

class Brother extends Parent { ... }

... 다형성

Parent pa = new Parent(); // 허용
Child ch = new Child();   // 허용
Parent pc = new Child();  // 허용
Child cp = new Parent();  // 오류 발생.


... 타입변환

Parent pa01 = null;

Child ch = new Child();

Parent pa02 = new Parent();

Brother br = null;

pa01 = ch;          // pa01 = (Parent)ch; 와 같으며, 타입 변환을 생략할 수 있음.
br = (Brother)pa02; // 타입 변환을 생략할 수 없음.
br = (Brother)ch;   // 직접적인 상속 관계가 아니므로, 오류 발생
```

<br>

``` java
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// 다형성 실 사용 예제
public class PolymorphismTest {

	public static void main(String[] args) {
		Map<String, String> m = new HashMap<String, String>(); // 실 사용 예제
		Map<String, String> m2 = new LinkedHashMap<String, String>(); // 실 사용 예제 2		
	}

}
```

<hr>

## 참고문서
* http://tcpschool.com/java/java_polymorphism_concept
* https://woowacourse.github.io/javable/post/2020-10-27-polymorphism/
* https://wakestand.tistory.com/103