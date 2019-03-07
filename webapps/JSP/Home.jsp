<%@page import="com.dao.TrolleyLogs"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Smart Trolley</title>

<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/Extra/vendors/nprogress/nprogress.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Extra/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/Extra/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath}/Extra/build/css/custom.min.css"
	rel="stylesheet">
<style type="text/css">
iframe.mainFrame {
	overflow: auto;
}
</style>
<%
	String settingUrl = "";
	String name = "";
	String url = "";
	String d_name = "";
	String ability = "";
	int admin = 0;
	int master = 0;
	if (session.getAttribute("isAuthenticated") == null) {
		response.sendRedirect(request.getContextPath() + "/sessionExpired");
	} else {
		try {
			name = session.getAttribute("Name").toString();
			ability = session.getAttribute("ability").toString();
			admin = Integer.parseInt(session.getAttribute("admin").toString());
			master = Integer.parseInt(session.getAttribute("superAdmin").toString());
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	}
%>
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="${pageContext.request.contextPath}/dashboard"
							target="mainFrame" class="site_title"><i class="fa fa-paw"></i><span
							style="font-size: 18px;"> Smart Trolley</span></a>
					</div>
					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic">
							<%
								System.out.println(request.getContextPath()+"/Extra/logo/trolley-logo.jpg");
							%>
							<img
								src="${pageContext.request.contextPath}/Extra/logo/trolley-logo.jpg"
								alt="Filed to load image" class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2><%=name%></h2>
							<h2>
								<span><%="Admin"%></span>
							</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>General</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-home"></i> Home <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a
											href="${pageContext.request.contextPath}/dashboard"
											target="mainFrame">Dashboard</a></li>
									</ul></li>
								<li><a><i class="fa fa-money"></i>Transactions<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a
											href="${pageContext.request.contextPath}/transactionDashboard"
											target="mainFrame">Transactions</a></li>
									</ul></li>
								<li><a><i class="fa fa-table"></i>Inventory<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a
											href="${pageContext.request.contextPath}/inventory"
											target="mainFrame">Inventory</a></li>
									</ul></li>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>

					<ul class="nav navbar-nav navbar-right">
						<li class=""><a href="javascript:;"
							class="user-profile dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false"> <img
								src="${pageContext.request.contextPath}/Extra/images/img.jpg"
								alt=""><%=name%><span class=" fa fa-angle-down"></span>
						</a>
							<ul class="dropdown-menu dropdown-usermenu pull-right">
								<!-- <li><a href="javascript:;"> Profile</a></li> -->
								</a></li>
						<li><a href="javascript:;">Help</a></li>
						<li><a href="${pageContext.request.contextPath}/logout"><i
								class="fa fa-sign-out pull-right"></i> Log Out</a></li>
					</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<!-- top tiles -->
				<iframe name="mainFrame" src="${pageContext.request.contextPath}/dashboard" width="100%" height="650px"
					style="border: 0; padding-right: 0px;"></iframe>
			</div>
			<!-- /page content -->
		</div>
	</div>

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<!-- Custom Theme Scripts -->
	<script
		src="${pageContext.request.contextPath}/Extra/build/js/custom.min.js"></script>

</body>
</html>
