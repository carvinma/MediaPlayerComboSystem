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
<body class="sys">
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
					<form id="mainForm" action="<%=request.getContextPath()%>/category_save.do" method="post">
						<input type="hidden" name="category.id" value="${category.id}" /> 
						<input type="hidden" name="category.superId" value="${category.superId}" /> 
						<div class="btn-toolbar">
							<button class="btn btn-primary">保存</button>
							<a class="btn btn-primary" href="category_list.do?param['super']=category.superId">返回</a>
						</div>
						<div class="well">
							<table class="table form">
									<s:if test="category.superCategory!=null">
									<tr><th>所属</th><td>${category.superCategory.categoryName}</td></tr>
									</s:if>
									<tr>
										<th width="100px;">code</th><td><input name="category.categoryCode" value="${category.categoryCode}"/></td>
									</tr>
								  	<tr>
										<th>名称</th><td><input name="category.categoryName" value="${category.categoryName}"/></td>
									</tr>
									<tr>
										<th>值</th><td><input name="category.categoryValue" value="${category.categoryValue}"/></td>
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