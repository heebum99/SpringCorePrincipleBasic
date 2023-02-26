package SpringTemplate.demo.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request") //HTTP 요청 하나당 하나씩 생성되고 요청이 끝나는 시점에 소멸
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //이 클래스에 대해서 프록시(가짜)를 생성
//이렇게하면 Provider대신 가짜 프록시 클래스를 만들어서 request와 관계없이 가짜 프록시 클래스를 다른 빈에 미리 주입해둘 수 있다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); //절대 겹치지 않는 id가 생성됨.
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
