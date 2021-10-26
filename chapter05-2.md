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
    -  
  - 2) 문법의 구현이 용이
    - 
  - 3) 복잡한 문법은 관리하기 어려움
    - 
  - 4) 표현식을 해석하는 새로운 방법을 추가할 수 있음 
    - 

## 구현


## 예제코드

## 잘 알려진 사용예

## 관련 패턴

