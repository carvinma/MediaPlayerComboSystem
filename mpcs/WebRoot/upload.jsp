<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SSH2架构原型：登录成功</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="${pageContext.request.contextPath}/css/bootstrap.css"/>
</head>
<body>

	欢迎您：${sessionScope.USERSESSION.user.userName }
	
	<form action="clientManager.do" method="post">
		<textarea rows="10" cols="50" name="data"></textarea>
		<p><button>提交</button></p>
	</form>
	
</body>
</html>
