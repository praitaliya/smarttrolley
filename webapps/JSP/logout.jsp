<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logout</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<% 

		session.removeAttribute("uid");
		session.removeAttribute("uname");
		session.removeAttribute("name");
		session.removeAttribute("email");
		session.invalidate();
		
// 	   response.sendRedirect("/eManagerWeb/sessionExpired");
%>
<body class="container theme-showcase">
<div class="jumbotron">
	<div class="container">
        <h3>Session Ended</h3>
        <p>You are logged out successfully.</p>
        <p><a class="btn btn-primary btn-sm" href="#" role="button" onclick="loadPage()">Login</a></p>
      </div>
</div>
<script type="text/javascript">
function loadPage(){
	try{
	window.top.location.href="${pageContext.request.contextPath}/JSP/index.jsp";
	}catch(e){
		alert('Please refresh the page. Your session has expired.')
	}
}
</script>
</body>
</html>