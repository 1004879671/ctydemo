package com.ccty.service.common.advice;

import com.ccty.service.common.common.CommonResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {
    private static Logger logger = LoggerFactory.getLogger(ApiExceptionAdvice.class);
    @Value("${spring.application.name:#{null}}")
    private String applicationName;

    private String getModuleName(){
        if(StringUtils.isBlank(applicationName)){
            return "";
        }
        return applicationName;
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e){
        String errorCode = Long.valueOf(System.currentTimeMillis()).toString();
        logger.error("发生系统错误，编号"+getModuleName()+errorCode,e);
        return new CommonResponse("500","系统错误，编号："+getModuleName()+errorCode);
    }
}
