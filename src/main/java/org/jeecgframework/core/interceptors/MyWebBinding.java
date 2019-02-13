package org.jeecgframework.core.interceptors;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class MyWebBinding {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 1. 使用spring自带的CustomDateEditor
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// binder.registerCustomEditor(Date.class, new
		// CustomDateEditor(dateFormat, true));
		//2. 自定义的PropertyEditorSupport
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}

}
