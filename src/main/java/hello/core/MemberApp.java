package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl();

//        AppConfing appConfing = new AppConfing();
//        MemberService memberService = appConfing.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfing.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//이릅,타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
