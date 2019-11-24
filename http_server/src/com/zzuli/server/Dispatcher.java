package com.zzuli.server;

import java.io.IOException;
import java.net.Socket;

import com.zzuli.servlet.Servlet;
import com.zzuli.util.CloseUtil;

/**
 * 线程类, 解决多个请求
 * 有个请求与响应就一个此对象.
 * 
 * @author: hejjon
 * @date 2019年3月3日 上午10:03:37
 *
 */
public class Dispatcher implements Runnable {
	
	private Socket client;
	private Request req;
	private Response rep;
	private int code = 200;
	
	
	public Dispatcher(Socket client) {
		this.client = client;
		try {
			req = new Request(client.getInputStream());
			rep = new Response(client.getOutputStream());
		} catch (IOException e) {
			// e.printStackTrace();
			code = 500;
			return;
		}
	}

	/**
	 * 获取servlet并向浏览器响应
	 */
	@Override
	public void run() {
		
		try {
			Servlet serv = WebApp.getServlet(req.getUrl());
			
			if (null == serv) {
				this.code = 404;
			} else {
				serv.service(req, rep);
			}
			
			rep.pushToClient(code);
		} catch (Exception e) {
			this.code = 500;
		}
		try {
			rep.pushToClient(500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CloseUtil.closeSocket(client);
		
	}	// 方法run()结束 
	
}	// 类Dispatcher结束
