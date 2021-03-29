import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author huangwb
 * @Date 2019-02-23 14:24
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {
    //    @Autowired
//    private UserService userService;
    //读取数据库保存到的list
    private List<String> list = new ArrayList<>();
    //不想打印list 干脆就保存在list2中 模拟读取的数据添加进数据表操作
    private List<String> list2 = new ArrayList<>();
    //用来统计线程是否执行完毕 如果countDownLatch为0代表全部执行完毕了
    private CountDownLatch countDownLatch = null;
    private CountDownLatch countDownLatch2 = null;
    private ReentrantReadWriteLock readLock = new ReentrantReadWriteLock();


    //线程池固定3个线程来读取
    private void readUserTable(Integer threadNum) throws InterruptedException {
        ExecutorService readUserTableExectors = Executors.newFixedThreadPool(threadNum);
        //数据分段分页 数据库查询的操作 我目标添加了20W个用户
//        Integer totalSize = userService.selectAll().size();
        Integer totalSize = 200000;
        Integer pageSize = totalSize % threadNum == 0 ? totalSize / threadNum : (totalSize / threadNum + 1);
        countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            int startSize = i * pageSize;
            int end = pageSize * (i + 1);
            readUserTableExectors.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        readLock.readLock().lock();
                        System.out.println("线程" + Thread.currentThread().getName() + "在模拟数据库读取保存list操作");
                        for(int j=startSize;j<=end;j++){
                            list.add(String.valueOf(j));
                        }
//                        list.addAll(userService.selectAllByPageSizeAndLimit(pageSize, limit));
                        countDownLatch.countDown();
                    } finally {
                        readLock.readLock().unlock();
                    }
                }
            });
        }


    }


    //线程池固定10个线程来读取保存到的list
    private void createTenThreadReadList(List<String> list, Integer threadNum) {
        ExecutorService readListExecutors = Executors.newFixedThreadPool(threadNum);
        Integer pageSize = list.size() % threadNum == 0 ? list.size() / threadNum : (list.size() / threadNum + 1);
        countDownLatch2 = new CountDownLatch(threadNum);
        ReentrantReadWriteLock readWriteLockLock = new ReentrantReadWriteLock();
        for (int i = 0; i < threadNum; i++) {
            //数据分段读取添加list
            int end = pageSize * (i + 1);
            int startSize = i * pageSize;
            int forCountSize = end > list.size() ? list.size() : end;
            System.out.println("start=" + startSize + "     forSize=" + forCountSize);
            readListExecutors.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        readWriteLockLock.writeLock().lock();
                        System.out.println("线程" + Thread.currentThread().getName() + "在读取list操作");
                        for (int j = startSize; j < forCountSize; j++) {
                            list2.add(list.get(j));
                        }
                        countDownLatch2.countDown();
                    } finally {
                        readWriteLockLock.writeLock().unlock();
                    }
                }
            });
        }
    }


    private boolean equalList(List list1, List list2) {
        return (list1.size() == list2.size()) && list1.containsAll(list2);
    }


    @Test
    public void read() throws InterruptedException {
        //三个线程读取数据库
        readUserTable(3);
        countDownLatch.await();
        //10个线程读前面读取数据库的list内容保存进另外一个list
        createTenThreadReadList(list, 10);
        countDownLatch2.await();
        System.out.println("list1有" + list.size() + "个用户");
        System.out.println("list2有" + list2.size() + "个用户");
        //判断两个list是否一致
        System.out.println("比较两个list是否一致" + equalList(list, list2));
    }
}


