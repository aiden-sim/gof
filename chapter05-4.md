# 메멘토(MEMENTO)
## 의도
- 캡슐화를 위배하지 않은 채 어떤 객체의 내부 상태를 잡아내고 실체화시켜 둠으로써, 이후 해당 객체가 그 상태로 되돌아올 수 있도록 함

## 다른 이름
- 토큰(Token)

## 동기
- 객체를 이전 상태로 북구하려면 복구할 시점의 상태 정보가 있어야 함
  - 그러나 객체는 자체적으로 상태의 일부나 전부를 캡슐화하여 상태를 외부에 공개하지 않기 때문에, 다른 객체는 상태에 접근하지 못함 
  - 이 상태를 외부에 노출하는 것은 캡슐화 위반이지만 응용프로그램의 확장성 및 신뢰도와 절충할 수 있는 부분임

- 메멘토 패턴은 다른 객체, 다른 말로 원조본(Originator) 객체가 가진 내부 상태의 스냅샷을 저장하는 객체
  - 이것을 사용하면, 실행 취소 메커니즘은 원조본 객체의 상태를 확인할 필요가 있을 때 원조본이 메멘토에게 상태를 알려달라는 요청을 보낼 수 있음
  - 원조본은 현재 상태를 알려주는 정보로 메멘토를 초기화
  - 메멘토에 정보를 저장하고 메멘토에서 정보를 검색할 수 있는 것은 원조본의 자격을 가진 객체 뿐

- 실행 취소 절차 순서
  - 1) 편집기는 ConstraintSolver(원조본) 클래스에서 이동 연산의 부수 효과로 얻어지는 것으로 메멘토를 요청
  - 2) ConstraintSolver 클래스는 메멘토를 생성해서 반환하는데, 이 메멘토는 SolverState 클래스의 인스턴스임
    - SolverState 메멘토는 ConstraintSolver의 내부 수식과 변수의 상태를 표현하는 자료 구조를 포함
  - 3) 이후 사용자가 이동 연산을 취소할 때, 편집기는 SolverState 클래스를 ConstraintSolver 클래스에 돌려줌 (메멘토가 돌려 주는 것이겠지?)
  - 4) SolverState 클래스에 있는 정보를 바탕으로 ConstraintSolver 클래스는 자신의 내부 구조를 변경하여 수식 및 변수를 이전 상태로 정확하게 돌림

## 활용성
- 메멘토 패턴은 다음의 경우에서 사용
  - 어떤 객체의 상태에 대한 스냅샷을 저장한 후 나중에 이 상태로 북구해야 할 때
  - 상태를 얻는 데 필요한 직접적인 인터페이스를 두면 그 객체의 구현 세부 사항이 드러날 수 밖에 없고, 이것으로 객체의 캡슐화가 깨질 때

## 구조
- ![memento1](https://user-images.githubusercontent.com/7076334/139362317-1ee4d458-386e-469b-af22-d482deb2d7a0.png)


## 참여자
- Memento(SolverState)
  - 원조본 객체의 내부 상태를 필요한 만큼 저장
  - 메멘토는 원조본 객체를 제외한 다른 객체는 자신에게 접근할 수 없도록 막음
  - 메멘토의 두 종류 인터페이스
    - 관리 책임을 갖는 Caretaker 클래스는 Memento에 제한 범위 인터페이스만을 볼수 있음
    - Originator 클래스에게는 광범위 인터페이스가 보임
      - 이상적으로 메멘토를 생성하는 원조본 객체만이 메멘토의 내부 상태에 접근할 수 있는 권한을 갖음
- Originator(ConstraintSolver) : 원조본 객체로, 메멘토를 생성하여 현재 객체의 상태를 저장하고 메멘토를 사용하여 내부 상태를 복원
- Caretaker(실행 취소 메커니즘) : 메멘토의 보관을 책임지는 보관자. 메멘토의 내용을 검사하거나 그 내용을 건드리지는 않음

## 협력 방법
- ![memento2](https://user-images.githubusercontent.com/7076334/139363226-994dffd4-319f-47f0-9916-f35fbe838ac9.png)
  - 보관자(Caretaker) 객체는 원조본 객체에 메멘토 객체를 요청함. 또 요청한 시간을 저장하며, 받은 메멘토 객체를 다시 원조본 객체에게 돌려줌
  - 보관자 객체는 메멘토 객체를 원조본 객체에 전달하지 않을 수도 있음 (원조본 객체가 이전 상태로 돌아갈 필요가 없는 경우)
  - 메멘토 객체는 수동적임. 메멘토 객체를 생성한 원조본 객체만이 상태를 설정하고 읽어올 수 있음

## 결과
- 메멘토 패턴을 사용했을 때의 결과
  - 1) 캡슐화된 경계를 유지할 수 있음
    - 원조본만 메멘토를 다룰 수 있기 때문에 메멘토가 외부에 노출되지 않음
    - 이 패턴은 복잡한 Originator 클래스의 내부 상태를 다른 객체로 분리하는 방법으로 상태에 대한 정보의 캡슐화를 보장 
  - 2) Originator 클래스를 단순화할 수 있음
    - 사용자가 자신들이 필요한 상태를 별도로 관리하게 하면 Originator 클래스는 간단해지고, 상태를 변경할 때마다 사용자가 이를 원조본에 알려줄 필요도 없음 
  - 3) 메멘토의 사용으로 더 많은 비용이 들어갈 수도 있음
    - Originator 클래스가 많은 양의 정보를 저장해야 할 때나 상당히 자주 메멘토를 반환해야 할 때라면 메멘토가 상당한 오버헤드를 가져올 수 있음
    - Originator 클래스의 상태를 보호하는 비용과 상태 복구의 비용이 싸지 않으면, 메멘토 패턴은 적합하지 않음
  - 4) 제한 범위 인터페이스와 광범위 인터페이스(Originator 접근) 를 정의해야 함
    - 어떤 프로그래밍 언어에서는 원조본 객체만 메멘토의 상태에 접근할 수 있도록 보장하기가 힘들 수 있음
    - ex) 자바는 접근 제한자를 통해서 제어 
  - 5) 메멘토를 관리하는 데 필요한 비용이 숨어 있음
    - 보관자 객체는 자신이 보관하는 메멘토를 삭제할 책임이 있음
    - 보관자는 얼마나 많은 상태가 메멘토에 저장되어 있는지 알 수 없기 때문에 메멘토를 저장할 때 적지 않은 저장 비용이 유발될 수도 있음

## 구현
- 메멘토 패턴 구현 시, 고려사항
  - 1) 언어의 지원 여부
    - 메멘토에는 두 종류의 인터페이스가 있음. 제한 범위 인터페이스와 광범위 인터페이스 (반복적인 내용)
    - 이상적으로 구현 언어에서 수준이 서로 다른 정적 보호(static protection) 정책을 지원할 수 있음
    - C++의 프렌드 클래스 좋아 보임
      - java 에서는 OOP 위반이라 생각해서 고슬링 아저씨가 넣지 않은듯
      - 참고) https://stackoverflow.com/questions/182278/is-there-a-way-to-simulate-the-c-friend-concept-in-java
  - 2) 점증적 상태 변경을 저장 
    - 메멘토가 생성되어 다시 원조본에게 반환되면 메멘토는 원조본의 내부 상태 변경 과정을 지속적으로 저장해야 됨
      - 변경된 정보들만 계속 저장
    - 메멘토는 모든 객체의 전체 객체를 저장하기보다는 각 명령어가 수행되어 변경이 발생한 부분, 즉 점증적 상태 변화를 저장해야 함

## 예제 코드
```
/**
 * caretaker
 */
public class MoveCommand {
    private ConstraintSolver.ConstraintSolverMemento state;
    private Point delta;
    private Graphic target;

    public MoveCommand(Graphic target, Point delta) {
        this.target = target;
        this.delta = delta;
    }

    public void execute() {
        ConstraintSolver solver = ConstraintSolver.getInstance();
        state = solver.createMemento();
        target.move(delta);
        solver.solve();
    }

    public void unexecute() {
        ConstraintSolver solver = ConstraintSolver.getInstance();
        target.move(Point.ZERO.sub(delta));
        solver.setMemento(state);
        solver.solve();
    }
}

/**
 * originator
 */
public class ConstraintSolver {
    private static ConstraintSolver instance;

    private ConstraintSolver() {
        instance = null;
    }

    public void solve() {
    }

    public void addConstraint(Graphic startConnection, Graphic endConnection) {
    }

    public void removeConstraint(Graphic startConnection, Graphic endConnection) {
    }

    /**
     * memento
     */
    public class ConstraintSolverMemento {
        private ConstraintSolverMemento(ConstraintSolver constraintSolver) {
        }
    }

    public ConstraintSolverMemento createMemento() {
        return new ConstraintSolverMemento(this);
    }

    public void setMemento(ConstraintSolverMemento memento) {
    }

    public static ConstraintSolver getInstance() {
        if (instance == null) {
            instance = new ConstraintSolver();
        }
        return instance;
    }
}
```
- MoveCommand(CareTaker)
  - 그래픽 객체를 한 위치에서 다른 위치로 이동 시키는 역할
  - execute() 연산을 호출하여 그래픽 객체를 이동시키고, unexecute() 연산을 호출하여 이동 취소
- ConstraintSolver(Originator)
  - 객체 간에 연결되어 있을 때의 제약 조건들을 처리
- ConstraintSolverMemento(Memento)

## 잘 알려진 사용예
- 문서 편집기 실행취소

- 메멘토 기반의 반복 인터페이스 장점
  - 1) 하나 이상의 상태가 동일한 컬렉션에 동작하게 할 수 있다는 것
  - 2) 컬렉션 클래스의 캡슐화를 파괴하지 않는다는 것

## 관련 패턴
- 명령 패턴은 실행 취소가 가능한 연산의 상태를 저장할 때 메멘토 패턴을 사용할 수 있음
- 메멘토 패턴은 반복자 패턴에서의 반복 과정 상태를 관리할 수 있음
