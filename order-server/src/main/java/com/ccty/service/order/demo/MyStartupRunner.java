//package com.ccty.service.order.demo;
//
//
//import com.alibaba.fastjson.JSON;
//import com.ccty.service.order.entity.Product;
//import com.ccty.service.order.util.JedisUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 服务启动时运行
// */
//@Component
//public class MyStartupRunner implements CommandLineRunner {
//
//    private Logger logger= LoggerFactory.getLogger(MyStartupRunner.class);
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//
//    @Override
//    public void run(String... strings) throws Exception {
//        List<Product> list= new ArrayList<Product>();
//        Product p = new Product();
//        p.setId("111L");
//        p.setPrice(new BigDecimal(1000.00));
//        p.setProductName("手机");
//        list.add(p);
//
//        Product pt = new Product();
//        pt.setId("222L");
//        pt.setPrice(new BigDecimal(2000.00));
//        pt.setProductName("电脑");
//        list.add(pt);
//
//        Product yf = new Product();
//        yf.setId("333L");
//        yf.setPrice(new BigDecimal(500.00));
//        yf.setProductName("衣服");
//        list.add(yf);
//
//        Jedis jedis = JedisUtils.getJedis();
//
//        for(Product pro :list){
////            redisTemplate.opsForZSet().add("productList",pro.getPrice(),pro.getId());
//            jedis.hset("product",pro.getId(),JSON.toJSONString(pro));
//        }
//
//        System.out.println(jedis.hget("product","111L"));
//
//        Product pd = (Product) JSON.parseObject(jedis.hget("product", "111L"), Product.class);
//        System.out.println(pd);
//
//
//        list.forEach(action->{
//            stringRedisTemplate.opsForHash().put("pt",action.getId(),JSON.toJSONString(action));
//        });
//
//        list.forEach(pro->{
//            jedis.zadd("zp1",pro.getPrice().setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue(),JSON.toJSONString(pro));
//            stringRedisTemplate.opsForZSet().add("zp2",pro.getProductName(),pro.getPrice().setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
//
//        });
//
//    }
//}