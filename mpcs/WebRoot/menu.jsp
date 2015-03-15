<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
</head>
<body>
<div class="sidebar-nav">
		<a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>机台讯息</a>
		<ul id="dashboard-menu" class="eqinfo nav nav-list collapse">
			<li><a href="eqinfo_list.do">实时状态</a></li>
		</ul>

		<a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>档案管理</a>
		<ul id="accounts-menu" class="media nav nav-list collapse">
			<li><a href="media_list.do">档案信息</a></li>
			<li><a href="eqmedia_list.do">机台档案</a></li>
		</ul>

		<a href="#legal-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-exclamation-sign"></i>系统管理</a>
		<ul id="legal-menu" class="three nav nav-list collapse">
			<li><a href="user_list.do">用户管理</a></li>
			<li><a href="role_list.do">权限管理</a></li>
		</ul>
</div>

<script type="text/javascript">
$(function() {
	var curcss=$('body').attr('class');
	$('.sidebar-nav ul.' + curcss).addClass("in");
});

</script>
</body>
</html>