package com.dao;

import java.util.ArrayList;
import com.Model.billingModel;
import com.Model.discountModel;
import com.Model.itemCategoryModel;
import com.Model.itemDetailsModel;
import com.Model.itemModel;
import com.Model.itemTaxPlanModel;

public class DataConverter {  // class with functions to convert list data to html forms
	public String getBlankTable() {
		String sB = "";
		sB += "<thead><tr class=\"headings\" align=\"center\">";
		sB += "<th class=\"column-title\"></th>";
		sB += "</tr></thead>";
		sB += "<tbody>";
		sB += "<tr class=\"even pointer\" align=\"center\">";
		sB += "<td>No Data To Display</td></tr></tbody>";
		return sB;
	}
	
	public String getItemsTable(ArrayList<itemModel> itemData) {
		String sB = "";
		if (itemData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			if (itemData != null) {
				String hData[] = {"Item ID","Item Name","Item Desc","Price","Discount Desc","Discount(%)","Tax","Tax(%)","RFID"};
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (itemModel i : itemData) {
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					sB += "<td align=\"left\">" + i.getItem_ID() + "</td>";
					sB += "<td align=\"left\">" + i.getItemDetailsModel().getItemCategory().getItemName() + "</td>";
					sB += "<td align=\"left\">" + i.getItemDetailsModel().getItemCategory().getItemDesc() + "</td>";
					sB += "<td align=\"left\">$" + i.getItemDetailsModel().getItemCategory().getPrice() + "</td>";
					sB += "<td align=\"left\">" + i.getItemDetailsModel().getDiscountModel().getDiscontDescription() + "</td>";
					sB += "<td align=\"left\">" + i.getItemDetailsModel().getDiscountModel().getDiscount() + " %</td>";
					sB += "<td align=\"left\">" + i.getItemDetailsModel().getItemVATPlan().gettaxPlanName() + "</td>";
					sB += "<td align=\"left\">" + i.getItemDetailsModel().getItemVATPlan().gettaxPercentage() + "%</td>";
					sB += "<td align=\"left\">" + i.getRfidModel().getRFID() + "</td>";
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}
	public String getItemsDetailsTable(ArrayList<itemDetailsModel> itemData) {
		String sB = "";
		if (itemData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			if (itemData != null) {
				String hData[] = {"Item ID","Item Name","Item Desc","Price","Discount Desc","Discount(%)","Tax","Tax(%)",""};
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (itemDetailsModel i : itemData) {
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					sB += "<td align=\"left\">" + i.getItemDetailsID() + "</td>";
					sB += "<td align=\"left\">" + i.getItemCategory().getItemName() + "</td>";
					sB += "<td align=\"left\">" + i.getItemCategory().getItemDesc() + "</td>";
					sB += "<td align=\"left\">$" + i.getItemCategory().getPrice() + "</td>";
					sB += "<td align=\"left\">" + i.getDiscountModel().getDiscontDescription() + "</td>";
					sB += "<td align=\"left\">" + i.getDiscountModel().getDiscount() + " %</td>";
					sB += "<td align=\"left\">" + i.getItemVATPlan().gettaxPlanName() + "</td>";
					sB += "<td align=\"left\">" + i.getItemVATPlan().gettaxPercentage() + "%</td>";
					sB += "<td align=\"center\"><input type=\"button\" class=\"btn btn-primary\" id=\"editItemDetails\" value=\"Edit\" onclick=\"editItemDetails(this)\"> </td>";
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}
	public String getDiscountTable(ArrayList<discountModel> itemData) {
		String sB = "";
		if (itemData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			if (itemData != null) {
				String hData[] = {"Discount ID","Discount Desc","Discount(%)",""};
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (discountModel i : itemData) {
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					sB += "<td align=\"left\">" + i.getDiscountID() + "</td>";
					sB += "<td align=\"left\">" + i.getDiscontDescription() + "</td>";
					sB += "<td align=\"left\">" + i.getDiscount() + "%</td>";
					sB += "<td align=\"center\"><input type=\"button\" class=\"btn btn-primary\" id=\"editDiscountDetails\" value=\"Edit\" onclick=\"editDiscountDetails(this)\"> </td>";
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}
	public String getTaxTable(ArrayList<itemTaxPlanModel> itemData) {
		String sB = "";
		if (itemData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			if (itemData != null) {
				String hData[] = {"Tax ID","Tax Name","Tax(%)",""};
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (itemTaxPlanModel i : itemData) {
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					sB += "<td align=\"left\">" + i.gettaxPlanId() + "</td>";
					sB += "<td align=\"left\">" + i.gettaxPlanName() + "</td>";
					sB += "<td align=\"left\">" + i.gettaxPercentage() + "%</td>";
					sB += "<td align=\"center\"><input type=\"button\" class=\"btn btn-primary\" id=\"editTaxDetails\" value=\"Edit\" onclick=\"editTaxDetails(this)\"> </td>";
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}
	public String getItemsCategoryTable(ArrayList<itemCategoryModel> itemData) {
		String sB = "";
		if (itemData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			if (itemData != null) {
				String hData[] = {"Categoty ID","Item Name","Item Desc","Price",""};
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (itemCategoryModel i : itemData) {
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					sB += "<td align=\"left\">" + i.getCatID() + "</td>";
					sB += "<td align=\"left\">" + i.getItemName() + "</td>";
					sB += "<td align=\"left\">" + i.getItemDesc() + "</td>";
					sB += "<td align=\"left\">$" + i.getPrice() + "</td>";
					sB += "<td align=\"center\"><input type=\"button\" class=\"btn btn-primary\" id=\"editCategoryDetails\" value=\"Edit\" onclick=\"editCategoryDetails(this)\"> </td>";
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}
	
	public String getCollapseTable(ArrayList<String> statusReportData) {
		String sB = "";
		if (statusReportData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"details-control sorting_disabled\" rowspan=\"1\" colspan=\"1\" aria-label=\"\" style=\"width: 16px;\"></th>";
			if (statusReportData != null) {
				String hData[] = statusReportData.get(0).split(";");
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (int i = 1; i < statusReportData.size(); i++) {
					String data[] = statusReportData.get(i).split(";");
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					sB += "<td class=\"details-control\"></td>";
					for (int j = 0; j < data.length; j++) {
						sB += "<td align=\"left\">" + data[j] + "</td>";
					}
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}

	public String getReportTable(ArrayList<String> statusReportData) {
		String sB = "";
		if (statusReportData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			if (statusReportData != null) {
				String hData[] = statusReportData.get(0).split(";");
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (int i = 1; i < statusReportData.size(); i++) {
					String data[] = statusReportData.get(i).split(";");
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					for (int j = 0; j < data.length; j++) {
						sB += "<td align=\"left\">" + data[j] + "</td>";
					}
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}

	public String getInventoryTable(ArrayList<String> inventoryData) {
		String sB = "";
		if (inventoryData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			if (inventoryData != null) {
				String hData[] = inventoryData.get(0).split(";");
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (int i = 1; i < inventoryData.size(); i++) {
					String data[] = inventoryData.get(i).split(";");
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					for (int j = 0; j < data.length; j++) {
						if (j == 3 && Integer.parseInt(data[3]) < 5) {
							System.out.println(data[j]);
							sB += "<td align=\"left\"><a style=\"color:red\" id=\"" + data[0]
									+ "\" href=\"javascript:void(0);\" name=\"" + data[0]
									+ "\" onclick=\"openDialogData(this);\">" + data[j] + "</td>";
						} else {
							sB += "<td align=\"left\">" + data[j] + "</td>";
						}

					}
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}

	public String getTransactiontable(ArrayList<billingModel> trData) {
		String sB = "";
		if (trData.size() == 0) {
			sB = "";
			sB += "<thead><tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"column-title\"></th>";
			sB += "</tr></thead>";
			sB += "<tbody>";
			sB += "<tr class=\"even pointer\" align=\"center\">";
			sB += "<td>No Data To Display</td></tr></tbody>";
		} else {
			sB = "";
			sB += "<thead>";
			sB += "<tr class=\"headings\" align=\"center\">";
			sB += "<th class=\"details-control sorting_disabled\" rowspan=\"1\" colspan=\"1\" aria-label=\"\" style=\"width: 16px;\"></th>";
			if (trData != null) {
				String hData[] = {"Bill ID","Transaction ID","Trolley ID","Date","Total Items","Total Amount","Total Discount","Total Tax","Final Amount"};
				for (int i = 0; i < hData.length; i++) {
					sB += "<th class=\"column-title\" align=\"center\">" + hData[i].toUpperCase() + "</th>";
				}
				sB += "</tr></thead>";
				sB += "<tbody>";
				for (billingModel b : trData) {
					sB += "<tr class=\"even pointer\" align=\"center\" style=\"font-weight: bold\">";
					sB += "<td class=\"details-control\"></td>";		
					sB += "<td align=\"left\">" + b.getBillID() + "</td>";
					sB += "<td align=\"left\">" + b.getTransactionID() + "</td>";
					sB += "<td align=\"left\">" + b.getTrolleyID() + "</td>";
					sB += "<td align=\"left\">" + b.getTimeStamp() + "</td>";
					sB += "<td align=\"left\">" + b.getNumItems() + "</td>";
					sB += "<td align=\"left\">$" + b.getTotalAmount() + "</td>";
					sB += "<td align=\"left\">$" + b.getTotalDiscount() + "</td>";
					sB += "<td align=\"left\">$" + b.getTotalTax() + "</td>";
					sB += "<td align=\"left\">$" + b.getFinalAmount() + "</td>";
					sB += "</tr>";
				}
				sB += "</tbody>";
			}
		}
		return sB;
	}
}
