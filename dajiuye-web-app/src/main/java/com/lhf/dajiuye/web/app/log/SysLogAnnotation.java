package com.lhf.dajiuye.web.app.log;

import java.lang.annotation.*;

/**
 * @date 2019/2/1 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {

	/**
	 * 描述
	 * @return {String}
	 */
	String value() default "";
}
