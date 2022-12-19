package me.whiteship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
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

        List<String> name = new ArrayList<>();
        name.add("lee");
        name.add("jong");
        name.add("kim");
        name.add("kim2");

        //Spliterator는 Itgerate와 비슷하게 나열하는 기능이지만 쪼개는 기능이 포함되어있다.
        final Spliterator<String> spliterator = name.spliterator();
        //trySplit을 써서 절반으로 쪼갤수 있다.
        final Spliterator<String> spliterator1 = spliterator.trySplit();

        while (spliterator.tryAdvance(System.out::println));
        System.out.println("=================================");
        while (spliterator1.tryAdvance(System.out::println));

        //l로 시작하는 데이터는 지운다.
        name.removeIf(s -> s.startsWith("l"));
        name.forEach(System.out::println);
    }
}
