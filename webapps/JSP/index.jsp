<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>

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
<!-- Animate.css -->

<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath}/Extra/build/css/custom.min.css"
	rel="stylesheet">

<%
	int wrongUPW = 0;
	int userblocked = 0;
	String str = "";
	try {
		wrongUPW = Integer.parseInt(session.getAttribute("wrongUPW").toString());
		userblocked = Integer.parseInt(session.getAttribute("accntBlock").toString());
		session.setAttribute("wrongUPW", 0);
		session.setAttribute("accntBlock", 0);
		if (wrongUPW == 1) {
			str = "Invalid username or password";
		} else if (userblocked == 1) {
			str = "This account is blocked, Please contact SM team!!";
		}
	} catch (Exception e) {
	}
%>

<script type="text/javascript">
<%-- 	$(document).ready(function() {
		<%if (wrongUPW == 1 || userblocked == 1) 
			{
		%>
			$("#errorMsg").text(<%= "\"" + str + "\""%>).show(0).delay(5000).hide(0);
		<%
			}
		%>
	}); --%>
	$("input#username,input#password").keyup(function(ev) {
		if (ev.which === 13) {
			loginFun();
		}
	});
	$("#loginForm").submit(function(event) {
		event.preventDefault();
	});
	function loginFun() {
		if ($("#username").val() == "") {
			alert("Wrong Username");
			return false;
		} else if ($("#pwd").val() == "") {
			alert("Wrong Password");
			return false;
		} else {
			$('#password').val(hex_md5($('#pwd').val()));
			$("#loginForm").submit();
		}
	}
</script>
</head>
<body class="login">
	<div>
		<a class="hiddenanchor" id="signup"></a>
		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<form name="loginForm" id="loginForm"
						action="${pageContext.request.contextPath}/userLogin"
						method="post">
						<h1>Please Login</h1>
						<div>
							<input type="text" class="form-control" name="username"
								id="username" placeholder="Username" autocomplete='off' />
						</div>
						<div>
							<input type="password" class="form-control"
								placeholder="************" id="pwd" autocomplete='off' /> <input
								type="hidden" class="" name="password" id="password">
						</div>
						<div class="col-sm-12 text-center" id='errorMsg'
							style="color: red">
							<span id="error"><%=str%></span>
						</div>
						<div>
							<button type="submit" class="btn btn-primary submit"
								name="submit" value="Login" onclick="return loginFun();">Log
								in</button>
						</div>
						<div class="separator">
							<br />
							<div>
								<h1>Smart Trolley</h1>
								<h1>
									<i> <%-- <img style="height: 90px" alt="" src="${pageContext.request.contextPath}/Extra/images/ecw-logo.jpg">  --%>
									</i>
								</h1>
								<p>©2017-18 All Rights Reserved. Version 1.0.0</p>
							</div>
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/Extra/vendors/jquery/dist/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/hex_md5.js"
		type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/Login.js"
		type="text/javascript"></script>
</body>
</html>