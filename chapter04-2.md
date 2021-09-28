# 가교(BRIDGE)
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


# 복합체(COMPOSITE)
## 의도
- 부분과 전체의 계층을 표현하기 위해 객체들을 모아 트리 구조로 작성
- 사용자로 하여금 개별 객체와 복합 객체를 모두 동일하게 다룰 수 있도록 하는 패턴

## 동기
- 그래픽 응용프로그램에서 사용자가 간단한 그림 구성요소들을 모아서 복잡한 다이어그램을 생성
- 이를 구현하기 위해서는 텍스트, 라인 등 기본 그림 요소에 대한 클래스들을 정의하고 이를 포함한 컨테이너로 동작하는 클래스를 추가
  - 이 방식의 문제점은 그림에 해당하는 클래스들과 컨테이너 클래스들을 구분하기 위해 클래스의 속성으로 크기, 색깔, 위치 등의 기본 속성외 코드에 해당하는 속성을 정의해야 함
  - 이를 사용하는 응용프로그램에서 코드 값을 기억하고 코드에 따라 여러 분기를 처리해야 하는 어려움이 있음 (컨테이너에 해당하는지 개별 그림 요소에 해당하는지)


- 복합체 패턴의 가장 중요한 요소는 기본 클래스와 이들의 컨테이너를 모두 표현할 수 있는 하나의 추상화 클래스를 정의하는 것
- ![graphic](https://user-images.githubusercontent.com/7076334/135115821-0238e0e4-7283-4b84-b6db-9aaef7981dca.png)
  - Graphic 클래스는 그림을 그리기 위한 기본 클래스의 연산인 Draw 뿐만 아니라, 기본 클래스를 관리하는데 필요한 Add(), Remove(), getChild() 등의 연산도 정의
  - Graphic 서브 클래스들(Line, Rectangle, Text)는 기본적인 그래픽 객체들로서 각 그림을 그릴 수 있는 Draw() 연산 구현
  - Picture 클래스는 Graphic 객체들과 집합 관계가 성립되어 있어, Graphic 객체들을 포함할 수 있는 복합 객체로 정의
    - Picture 클래스도 Draw() 연산 구현하는데 복합 그림도 실제로 그려지는 기본 행동을 제공하기 때문
  - 일반적인 복합 객체 구조의 재귀적 특성
    - ![apicutre](https://user-images.githubusercontent.com/7076334/135115813-55e5cb71-f71f-4959-a1ae-43e2a4515148.png)
      - 이미 그룹으로 만들어진 대상을 다른 요소들과 함께 좀더 큰 그룹으로 만들기도 함
      - 이럴 때 Picture의 인스턴스가 다른 Picutre 인스턴스 포함하는 재귀적 관계 성립
      - 우리는 대상이 그룹인지 각각의 그림인지 구분하지 않고 draw()를 요청할 수 있음 (사용자 프로그램은 일관성을 갖게 됨)

## 활용성
- 복합체 패턴은 다음과 같은 경우 사용됨
  - 부분-전체의 객체 계통을 표현하고 싶을 때
  - 사용자가 객체의 합성으로 생긴 복합 객체와 개개의 객체 사이의 차이를 알지 않고도 자기 일을 할 수 있도록 만들고 싶을 때
    - 사용자는 복합 구조의 모든 객체를 똑같이 취급하게 됨  

## 구조
- 책의 구조
  - ![composite](https://user-images.githubusercontent.com/7076334/135121766-192b3619-1b69-42c6-a5f6-152b10b93fce.png)

- 내가 알고 있던 구조
  - ![composite2](https://user-images.githubusercontent.com/7076334/135121773-ff7e0f09-b762-4bbc-b452-958c9365cc24.png)


## 참여자
- Component(Graphic)
  - 집합 관계에 정의될 모든 객체에 대한 인터페이스를 정의
  - 모든 클래스에 해당하는 인터페이스에 대해서는 공통의 행동을 구현
  - 전체 클래스에 속한 요소들을 관리하는데 필요한 인터페이스를 정의   
- Leaf(Rectangle, Line, Text, 기타) : 객체 합성에 가장 기본이 되는 객체의 행동을 정의 
- Composite(Picture) : 자신이 복합하는 요소들을 저장하며, Component 인터페이스에 정의된 자식 관련 연산을 구현
- Client : Component 인터페이스를 통해 복합 구조 내의 객체들을 조작함

## 협력 방법
- 사용자는 복합 구조 내 객체 간의 상호작용을 위해 Component 클래스 인터페이스를 사용
- 요청 받은 대상이 Leaf 인스턴스이면 자신이 정의한 행동을 직접 수행
- 대상이 Composite면 자식 객체들에게 요청을 위임 (위임 전후에 다른 처리를 수행할 수도 있음)

## 결과
- 복합체 패턴으로 발생하는 결과
  - 기본 객체와 복합 객체로 구성된 하나의 일관된 클래스 계통을 정의
    - 기본 객체는 복합 객체에 속할 수 있고, 복합 객체 역시 다른 것에 속해있는 것일 수 있음
    - 사용자 코드는 상위 개념의 객체(Component)를 조작하기 떄문에, 런타임 기본 객체와 복합 객체를 구분하지 않고 일관되게 프로그래밍할 수 있게 됨  
  - 사용자 코드가 단순해짐
    - 사용자는 객체 특성이 복합 구조인지 단일 구조인지 모르고 개발할 수 있음
    - 코드에 "꼬리표-case-문장"(if나 case를 말하는듯)을 쓸 필요 없기 때문에 코드가 단순해짐 
  - 새로운 종류의 구성요소를 쉽게 추가할 수 있음
    - 새로운 요소가 추가되었다고 해서 사용자의 프로그램이 변경될 필요 없음
  - 설계가 지나치게 범용성을 많이 가짐
    - 새로운 요소를 쉽게 추가할 떄의 단점은 복합체의 구성요소에 제약을 가하기 힘들다는 점
    - 런타임에 점검이 들어가야됨 

## 구현
- 1) 포함 객체에 대한 명확한 참조자
  - 
- 2) 구성요소 공유
  -   
- 3) Component 인터페이스를 최대화
  -  
- 4) 자식을 관리하는 연산 선언
  - 
- 5) Componenet가 Component의 리스트를 구현할 수 있을까?
  - 
- 6) 자식 사이의 순서 정하기
  - 
- 7) 성능 개선을 위한 캐싱
  - 
- 8) 누가 구성요소를 삭제하는 책임을 질까?
  - 
- 9) 구성요소를 저장하기 위해 가장 적당한 데이터 구조는?
  - 
  
## 예제 코드


## 잘 알려진 사용예
- 요즘 자주 사용하는 filter 구조?

## 관련 패턴
- 구성요소-부모 간의 연결은 책임 연쇄 패턴에서 많이 사용됨
- 장식자 패턴은 자주 복합체 패턴과 함께 사용됨
  - 이 두 패턴이 함께 사용될 때는 둘다 동일한 하나의 부모 클래스를 상속받게 됨
- 플라이급 패턴으로 구성요소의 공유 방법을 얻을 수 있음
  - 단, 공유되는 구성요소의 부모는 참조할 수 없음
- 방문자 패턴을 이용하면, 이 패턴을 사용하지 않을 때 Composite와 Leaf 클래스에 걸쳐 분산될 수 있는 행동을 국소화(어느 한부분에 국한됨)시킬 수 있음


