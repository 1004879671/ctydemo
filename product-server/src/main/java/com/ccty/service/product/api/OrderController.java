package com.ccty.service.product.api;

import com.ccty.service.product.service.ProductService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class OrderController {

    private static final String product = "123456789";


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private Redisson redission;



    @RequestMapping("/sumitOrder")
    public String sumitOrder(){
        //互斥性与锁超时
//        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product,"ant",30, TimeUnit.SECONDS);
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product,"ant");
        stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);

        if(!lock){//支持阻塞
            while(!lock){
//                lock = stringRedisTemplate.opsForValue().setIfAbsent(product,"ant",30, TimeUnit.SECONDS);
                lock = stringRedisTemplate.opsForValue().setIfAbsent(product,"ant");
                stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);
                if(lock){
                     break;
                }
            }
            return "error";//非阻塞
        }
        if(lock){
            try {
                int stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
                if (stock > 0) {
                    stock = stock - 1;
                    stringRedisTemplate.opsForValue().set("stock", stock + "");
                    System.out.println("库存扣减成功，库存stock：" + stock);
                } else {
                    System.out.println("库存扣减失败，库存stock：" + stock);
                }
            }catch (Exception e){

            }finally {
                stringRedisTemplate.delete(product);
            }
        }
        return "剩余库存："+stringRedisTemplate.opsForValue().get("stock");
    }

    @RequestMapping("/sumitOrder1")
    public String sumitOrder1(){
        int stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
        synchronized (product){//服务部署是单节点时可行
            if(stock>0){
                stock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("库存扣减成功，库存stock："+stock);
            }else{
                System.out.println("库存扣减失败，库存stock："+stock);
            }
        }
        return "剩余库存："+stock;
    }

    @RequestMapping("/sumitOrder2")
    public String sumitOrder2() {
        productService.relStock();
        return "剩余库存："+stringRedisTemplate.opsForValue().get("stock");

    }

    @RequestMapping("/sumitOrder3")
    public String sumitOrder3(){

        RLock lock1 = redission.getLock(product);
        lock1.lock();
        try {
            int stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                stock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", stock + "");
                System.out.println("库存扣减成功，库存stock：" + stock);
            } else {
                System.out.println("库存扣减失败，库存stock：" + stock);
            }
        }catch (Exception e){

        }finally {
            lock1.unlock(); 
        }
        return "剩余库存："+stringRedisTemplate.opsForValue().get("stock");
    }

}
