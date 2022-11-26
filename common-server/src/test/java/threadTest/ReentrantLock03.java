package threadTest;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock03 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lockInterruptibly();
            for(int i=0;i<10;i++){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
            System.out.println("m2....");
    }
    public static void main(String[] args) {
        ReentrantLock03 rl = new ReentrantLock03();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
            new Thread(rl::m1).interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(rl::m2).start();
    }
}
