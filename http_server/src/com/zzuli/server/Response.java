package com.zzuli.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import com.zzuli.util.CloseUtil;

/**
 * 封装响应信息
 * 
 * @author: hejjon
 * @date 2019年3月1日 下午4:41:06
 *
 */
public class Response {

	// 两个常量
	public static final String CRLF = "\r\n"; // 换行
	public static final String BLANK = " "; // 空格

	// 流
	private BufferedWriter bw;

	// 存储头信息
	private StringBuilder headInfo;

	// 正文
	private StringBuilder content;

	// 存储正文长度
	private int len = 0;

	public Response() {
		headInfo = new StringBuilder();
		content = new StringBuilder();
		len = 0;
	}

	public Response(Socket client) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Response(OutputStream os) {
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}

	/**
	 * 构建正文
	 */
	public Response print(String info) {
		content.append(info);
		len += info.getBytes().length;
		return this;
	}

	/**
	 * 构建正文 + 回车
	 */
	public Response println(String info) {
		content.append(info).append(CRLF);
		len += (info + CRLF).getBytes().length;
		return this;
	}

	/**
	 * 构建响应头
	 * 
	 * @param code
	 *            应答码
	 */
	private void createHeadInfo(int code) {
		// 1)HTTP协议版本, 状态代码, 描述
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
		case 200:
			headInfo.append("OK");
			break;
		case 404:
			headInfo.append("NOT FOUND");
			break;
		case 500:
			headInfo.append("SERVER ERROR");
			break;
		}
		headInfo.append(CRLF);
		// 2) 响应头(Response Head)
		headInfo.append("Server:zzuli Server/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF); // 日期
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		// 正文长度, 字节长度
		headInfo.append("Content-length:").append(len).append(CRLF);
		headInfo.append(CRLF);
	}

	void pushToClient(int code) throws IOException {
		if (null == headInfo) {
			code = 500;
		}
		createHeadInfo(code);
		// 头信息+分隔符
		bw.append(headInfo.toString());
		// 正文
		bw.append(content.toString());
		bw.flush();

	}

	public void close() {
		CloseUtil.closeIO(bw);
	}

}
