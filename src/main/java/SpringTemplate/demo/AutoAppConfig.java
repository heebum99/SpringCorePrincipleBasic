package SpringTemplate.demo;

import SpringTemplate.demo.member.MemberRepository;
import SpringTemplate.demo.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //구성 설정을 위한 애노테이션
@ComponentScan( //컴포넌트 스캔을 사용하기 위한 애노테이션
        //basePackages = "SpringTemplate.demo.member", => 컴포넌트 스캔 탐색할 패키지의 시작위치를 지정
        //컴포넌트 스캔 => -@Component 애노테이션이 붙은 클래스를 찾아서 자동으로 스프링빈으로 등록해준다.
        //스프링 빈에 자동 등록할때 필터로 거를것을 설정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //이전에 만들어놨던 수동으로 등록하는 AppConfig와 충돌나지 않기 위해 Configuration클래스는 제외

)
public class AutoAppConfig {

    //자동으로 등록된 빈과 충돌나게 수동으로 같은 이름의 빈을 등록
    //수동, 자동으로 등록된 빈의 이름이 같은 경우 오류발생x, 수동 등록된 빈이 오버라이딩되서 우선권을 갖는다.
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
