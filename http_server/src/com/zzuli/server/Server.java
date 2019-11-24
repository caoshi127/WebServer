package com.zzuli.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.zzuli.util.CloseUtil;

/**
 * 
 * 手写服务器--裴新
 * 
 * @author: hejjon
 * @date 2019年2月28日 下午7:19:33
 *
 */
public class Server {

	private ServerSocket server;

	//public static final String CRLF = "\r\n"; // 换行
	public static final String BLANK = " "; // 空格

	private boolean isShutDown = false;		// 服务器是否关闭
	
	public static void main(String[] args) {

		Server server = new Server();
		server.start();
	}

	/**
	 * 启动方法
	 */
	public void start() {
		start(8888);
	}
	
	/**
	 * 指定端口的启动方法
	 */
	public void start(int port) {

		System.out.println("服务器启动!");
		try {
			server = new ServerSocket(port);
			this.receive(); // 接收数据
		} catch (IOException e) {
			//e.printStackTrace();
			stop();
		}
	}

	/**
	 * 接收客户端的信息
	 */
	private void receive() {
		try {
			while (!isShutDown) {
				new Thread(new Dispatcher(server.accept())).start();		// 启动线程
			}

		} catch (IOException e) {
			// e.printStackTrace();
			stop();
		}

	}	// 方法receive()结束
	
	/**
	 * 关闭服务器
	 */
	public void stop() {
		isShutDown = true;
		CloseUtil.closeSocket(server);
	}
}	// 类Server结束


