package SpringTemplate.demo.order;

import SpringTemplate.demo.AppConfig;
import SpringTemplate.demo.member.Grade;
import SpringTemplate.demo.member.Member;
import SpringTemplate.demo.member.MemberService;
import SpringTemplate.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach //각 테스트마다 테스트 실행 전에 BeforeEach 먼저 실행
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
        orderService = appconfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L; //long이 아닌 Long으로 쓰는 이유는 null값이 들어갈 수 있게 설정하기 위함
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
