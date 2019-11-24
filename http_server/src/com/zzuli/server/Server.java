package com.zzuli.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.zzuli.util.CloseUtil;

/**
 * 
 * ��д������--����
 * 
 * @author: hejjon
 * @date 2019��2��28�� ����7:19:33
 *
 */
public class Server {

	private ServerSocket server;

	//public static final String CRLF = "\r\n"; // ����
	public static final String BLANK = " "; // �ո�

	private boolean isShutDown = false;		// �������Ƿ�ر�
	
	public static void main(String[] args) {

		Server server = new Server();
		server.start();
	}

	/**
	 * ��������
	 */
	public void start() {
		start(8888);
	}
	
	/**
	 * ָ���˿ڵ���������
	 */
	public void start(int port) {

		System.out.println("����������!");
		try {
			server = new ServerSocket(port);
			this.receive(); // ��������
		} catch (IOException e) {
			//e.printStackTrace();
			stop();
		}
	}

	/**
	 * ���տͻ��˵���Ϣ
	 */
	private void receive() {
		try {
			while (!isShutDown) {
				new Thread(new Dispatcher(server.accept())).start();		// �����߳�
			}

		} catch (IOException e) {
			// e.printStackTrace();
			stop();
		}

	}	// ����receive()����
	
	/**
	 * �رշ�����
	 */
	public void stop() {
		isShutDown = true;
		CloseUtil.closeSocket(server);
	}
}	// ��Server����


