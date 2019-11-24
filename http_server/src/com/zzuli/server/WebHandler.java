package com.zzuli.server;

import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 
 * sax解析实现
 * @author: hejjon
 * @date 2019年3月4日 下午4:27:22
 *
 *要点:
 *sax解析xml文件   注：要继承Defaulthandle类 里面有5个方法 startdocument() 、
 *startelentment()得到元素名称 、charcter()得到values值、endelement()、enddocument()  
 *解析式一行一行解析，
 */
public class WebHandler extends DefaultHandler {

	private List<Entity> entityList;
	private List<Mapping> mappingList;
	private String beginTag;
	private boolean isMap;
	
	private Entity entity;
	private Mapping mapping;

	@Override
	public void startDocument() throws SAXException {
		// 文档解析开始
		entityList = new ArrayList<>();
		mappingList = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		// 开始元素
		if (null != qName) {
			beginTag = qName;
			if ("servlet".equals(qName)) {
				isMap = false;
				entity = new Entity();
			} else if("servlet-mapping".equals(qName)) {
				isMap = true;
				mapping = new Mapping();
			}
		}
	}	// 方法startElement()结束

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// 处理内容
		if (null != beginTag) {
			String str = new String(ch, start, length);		// 临时存储具体标签元素
			if (isMap) {
				if ("servlet-name".equals(beginTag)) {
					mapping.setName(str);
				} else if ("url-pattern".equals(beginTag)) {
					mapping.getUrlPattern().add(str);
				}
			} else {
				if ("servlet-name".equals(beginTag)) {
					entity.setName(str);
				} else if ("servlet-class".equals(beginTag)) {
					entity.setClz(str);
				}
			}
		}
	}	// 方法characters()结束
	

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// 结束元素
		if (null != qName) {
			if ("servlet".equals(qName)) {
				entityList.add(entity);
			} else if ("servlet-mapping".equals(qName)){
				isMap = true;
				mappingList.add(mapping);
			}
		}
		beginTag = null;			// 这里的beginTag一定要置空, 不然解析到null后就不往下继续解析了
	}
	
	@Override
	public void endDocument() throws SAXException {
		// 文档解析结束
		
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public List<Mapping> getMappingList() {
		return mappingList;
	}

	public void setMappingList(List<Mapping> mappingList) {
		this.mappingList = mappingList;
	}
	
}	// 类WebHandler结束
