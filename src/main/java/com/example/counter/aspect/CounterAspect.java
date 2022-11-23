package com.example.counter.aspect;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class CounterAspect {
    private final String exceptionPointcut = "execution(* com.example.counter.*.*.*(..))";

    private final String apiPointCut="execution(* com.example.counter.controller.*.*(..))";

    @Pointcut(apiPointCut)
    public void logController() {
    }

    @After("logController()")
    public void logRequest(JoinPoint joinPoint){
        log.info(joinPoint.getSignature().getName() + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = exceptionPointcut, throwing = "exception")
    public void logErrors(Throwable exception) {
        log.error(exception.getMessage());
    }
}
