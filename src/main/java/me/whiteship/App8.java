package me.whiteship;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App8 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "hello";
        };

        Callable<String> hello1 = () -> {
            Thread.sleep(3000L);
            return "hello1";
        };

        Callable<String> hello2 = () -> {
            Thread.sleep(1000L);
            return "hello2";
        };

        //invokeAll은 모든 스레드들이 들어올때까지 기다렸다가 출력
//        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, hello1, hello2));
//        for (Future<String> f : futures) {
//            System.out.println(f.get());
//        }

        //invokeAny는 먼저 들어오는 것만 출력
        final String s = executorService.invokeAny(Arrays.asList(hello, hello1, hello2));
        System.out.println("invokeAny = " + s);


        executorService.shutdown();
    }
}
