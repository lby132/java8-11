package me.whiteship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App6 {
    public static void main(String[] args) {
        //Executors는 스레드를 만들고 관리하고 작업을 실행한다.
        final ExecutorService executorService = Executors.newFixedThreadPool(2);//스레드 풀을 두개 생성했기 때문에 두개의 스레드로 번갈아가면서 코드를 실행한다.
        executorService.submit(getRunnable("Hello"));
        executorService.submit(getRunnable("Hello1"));
        executorService.submit(getRunnable("Hello2"));
        executorService.submit(getRunnable("Hello3"));
        executorService.submit(getRunnable("Hello4"));

        executorService.shutdown(); //현재 작업중인것들은 다 마치고 끝낸다.
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
