$(document).ready(function() {
	var data = $("#requestData").val();
	loadReportTableData(data);
});

function loadReportTableData(type,server,status,date) {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "statusReport",
			data : data
		},
		dataType : "text",
		cache : false,
		beforeSend : function() {
			$("div#divLoading").addClass('show');
			NProgress.start();
		},
		complete : function() {
			$("div#divLoading").removeClass('show');
			NProgress.done();
		},
		success : function(data) {
			if ($.fn.dataTable.isDataTable("#loadTable")) {
				$("#loadTable").DataTable().destroy();
			}
			$("#loadTable").html(Base64.decode(data));
			$("#loadTable").DataTable({
				"destroy" : true,
				dom : 'Bfrtip',
				buttons : [ 'copy', 'csv', 'excel', 'pdf', 'print' ]
			});
		},
		error : function() {
			alert("Something went wrong! Please try again.");
			res = "500";
		}
	});
}