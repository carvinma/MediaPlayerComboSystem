<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${errorMsg }</title>
</head>

<body>
	<center>
		<div style="padding-top:100px;padding-bottom: 100px;">
			<span style="font-size: 16px; font-weight:bold;">${errorMsg }</span><a style="color:#378BD1;" href="<%=request.getContextPath()%>/index.action">返回首页</a>
		</div>
	</center>
</body>
</html>
