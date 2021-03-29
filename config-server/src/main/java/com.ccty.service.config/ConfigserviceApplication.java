package com.ccty.service.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
  * @ClassName: ConfigserviceApplication
  * @Description: 配置中心 添加注解@EnableConfigServer
  * @author xub
  * @date 2019/7/12 下午3:39
  */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigserviceApplication.class, args);
	}
}
