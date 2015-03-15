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
</head>
<body class="one">
	<jsp:include page="/header.jsp"/>
	<jsp:include page="/menu.jsp"/>
	
	<div class="content">
	
			<div class="header"><h1 class="page-title">机台讯息</h1></div>
		
			<ul class="breadcrumb">
				<li><a href="eqinfo_list.do">实时状态</a><span class="divider">/</span></li>
				<li class="active">详细</li>
			</ul>
		
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="btn-toolbar">
						<button class="btn btn-primary"><i class="icon-plus"></i> 返回</button>
					</div>
					  
						<div class="well">
							<table class="table">
								<thead>
									<tr>
										<th>NO</th>
										<th>IP</th>
										<th>MAC</th>
										<th>手机号</th>
										<th>IMEI</th>
										<th>上传日期</th>
										<th>上传时间</th>
										<th>上传数据</th>
										<th>下载数据</th> 
									</tr>
								</thead>
								<tbody>
									<s:iterator value="page.dataList" >
									<tr>
										<td>${index+1}</td>
										<td>${IP}</td>
										<td>${MAC}</td>
										<td>${phone}</td>
										<td>${phone}</td>
										<td>${phone}</td>
										<td>${phone}</td>
										 
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