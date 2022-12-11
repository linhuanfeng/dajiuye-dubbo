package com.lhf.dajiuye.web.app.log;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Slf4j
@Component
public class SysLogAspect {

	@Around("@annotation(sysLogAnnotation)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point, SysLogAnnotation sysLogAnnotation) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		long startTime = System.currentTimeMillis();
		SysLog sysLog = new SysLog();
		sysLog.setMethod(strMethodName);
		sysLog.setParameter(point.getArgs());
		sysLog.setOperationUser(SecurityContextHolder.getContext().getAuthentication().getName());
		sysLog.setStartTime(new Date());
		sysLog.setClaz(point.getTarget().getClass().toString());

		Object obj;

		try {
			obj = point.proceed(point.getArgs());
		}
		catch (Exception e) {
			sysLog.setError(e.getMessage());
			throw e;
		}
		finally {
			Long endTime = System.currentTimeMillis();
			sysLog.setTotalTime(endTime-startTime);
			SpringContextHolder.publishEvent(new SysLogEvent(sysLog));
		}

		return obj;
	}
}
