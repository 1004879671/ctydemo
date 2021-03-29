package com.ccty.service.product.service.impl;

import com.ccty.service.product.entity.Product;
import com.ccty.service.product.map.ProductMapper;
import com.ccty.service.product.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String product = "123456789";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ProductMapper productDao;


    @Override
    public void relStock() {
        RLock rLock = redissonClient.getLock(product);
        try {
            boolean lock = rLock.tryLock(10, TimeUnit.SECONDS);
            if(lock) {
                int stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
                if (stock > 0) {
                    stock = stock - 1;
                    stringRedisTemplate.opsForValue().set("stock", stock + "");
                    System.out.println("库存扣减成功，库存stock：" + stock);
                } else {
                    System.out.println("库存扣减失败，库存stock：" + stock);
                }
            }
        }catch (Exception e){

        }finally {
            rLock.unlock();
            stringRedisTemplate.delete(product);
        }
    }



    @Override
    public Product findById(String  id) {
        return productDao.findById(id);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(String id) {
        productDao.delete(id);
    }
}
