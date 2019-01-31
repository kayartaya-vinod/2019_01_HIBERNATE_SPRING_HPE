package com.hpe.training.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Component
@Aspect
public class LoggerAspect {

	@Pointcut("execution( * com.hpe..ProductDao.get*(..) )")
	public void pc1() {
	}

	@Pointcut("execution( * com.hpe..ProductDao.count(..) )")
	public void pc2() {
	}

	// An advice is just an ordinary function, that may have some dependencies.
	// 4 types - Before, After, Around, and AfterThrowing
	// Advice also should specify a predicate - pointcut.
	// A pointcut expression narrows down the joinpoints (function of the target
	// bean)
	// @Before("execution( * com.hpe..ProductDao.get*(..) )")
	@Before("pc1() || pc2()")
	public void printInfo(JoinPoint jp) {
		System.out.println("-----------------------------------------");
		System.out.println("Method name    - " + jp.getSignature().getName());
		System.out.println("Arguments      - " + Arrays.toString(jp.getArgs()));
		System.out.println("Declaring type - " + jp.getSignature().getDeclaringTypeName());
		System.out.println("-----------------------------------------");
	}

}
