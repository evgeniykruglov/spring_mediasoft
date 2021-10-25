package app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {

    @Pointcut(value = "execution(* app.controller.UserController.getUsers())")
    public void logGetUsers() {
    }

    @Around(value = "logGetUsers()")
    public Object beforeAdvice(ProceedingJoinPoint joinPoint) {
        Object result;
        System.out.println("Transaction opening...");
        try {
            Object obj = joinPoint.proceed();
            System.out.println("Transaction closing...");
            result = obj;
        } catch (Throwable throwable) {
            System.out.println("Operation failed, transaction rollback");
            result = throwable;
        }
        return result;
    }
}
