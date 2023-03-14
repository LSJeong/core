package hello.core.singleton;

import hello.core.AppConfing;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfing.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);;
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);;
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfing.class);

        //AppConfig도 스프링 빈으로 등록된다.
        AppConfing bean = ac.getBean(AppConfing.class);

        //순수한 클래스라면 출력이 class hello.core.AppConfig 이렇게 되어야한다.
        //하지만 실제 출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$~~~
        //스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한다.
        //그 임의의 클래스가 싱글톤을 보장해주는것!
        System.out.println("bean = " + bean.getClass());
    }
}
