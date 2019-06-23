package com.inda.trafficbuz.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class LoggigAspect {
	
	@Before("execution(* com.inda.trafficbuz.repository.*.*(..))")
		public void log(JoinPoint jp) {
		System.out.println("LoggingAspect invoked before "+jp.getTarget()+"->"+jp.getSignature().getName());
		System.currentTimeMillis();
		
	}
	
	@Around("execution(* com.inda.trafficbuz.repository.*.*(..))")
		public Object profile(ProceedingJoinPoint jp) throws Throwable{
		StopWatch watch = new StopWatch(jp.getSignature().getName());
		watch.start();
		try {
			return jp.proceed();
		}
		finally {
			watch.stop();
			System.out.println(watch.prettyPrint());
		}
		
	}

}
