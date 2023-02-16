package SpringTemplate.demo.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    //일반 Hashmap을 사용시 동시성 문제가 있을수 있기 때문에 ConcurrentHashmap을 사용하는 것이 좋음.
    //ConcurrentHashmap는 읽기 작업보다는 쓰기 작업의 성능이 중요한 상황에서 쓰면 적합(멀티 쓰레드에서 동시에 쓰는 작업 가능)
    private static Map<Long, Member> store = new HashMap<>();

    //오류 처리는 생략
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
