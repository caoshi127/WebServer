总结:

A:	服务器与浏览器的信息交互:
	浏览器发送请求(Request)到服务器, 服务器作出响应(Response).
	
B:	类概要:
	LoginWeb.java			业务代码, 展现在用户眼前的内容(重点).
	WebHandler.java			sax解析实现(固定套路, 了解)
	WebApp.java				处理解析到的配置文件, 提供getServlet方法, 可通过url获取相应的servlet.		
	ServletContext.java		提供容器, 用来存放servlet和mapping
	Request.java			封装请求
	Response.java			封装响应, 主要是生成响应和http错误信息
	CloseUtil.java			工具包, 提供关闭IO流, socket资源的方法
	Servlet.java			抽象父类, 提供HTTP请求方法: GET方法与POST方法
	Mapping	.java			mapping映射), 字段urlattern(url)和name对应Entity里的name和clz(servlet类的路径信息)
	Entity.java				Entity(实体), 字段anme和clz分别指servlet页面的名字(比如登录页面的名叫login)和servlet类的路径信息
	Dispatcher.java			线程类, 解决多个请求, 有个请求与响应就new一个此对象.
	Server.java				服务器工作流程
	
	web.xml					配置文件, 提供 url-->name-->servlet-class
