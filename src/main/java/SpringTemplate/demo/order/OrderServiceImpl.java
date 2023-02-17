package SpringTemplate.demo.order;

import SpringTemplate.demo.discount.DiscountPolicy;
import SpringTemplate.demo.discount.FixDiscountPolicy;
import SpringTemplate.demo.discount.RateDiscountPolicy;
import SpringTemplate.demo.member.Member;
import SpringTemplate.demo.member.MemberRepository;
import SpringTemplate.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //할인 정책을 바꿈으로 코드를 변경하는것 => OCP위반.
    /**
     * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * => private final DiscountPolicy로 바뀐다는 것은
     * OrderServiceImpl는 인터페이스 DiscountPolicy 뿐만아니고 구현체 Fix ~ , Rate ~ 에도 영향을 받고 있으므로 DIP 위반.
     **/
    private DiscountPolicy discountPolicy; //인터페이스에만 의존 => DIP 적합, 현재는 NullPointException 발생

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
