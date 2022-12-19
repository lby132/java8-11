package me.whiteship;

public interface FooInterface {

    void printName();

    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();
}
