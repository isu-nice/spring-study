package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 앱 실제 동작에 필요한 구현 객체를 생성해주는 클래스
 * 설정 정보에 대한 내용
 */
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 싱글톤이 깨진다?? -> 아님(테스트 해 본 결과)
    // 진짜 실행되는 건 맞는지 로그 남겨서 확인해보자.

    @Bean
    public MemberService memberService() {
        // 로그 남김 -> 한 번
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    } // 생성한 객체 인스턴스의 레퍼런스를 '생성자를 통해서 주입'해줌

    @Bean
    public MemberRepository memberRepository() {
        // 로그 남김 -> 두 번?? 세 번?? -> 결론: 한 번
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        // 로그 남김 -> 한 번
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 할인 정책 변경이 쉬워짐
    }
}
