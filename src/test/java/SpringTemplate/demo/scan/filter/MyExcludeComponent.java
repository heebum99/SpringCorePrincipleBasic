package SpringTemplate.demo.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //TYPE => 클래스 레벨에 붙는다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent { //MyExcludeComponent가 붙으면 컴포넌트 스캔에서 제외

}
