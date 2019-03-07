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
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>
								<i class="fa fa-bars"></i> Inventory
							</h2>
							<ul class="navbar-right panel_toolbox">
								<li><a class="collapse-link"><i
										class="fa fa-chevron-up"></i></a></li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">


							<div class="" role="tabpanel" data-example-id="togglable-tabs">
								<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
									<li role="presentation" class="active"><a href="#items"
										id="items-tab" role="tab" data-toggle="tab"
										aria-expanded="true">Items</a></li>
									<li role="presentation" class=""><a href="#item_details"
										role="tab" id="item-details-tab" data-toggle="tab"
										aria-expanded="false">Item Details</a></li>
									<li role="presentation" class=""><a
										href="#item_categories" role="tab" id="item-categories-tab"
										data-toggle="tab" aria-expanded="false">Item Categories</a></li>
									<li role="presentation" class=""><a
										href="#item_tax" role="tab" id="item-tax-tab"
										data-toggle="tab" aria-expanded="false">Tax</a></li>
									<li role="presentation" class=""><a
										href="#item_discount" role="tab" id="item-discount-tab"
										data-toggle="tab" aria-expanded="false">Discounts</a></li>
								</ul>
								<div id="myTabContent" class="tab-content">
									<div role="tabpanel" class="tab-pane fade active in" id="items"
										aria-labelledby="items-tab">
										<table id="items_data"
											class="table table-striped table-bordered table-hover"></table>
									</div>
									<div role="tabpanel" class="tab-pane fade" id="item_details"
										aria-labelledby="item-details-tab">
										<table id="items_details_data"
											class="table table-striped table-bordered table-hover"></table>
									</div>
									<div role="tabpanel" class="tab-pane fade" id="item_categories"
										aria-labelledby="item-categories-tab">
										<table id="items_category_data"
											class="table table-striped table-bordered table-hover"></table>
									</div>
									<div role="tabpanel" class="tab-pane fade" id="item_tax"
										aria-labelledby="item-tax-tab">
										<table id="items_tax_data"
											class="table table-striped table-bordered table-hover"></table>
									</div>
									<div role="tabpanel" class="tab-pane fade" id="item_discount"
										aria-labelledby="item-discount-tab">
										<table id="items_discount_data"
											class="table table-striped table-bordered table-hover"></table>
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
	<script src="${pageContext.request.contextPath}/js/inventory.js"></script>
	<script src="${pageContext.request.contextPath}/js/util.js"></script>
	<script type="text/javascript">
		setTimeout(function() {
			location.reload();
		}, 1800000);
	</script>
</body>
</html>
