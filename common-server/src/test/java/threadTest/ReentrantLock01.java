package threadTest;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock01 {
    ReentrantLock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
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
        try {
            lock.lock();
            System.out.println("m2....");
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantLock01 rl = new ReentrantLock01();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(rl::m2).start();
    }
}
