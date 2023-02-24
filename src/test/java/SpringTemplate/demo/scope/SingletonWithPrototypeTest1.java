package SpringTemplate.demo.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() { //싱글톤 빈 안에서 프로토타입 빈을 주입받아 사용하는 경우
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
        //이 경우 2번의 prototypeBean을 호출했지만 이미 prototypeBean은 ClientBean의 생성 시점에 주입이 된 상태이므로
        //prototypeBean을 사용할때마다가 아닌 주입 시점에만 새로 생성이 된다.
        //몇번을 호출을 해도 새로운 prototypeBean이 아닌 같은 prototypeBean이 호출된다.
    }

    @Scope("singleton")
//    @RequiredArgsConstructor => final 필드를 포함한 생성자 자동 생성
    static class ClientBean {
        private final PrototypeBean prototypeBean; //ClientBean 생성 시점에 주입.

        /*  @Autowired
            ApplicationContext ac;

            public int logic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
        이런 식으로 프로토타입 빈을 사용할때마다 생성하게 만들수는 있지만 좋은 코드는 아님.
        */
        @Autowired //생략가능
        public ClientBean(PrototypeBean prototypeBean) { //의존관계 주입, 이때 프로토타입 빈을 생성해서 줌.
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
