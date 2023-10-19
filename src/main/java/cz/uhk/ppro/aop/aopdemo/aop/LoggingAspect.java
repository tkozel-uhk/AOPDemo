package cz.uhk.ppro.aop.aopdemo.aop;

import cz.uhk.ppro.aop.aopdemo.model.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* cz.uhk.ppro.aop.aopdemo.repos.*.*(..))")
    void allRepositoryMethods() {}

    @Pointcut("execution(* cz.uhk.ppro.aop.aopdemo.repos.*.get*(..))")
    void allRepositoryGetMethods() {}

    @Pointcut("execution(* save(..))")
    void allSaveMethods() {}

    @Before("allRepositoryMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("allRepositoryMethods - zavoláno před "+ joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName());
    }

    @After("allRepositoryMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("allRepositoryMethods - zavoláno po "+ joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName());
    }

    @Around("allRepositoryGetMethods()")
    public List<Person> logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Okolo metody "+ joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName() + " před zavoláním");
        List<Person> result = (List<Person>) joinPoint.proceed();
        System.out.println("Okolo metody "+ joinPoint.getSignature().getName() + " třídy " + joinPoint.getTarget().getClass().getName() + " po zavolání");
        return result;
    }


    @Around("allSaveMethods()")
    public void logAroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("In method "+ joinPoint.getSignature().getName() + " of class " + joinPoint.getTarget().getClass().getName() + " before method call");
        joinPoint.proceed();
        System.out.println("In method "+ joinPoint.getSignature().getName() + " of class " + joinPoint.getTarget().getClass().getName() + " after method call");
    }

}
