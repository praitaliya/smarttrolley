<%@page import="com.dao.DataValidation"%>
<%@page import="com.xml.EncryptUtils"%>
<%@page import="com.Model.serverInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.xml.XMLParser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%
	String str = DataValidation.escapeStringForHTML(request.getParameter("data"));
	String titleString = ""; 	
	int admin = 0;
	if (session.getAttribute("isAuthenticated") == null) {
		response.sendRedirect(request.getContextPath() + "/sessionExpired");
		return;
	}
	else{
		admin = Integer.parseInt(session.getAttribute("admin").toString());
	}
	if(str==null){
		response.sendError(404);
	}
%>

<title><%= titleString %></title>
<link href="${pageContext.request.contextPath}/CSS/load.css"
	rel="stylesheet"></link>
<link
	href="${pageContext.request.contextPath}/Extra/vendors/jquery/dist/jquery-ui.css"
	rel="stylesheet"></link>
<!-- Datatables -->
<link
	href="${pageContext.request.contextPath}/Extra/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap -->
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
</head>
<style>
 .modal-dialog {
	width: 75%;
	overflow: auto;
}
</style>
<body>

	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_content">
				<input type="hidden" name="requestData" id="requestData" value=<%= DataValidation.escapeStringForHTML(str)%>>
					<table id="loadTable"
						class="table table-striped jambo_table table-bordered">
					</table>
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
		src="${pageContext.request.contextPath}/Extra/vendors/pdfmake/build/vfs_fonts.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="${pageContext.request.contextPath}/js/loadTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/util.js"></script>
	<%-- <script
		src="${pageContext.request.contextPath}/Extra/vendors/moment/min/moment.min.js"></script> --%>
</body>
</html>