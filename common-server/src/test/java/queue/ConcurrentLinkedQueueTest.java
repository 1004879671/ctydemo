package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class ConcurrentLinkedQueueTest {

    static Queue<String> clq = new ConcurrentLinkedDeque<>();
    static List<String>  lt = new ArrayList<>();

    static {
        for(int i=0;i<1000;i++){
            clq.add("票号" + i);
//            lt.add("票号" + i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(true){
                    String tickets = clq.poll();
                    if(tickets==null){
                        break;
                    }else{
                        System.out.println("---销售了---"+tickets);
                    }


//  线程不安全   会出现超卖现象
//                    if(lt.size()==0){
//                        break;
//                    }else{
//                        System.out.println("---销售了---"+lt.get(0));
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        lt.remove(0);
//                    }


                }
            }).start();
        }
    }
}
