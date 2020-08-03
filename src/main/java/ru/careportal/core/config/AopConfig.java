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

    @AfterThrowing("controllersPoint()")
    public void logErrorControllers(JoinPoint point) {
        log.error(point.toLongString());
    }

    @Pointcut("within(ru.careportal.core.service..*)")
    public void servicesPoint(){}

    @Before("servicesPoint()")
    public void logServices(JoinPoint point){
        log.debug(point.toShortString());
    }

    @AfterThrowing("servicesPoint()")
    public void logErrServices(JoinPoint point){
        log.error(point.toShortString());
    }
}
