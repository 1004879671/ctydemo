package queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 无界队列
 */
public class LinkedBlockingQueueTest {
    static BlockingQueue<String> strs = new LinkedBlockingDeque<>();

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() ->{
            for (int i=0;i<100;i++){
                try {
                    strs.put("a"+i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for(int i=0;i<5;i++){
            new Thread(()->{
                for (;;){
                    try {
                        System.out.println(Thread.currentThread().getName() + "take - " + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c" + i).start();
        }


    }



}
