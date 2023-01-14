package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 딱 1개의 객체를 생성한다.
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 만들어서 객체 인스턴스가 필요하면 이 메서드를 이용하여 조회하도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private 로 선언해서 외부에서 new 하지 못하도록 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
