<%@page import="com.dao.TrolleyLogs"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Dashboard</title>
<link href="${pageContext.request.contextPath}/CSS/load.css"
	rel="stylesheet"></link>
<link
	href="${pageContext.request.contextPath}/Extra/vendors/select2/dist/css/select2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Extra/vendors/jquery/dist/jquery-ui.css"
	rel="stylesheet"></link>
<%-- <link
	href="${pageContext.request.contextPath}/Extra/vendors/switchery/dist/switchery.min.css"
	rel="stylesheet"> --%>
<!-- Datatables -->
<link
	href="${pageContext.request.contextPath}/Extra/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Extra/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Extra/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Extra/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Extra/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/Extra/vendors/bootstrap-dialog/dist/css/bootstrap-dialog.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Extra/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/Extra/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link
	href="${pageContext.request.contextPath}/Extra/vendors/nprogress/nprogress.css"
	rel="stylesheet">
<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath}/Extra/build/css/custom.min.css"
	rel="stylesheet">
<%
	String name = "";
	String url = "";
	int admin = 0;
	int master = 0;
	if (session.getAttribute("isAuthenticated") == null) {
		response.sendRedirect(request.getContextPath() + "/sessionExpired");
	} else {
		try {
			admin = Integer.parseInt(session.getAttribute("admin").toString());
			master = Integer.parseInt(session.getAttribute("superAdmin").toString());
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	}
%>
</head>
<body class="login">
	<div class="container body">
		<div class="main_container">
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Inventory</h2>
							<ul class="navbar-right panel_toolbox">
								<li><a class="collapse-link"><i
										class="fa fa-chevron-up"></i></a></li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<div id="inventory" class="table-responsive">
								<table id="inventoryTable"
									class="table table-striped table-bordered table-hover"></table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Today's Sale</h2>
							<ul class="navbar-right panel_toolbox">
								<li><a class="collapse-link"><i
										class="fa fa-chevron-up"></i></a></li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<div id="todaysSale" class="table-responsive">
								<table id="todaysSaleTable"
									class="table table-striped table-bordered table-hover"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="trolleyDetails">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<h2>Trollies</h2>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel" id="frontBar">
								<div class="control-group">
									<div class="controls">
										<div class="col-md-6 xdisplay_inputx form-group has-feedback">
											<div class="x_title">
												<h2>Trollies in Use</h2>
												<ul class="navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<div id="inusetrolleytable" class="table-responsive">
													<table id="inUseTrollies"
														class="table table-striped"></table>
												</div>
											</div>
										</div>
										<div class="col-md-6 xdisplay_inputx form-group has-feedback">
											<div class="x_title">
												<h2>Trollies not in Use</h2>
												<ul class="navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<div id="notinusetrolleytable" class="table-responsive">
													<table id="notInUseTrollies"
														class="table table-striped table-bordered table-hover"></table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-body">
			<div class="row table-responsive">
				<div class="col-sm-12">
					<table id="popupTable"
						class="table table-striped table-bordered table-hover"></table>
				</div>
			</div>
		</div>
		<div id="divLoading"></div>
	</div>
	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/jquery/dist/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/jquery/dist/jquery-ui.js"></script>
	<!-- Bootstrap -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/bootstrap-dialog/dist/js/bootstrap-dialog.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/Extra/vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/jszip/dist/jszip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/pdfmake/build/pdfmake.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/moment/min/moment.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/build/js/custom.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/pdfmake/build/vfs_fonts.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
	<script src="${pageContext.request.contextPath}/js/util.js"></script>
	<script type="text/javascript">
		setTimeout(function() {
			location.reload();
		}, 1800000);
	</script>
</body>
</html>
