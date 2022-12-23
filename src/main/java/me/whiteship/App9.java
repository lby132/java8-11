package me.whiteship;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class App9 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //비동기로 작업 실행하기 리턴타입이 없는 경우 runAsync
        final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future.get();

        //비동기로 작업 실행하기 리턴타입이 있는 경우 runAsync
        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future1.get());

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        final CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        final CompletableFuture<String> future2 = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println("future2 = " + future2.get());

        List<CompletableFuture> futures = Arrays.asList(hello, world);
        final CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        final CompletableFuture<List<Object>> result = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));

        final List<Object> objects = result.get();

        final CompletableFuture<Void> future3 = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future3.get();
    }
}
