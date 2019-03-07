package com.dao;

/**
 * interface with list of functions
 * @author Prashant Italiya
 * 
 */

import java.util.ArrayList;

import com.Model.CustomerModel;
import com.Model.RFIDModel;
import com.Model.billingModel;
import com.Model.discountModel;
import com.Model.itemCategoryModel;
import com.Model.itemDetailsModel;
import com.Model.itemModel;
import com.Model.itemTaxPlanModel;
import com.Model.transactionModel;

public interface interfaceTrolley {
	public String addItem(); 
	public String removeItem();
	public String discountPrice();
	public int billGenerate(String trolley_id); // generate bill for trolley
	public String viewNumberOfItem(); // 
	public int checkBudget(Long budget);
	//public void importItems();
	public itemDetailsModel getitemDetailsByID(int id); // fetch item details from item id
	public RFIDModel getRFIDByID(int itemRFID); // get rfid by id
	public itemTaxPlanModel getitemVATPlanByID(int itemVATPlanID); // // get tax plan by tax id
	public discountModel getdicountByID(int itemDiscountID); // get discount data by discount id 
	public void importdiscountData(); // import discount data from excel file
	public void importTaxData(); // impoert tax data from excel file
	public void importRFIDData(); // import rfid dta from excel file
	public void importitemDetailsData(); // import item details from excel file
	public void importItemsData(); // import items from excel file
	public void importitemCatData(); // import category data from excel file
	public ArrayList<itemModel> getAllData(); // get detailed item data
	public ArrayList<itemDetailsModel> getitemDetailsData(); // det detailed item details data
	public ArrayList<itemCategoryModel> getitemCategoryData(); // get category data
	public ArrayList<itemTaxPlanModel> getTaxData(); // get tax data
	public ArrayList<discountModel> getDiscountData(); // get discount data
	public int deleteItem(int itemID); // delete item by item id
	public int validateTrolley(String trolleyID); // validate trolley for usage
	public int validateRFID(String rfid); // validate for correct rfid
	public int insertCustomer(CustomerModel cm); // register customer
	public int updateTrolley(String trolley_id); // update trolley before and after transaction
	public int getitemIDfromRFID(String rfid); // get item is from rfid
	public long getTransactionID(String trolleyID); // get transaction id from trolley id
	public String newRFIDRequest(String trolleyID,String rfid); // handle new request from reader to add or remove item from trolley
	public int insertTransaction(transactionModel tm); // set final transaction into transaction master table
	public int deleteFromTransaction(int itemID,long transactionID, String flag); // delete temp transaction data 
	public ArrayList<String> getInUseTrollies(); // get list of in use trollies
	public ArrayList<itemModel> getitemDetailsByTrolley(long tr_id); // get list of items in trolley by transaction id
	public CustomerModel getCutomerDetailsByTrID(long tr_id); // get customer details by transation id
	public ArrayList<billingModel> getBillingData(); // get billing details
	public ArrayList<String> getNotInUseTrolly(); // get list of not in use trollies
	public ArrayList<String> getTransactionData(int id); // get transaction details 
	public itemCategoryModel getitemCategory(int itemCatID); // get item category data by item category
	public ArrayList<String> getInventory(); // get inventory to display on dashboard
	public ArrayList<String> getTodaysSale(); // get today sale data to display on dashboard
	public int getLastMaxRFID(String flag); // get last added rfid from excel file
	public int updateDiscountDetails(discountModel d); // update discount model
	public int updateTaxDetails(itemTaxPlanModel tm); // update tax model
	public int updateCatDetails(itemCategoryModel icm); // update item category
}
