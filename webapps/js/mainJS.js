$(document).ready(function() {
	//alert("test");
	var data = getServerDetails("voice");
	//alert(data);
});
function getServerDetails(serverType){
	var res = "test";
	$.ajax({
		url : "/Smart-Trolley/JSP/Controller.jsp",
		type : "POST",
		data : {
			submit : "fetchData",
			type : serverType
		},
		dataType : "JSON",
		success : function(data) {
			 var res = data;
		}
	});
	return res;
}
