package SpringTemplate.demo.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//애노테이션 직접 작성
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
