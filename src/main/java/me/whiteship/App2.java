package me.whiteship;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App2 {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> javaEvent = new ArrayList<>();
        javaEvent.add(springClasses);
        javaEvent.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        javaEvent.stream().flatMap(Collection::stream)
                .forEach(OnlineClass::getId);

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        final boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
        final List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle) // map은 OnlineClass타입을 getTitle의 String타입으로 바꿔준다.
                                            // map(oc -> oc.getTitle()) 이렇게도 사용가능
                .collect(Collectors.toList());

        spring.forEach(System.out::println);

        System.out.println("====================Optional================================");
        final OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Optional<Progress> progress = spring_boot.getProgress();
        progress.ifPresent(p -> System.out.println(p.getStudyDuration()));
        //final Duration studyDuration = spring_boot.getProgress().getStudyDuration();

        final Optional<OnlineClass> spring1 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("sprding"))
                .findFirst();

        spring1.ifPresent(oc -> System.out.println(oc.getTitle())); //값이 있으면 반환 없으면 아무것도 리턴안함

        final OnlineClass onlineClass = spring1.orElse(createNewClass());
        System.out.println(onlineClass.getTitle()); // 타이틀에 값이 있으면 값을 반환하고 없으면 orElse()에 정해준 값이 반환됨.

        final OnlineClass onlineClass1 = spring1.orElseGet(App2::createNewClass); //orElse는 무조건 값을 출력한다. 매번 출력하기 싫으면 orElseGet을쓰자.
        System.out.println("onlineClassOrElseGet = " + onlineClass1.getTitle());

      //  OnlineClass onlineClass2 = spring1.orElseThrow(IllegalStateException::new); //값이 없으면 원하는 에러를 낼 수 있다.
      //  System.out.println("onlineClassThrow = " + onlineClass2.getTitle());

        final Optional<OnlineClass> onlineClass3 = spring1.filter(OnlineClass::isClosed);
        System.out.println(onlineClass3.isEmpty());

        // map과 flatMap의 차이점은 OnlineClass안에 Progress라는 객체가 있듯이 감싸고 있는것들을 한번에 까주지만
        // map은 아래와 같이 감싸고 있는 껍질을 두번에 걸쳐 까줘야한다.
        final Optional<Progress> flatMap = spring1.flatMap(OnlineClass::getProgress);
        final Optional<Optional<Progress>> map = spring1.map(OnlineClass::getProgress);
        final Optional<Progress> map2 = map.orElseThrow();

        //Stream에서의 flatMap 같은 경우는 input이 하나면 output이 여러개일수 있고
        //Optional에서의 flatMap 같은 경우에서는 input이 하나면 output이 하나이다.

    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New class", false);
    }
}
