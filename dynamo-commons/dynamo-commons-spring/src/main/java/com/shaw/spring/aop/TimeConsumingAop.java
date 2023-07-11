package com.shaw.spring.aop;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;
import com.shaw.commons.annotation.TimeConsuming;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 方法耗时的{@link TimeConsuming}切面
 *
 * @author shaw
 * @date 2023/7/11
 */
@Aspect
@Slf4j
@Component
public class TimeConsumingAop {

	@Around("@annotation(timeConsuming)")
	public Object doAround(ProceedingJoinPoint pjp, TimeConsuming timeConsuming) throws Throwable {
		// 创建的时候就开始计时
		Stopwatch stopwatch = Stopwatch.createStarted();
		Object obj = pjp.proceed();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
		// 停止计时，然后计算时长.单位为毫秒.
		long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
		log.info("方法 [{}] 花费时间：{}ms", methodName, (elapsed));
		return obj;
	}

}
