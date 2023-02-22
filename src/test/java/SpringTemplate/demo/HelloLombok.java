package SpringTemplate.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Getter, Setter를 따로 생성하지 않아도 롬복의 @getter, @setter 애노테이션을 작성하면 사용가능.
@Getter
@Setter
@RequiredArgsConstructor //final로 선언된 필드변수를 매개변수로 하는 생성자를 생성, 생성자 주입을 이용할때도 직접 만들어주지 않아도 됨.
@ToString
//생성자, ToString등 여러 기능을 지원해줌
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("안녕");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
