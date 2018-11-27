package org.yky.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by hp on 2018/8/1.
 */
@Component  //加入到IoC容器
@Aspect
public class CommomAspect {

    @Pointcut("execution(* org.yky.controller.*.*(..))")
    public void poA() {}

    @Before("poA()")
    public void before() {
        System.out.println("before");
    }

    @AfterReturning(returning = "result", pointcut = "poA()")
    public void doAfterReturning(Object result) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("return");
    }

    //后置异常通知
    @AfterThrowing("poA()")
    public void throwss(JoinPoint jp){
        System.out.println("throw");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("poA()")
    public void after(JoinPoint jp){
        System.out.println("after" + jp.getThis().getClass().getName());
    }

//    //环绕通知,环绕增强，相当于MethodInterceptor
//    @Around("poA()")
//    public Object arround(ProceedingJoinPoint pjp) {
//        Object o = null;
//        System.out.println("before1");
//        try {
//            o =  pjp.proceed();
//            System.out.println("方法环绕proceed，结果是 :" + o);
//        } catch (Throwable e) {
//            System.out.println("throw1");
//        }
//        System.out.println("after1");
//        return o;
//    }
}
