package com.zzuli.myserver.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
ServerSocket server;
	
	public static void main(String[] args) throws IOException {
		Server2 server = new Server2();
		server.start();
	}
	
	/**
	 * ����������
	 */
	public void start() {

		try {
			server = new ServerSocket(8888);
			System.out.println("����������.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.receive();
	}
	
	/**
	 * ���տͻ�����Ϣ
	 */
	private void receive() {
		try {
			Socket client = server.accept();
			
			byte[] b = new byte[1024];

			InputStream is = client.getInputStream();
			int len = is.read(b);
			
			
			String requestinfo = new String(b, 0, len);
			
			System.out.println(requestinfo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
