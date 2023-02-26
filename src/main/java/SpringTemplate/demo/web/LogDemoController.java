package SpringTemplate.demo.web;

import SpringTemplate.demo.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //final 변수를 포함한 생성자 자동 생성하며 Autowired로 자동 주입까지.
public class LogDemoController {

    private final LogDemoService logDemoService;
    //MyLogger를 찾아서 연결해주는 DI가 아닌 MyLogger를 찾아주는 DL을 해준다.
    private final ObjectProvider<MyLogger> myLoggerProvider;
//    private final MyLogger myLogger;
    //의존 관계 주입할때 오류발생 => MyLogger의 스코프는 request 타입이라 요청이 들어올때 빈이 생성이 되는데 아무런 요청이 없기때문에 오류 발생


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString(); //고객이 어떤 url을 요청했는지 알 수 있다.
        MyLogger myLogger = myLoggerProvider.getObject(); //DL을 통해 MyLogger를 찾아온다.
        myLogger.setRequestURL(requestURL); //setter로 requestURL 값 초기화

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
