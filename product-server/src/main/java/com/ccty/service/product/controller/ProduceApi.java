package com.ccty.service.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("产品模块服务")
@RestController
@RequestMapping("/product")
public class ProduceApi {
    @Value("${name:0}")
    private String name;

    @ApiOperation(value = "name属性获取测试")
    @GetMapping("/getName")
    public String getName(){
        return name;
    }

    @ApiOperation(value="日志异常测试")
    @GetMapping("/test1")
    public String test1(@RequestParam(value = "id") String id) throws Exception{
        int i = 1/0;
        return "造异常";
    }
}
