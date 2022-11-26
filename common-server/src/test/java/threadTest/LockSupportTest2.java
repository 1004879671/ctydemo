package threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest2 {
    static List<Object> lt = new ArrayList<>();
    static Thread t1=null;
    static Thread t2=null;
    public static void main(String[] args) {
         t1 = new Thread(() ->{
             System.out.println("t1开始");
             for(int i=0;i<10;i++){
                lt.add(i);
                System.out.println("add"+i);
                if(lt.size()==5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
             }
             System.out.println("t1结束");
         },"t1");

        t2 = new Thread(() ->{
            System.out.println("t2开始");
                LockSupport.park();
            System.out.println("t2结束");
            LockSupport.unpark(t1);
        },"t2");

        t2.start();
        t1.start();

    }
}
