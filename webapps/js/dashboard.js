$(document).ready(function() {
	loadTableData();
	loadInUseTrollies();
	loadNotInUseTrollies();
});

function loadNotInUseTrollies() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getNotInUseTrollies",
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
			if ($.fn.dataTable.isDataTable("#notInUseTrollies")) {
				$("#notInUseTrollies").DataTable().destroy();
				alert(true);
			}
			$("#notInUseTrollies").html(Base64.decode(data));
			var table = $("#notInUseTrollies").DataTable({
				"destroy" : true,
			});
		},
		error : function() {
			alert("Something went wrong! Please try again.");
			res = "500";
		}
	});
}
function loadInUseTrollies() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getInUseTrollies",
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
			if ($.fn.dataTable.isDataTable("#inUseTrollies")) {
				$("#inUseTrollies").DataTable().destroy();
				alert(true);
			}
			$("#inUseTrollies").html(Base64.decode(data));
			var table = $("#inUseTrollies").DataTable({
				"destroy" : false,
			});
			$('#inUseTrollies').on('click', 'tbody td.details-control',
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
	// `d` is the original data object for the row
	var tData = d.toString().split(',').toString().split(' : ');
	$
			.ajax({
				url : '/Smart-Trolley/JSP/getTableData.jsp',
				type : 'POST',
				data : jQuery.param({
					submit : "getTrolleyItems",
					trID : tData[1]
				}),
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(response) {
					var sOut = '<table class="table table-striped table-bordered">';
					sOut += '<tr style=bold><td>Item Name</td><td>Item Price</td><td>RFID</td></tr>';
					if ($.trim(response)) {
						var data = response.toString().split("/");
						for (i = 0; i < (data.length - 1); i++) {
							var trData = data[i].split(",");
							sOut += '<tr><td>' + trData[0] + '</td><td>'
									+ trData[1] + '</td><td>' + trData[2]
									+ '</td></tr>';
						}
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
function openLogs() {
	var url = "/Smart-Trolley/JSP/getLogs.jsp?submit=activityLogs&server=fax";
	BootstrapDialog.show({
		title : "Activity Logs",
		cssClass : 'login-dialog',
		message : $('<div></div>').load(url),
		closable : true,
		stack : true,
		closeByBackdrop : false,
		closeByKeyboard : false,
	});
}
function loadTableData() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getInventoryData",
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
			var data = data.split(" !@#$%^&* ");
			if ($.fn.dataTable.isDataTable("#inventoryTable")) {
				$("#inventoryTable").DataTable().destroy();
			}
			$("#inventoryTable").html(Base64.decode(data[0]));
			$("#inventoryTable").DataTable({
				"destroy" : true,
				dom : 'Bfrtip',
				buttons : [ 'copy', 'csv', 'excel', 'pdf', 'print' ]
			});
			if ($.fn.dataTable.isDataTable("#todaysSaleTable")) {
				$("#todaysSaleTable").DataTable().destroy();
			}
			$("#todaysSaleTable").html(Base64.decode(data[1]));
			$("#todaysSaleTable").DataTable({
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
function openDialogData(e) {
	var data = e.name;
	alert(data);
	data = data.split(',');
	var url = "/Smart-Trolley/JSP/getStatusCountTableData.jsp?data=" + data;
	BootstrapDialog.show({
		title : "Test",
		cssClass : 'login-dialog',
		message : $('<div></div>').load(url),
		closable : true,
		closeByBackdrop : false,
		closeByKeyboard : false,
	});
}