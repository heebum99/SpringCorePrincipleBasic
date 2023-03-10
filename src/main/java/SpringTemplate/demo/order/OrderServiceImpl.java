package SpringTemplate.demo.order;

import SpringTemplate.demo.annotation.MainDiscountPolicy;
import SpringTemplate.demo.discount.DiscountPolicy;
import SpringTemplate.demo.discount.FixDiscountPolicy;
import SpringTemplate.demo.discount.RateDiscountPolicy;
import SpringTemplate.demo.member.Member;
import SpringTemplate.demo.member.MemberRepository;
import SpringTemplate.demo.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    //@Autowired private final MemberRepository memberRepository; => 의존관계 필드 주입 방식
    //필드로 의존관계를 주입하려면 결국 private 필드기 때문에 setter가 필요하다.

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    } => 의존관계 수정자(setter) 주입 방식

    private final MemberRepository memberRepository;
    //할인 정책을 바꿈으로 코드를 변경하는것 => OCP위반.

//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * => private final DiscountPolicy로 바뀐다는 것은
     * OrderServiceImpl는 인터페이스 DiscountPolicy 뿐만아니고 구현체 Fix ~ , Rate ~ 에도 영향을 받고 있으므로 DIP 위반.
     **/
    private final DiscountPolicy discountPolicy; //인터페이스에만 의존 => DIP 적합, 현재는 NullPointException 발생
    //DiscountPolicy에 들어갈 구현체를 생성자를 통해 선택

//    @Autowired //생성자가 한개인 경우에는 생략가능하다. => 의존관계 생성자 주입 방식
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired //생성자가 한개인 경우에는 생략가능하다. => 의존관계 생성자 주입 방식
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        //직접 만든 애노테이션 사용
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        @Qualifier로 추가 구분자를 이용한 문제 해결
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired 
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy; //discountPolicy는 fix, rate일때로 같은 타입이 존재하므로 존재하는 특정 이름으로 설정
//        //@Autowired가 같은 타입이 존재할때 필드명, 파라미터명으로 빈 이름과 추가 매칭
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
