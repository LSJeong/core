package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //@Bean이라고 적힌 메서드를 모두 호출해서 반한된 객체를 스프링 컨테이너에 등록
public class AppConfing {
    //AppConfing는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
    //생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입해준다.

    //여러번 호출되니 싱글톤 깨지는것이 아닐까? -> ConfigurationSingletonTest에서 같은 인스턴스 확인
    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //스프링 컨테이너가 Bean 등록할때 호출하니까
    //이렇게 되어야? 아님
    //call AppConfing.memberService
    //call AppConfing.memberRepository  1번
    //call AppConfing.memberRepository  2번
    //call AppConfing.orderService
    //call AppConfing.memberRepository  3번

    //실제 이렇게만 호출  -> 왜?? @Configuration의 바이트코드 조작때문 (configurationDeep() 에서 설명)
    //@Configuration 없이 @Bean만 쓴다면?
      // -> @Configuration의 CGLIB 기술을 사용X : 순수한 AppConfig 클래스로 빈에 등록되고 위의 call처럼 memberRepository가 3번 호출된다.(각 다른 인스턴스)
      // -> 결론: @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지않는다.
    //call AppConfing.memberService
    //call AppConfing.memberRepository  1번
    //call AppConfing.orderService

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfing.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfing.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfing.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
