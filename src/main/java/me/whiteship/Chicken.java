package me.whiteship;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)   // TYPE_USE는 타입을 선언하는 모든곳이 가능하다.
//@Repeatable(ChickenContainer.class)
public @interface Chicken {
  // String value();
}
