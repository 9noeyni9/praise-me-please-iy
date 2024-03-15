package com.spring.nbcijo.global.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class RequestLog {

    @Pointcut("execution(* com.spring.nbcijo.controller..*.*(..)) && !execution(* com.spring.nbcijo.controller.UserController.*(..))")
    private void cut() {

    }

    @Before("cut()")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n").append("=========================================================================================\n");
        buffer.append("Class Method : ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName());
        buffer.append("[ ").append(LocalDateTime.now()).append(" ]\n");
        buffer.append("Request URL  : ").append(request.getRequestURL().toString());
        buffer.append("\nHTTP Method  : ").append(request.getMethod());
        buffer.append("\nRequest URI  : ").append(request.getRequestURI());
        buffer.append("\nIP Address   : ").append(request.getRemoteAddr());
        buffer.append("\nUsername     : ").append(getUsernameFromHeader(joinPoint));
        buffer.append("\n=========================================================================================");

        if (joinPoint.getSignature().getDeclaringType().getSimpleName().contains("controller")) {
            log.info(String.valueOf(buffer));
        } else {
            log.info(String.valueOf(buffer));
        }
    }

    private String getUsernameFromHeader(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return username;
    }

}
