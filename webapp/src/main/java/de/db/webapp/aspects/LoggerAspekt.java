package de.db.webapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspekt {

    @Before("execution(public * de.db.webapp.controller.PersonenCommandController.*(..))")
    public void logAdvice(JoinPoint joinPoint) {
        log.warn("Hallo Aspect " + joinPoint.getSignature().getName() + " wurde gerufen");
    }

    @AfterReturning(value = "execution(public * de.db.webapp.controller.PersonenCommandController.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
       log.warn(result.toString());
    }
    @AfterThrowing(value = "execution(public * de.db.webapp.controller.PersonenCommandController.*(..))",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Fehler {} ist aufgetreten", ex);
    }

    @Around("execution(public * de.db.webapp.controller.PersonenCommandController.*(..))")
    public Object benchmark(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed();
    }
}
