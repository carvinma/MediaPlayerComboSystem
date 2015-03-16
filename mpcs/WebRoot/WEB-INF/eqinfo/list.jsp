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
<body class="eqinfo">
	<jsp:include page="/header.jsp"/>
	<jsp:include page="/menu.jsp"/>
	
	<div class="content">
	
			<div class="header"><h1 class="page-title">机台讯息</h1></div>
		
			<ul class="breadcrumb">
				<li>实时状态 </li>
			</ul>
		
			<div class="container-fluid">
				<div class="row-fluid">
		
					<!-- <div class="btn-toolbar">
						<button class="btn btn-primary">
							<i class="icon-plus"></i> New User
						</button>
						<button class="btn">Import</button>
						<button class="btn">Export</button>
						<div class="btn-group"></div>
					</div> -->
					<form id="mainForm" class="form-inline search-form" action="<%=request.getContextPath()%>/eqinfo_list.do" method="post">
						<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}" /> 
						<label for="param_area">地区</label>
					    <select id="param_area" name="param['area']">
							<option value=""></option>
							<option value="上海">上海</option>
							<option value="北京">北京</option>
							<option value="广州">广州</option>
							<option value="成都">成都</option>
						</select>
					    <label for="param_sn">SN</label>
					    <input id="param_sn" type="text" name="param['sn']" value="${param['sn']}"/> 
						<button type="submit" class="btn btn-primary">查询</button>
					</form>
					<div class="btn-toolbar">
						<button class="btn">停止</button>
						<button class="btn">启动</button>
					</div>
						<div class="well">
							<table class="table">
								<thead>
									<tr>
										<th><input id="checkAll" type="checkbox" /> </th>
										<th>NO</th>
										<th>SN</th>
										<th>地区</th>
										<th>组别</th>
										<th>状态</th>
										<th style="width: 60px;">操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="page.dataList" >
									<tr>
										<td><input name="checkBoxId"  type="checkbox" value="${id}" /></td>
										<td>${index+1}</td>
										<td>${sn}</td>
										<td>${area}</td>
										<td>${group}</td>
										<td><s:if test="state==1">在线</s:if><s:else>离线</s:else></td>
										<td>
											<a href="<%=request.getContextPath()%>/eqmedia_list.do?param['eqId']=${id}"><i class="icon-pencil">查看</i></a> 
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
		
				</div>
			</div>
	</div>
</body>
</html>