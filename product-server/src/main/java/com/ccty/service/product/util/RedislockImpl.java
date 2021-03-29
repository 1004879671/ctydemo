package com.ccty.service.product.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedislockImpl implements  RedisLock{

//    private static final String product = "123456789";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private ThreadLocal threadLocal = new ThreadLocal();//线程安全问题，线程复用

    private String s = "";

    @Override
    public boolean tryLock(String key, long timeOut, TimeUnit unit) {
        boolean lock = false;
        if(threadLocal.get()==null){//第一次进来
            //高可用
            Thread thread = new Thread(){//异步编程，实现锁的异步续命
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        stringRedisTemplate.expire(key,timeOut,unit);//重新设置过期时间
                    }
                }
            };

            String uuid = thread.getId()+":"+UUID.randomUUID().toString();//
            threadLocal.set(uuid);
//            s = uuid ; //如果是这样设置。单例，有线程安全问题
            //互斥性与锁超时
//            lock = stringRedisTemplate.opsForValue().setIfAbsent(key,uuid,timeOut,unit);
            lock = stringRedisTemplate.opsForValue().setIfAbsent(key,uuid);
            stringRedisTemplate.expire(key,timeOut, unit);
            if(!lock){//支持阻塞
                while(!lock){
//                    lock = stringRedisTemplate.opsForValue().setIfAbsent(key,uuid,timeOut,unit);
                    lock = stringRedisTemplate.opsForValue().setIfAbsent(key,uuid);
                    stringRedisTemplate.expire(key,timeOut, unit);
                    if(lock){
                        break;
                    }
                }
            }

            thread.start();
        }else if(threadLocal.get().equals(stringRedisTemplate.opsForValue().get(key))){//支持可重入。后面同个进程进来走这里。
            return true;
        }
        return lock;
    }

    @Override
    public void releaseLock(String key) {
        if(threadLocal.get().equals(stringRedisTemplate.opsForValue().get(key))) {//保证是同一个线程去释放锁
            //停止异步线程
            String[] threadID = threadLocal.get().toString().split(":");
            if(threadID.length>0){
                long thID = Long.valueOf(threadID[0]);
                Thread thread = findThread(thID);
                thread.interrupt();
            }

            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 通过线程组获得线程
     *
     * @param threadId
     * @return
     */
    public static Thread findThread(long threadId) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
                if(threadId == threads[i].getId()) {
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }
}
