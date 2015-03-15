<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">
				<li id="fat-menu" class="dropdown">
					<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
					<i class="icon-user"></i> ${sessionScope.USERSESSION.user.userName} 
					</a>
				</li>
				<li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">退出</a></li>
			</ul>
			<a class="brand" href="index.html"><span class="first">Your</span><span class="second">Company</span></a>
		</div>
</div>