package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//빈 이름 기본: MemberServiceImpl 클래스 -> memberServiceImpl 맨 앞글자 소문자가 됨
//직접 지정하고 싶으면 @Component("memberService2") 지정하면 됨
@Component
public class MemberServiceImpl implements MemberService{
    //DIP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    @Autowired  //자동 의존관계 주입, 이전 AppConfig에서 직접 Bean등록 및 의존관계 명시(ac.getBean(MemberRepository.class)) 해준 것과 같다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
