# 생성 패턴
- 생성 패턴
  - 인스턴스를 만드는 절차를 추상화하는 패턴
  - 해당하는 패턴은 객체를 생성,합성하는 방법이나 객체의 표현 방법과 시스템을 분리해 준다.

- 생성 패턴의 기능
  - 1) 생성 패턴은 시스템이 어떤 구체 클래스를 사용하는지에 대한 정보를 캡슐화
  - 2) 생성 패턴은 이들 클래스의 인스턴스들이 어떻게 만들고 어떻게 서로 맞붙는지에 대한 부분을 완전히 가려줌
    - 생성 패턴을 이용하면 무엇이 생성되고,누가 이것을 생성하며, 이것이 어떻게 생성되는지, 언제 생성할 것인지 결정하는 데 유연성을 확보할 수 있음

- 생성 패턴들의 비교
  - 생성 패턴들은 서로 보완적일 수도 있고 선택되기 위해 서로 경쟁적일 수도 있음 (선택의 결정을 내리기 어려움)
  - 미로와 게임을 예시로 각 생성 패턴들의 차이를 확인

  - 미로 클래스 다이어그램
    - ![image1](https://user-images.githubusercontent.com/7076334/129391632-b2dec1fd-bde8-439b-92e0-9937b6aeb578.png)
      - MapSite 클래스는 미로의 구성요소들에 필요한 모든 연산을 정의한 공통 추상 클래스
      - Room 클래스는 미로에 있는 다른 요소와(벽,문 등) 관련성을 갖도록 정의
      - Wall과 Door는 방의 각 측면에 있을 수 있는 문과 벽을 나타냄
      - Maze는 방들의 집합을 표현 (RoomNo() 연산을 통해 특정 방을 찾을 수 있음)
      - MazeGame는 실제로 미로를 생성하는 클래스 (다이어그램에는 없음)

- 간단한 미로 생성 (겨우 방 두개를 만들었을 뿐인데 약간 복잡함)
    ```
    public Maze createMaze() {
        Maze aMaze = new Maze();
        Room r1 = new Room(1);
        Room r2 = new Room(2);
        Door theDoor = new Door(r1, r2);

        aMaze.addRoom(r1);
        aMaze.addRoom(r2);

        r1.setSide(NORTH, new Wall());
        r1.setSide(EAST, theDoor);
        r1.setSide(SOUTH, new Wall());
        r1.setSide(WEST, new Wall());

        r2.setSide(NORTH, new Wall());
        r2.setSide(EAST, new Wall());
        r2.setSide(SOUTH, new Wall());
        r2.setSide(WEST, theDoor);

        return aMaze;
    }
    ```

  - createMaze 단점은 유연성이 떨어짐
    - 방의 레이아웃이 하드코딩 되어 있음 (가장 큰 장애요인)
    - 레이아웃을 변경하려면 멤버 함수를 바꾸는 수 밖에 없음
      - 생성 패턴은 이런 상호아에서 어떻게 유연한 설계를 할 수 있는지에 대한 해법을 제공 

- 생성 패턴을 이용하면?
  - 팩토리 메서드 패턴
    - 방, 벽, 문을 생성하기 위해 생성자를 이용하지 않고 가상 함수(추상 메서드를 말하는듯)를 호출
  - 추상 팩토리 패턴
    - 방, 벽, 문을 생성하기 위해 생성 방법을 알고 있는 객체를 매개변수로 넘겨 받을 수 있는 경우
  - 빌더 패턴
    - 방, 벽, 문을 추가하는 연산을 사용해서 새로운 미로를 만들 수 있는 객체를 넘겨받는다면 미로를 만드는 방법이나 변경을 이 객체의 상속을 통해서 해결
  - 원형 패턴
    - 이미 만든 객체를 복사해서 미로에 추가하면, 이들 인스턴스를 교체 하여 미로의 복합 방법으로 변경 가능
  - 단일체 패턴(싱글턴)
    - 한 게임에 오직 하나의 미로 객체만 존재할 수 있고 그 게임에서 돌아가는 모든 게임 객체들이 이 미로에 접근 가능하도록 보장


# 추상 팩토리(Abstract Factory) / 객체 생성
## 의도 ('이 디자인 패턴은 무엇을 하는것일까요? 의도와 논리적인 근거가 무엇일까요? 어떤 특정한 문제나 이슈를 해결하기 위한 것일까요?' 에 대한 간결한 답을 제시)
- 상세화된 서브클래스를 정의하지 않고도 서로 관련성이 있거나 독립적인 여러 객체의 군을 생성하기 위한 인터페이스 제공

## 다른 이름
- 키트(Kit)

## 동기 (시나리오)
- 사례
  - 룩앤필 : 소프트웨어 디자인에서 룩 앤필은 그래픽 사용자 인터페이스의 관점에서 쓰이며 색, 모양, 레이아웃, 글꼴 뿐 아니라 단추, 상자, 메뉴와 같은 동적의 요소의 동작을 수반하는 디자인의 측면을 이루고 있음
  - 개발한 응용프로그램이 서로 다른 룩앤필 표준에 상관없이 이식성을 가지려면, 응용프로그램이 각 사용자 인터페이스 툴킷에서 제공하는 위젯을 직접 사용하지 못하도록 해야 함

- ![abstract_factory1](https://user-images.githubusercontent.com/7076334/129565276-f1dbf82e-82ae-44b0-b30b-948ae1e5d78a.png)
  - WidgetFactory 인터페이스는 각 추상화된 위젯 클래스 (Product) 의 인스턴스를 생성하여 반환하는 연산을 정의
  - 응용프로그램 (Client)는 필요한 사용자 인터페이스 요소를 WidgetFactory에 생성해 줄 것을 요청하여, 필요한 요소의 인스턴스를 얻어옴
  - 실제적으로 구현 종속적인 인스턴스를 생성하기 위해서는 팩토리와 구분하여 각각의 위젯별(Product)로 추상화된 클래스를 정의해야 하고, 이를 상속하는 구체적인 서브클래스를 정의하여 룩앤필 표준에 대한 구현을 제공
    - 사용자는 WidgetFactory를 상속받은 어떤 구체적 서브클래스가 이들 연산을 구현하여 결과를 반환하는지 알 수 없고, 알 필요도 없음
    - 사용자는 룩앤필과 분리될 수 있음 (위젯을 직접 사용하지 못함)

## 활용성 ('해당 패턴을 어떤 상황에 적용할 수 있을까요? 패턴이 문제로 삼는 잘못된 설계의 예는 무엇일까요? 이 상황을 어떻게 팡가할 수 있을까요?')
- 추상 팩토리 사용 예
  - 객체가 생성되거나 구성, 표현되는 방식과 무관하게 시스템을 독립적으로 만들고자 할 때
  - 여러 제품군 중 하나를 선택해서 시스템을 설정해야 하고 한번 구성한 제품을 다른 것으로 대체할 수 있을 때
  - 관련된 제품 객체들이 함께 사용되도록 설계되었고, 이 부분에 대한 제약이 외부에도 지켜지도록 하고 싶을 때 ex) 위젯 팩토리
  - 제품에 대한 클래스 라이브러리를 제공하고, 그들의 구현이 아닌 인터페이스를 노출시키고 싶을 때

## 구조 (객체 모델링 기법에 기반을 둔 표기법을 이용하여 해당 패턴에서 쓰는 클래스들을 시각적으로 나타냄)
- ![abstract_factory2](https://user-images.githubusercontent.com/7076334/129567548-a6e1debb-2e79-4d3d-b8f1-417c738dd5ec.png)
  - 실제 Maze 예제에서는 AbstractProduct 을 구체 클래스로 나타낸것 같음 ex) Door, Maze, Room, Wall

## 참여자 (주어진 패턴을 구성하고 책임을 수행하는 클래스나 객체들을 설명)
- AbstractFactory(WidgetFactory) : 개념적 제품에 대한 객체를 생성하는 ㅇ녀산으로 인터페이스를 정의
- ConcreteFactory(MotifWidgetFactory, PMWidgetFactory) : 구체적인 제품에 대한 객체를 생성하는 연산을 구현
- AbstractProduct(Window, ScrollBar) : 개념적 제품 객체에 대한 인터페이스를 정의
- ConcreteProduct(MotifWindow, MotifScrollBar) : 구체적으로 팩토리가 생성할 객체를 정의하고, AbstractProduct가 정의하는 인터페이스를 구현
- Client : AbstractFactory와 AbstractProduct 클래스에 선언된 인터페이스를 사용

## 협력 방법 (참여자들이 작업을 수행하기 위한 참여자들 간의 협력 관계를 정의)
- ConcreteFactory
  - 일반적으로 ConcreteFactory 클래스의 인스턴스 한 개가 런타임에 만들어지고 이 구체 팩토리는 어떤 특정 구현을 갖는 제품 객체를 생성함
  - 서로 다른 제품 객체를 생성하려면 사용자는 서로 다른 구체 팩토리를 사용해야 함
- AbstractFactory는 필요한 제품 객체를 생성하는 책임을 ConcreteFactory 서브 클래스에 위임함
  - Client 입장에서는 ConcreteFactory을 알 필요 없음

## 결과 ('이 패턴이 자신의 목표를 어떻게 지원할까요? 이 패턴을 이용한 결과는 무엇인고 장단점은 무엇일까요? 이 패턴을 사용하면 시스템 구조의 어떤 면을 독립적으로 다양화시킬 수 있을까요?')
- 추상 팩토리 패턴의 이익과 부담
  - 1) 구체적인 클래스 분리
    - 팩토리는 제품 객체를 생성하는 과정과 책임을 캡슐화한 것이기 때문에, 구체적인 구현 클래스가 사용자에게서 분리
    - 일반 프로그램은 추상 인터페이스를 통해서만 인스턴스를 조작
  - 2) 제품군을 쉽게 대체할 수 있도록 함
    - 응용프로그램이 사용할 구체 팩토리를 변경하기 쉬움 (선언되는 부분만 변경하면 될듯)
  - 3) 제품 사이의 일관성을 증진시킴
    - 하나의 군 안에 속한 제품 객체들이 함께 동작하도록 설계되어 있을 때, 응용프로그램은 한 번에 오직 한 군에서 만든 객체를 사용하도록 함으로써 프로그램의 일관성을 갖도록 해야 함 (모티프를 쓸것인가? PM을 쓸 것인가?)
      - 추상 팩토리를 쓰면 쉽게 보장 가능 
  - 4) 새로운 종류의 제품을 제공하기 어려움 
    - 새로운 종류의 제품이 등장하면 팩토리의 구현을 변경해야 됨
      - 추상 팩토리와 모든 서브클래스의 변경을 가져옴 (Window, ScroolBar가 아닌 Button이 추가되야한다면?)  

## 구현 ('패턴을 구현할 때 주의해야 할 함정, 힌트, 기법 등은 무엇일까요? 특정 언어에 국한된 특이 사항은 무엇일까요?')
- 추상 팩토리 패턴 구현 기법
  - 1) 팩토리를 단일체(singleton)로 정의
    - 응용프로그램은 한 제품군에 대해서 하나의 ConcreteFactory 인스턴스만 있으면 됨
  - 2) 제품을 생성함
    - AbstractFactory 방식은 제품이군이 약간 다르다면 각 제품을 위한 새로운 구체 팩토리 서브클래스가 필요 (공통된 기능 중복 발생)
    - 많은 제품군이 가능하다면 구체 팩토리는 원형 패턴(Prototype)을 이용해서 구현 가능
    - 원형 기반의 접근법은 새로운 제품군별로 새로운 구체 팩토리를 생성할 필요를 없애줌
    - 스몰토크 예제는 이해 못함. 그냥 프로토 타입용 구체 팩토리 하나 만들어 두고, 원형 패턴을(복사) 이용해서 인스턴스 생성 하는 방식으로 이해함 (뒤에 프로토 타입 예제 봐야 할듯)
  - 3) 확장 가능한 팩토리들을 정의함
    - AbstractFactory에는 생성할 각 제품의 종류 별로 서로 다른 연산(CreateProductA(), createProductB())을 정의함
    - 새로운 종류의 제품이 추가되면 AbstractFactory에도 추가되야함 (유연하지 못함)
      - 유연하게 하려면 생성할 객체를 매개변수로 만들어 연산에 넘기는 형태 (원형 패턴과 이어지는듯)

```
public class Client
{
	public static void main( String[] args )
	{
		MazeGame mazeGame = new MazeGame();

		/*
		 * Using prototype factory with default prototype
		 */
		MazeFactory factory = new MazePrototypeFactory( new Maze(), new Wall(), new Room(), new Door() );
		Maze maze = mazeGame.createMaze( factory );
		maze.enterTheRoom( 1 );

		/*
		 * Using prototype factory with bombed wall
		 */
		factory = new MazePrototypeFactory( new Maze(), new BombedWall(), new Room(), new Door() );
		maze = mazeGame.createMaze( factory );
		maze.enterTheRoom( 1 );
	}
}
```


## 예제코드
```
/**
 * AbstractFactory
 */
public interface MazeFactory {
    Maze makeMaze();

    Wall makeWall();

    Room makeRoom(int n);

    Door makeDoor(Room r1, Room r2);
}

/**
 * ConcreteFactory
 */
public class EnchantedMazeFactory implements MazeFactory {
    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Wall makeWall() {
        return new Wall();
    }

    @Override
    public Room makeRoom(int n) {
        return new EnchantedRoom(n, castSpell());
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new DoorNeedingSpell(r1, r2);
    }

    protected Spell castSpell() {
        return new Spell();
    }
}

/**
 * ConcreteFactory
 */
public class BombedMazeFactory implements MazeFactory {
    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Wall makeWall() {
        return new BombedWall();
    }

    @Override
    public Room makeRoom(int n) {
        return new RoomWithABomb(n);
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}


/**
 * Client
 */
 
public Maze createMaze(MazeFactory factory) {
        Maze maze = factory.makeMaze();
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        Door door = factory.makeDoor(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(EAST, door);

        r2.setSide(WEST, door);

        return maze;
}
 
public class MazeGame {
    public static void main(String[] args) {
        MazeGame game = new MazeGame();
        MazeFactory factory1 = new BombedMazeFactory(); // 폭탄이 설치되어 있는 미로
        Maze maze1 = game.createMaze(factory1);
        maze1.getRoomList();

        MazeFactory factory2 = new EnchantedMazeFactory(); // 마법에 걸린 미로
        Maze maze2 = game.createMaze(factory2);
        maze2.getRoomList();
    }
}
```
- MazeFactory 클래스는 미로의 구성요소들을 생성 (방, 벽, 문)
  - P.141 AbstractFactory를 꼭 추상 클래스로 하지 않아도 되나? (유연하게 생각)
- 맨 처음 예제의 MazeGame의 createMaze는 하드코딩되어 여러 미로를 만들어 내기 힘듬. 그래서 MazeFactory 를 매개변수로 받로록 하여 처리
  - BombedMazeFactory를 넘기면 폭탄이 들어 있는 미로 생성, EnchantedMazeFactory를 넘기면 마법이 걸린 미로 생성
- EnchantedMazeFactory(마법의 걸린 미로를 만든 팩토리) 는 Room, Door (Product)에 대해 다른 서브클래스 인스턴스를 반환하게 함
  - DoorNeedingSpell (단어를 맞추면 문이 열림)
  - EnchantedRoom (마법 키나 단어 등 특별한 항목을 포함) 
- BombedMazeFactory (폭탄이 설치되어 있는 미로를 만드는 팩토리)
  - RoomWithABomb (폭탄이 장착된 방)
  - BombedWall (폭탄이 터진 후 손상된 벽)


## 잘 알려진 사용예
- 책에 나와 있는 사용예가 와닿지 않아서 java collection 참고
![abstract_factory_example](https://user-images.githubusercontent.com/7076334/129589517-6cc31868-c0ad-48f2-b9a5-a6c531b28712.png)


## 관련 패턴
- AbstractFactory 클래스는 팩토리 메서드 패턴을 이용해서 구현되는데, 원형 패턴을 이용할 때도 있음
- 구체 팩토리는 단일체 패턴을 이용해 구현하는 경우가 많음

# 기타
- 모티프) https://ko.wikipedia.org/wiki/%EB%AA%A8%ED%8B%B0%ED%94%84_(%EC%9C%84%EC%A0%AF_%ED%88%B4%ED%82%B7)
- https://johngrib.github.io/wiki/abstract-factory-pattern/#fn:holub0
