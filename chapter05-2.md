# 해석자
## 의도
- 어떤 언어에 대해, 그 언어의 문법에 대한 표현을 정의하면서 그것을 사용하여 해당 언어로 기술된 문장을 해석하는 해석자를 함께 정의

## 동기
- 특정한 종류의 문제가 자주 발생할 경우?
  - 어떤 간결한 언어를 써서 그 문제를 문장으로 표현하는 것이 나을 수 있음
  - 그리고 그 문장을 해석하는 해석자를 만들어 문장을 해석하게 하여 문제를 해결
  - 이때, 해결에 필요한 코드를 작성하기보다는 간단한 언어로 문장을 반복적으로 작성하기만 하면 문제가 해결될 수 있음 

- 해석자 패턴은 간단한 언어의 문법을 정의하는 방법과 그 언어로 문장을 구성하는 방법, 이들 문장을 해석하는 방법을 설명

- 정규 표현식 예제
```
expression ::= literal | alternation | sequence | repetition | '(' expression ')'
alternation ::= expression '|' expression
sequence ::= expression '&' expression
repetition ::= expression '*'
literal ::= 'a' | 'b' | 'c' | ... { 'a' | 'b' | 'c' | ... }*
```
  - expression 기호는 문법의 시작 기호
  - literal은 간단한 단어를 정의하는 터미널 기호

- ![interpreter1](https://user-images.githubusercontent.com/7076334/138876304-feedb40f-a8e7-4cf3-bd40-0512f176c1f8.png)
  - RegularExpression을 추상 클래스로 정의하고 나머지들은 이 추상 클래스를 상속하는 서브클래스로 정의
    - 서브 클래스들은 LiteralExpression, AlternationExpression, SequenceExpression, RepetitionExpression
    - AlternationExpression, SequenceExpression, RepetitionExpression 은 자신이 포함한 내부 표현식을 (expression)을 저장할 인스턴스 변수를 정의
  - 문법이 정의한 각각의 정규 표현식을 추상 구문 트리로 표현할 수 있음  (P.326)
  - 정규 표현식을 정의하였으면 RegularExpression의 서브클래스에 해석과 관련된 연산(Interpret 연산)을 정의하여 정규 표현식을 해석하는 해석자를 만들자
    - Interpret() 연산은 문장을 해석하기 위해서 문장이 속한 문맥에 대한 정보를 매개변수로 받아야 함
    - 상황에 대한 정보는 입력 문자열이 무엇이고 지금까지 어떻게 일치되는 내용을 찾아왔는지에 대한 상태 정보를 포함
  - RegularExpression의 각 서브클래스는 Interpret() 연산을 구현하여 현재 상황을 근거로 입력 문자열에서 다음번 일치하는 문자열을 발견
    - LiteralExpression : 자신이 정의한 문자와 일치하는 정보가 입력 매개변수에 있는지 확인
    - AlternationExpression : 입력 매개변수에 다른 대안 문자들과 일치하는 것이 있는지 확인
    - RepetitionExpression : 입력에 자신이 반복하는 표현에 대한 여러 개의 복사본이 존재하는지 확인
  - 각각의 서브클래스는 Interpret() 연산에 필요한 구현을 정의하며, 정규 표현식을 해석하는 다양한 방법을 정의

## 활용성
- 해석이 필요한 언어가 존재하거나 추상 구문 트리로서 그 언어의 문장을 표현하고자 한다면 해석자 패턴을 사용할 때
- 해석자 패턴이 가장 잘 먹힐때?
  - 정의할 언어의 문법이 간단함
    - 문법이 복잡하다면 분법을 정의하는 클래스 계통이 복잡해지고 관리할 수 없게 됨
    - 이는 해석자 패턴 보다 파서 생성기 같은 도구를 사용하는게 더 나음
  - 효율성은 별로 고려할 사항이 아님
    - 가장 효율적인 해석자를 구현하는 방법은 파스 트리를 직접 해석하도록 만드는 것이 아니라, 일차적으로 파스트리를 다른 형태로 번역 시키는 것

## 구조
- ![interpreter2](https://user-images.githubusercontent.com/7076334/138882161-ebfa46e3-e022-40b7-9d6d-55132233a330.png)

## 참여자
- AbstractExpression(RegularExpression) : 추상 구문 트리에 속한 모든 노드에 해당하는 클래스들이 공통으로 가져야 할 Interpret() 연산을 추상 연산으로 정의
- TerminalExpression(LiteralExpression)
  - 문법에 정의한 터미널 기호와 관련된 해석 방법을 구현
  - 문장을 구성하는 모든 터미널 기호에 대해서 해당 클래스를 만들어야 함
- NonterminalExpression(AlternationExpression, RepetitionExpression, SequenceExpression)
  - 문법의 오른편에 나타나는 모든 기호에 대해서 클래스를 정의해야 함 
  - R ::= R1R2....Rn
    - R에 대한 NonterminalExpression 클래스 정의
    - R1 ~Rn에 이르기 까지 모든 기호에 대응하는 인스턴스 변수 정의
    - 터미널 기호가 아닌 모든 기호들에 대해서 Interpret() 연산을 구현 (R1 ~ Rn 에 이르기까지 각 인스턴스 변수를 재귀적으로 호출하는 것이 일반적)
- Context : 번역기에 대한 포괄적인 정보를 포함
- Client
  - 언어로 정의한 특정 문장을 나타내는 추상 구문 트리
  - 추상 구문 트리는 NonterminalExpression과 TerminalExpression 클래스의 인스턴스로 구성됨
  - 이 인스턴스의 Interpret() 연산을 호출

## 협력방법
- 사용자는 NonterminalExpression과 TerminalExpression 인스턴스들로 해당 문장에 대한 추상 구문 트리를 만듬
  - 그리고 Interpret() 연산을 호출하는데, 이때 해석에 필요한 문맥 정보를 초기화함
- 각 NonterminalExpression 노드는 또 다른 서브 표현식에 대한 Interpret()를 이용하여 자신의 Interpret() 연산을 정의
  - Interpret() 연산은 재귀적으로 Interpret() 연산을 이용하여 기본적 처리를 담당
- 각 노드에 정의한 Interpret() 연산은 해석자의 상태를 저장하거나 그것을 알기 위해서 문맥(context) 정보를 이용

## 결과
- 해석자 패턴의 장점과 단점
  - 1) 문법의 변경과 확장이 쉬움
    - 패턴에 문법에 정의된 규칙을 클래스로 표현하였기 때문에 문법을 변경하거나 확장하고나 할 때는 상속을 이용
    - 확장을 통해 기존의 표현식을 지속적으로 수정하거나 새로운 서브클래스 정의를 통해 새로운 표현식을 정의할 수 있음
  - 2) 문법의 구현이 용이
    - 추상 구문 트리의 노드에 해당하는 클래스들은 비슷한 구현 방법을 갖음
    - 이들 클래스를 작성하는 것은 쉬운 일이며, 컴파일러나 파서 생성기를 이용해서 자동 생성할 수도 있음
  - 3) 복잡한 문법은 관리하기 어려움
    - 해석자 패턴은 문법에 정의된 각 규칙별로 적어도 하나의 클래스를 정의
    - 문법이 복잡해지면, 컴파일러 생성기나 파서 생성기를 사용하는 것이 바람직함
  - 4) 표현식을 해석하는 새로운 방법을 추가할 수 있음 
    - 해석자 패턴은 새로운 방식으로 정의된 표현식을 쉽게 해석할 수 있게 해줌
    - 새로운 방식으로 해석하는 방법을 계속 추가해야 한다면 문법 클래스 변경을 피하기 위해 방문자 패턴을 이용하는 것이 좋음

## 구현
- 다음은 해석자 패턴에만 국한된 고려 사항(복합체 패턴 배제)
  - 1) 추상 구문 트리를 생성
    - 어떻게 추상 구문 트리를 생성하는지 다루지 않음(파싱 과정을 다루는 패턴이 아님)
    - 추상 구문 트리는 테이블을 이용한 파서로 만들거나 재귀 하향식 파서를 사용자가 직접 만들어야 함
  - 2) Interpret() 연산을 정의
    - 표현식에 해당하는 클래스에서 Interpret() 연산을 정의할 필요는 없음
    - 만약 새로운 해석자를 생성하는 것이 일반적이라면 방문자 패턴을 사용해서 분리한 방문자 객체에게 해석의 책임을 지우는 것이 바람직함 
  - 3) 플라이급 패턴을 적용하여 단말 기호를 공유
    - 단말(terminal) 기호가 여러번 나타나는 문장은 그 기호에 대한 인스턴스를 공유 하는 것이 바람직함

## 예제코드
- 요건
  - 불(boolean) 식을 처리하는 시스템
  - 터미널 기호는 불 변수이고 상수 값은 참과 거짓
  - 비단말(nonterminal) 기호는 and, or, not의 연산자를 포함한 식

- 문법을 정의
```
BooleanExp ::= VariableExp | Constant | OrExp | AndExp | NotExp | '(' BooleanExp ')'
AndExp ::= BooleanExp 'and' BooleanExp
OrExp ::= BooleanExp 'or' BooleanExp
NotExp ::= 'not' BooleanExp
Constant ::= 'true' | 'false'
VariableExp ::= 'A' | 'B' | ... | 'X' | 'Y' | 'Z'
```


```
/**
 * AbstractExpression
 */
public interface BooleanExp {
    boolean evaluate(Context context);

    BooleanExp replace(String variable, BooleanExp expression);

    BooleanExp copy();
}

/**
 * Context
 */
public class Context {
    private Map<String, Boolean> values = new HashMap<String, Boolean>();

    public Boolean lookup(String variable) {
        return values.get(variable);
    }

    public void assign(VariableExp variable, boolean value) {
        System.out.println("Przypisanie " + variable.getName() + " = " + value);
        values.put(variable.getName(), value);
    }
}

/**
 * TerminalExpression
 */
public class VariableExp implements BooleanExp {
    private String name;

    public VariableExp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean evaluate(Context context) {
        return context.lookup(name);
    }

    @Override
    public BooleanExp replace(String variable, BooleanExp expression) {
        if (variable != null && variable.equals(name)) {
            return expression.copy();
        } else {
            return this.copy();
        }
    }

    @Override
    public BooleanExp copy() {
        return new VariableExp(name);
    }
}

/**
 * NonterminalExpression
 */
public class AndExp implements BooleanExp {
    private BooleanExp operand1;
    private BooleanExp operand2;

    public AndExp(BooleanExp operand1, BooleanExp operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public boolean evaluate(Context context) {
        return operand1.evaluate(context) && operand2.evaluate(context);
    }

    @Override
    public BooleanExp replace(String variable, BooleanExp expression) {
        return new AndExp(operand1.replace(variable, expression),
                          operand2.replace(variable, expression));
    }

    @Override
    public BooleanExp copy() {
        return new AndExp(operand1.copy(), operand2.copy());
    }
}

public class Client {
    public static void main(String[] args) {
        BooleanExp expression;
        Context context = new Context();

        VariableExp x = new VariableExp("X");
        VariableExp y = new VariableExp("Y");

        // (true and x) or (y and (not x))
        expression = new OrExp(new AndExp(new Constant(true), x), new AndExp(y, new NotExp(x)));

        context.assign(x, false);
        context.assign(y, true);

        boolean result = expression.evaluate(context);
        System.out.println("Wynik ((true and x) or (y and (not x))) = " + result);
    }
}
```
- BooleanExp (AbstractExpression)
  - 불 식을 정의하는 모든 클래스에 공통인 인터페이스를 정의
  - 참이나 거짓의 값을 확인할 수 있는 Evaluate() 연산 필요
  - 변수를 또 다른 식으로 대체하여 새로운 불 식을 생성할 수 있는 Replace() 필요
  - 추상 구문 트리에 속한 모든 노드가 공통으로 가져야 할 Interpret() 연산을 추상 연산으로 정의

- Context (Context)
  - 변수를 불 값에 대응 시키는 것 (true / false)
  - 번역기에 대한 포괄적인 정보를 포함 

- VariableExp (TerminalExpression)
  - 이름을 갖는 변수를 클래스로 정의
    - evaluate : 현재 상황에서의 값을 알아낸 후 반환
    - copy : 변수를 복사하는 것은 새로운 VariableExp 객체를 반환하는 것
    - replace : 변수를 수식으로 대체하기 위해서는 변수가 인자로 전달받는 것과 같은 이름인지 확인
  - 문장을 구성하는 모든 터미널 기호에 대해서 해당 클래스를 만들어야 함 

- AndExp (NonterminalExpression)
  - 두 불 식을 AND 연산 정의
    - evaluate : 피연산자를 확인해서 'And'의 논리 연산을 수행한 결과를 반환
    - copy, replace : 연산을 구현할 때 자신의 피연산자에 대한 재귀적 호출을 이용

- Client
  - 언어로 정의한 특정 문장을 나타내는 추상 구문 트리 ( NonterminalExpression, TerminalExpression 로 구성)
  - Interpret() 연산 호출 (evaluate)

- 해석자 패턴은 복합체 패턴에서처럼 단순히 연산을 여러 클래스 계통에 걸쳐 분산하는 것 이상의 의미를 가짐
  - Evaluate() 연산을 해석자로 간주한 이유는 BooleanExp 클래스를 하나의 언어(true/false) 로 정의하였기 때문

## 잘 알려진 사용예
- 객체지향 컴파일러 구현
- java interpreter

- 가장 일반적인 형태는 복합체 패턴이 사용된든 곳에 해석자 패턴을 사용할 수 있음
  - 그러나 복합체 패턴으로 정의한 클래스들이 하나의 언어 구조를 정의할 때만 해석자 패턴이라고 함 

## 관련 패턴
- 추상 구문 트리는 복합체 패턴의 한 인스턴스로 볼 수 있음
- 하나의 구문 트리내에 터미널 기호를 여러 개 공유하기 위해서는 플라이급 패턴을 적용 가능
- 해석자는 반복자 패턴(iterator)을 이용해서 자신의 구조를 순회
- 방문자 패턴을 이용하면 하나의 클래스에 정의된 구문 트리 각 노드에 대한 상태를 관리할 수 있음

## 참고
- BNF : https://ko.wikipedia.org/wiki/%EB%B0%B0%EC%BB%A4%EC%8A%A4-%EB%82%98%EC%9A%B0%EB%A5%B4_%ED%91%9C%EA%B8%B0%EB%B2%95


# 반복자(ITERATOR)
## 의도
- 내부 표현부를 노출하지 않고 어떤 집합 객체에 속한 원소들을 순차적으로 접근할 수 있는 방법을 제공

## 다른 이름
- 커서(Cursor)

## 동기
- 이런 문제를 해결하는 데 사용하는 것이 반복자 패턴
  - 순회 방법이 바뀌었따고 List 클래스의 인터페이스를 부풀리고 싶지는 않을 것
  - 동일한 리스트에 대해서 하나 이상의 순회 방법을 정의하고 싶을 때

- 반복자 패턴의 주요 골자는 리스트 객체에 접근해서 새로운 내용을 삽입, 삭제하거나 순회하는 내용을 반복자 객체에 정의하는 것
  - 반복자 객체를 나타내는 Iterator 클래스는 리스트의 원소들에 접근하는 데 필요한 인터페이스를 제공
  - 즉 Iterator 객체는 현재 원소가 무엇인지 관리하고, 이미 방문한 원소들이 무엇인지 알고 있음 

- ![iterator](https://user-images.githubusercontent.com/7076334/138907141-93a530a9-5036-4f3c-aa2c-d0ca7b2267a8.png)
  - List 클래스는 ListIterator 클래스에 대해 그림과 같은 관련성을 유지
  - ListIterator 클래스의 인터페이스를 생성하기 전에 먼저 순회 주체가 되는 List 객체를 생성해야 됨
  - ListIterator 클래스의 인스턴스를 생성하고 나면 이를 이용해서 리스트 원소에 접근할 수 있게 됨
    - CurrentItem() 연산은 리스트의 현재 원소를 알아 내는 연산
    - First() 연산은 현재 원소를 리스트 첫 번째 원소로 초기화하는 연산
    - Next() 다음 원소를 순회 과정 중의 현재 원소로 지정함
    - IsDone()은 순회할 원소가 더 있는지 없는지 확인하는 연산

- 순회 메커니즘을 클래스 List에서 분리시키면, List 클래스의 인터페이스를 변경하지 않으면서 다양한 순회 알고리즘을 구현할 수 있음

- Iterator 클래스와 List 클래스가 한 쌍으로 묶여있기 때문에, 사용자는 어떤 집합 구조에 대해서 순회할 주체가 리스트인지 알아야 됨
  - 따라서 사용자는 반드시 특정한 집합 구조에 따라 가야함
  - List의 복합 구조를 변경하더라도 사용자 코드는 변경하지 않도록 만들면 더욱 좋음
  - 이렇게 하려면 반복자의 개념을 일반화하여 '다형성을 지닌 반복'이 가능하도록 하면 됨

- ![iterator2](https://user-images.githubusercontent.com/7076334/138909526-f2b8ab7c-26e2-46d1-afd2-6f62437c4f9f.png)
  - 다양한 리스트를 조작하는데 필요한 공통의 인터페이스를 AbstractList 클래스에 정의
  - 리스트를 계속 반복해서 접근하는 데 필요한 공통 인터페이스를 정의하는 Iterator 추상 클래스가 필요
    - 서로 다른 리스트의 구현 방식마다 구체적인 반복 기법을 제공하는 Iterator 클래스의 서브클래스를 제공하면 됨
    - ex) List <-> ListIterator, SkipList <-> SkipListIterator
  - 이렇게 하면 반복 메커니즘의 원소들이 어떤 집합 구조로 되어 있는가로부터 독립될 수 있음 (사용자는 추상 클래스만 접근)
  - CreateIterator()와 같은 연산을 이용해서 Iterator를 얻을 수 있도록 함
    - 팩토리 메서드 패턴 사용 가능  

## 활용성
- 반복자 패턴의 목적
  - 객체 내부 표현 방식을 모르고도 집합 객체의 각 원소들에 접근하고 싶을 때
  - 집합 객체를 순회하는 다양한 방법을 지원하고 싶을 때
  - 서로 다른 집합 객체 구조에 대해서도 동일한 방법으로 순회하고 싶을 때 

## 구조
- ![iterator3](https://user-images.githubusercontent.com/7076334/138911835-8fdc1921-1603-4176-b0ab-c3e29b89806b.png)

## 참여자
- Iterator : 원소를 접근하고 순회하는 데 필요한 인터페이스를 제공
- ConcreteIterator : Iterator에 정의된 인터페이스를 구현하는 클래스로, 순회 과정 중 집합 객체 내에서 현재 위치를 기억
- Aggregate : Iterator 객체를 생성하는 인터페이스를 정의
- ConcreteAggregate : 해당하는 ConcreteIterator의 인스턴스를 반환하는 Iterator 생성 인터페이스를 구현

## 협력 방법
- ConcreteIterator는 집합 객체 내 현재 객체를 계속 추적하고 다음번 방문할 객체를 결정

## 결과
- Iterator 패턴의 주요 특징
  - 1) 집합 객체의 다양한 순회 방법을 제공
    - 구조가 복잡한 집합 객체는 다양한 방법으로 순회할 수 있음
    - 새로운 순회 방법을 Iterator 서브클래스로 정의하여 기존 순회 방법을 다른 순회 알고리즘 인스턴스로 교체 가능
  - 2) Iterator는 Aggregate 클래스의 인터페이스를 단순화함
    - Iterator 순회 인터페이스는 Aggregate 클래스에 정의한 자신과 비슷한 인터페이스들을 없애서 Aggregate 인터페이스를 단순화할 수 있음
  - 3) 집합 객체에 따라 하나 이상의 순회 방법이 제공될 수 있음 
    - 각 Iterator마다 자신의 순회 상태가 있으므로 하나의 집합 객체를 한번에 여러 번 순회시킬 수 있음

## 구현
- 반복자 패턴은 다양한 구현이 가능
- 프로그래밍 언어가 제공하는 제어 구조에 따라 달라질 수 있음
  - 1) 누가 반복을 제어하게 할까?
    - 사용자가 반복을 제어할 때, 외부 반복자라고 함
      - 외부 반복자를 사용하는 사용자 프로그램은 순회를 계속하고 다음번 원소를 명시적으로 반복자에게 요청해야 함 
    - 반복자 자신이 제어를 담당하면 내부 반복자라고 함
      - 처리할 연산을 내부 반복자에게 넘겨주고, 해당 반복자는 그 연산을 모든 원소에 적용
    - 외부 반복자가 내부 반복자보다 유연한 방법
      - 외부 반복자를 이용하면 두 집합 객체가 동일한 집합인지 비교가능
      - 내부 반복자는 불가능함 (하지만 순회 처리 방법을 정의하기 때문에 사용하기 쉬움) 
  - 2) 순회 알고리즘을 어디에서 정의할 것인까?
    - Aggregate 클래스에도 순회 알고리즘을 정의하고, Iterator에는 순회 상태만 저장할 수도 있음 (커서)
    - Iterator는 단순히 집합 구조 내 현재 위치만 가리킴
  - 3) 어떻게 반복자를 견고하게 만들 수 있을까?
    - 집합 객체를 순회하는 동안 집합 객체를 수정하는 것을 막기 위해 집합 객체를 복사해 두었다가 그 복사본을 순회하는 것
      - 하지만 추가 비용이 많이 듬 (원형 패턴) 
  - 4) 추가적으로 필요한 반복자 연산
    - Iterator 클래스에 필요한 최소한 연산. (First(), Next(), IsDone(), CurrentItem())
    - 순서가 정해진 집합 객체라면 Previous() 를 사용해서 반복자 위치를 앞으로 이동
    - 인덱스를 갖는 집합 객체는 SkipTo() 연산을 통해 조건에 일치하는 객체로 반복자를 바로 이동
  - 5) C++에서 다형성을 지닌 반복자를 이용하는 방법
    - 다형적인 반복자는 런타임에 팩토리 메서드로 동적으로 반복자 객체를 제공해야 하기 때문에 추가 비용 지불
      - 필요할 때만 사용하는 것이 좋음
    - 다형적인 반복자의 단점 중 하나는 사용자가 직접 반복자를 삭제하는 책임을 져야 한다는 것
      - 오류 발생할 수도 있음   
  - 6) 반복자에는 특수한 접근 권한이 있음
    - 반복자는 반복자를 생성한 집합 객체의 확장으로 바라 볼 수 있음
      - 반복자와 집합 객체 간의 결합도가 매우 커짐
    - C++ 사용하면 프렌드(friend) 정의해서 반복자는 마음대로 집합 객체에 정의된 속성 접근 가능
      - 하지만 새로운 순회 방법을 정의하기 어려움
      - 프렌드 하나 더 추가 시, 객체의 인터페이스 변경이 요구되기 때문에
      - 집합 객체에 정의된 멤버 변수 중에 중요하기는 하지만 공개할 수 없는 멤버 변수에 접근하는 연산을 Iterator 안에 protected로 정의
        - Iterator를 상속하는 서브클래스들은 이 연산을 통해 집합 객체에 접근 
  - 7) 복합체를 위한 반복자
    - 외부 반복자는 재귀적 합성 구조를 처리하도록 구현하기 어려움
      - 이때는 복합체 패턴을 이용해서 자신이 거쳐 온 단계에 대한 정보를 저장해 두어야 함 (내부 반복자는 자기 스스로가 재귀적 호출로 현재 위치를 저장)
  - 8) 널 반복자
    - NullIterator는 반복자이기는 하지만 기능이 미약한 것으로, 영역 판단을 하는데 유용함
    - 널 반복자는 항상 순회 시에 끝나는 반복자로 정의됨 (done)
      - NullIterator는 IsDone() 연산에서 항상 참을 리턴 

## 예제코드
- ![iterator4](https://user-images.githubusercontent.com/7076334/138921044-307f6561-0ab9-4a52-9697-165dcdc98f82.png)

```
/**
 * Aggregate(집합체)
 * Iterator 역할을 만들어내는 인터페이스를 결정
 */
public interface Aggregate {
    Iterator iterator();
}

/**
 * Aggregate(집합체)
 * Iterator 역할을 만들어내는 인터페이스를 결정
 */
public interface Aggregate {
    Iterator iterator();
}

public class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/**
 * ConcreteAggregate(구체적인 집합체)
 * Aggregate 역할이 결정한 인터페이스를 실제로 구현하는 일
 */
public class BookShelf implements Aggregate {
    private Book[] books;
    private int last = 0;

    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    public int getLength() {
        return last;
    }

    public Iterator iterator() {
        return new BookShelfIfIterator(this);
    }
}

/**
 * ConcreteIterator(구체적인 반복자)
 * Iterator가 결정한 인터페이스를 실제로 구현
 */
public class BookShelfIfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index;

    public BookShelfIfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        }
        return false;
    }

    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}

public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));

        /**
         * Array에서 Vector로 변경한다고 해도 변경없이 정상적으로 동작한다.
         */
        Iterator it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = (Book) it.next();
            System.out.println(book.getName());
        }
    }
}

```


## 잘 알려진 사용예
- 대부분 클래스 라이브러리에 정의된 컬렉션 클래스는 한 가지 이상의 방법으로 반복자를 제공

## 관련 패턴
- 반복자 패턴은 복합체 패턴과 같이 재귀적 구조가 있을 때 자주 사용
- 다양한 반복자를 사용해서 적당한 Iterator 서브 클래스를 얻으려면 팩토리 메서드 패턴 사용
- 메멘토 패턴도 반복자 패턴과 함께 자주 사용, 이때 반복자 자신이 반복한 결과를 저장하기 위해 메멘토를 사용

## 참고
- http://www.incodom.kr/%EC%9D%B4%ED%84%B0%EB%A0%88%EC%9D%B4%ED%84%B0_%ED%8C%A8%ED%84%B4

