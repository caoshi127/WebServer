�ܽ�:

A:	�����������������Ϣ����:
	�������������(Request)��������, ������������Ӧ(Response).
	
B:	���Ҫ:
	LoginWeb.java			ҵ�����, չ�����û���ǰ������(�ص�).
	WebHandler.java			sax����ʵ��(�̶���·, �˽�)
	WebApp.java				����������������ļ�, �ṩgetServlet����, ��ͨ��url��ȡ��Ӧ��servlet.		
	ServletContext.java		�ṩ����, �������servlet��mapping
	Request.java			��װ����
	Response.java			��װ��Ӧ, ��Ҫ��������Ӧ��http������Ϣ
	CloseUtil.java			���߰�, �ṩ�ر�IO��, socket��Դ�ķ���
	Servlet.java			������, �ṩHTTP���󷽷�: GET������POST����
	Mapping	.java			mappingӳ��), �ֶ�urlattern(url)��name��ӦEntity���name��clz(servlet���·����Ϣ)
	Entity.java				Entity(ʵ��), �ֶ�anme��clz�ֱ�ָservletҳ�������(�����¼ҳ�������login)��servlet���·����Ϣ
	Dispatcher.java			�߳���, ����������, �и���������Ӧ��newһ���˶���.
	Server.java				��������������
	
	web.xml					�����ļ�, �ṩ url-->name-->servlet-class
