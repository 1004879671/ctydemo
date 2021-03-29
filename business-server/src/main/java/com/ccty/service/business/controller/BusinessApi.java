package com.ccty.service.business.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("bisiness模块服务")
@RestController
@RequestMapping("/business")
public class BusinessApi {

    @Value("${name:true}")
    private boolean name;

    @GetMapping("/getName")
    public String getName(){
        if(!name){
            return "ccccc";
        }else{
            return "aaaaa";
        }
    }

}
