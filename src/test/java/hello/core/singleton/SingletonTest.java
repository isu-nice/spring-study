package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 3. 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 4. memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // 1. 객체 여러번 생성
        SingletonService service1 = SingletonService.getInstance();
        SingletonService service2 = SingletonService.getInstance();

        // 2. 참조값이 같은 것을 확인
        System.out.println("service1 = " + service1);
        System.out.println("service2 = " + service2);

        // 3. service1 == service2
        assertThat(service1).isSameAs(service2);
        // isSameAs -> 객체 인스턴스를 비교
        // isEqualTo -> equals 메서드를 의미
    }

    @Test
    @DisplayName("스프링 컨테이너 싱글톤")
    void springContainer() {
        // 1. 스프링 컨테이너로
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. 객체 여러개 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 3. 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 4. memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
