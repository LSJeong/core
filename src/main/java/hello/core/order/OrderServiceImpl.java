package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor  //final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
public class OrderServiceImpl implements OrderService{

   // private final MemberRepository memberRepository= new MemoryMemberRepository();
    //OCP, DIP 위반
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //@Autowired 필드 주입
    //private MemberRepository memberRepository;
    //@Autowired 필드 주입
    //private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 수정자 주입
    @Autowired   //주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
     */

    /*  @RequiredArgsConstructor 에 의해서 자동 생성되므로 주석처
    @Autowired
    //생성자가 하나면 @Autowired 생략해도 자동 주입
    //DIP지킴(인터페이스만 의존), 생성자 주입(AppConfing에서 주입 관리)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
