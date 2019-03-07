<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Session Expired</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

</head>
<body class="container theme-showcase">
<div class="jumbotron">
	<div class="container">
        <h3>Session Expired</h3>
        <p>Your session has timed out.</p>
        <p><a class="btn btn-primary btn-sm" href="#" role="button" onclick="loadPage()">Login</a></p>
      </div>
</div>
<script type="text/javascript">
function loadPage(){
	try{
	window.top.location.href="/Smart-Trolley/";
	}catch(e){
		alert('Please refresh the page. Your session has expired.')
	}
}
</script>
</body>
</html>