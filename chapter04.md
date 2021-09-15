# 구조패턴
- 구조 패턴
  - 더 큰 구조를 형성하기 위해 어떻게 클래스와 객체를 합성하는가와 관련된 패턴
  - 구조 클래스 패턴
    - 상속 기법을 이용하여 인터페이스나 구현에 복합
    - 이 패턴은 서로 독립적으로 개발한 클래스 라이브러리를 마치 하나인 양 사용할 필요가 있을 때 매우 유용한 방법
  - 구조 객체 패턴
    - 새로운 기능을 실현하기 위해 객체를 합성하는 방법을 제공
    - 런타임에 복합 방법이나 대상을 변경할 수 있음

- 적응자 패턴
  - 어떤 인터페이스가 다른 인터페이스를 따르게 만들어 서로 다른 인터페이스들의 통일된 추상을 제공 

- 복합체 패턴
  - 두 종류의 객체에 대한 클래스(기본 클래스와 복합 클래스)로 클래스 계층 구조를 어떻게 형성하는지 보여줌

- 프록시 패턴
  - 다른 객체의 대리자 역할을 수행
  - 프록시를 통해 객체의 특정한 어떤 특성에 대해서 직접 접근하지 못하게 함으로써 이 특성들을 아무 부담 없이 수정하고, 확장할 수 있게 됨 

- 플라이급 패턴
  - 객체들을 공유할 수 있는 구조를 정의
  - 객체 공유가 필요한 이유는 효율성과 일관성 보장
  - 플라이급 패턴의 목적은 효율적으로 공간을 활용하려고 객체를 공유
    - 즉 플라이급 객체는 상태가 없어야 함

- 퍼사드 패턴
  - '하나의 객체로 전체 서브시스템을 표현할 수 있을까' 고민한 패턴
  - 퍼사드는 여러 객체를 대표하는 객체로서, 자신이 받은 메시지를 자신이 대표하고 있는 다른 객체에 전달하는 역할을 수행

- 가교 패턴
  - 객체의; 개념적 추상화와 구현을 분리하여 각각 독립적으로 다양하게 변형할 수 있게 해줌 

- 장식자 패턴
  - 객체에 동적으로 새로운 책임을 추가할 수 있는 방법을 제공
  - 객체를 재귀적으로 합성하여 책임을 계속적으로 추가할 수 있도록 허용하는 구조 패턴


# 적응자
## 의도 ('이 디자인 패턴은 무엇을 하는것일까요? 의도와 논리적인 근거가 무엇일까요? 어떤 특정한 문제나 이슈를 해결하기 위한 것일까요?' 에 대한 간결한 답을 제시)
- 클래스의 인터페이스를 사용자가 기대하는 인터페이스 형태로 적응(변환)시킴
- 서로 일치하지 않는 인터페이스를 갖는 클래스들을 함께 동작시킴

## 다른 이름
- 래퍼(Wrapper)

## 동기 (시나리오)
- 사례
  - 응용프로그램이 요청하는 인터페이스와 툴킷에 정의된 인터페이스가 일치하지 않아 재사용성을 발휘하지 못하는 경우가 있음
  - 그림 편집기 예
    - 사용자가 선, 다각형, 텍스트와 같은 그래픽 요소를 이용하여 그림과 다이어그램을 만들 수 있도록 지원하는 응용프로그램
    - 그래픽 요소에 대한 인터페이스는 추상 클래스인 Shape에 정의
    - 각각의 그래픽 요소를 Shape의 서브클래스로 정의 (LineShape, PolygonShape)
    - TextShape 다른 요소들에 비해 구현이 용이하지 않음 (화면 수정과 버퍼 관리 등 복잡한 기능 필요)
      - 기존에 출시된 인터페이스 툴킷에 복잡한 TextView 처리하는 기능 제공
    - 기존에 만든 TextView의 인터페이스를 변경해서 이를 사용하려는 Shape의 인터페이스와 일치하게 할 수 있을까?
    - 이미 개발된 클래스의 인터페이스를 수정할 수 없다면 Shape와 TextView 인터페이스에 둘 다 맞도록 TextShape 클래스 조정 필요 (두 가지 방법)
      - Shape의 인터페이스와 TextView의 구현을 모두 상속 (클래스)
      - TextView의 인터페이스를 TextShape에 포함시키고, TextView 인터페이스를 사용하여 TextShape를 구현 (객체)

  - ![structure1](https://user-images.githubusercontent.com/7076334/133366293-a75c1f91-a1e7-4ca5-a1b2-f11102573999.png)
    - TextShape(적응자) 는 Shape 클래스를 상속받고 TextView 클래스의 인스턴스 포함
    - Shape 클래스를 상속받아 재정의한 BoundingBox() 연산 구현에서 TextView 클래스에 정의된 GetExtend() 메서드를 호출하도록 변경
    - TextShape 클래스는 TextView 클래스에 정의된 인터페이스를 바꾸어 Shape 클래스에 정의된 인터페이스와 부합하게 처리함 (재사용 가능)
    - 가끔 적응자(TextShape)는 적응 대상 클래스(TextView)가 제공하지 않는 기능을 제공하는 책임도 지니게 됨
      - CreateManipulator() 연산에 드래그 처리하는 행동을 추가 정의하여 TextView에 빠진 이 기능을 제공

# 활용성
- 적응자 패턴은 다음과 같은 상황에서 사용
  - 기존 클래스를 사용하고 싶은데 인터페이스가 맞지 않을 때
  - 이미 만든 것을 재사용하고자 하나 이 재사용 가능한 라이브러리를 수정할 수 없을 때
  - (객체 적응자만 해당) 이미 존재하는 여러 개의 서브클래스를 사용해야 하는데, 이 서브클래스들의 인터페이스를 다 개조한다는 것이 현싱성이 없을 때
    - 객체 적응자를 사용해서 인터페이스 기능을 변경하는 것으로 이해함 

## 구조
- 클래스 적응자
  - 다중 상속을 활용해서 한 인터페이스를 다른 인터페이스로 적응시킴
  - ![adapter_class](https://user-images.githubusercontent.com/7076334/133369615-158aaf2c-f45c-427f-94b0-5faf56c19419.png)

- 객체 적응자
  - 객체 적응자는 객체 합성을 써서 이루어짐
  - ![adapter_object](https://user-images.githubusercontent.com/7076334/133369620-f18b81e1-b6c8-4363-a82f-e56cead39152.png)

## 참여자 (주어진 패턴을 구성하고 책임을 수행하는 클래스나 객체들을 설명)
- Target(Shape) : 사용자가 사용할 응용 분야에 종속적인 인터페이스를 정의하는 클래스
- Client(DrawingEditor) : Target 인터페이스를 만족하는 객체와 동작할 대상
- Adaptee(TextView) : 인터페이스의 적응(adapter)이 필요한 기존 인터페이스를 정의 하는 클래스로서, 적응대상자(adaptee)라고 함
- Adapter(TextShape) : Target 인터페이스에 Adaptee의 인터페이스를 적응시키는 클래스

## 협력방법 (참여자들이 작업을 수행하기 위한 참여자들 간의 협력 관계를 정의)
- 사용자는 적응자에 해당하는 클래스의 인터스턴스(Target)에게 연산을 호출하고, 적응자(Adapter)는 해당 요청을 수행하기 위해 적응대상(Adaptee)의 연산을 호출함

## 결과 ('이 패턴이 자신의 목표를 어떻게 지원할까요? 이 패턴을 이용한 결과는 무엇인고 장단점은 무엇일까요? 이 패턴을 사용하면 시스템 구조의 어떤 면을 독립적으로 다양화시킬 수 있을까요?')
- 클래스 적응자와 객체 적응자의 장단점
- 클래스 적응자
  - Adapter는 명시적으로 Adaptee를 상속받고 있을 뿐 Adaptee의 서브클래스들을 상속받는 것은 아니므로, Adaptee의 서브클래스에 정의된 기능들을 사용할 수 없음
  - Adapter 클래스는 Adaptee 클래스를 상속하기 때문에 Adaptee에 정의된 행동을 재정의할 수도 있음
  - 한 개의 객체(Adapter)만 사용하며, Adaptee로 가기 위한 추가적인 포인터 간적화는 필요하지 않음

- 객체 적응자
  - Adapter 클래스는 하나만 존재해도 수많은 Adaptee 클래스들과 동작할 수 있음
    - 하나의 Adapter 클래스로 모든 Adaptee 클래스와 이를 상속받는 서브클래스 모두를 이용할 수 있게 됨
  - Adaptee 클래스의 행동을 재정의하기 매우 어려움
    - Adaptee 클래스를 상속받아서 새로운 서브클래스를 만들고, Adapter 클래스는 Adaptee 클래스가 아닌 Adaptee 클래스의 해당 서브클래스를 참조하도록 해야 함

- Adapter 패턴을 사용하면서 고려해야 할 추가적인 사항들
  - 1) Adapter 클래스가 실제 적응 작업을 위해 들어가는 품이 얼마나 되나?
    - 작업량을 결정짓는 요인은 Target 인터페이스와 Adaptee 간에 얼마만큼의 유사성을 갖는가 하는 부분
  - 2) 대체 가능 적응자
    - 클래스의 재사용성을 높이려면, 누가 이 클래스를 사용할지에 대한 생각을 최소화 해야함 
    - 하나의 클래스를 설계할 때 모든 사용자가 원하는 표준화된 인터페이스를 정의해야 한다는 부담을 덜 수 있음
    - 다른 인터페이스를 원하는 사용자가 있다면, 적응자 클래스를 만들면 됨
    - 인터페이스 개조를 담당하는 클래스를 '대체 가능 적응자' 라고 함
  - 3) 양방향 적응자를 통한 투명성 제공
    - 서로 다른 두 개의 사용자가 객체를 서로 다르게 바라봐야 할 때 필요한 기능
    - 양방향 적응자는 개조되는 두 클래스의 인터페이스를 모두 상속받아 정의하도록 하는 것 (사용자 관점으로)

## 구현 ('패턴을 구현할 때 주의해야 할 함정, 힌트, 기법 등은 무엇일까요? 특정 언어에 국한된 특이 사항은 무엇일까요?')
- Adapter 구현에 염두해야 될 부분
  - 1) 클래스 적응자를 C++로 구현
    - Adapter 클래스는 Target 클래스에서 public으로 상속받고, Adaptee는 private로 상속받아야 함
    - Target에 정의된 인터페이스는 Adapter에서도 pulbic 으로 공개되지만, Adaptee는 내부 구현에 필요한 것이므로, Adaptee가 사용자에게 알려질 필요가 없음
  - 2) 대체 가능 적응자
    - 세가지 구현 방법
    - 1) 추상 연산을 사용하는 방법
    - 2) 위임 객체를 사용하는 방법
    - 3) 매개 변수화된 적응자를 사용하는 방법

## 예제코드
- P.206

## 잘 알려진 사용예

## 관련 패턴
- 가교 패턴
  - 객체 적응자와 클래스 구조가 유사하나 그 사용 목적이 다름
  - 가교 패턴은 구현과 이 구현이 만족할 추상 개념을 분리하여 서로에게 영향을 주지 않고 각각 확장할 수 있도록 하려는 것
  - 적응자 패턴은 존재하는 객체의 인터페이스를 변경하려는 것

- 장식자 패턴
  - 다른 인터페이스의 변경 없이도 객체에 새로운 행동을 추가할 수 있도록 함
  - 이것이 적응자보다 응용프로그램을 위해 훨씬 좋은 방버이고, 순수한 적응자로는 불가능한 재귀적 합성을 가능하게 함

- 프록시 패턴
  - 다른 객체에 대한 대표자 또는 대리인의 역할을 수행하지만 인터페이스를 변경하는 책임은 없음 
