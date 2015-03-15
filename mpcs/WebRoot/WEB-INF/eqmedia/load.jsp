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
<script type="text/javascript">
$(function(){
	$('#cload').val('${eqmedia.canDownLoad}');
	$('#cplya').val('${eqmedia.canPlay}');
});
</script>
</head>
<body class="media">
	<jsp:include page="/header.jsp"/>
	<jsp:include page="/menu.jsp"/>
	
	<div class="content">
	
			<div class="header"><h1 class="page-title">档案管理</h1></div>
		
			<ul class="breadcrumb">
				<li><a href="media_list.do">机台档案</a><span class="divider">/</span></li>
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
										<th width="100px;">机台</th><td><input name="eqmedia.eqId"/></td>
									</tr>
								  	<tr>
										<th>多媒体</th><td><input name="eqmedia.mediaId"/></td>
									</tr>
									<tr>
										<th>可以下载</th>
										<td>
											<select id="cload" name="eqmedia.canDownLoad">
												<option value="1">是</option>
												<option value="0">否</option>
											</select> 
										</td>
									</tr> 
									<tr>
										<th>可以播放</th>
										<td>
											<select id="cplay" name="eqmedia.canPlay">
												<option value="1">是</option>
												<option value="0">否</option>
											</select> 
										</td>
									</tr> 
									<s:if test="eqmedia.id!=null">
									<tr>
										<th>更新时间</th><td><s:date name="eqmedia.updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									</tr> 
									</s:if>
									 
								</tbody>
							</table>
						</div>
						 
					</form>
		
				</div>
			</div>
	</div>
</body>
</html>