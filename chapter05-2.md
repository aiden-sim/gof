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

```
- BooleanExp
  -  
  - 참이나 거짓의 값을 확인할 수 있는 Evaluate() 연산 필요
  - 변수를 또 다른 식으로 대체하여 새로운 불 식을 생성할 수 있는 Replace() 필요


## 잘 알려진 사용예

## 관련 패턴

## 참고
- BNF : https://ko.wikipedia.org/wiki/%EB%B0%B0%EC%BB%A4%EC%8A%A4-%EB%82%98%EC%9A%B0%EB%A5%B4_%ED%91%9C%EA%B8%B0%EB%B2%95


