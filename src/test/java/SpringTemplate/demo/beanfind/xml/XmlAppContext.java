package SpringTemplate.demo.beanfind.xml;

import SpringTemplate.demo.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext { //순수 자바코드로 만들어진 Config가 아닌 Xml Config(설정 정보)를 이용해 설정하기

    @Test
    void xmlAppContext() { //resources의 appConfig.xml의 설정 정보에 따라 테스트하게 됨
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
