package com.example.bootdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 打印sql执行方法
 */
@Aspect
@Component
@Slf4j
public class MybatisAspect {
    @AfterReturning("execution(* com.example.bootdemo.dao.*Mapper.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        log.info("Completed: " + joinPoint);
    }

    @Pointcut("execution(* com.example.bootdemo.dao.*Mapper.*(..))")
    private void pointCutMethod() {

    }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();

        log.info("调用Mapper方法：{}，参数：{}，\n耗时：{}毫秒",
                pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()), (end - begin) / 1000000);
        return obj;
    }
}
