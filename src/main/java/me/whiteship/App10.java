package me.whiteship;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App10 {

    //@Target(ElementType.TYPE_USE)이면 이렇게 여러군데에서 @Chicken 어노테이션을 사용할 수 있다.
    public static void main(@Chicken String[] args) throws @Chicken RuntimeException {
        List<@Chicken String> names = Arrays.asList("lby");
    }

    static class FeelsLikeChicken<@Chicken T> {

        public static <@Chicken C> void print(@Chicken C c) {
            System.out.println(c);
        }
    }
}
