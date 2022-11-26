package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable{
    private  int i;

    public Task(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Task{" +
                "i=" + i +
                '}';
    }

    @Override
    public void run() {

        System.out.println("线程执行业务逻辑---");
        System.out.println(Thread.currentThread().getName() + "Task" + i);

    }

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i=0;i<8;i++){
            tpe.execute(new Task(i));
        }
        System.out.println(tpe.getQueue());
        tpe.execute(new Task(1000));
        System.out.println(tpe.getQueue());
        tpe.shutdown();
    }


}
