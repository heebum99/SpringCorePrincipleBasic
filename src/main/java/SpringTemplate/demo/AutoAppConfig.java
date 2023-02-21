package SpringTemplate.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //구성 설정을 위한 애노테이션
@ComponentScan( //컴포넌트 스캔을 사용하기 위한 애노테이션
        //컴포넌트 스캔 => -@Component 애노테이션이 붙은 클래스를 찾아서 자동으로 스프링빈으로 등록해준다.
        //스프링 빈에 자동 등록할때 필터로 거를것을 설정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //이전에 만들어놨던 수동으로 등록하는 AppConfig와 충돌나지 않기 위해 Configuration클래스는 제외 
)
public class AutoAppConfig {

}