package com.example.employeemanagement.aop.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AopLogger {

	private Logger logger;

	@Before("execution(* com.example.employeemanagement.*.*.*(..))")
	public void before(JoinPoint joinPoint) throws Throwable {
		logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		logger.debug(joinPoint.getSignature().getName());
		logger.debug(Arrays.toString(joinPoint.getArgs()));
	}

	@AfterThrowing(pointcut = "execution(* com.example.employeemanagement.*.*.*(..))", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		logger.error(joinPoint.getSignature().getName(), exception.getMessage());
		logger.error("Exception :", exception);
	}

	@AfterReturning(pointcut = "execution(* com.example.employeemanagement.*.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		logger.debug(joinPoint.getSignature().getName() + "Returned value" + result);
	}

	@After("execution(* com.example.employeemanagement.*.*.*(..))")
	public void after(JoinPoint joinPoint) {
		logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		logger.debug(joinPoint.getSignature().getName());
		logger.debug(Arrays.toString(joinPoint.getArgs()));
	}
	
	//AOP expression for which methods shall be intercepted
    @Around("execution(* com.example.employeemanagement.*.*.*(..))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
         
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
         
        final StopWatch stopWatch = new StopWatch();
         
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
 
        //Log method execution time
        logger.debug("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
 
        return result;
    }

}