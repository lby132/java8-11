package me.whiteship;

import java.util.concurrent.*;

public class App7 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Callable<String> hello = () -> {    //리턴을 받을 수 있음
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());
        System.out.println("Started!");

        helloFuture.get();

        System.out.println(helloFuture.isDone());
        System.out.println("End!!");
        executorService.shutdown();
    }
}
