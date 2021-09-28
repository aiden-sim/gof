# 가교
## 의도
- 구현에서 추상을 분리하여, 이들이 독립적으로 다양성을 가질 수 있도록 함

## 다른 이름
- 핸들/구현부

## 동기
- 상속은 구현과 추상적 개념을 영구적으로 종속시키기 때문에, 추상적 개념과 구현을 분리해서 재사용하거나 수정,확장하기 쉽지 않음

- Window 추상화를 통해 PMWindow, XWIndow 구현 시 문제점
  - 1) 다른 개념의 윈도우를 생성하거나 새로운 플랫폼에서 운영 가능한 윈도우를 만들기 위해 Window 추상을 확장하기 불편함
    - ![window](https://user-images.githubusercontent.com/7076334/135083877-dcb3118a-7850-40ae-aa53-cc79892b2b1e.png)
      - 새로운 개념의 IconWindow가 추가되면 XIconWindow, PMIconWindow 서브클래스를 구현해야 함. 
      - 서브 클래스가 계속 늘어남
  - 2) 사용자 코드가 플랫폼에 종속됨
    - XWindow 객체를 생성하면 Window 추상이 XWindow 구현에 묶이게 됨
    - 이는 응용프로그램이 서로 다른 플랫폼에 이식하기가 어려움을 의미함 

- 가교 패턴
  - 추상적 개념에 해당하는 클래스 계통과 구현에 해당하는 클래스 계통을 분리함으로써 문제를 해결
  - 윈도우 인터페이스에 대한 계통 하나와 구현에 해당하는 계통 하나를 따로 구축 
  - ![bridge](https://user-images.githubusercontent.com/7076334/135085590-cf8f48db-5e21-4a0d-be48-8cf27b2f267a.png)
    - Window : 윈도우 인터페이스의 최상위 클래스
      - Window 클래스는 윈도우에 대한 새로운 개념이 만들어질 때마다 그 개념을 추상화한 클래스로 Window의 서브클래스를 만들고 새로운 연산을 정의
    - WindowImp : Window를 구현하는 클래스의 최상위 클래스
      - WindowImp 클래스가 플랫폼에 종속적인 구현을 정의하므로 플랫폼별 서브클래스를 갖게 됨
    - Window 클래스와 WindowImp 클래스 간의 관련성을 가리켜 가교(bridge)라고 정의


## 활용성
- 가교 패턴은 다음과 같은 경우에 사용
  - 추상적 개념과 이에 대한 구현 사이의 지속적인 종속 관계를 피하고 싶을 때
  - 추상적 개념과 구현 모두가 독립적으로 서브클래싱을 통해 확장되어야 할 때
    - 가교는 개발자가 구현을 또 다른 추상적 개념과 연결할 수 있게 할 뿐 아니라, 각각을 독립적으로 확장 가능하게 함
  - 추상적 개념에 대한 구현 내용을 변경하는 것이 다른 관련 프로그램에 아무런 영향을 주지 않아야 할 때
  - 사용자들은 사용자에게 구현을 완벽하게 은닉하길 원할 때 (C++)
  - 처음의 Motivation 다이어그램에서 본 것철첨 클래스 계통에서 클래스 수가 급증하는 것을 방지하고자 할 때
  - 여러 객체들에 걸쳐 구현을 공유하고자 하며, 또 이런 사실을 사용자 쪽에 공개하고 싶지 않을 때

## 구조
- ![bridge2](https://user-images.githubusercontent.com/7076334/135095229-0767f2db-1289-4ef3-9bed-1efe1c549733.png)
 
## 참여자
- Abstraction(Window) : 추상적 개념에 대한 인터페이스를 제공하고 객체 구현자(Implementor)에 대한 참조자를 관리
- RefinedAbstract(IconWindow) : 추상적 개념에 정의된 인터페이스를 확장
- Implementor(WindowImp) : 구현 클래스에 대한 인터페이스를 제공
  - 서브클래스들에 공통적인 연산의 시그너처만을 정의
  - Abstraction 클래스에 정의된 인터페이스에 정확하게 대응할 필요가 없음 (두 인터페이스는 다른 형태일 수 있음)
  - 일반적으로 Implementor 인터페이스는 기본적인 구현 연산을 수행하고, Abstraction은 더 추상화된 서비스 관점의 인터페이스를 제공
- ConcreteImplementor(XWindowImp, PMWindowImp) : Implementor 인터페이스를 구현하는 것으로 실제적인 구현 내용을 담았음

## 협력 방법
- Abstraction 클래스가 사용자 요청을 Implementor 객체에 전달

## 결과
- 1) 인터페이스와 구현 분리
  - 추상적 개념에 대한 어떤 방식의 구현을 택할지가 런타임에 결정될 수 있음
  - Abstraction과 Implementor의 분리는 컴파일 타임 의존성을 제거할 수 있음
  - 구현을 변경하더라도 추상적 개념에 대한 클래스를 다시 컴파일할 필요가 없고, 추상적 개념 클래스와 관련된 다른 코드 역시도 다시 컴파일 필요가 없음
- 2) 확장성 제고
  - Abstaction과 Implementor를 독립적으로 확장 가능 
- 3)구현 세부 사항을 사용자에게 숨기기
  - 상세한 구현 내용을 사용자에게서 은닉할 수 있음 

## 구현
- 가교 패턴 사용 시, 다음 사항을 고려
  - 1) Implementor 하나만 사용
    - 구현 방법이 하나일 때 Implementor를 추상 클래스로 정의하는 것은 불필요
    - 하지만 그럼에도 의미가 있는 부분은 사용자(Client)를 클래스 구현의 변경에서 독립시킬 수 있음
  - 2) 정확한 Implementor 객체를 생성
    - Implementor 클래스가 여러 개일 때 어떤 Implementor 클래스의 인스턴스를 언제, 어떻게, 몇 개나 생성해야 할까?
      - Abstaction 클래스가 모든 ConcreteImplementor를 알고 있다면, 이들 중 하나를 자신의 생성자 안에서 생성할 수 있을 것임
        - 용도에 맞게 생성자에 전달된 매개변수를 이용해서 어떤 것을 선택해야 하는지 결정
      - 초기에는 기본 구현을 선택한 후 필요에 따라서 다른 것을 선택
      - 결정을 다른 객체에게 위임하는 것도 가능
        - ex) 팩토리
        - 이 방법의 장점은 Abstraction 클래스에 어떤 Implementor 클래스와도 종속성을 갖지 않음 (factory를 통하니까)   
  - 3) Implementor를 공유
    - ex) Handle/Body 패턴
  - 4) 다중 상속을 이용
    - c++ 다중 상속을 이용해서 인터페이스와 구현을 합칠 수 있지만, 인터페이스에 해당 구현을 영구히 종속시키는 것이기 때문에 진정한 의미의 Bridge 구현이라 볼 수 없음

## 예제코드
```
/**
 * abstraction
 */
public class Window {
    private View contents;
    private WindowImp imp;

    public Window(View contents) {
        this.contents = contents;
    }

    public void drawRect(Point point1, Point point2) {
        WindowImp imp = getWindowImp();
        if (imp != null) {
            imp.deviceRect(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        }
    }

    public void drawText(String text, Point at) {
        WindowImp imp = getWindowImp();
        if (imp != null) {
            imp.deviceText(text, at);
        }
    }

    protected WindowImp getWindowImp() {
        if (imp == null) {
            imp = WindowSystemFactory.getInstance().makeWindowImp();
        }
        return imp;
    }

    protected View getView() {
        return this.contents;
    }
}

/**
 * implementor
 */
public interface WindowImp {
    void impTop();

    void impBottom();

    void impSetExtent(Point extent);

    void impSetOrigin(Point origin);

    void deviceRect(float x, float y, float w, float h);

    void deviceText(String text, Point at);

    void deviceBitmap(String text, float x, float y);
}

/**
 * refined abstraction
 */
public class ApplicationWindow extends Window {
    public ApplicationWindow(View contents) {
        super(contents);
    }

    @Override
    public void drawContents() {
        getView().drawOn(this);
    }
}

/**
 * refined abstraction
 */
public class IconWindow extends Window {
    private String bitmapName;

    public IconWindow(View contents) {
        super(contents);
    }

    @Override
    public void drawContents() {
        WindowImp imp = getWindowImp();
        if (imp != null) {
            imp.deviceBitmap(bitmapName, 0.0f, 0.0f);
        }
    }
}


/**
 * concrete implementor
 */
public class XWindowImp implements WindowImp {
    @Override
    public void impTop() {
    }

    @Override
    public void impBottom() {
    }

    @Override
    public void impSetExtent(Point extent) {
    }

    @Override
    public void impSetOrigin(Point origin) {
    }

    @Override
    public void deviceRect(float x, float y, float w, float h) {
    }

    @Override
    public void deviceText(String text, Point at) {
    }

    @Override
    public void deviceBitmap(String text, float x, float y) {
    }
}
```

## 잘 알려진 사용예


## 관련 패턴
- 추상 팩토리 패턴을 이용해서 특정 가교를 생성하고 복합할 수 있도록 함
- 적응자 패턴(Adapter)은 서로 관련이 없는 클래스들이 함께 동작하게 만드는 쪽에 특화시켜 만든 패턴
  - 보통 각 클래스의 설계가 끝난 후에 적용 (후적용)
- 반면 가교 패턴은 설계 단계 초기에 투입되어 추상화 및 구현이 독립적으로 다양화되도록 만드는데 사용 


