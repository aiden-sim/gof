# 행동 패턴
- 어떤 처리의 책임을 어느 객체에 할당하는 것이 좋은지, 알고리즘을 어느 객체에 정의하는 것이 좋은지 등을 다룸
- 행동 패턴은 객체나 클래스에 대한 패턴을 정의하는 것이 아니고, 그들 간의 교류 방법에 대하여 정의
- 행동 패턴을 사용하면 우리는 객체 간의 제어 구조보다는 객체들을 어떻게 연결시킬 것인가에 좀더 중점

- 상속 행동 패턴
  - 1) 템플릿 메서드 패턴
    - 알고리즘에 대한 추상화된 정의로, 알고리즘을 한 단계씩 정의
    - 각 단계는 추상 연산 또는 기본 연산 중 하나 (기본 연산은 자신이 처리 내용을 정의하고 구현 내용을 확정한 연산을 의미)
    - 추상 메서드의 실제 구체적인 구현은 서브클래스가 정의
  - 2) 해석자 패턴
    - 문법을 클래스 계통으로 구성하고 이 클래스들의 인스턴스에 대한 연산으로서 해석자를 구현 

- 복합 행동 패턴
  - 하나의 객체가 스스로 모든 처리를 하는 것이 아니라, 관련된 객체들이 하나의 처리를 책임지는 방법
  - 1) 중재자 패턴
    - 관련된 객체 집합 사이의 중재자로 새로운 객체를 하나 도입함으로써 이 상황(필요한 객체를 직접 참조해야되는 상황)을 피하게 해줌
    - 중재자 객체가 관련 객체 간의 처리를 담당함으로써, 객체 간의 결합도가 느슨해질 수 있음
  - 2) 책임 연쇄 패턴
    - 결합도를 좀 더 약화시키는 효과를 줌
    - 한 객체에게 보낸 메시지가 내부적으로 연결된 다른 객체에게 자동으로 전달
    - 메시지를 받은 객체는 런타임 조건에 따라서 메시지를 처리할 것인지 결정
  - 3) 감시자 패턴
    - 객체 간의 종속성을 정의하고 관리하는 패턴
    - 스몰토크 언어 MVC에서 사용
      - 모델이 변경되면, 모델과 관련된 모든 뷰에 그 변경 사실 통지  

  - 행동을 객체로 어떻게 잘 감쌀 것인지, 또 자신이 메시지를 처리할 것인지 아니면 다른 객체에게 위임할 것인지 등에 관련
  - 4) 전략 패턴
    - 알고리즘을 객체로 만들어 그것을 캡슐화
    - 알고리즘(행동)을 별도 객체로 분리함으로써, 알고리즘이 바뀔 때 해당 알고리즘 객체를 변경 또는 추가해서 이를 사용하는 곳에서는 아무 변화 없이 사용할 수 있도록 해줌 
  - 5) 명령 패턴
    - 요청 자체를 객체로 만들어서 이 객체를 매개변수로 넘기거나 수행한 명령 리스트에 저장하는 방식으로 사용 
  - 6) 상태 패턴
    - 객체의 상태를 또 다른 객체로 정의하여 객체의 상태가 바뀌면 이에 대항하는 객체를 변경할 수 있도록 함 
  - 7) 방문자 패턴
    - 하나의 행동을 여러 클래스에 걸쳐서 분산할 수 있게 해줌 
  - 8) 반복자 패턴
    - 모든 객체들을 순회하거나 차례로 접근하는 방법을 제공


# 책임 연쇄(CHAIN OF RESPONSIBILITY)
## 의도
- 메시지를 보내는 객체와 이를 받아 처리하는 객체들 간의 결합도를 없애기 위한 패턴
- 하나의 요청에 대한 처리가 반드시 한 객체에서만 되지 않고, 여러 객체에게 그 처리 기회를 줌

## 동기
- 그래픽 사용자 인터페이스(GUI)에 있는 문맥 감지 도움말 기능
  - 사용자가 정보를 선택하면 그 부분에 대한 도움말 정보를 얻을 수 있음
  - 선택한 주체에 대한 구체적인 도움말이 없다면, 도움말 시스템은 적어도 응용프로그램이 정의한 일반적인 도움말이라도 제공할 수 있어야 함
  - 어느 정도의 구체적인 도움말이 가능한가에 따라 여러 인터페이스 중 하나가 이 요청을 처리할 수 있음
  - 하지만, 요청을 일으키는 객체는 실제로 자신에게 해당 도움말을 제공하는 객체가 누구인지 알 수 없음
  - 이를 위해 도움말 요청을 발생시키는 버튼과 도움말 정보를 제공하는 객체를 분리해야 할 필요가 있음
    - 이 부분의 기작을 정의하는 패턴이 책임 연쇄 패턴 

- 책임 연쇄 패턴의 아이디어는 메시지 송신 측과 수신측을 분리하는 것
  - 송신(보내는)하는 측이 자신이 아는 주체에게 처리를 요청하면, 이를 수신한 객체가 자신과 연결된 고리를 따라서 계속 이 요청을 전달
  - 이 중에 어느 한 객체가 실제 상황에 적합하다고 판단되면 자신에게 정의된 서비스를 제공

- ![chain1](https://user-images.githubusercontent.com/7076334/137943545-3920b292-1230-488f-895b-c244ab24fbf4.png)
  - 사용자는 요청을 발생하기는 했지만, 이 요청을 궁극적으로 처리할 객체의 직접적인 참조자는 없음
  - 사용자는 그저 PrintButton에 메시지를 보냈을 뿐
  - 연결 고리를 따라 요청을 계속 전달할 수 있어야 하고, 메시지 수신 객체를 명시할 수 없는 상황을 고려한다면, 객체의 연결 고리에 존재하는 객체는 누구든지 동일한 요청을 처리할 수 있어야 함
    - 공통의 인터페이스를 가져가야 함

## 활용성
- 책임 연쇄 패턴은 다음의 경우에 사용
  - 하나 이상의 객체가 요청을 처리해야 하고, 그 요청 처리자 중 어떤 것이 선행자인지 모를 때. 처리자가 자동으로 확정되어야 함
  - 메시지를 받을 객체를 명시하지 않은 채 여러 객체 중 하나에게 처리를 요청하고 싶을 때
  - 요청을 처리할 수 있는 객체 집합이 동적으로 정의되어야 할 때

## 구조
- ![chain3](https://user-images.githubusercontent.com/7076334/137946061-c54e3d65-383e-4330-a5a2-a71f6c6d7189.png)

## 참여자
- Handler(HelpHandler) :
  - 요청을 처리하는 인터페이스를 정의하고, 후속 처리자(successor)와 연결을 구현
  - 즉, 연결 고리에 연결된 다음 객체에게 다시 메시지를 보냄
- ConcreteHandler(PrintButton, PrintDialog) :
  - 책임져야 할 행동이 있다면 스스로 요청을 처리하여 후속 처리자에 접근할 수 있음
  - 즉, 자신이 처리할 행동이 있으면 처리하고, 그렇지 않으면 후속 처리자에 다시 처리를 요청
- Client : ConcreteHandler 객체에게 필요한 요청을 보냄

## 협력 방법
- 사용자는 처리를 요청하고, 이 처리 요청은 실제로 그 요청을 받을 책임이 있는 ConcreteHandler 객체를 만날 때까지 정의된 연결 고리를 따라서 계속 전달됨

## 결과
- 책임 연쇄 패턴 장점
  - 1) 객체 간의 행동적 결합도가 적어짐
    - 다른 객체가 어떻게 요청 처리하는지 몰라도 되고, 단지 요청을 보내는 객체는 이 메시지가 적절하게 처리된 것이라는 것만 확신하면 됨
    - 메시지를 보내는 측이나 받는 측 모두 서로를 모르고, 또 연결된 객체들조차도 그 연결 구조가 어떻게 되는지 모름
    - 결과적으로, 이 패턴은 객체들 간의 상호작용 과정을 단순화 시킴 (자신은 자신과 연결된 단 하나의 후보 객체만 알면 됨)
  - 2) 객체에게 책임을 할당하는 데 유연성을 높일 수 있음
    - 객체의 책임을 여러 객체에게 분산시킬 수 있으므로 런타임에 객체 연결 고리를 변경하거나 추가하여 책임을 변경하거나 확장할 수 있음

- 책임 연쇄 패턴 단점
  - 1) 메시지 수신이 보장되지 않음
    - 어떤 객체가 이 처리에 대한 수신을 담당한다는 것을 명시하지 않으므로 요청이 처리된다는 보장 없음
    - 객체들 간의 연결 고리가 잘 정의되지 않는경우, 요청은 처리되지 못한 채로 버려질 수 있음 

## 구현
- 책임 연쇄 패턴을 구현할 때 고려사항
  - 1) 후속 처리자들의 연결 고리 구현하기
    - 후속 처리자들의 연결 고리 구현에 두가지 방법이 있음
      - 새로운 연결을 만드는 것
      - 이미 있는 연결 정보를 사용하는 것
    - 이미 있는 연결 정보를 사용하는 방법
      - 부분-전체에 해당하는 관계였다면, 이 문제에는 부모에 대한 참조자가 정의되었을 것이고, 이를 후속 처리자에 대한 참조자로 이용
      - 복합체 패턴을 참조하면 부모 객체의 참조자에 대한 자세한 설명을 얻을 수 있음 
  - 2) 후속 처리자 연결하기
    - 연결 정보에 대한 미리 정의된 참조자가 없다면 직접 정의해야 하는데, Handler 클래스는 요청 처리에 대한 인터페이스를 정의할 뿐만 아니라, 후속 처리자에 대한 정보 저장의 인스턴스 변수도 정의
    - 이 정보를 이용해서 Handler 클래스의 기본 구현은 자신의 후속 처리자로 정의된 객체에 HandleRequest() 메시지를 전달하도록 만듬
  - 3) 처리 요청의 표현부를 정의
    - 가장 쉬운 방법은 HandlHelp() 연산처럼 하드코딩 (P.300)
      - 간편하고 안전하지만, Handler 클래스가 정의한 연산의 집합에 대해서만 요청을 전달할 수 있어서 제한적
    - 처리를 코드화하여 매개변수로 받아들이도록 하는 처리자 함수를 하나만 정의
      - 이렇게 하려면, 메시지 송신 측과 수신 측 사이에 처리 요청을 어떻게 코드로 만들지에 대한 동의가 있어야 함
      - 좀 더 유연한 방법이지만, 코드에 따라서 대응하는 처리 요청을 발생시키려면 조건문을 정의해야 함
      - 하드코딩 보다 안전하지 못함
    - 매개변수 전송 문제를 처리하기 위해서 매개변수를 묶어 별도의 객체로 만듬
      - Request 클래스를 이용해서 처리 요청을 명시적으로 정의 (P.305 코드)
        - 새로운 요청 시, Request 서브클래스로 정의하면 됨
        - Handler는 어떤 종류의 처리 요청인지만 알면 필요한 매개변수에 접근 가능
        - HandlerRequest() 연산을 상속한 후 재정의하면 새로운 처리방법으로 구현 가능
  - 4) 스몰토크를 쓸 때 자동 전달 기능 이용 
    - doesNotUnderstand 매커니즘을 이용하여 처리 요청을 전달할 수 있음

## 예제코드
- 도움말 시스템
  - ![chain4](https://user-images.githubusercontent.com/7076334/137953317-5895755a-2fff-41c4-bf5f-a49b0bb98ee7.png)

```
/**
 * Handler
 */
public class HelpHandler {
    private HelpHandler successor;
    private Topic topic;

    public HelpHandler(HelpHandler successor) {
        this(successor, Topic.NO_HELP_TOPIC);
    }

    public HelpHandler(HelpHandler successor, Topic topic) {
        this.successor = successor;
        this.topic = topic;
    }

    public void setHandler(HelpHandler handler, Topic topic) {
        this.successor = handler;
        this.topic = topic;
    }

    public boolean hasHelp() {
        return topic != Topic.NO_HELP_TOPIC;
    }

    public void handleHelp() {
        if (successor != null) {
            successor.handleHelp();
        }
    }
}

/**
 * ConcreteHandler
 */
public class Widget extends HelpHandler {
    @SuppressWarnings("unused")
    private Widget parent;

    protected Widget(Widget parent) {
        this(parent, Topic.NO_HELP_TOPIC);
    }

    protected Widget(Widget parent, Topic topic) {
        super(parent, topic);
        this.parent = parent;
    }
}

/**
 * ConcreteHandler
 */
public class Button extends Widget {
    public Button(Widget parent) {
        this(parent, Topic.NO_HELP_TOPIC);
    }

    public Button(Widget parent, Topic topic) {
        super(parent, topic);
    }

    @Override
    public void handleHelp() {
        if (hasHelp()) {
            System.out.println("Button: handling help request ");
        } else {
            super.handleHelp();
        }
    }
}

/**
 * ConcreteHandler
 */
public class Dialog extends Widget {
    public Dialog(HelpHandler handler) {
        this(handler, Topic.NO_HELP_TOPIC);
    }

    public Dialog(HelpHandler handler, Topic topic) {
        super(null);
        setHandler(handler, topic);
    }

    @Override
    public void handleHelp() {
        if (hasHelp()) {
            System.out.println("Dialog: handling help request");
        } else {
            super.handleHelp();
        }
    }
}

/**
 * ConcreteHandler
 */
public class Application extends HelpHandler {
    public Application(Topic topic) {
        super(null, topic);
    }

    @Override
    public void handleHelp() {
        System.out.println("Application: handling help request");
    }
}

/**
 * Client
 */
public class Client {
    public static void main(String[] args) {
        Application application = new Application(Topic.APPLICATION_TOPIC);
        Dialog dialog = new Dialog(application, Topic.PRINT_TOPIC);
        Button button = new Button(dialog, Topic.PAPER_ORIENTATION_TOPIC);

        button.handleHelp();
    }
}
```
- HelpHandler
  - 도움말 요청을 처리하는데 필요한 인터페이스를 정의
  - 도움말 목록을 관리하고 HelpHandler의 연결 고리 다음번 객체에 대한 참조자를 관리
  - 가장 중요한 연산은 handleHelp()로 서브클래스들에서 이 연산을 재정의 해야됨
  - hasHelp()는 관련된 도움말 항목이 있는지 확인
- Widget
  - 모든 위젯의 부모 클래스
  - Widget은 HelpHandler 서브클래스로, 모든 사용자 인터페이스 요소들은 이와 관련된 도움말 기능이 있기 때문
- Button
  - Widget의 서브클래스이고, Button의 생성자는 자신을 포함하는 객체 대한 참조자와 도움말 항목 정보를 매개변수로 받아들임
  - 도움말 항목이 있다면 도움말을 보여주고 객체 찾는 일은 끝, 도움말이 없다면 다음 객체에게 전달
- Dialog
  - Button과 비슷하지만 차이는 Dialog 객체 다음에 오는 객체는 위젯 객체가 아니라 임의의 HelpHandler 클래스의 인스턴스
  - 해당 예제에서는 Application 인스턴스가 다음 후보로 옴
- Application
  - 응용프로그램은 위젯이 아니기 때문에 HelpHandler 클래스를 직접 상속
  - 도움말 요청이 이 단계까지 전달되었으면 응용프로그램에 대한 정보를 제공
- Client
  - HelpHandler 클래스를 호출하여 도움말을 요청 (handleHelp() 메서드 호출)

- 최종적인 호출 형태
  - ![chain1](https://user-images.githubusercontent.com/7076334/137943545-3920b292-1230-488f-895b-c244ab24fbf4.png)

## 잘 알려진 사용예
- java try-catch
- spring security filter chain

## 관련 패턴
- 책임 연쇄 패턴은 복합체 패턴과 함께 대부분 사용되는데, 이때 구성요소의 부모는 후속 처리자처럼 동작함

## 참고
- https://k0102575.github.io/articles/2020-02/chain-of-responsibility-pattern

# 명령(COMMAND)
## 의도
- 요청 자체를 캡슐화 하는 것
- 이를 통해 요청이 서로 다른 사용자를 매개변수로 만들고, 요청을 대기시키거나 로깅하며, 되돌릴 수 있는 연산을 지원

## 다른 이름
- 작동(Action), 트랜잭션(Transaction)

## 동기
- 명령 패턴은 툴킷 객체가 요청 자체를 객체로 바꿈으로써 명시되지 않은 응용프로그램 객체의 요청을 처리할 수 있도록 지원하는 패턴
- 이 패턴의 핵심은 연산을 실행하는데 필요한 인터페이스를 선언해 놓은 Command 추상 클래스임
  - 가장 기본적인 연산이 Execute()
  - Command 추상 클래스에서 상속받은 Command 서브클래스들은 수신 객체에 대한 참조자를 인스턴스 변수로 저장하며, 이 수신 객체에 정의된 요청을 호출하도록 Execute()를 구현하여 수신자-작동 쌍을 정의
  - 수신 객체는 자신에게 전달된 요청을 어떻게 처리해야 하는지 알고 있음

- 메뉴예제
  - ![command2](https://user-images.githubusercontent.com/7076334/137965098-7f8cd83c-dd64-4407-9fab-710b4bb7a95f.png)
  - MenuItem(Invoker), Document(Receiver)로 보면 될까?
  - 응용프로그램은 각 MenuItem 객체를 Command의 어떤 서브클래스 인스턴스와 연결되도록 설정
  - 사용자가 MenuItem 인스턴스를 선택하면 MenuItem 인스턴스는 연결된 Command 서브클래스의 Execute() 연산 호출
    - MenuItem는 Command의 어떤 서브클래스가 사용되는지 모름
    - Command 서브클래스들에 요청을 처리해 줄 객체에 대한 정보가 있으며, 이들 객체에 하나 이상의 연산을 호출
      - PasteCommand
        - 클립보드에 있는 내용을 문서로 붙이는 역할
        - Document의 Paste()를 호출
      - OpenCommand
        - 사용자에게 열어야 하는 문서의 이름을 물어 보고 해당하는 Document 객체를 생성한 다음, 응용프로그램에서 다루는 문서로 첨가 후 염
      - MacroCommand
        - 일련의 명령어를 순차적으로 처리해야 할 때

- 위의 예제를 보면 Command 패턴이 연산을 호출하는 객체와 이를수행하는 객체를 분리하고 있음을 알 수 있음
  - 이로써 사용자 인터페이스를 구현하는 데 많은 융통성을 부여할 수 있음
  - 명령어를 동적으로 교체 가능
    - 사용자 인터페이스는 동일한데, 선택시에 처리되는 방식을 달리하려면 Command를 상속하는 새로운 클래스만 정의  


## 활용성
-

## 구조
- ![command1](https://user-images.githubusercontent.com/7076334/137960412-5dba86fb-324d-480c-8ad3-014683933ba0.png)

## 참여자
- Command : 연산 수행에 필요한 인터페이스를 선언
- ConcreteCommand(PasteCommand, OpenCommand) : 
  - Receiver 객체와 액션 간의 액션 간의 연결성을 정의함
  - 또한 처리 객체에 정의된 연산을 호출하도록 Execute를 구현
- Client(Application) : ConcreteCommand 객체를 생성하고 처리 객체로 정의
- Invoker(MenuItem) : 명령어에 처리를 수행할 것을 요청
- Receiver(Document, Application)
  - 요청에 관련된 연산 수행 방법을 알고 있음
  - 어떤 클래스도 요청 수신자로서 동작할 수 있음

