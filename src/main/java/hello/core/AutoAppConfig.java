package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(   //@Component 가 붙은 클래스를 스캔해서 자동으로 빈 등록
        /* 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치,
        //그래서 설정정보를 프로젝트 최상단, 시작루트에 두는것이 좋다

        //탐색할 패키지의 시작 위치를 지정, 이 패키지를 포함해서 하위 패키지를 탐색
        basePackages = "hello.core",
        //지정한 클래스의 패키지를 탐색 시작 위치로 지정한다
        basePackageClasses = AutoAppConfig.class,

         */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //@Configuration 붙은 것은 스캔대상에서 제외하도록함(이전 실습의 AppConfig, TestConfig 등록하지않기위해)
        //@Configuration도 스캔의 대상이 되는 이유? -> 소스코드 열어보면 @Component 애노테이션 붙어있음
)
public class AutoAppConfig {
    /* MemoryMemberRepository 에서 @Component로 자동등록되므로 충돌에러남, 주석처리
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
     */
}
