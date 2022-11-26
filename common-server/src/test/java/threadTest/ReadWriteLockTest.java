package threadTest;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    private static  Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock readLock =  reentrantReadWriteLock.readLock();//共享锁  读线程共享
    static Lock writeLock = reentrantReadWriteLock.writeLock();//排他锁 也叫互斥锁  写线程互斥


    private static void read(Lock lock){
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("read over.."+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock,int v){
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
//        new Thread(readWriteLockTest::read).start();
//        Runnable readR = () -> readWriteLockTest.read(lock);
        Runnable readR = () -> readWriteLockTest.read(readLock);

        Runnable writeW = () -> readWriteLockTest.write(writeLock,new Random().nextInt());

        for(int i=0;i<18;i++){
            new Thread(readR).start();
        }

        for(int i=0;i<2;i++){
            new Thread(writeW).start();
        }
    }



}
