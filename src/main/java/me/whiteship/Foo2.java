package me.whiteship;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo2 {
    public static void main(String[] args) {
        final Foo2 foo2 = new Foo2();
        foo2.run();
    }
        private void run () {
            int baseNumber = 10;

            //로컬 클래스
            class LocalClass {
                void printBaseNumber() {
                    int baseNumber = 11;
                    System.out.println("baseNumber = " + baseNumber);
                }
            }

            //익명 클래스
            Consumer<Integer> integerConsumer = new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) {
                    System.out.println("integer = " + integer);
                }
            };

            //람다
            IntConsumer printInt = (i) -> {
                System.out.println(i + baseNumber);
            };

            printInt.accept(10);
        }

}
