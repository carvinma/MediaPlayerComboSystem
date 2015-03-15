<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="lib/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<link href="stylesheets/theme.css" type="text/css" rel="stylesheet">
<link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="stylesheets/elements.css" type="text/css" rel="stylesheet">
<script src="lib/jquery-1.7.2.min.js" type="text/javascript" ></script>
<script src="lib/bootstrap/js/bootstrap.js" type="text/javascript" ></script>
<script src="js/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>

<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>
</head>
<body>
	<!--<![endif]-->

	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">

			</ul>
			<a class="brand" href="index.html"><span class="first">Your</span>
				<span class="second">Company</span></a>
		</div>
	</div>
	<div class="row-fluid">
		<div class="dialog">
			<div class="block">
				<p class="block-heading">登录</p>
				<div class="block-body">
					<s:actionerror/>
					<form id="loginForm" action="login.do" method="post">
						<label>用户名</label><input type="text" class="span12" name="loginname" id="loginname">
						<label>密　码</label><input type="password" class="span12" name="password" id="password">
						<a href="index.html" id="lg_btn" class="btn btn-primary pull-right">登录</a> 
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
			<!-- <p class="pull-right" style=""><a href="#" target="blank">Theme by Portnine</a></p> -->
			<p><a href="reset-password.html">忘记密码?</a></p>
		</div>
	</div>


	
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('#lg_btn').click(function() {
				if($('#loginname').val()==''||$('#password').val()==''){
					alert('请输入用户名密码!');
				}else{
					$('#loginForm').submit();
				}
				return false;
			});
		});
	</script>

</body>
</html>



