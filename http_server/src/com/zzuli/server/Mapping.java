package com.zzuli.server;

import java.util.ArrayList;
import java.util.List;

/**
 * mappingӳ��), �ֶ�urlattern(url)��name��ӦEntity���name��clz(servlet���·����Ϣ)
 * @author: hejjon
 * @date 2019��3��4�� ����4:32:01
 *
 */
public class Mapping {
	private String name;
	private List<String> urlPattern;		// ͬһ��ҳ���url�����ж��
	
	/*
	 * ����http://localhost:8888/log��http://localhost:8888/g����ǰ��ע��ҳ��.
	 */

	public Mapping() {
		urlPattern = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}
}	// ��Mapping����




















