$(document).ready(function() {
	loadTableData();
});
function loadTableData() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getTransactionData",
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
			if ($.fn.dataTable.isDataTable("#transactionData")) {
				$("#transactionData").DataTable().destroy();
			}
			$("#transactionData").html(Base64.decode(data));
			var table = $("#transactionData").DataTable({
				"destroy" : false,
				dom : 'Bfrtip',
				buttons : [ 'copy', 'csv', 'excel', 'pdf', 'print' ]
			});
			$('#transactionData').on('click', 'tbody td.details-control',
					function() {
						var tr = $(this).closest('tr');
						var row = table.row(tr);
						if (row.child.isShown()) {
							// This row is already open - close it
							row.child.hide();
							tr.removeClass('shown');
						} else {
							// Open this row
							format(row, tr);
						}
					});
		},
		error : function() {
			alert("Something went wrong! Please try again.");
			res = "500";
		}
	});
}
function format(row, tr) {
	var d = row.data();
	var tData = d.toString().split(',');
	$
			.ajax({
				url : '/Smart-Trolley/JSP/Controller.jsp',
				type : 'POST',
				data : jQuery.param({
					submit : "billingMaster",
					trID : tData[2]
				}),
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(response) {
					var sOut = '<table class="table table-striped table-bordered">';
					sOut += '<tr><th>Trolley ID</th><th>RFID</th><th>Item Name</th><th>Item Price</th><th>Discount</th><th>Tax</th><th>Final Amount</th></tr>';
		            var data = response.toString().split(",");
		            console.log(data[0]);
		            for(i = 0;i < data.length ; i++){
		            	var trData = data[i].split("/");
		            	sOut += '<tr><td>' + trData[1] + '</td><td>' + trData[0] +  '</td><td>' + trData[2] + '</td><td>' + trData[3] + '</td><td>' + trData[4] + '</td><td>' + trData[5] + '</td><td>' + trData[6] + '</td></tr>';
		            }
		            sOut += '</table>';
					row.child(sOut).show();
					tr.addClass('shown');
				},
				error : function() {
					alert("error");
				}
			});
}