package com.zzuli.server;

/**
 * Entity(实体), 字段anme和clz分别指servlet页面的名字(比如登录页面的名叫login)和servlet类的路径信息
 * 
 * @author: hejjon
 * @date 2019年3月4日 下午4:27:13
 *
 */
public class Entity {

	private String name; // servlet-name
	private String clz; // servlet-class			// 类的路径信息, 为反射提供便利

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClz() {
		return clz;
	}

	public void setClz(String clz) {
		this.clz = clz;
	}
}	// 类Entity结束
