package SpringTemplate.demo.discount;

import SpringTemplate.demo.member.Grade;
import SpringTemplate.demo.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { //Enum타입은 ==으로 비교
            return discountFixAmount;
        } else {
            return 0;
        }
    }

}
