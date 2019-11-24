package com.zzuli.server;

import java.util.HashMap;
import java.util.Map;

/**
 * servlet上下文, 实际上就是一个容器
 * 
 * @author: hejjon
 * @date 2019年3月3日 下午1:38:56
 *
 */
public class ServletContext {
	
	// 为每一个Servlet取一个别名.
	// 例如: login--->com.zzuli.myserver.demo03LoginServlet
	private Map<String, String> servlet;
	
	// url--->Login
	// 例如: /log --> login
	// 		/login --> login
	private Map<String, String> mapping;

	public ServletContext() {
		servlet = new HashMap<>();
		mapping = new HashMap<>();
	}
	
	
	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

}
