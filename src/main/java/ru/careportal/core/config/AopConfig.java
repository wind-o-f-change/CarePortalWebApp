package ru.careportal.core.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class AopConfig {

    @Pointcut("within(ru.careportal.core.controller..*)")
    public void controllersPoint(){}

    @Before("controllersPoint()")
    public void logControllers(JoinPoint point) {
        log.debug(point.toShortString());
    }

    @AfterThrowing(value = "controllersPoint()", throwing = "e")
    public void logErrorControllers(JoinPoint point, Throwable e) {
        log.warn(String.format("%s method throw exception: %s", point.toShortString(), e));
    }

    @Pointcut("within(ru.careportal.core.service..*)")
    public void servicesPoint(){}

    @Before("servicesPoint()")
    public void logServices(JoinPoint point){
        log.debug(point.toShortString());
    }

    @AfterThrowing(value = "servicesPoint()", throwing = "e")
    public void logErrServices(JoinPoint point, Throwable e){
        log.warn(String.format("%s method throw exception: %s", point.toShortString(), e));
    }
}
