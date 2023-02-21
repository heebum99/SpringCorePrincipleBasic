package SpringTemplate.demo.member;

public class MemberServiceImpl implements MemberService { //구현체를 관례상 impl이라고 지칭

    //생성자를 통해서 memberRepository에 들어갈 구현체를 선택 => DIP, 의존관계 주입(DI)
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
