package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disConnect(){
        System.out.println("close: " + url);
    }

    /*  implements InitializingBean, DisposableBean  : 초기화, 소멸할때 호출  => 현재는 거의 쓰이지 않는 방법

    @Override
    public void afterPropertiesSet() throws Exception {
        //afterPropertiesSet() : 의존관계 주입이 끝나면 호출해주겠다는 뜻
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disConnect();
    }

     */

    @PostConstruct  //생성이 된 이후에
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy  //소멸되기 전
    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }
}
