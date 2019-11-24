package com.zzuli.server;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.zzuli.servlet.Servlet;
/**
 * 
 * @author: hejjon
 * @date 2019��3��4�� ����8:06:13
 *
 */
public class WebApp {
	private static ServletContext context;
	
	static { 
		
		try {
			// ��ȡ��������
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// ��ȡ������
			SAXParser sax = factory.newSAXParser();
			// ָ��xml+������
			WebHandler web = new WebHandler();
			sax.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("WEB_INFO/web.xml"), web);
			context = new ServletContext();
			// ��ȡservlet��mapping
			Map<String, String> servlet = context.getServlet();
			Map<String, String> mapping = context.getMapping();
			// ��List���Ԫ�ط���Map
			for (Entity entity : web.getEntityList()) {
				servlet.put(entity.getName(), entity.getClz());
			}
			
			for (Mapping mapp : web.getMappingList()) {
				List<String> urls = mapp.getUrlPattern();
				for (String url : urls) {		// ����url list �����е�url����Map, ��Ϊ��
					mapping.put(url, mapp.getName());
				}
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	/**
	 * ͨ��url��ȡServlet
	 * @param url
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Servlet getServlet(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (null == url || (url = url.trim()).equals("")) {
			return null;
		} else {
			//return context.getServlet().get(context.getMapping().get(url));
			// �����ַ���(����·��)��������.
			String path = context.getServlet().get(context.getMapping().get(url));
			return (Servlet)Class.forName(path).newInstance();
		}
	}
}	// ��WebApp����











