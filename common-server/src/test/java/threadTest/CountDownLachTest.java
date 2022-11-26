package threadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLachTest {
    private static void useCountDownLach(){
        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);



        for (int i=0;i<threads.length;i++){
            threads[i] = new Thread(() -> {
                System.out.println("线程countDown..");
                countDownLatch.countDown();
            });
        }

        for(int i=0;i<threads.length;i++){
            System.out.println("----threads["+ i + "]start---");
            threads[i].start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end lach..");

    }

    public static void main(String[] args) {
        useCountDownLach();
    }
}
