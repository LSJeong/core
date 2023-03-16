package hello.core.autowiredTest;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)  //의존 관계가 없으면 메서드 자체가 호출이 안됨, 기본설정은 true로 되어있음
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){   //호출은 되지만, Null로 들어감
            System.out.println("noBean2 = " + noBean2);  //noBean2 = null
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){  //Optional.empty
            System.out.println("noBean3 = " + noBean3);  //noBean3 = Optional.empty
        }
    }

}
