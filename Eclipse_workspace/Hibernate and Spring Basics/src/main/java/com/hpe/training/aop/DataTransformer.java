package com.hpe.training.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hpe.training.dao.DaoException;

@Component
@Aspect
public class DataTransformer {
	
	// convert any exception into a DaoException
	@AfterThrowing(value="execution(* com.hpe..ProductDao.*(..))", throwing="ex")
	public void exceptionConverter(Throwable ex) throws DaoException {
		// System.out.println("converting exception of type " + ex.getClass() + " into a DaoException...");
		throw new DaoException(ex);
	}

	// ProceedingJoinPoint is only supported for around advice
	@Around("execution(* com.hpe..ProductDao.*(java.lang.Double, java.lang.Double))")
	public Object swapArgs(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Double d1 = (Double) args[0], d2 = (Double) args[1];
		if (d1 > d2) {
			args = new Object[] { d2, d1 };
		}
		// System.out.println("before proceeding to the target method...");
		Object obj = pjp.proceed(args);
		// System.out.println("after proceeding to the target method...");
		return obj;
	}

}
