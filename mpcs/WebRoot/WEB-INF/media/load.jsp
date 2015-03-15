<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="stylesheets/elements.css">
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="lib/table.js" type="text/javascript"></script>
</head>
<body class="media">
	<jsp:include page="/header.jsp"/>
	<jsp:include page="/menu.jsp"/>
	
	<div class="content">
	
			<div class="header"><h1 class="page-title">档案管理</h1></div>
		
			<ul class="breadcrumb">
				<li><a href="media_list.do">档案信息</a><span class="divider">/</span></li>
				<li class="active">详细</li>
				 
			</ul>
		
			<div class="container-fluid">
				<div class="row-fluid">
					<form id="mainForm" action="<%=request.getContextPath()%>/media_save.do" method="post">
						<input type="hidden" name="media.id" value="${media.id}" /> 
						<div class="btn-toolbar">
							<button class="btn btn-primary">保存</button>
							<a class="btn btn-primary" href="media_list.do">返回</a>
						</div>
						<div class="well">
							<table class="table form">
									<tr>
										<th width="100px;">名称</th><td><input name="media.mediaName"/></td>
									</tr>
								  	<tr>
										<th>URL</th><td><input name="media.url"/></td>
									</tr>
									<tr>
										<th>地区</th><td><input name="media.mediaArea"/></td>
									</tr> 
									<tr>
										<th>地区组别</th><td><input name="media.mediaName"/></td>
									</tr> 
									<s:if test="media.id!=null">
									<tr>
										<th>类型</th><td>${media.mediaType}</td>
									</tr> 
									<tr>
										<th>大小</th><td>${media.mediaSize}</td>
									</tr>
									
									<tr>
										<th>更新时间</th><td><s:date name="media.updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									</tr> 
									</s:if>
									<tr>
										<th>描述</th><td><textarea name="media.description">${media.description}</textarea></td>
									</tr> 
								</tbody>
							</table>
						</div>
						 
					</form>
		
				</div>
			</div>
	</div>
</body>
</html>