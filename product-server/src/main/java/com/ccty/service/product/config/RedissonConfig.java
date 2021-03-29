package com.ccty.service.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
     public RedissonClient redissonClient() {
         Config config = new Config();
         config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//         config.useSingleServer().setPassword("cty123456");
         RedissonClient redisson = Redisson.create(config);
         return redisson;
    }

//    private static Config config = new Config();
//    //声明redisso对象
//    private static Redisson redisson = null;
//    //实例化redisson
//    static{
//
//        config.useSingleServer().setAddress("127.0.0.1:6379");
//        //得到redisson对象
//        redisson = (Redisson) Redisson.create(config);
//
//    }

    //获取redisson对象的方法
//    public static Redisson getRedisson(){
//        return redisson;
//    }

}
