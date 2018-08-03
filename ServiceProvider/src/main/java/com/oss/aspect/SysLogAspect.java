package com.oss.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/3.
 */
@Aspect
@Component
@Order(5)
public class SysLogAspect {

    private Logger logger = Logger.getLogger(getClass());

    @Pointcut("execution(public * com.oss.controller..*.*(..)) or execution(public * com.oss.services..*.*(..))")
    public void sysLog() {
    }

    @Before("sysLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("----syslog before----");
    }

    @AfterReturning(value = "sysLog()", returning = "ret")
    public void doAfterReturning(Object ret) {
        logger.info("----syslog afterReturning----" + ret);
    }

    @AfterThrowing(value = "sysLog()",throwing = "throwable")
    public void afterThrowable(Throwable throwable) {
        logger.error("----throwable----");
        throwable.printStackTrace();
    }
}
