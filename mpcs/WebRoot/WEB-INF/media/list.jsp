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
				<li>档案信息</li>
			</ul>
		
			<div class="container-fluid">
				<div class="row-fluid">
		
					<div class="btn-toolbar">
						<a class="btn btn-primary" href="media_load.do">
							<i class="icon-plus"></i> 新增
						</a>
					</div>
					<form id="mainForm" action="<%=request.getContextPath()%>/media_list.do" method="post">
						<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}" /> 
						<div class="well">
							<table class="table">
								<thead>
									<tr>
										<th>NO</th>
										<th>名称</th>
										<th>URL</th>
										<th>地区</th>
										<th>组别</th>
										<th>类型</th>
										<th>大小</th>
										<th>更新时间</th>
										<th style="width: 26px;"></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="page.dataList" >
									<tr>
										<td>${index+1}</td>
										<td>${mediaName}</td>
										<td>${url}</td>
										<td>${mediaArea}</td>
										<td>${mediaGroup}</td>
										<td>${mediaType}</td>
										<td>${mediaSize}</td>
										<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<a href="<%=request.getContextPath()%>/media_load.do?id=${id}"><i class="icon-pencil"></i></a> 
										</td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<div class="pagination">
							<ul>
								<li><a>当前${page.pageNo}页 / 共${page.totalPages}页</a></li>
								<li><a href="javascript:jumpPage(1);">首页</a></li>
								<li><a href="javascript:jumpPage(${page.prePage});">上一页</a></li>
								<li><a href="javascript:jumpPage(${page.nextPage});">下一页</a></li>
								<li><a href="javascript:jumpPage(${page.totalPages});">末页</a></li>
								 
							</ul>
						</div>
					</form>
		
				</div>
			</div>
	</div>
</body>
</html>