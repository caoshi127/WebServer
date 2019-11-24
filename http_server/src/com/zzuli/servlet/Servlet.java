package com.zzuli.servlet;

import com.zzuli.server.Request;
import com.zzuli.server.Response;

/**
 * Servlet�����һ������
 * @author: hejjon
 * @date 2019��3��3�� ����9:51:07
 *
 */
public abstract class Servlet {
	public void service(Request req, Response rep) throws Exception {
		this.doGet(req, rep);
		this.doPost(req, rep);
	}
	// ֻ�����������
	protected abstract void doGet(Request req, Response rep) throws Exception;
	protected abstract void doPost(Request req, Response rep) throws Exception;
}
