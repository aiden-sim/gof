# 원형(PROTOTYPE) / 객체생성
## 의도 ('이 디자인 패턴은 무엇을 하는것일까요? 의도와 논리적인 근거가 무엇일까요? 어떤 특정한 문제나 이슈를 해결하기 위한 것일까요?' 에 대한 간결한 답을 제시)
- 원형이 되는 인스턴스를 사용하여 생성할 객체의 종류를 명시하고, 이렇게 만든 견본을 복사해서 새로운 객체를 생성

## 동기 (시나리오)
- 그래픽 편집기로 음계에 대한 편집기를 만들 때 문제점
  - 음표와 보표에 대한 클래스는 개발할 응용프로그램에만 국한된 것
  - GraphicTool 클래스는 범용적인 프레임워크에 속해있음
    - GraphicTool은 악보에 추가할 음악 클래스들의 인스턴스를 어떻게 생성해야 하는지 모름
  - 음악 객체를 각각 생성하려면? (팩토리 메서드 패턴 얘기 하는듯)
    -  GraphicTool을 상속받는 새로운 서브클래스를 만들어야 함
    -  인스턴스화해야 하는 음악 객체 종류마다 여러 가지 다른 서브클래스를 만들어야 
 
- ![image1](https://user-images.githubusercontent.com/7076334/131447876-6232c13b-c3bc-4693-93f0-3123b521f68d.png)
 
- 해결 방법
  - GraphicTool 클래스가 Graphic 서브클래스의 인스턴스를 복제해서 새로운 Graphic 인스턴스 생성
    - 복제된 인스턴스를 '원형' 이라고 함
    - 모든 Graphic 서브클래스가 Clone 연산을 제공하면 GraphicTool 클래스는 어떤 종류의 Graphic 클래스도 복제 가능
  - 음악 객체를 생성하는 각 도구는 GraphicTool 클래스의 인스턴스이고, 이들은 서로 다른 원형으로 초기화됨
    - GraphicTool 클래스의 인스턴스는 이 원형을 복제하여 음악 객체를 만들며, 복자본을 악보에 추가

- 원형 패턴 사용
  - 클래스를 더 줄일 수 있음
  - 반음표, 온음표 등을 같은 클래스의 인스턴스로 생성하되, 값을 이용해 초기화
    - 클래스는 동일하게 하고, 안에 정의될 값이나 구조를 달리하는 것
 
## 활용성 ('해당 패턴을 어떤 상황에 적용할 수 있을까요? 패턴이 문제로 삼는 잘못된 설계의 예는 무엇일까요? 이 상황을 어떻게 평가할 수 있을까요?')
- 원형 패턴은 제품의 생성, 복합, 표현 방법에 독립적인 제품을 만들고지 할 때 사용
  - 인스턴스화할 클래스를 런타임에 지정할 때(동적 로딩)
  - 제품 클래스 계통과 병렬적으로 만드는 팩토리 클래스를 피하고 싶을 때
  - 클래스의 인스턴스들이 서로 다른 상태 조합 중에 어느 하나일 때 원형 패턴을 사용
    - 미리 원형으로 초기화해 두고(파라미터를 통해 값을 다르게 주입한다는 의미인듯), 나중에 이를 복제해서 사용 

## 구조 (객체 모델링 기법에 기반을 둔 표기법을 이용하여 해당 패턴에서 쓰는 클래스들을 시각적으로 나타냄)
- ![image2](https://user-images.githubusercontent.com/7076334/131447887-fff6a4bf-a13a-4efa-baa6-9f42c795b6fb.png)

## 참여자 (주어진 패턴을 구성하고 책임을 수행하는 클래스나 객체들을 설명)
- Prototype(Graphic) : 자신을 복제하는 데 필요한 인스턴스를 정의
- ConcretePrototype(Staff, WhileNote, HalfNote) : 자신을 복제하는 연산을 구현함
- Client(GraphicTool) : 원형에 자기 자신의 복제를 요청하여 새로운 객체를 생성

## 협력 방법 (참여자들이 작업을 수행하기 위한 참여자들 간의 협력 관계를 정의)
- 사용자는 원형 클래스에 스스로 복제하도록 요청함

## 결과 ('이 패턴이 자신의 목표를 어떻게 지원할까요? 이 패턴을 이용한 결과는 무엇인고 장단점은 무엇일까요? 이 패턴을 사용하면 시스템 구조의 어떤 면을 독립적으로 다양화시킬 수 있을까요?')
- 사용자 쪽에서는 어떤 구체적인 제품이 있는지 알리지 않아도 되기 때문에 사용자 쪽에서 상대하는 클래스가 적음 (추상 팩토리, 빌더랑 비슷한 결과)

- 원형 패턴의 추가적인 특정
  - 1) 런타임에 새로운 제품을 추가하고 삭제할 수 있음
    - 사용자(Client)에게 원형으로 생성되는 인스턴스를 등록하는 것만으로도 시스템에 새로운 제품 클래스를 추가 가능 
  - 2) 값들의 다양화함으로써 새로운 객체를 명세함
    - 새로운 클래스 생성 없이 객체 합성(객체에 변수의 값을 지정 - 파라미터로 이해함)
      - 객체 변수가 인터페이스면, 여러 인스턴스를 넘겨서 새로운 행동이 정의되는 것으로 이해함
    - 원형을 복제하는 것은 클래스의 인스턴스를 만드는것과 동일 (클래스의 수를 대폭 줄여줌)
  - 3) 구조를 다양화함으로써 새로운 객체를 명세할 수 있음
    - 많은 응용프로그램은 구성요소와 부분 구성요소의 복합을 통해 객체를 구축함
    - 원형 패턴을 사용하면 부분 구성요소(세부 사항)을 원형으로 만들어서 구성 요소에 등록해서 사용
  - 4) 서브클래스의 수를 줄여줌
    - 팩토리 메서드 같은 경우 Creator와 Product이 병렬로 복합
    - 원형 패턴에서는 원형을 복제하는 것이므로 Creator 클래스에 따른 새로운 상속 계층은 필요 없음
  - 5) 동적으로 클래스에 따라 응용프로그램을 설정할 수 있음
    - 원형 관리자를 통해 동적으로 필요한 클래스의 인스턴스를 응용 프로그램에 등록할 수 있다 정도로만 이해함 

- 원형 패턴의 걸림돌
  - 원형의 서브클래스가 Clone 연산을 구현해야 함
  - 이미 클래스가 만들어져 있으면 Clone 연산을 추가하기 어려움

## 구현 ('패턴을 구현할 때 주의해야 할 함정, 힌트, 기법 등은 무엇일까요? 특정 언어에 국한된 특이 사항은 무엇일까요?')
- 정적 언어에서 매우 유용함 (컴파일 시 타입이 확정됨)

- 1) 원형 관리자를 사용함
  - 사용자(Client)는 원형 자체를 다루지 않으며, 레지스트리에서 원형을 검색하고, 레지스트리에 저장할 뿐
    - 이런 레지스트리를 원형 관리자(prototype manager) 라고 함
  - 원형 관리자는 어떤 키에 부합되는 원형을 저장하고, 찾아서 반환하며, 삭제하는 기능을 담당하는 저장소
- 2) Clone 연산을 구현
  - 원형 패턴에서 가장 어려운 부분
    - 순환 참조에 대해서 이해 못함. 순환 참조는 가급적 배제해야 되는게 아닌가?
  - 깊은 복사 vs 얕은 복사
    - java도 primitive type에 대해서는 깊은 복사가 되지만, reference 형태는 얕은 복사 처리
    - 구조가 복잡한 경우 사본과 원본이 독립적일 수 있어야 하기 때문에, 원형이 정의하는 각 요소 모두를 개별적으로 복제
      - clone 오버라이딩을 통해 결정  
    - save/load 얘기가 나와서 생각이 들었는데 직렬화/역직렬화를 통해서도 깊은 복사 가능 (하지만 성능적인 부분 고려)
 
- 3) Clone을 초기화
  - Clone 연산에 매개변수를 정의하게 되면 복제 인터페이스의 일관성이 없어짐
  - 원형 클래스의 상태를 재정의할 연산(setter)이 있다면, 사용자는 객체 복제 직후에 이들 연산을 호출할 것임
  - 아니면 Initialize 등의 이름을 가진 연상을 사용자에게 제공 (값을 파라미터로 받겠지?)


## 예제코드
```
public interface MazeFactory {
    Maze makeMaze();

    Wall makeWall();

    Room makeRoom(int n);

    Door makeDoor(Room r1, Room r2);
}


/**
 * Client
 * 생성할 객체의 원형(인터페이스)로 초기화됨
 */
public class MazePrototypeFactory implements MazeFactory {
    Maze prototypeMaze;
    Wall prototypeWall;
    Room prototypeRoom;
    Door prototypeDoor;

    public MazePrototypeFactory(Maze maze, Wall wall, Room room, Door door) {
        prototypeMaze = maze;
        prototypeWall = wall;
        prototypeRoom = room;
        prototypeDoor = door;
    }

    @Override
    public Maze makeMaze() {
        return prototypeMaze.clone();
    }

    @Override
    public Wall makeWall() {
        return prototypeWall.clone();
    }

    @Override
    public Room makeRoom(int n) {
        return prototypeRoom.clone();
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        Door door = prototypeDoor.clone();
        door.initialize(r1, r2);
        return door;
    }
}

/**
 * Prototype
 */
public class Door extends MapSite implements Cloneable {
    public Door() {

    }

    // 복사 생성자 추가
    public Door(Door other) {
        room1 = other.room1;
        room2 = other.room2;
    }

    public void initialize(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    /**
     * Clone operation
     */
    @Override
    public Door clone() {
        try {
            return (Door) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Door(this);
        }
    }
}

/**
 * Concrete Prototype
 */
public class BombedWall extends Wall {
    private boolean hasBomb;

    public BombedWall() {

    }

    /**
     * 복사 생성
     */
    public BombedWall(BombedWall wall) {
        this.hasBomb = wall.hasBomb;
    }

    @Override
    public BombedWall clone() {
        return new BombedWall(this);
    }
}

```
- MazePrototypeFactory (client)
  - 생성할 객체의 원형(인터페이스)으로 초기화 됨
    - 서브 클래스 필요 없음
  - MazeFactory 인터페이스를 확장 하여 벽, 방, 문 등을 만드는 함수 정의
    - clone을 통해 복제하고 initialize가 필요 시 초기화 
  - 미로의 형식을 바꾸려면 MazePrototypeFactory를 이용하되 다른 원형으로 초기화 하면 됨
- Door, Maze, Room, Wall(prototype)
  - 인스턴스처럼 원형으로 사용할 수 있는 객체는 반드시 clone 연산자를 제공
  - 객체 복제를 위해 복사 생성자도 있어야 됨
  - 내부 연산을 재초기화하기 위한 연산도 필요함
- BombedWall, RoomWithABomb(concrete prototype)
  - Clone을 재정의하고 복사 생성자를 구현
  - 사용자가 구체적인 서브클래스를 몰라도 원형을 복제할 수 있도록 해야 함
    - 사용자가 원하는 타입으로 다운캐스트할 필요가 업성야 함

## 잘 알려진 사용예


## 관련 패턴
- 원형 패턴과 추상 팩토리 패턴은 어떤 면에서는 경쟁적인 관계임
  - 함께 사용 가능
  - 추상 팩토리 패턴은 원형 집합을 저장하다가 필요할 때 복제하여 제품 객체를 반환하도록 사용 가능
- 복합체 패턴과 장식자 패턴을 많이 사용하는 곳에서도 원형 패턴을 사용하면 좋음

## 추상 팩토리 패턴 에서 원형 패턴
- P136


# 단일체(SINGLETON) / 객체 생성
## 의도 ('이 디자인 패턴은 무엇을 하는것일까요? 의도와 논리적인 근거가 무엇일까요? 어떤 특정한 문제나 이슈를 해결하기 위한 것일까요?' 에 대한 간결한 답을 제시)
- 오직 한 개의 클래스 인스턴스만을 갖도록 보장하고, 이에 대한 전역적인 접근점을 제공

## 동기 (시나리오)
- 하나의 인스턴스를 어떻게 만들까?
  - 전역 변수 사용
  - 더 좋은 방법은 클래스 자신이 자기의 유일한 인스턴스로 접근하는 방법을 자체적으로 관리
    - 이를 단일체 패턴

## 활용성 ('해당 패턴을 어떤 상황에 적용할 수 있을까요? 패턴이 문제로 삼는 잘못된 설계의 예는 무엇일까요? 이 상황을 어떻게 팡가할 수 있을까요?')
- 다음과 같은 상황에서 사용
  - 클래스의 인스턴스가 오직 하나여야 함을 보장하고, 잘 정의된 접근점으로 모든 사용자가 접근할 수 있도록 해야 할 때
  - 유일한 인스턴스가 서브클래싱으로 확장되어야 하며, 사용자는 코드의 수정 없이 확장된 서브클래스의 인스턴스를 사용 할 때 (확장을 얘기 하는듯)


## 구조
- ![image3](https://user-images.githubusercontent.com/7076334/131503526-04c5f4fe-c67c-43f1-8aa2-eca7407b12bb.png)

## 참여자 (주어진 패턴을 구성하고 책임을 수행하는 클래스나 객체들을 설명)
- Singleton : Instance 연산을 정의하여, 유일한 인스턴스로 접근할 수 있도록 함
  - Instance 연산은 클래스 연산 (자바 클래스 변수)

## 협력 방법 (참여자들이 작업을 수행하기 위한 참여자들 간의 협력 관계를 정의)
- 사용자는 Singleton 클래스에 정의된 Instance 연산을 통해서 유일하게 생성되는 단일체 인스턴스에 접근할 수 있음

## 결과 ('이 패턴이 자신의 목표를 어떻게 지원할까요? 이 패턴을 이용한 결과는 무엇인고 장단점은 무엇일까요? 이 패턴을 사용하면 시스템 구조의 어떤 면을 독립적으로 다양화시킬 수 있을까요?')
- 단일체 패턴의 장점
  - 1) 유일하게 존재하는 인스턴스로의 접근을 통제
    - Singleton 클래스 자체가 인스턴스를 캡슐화하기 때문에, 이 클래스에서 사용자가 언제, 어떻게 이 인스턴스에 접근할 수 있는지 제어할 수 있음 
  - 2) 이름 공간(name space)를 좁힘
    - 전역 변수보다 더 좋음
    - 전역 변수를 정의하여 발생하는 디버깅의 어려움 등 문제를 없앰
      - 결국 싱글톤도 전역 변수로 저장되는거 아닌가? (클래스 변수)
      - 개인적으로 Lazy Loding 등 제어 이점이 더 크다고 생각됨
  - 3) 연산 및 표현의 정제를 허용함
    - Singleton 클래스는 상속될 수 있기 때문에, 서브클래스를 통해서 새로운 인스턴스를 만들 수 있음
    - 이 패턴(서브 클래스를 사용하는?)을 사용하면 런타임에 필요한 클래스의 인스턴스를 써서 응용프로그램 구성 가능
  - 4) 인스턴스의 개수를 변경하기가 자유로움
    - Singleton 클래스의 인스턴스에 접근할 수 있는 허용 범위를 결정하는 연산만 변경하면 됨
    - 스프링의 빈 Scope(prototype, request, session 등) 조절 하는걸로 생각했음
      - 싱글턴이 한 개의 클래스 인스턴스만 갖는건데 왜 개수를 여러개로 바꿀까?
  - 5) 클래스 연산을 사용하는 것보다 훨씬 유연한 방법 
    - 자바 클래스 변수로 초기화해서 사용하는거
      - 이 방법은 하나 이상 존재할 수 있도록 설계하는게 어렵다 

## 구현 ('패턴을 구현할 때 주의해야 할 함정, 힌트, 기법 등은 무엇일까요? 특정 언어에 국한된 특이 사항은 무엇일까요?')
- 단일체 패턴을 사용할 때 고려 사항
  - 1) 인스턴스가 유일해야 함을 보장해야함
    - 단일체 패턴은 클래스의 인스턴스가 오로지 하나임을 만족해야 함
    - 일반적인 방법은 인스턴스를 생성하는 연산을 클래스 연산(static) 으로 만드는 것
    ```
    public class Singleton {
      private static Singleton instance;

      protected Singleton() {
      }

      public static Singleton getInstance() {
          if (instance == null) {
              instance = new Singleton();
          }
          return instance;
      }
    }
    ```
      - 사용자는 반드시 getInstance() 를 통해서만 인스턴스 접근 해야됨
    - 정적 멤버함수 기법을 사용하지 않고 단일체를 전역 변수나 정적 객체로 정의하고 이를 자동 초기화하는 것만으로는 충분하지 않음
      - a) 전역 객체의 유일한 인스턴스만 선언되리라는 보장을 할 수 없음
      - b) 정적 초기화 시점에 모든 단일체를 인스턴스화하기 위해 필요한 모든 정보가 없을 수도 있음
      - c) c++에서는 전역 객체에 대한 생성자를 언제 호출하는지에 대한 명확한 순서를 정의하지 않음 (순서 잘못되면 오류 발생할 수도 있음)
        - 정적 멤버 함수 기법을 이용하면 앞의 예에서처럼 실제 호출이 일어나는 시점에 객체를 생성할 수 있음
  - 2) Singleton 클래스를 서브클래싱해야 함
    - 서브클래스를 만드는 것이 중요한 게 아니라, 이 새로운 서브클래스의 유일한 인스턴스를 만들어 사용자가 이를 사용할 수 있도록 하는것이 중요
    - 가장 쉬운 방법은 getInstance 연산을 사용 시, 슈퍼클래스의 단일체를 쓸지, 서브 클래스의 단일체를 쓸지 결정함 (팩토리 메서드 처럼?)
    - 다른 방법으로는 getInstance 연산의 구현을 슈퍼클래스가 아닌 서브클래스에서 하는 것
      - 단일체를 고정하기 때문에 런타임에 단일체 클래스를 선택하기 힘들어짐
      - 좀 더 유연한 방법은 단일체에 대한 레지스트리를 사용 (prototype manager랑 비슷한듯) 
      ```
      /**
       * 단일체에 대한 레지스트리 사용
       */
      public class Singleton {
          private static final Map<String, Singleton> REGISTRY = new HashMap<>();

          private static Singleton instance;

          protected Singleton() {
              register("SINGLETON", this);
          }

          protected static Singleton lookUp(String key) {
              return REGISTRY.get(key);
          }

          public static Singleton getInstance() {
              if (instance == null) {
                  final String singletonName = "SINGLETON";

                  instance = lookUp(singletonName);
              }
              return instance;
          }

          public static void register(String key, Singleton instance) {
              REGISTRY.put(key, instance);
          }
      }
      
      public class SubSingleton extends Singleton {
          private static SubSingleton instance;

          protected SubSingleton() {
              register("SUBSINGLETON", this);
          }

          public static Singleton GetInstance() {
              return lookUp("SUBSINGLETON");
          }
      }
      ```
      - Singleton 클래스는 더는 단일체 인스턴스를 생성하는 책임은 없고, 시스템 전반에서 단일체 인스턴스로의 유일한 접근 창구 역할만 함
        - 이 방법에도 문제가 있는데 서브클래스의 인스턴스가 미리 생성되어 있어야 레지스트리 등록 가능

## 예제 코드
- MazeFactory의 인스턴스가 하나만 있어도 되면 단일체 패턴 사용

- 단순한 (서브클래스 필요 없는) 단일체 패턴 사용
```
public class MazeFactory {

    private static MazeFactory instance;

    protected MazeFactory() {

    }

    public static MazeFactory getInstance() {
        if (instance == null) {
            instance = new MazeFactory();
        }
        return instance;
    }
}
```
  - instance 정적 멤버 정의하고, getInstance 정적 연산 추가
  - 사용자에 의한 객체 생성을 막기 위해, 생성자는 반드시 protected 로 선언 (서브 클래스 확장도 고려한듯)

- MazeFactory에 서브클래스가 있다면?
```
public class MazeFactory {
    private static MazeFactory instance;

    protected MazeFactory() {

    }

    public static MazeFactory getInstance() {
        if (instance == null) {
            String mazeStyle = System.getenv("MAZESTYLE");
            if (mazeStyle.equals("bombed")) {
                instance = new BombedMazeFactory();
            } else if (mazeStyle.equals("enchanted")) {
                instance = new EnchantedMazeFactory();
            } else {
                instance = new ComplexMazeFactory();
            }
        }
        return instance;
    }
}
```
  - MazeFactory의 새로운 서브클래스를 만들 때 마다 instance가 달라짐
  - 프레임워크에 정의된 추상 팩토리라면 프레임워크를 수정해야 하므로 문제가 됨
    - 위에서 사용한 단일체 레지스트리 사용하면 새로 안만들고 메모리에서 꺼내서 사

## 잘 알려진 사용예
- 스프링 빈 스코프

## 관련 패턴
- 추상 팩토리, 빌더, 원형 패턴 등을 참고

# 생성 패턴에 대한 논의
- 

## 궁금한 부분
- 클래스와 객체
  - 클래스 주로 상속 (정적)
  - 객체 (동적)



# 기타
- Prototype Manager : https://lee1535.tistory.com/76
- Singleton (effective java3) : https://lelecoder.com/7
- Singleton subclass : https://community.wvu.edu/~hhammar/rts/adv%20rts/design%20patterns%20tutorials/course%20slides%20on%20patterns%20and%20java/Singleton-2pp.pdf

