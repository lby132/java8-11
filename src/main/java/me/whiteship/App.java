package me.whiteship;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) {

        Function<String, Greeting> newGreeting1 = Greeting::new;
        Supplier<Greeting> newGreeting2 = Greeting::new;
        //두개가 같아 보이지만 newGreeting1은 파라미터가 있는 생성자를 참조하고 있고 newGreeting2는 파라미터가 없는 기본생성자를 참조하고 있다.
        //테스트를 해보면
        final Greeting lee = newGreeting1.apply("lee");
        System.out.println("lee.getName() = " + lee.getName());
        //파라미터가 있는 생성자에 값이 들어가게 된걸 확인할 수 있다.

        String[] names = {"lee", "bbbb", "cccc"};
        //임의 객체의 인스턴스 메서드 참조 예제
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
        //String::compareToIgnoreCase처럼 메서드 래퍼런스를 사용할수 있다.


        //인터페이스에서 디폴트사용
        final DefaultFoo foo = new DefaultFoo("lee");
        foo.printName();
        foo.printNameUpperCase();
    }
}
