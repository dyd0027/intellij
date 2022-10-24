package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//aop사용 어노테이션이고 스프링에서 관리해야 하므로 component 적어줌
@Aspect
@Component
public class ParameterAop {

    //execution에 관한건 구글링하면 잘 나옴
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    //before 지점에서 cut을 실행 시키겠다
    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            System.out.println("type: "+arg.getClass().getSimpleName());
            System.out.println("value: "+arg);
        }
    }

    //after같은 경우 object를 받을 수 있음. 이 때 변수명은 같아야 함.
    @AfterReturning(value = "cut()",returning = "obj")
    public void afterReturn(JoinPoint joinPoint,Object obj){
        System.out.println("return obj: "+obj);
    }
}
