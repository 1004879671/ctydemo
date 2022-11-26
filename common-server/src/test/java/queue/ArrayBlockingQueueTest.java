package queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界队列
 */
public class ArrayBlockingQueueTest {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {

        for (int i=0;i<10;i++){
            strs.put("a"+i);
        }
//        strs.put("aaaa");//满了就会等待，程序阻塞
//        strs.add("aaaa");//满了会异常
        boolean b = strs.offer("aaaa",1, TimeUnit.SECONDS);
        System.out.println(b);
        System.out.println(strs);


    }



}
