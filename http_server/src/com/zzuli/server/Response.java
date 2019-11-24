package com.zzuli.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import com.zzuli.util.CloseUtil;

/**
 * ��װ��Ӧ��Ϣ
 * 
 * @author: hejjon
 * @date 2019��3��1�� ����4:41:06
 *
 */
public class Response {

	// ��������
	public static final String CRLF = "\r\n"; // ����
	public static final String BLANK = " "; // �ո�

	// ��
	private BufferedWriter bw;

	// �洢ͷ��Ϣ
	private StringBuilder headInfo;

	// ����
	private StringBuilder content;

	// �洢���ĳ���
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
	 * ��������
	 */
	public Response print(String info) {
		content.append(info);
		len += info.getBytes().length;
		return this;
	}

	/**
	 * �������� + �س�
	 */
	public Response println(String info) {
		content.append(info).append(CRLF);
		len += (info + CRLF).getBytes().length;
		return this;
	}

	/**
	 * ������Ӧͷ
	 * 
	 * @param code
	 *            Ӧ����
	 */
	private void createHeadInfo(int code) {
		// 1)HTTPЭ��汾, ״̬����, ����
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
		// 2) ��Ӧͷ(Response Head)
		headInfo.append("Server:zzuli Server/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF); // ����
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		// ���ĳ���, �ֽڳ���
		headInfo.append("Content-length:").append(len).append(CRLF);
		headInfo.append(CRLF);
	}

	void pushToClient(int code) throws IOException {
		if (null == headInfo) {
			code = 500;
		}
		createHeadInfo(code);
		// ͷ��Ϣ+�ָ���
		bw.append(headInfo.toString());
		// ����
		bw.append(content.toString());
		bw.flush();

	}

	public void close() {
		CloseUtil.closeIO(bw);
	}

}
