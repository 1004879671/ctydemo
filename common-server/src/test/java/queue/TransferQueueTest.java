package queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {

    public static void main(String[] args) throws InterruptedException {
        TransferQueue<String> tf = new LinkedTransferQueue<>();
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(tf.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //线程  装完等着  阻塞
        tf.transfer("aaa");
        System.out.println("-----transfer-----");
        //装完就走
//        tf.put("bbb");
//        System.out.println("----put-----");




    }


}
