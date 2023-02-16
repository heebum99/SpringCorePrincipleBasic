package SpringTemplate.demo.member;

public class MemberServiceImpl implements MemberService { //구현체를 관례상 impl이라고 지칭

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
