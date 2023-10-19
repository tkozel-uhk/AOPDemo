package cz.uhk.ppro.aop.aopdemo.aop;

import cz.uhk.ppro.aop.aopdemo.model.Person;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Aspekt pro logování
 */
@Component
@Aspect
public class LoggingAspect {
    private Logger log; //reference na logovací bean

    @Autowired
    public LoggingAspect(Logger logger) {
        log = logger;
    }

    //Definice pointcutů
    @Pointcut("within(cz.uhk.ppro.aop.aopdemo.repos.*)")
    void allRepositoryMethods() {
    }

    @Pointcut("execution(* cz.uhk.ppro.aop.aopdemo.repos.*.get*(..))")
    void allRepositoryGetMethods() {
    }

    @Pointcut("execution(* save(..))")
    void allSaveMethods() {
    }

    //Definice advice
    @Before("allRepositoryMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("allRepositoryMethods - zavoláno před [@Before] " + joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName());
    }

    @After("allRepositoryMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("allRepositoryMethods - zavoláno po [@After] " + joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName());
    }

    @Around("allRepositoryGetMethods()")
    public List<Person> logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("allRepositoryGetMethods - kolem metody [@Around] " + joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName() + " před zavoláním");
        List<Person> result = (List<Person>) joinPoint.proceed();
        log.info("allRepositoryGetMethods - kolem metody [@Around] " + joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName() + " po zavolání");
        return result;
    }

    @Around("allSaveMethods()")
    public void logAroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("allSaveMethods - kolem metody [@Around]" + joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName() + " před zavoláním");
        joinPoint.proceed();
        log.info("allSaveMethods - kolem metody [@Around]" + joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName() + " po zavolání");
    }

}
