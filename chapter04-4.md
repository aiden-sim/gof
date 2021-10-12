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

# 프록시(PROXY)

## 의도
- 다른 객체에 대한 접근을 제어하기 위한 대리자 또는 자리채움자 역할을 하는 객체를 둠

## 다른 이름
- 대리자(Surrogate)

## 동기
- 어떤 객체에 대한 접근을 제어하는 한 가지 이유는 실제로 그 객체를 사용할 수 있을 때까지 객체 생성과 초기화에 들어가는 비용 및 시간을 물지 않겠다는 것
- 문서 편집기 예
  - 이미지 같은 그래픽 객체를 생성하려면 비용이 많이 들기 때문에 꼭 필요할 때만 객체 생성하도록
  - ![proxy1](https://user-images.githubusercontent.com/7076334/136972320-b45875a1-ed7c-4d9f-b95e-af0bf465315c.png)
    - 이미지가 다른 파일에 저장되어 있따면 실제 객체에 대한 참조자로 파일 이름을 관리하면 됨
    - 또한 프록시는 자신이 책임져야 할 이미지의 넓이와 높이를 한계 정보로 관리
      - 문서를 읽을 때 이미지 자리에 일단 이미지 크기만큼 정보가 있다는 정도는 알려줄 수 있음

## 활용성
- 프록시 패턴은 단순한 포인터보다는 조금 더 다방면에서 활용할 수 있거나 정교한 객체 참조자가 필요한 때 적용할 수 있음
  - 1) 원격지 프록시(remote proxy)는 서로 다른 주소 공간에 존재하는 객체를 가리키는 대표 객체로, 로컬환경에 위치
    - Java RMI(자바의 원격 메소드 호출) 
  - 2) 가상 프록시(virtual proxy)는 요청이 있을 때만 필요한 고비용 객체를 생성함
  - 3) 보호용 프록시(protection proxy)는 원래 객체에 대한 실제 접근을 제어
    - 객체별로 접근 제어 권한이 다를 때 유용하게 사용 가능 
  - 4) 스마트 참조자(smart reference)는 원시 포인터의 대체용 객체로, 실제 객체에 접근이 일어날 때 추가적인 행동을 수행

## 구조
- ![proxy2](https://user-images.githubusercontent.com/7076334/136977709-20394ae8-4b90-4053-a807-9a9e0afdfae4.png)

- 프로그램 실행 중 프록시 구조를 객체 다이어그램으로 나타내면 다음과 같음
- ![proxy3](https://user-images.githubusercontent.com/7076334/136977896-227fba46-13b6-4300-a320-1f04c43a2c4f.png)

## 참여자
- Proxy(ImageProxy)
  - a) 실제로 참조할 대상에 대한 참조자를 관리함.
  - b) Subject와 동일한 인터페이스를 제공하여 실제 대상을 대체할 수 있어야 함
  - c) 실제 대상에 대한 접근을 제어하고 실제 대상의 생성과 삭제를 책임짐
  - d) Proxy의 종류에 따라서 다음을 수행
    - 원격지 프록시는 요청 메시지와 인자를 인코딩하여 이를 다른 주소 공간에 있는 실제 대상에게 전달 (RMI)
    - 가상의 프록시는 실제 대상에 대한 추가적 정보를 보유하여 실제 접근을 지연할 수 있도록 해야 함 (Image 크기만 가지고 있고 실제 접근 시 생성)
    - 보호용 프록시는 요청한 대상이 실제 요청할 수 있는 권한이 있는지 확인 
  
- Subject(Graphic) : RealSubject와 Proxy에 공통적인 인터페이스를 정의하여, RealSubject가 요청되는 곳에 Proxy를 사용할 수 있게 함
- RealSubject(Image) : 프록시가 대표하는 실제 객체

## 협력 방법
- 프록시 클래스는 자신이 받은 요청을 RealSubject 객체에 전달함

## 결과
- 프록시 패턴은 어떤 객체에 접근할 때 추가적인 간접화 통로를 제공함. 추가된 간접화 통로는 프록시의 종류에 따라 여러 가지 쓰임새가 있음
  - 1) 원격지 프록시는 객체가 다른 주소 공간에 존재한다는 사실을 숨길 수 있음 (Client 에게)
  - 2) 가상 프록시는 요구에 따라 객체를 생성하는 등 처리를 최적화할 수 있음
  - 3) 보호용 프록시 및 스마트 참조자는 객체가 접근할 때마다 추가 관리를 책임짐. 객체를 생성할 것인지 삭제할 것인지 관리

- 기록 시점 복사
  - 요구가 들어올 때만 객체를 생성하는 개념
  - 프록시를 사용해서 복사 절차를 미룸으로써, 사본이 수정될 때만 실제 복사 비용을 물게 만드는 것 

## 구현
- 프록시 패턴을 구현하려면, 다음 언어적 특성을 사용할 수 있음
  - 1) C++에서는 멤버 접근 연산자를 오버로드 함
    - C++에서는 멤버 접근 연산자인 operator -> 연산의 오버로드 기능 지원
    - 이 연산자를 사용하면 포인터를 통해 해당 객체에 접근할 때마다 뭔가 추가적인 행동을 할 수 있음 
  - 2) 스몰토크에 정의된 doesNotUnderstand를 사용하는 방법
    - 사용자가 호출한 메서드를 찾을 수 없을 때 doesNotUnderstand: asMessage를 호출
    - 그러면 Proxy 클래스가 doesNotUnderstand 메서드를 재정의하여 적당한 대상에 전달될 수 있도록 함
  - 3) Proxy가 항상 자신이 상대할 실제 대상을 알 필요 없음
    - Proxy 클래스는 추상 인터페이스를 통해서만 대상과 일을 하므로, 각 RealSubject 별로 Proxy 클래스를 만들 필요가 없음
    - 프록시는 모든 RealSubject를 마치 하나의 클래스인 것처럼 하나의 인터페이스를 통해 사용하면 됨 

- 대상 인스턴스를 만들기 전에 그 대상을 어떻게 알 것인가?
 - 대상이 메모리 또는 디스크에 있던 대상을 항상 참조해야 하는 프록시가 있다면?
 - 저장 공간과 관계없는 객체 식별자를 사용해야 한다는 말 (파일 이름과 같은 식별자) 

## 예제코드 (가상 프록시)
```
/**
 * client
 */
public class Client {
    public static void main(String[] args) {
        TextDocument document = new TextDocument();
        document.insert(new ImageProxy("anImageFile"));
    }
}

/**
 * subject
 */
public interface Graphic {
    void draw(Point at);

    void handleMouse(Event event);

    Point getExtent();

    void load(InputStream inputStream);

    void save(OutputStream outputStream);
}

/**
 * real subject
 */
public class Image implements Graphic {
    private String fileName;
    private Point extent;

    public Image(String file) {
        this.fileName = file;
        load(System.in);
    }

    @Override
    public void draw(Point at) {
        System.out.println("Drawing " + fileName + " at " + at);
    }

    @Override
    public void handleMouse(Event event) {
        System.out.println("Handling mouse event in " + fileName);
    }

    @Override
    public Point getExtent() {
        return extent;
    }

    @Override
    public void load(InputStream inputStream) {
        System.out.println("Loading whole image " + fileName + " from inpuStream");
        extent = new Point(400, 500);
    }

    @Override
    public void save(OutputStream outputStream) {
        System.out.println("Saving whole image " + fileName + " to outpuStream");
    }
}

/**
 * proxy
 */
public class ImageProxy implements Graphic {
    private String fileName;
    private Point extent;
    private Image image;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
        this.extent = Point.ZERO;
        this.image = null;
    }

    public Image getImage() {
        if (image == null) {
            image = new Image(fileName);
        }
        return image;
    }

    @Override
    public Point getExtent() {
        if (extent == Point.ZERO) {
            extent = getImage().getExtent();
        }
        return extent;
    }

    @Override
    public void draw(Point at) {
        getImage().draw(at);
    }

    @Override
    public void handleMouse(Event event) {
        getImage().handleMouse(event);
    }

    @Override
    public void save(OutputStream outputStream) {
        System.out.println("Save extent and fileName to outputStream");
    }
    
    @Override
    public void load(InputStream inputStream) {
        System.out.println("Load extent and fileName from inputStream");
    }
}
```
- Graphic 클래스는 그래픽 객체에 대한 인터페이스를 정의
- Image는 Graphic 인터페이스를 구현하여 이미지 파일을 출력
- ImageProxy는 Image와 같은 인터페이스를 갖음
  - getExtend에 대한 구현은 가능하면 자신이 갖고 있는 크기 정보를 제공하고, 그렇지 않으면 파일에서 이미지를 읽어옴
  - draw() 연산은 이미지를 읽어 옴
  - handleMouse() 연산은 이벤트를 이미지에 전달하는 역할을 함
  - save() 연산은 자신이 갖고 있는 정보와 이미지 파일 이름을 스트림에 저장
  - load() 연산은 이 정보를 검색해서 해당 멤버 데이터를 초기화 함

## 잘 알려진 사용예
- Spring AOP

## 관련 패턴
- 적응자 vs 프록시
  - 적응자는 자신이 개조할 객체가 정의된 인터페이스와 다른 인터페이스를 제공
  - 이에 반해, 프록시는 자신이 상대하는 대상과 동일한 인터페이스를 제공

- 장식자 vs 프록시
  - 장식자는 그 사용 목적이 하나 이상의 서비스를 추가하기 위한 것
  - 프록시는 객체에 대한 접근을 제어하는 목적
  - 구현 방법 차이
    - 보호성 프록시는 장식자 구현 방법과 거의 유사 
    - 원격 프록시는 실제 처리 대상을 직접 참조하도록 관리하지 않고 간적접 접근 방법을 관리 (호스트 식별자, 호스트 머신 내 주소등 포함)
    - 가상 프록시는 파일 이름과 같은 간접적 참조자를 정의하지만, 궁극적으로는 직접적 참조자를 얻어온 후 이를 사용 

