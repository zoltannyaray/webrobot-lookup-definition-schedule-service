package com.dayswideawake.webrobot.aop.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());
	
	@Around("execution(* *(..)) && @annotation(com.dayswideawake.webrobot.aop.annotation.Loggable)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = point.proceed();
		String where = point.getSignature().getDeclaringType().getName() + "." + point.getSignature().getName();
		Object[] args = point.getArgs();
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info(String.format( "%s(%s) -> %s (%s msec)", where, args, result, elapsedTime));
		return result;
	}

}
