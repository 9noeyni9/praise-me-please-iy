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

        buffer.append("\n");
        buffer.append("=========================================================================================\n");
        buffer.append("Class Method : ");
        buffer.append(joinPoint.getSignature().getDeclaringTypeName());
        buffer.append(".");
        buffer.append(joinPoint.getSignature().getName());
        buffer.append("[ ");
        buffer.append(LocalDateTime.now());
        buffer.append(" ]\n");
        buffer.append("Request URL  : ");
        buffer.append(request.getRequestURL().toString());
        buffer.append("\nHTTP Method  : ");
        buffer.append(request.getMethod());
        buffer.append("\nRequest URI  : ");
        buffer.append(request.getRequestURI());
        buffer.append("\nIP Address   : ");
        buffer.append(request.getRemoteAddr());
        buffer.append("\nUsername     : ");
        buffer.append(getUsernameFromHeader(joinPoint));
        buffer.append("\n=========================================================================================");


//        String content = "\n" +
//                "==========================================================\n" +
//                "Class Method : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "[" + LocalDateTime.now() + "]\n" +
//                "Request URL  : " + request.getRequestURL().toString() + "\n" +
//                "HTTP Method  : " + request.getMethod() + "\n" +
//                "Request URI  : " + request.getRequestURI() + "\n" +
//                "IP Address   : " + request.getRemoteAddr() + "\n" +
//                "Username     : " + getUsernameFromHeader(joinPoint) + "\n" +
//                "===========================================================";

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
