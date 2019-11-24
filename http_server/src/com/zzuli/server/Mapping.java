package com.zzuli.server;

import java.util.ArrayList;
import java.util.List;

/**
 * mapping映射), 字段urlattern(url)和name对应Entity里的name和clz(servlet类的路径信息)
 * @author: hejjon
 * @date 2019年3月4日 下午4:32:01
 *
 */
public class Mapping {
	private String name;
	private List<String> urlPattern;		// 同一个页面的url可能有多个
	
	/*
	 * 比如http://localhost:8888/log和http://localhost:8888/g都是前往注册页面.
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
}	// 类Mapping结束




















