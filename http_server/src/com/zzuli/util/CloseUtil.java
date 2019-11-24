package com.zzuli.util;
/**
 * �ر�IO��, Socket��Դ���߰�
 * 
 * @author: hejjon
 * @date 2019��3��1�� ����5:41:54
 *
 */

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUtil {

	/**
	 * ʹ�÷��ͷ���ʵ�ֹر�IO��
	 * 
	 * @param io
	 */
	@SafeVarargs
	public static <T extends Closeable> void closeIO(T... io) {
		for (Closeable temp : io) {
			if (null != temp) {
				try {
					temp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void closeSocket(ServerSocket socket) {
		if (null != socket) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeSocket(Socket socket) {
		if (null != socket) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static  void closeSocket(DatagramSocket socket){
		 if (null != socket) {
			 socket.close();
		 }
	 }

} // ��CloseUtil����
