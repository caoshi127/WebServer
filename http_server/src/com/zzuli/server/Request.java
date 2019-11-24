package com.zzuli.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * ��װ����(request)
 * 
 * @author: hejjon
 * @date 2019��3��1�� ����8:44:38
 *
 */
public class Request {
	// ����ʽ get��post
	private String method;
	// ������Դ /index.html
	private String url;
	// �������
	private Map<String, List<String>> parameterMapValues;

	// �ڲ�
	public static final String CRLF = "\r\n";
	InputStream is;
	private String requestInfo;

	public Request() {
		method = "";
		url = "";
		parameterMapValues = new HashMap<String, List<String>>();
		requestInfo = "";
	}

	public Request(InputStream is) {
		this();
		this.is = is;

		try {
			byte[] data = new byte[20480];
			int len = is.read(data);
			requestInfo = new String(data, 0, len);
		} catch (IOException e) {
			return;
		}

		// ����ͷ��Ϣ
		parseRequestInfo();
	}

	/**
	 * ����������Ϣ
	 */
	private void parseRequestInfo() {
		if (null == requestInfo || (requestInfo = requestInfo.trim()).equals("")) {
			return;
		}

		/**
		 * =================================== ����Ϣ�����зֽ��: ����ʽ, ����·��(url),
		 * �������(get��ʽ���ܴ���) ��: GET /index.html?uname=wegw&pwd=wehwe HTTP/1.1
		 * 
		 * ���Ϊpost����ʽ, ����������������������. ===================================
		 */

		String paramString = ""; // �����������

		// 1.��ȡ����ʽ
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = requestInfo.indexOf("/"); // / ��λ��
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim(); // ��url���ַ���
		this.method = firstLine.substring(0, idx).trim(); // ��ȡ������ʽ

		// 2.��ȡ����·��(url)
		if (this.method.equalsIgnoreCase("post")) { // post����ʽ, ���Դ�Сд
			this.url = urlStr;
		} else if (this.method.equalsIgnoreCase("get")) { // get����ʽ
			if (urlStr.contains("?")) { // ���ڲ���
				String[] urlStrArray = urlStr.split("\\?"); // ��"?"���ַ���urlStr�ָ�������ַ����γ��ַ�������.

				this.url = urlStrArray[0];
				paramString = urlStrArray[1];

			} else {
				this.url = urlStr;
			}
		}

		// �����������װ��Map��
		parseParams(paramString);

	} // ����parseRequestInfo()����

	/**
	 * �����������װ��Map��
	 * 
	 * @param paramString
	 *            uname=null&pwd=213&fav=0&fav=1&fav=2
	 *            
	 *            �������������ִ�е�, ֻ�費��debug
	 */
	private void parseParams(String paramString) {
		// �ָ� ���ַ���ת������
		StringTokenizer token = new StringTokenizer(paramString, "&");
		while (token.hasMoreTokens()) {
			String keyValue = token.nextToken();
			String[] keyValues = keyValue.split("=");
			
			if (keyValues.length == 1) {
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1] = null;
			}

			String key = keyValues[0].trim();
			String value = null == keyValues[1] ? null : decode(keyValues[1].trim(), "utf-8");
			
			// "װ��Map"��
			if (!parameterMapValues.containsKey(key)) {
				parameterMapValues.put(key, new ArrayList<String>());
			}
			
			List<String> values = parameterMapValues.get(key);
			values.add(value);
		}
	}
	
	

	/**
	 * ���������������
	 * @param value
	 * @param code
	 * @return
	 */
	private String decode(String value, String code) {
		try {
			return java.net.URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * ��name��ȡvalue, ���value�����ж��
	 * ����ҳ���name����, ��ȡ��Ӧ�Ķ��ֵ(value). ������name=fav��ȡ��Ӧ�Ķ��value
	 * @param name		ǰ̨��name����
	 * @return			name��Ӧ��value
	 */
	public String[] getParameterValues(String name) {
		List<String> values = null;
		if (null == (values = parameterMapValues.get(name))) {
			return null;
		} else {
			return values.toArray(new String[0]);		// ��һ���ַ���List����ת�����ַ�������
		}
	}
	
	/**
	 * ����ҳ���name��ȡ����ֵ
	 * @param name
	 * @return
	 */
	public String getParameterValue(String name) {
		String[] values = getParameterValues(name);
		if (null == values) {
			return null;
		} else {
			return values[0];
		}
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
} // ��Request����






















