package com.zzuli.server;

import java.util.HashMap;
import java.util.Map;

/**
 * servlet������, ʵ���Ͼ���һ������
 * 
 * @author: hejjon
 * @date 2019��3��3�� ����1:38:56
 *
 */
public class ServletContext {
	
	// Ϊÿһ��Servletȡһ������.
	// ����: login--->com.zzuli.myserver.demo03LoginServlet
	private Map<String, String> servlet;
	
	// url--->Login
	// ����: /log --> login
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
