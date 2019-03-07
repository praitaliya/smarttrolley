$(document).ready(function() {
	loadItemData();
});
$("#items-tab").click(function() {
	loadItemData();
});
$("#item-discount-tab").click(function() {
	loadDiscountData();
});
$("#item-details-tab").click(function() {
	loadItemDetails();
});
$("#item-categories-tab").click(function() {
	loadItemCatData();
});
$("#item-tax-tab").click(function() {
	loadTaxDetails();
});
function loadItemData() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getItemData",
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
			if ($.fn.dataTable.isDataTable("#items_data")) {
				$("#items_data").DataTable().destroy();
			}
			$("#items_data").html(Base64.decode(data));
			var table = $("#items_data").DataTable({
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
function loadDiscountData() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getItemDiscountData",
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
			if ($.fn.dataTable.isDataTable("#items_discount_data")) {
				$("#items_discount_data").DataTable().destroy();
			}
			$("#items_discount_data").html(Base64.decode(data));
			var table = $("#items_discount_data").DataTable({
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
function loadItemCatData() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getItemCategoryData",
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
			if ($.fn.dataTable.isDataTable("#items_category_data")) {
				$("#items_category_data").DataTable().destroy();
			}
			$("#items_category_data").html(Base64.decode(data));
			var table = $("#items_category_data").DataTable({
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
function loadItemDetails() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getItemDetailsData",
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
			if ($.fn.dataTable.isDataTable("#items_details_data")) {
				$("#items_details_data").DataTable().destroy();
			}
			$("#items_details_data").html(Base64.decode(data));
			var table = $("#items_details_data").DataTable({
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
function loadTaxDetails() {
	$.ajax({
		url : "/Smart-Trolley/JSP/getTableData.jsp",
		type : "POST",
		data : {
			submit : "getItemTaxData",
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
			if ($.fn.dataTable.isDataTable("#items_tax_data")) {
				$("#items_tax_data").DataTable().destroy();
			}
			$("#items_tax_data").html(Base64.decode(data));
			var table = $("#items_tax_data").DataTable({
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
function editDiscountDetails(id) {
	var str = "";
	var dname = "";
	var dperc = "";
	var did = "";
	var checkedValue = $(id).val();
	$(id).each(function() {
		arr = $(this).closest('tr').find('td');
		did = arr[0].innerHTML;
		dname = arr[1].innerHTML;
		perc = arr[2].innerHTML;
		dperc = perc.replace('%', '');
	});
	/* var url = "/MSG-DB/JSP/getStatusCountTableData.jsp?data=" + data */;
	str += "<form id=\"\" method=\"post\" class=\"form-horizontal\">";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Discount ID <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-4 col-sm-4 col-xs-12\"><input id=\"discountid\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Tonny\" type=\"text\" disabled value=\""
			+ did + "\"></div>";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Discount Details <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-4 col-sm-4 col-xs-12\"><input id=\"discountdesc\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ dname + "\"></div></div>";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Discount Percentage <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-10 col-sm-10 col-xs-12\"><input type=\"text\" id=\"dpercentage\" class=\"form-control col-md-10 col-xs-12\" placeholder=\"e.g 10\" value=\""
			+ dperc + "\"></div></div>";
	str += "<label class=\"col-xs-12 control-label\" id=\"errtab\" readonly=\"readonly\" style=\"text-align: center;\"></label>";
	str += "<label class=\"col-xs-12 control-label\" id=\"msgtab\" readonly=\"readonly\" style=\"text-align: center;color:green\"></label>";
	str += "</div></form>";

	BootstrapDialog
			.show({
				title : 'Edit Discount : ' + dname,
				message : $('<div></div>').html(str),
				buttons : [
						{
							icon : 'glyphicon glyphicon-send',
							label : 'Submit',
							cssClass : 'btn-primary',
							action : function(dialogRef) {
								var did = $("#discountid").val();
								var dname = $("#discountdesc").val();
								var dperc = $("#dpercentage").val();
								dialogRef.enableButtons(false);
								dialogRef.setClosable(false);
								var data = did + "," + dname + "," + dperc;
								$
										.post(
												"/Smart-Trolley/JSP/Controller.jsp",
												{
													submit : "editDiscountData",
													data : Base64.encode(data)
												})
										.done(
												function(data) {
													if (data === 0) {
														$("#errtab")
																.text(
																		"Something went wrong please try againg later.");
														$("#msgtab")
																.text(
																		"This Dialog will close in 2 seconds.");
														setTimeout(function() {
															dialogRef.close();
														}, 2000);
													} else {
														$("#errtab").text(
																"Success");
														$("#msgtab")
																.text(
																		"This Dialog will close in 3 seconds.");
														setTimeout(function() {
															dialogRef.close();
															loadDiscountData();
														}, 3000);
													}
												});
							}
						}, {
							label : 'Close',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
			});
}
function editTaxDetails(id) {
	var str = "";
	var tid = "";
	var tname = "";
	var tperc = "";
	var tfrom = "";
	var tto = "";
	var checkedValue = $(id).val();
	$(id).each(function() {
		arr = $(this).closest('tr').find('td');
		tid = arr[0].innerHTML;
		tname = arr[1].innerHTML;
		var perc = arr[2].innerHTML;
		tperc = perc.replace('%', '');
		tfrom = arr[2].intterHTML;
		tto = arr[3].innerHTML;
	});
	/* var url = "/MSG-DB/JSP/getStatusCountTableData.jsp?data=" + data */;
	str += "<form id=\"\" method=\"post\" class=\"form-horizontal\">";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Tax ID <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-4 col-sm-4 col-xs-12\"><input id=\"taxid\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Tonny\" type=\"text\" disabled value=\""
			+ tid + "\"></div>";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Tax Details <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-4 col-sm-4 col-xs-12\"><input id=\"taxdesc\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ tname + "\"></div></div>";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Tax Percentage <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-10 col-sm-10 col-xs-12\"><input type=\"text\" id=\"tpercentage\" class=\"form-control col-md-10 col-xs-12\" placeholder=\"e.g 10\" value=\""
			+ tperc + "\"></div></div>";
	str += "<label class=\"col-xs-12 control-label\" id=\"errtab\" readonly=\"readonly\" style=\"text-align: center;\"></label>";
	str += "<label class=\"col-xs-12 control-label\" id=\"msgtab\" readonly=\"readonly\" style=\"text-align: center;color:green\"></label>";
	str += "</div></form>";

	BootstrapDialog
			.show({
				title : 'Edit Tax : ' + tname,
				message : $('<div></div>').html(str),
				buttons : [
						{
							icon : 'glyphicon glyphicon-send',
							label : 'Submit',
							cssClass : 'btn-primary',
							action : function(dialogRef) {
								var did = $("#taxid").val();
								var dname = $("#taxdesc").val();
								var dperc = $("#tpercentage").val();
								dialogRef.enableButtons(false);
								dialogRef.setClosable(false);
								var data = did + "," + dname + "," + dperc;
								$
										.post(
												"/Smart-Trolley/JSP/Controller.jsp",
												{
													submit : "editTaxData",
													data : Base64.encode(data)
												})
										.done(
												function(data) {
													if (data === 0) {
														$("#errtab")
																.text(
																		"Something went wrong please try againg later.");
														$("#msgtab")
																.text(
																		"This Dialog will close in 2 seconds.");
														setTimeout(function() {
															dialogRef.close();
														}, 2000);
													} else {
														$("#errtab").text(
																"Success");
														$("#msgtab")
																.text(
																		"This Dialog will close in 3 seconds.");
														setTimeout(function() {
															dialogRef.close();
															loadTaxDetails();
														}, 3000);
													}
												});
							}
						}, {
							label : 'Close',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
			});
}
function editItemDetails(id) {
	var str = "";
	var itemid = "";
	var itemname = "";
	var discountname = "";
	var taxname = "";
	var dperc = "";
	var checkedValue = $(id).val();
	$(id).each(function() {
		arr = $(this).closest('tr').find('td');
		itemid = arr[0].innerHTML;
		itemname = arr[1].innerHTML;
		discountname = arr[4].innerHTML;
		dperc = arr[5].innerHTML;
		taxname = arr[6].innerHTML;
		tperc = arr[7].innerHTML;
	});
	/* var url = "/MSG-DB/JSP/getStatusCountTableData.jsp?data=" + data */;
	str += "<form id=\"\" method=\"post\" class=\"form-horizontal\">";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Item ID</span></label>";
	str += "<div class=\"col-md-2 col-sm-2 col-xs-12\"><input id=\"itemid\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Tonny\" type=\"text\" disabled value=\""
			+ itemid + "\"></div>";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Item Name <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-5 col-sm-5 col-xs-12\"><input type=\"text\" id=\"itemname\" class=\"form-control col-md-10 col-xs-12\" placeholder=\"e.g 10\" disabled value=\""
			+ itemname + "\"></div></div>";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Discount Name <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-5 col-sm-5 col-xs-12\"><input id=\"dname\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ discountname + "\"></div>";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Discount(%)</label>";
	str += "<div class=\"col-md-2 col-sm-2 col-xs-12\"><input id=\"dperc\" disabled class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ dperc + "\"></div></div>";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Tax Name <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-5 col-sm-5 col-xs-12\"><input id=\"taxname\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ taxname + "\"></div>";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Tax(%)</label>";
	str += "<div class=\"col-md-2 col-sm-2 col-xs-12\"><input id=\"tperc\" disabled class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ tperc + "\"></div></div>";
	str += "<label class=\"col-xs-12 control-label\" id=\"errtab\" readonly=\"readonly\" style=\"text-align: center;\"></label>";
	str += "<label class=\"col-xs-12 control-label\" id=\"msgtab\" readonly=\"readonly\" style=\"text-align: center;color:green\"></label>";
	str += "</div></form>";

	BootstrapDialog
			.show({
				title : 'Edit Item Details : ' + itemname,
				message : $('<div></div>').html(str),
				buttons : [
						{
							icon : 'glyphicon glyphicon-send',
							label : 'Submit',
							cssClass : 'btn-primary',
							action : function(dialogRef) {
								var cid = $("#itemid").val();
								var cname = $("#itemname").val();
								var cdesc = $("#dname").val();
								var price = $("#taxname").val();
								dialogRef.enableButtons(false);
								dialogRef.setClosable(false);
								var data = cid + "," + cname + "," + cdesc
										+ "," + price;
								$
										.post(
												"/Smart-Trolley/JSP/Controller.jsp",
												{
													submit : "editCatData",
													data : Base64.encode(data)
												})
										.done(
												function(data) {
													if (data === 0) {
														$("#errtab")
																.text(
																		"Something went wrong please try againg later.");
														$("#msgtab")
																.text(
																		"This Dialog will close in 2 seconds.");
														setTimeout(function() {
															dialogRef.close();
														}, 2000);
													} else {
														$("#errtab").text(
																"Success");
														$("#msgtab")
																.text(
																		"This Dialog will close in 3 seconds.");
														setTimeout(function() {
															dialogRef.close();
															loadItemCatData();
														}, 3000);
													}
												});
							}
						}, {
							label : 'Close',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
			});
}
function editCategoryDetails(id) {
	var str = "";
	var cid = "";
	var cname = "";
	var cdesc = "";
	var cprice = "";
	var checkedValue = $(id).val();
	$(id).each(function() {
		arr = $(this).closest('tr').find('td');
		cid = arr[0].innerHTML;
		cname = arr[1].innerHTML;
		cdesc = arr[2].innerHTML;
		var price = arr[3].innerHTML;
		cprice = price.replace('$', '');
	});
	/* var url = "/MSG-DB/JSP/getStatusCountTableData.jsp?data=" + data */;
	str += "<form id=\"\" method=\"post\" class=\"form-horizontal\">";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Category ID <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-2 col-sm-2 col-xs-12\"><input id=\"catid\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Tonny\" type=\"text\" disabled value=\""
			+ cid + "\"></div>";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Price <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-5 col-sm-5 col-xs-12\"><input type=\"text\" id=\"price\" class=\"form-control col-md-10 col-xs-12\" placeholder=\"e.g 10\" value=\""
			+ cprice + "\"></div></div>";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Item Name <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-9 col-sm-9 col-xs-12\"><input id=\"cname\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ cname + "\"></div></div>";
	str += "<div class=\"form-group\">";
	str += "<label class=\"control-label col-md-2 col-sm-2 col-xs-12\">Item Desc <span class=\"required\">*</span></label>";
	str += "<div class=\"col-md-9 col-sm-9 col-xs-12\"><input id=\"cdesc\" class=\"form-control col-md-7 col-xs-12\" placeholder=\"e.g Description\" type=\"text\" value=\""
			+ cdesc + "\"></div></div>";
	str += "<label class=\"col-xs-12 control-label\" id=\"errtab\" readonly=\"readonly\" style=\"text-align: center;\"></label>";
	str += "<label class=\"col-xs-12 control-label\" id=\"msgtab\" readonly=\"readonly\" style=\"text-align: center;color:green\"></label>";
	str += "</div></form>";

	BootstrapDialog
			.show({
				title : 'Edit Category : ' + cname,
				message : $('<div></div>').html(str),
				buttons : [
						{
							icon : 'glyphicon glyphicon-send',
							label : 'Submit',
							cssClass : 'btn-primary',
							action : function(dialogRef) {
								var cid = $("#catid").val();
								var cname = $("#cname").val();
								var cdesc = $("#cdesc").val();
								var price = $("#price").val();
								dialogRef.enableButtons(false);
								dialogRef.setClosable(false);
								var data = cid + "," + cname + "," + cdesc
										+ "," + price;
								alert(data);
								$
										.post(
												"/Smart-Trolley/JSP/Controller.jsp",
												{
													submit : "editCatData",
													data : Base64.encode(data)
												})
										.done(
												function(data) {
													if (data === 0) {
														$("#errtab")
																.text(
																		"Something went wrong please try againg later.");
														$("#msgtab")
																.text(
																		"This Dialog will close in 2 seconds.");
														setTimeout(function() {
															dialogRef.close();
														}, 2000);
													} else {
														$("#errtab").text(
																"Success");
														$("#msgtab")
																.text(
																		"This Dialog will close in 3 seconds.");
														setTimeout(function() {
															dialogRef.close();
															loadItemCatData();
														}, 3000);
													}
												});
							}
						}, {
							label : 'Close',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
			});
}