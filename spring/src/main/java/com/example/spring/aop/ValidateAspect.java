package com.example.spring.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;

@Aspect
@Component
public class ValidateAspect {
    @Around("@annotation(com.example.spring.aop.ParamsCheck)")
    public Object ParamsCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ParamsCheck paramsCheckAnnotation = method.getAnnotation(ParamsCheck.class);
        if (paramsCheckAnnotation != null && paramsCheckAnnotation.ignore()) {
            return proceedingJoinPoint.proceed();
        }
        Object[] objects = proceedingJoinPoint.getArgs();
        for (Object o : objects) {
            if (o == null) {
                break;
            }
        }
        return null;
    }

}
