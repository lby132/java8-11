package me.whiteship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App6 {
    public static void main(String[] args) {
        //Executors는 스레드를 만들고 관리하고 작업을 실행한다.
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);    //3초 후에 실행

        executorService.shutdown(); //현재 작업중인것들은 다 마치고 끝낸다.
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
