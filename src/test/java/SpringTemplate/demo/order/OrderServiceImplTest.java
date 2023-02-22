package SpringTemplate.demo.order;

import SpringTemplate.demo.discount.FixDiscountPolicy;
import SpringTemplate.demo.member.Grade;
import SpringTemplate.demo.member.Member;
import SpringTemplate.demo.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    //수정자(setter)로 OrderServiceImpl의 의존관계 주입했을 경우 테스트 케이스

//    @Test
//    void createOrder() {
//        //NullPointerException 오류 발생 => setter로 설정을 안해줬기에
//
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L,"itemA",10000);
//
//    }

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}