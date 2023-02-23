package SpringTemplate.demo.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    //스프링빈의 라이프 사이클: 객체 생성 -> 의존관계 주입

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
        //ApplicationContext에서는 close()메서드를 제공하지 않음. => Annotation ~로 바꾸거나 ConfigurableApplication ~ 으로 바꿔야함.
        //ApplicationContext -> ConfigurableApplication -> AnnotationConfigApplicationContext
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "close") //@Bean 애노테이션을 통한 초기화/소멸 메서드 지정
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
