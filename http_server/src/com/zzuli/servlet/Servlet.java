package com.zzuli.servlet;

import com.zzuli.server.Request;
import com.zzuli.server.Response;

/**
 * Servlet抽象成一个父类
 * @author: hejjon
 * @date 2019年3月3日 上午9:51:07
 *
 */
public abstract class Servlet {
	public void service(Request req, Response rep) throws Exception {
		this.doGet(req, rep);
		this.doPost(req, rep);
	}
	// 只允许子类调用
	protected abstract void doGet(Request req, Response rep) throws Exception;
	protected abstract void doPost(Request req, Response rep) throws Exception;
}
