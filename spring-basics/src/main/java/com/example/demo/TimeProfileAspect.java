package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeProfileAspect {
		TimeProfileAspect(){
			System.out.println("+++++++++");
		}
		@Around("execution(* com.example.demo.service.NoteService.getOrder*())" )
		public Iterable calculateTime(ProceedingJoinPoint pjp) throws Throwable {
			long start = System.currentTimeMillis();
			Iterable result=(Iterable) pjp.proceed();
			long end = System.currentTimeMillis();
			System.out.println("total time"+(end-start));
			return result;
		}

//		@Before("execution(*    com.example.demo.service.NoteService.*())")
		@Before("execution(*    com.example.demo.service.NoteService.addOrd*())")
		public void logger1() { //advice
			System.out.println("adviced=========");
		}
}
