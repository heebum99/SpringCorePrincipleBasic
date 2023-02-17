package SpringTemplate.demo.order;

import SpringTemplate.demo.discount.DiscountPolicy;
import SpringTemplate.demo.discount.FixDiscountPolicy;
import SpringTemplate.demo.member.Member;
import SpringTemplate.demo.member.MemberRepository;
import SpringTemplate.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
