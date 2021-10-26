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
  - RegularExpression


## 활용성

## 구조

## 참여자

## 협력방법

## 결과

## 구현

## 예제코드

## 잘 알려진 사용예

## 관련 패턴

