package threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Testadd {
    static List<Object> list = new ArrayList<>();
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);


        Thread t2 = new Thread(() ->{
            System.out.println("t2 启动");
            if(list.size()!=5){
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
            countDownLatch2.countDown();
        },"t2");

        t2.start();

        Thread t1 = new Thread(() ->{
            System.out.println("t1 启动");
            for(int i=0;i<10;i++){
                list.add("1");
                System.out.println("add.."+i);

                if(list.size()==5){
                    countDownLatch.countDown();
                    try {
                        countDownLatch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1 结束");
        },"t1");

        t1.start();

    }

}
