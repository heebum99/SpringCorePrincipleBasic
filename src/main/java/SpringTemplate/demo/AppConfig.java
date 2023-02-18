package SpringTemplate.demo;

import SpringTemplate.demo.discount.DiscountPolicy;
import SpringTemplate.demo.discount.FixDiscountPolicy;
import SpringTemplate.demo.discount.RateDiscountPolicy;
import SpringTemplate.demo.member.MemberRepository;
import SpringTemplate.demo.member.MemberService;
import SpringTemplate.demo.member.MemberServiceImpl;
import SpringTemplate.demo.member.MemoryMemberRepository;
import SpringTemplate.demo.order.OrderService;
import SpringTemplate.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //DI 컨테이너를 위한 애노테이션
public class AppConfig { //객체 생성과 연결을 담당

    //생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결)한다.
    //구현체가 바뀌더라도 실 사용 코드에서는 변경이 없고 Config 파일에서만 구현체를 변경해주면된다.
    //DIP와 OCP모두 만족
    @Bean //스프링 빈으로 스프링 컨테이너에 등록하는 애노테이션
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
