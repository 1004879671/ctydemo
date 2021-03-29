package com.ccty.service.order.service;

import com.ccty.service.order.aop.MyLog;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl {

    @MyLog
    public String getA(String name){
        return "您正在访问A资源";
    }
}
