package SpringTemplate.demo;

import SpringTemplate.demo.member.Grade;
import SpringTemplate.demo.member.Member;
import SpringTemplate.demo.member.MemberService;
import SpringTemplate.demo.member.MemberServiceImpl;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class MemberApp {
    public static void main(String[] args) { //Appconfig를 이용해 실행
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        //ApplicationContext => 모든 @애노테이션들을 관리해주는 역할(스프링 컨테이너)
        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);
        //이름은 메소드명으로 적어줘야함, 두번째 인자는 타입.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
