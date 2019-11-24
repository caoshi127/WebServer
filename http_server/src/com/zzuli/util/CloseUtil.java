package com.zzuli.util;
/**
 * 关闭IO流, Socket资源工具包
 * 
 * @author: hejjon
 * @date 2019年3月1日 下午5:41:54
 *
 */

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUtil {

	/**
	 * 使用泛型方法实现关闭IO流
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

} // 类CloseUtil结束
