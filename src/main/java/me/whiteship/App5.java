package me.whiteship;

public class App5 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start(); //start()로 실행하면 main스레드와 생성한 스레드가 동시에 실행된다.

        System.out.println("Hello: " + Thread.currentThread().getName());
        thread.join();  //main스레드에서 join()을 호출하면 생성한 스레드(9번라인에 sleep이 3초걸려있는 스레드)가 종료될때 까지 기다림.
        System.out.println(thread + " is finished");
    }
}
