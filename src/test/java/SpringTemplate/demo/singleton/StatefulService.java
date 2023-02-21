package SpringTemplate.demo.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드, 싱글톤에서의 잘못된 설계

//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기서 문제가 발생
//    } => 잘못된 설계

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
