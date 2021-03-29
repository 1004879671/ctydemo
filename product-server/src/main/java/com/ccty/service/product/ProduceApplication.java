package com.ccty.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
  * @ClassName: ProduceApplication
  * @Description: 商品服务启动类
  * @author xub
  * @date 2019/7/12 下午12:29
  */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ProduceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProduceApplication.class, args);
	}
}
