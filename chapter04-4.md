# 플라이급(FLYWEIGHT)

## 의도
- 공유를 통해 많은 수의 소립(작은) 객체들을 효과적으로 지원함

## 동기
- 객체지향 문서 편집기의 테이블이나 그림과 같은 문서에서 처리할 요소들을 모두 객체로 표현
  - 그러나 개발 과정에서 문자 단위까지 객체로 설계한다면?
    - 문자 하나를 객체로 관리하는 데는 비용이 많이 듬
    - 적은 분량의 문서라 하더라도 수천 개의 문자 객체를 포함할 것이고, 이는 엄청난 메모리와 예상치 못한 실행 시간 낭비를 가져옴
- 플라이급 패턴은 이런 문제를 객체 공유를 통해 해결하는 방법을 보여줌

- 플라이급 패턴에서 중요한 개념은 본질적 상태와 부가적 상태의 구분임
  - 본질적 상태는 플라이급 객체에 저장되어야 하며, 이것이 적용되는 상황과 상관없는 본질적 특성 정보들이 객체를 구성함
  - 부가적 상태는 플라이급 객체가 사용될 상황에 따라 달라질 수 있고, 그 상황에 종속적임
  - 부가적 상태는 공유될 수 없고, 사용자 객체는 이런 부가적 상태를 그것이 필요한 플라이급 객체에 전달해야 하는 책임을 갖음
  - ex) 본질적 : 문자 코드(a~z), 부가적 : 위치와 스타일
    - ![flyweight](https://user-images.githubusercontent.com/7076334/136948969-8a0c572a-22b5-4df7-859f-968e1e60e871.png)

- 글리프 예제
  - 본질적 : Character 의 char c
  - 부가적 : Charcater 의 위치 폰트, Row, Column

## 활용성
- 플라이급 패턴은 다음의 경우 사용 가능
  - 응용프로그램이 대량의 객체를 사용해야 할 때
  - 객체의 수가 너무 많아져 저장 비용이 높아질 때
  - 대부분의 객체 상태를 부가적인 것으로 만들 수 있을 때
  - 부가적인 속성들을 제거한 후 객체들을 조사해 보니 객체의 많은 묶음이 비교적 적은 수의 공유된 객체로 대체될 수 있을 때
  - 응용프로그램이 객체의 정체성에 의존하지 않을 때 (식별자가 없어서 공유될 수 있음을 의미)

## 구조
- ![flyweight3](https://user-images.githubusercontent.com/7076334/136952165-d71d1829-4511-41c8-98a1-1a57e7e9b969.png)

- 이 구조에서 플라이급 객체의 공유 방법은 다음과 같음
- ![flyweight4](https://user-images.githubusercontent.com/7076334/136952463-24d49d57-389b-45ab-aaa9-20d01ac9b47f.png)

## 참여자
- Flyweight(Glyph) : Flyweight가 받아들일 수 있고, 부가적 상태에서 동작해야 하는 인터페이스를 선언
- ConcreteFlyweight(Character) :
  - Flyweight 인터페이스를 구현하고 내부적으로 갖고 있어야 하는 본질적 상태에 대한 저장소를 정의
  - ConcreteFlyweight 객체는 공유할 수 있는 것이어야 함. 그러므로 관리하는 어떤 상태라도 본질적인 것이어야 함
- UnsharedConcreteFlyweight(Row, Column) :
  - 모든 플라이급 서브클래스들이 공유될 필요 는 없음
  - UnsharedConcreteFlyweight 객체가 ConcreteFlyweight 객체를 자신의 자식으로 갖는 것은 흔함
- FlyweightFactory : 
  - 플라이급 객체를 생성하고 관리하며, 플라이급 객체가 제대로 공유되도록 보장
  - 객체가 이미 존재하는 인스턴스를 제공하거나 만약 존재하지 않는다면 새로 생성
- Client : 플라이급 객체에 대한 참조자를 관리하며 플라이급 객체의 부가적 상태를 저장
  - 예를들어 글자를 가지고 와서(본질적) 폰트(부가적) 상태를 설정?

## 협력 방법
- 플라이급 객체가 기능을 수행하는 데 필요한 상태가 본질적인 것인지 부가적인지 구분해야 함
  - 본질적 상태는 ConcreteFlyweight에 저장해야 하고, 부가적인 상태는 사용자가 저장하거나, 연산되어야 하는 다른 상태로 관리 해야 됨
  - 사용자는 연산을 호출할 때 자신에게만 필요한 부가적 상태를 플라이급 객체에 매개변수로 전달 (폰트, 위치 등)
- 사용자는 ConcreteFlyweight의 인스턴스를 직접 만들 수 없음
  - ConcreteFlyweight 객체를 FlyweightFactory 객체에서 얻어야 함

## 결과
- 플라이급 패턴은 예전에는 모두 본질적인 상태로 저장되어 있던 것을 부가적인 상태로 만들어, 부가적인 상태의 연산과 전송에 드는 런타임 비용을 새로 들여올 수 있음
- 하지만 이런 비용은 플라이급 객체의 공유를 통해서 저장소 절약이라는 반대급부(어떤 사건에 반하여 얻게 되는 이익)를 가질 수 있음
  - 플라이급 패턴의 일부를 부가적 상태로 전환해서 사용 시, 저장소 절약의 효과가 떨어진다는 의미인듯 (부정적)

- 저장소 절약은 여러 면에서 기능적
  - 공유해야 하는 인스턴스의 전체 수를 줄일 수 있음 (pool 로 등록해서 사용하니까)
  - 객체별 본질적 상태의 양을 줄일 수 있음 (이것도 pool로 등록해서 본질적 상태가 공유되서 줄어든다는 것인가?)
  - 부가적인 상태는 연산되거나 저장될 수 있음

- 더 많은 Flyweight가 공유될 수록 저장소는 절약됨. 공유할 상태가 많아질 수록 절약됨
- 대부분의 본질적인 상태가 저장되고 부가적인 상태는 연산될 때라면 절약의 효과가 가장 큼
  - 이때는 본질적 상태를 저장하는 비용이 줄어드는 대신, 부가적 상태를 만들기 위한 연산의 시간을 투자해야 함

## 구현
- 플라이급 패턴 구현 시 고려사항들
  - 1) 부가적 상태를 제외
    - 부가적인 상태 정보를 제거한다는 것은 객체의 저장소 공간을 그만큼 절약할 수 있다는 것
    - 이론적으로 이들 부가적 상태 정보는 별도의 객체 구조에서 계산될 수 있는 것으로, 일반 상태를 저장할 때보다 더 작은 저장소가 필요함
    - ex) 글자에 대한 폰트와 스타일
  - 2) 공유할 객체를 관리
    - 객체는 공유할 수 있으므로, 사용자는 직접 인스턴스를 만들면 안됨
    - FlyweightFactory 객체는 연관 저장소를써서 사용자가 자신이 관심 있는 Flyweight를 찾아볼 수 있게 해줌
    - 플라이급 객체가 작다면 이를 메모리에서 삭제할 필요 없이 계속 두는게 좋음

## 예제코드
```
/**
 * Flyweight
 */
public interface Shape {
    void draw();
}


/**
 * ConcreteFlyweight
 */
public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println("Circle [color=" + color + ", x=" + x + ", y=" + y + "]");
    }
}

/**
 * FlyweightFactory
 */
public class ShapeFactory {
    private static final Map<String, Circle> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
        }
        return circle;
    }
}


/**
 * Client
 */
public class Client {
    public static void main(String[] args) {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};

        for (int i = 0; i < 10; i++) {
            Circle circle = (Circle) ShapeFactory.getCircle(colors[(int) (Math.random() * 4)]);
            circle.setX(100 * i);
            circle.setY(100 * i);
            circle.draw();
        }
    }
}

```


## 잘 알려진 사용예
- Java String constant pool
```
    void stringComparision() {
        String foo = "hello";
        String foz = "hello";
        String bar = new String("hello");
        String baz = bar.intern();

        assertThat(foo == foz).isTrue();
        assertThat(foo == bar).isFalse();
        assertThat(foo == baz).isTrue();
    }
```

- Java Integer valueOf (-128 ~ 127 캐싱)
```
    public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
```

## 관련 패턴
- 플라이급은 복합체 패턴과 함께 사용되는데, 공유되는 단말 노드를 갖는 방향성 비순환 그래프 형태를 써서 논리적으로 계층 구조를 구현하는 것이 여기에 해당됨
- 상태 패턴 또는 전략 패턴을 플라이급 객체로 구현할 수 있음 (알고리즘이나 상태를 본질적 상태로 저장)



## 참고
- https://sshplendid.github.io/blog/dev/designpattern/2020/02/12/flyweight-pattern/
