package com.ccty.service.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLogAspect {

    @Pointcut("@annotation(com.ccty.service.order.aop.MyLog)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] param = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer();

        for(Object o:param){
            sb.append(o+";");
        }

        System.out.println("进入【"+methodName+"】方法，参数为："+sb.toString());
        Object o = null;
        try {
             o = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println(methodName+"方法执行结束");
        return o;
    }
}
