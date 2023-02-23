package SpringTemplate.demo.autowired;

import SpringTemplate.demo.AutoAppConfig;
import SpringTemplate.demo.discount.DiscountPolicy;
import SpringTemplate.demo.member.Grade;
import SpringTemplate.demo.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }

    static class DiscountService {
        //Map의 키에 스프링 빈의 이름, 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
        private final Map<String, DiscountPolicy> policyMap;
        //DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            //Map의 키값을 통해서 등록된 스프링 빈을 가져오고 discountPolicy에 그 스프링 빈을 저장.
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price );
        }
    }
}
