package com.zzuli.myserver.demo1;
/**
 *	手写服务httpserver.	http://localhost:8888	在浏览器地址栏输入
 *	创建服务器并启动.
 * @author: hejjon
 * @date 2019年2月14日 下午7:10:42
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket server;
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();
	}
	
	/**
	 * 启动服务器
	 */
	public void start() {

		try {
			server = new ServerSocket(8888);
			System.out.println("服务器启动.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.receive();
	}
	
	/**
	 * 接收客户端信息
	 */
	private void receive() {
		try {
			Socket client = server.accept();
			String msg = null;
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			while (null != (msg = br.readLine())) {
				sb.append(msg);
				sb.append("\r\n");
				if (msg == null) {
					break;
				}
			}
			
			String requestinfo = sb.toString().trim();		// 去空格
			
			System.out.println(requestinfo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}





































