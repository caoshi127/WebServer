package com.zzuli.server;

/**
 * Entity(ʵ��), �ֶ�anme��clz�ֱ�ָservletҳ�������(�����¼ҳ�������login)��servlet���·����Ϣ
 * 
 * @author: hejjon
 * @date 2019��3��4�� ����4:27:13
 *
 */
public class Entity {

	private String name; // servlet-name
	private String clz; // servlet-class			// ���·����Ϣ, Ϊ�����ṩ����

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClz() {
		return clz;
	}

	public void setClz(String clz) {
		this.clz = clz;
	}
}	// ��Entity����
