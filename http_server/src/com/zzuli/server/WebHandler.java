package com.zzuli.server;

import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 
 * sax����ʵ��
 * @author: hejjon
 * @date 2019��3��4�� ����4:27:22
 *
 *Ҫ��:
 *sax����xml�ļ�   ע��Ҫ�̳�Defaulthandle�� ������5������ startdocument() ��
 *startelentment()�õ�Ԫ������ ��charcter()�õ�valuesֵ��endelement()��enddocument()  
 *����ʽһ��һ�н�����
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
		// �ĵ�������ʼ
		entityList = new ArrayList<>();
		mappingList = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		// ��ʼԪ��
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
	}	// ����startElement()����

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// ��������
		if (null != beginTag) {
			String str = new String(ch, start, length);		// ��ʱ�洢�����ǩԪ��
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
	}	// ����characters()����
	

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// ����Ԫ��
		if (null != qName) {
			if ("servlet".equals(qName)) {
				entityList.add(entity);
			} else if ("servlet-mapping".equals(qName)){
				isMap = true;
				mappingList.add(mapping);
			}
		}
		beginTag = null;			// �����beginTagһ��Ҫ�ÿ�, ��Ȼ������null��Ͳ����¼���������
	}
	
	@Override
	public void endDocument() throws SAXException {
		// �ĵ���������
		
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
	
}	// ��WebHandler����
