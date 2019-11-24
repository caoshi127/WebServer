package com.zzuli.myserver.demo1;
/**
 *	��д����httpserver.	http://localhost:8888	���������ַ������
 *	����������������.
 * @author: hejjon
 * @date 2019��2��14�� ����7:10:42
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
			
			String requestinfo = sb.toString().trim();		// ȥ�ո�
			
			System.out.println(requestinfo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}





































