package threadTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBrrierTest {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人。发车。。。");
            }
        });

        for(int i=0;i<100;i++){
             new Thread(()->{
                 try {
                     cb.await();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (BrokenBarrierException e) {
                     e.printStackTrace();
                 }
             }).start();
        }

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        atomicInteger.compareAndSet(1,2);
    }
}
