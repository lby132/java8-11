package me.whiteship;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
//        RunSomething runSomething = () -> System.out.println("Hello");
//        runSomething.doIt();

//        Plus10 plus10 = new Plus10();
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply = (i) -> i * 2;
        System.out.println(plus10.apply(1));

        //compose는 괄호안에있는걸 먼저 계산하고 plus10에 있는 값에 계산된 값을 대입한다. 즉 plus10의 i에는 multiply에서 계산된 값이 들어간다.
        final Function<Integer, Integer> compose = plus10.compose(multiply);
        //andThen은 compose와 반대로 본인걸 먼저 계산하고 괄호안에 있는걸 계산한다.
        final Function<Integer, Integer> integerIntegerFunction = plus10.andThen(multiply);
        System.out.println(compose.apply(5));
        System.out.println(integerIntegerFunction.apply(5));


        //Consumer는 T타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스이다.
        Consumer<Integer> printT = System.out::println;
        printT.accept(10);

        //타입의 값을 제공하는 함수 인터페이스. 그냥 값넣어주면 값을 출력한다.
        //매개변수를 받지 않고 단순히 무엇인가를 반환하는 추상메서드가 존재한다.
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());;

        //Predicate는 결과값으로 true, fals를 리턴해줌
        Predicate<String> startWithLby = (s) -> s.startsWith("lby");
        System.out.println(startWithLby.test("lbydddd"));
        Predicate<Integer> isEven = (i) -> i%2 == 0;
        System.out.println(isEven.test(3));

        //UnaryOperator는 위에서 테스트한 Function<T,R>의 특수형태이고 입력값의 타입과 결과값의 타입이 같을 경우 타입을 하나만으로도 사용가능
        UnaryOperator<Integer> plus = (i) -> i + 10;
        UnaryOperator<Integer> multi = (i) -> i * 10;

        //BinaryOperator는 BiFunction<T,T,T>의 특수 형태이고 타입이 세개가 같을때 한가지 타입만 명시해줘도 됨.
        BinaryOperator<Integer> b_plus = (n1,n2) -> n1 + n2;
        System.out.println("b_plus = " + b_plus.apply(10, 12));

    }
}
