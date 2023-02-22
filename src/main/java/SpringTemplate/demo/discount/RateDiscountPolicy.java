package SpringTemplate.demo.discount;

import SpringTemplate.demo.annotation.MainDiscountPolicy;
import SpringTemplate.demo.member.Grade;
import SpringTemplate.demo.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") //추가 구분자를 설정함으로 자동 의존관계 설정시 같은 타입으로 인한 오류 해결
//@Primary //fix와 rate 둘 다 타입이 같지만 @Primary로 지정된 rate클래스가 우선 순위로 매칭된다.
@MainDiscountPolicy //직접 만든 애노테이션을 가져다 쓰기.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
