package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)  //HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸됨
//ScopedProxyMode : 클래스면 TARGET_CLASS, 인터페이스면 INTERFACES
//CGLIB이라는 바이트코드를 조작하는 라이브러리를 사용해서 가짜 프록시 객체를 생성한다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();  //빈이 생성되는 시점에 uuid를 생성해 저장
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
