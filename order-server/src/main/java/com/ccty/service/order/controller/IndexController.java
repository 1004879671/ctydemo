package com.ccty.service.order.controller;

import com.ccty.service.order.service.IndexServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单信息查询相关接口")
@RestController
@RequestMapping("/order")
public class IndexController {
    @Autowired
    private IndexServiceImpl indexServiceImpl;

    @ApiOperation(value="a测试")
    @GetMapping(value="/a")
    public String getSouceA(@RequestParam String name){
        String a = indexServiceImpl.getA(name);
        System.out.println(a);
        return indexServiceImpl.getA(name);
    }

    @GetMapping(value="/b")
    public String getSouceB(@RequestParam String name){
        return "您正在访问B资源"+name;
    }


}
