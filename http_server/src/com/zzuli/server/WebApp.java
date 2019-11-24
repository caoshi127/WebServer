package com.zzuli.server;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.zzuli.servlet.Servlet;
/**
 * 
 * @author: hejjon
 * @date 2019年3月4日 下午8:06:13
 *
 */
public class WebApp {
	private static ServletContext context;
	
	static { 
		
		try {
			// 获取解析工厂
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 获取解析器
			SAXParser sax = factory.newSAXParser();
			// 指定xml+处理器
			WebHandler web = new WebHandler();
			sax.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("WEB_INFO/web.xml"), web);
			context = new ServletContext();
			// 获取servlet和mapping
			Map<String, String> servlet = context.getServlet();
			Map<String, String> mapping = context.getMapping();
			// 将List里的元素放入Map
			for (Entity entity : web.getEntityList()) {
				servlet.put(entity.getName(), entity.getClz());
			}
			
			for (Mapping mapp : web.getMappingList()) {
				List<String> urls = mapp.getUrlPattern();
				for (String url : urls) {		// 遍历url list 将所有的url放入Map, 作为键
					mapping.put(url, mapp.getName());
				}
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	/**
	 * 通过url获取Servlet
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
			// 根据字符串(完整路径)创建对象.
			String path = context.getServlet().get(context.getMapping().get(url));
			return (Servlet)Class.forName(path).newInstance();
		}
	}
}	// 类WebApp结束











