package com.spring.nbcijo.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeTraceLog {

    @Around("execution(* com.spring.nbcijo.controller..*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuffer buffer = new StringBuffer();
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        buffer.append("\n==================================================================================================================\n");
        buffer.append(joinPoint.getSignature()).append(" 실행 시간 : ").append(executionTime).append("ms");
        buffer.append("\n==================================================================================================================\n");
        log.info(String.valueOf(buffer));
        return result;
    }
}
