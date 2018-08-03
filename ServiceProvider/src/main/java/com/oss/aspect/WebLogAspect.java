package com.oss.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *   使用@Aspect注解将一个java类定义为切面类
     使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
     根据需要在切入点不同位置的切入内容
         使用@Before在切入点开始处切入内容
         使用@After在切入点结尾处切入内容
         使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
         使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
         使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 */
@Aspect
@Component
@Order(0)
public class WebLogAspect {

    private Logger logger = Logger.getLogger(getClass());

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    /*
     execution解析
     public：匹配所有目标类的public方法
     第一个*号 ：表示返回类型，*号表示所有的类型
     com.oss.controller.. ：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包
     第二个*号 ：表示类名，*号表示所有的类
     *(..) ：最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
      */
    @Pointcut("execution(public * com.oss.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("REQUEST CONTENT [ URL : " + request.getRequestURL().toString()
                + ", HTTP_METHOD : " + request.getMethod()
                + ", IP : " + request.getRemoteAddr()
                + ", CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                + ", ARGS : " + Arrays.toString(joinPoint.getArgs()) + " ]");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME(ms) : " + (System.currentTimeMillis() - startTime.get()));
    }

}