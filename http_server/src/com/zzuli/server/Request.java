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
 * 封装请求(request)
 * 
 * @author: hejjon
 * @date 2019年3月1日 下午8:44:38
 *
 */
public class Request {
	// 请求方式 get和post
	private String method;
	// 请求资源 /index.html
	private String url;
	// 请求参数
	private Map<String, List<String>> parameterMapValues;

	// 内部
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

		// 分析头信息
		parseRequestInfo();
	}

	/**
	 * 分析请求信息
	 */
	private void parseRequestInfo() {
		if (null == requestInfo || (requestInfo = requestInfo.trim()).equals("")) {
			return;
		}

		/**
		 * =================================== 从信息的首行分解出: 请求方式, 请求路径(url),
		 * 请求参数(get方式可能存在) 如: GET /index.html?uname=wegw&pwd=wehwe HTTP/1.1
		 * 
		 * 如果为post请求方式, 请求参数可能在最后正文中. ===================================
		 */

		String paramString = ""; // 接收请求参数

		// 1.获取请求方式
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = requestInfo.indexOf("/"); // / 的位置
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim(); // 含url的字符串
		this.method = firstLine.substring(0, idx).trim(); // 获取到请求方式

		// 2.获取请求路径(url)
		if (this.method.equalsIgnoreCase("post")) { // post请求方式, 忽略大小写
			this.url = urlStr;
		} else if (this.method.equalsIgnoreCase("get")) { // get请求方式
			if (urlStr.contains("?")) { // 存在参数
				String[] urlStrArray = urlStr.split("\\?"); // 以"?"将字符串urlStr分割成两个字符串形成字符串数组.

				this.url = urlStrArray[0];
				paramString = urlStrArray[1];

			} else {
				this.url = urlStr;
			}
		}

		// 将请求参数封装到Map中
		parseParams(paramString);

	} // 方法parseRequestInfo()结束

	/**
	 * 将请求参数封装到Map中
	 * 
	 * @param paramString
	 *            uname=null&pwd=213&fav=0&fav=1&fav=2
	 *            
	 *            这个方法是怎样执行的, 只需不断debug
	 */
	private void parseParams(String paramString) {
		// 分割 将字符串转成数组
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
			
			// "装进Map"里
			if (!parameterMapValues.containsKey(key)) {
				parameterMapValues.put(key, new ArrayList<String>());
			}
			
			List<String> values = parameterMapValues.get(key);
			values.add(value);
		}
	}
	
	

	/**
	 * 解决中文乱码问题
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
	 * 由name获取value, 这个value可能有多个
	 * 根据页面的name属性, 获取响应的多个值(value). 例如由name=fav获取对应的多个value
	 * @param name		前台的name属性
	 * @return			name对应的value
	 */
	public String[] getParameterValues(String name) {
		List<String> values = null;
		if (null == (values = parameterMapValues.get(name))) {
			return null;
		} else {
			return values.toArray(new String[0]);		// 将一个字符串List集合转换成字符串数组
		}
	}
	
	/**
	 * 根据页面的name获取单个值
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
	
	
} // 类Request结束






















