<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<title>Error Page</title>

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
<body class="nav-md"
	style="overflow: hidden; margin: 0; background-color: #EDEDED;">
	<div class="container body">
		<div id="main" class="main_container">
			<!-- page content -->
			<div class="row">
				<div class="col-md-12 xol-sm-12 col-xs-12"
					style="background-color: #2A3F54;">
					<div class="col-middle">
						<div class="text-center text-center">
							<h1 class="error-number">500</h1>
							<h2>Internal Server Error</h2>
							<p>
								We track these errors automatically, but if the problem persists
								feel free to contact us. In the meantime, try refreshing. <a
									href="#">Report this?</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 xol-sm-12 col-xs-12"
					style="background-color: #EDEDED;">
					<div class="col-middle">
						<div class="text-center text-center">
							<img
								src="${pageContext.request.contextPath}/Extra/images/smily-icon.PNG"
								class="center-block" alt="smily">
							<p class="fnt14" style="padding-top: 20px">
								Don't worry!<br> You can return to home page.
							</p>
							<button class="btn btn-primary" onclick="goBack()">Home</button>
						</div>
					</div>
				</div>
			</div>
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

	<!-- Custom Theme Scripts -->
	<script
		src="${pageContext.request.contextPath}/Extra/build/js/custom.min.js"></script>
	<script>
		function goBack() {
			window.top.location.href = "${pageContext.request.contextPath}/Home";
		}
	</script>
</body>
</html>
