package com.lhf.dajiuye.web.app.log;

import org.springframework.context.ApplicationEvent;

public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {
		super(source);
	}

}
