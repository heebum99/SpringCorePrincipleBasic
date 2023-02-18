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

public class AppConfig { //객체 생성과 연결을 담당

    //생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결)한다.
    //구현체가 바뀌더라도 실 사용 코드에서는 변경이 없고 Config 파일에서만 구현체를 변경해주면된다.
    //DIP와 OCP모두 만족
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
