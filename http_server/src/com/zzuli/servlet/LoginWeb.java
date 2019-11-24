package com.zzuli.servlet;

import com.zzuli.server.Request;
import com.zzuli.server.Response;
/**
 * 业务代码
 * @author: hejjon
 * @date 2019年3月4日 下午7:52:53
 *
 */
public class LoginWeb extends Servlet {

	@Override
	protected void doGet(Request req, Response rep) throws Exception {
		rep.println("登录成功....");
	}

	@Override
	protected void doPost(Request req, Response rep) throws Exception {
		
	}
	
}	// 类LoginWeb结束
