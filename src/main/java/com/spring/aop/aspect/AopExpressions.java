package com.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.spring.aop.service.*.*(..))")
    public void forServicePackage() { }

    @Pointcut("execution(* com.spring.aop.service.*.get*(..))")
    public void getter() { }

    @Pointcut("execution(* com.spring.aop.service.*.set*(..))")
    public void setter() { }

    @Pointcut("forServicePackage() && !(getter() || setter())")
    public void forServicePackageNoGetterNoSetter() { }
}
