package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // 생성자 만들어주는 어노테이션
public class OrderServiceImpl implements OrderService {

    // 인터페이스에만 의존하게 된다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* @RequiredArgsConstructor 사용 -> 생성자 자동으로 만들어주기 때문에 아래 코드 삭제 가능

    // setter 주입하면 생략가능 -> 그렇지만 DI 방식이 낫다!
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
     */

    /* DI 방식 사용 (final 붙여줌) -> setter 사용하지 않을 것이기 때문에 삭제해줌
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
