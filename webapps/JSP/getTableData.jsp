<%@page import="com.Model.discountModel"%>
<%@page import="com.Model.itemTaxPlanModel"%>
<%@page import="com.Model.itemCategoryModel"%>
<%@page import="com.Model.itemDetailsModel"%>
<%@page import="com.Model.billingModel"%>
<%@page import="com.Model.itemModel"%>
<%@page import="com.dao.TrolleyLogs"%>
<%@page import="java.util.Base64"%>
<%@page import="com.xml.EncryptUtils"%>
<%@page import="com.dao.DataConverter"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.DAOController"%>
<%@page import="java.util.Map"%>
<%@page import="com.Model.serverInfo"%>
<%@page import="com.xml.XMLParser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	DAOController dao = new DAOController();
	DataConverter dc = new DataConverter();
	int admin = 0;
	String str = request.getParameter("submit");
	if (str == null) {
		response.sendError(404);
	}
	if (session.getAttribute("isAuthenticated") == null) {
		//response.sendRedirect(request.getContextPath() + "/sessionExpired");
		//return;
	} else {
		try {
			admin = Integer.parseInt(session.getAttribute("admin").toString());
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	}
	/* if(!(session.getAttribute("ability").toString().equalsIgnoreCase("All")) && !(session.getAttribute("ability").toString().equalsIgnoreCase(request.getParameter("server")))){
		System.out.println("Server : " + request.getParameter("server"));
		System.out.println("Invalid Access");
		response.sendError(403);
	} */
	if (str.equalsIgnoreCase("getTransactionData")) {
		ArrayList<billingModel> transactionData = dao.getBillingData();
		String tableData = dc.getTransactiontable(transactionData);
		response.setContentType("text/html");
		response.getWriter().write(EncryptUtils.base64encode(tableData));
	} else if (str.equalsIgnoreCase("getInUseTrollies")) {
		try {
			ArrayList<String> data = dao.getInUseTrollies();
			String tableData = dc.getCollapseTable(data);
			response.setContentType("text/html");
			response.getWriter().write(EncryptUtils.base64encode(tableData));
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	} else if (str.equalsIgnoreCase("getNotInUseTrollies")) {
		try {
			ArrayList<String> data = dao.getNotInUseTrolly();
			String tableData = dc.getReportTable(data);
			response.setContentType("text/html");
			response.getWriter().write(EncryptUtils.base64encode(tableData));
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	} else if (str.equalsIgnoreCase("getInventoryData")) {
		try {
			String tableData = "";
			//Map<String,smsStatus> vStatusData = dao.getSMSStatusCount(server, timeZone, outputDate);
			ArrayList<String> inventoryData = dao.getInventory();
			tableData = dc.getInventoryTable(inventoryData);
			ArrayList<String> salesData = dao.getTodaysSale();
			String saleData = dc.getReportTable(salesData);
			tableData = EncryptUtils.base64encode(tableData) + " !@#$%^&* "
					+ EncryptUtils.base64encode(saleData);
			response.setContentType("text/html");
			response.getWriter().write(tableData);
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	} else if (str.equalsIgnoreCase("getTrolleyItems")) {
		try {
			String trID = request.getParameter("trID");
			long tr_id = dao.getTransactionID(trID);
			System.out.println("Transaction id : " + tr_id);
			ArrayList<itemModel> data = dao.getitemDetailsByTrolley(tr_id);
			System.out.println(data);
			String trolleyData = "";
			for (itemModel i : data) {
				trolleyData += data.get(0).getItemDetailsModel().getItemCategory().getItemDesc() + ","
						+ data.get(0).getItemDetailsModel().getItemCategory().getPrice() + ","
						+ data.get(0).getRfidModel().getRFID() + " / ";
			}
			System.out.println(trolleyData);
			response.setContentType("text/html");
			response.getWriter().write(trolleyData);
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	} else if (str.equalsIgnoreCase("getItemData")) {
		try {
			ArrayList<itemModel> items = dao.getAllData();
			String itemsTable = dc.getItemsTable(items);
			response.setContentType("text/html");
			response.getWriter().write(EncryptUtils.base64encode(itemsTable));
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	} else if (str.equalsIgnoreCase("getItemDetailsData")) {
		try {
			ArrayList<itemDetailsModel> items = dao.getitemDetailsData();
			String itemsDetailsTable = dc.getItemsDetailsTable(items);
			response.setContentType("text/html");
			response.getWriter().write(EncryptUtils.base64encode(itemsDetailsTable));
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	}else if(str.equalsIgnoreCase("getItemCategoryData")){
		try {
			ArrayList<itemCategoryModel> items = dao.getitemCategoryData();
			String itemsDetailsTable = dc.getItemsCategoryTable(items);
			response.setContentType("text/html");
			response.getWriter().write(EncryptUtils.base64encode(itemsDetailsTable));
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	}else if(str.equalsIgnoreCase("getItemTaxData")){
		try {
			ArrayList<itemTaxPlanModel> items = dao.getTaxData();
			String itemsDetailsTable = dc.getTaxTable(items);
			response.setContentType("text/html");
			response.getWriter().write(EncryptUtils.base64encode(itemsDetailsTable));
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	}else if(str.equalsIgnoreCase("getItemDiscountData")){
		try {
			ArrayList<discountModel> items = dao.getDiscountData();
			String itemsDetailsTable = dc.getDiscountTable(items);
			response.setContentType("text/html");
			response.getWriter().write(EncryptUtils.base64encode(itemsDetailsTable));
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
		}
	} else {
		response.sendError(404);
	}
%>