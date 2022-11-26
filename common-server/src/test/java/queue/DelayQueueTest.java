package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    static BlockingQueue<Mytask> dq = new DelayQueue<>();

    static class Mytask implements Delayed{

        String name;
        long runningTime;

        @Override
        public String toString() {
            return "Mytask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }

        public Mytask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
                return  -1;
            }else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
                return  1;
            }else{
                return 0;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        long now = System.currentTimeMillis();
        Mytask t1 = new Mytask("t1",now + 1000);
        Mytask t2 = new Mytask("t2",now + 2000);
        Mytask t3 = new Mytask("t3",now + 3000);
        Mytask t4 = new Mytask("t4",now + 4000);
        Mytask t5 = new Mytask("t5",now + 5000);


        dq.put(t5);
        dq.put(t3);
        dq.put(t1);
        dq.put(t2);
        dq.put(t4);


        System.out.println(dq);

        for(int i=0;i<5;i++){
            System.out.println(dq.take());
        }
    }
}
