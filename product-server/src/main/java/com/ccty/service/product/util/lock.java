package com.ccty.service.product.util;


import java.util.concurrent.TimeUnit;

interface RedisLock {

    public boolean tryLock(String key, long timeOut, TimeUnit util);

    public void releaseLock(String key);
}
