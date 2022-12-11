package com.lhf.dajiuye.web.app.log;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@UtilityClass
public class SysLogUtils {

	public SysLog getSysLog() {
		//获取请求url,ip,httpMethod
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLog sysLog = new SysLog();
		//sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
		//sysLog.setUpdateBy(Objects.requireNonNull(getUsername()));
//		sysLog.setType(LogTypeEnum.NORMAL.getType());
//		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
//		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
//		sysLog.setMethod(request.getMethod());
//		sysLog.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
//		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		//sysLog.setServiceId(getClientId(request));
		return sysLog;
	}
}
