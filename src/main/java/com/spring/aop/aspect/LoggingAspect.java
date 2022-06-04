package com.spring.aop.aspect;

import com.spring.aop.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@Order(2)
public class LoggingAspect {

    @Around("execution(* com.spring.aop.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("=========> @Around-start exec addAccount() Service, {}", proceedingJoinPoint.getSignature().toString());
        long begin = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            log.info("Exception: {}", e.toString());
            result = "Handling exception";
            // to rethrow the exception
            // throw e;
        }
        long end = System.currentTimeMillis();
        long duration = end - begin;
        log.info("=========> Duration {} seconds", duration / 1000.0);
        return result;
    }

    @After("execution(* com.spring.aop.service.AccountService.findAccounts(..))")
    public void afterAddAccountService(JoinPoint joinPoint) {
        log.info("=========> @After exec addAccount() Service, {}", joinPoint.getSignature().toString());
    }

    @AfterThrowing(pointcut = "execution(* com.spring.aop.service.AccountService.findAccounts(..))",
            throwing = "t")
    public void afterThrowingAddAccountService(JoinPoint joinPoint, Throwable t) {
        log.info("=========> @AfterThrowing exec addAccount() Service, {}", joinPoint.getSignature().toString());
        log.info(t.toString());
    }

    @AfterReturning(pointcut = "execution(* com.spring.aop.service.AccountService.findAccounts(..))",
            returning = "result")
    public void afterReturningAddAccountService(JoinPoint joinPoint, List<Account> result) {
        log.info("=========> @AfterReturning exec addAccount() Service, {}", joinPoint.getSignature().toString());
        log.info(result.toString());
        result.stream().map(r -> {
            r.setName(r.getName().toUpperCase());
            return r;
        }).collect(Collectors.toList());
    }

    @Before("com.spring.aop.aspect.AopExpressions.forServicePackageNoGetterNoSetter()")
    public void beforeAddAccountService(JoinPoint joinPoint) {
        log.info("=========> @Before exec addAccount() Service");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Method: {}", methodSignature);

        Object[] objects = joinPoint.getArgs();

        for (Object o : objects) {
            log.info(o.toString());
            if (o instanceof Account) {
                Account account = (Account) o;
                log.info("Account: {}", account);
            }
        }
    }
}
