package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.ExcelReader;
import com.Model.CustomerModel;
import com.Model.RFIDModel;
import com.Model.billingModel;
import com.Model.discountModel;
import com.Model.itemCategoryModel;
import com.Model.itemDetailsModel;
import com.Model.itemModel;
import com.Model.itemTaxPlanModel;
import com.Model.serverInfo;
import com.Model.transactionMaster;
import com.Model.transactionModel;
import com.util.DBConnection;
import com.xml.EncryptUtils;
import com.xml.XMLParser;

public class DAOController implements interfaceTrolley {
	XMLParser parser = null;
	serverInfo servInfo = null;
	DBConnection db = null;
	Session session = null;
	itemModel itemModel = null;
	itemDetailsModel itemDetails = null;
	itemTaxPlanModel itemVATPlan = null;
	discountModel discount = null;
	RFIDModel rfid = null;
	ExcelReader reader = null;
	Connection conn = null;
	itemCategoryModel itemCat = null;

	public ArrayList<String> getTodaysSale() { // function to get day sale for dashboard
		ArrayList<String> saleData = new ArrayList<String>();
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);
				String sql = "select ic.itemname as itemname,ic.itemdesc as itemdes,ic.itemprice as price,count(i.itemDetailsid) as quantity from items i,itemdetails id,itemcategory ic where i.itemDetailsID=id.itemdetailsid and id.catid=ic.catid and i.issold=1 and DATE_FORMAT(i.TIMESTAMP,'%Y/%m/%d')=? group by (i.itemDetailsid)";
				PreparedStatement pst = conn.prepareStatement(sql);
				// System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				pst.setString(1, new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				ResultSet rs = pst.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int count = metaData.getColumnCount(); // number of column
				String columnName[] = new String[count];
				String columns = "";
				for (int i = 1; i <= count; i++) {
					columnName[i - 1] = metaData.getColumnLabel(i);
					columns += columnName[i - 1] + ";";
				}
				saleData.add(columns);
				while (rs.next()) {
					String data = "";
					for (int i = 0; i < count; i++) {
						data += rs.getString(columnName[i]) + ";";
					}
					saleData.add(data);
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
			TrolleyLogs.AppendToLog(e);
		}
		return saleData;
	}

	public ArrayList<String> getInventory() { // function to get inventory data to display on dashboard
		ArrayList<String> inventoryData = new ArrayList<String>();
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);
				String sql = "select ic.itemname as itemname,ic.itemdesc as itemdescription,ic.itemprice as price,IFNULL(COUNT(i.itemDetailsid), 0) as quantity from items i right join itemdetails id on i.itemDetailsID=id.itemdetailsid right join itemcategory ic on id.catid=ic.catid and i.issold=0 group by (i.itemDetailsid)";
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int count = metaData.getColumnCount(); // number of column
				String columnName[] = new String[count];
				String columns = "";
				for (int i = 1; i <= count; i++) {
					columnName[i - 1] = metaData.getColumnLabel(i);
					columns += columnName[i - 1] + ";";
				}
				inventoryData.add(columns);
				while (rs.next()) {
					String data = "";
					for (int i = 0; i < count; i++) {
						data += rs.getString(columnName[i]) + ";";
					}
					inventoryData.add(data);
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		System.out.println(inventoryData);
		return inventoryData;
	}

	public ArrayList<String> getInUseTrollies() { // will fetch list of trollies currently in use
		ArrayList<String> data = new ArrayList<String>();
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);
				String sql = "select TrolleyID from trolleydetails where isUsed=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, 1);
				ResultSet rs = pst.executeQuery();
				data.add("TrolleyID");
				while (rs.next()) {
					data.add("Trolley ID : " + rs.getString(1));
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	public String authenticate(String uname, String upass) { // authenticate user
		DBConnection db = new DBConnection();
		String response = "";
		String name = "";
		String pass = "";
		String fullName = "";
		int isDeleted = 0;
		int isPassReset = 0;
		parser = new XMLParser();
		int uid = 0;
		serverInfo sInfo = null;
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);
				String sql = "select uid,ufname,ulname,uname,pass,isdeleted,isPassReset from users where uname=? and pass=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, uname);
				pst.setString(2, upass);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					uid = rs.getInt("uid");
					name = rs.getString("uname");
					pass = rs.getString("pass");
					isDeleted = rs.getInt("isdeleted");
					isPassReset = rs.getInt("isPassReset");
					fullName = rs.getString("ufname") + " " + rs.getString("ulname");
				}
				if (uid == 0) { // if uid = 0 no user name or password invalid
					response = "0";
				} else if (isDeleted == 1) { // user is currently disabled
					response = "-1";
				} else if (isPassReset == 1) { // user to reset password
					response = uid + "," + isPassReset + "," + fullName;
				} else if (name.equals(uname) && pass.equals(upass)) { // authenticated user
					response = uid + "," + isPassReset + "," + fullName; 
				}
				conn.close();
			} else {
				System.out.println("Wrong Key");
			}

		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		System.out.println("Res : " + response);
		return response;
	}

	public HttpSession setRights(HttpSession session, int uid) { // get & set user rights after authentication
		DBConnection db = new DBConnection();
		serverInfo sInfo = null;
		parser = new XMLParser();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);
				String sql = "select * from users u,user_role ur where u.role_id=ur.rid and u.uid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, uid);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int admin = 0;
					int superAdmin = 0;
					session.setAttribute("uid", rs.getInt("uid"));
					session.setAttribute("uname", rs.getString("uname"));
					session.setAttribute("Name", rs.getString("ufname") + " " + rs.getString("ulname"));
					session.setAttribute("email", rs.getString("email"));
					session.setAttribute("role_id", rs.getString("role_id"));
					session.setAttribute("role", rs.getString("type"));
					if (rs.getString("type").equalsIgnoreCase("admin")) {
						admin = 1; // set right as admin
					} else if (rs.getString("type").equalsIgnoreCase("master")) {
						superAdmin = 1; // set rights for master user
						admin = 1;
					}
					session.setAttribute("admin", admin);
					session.setAttribute("superAdmin", superAdmin);
					session.setAttribute("isAuthenticated", 1);
					session.setMaxInactiveInterval(60 * 60);
				}
				conn.close();
			}
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return session;
	}

	public int addLoginTimeStamp(String uid, String uname) { // function to timestamp login logs
		DBConnection db = new DBConnection();
		int rs = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);
				String sql = "insert into login_logs (uid,uname) values (?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(uid));
				pst.setString(2, uname);
				rs = pst.executeUpdate();
				conn.close();
			}
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return rs;
	}

	public int validateUser(String fname, String lname, String email) { // check if user is already registered or not
		int response = 0;
		serverInfo sInfo = null;
		parser = new XMLParser();
		DBConnection db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);
				String uname = (fname + "" + lname.charAt(0)).toLowerCase();
				String sql = "select * from users where uname=? and email=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, uname);
				pst.setString(2, email);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					response = 1;
				} else {
					response = 0;
				}
				conn.close();
			}
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			e.printStackTrace();
		}
		return response;
	}

	public int checkPassword(String upass, String uid) { // to validate password for authentication
		serverInfo sInfo = null;
		int response = 0;
		String currPass = "";
		parser = new XMLParser();
		DBConnection db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);
				String sql = "select pass from users where uid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, uid);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					currPass = rs.getString("pass");
				}
				if (currPass.equals(upass)) {
					response = 1;
				}
				conn.close();
			}
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			e.printStackTrace();
		}
		return response;
	}

	public String addItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int billGenerate(String trolleyID) { // function to generate bill for user and their trolley items
		int i = 0;
		try {
			db = new DBConnection();
			Session sc = db.getHiberConnection();
			billingModel bm = new billingModel();
			long tr_id = getTransactionID(trolleyID); // get transaction id using trolley id
			if (tr_id != 0) {
				ArrayList<itemModel> data = getitemDetailsByTrolley(tr_id); // get items avvailable in trolley
				Transaction tr = sc.beginTransaction();
				double totalAmount = 0;
				double totalVAT = 0;
				double totalDisc = 0;
				double finalAmount = 0;
				for (itemModel it : data) {
					itemModel im = new itemModel();
					im = getitemByID(it.getItem_ID()); // fetch master item id from trolley item id
					double price = im.getItemDetailsModel().getItemCategory().getPrice(); // item price
					double vatPer = im.getItemDetailsModel().getItemVATPlan().gettaxPercentage(); // item tax
					double discPer = im.getItemDetailsModel().getDiscountModel().getDiscount(); // item discount
					transactionMaster tm = new transactionMaster();
					tm.setTrolleyID(trolleyID);
					tm.setTransaction_id(tr_id);
					tm.setItem_Name(im.getItemDetailsModel().getItemCategory().getItemDesc());
					tm.setItem_price(price);
					tm.setVATPercentage(vatPer);
					tm.setDiscount(discPer);
					tm.setRFID(im.getRfidModel().getRFID());
					tm.setTimeStamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
					sc.save(tm); // save transaction details to master table to make it final sale
					totalAmount += price;
					totalVAT += (price * vatPer) / 100;
					totalDisc += (price * discPer) / 100;
					deleteFromTransaction(it.getItem_ID(), tr_id, "billGenerate"); //delete item from transation table
				}
				tr.commit();
				sc.close();
				finalAmount = totalAmount + (totalVAT - totalDisc);
				bm.setTransactionID(tr_id);
				bm.setTrolleyID(trolleyID);
				bm.setTotalAmount(totalAmount);
				bm.setTotalDiscount(totalDisc);
				bm.setTotalTax(totalVAT);
				bm.setFinalAmount(finalAmount);
				bm.setNumItems(data.size());
				bm.setTimeStamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
				int val = saveBill(bm);
				if (val > 0) {
					i = updateTrolleyDetails(trolleyID, tr_id); // if bill is saved free the trolley
				}
			} else {
				i = 505; // not recent transaction for that trolley
			}
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	private int saveBill(billingModel bm) { // save bill to billing master table
		int i = 0;
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			Transaction tr = session.beginTransaction();
			session.save(bm);
			tr.commit();
			session.close();
			i = 1;
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	private int updateTrolleyDetails(String trolleyID, long tr_id) { // update trolley details after and before bill
		int i = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);
				// set transaction id for trolley and update flags
				String sql = "update transcation_customer_details set isbilled=? where TrolleyID = ? and transactionId=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, 1);
				pst.setString(2, trolleyID);
				pst.setLong(3, tr_id);
				i = pst.executeUpdate(); 
				if (i > 0) { // if transaction id is already available means that trolley was in use and now need to free trolley
					finishTransaction(trolleyID); // free the trolley
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	private void finishTransaction(String trolleyID) {
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "update trolleydetails set isused=0,isTransactionCompelted=1 where trolleyID=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, trolleyID);
				int i = pst.executeUpdate();
				if (i > 0) {
					System.out.println("Transaction Completed!..Trolley is free now..");
				} else {
					System.out.println("Something went wrong");
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
	}

	@Override
	public String viewNumberOfItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkBudget(Long budget) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String discountPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public void importItems() { try{ reader = new ExcelReader(); db =
	 * new DBConnection(); session = db.getHiberConnection(); ArrayList<itemModel>
	 * itemData = reader.getitemData(); ArrayList<itemDetailsModel> itemDetails =
	 * reader.getitemDetailsData(); Transaction tr = session.beginTransaction();
	 * for(itemModel data : itemData){ session.save(data); } tr.commit();
	 * System.out.println("Item Data Imported Successfully"); Transaction tr1 =
	 * session.beginTransaction(); for(itemDetailsModel data : itemDetails){
	 * session.save(data); } tr1.commit();
	 * System.out.println("Item Details Data Imported Successfully"); } catch
	 * (Exception e) { e.printStackTrace(); TrolleyLogs.AppendToLog(e); } }
	 */

	@Override
	public itemDetailsModel getitemDetailsByID(int id) { // fetch all items in itemdetails table
		itemDetails = new itemDetailsModel();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			itemDetails = session.get(itemDetailsModel.class, id);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return itemDetails;
	}

	@Override
	public RFIDModel getRFIDByID(int itemRFID) { // get RFID by its ID
		rfid = new RFIDModel();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			rfid = session.get(RFIDModel.class, itemRFID);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return rfid;
	}

	@Override
	public itemTaxPlanModel getitemVATPlanByID(int itemVATPlanID) { // get Tax Plan by tax id
		itemVATPlan = new itemTaxPlanModel();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			itemVATPlan = session.get(itemTaxPlanModel.class, itemVATPlanID);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return itemVATPlan;
	}

	@Override
	public discountModel getdicountByID(int itemDiscountID) { // get discount model by dicount id
		discount = new discountModel();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			discount = session.get(discountModel.class, itemDiscountID);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return discount;
	}

	@Override
	public void importdiscountData() { // read Excel file and import dicount data
		try {
			db = new DBConnection();
			reader = new ExcelReader();
			session = db.getHiberConnection();
			ArrayList<discountModel> dicountData = reader.getDiscountData(); // ExcelReader class function to get discount data in list
			Transaction tr = session.beginTransaction();
			for (discountModel disc : dicountData) {
				session.save(disc);
			}
			tr.commit();
			session.close();
			System.out.println("Discount Data Imported Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
	}

	@Override
	public void importTaxData() { // import Tax data from Excel File
		try {
			db = new DBConnection();
			reader = new ExcelReader();
			session = db.getHiberConnection();
			ArrayList<itemTaxPlanModel> vatData = reader.getVATPlanData();
			Transaction tr = session.beginTransaction();
			for (itemTaxPlanModel data : vatData) {
				session.save(data);
			}
			tr.commit();
			session.close();
			System.out.println("VAT Data Imported Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
	}

	@Override
	public void importitemCatData() { // Import Item Category data from excel file
		try {
			db = new DBConnection();
			reader = new ExcelReader();
			session = db.getHiberConnection();
			ArrayList<itemCategoryModel> catData = reader.getCategoryData();
			Transaction tr = session.beginTransaction();
			for (itemCategoryModel data : catData) {
				session.save(data);
			}
			tr.commit();
			session.close();
			System.out.println("VAT Data Imported Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
	}

	@Override
	public void importRFIDData() { // import RFID data from excel file
		try {
			db = new DBConnection();
			reader = new ExcelReader();
			session = db.getHiberConnection();
			ArrayList<RFIDModel> rfidData = reader.getRFIDData();
			Transaction tr = session.beginTransaction();
			for (RFIDModel data : rfidData) {
				session.save(data);
			}
			tr.commit();
			session.close();
			System.out.println("RFID Data Imported Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
	}

	@Override
	public void importitemDetailsData() { // Import Item Details data from excel file
		try {
			reader = new ExcelReader();
			db = new DBConnection();
			session = db.getHiberConnection();
			ArrayList<itemDetailsModel> itemDetailsData = reader.getitemDetailsData();
			Transaction tr = session.beginTransaction();
			// session.clear();
			for (itemDetailsModel detailsData : itemDetailsData) {
				session.save(detailsData);
			}
			tr.commit();
			session.close();
			System.out.println("Item Details Data Imported Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
	}

	@Override
	public void importItemsData() { // Import items from excel file
		try {
			reader = new ExcelReader();
			db = new DBConnection();
			session = db.getHiberConnection();
			ArrayList<itemModel> itemData = reader.getitemData();
			Transaction tr = session.beginTransaction();
			for (itemModel data : itemData) {
				session.save(data);
			}
			tr.commit();
			session.close();
			System.out.println("Item Data Imported Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<itemModel> getAllData() { // get data per item in detailed discription
		ArrayList<itemModel> data = new ArrayList<itemModel>();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			data = (ArrayList<itemModel>) session.createQuery("from itemModel").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public ArrayList<itemDetailsModel> getitemDetailsData() { // get item details for all available items
		ArrayList<itemDetailsModel> data = new ArrayList<itemDetailsModel>();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			data = (ArrayList<itemDetailsModel>) session.createQuery("from itemDetailsModel").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public ArrayList<itemTaxPlanModel> getTaxData() { // get all tax plan data
		ArrayList<itemTaxPlanModel> data = new ArrayList<itemTaxPlanModel>();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			data = (ArrayList<itemTaxPlanModel>) session.createQuery("from itemTaxPlanModel").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public ArrayList<discountModel> getDiscountData() { // get all discount data
		ArrayList<discountModel> data = new ArrayList<discountModel>();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			data = (ArrayList<discountModel>) session.createQuery("from discountModel").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public int deleteItem(int itemID) { // delete item from database 
		int i = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "delete from items where itemid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, itemID);
				i = pst.executeUpdate();
				session.close();
			}
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;

	}

	@Override
	public int validateTrolley(String trolleyID) { // validate trolley if it is in use or not and also if trolley id is correct or not
		int isused = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "select isUsed from TrolleyDetails where TrolleyID = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, trolleyID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					isused = rs.getInt(1);
				} 
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return isused;
	}

	@Override
	public int validateRFID(String rfid) { // validate for correct rfid passed by reader
		int isused = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "select rfid from rfidDetails where rfid = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, rfid);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					isused = 1; // check if rfid is valid or not
				} else {
					isused = 2;
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return isused;
	}

	@Override
	public int insertCustomer(CustomerModel cm) {  // register customer
		int i = 0;
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			Transaction tr = session.beginTransaction();
			session.save(cm);
			tr.commit();
			session.close();
			i = 1;
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	@Override
	public int updateTrolley(String trolley_id) { // update trolley
		int i = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "update TrolleyDetails set isused=?,isTransactionCompelted=? where TrolleyID = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, 1);
				pst.setInt(2, 0);
				pst.setString(3, trolley_id);
				i = pst.executeUpdate();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	@Override
	public int getitemIDfromRFID(String rfid) { // get item id from rfid
		int itemID = 0;
		String sql = null;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				sql = "select item_id from rfiddetails where rfid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, rfid);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					itemID = rs.getInt(1);
				} else {
					itemID = 0; // if rfid is not valid itemid is passed 0
				}
				conn.close();
			}
		} catch (Exception e) {
			itemID = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return itemID;
	}

	@Override
	public long getTransactionID(String trolleyID) { // get transaction id from trolley id
		long tr_id = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);
				// this query will find for max transaction id for particular trolley, because there exist only max transaction when trolley is registed to user
				String sql = "select max(TransactionID) from transcation_customer_details where trolleyid = ? and isbilled=0";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, trolleyID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					tr_id = rs.getInt(1);
				} else {
					tr_id = 0; // tr id = 0 means trolley is not registered for unbilled transaction
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return tr_id;
	}

	@Override
	public int insertTransaction(transactionModel tm) { // register transaction
		int i = 0;
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			Transaction tr = session.beginTransaction();
			session.save(tm);
			tr.commit();
			session.close();
			int j = updateItem(tm.getItem_ID(), "add");
			if (j == 1) {
				i = 1;
			}
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	private int updateItem(int item_ID, String flag) { // update item to sold or not
		int i = 0;
		String sql = null;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				if (flag.equalsIgnoreCase("add")) { // when customer will add item to cart
					sql = "update items set issold=1,timestamp=? where itemID=?";
				} else if (flag.equalsIgnoreCase("delete")) { // when customer will remove item from cart
					sql = "update items set issold=0,timestamp=? where itemID=?";
				}
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
				pst.setInt(2, item_ID);
				i = pst.executeUpdate();
				conn.close();
			}
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	@Override
	public int deleteFromTransaction(int itemID, long transactionID, String flag) { // delete item from transaction table
		int i = 0, j = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "delete from transaction_details where transaction_id=? and itemID=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setLong(1, transactionID);
				pst.setInt(2, itemID);
				if (flag.equalsIgnoreCase("billGenerate")) {
					j = 1;
				} else if (flag.equalsIgnoreCase("delete")) {
					j = updateItem(itemID, "delete");
				}
				if (j == 1) {
					i = pst.executeUpdate();
				}
				conn.close();
			}
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	@Override
	public ArrayList<itemModel> getitemDetailsByTrolley(long tr_id) { // get all items in trolley
		ArrayList<itemModel> data = new ArrayList<itemModel>();
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "select itemID from transaction_details where transaction_id=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setLong(1, tr_id);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					System.out.println("item id : " + rs.getInt(1));
					data.add(getitemByID(rs.getInt(1)));
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	private itemModel getitemByID(int item_id) { // get item model by item id
		itemModel = new itemModel();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			itemModel = session.get(itemModel.class, item_id);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return itemModel;
	}

	@Override
	public CustomerModel getCutomerDetailsByTrID(long tr_id) { // get customer details to whom trolley is registered
		CustomerModel cm = new CustomerModel();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			cm = session.get(CustomerModel.class, tr_id);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return cm;
	}

	@Override
	public ArrayList<billingModel> getBillingData() { // get all billing details to display
		ArrayList<billingModel> data = new ArrayList<billingModel>();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			data = (ArrayList<billingModel>) session.createQuery("from billingModel").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public ArrayList<String> getNotInUseTrolly() { // get list of trollies not in use
		ArrayList<String> data = new ArrayList<String>();
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "select TrolleyID from trolleydetails where isUsed=? and isTransactionCompelted=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, 0);
				pst.setInt(2, 1);
				ResultSet rs = pst.executeQuery();
				data.add("TrolleyID");
				while (rs.next()) {
					data.add("Trolley ID : " + rs.getString(1));
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public ArrayList<String> getTransactionData(int id) { // get master transation data by item id
		ArrayList<String> data = new ArrayList<String>();
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);

				String sql = "select * from transaction_master where transaction_id=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					double price = rs.getDouble("itemPrice");
					double vat = rs.getDouble("VAT");
					double disc = rs.getDouble("discount");
					double finalPrice = price + ((price * vat) / 100 - (price * disc) / 100); // calculate final price after discount and tax
					String transaction = "";
					transaction += rs.getString("RFID") + "/ ";
					transaction += rs.getString("trolleyID") + "/ ";
					transaction += rs.getString("itemName") + "/ ";
					transaction += "$" + price + " / " + vat + "% / " + disc + "% / $" + finalPrice;
					data.add(transaction);
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public itemCategoryModel getitemCategory(int itemCatID) {
		itemCat = new itemCategoryModel();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			itemCat = session.get(itemCategoryModel.class, itemCatID);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return itemCat;
	}

	@Override
	public ArrayList<itemCategoryModel> getitemCategoryData() {
		ArrayList<itemCategoryModel> data = new ArrayList<itemCategoryModel>();
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			data = (ArrayList<itemCategoryModel>) session.createQuery("from itemCategoryModel").list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return data;
	}

	@Override
	public int getLastMaxRFID(String flag) { // get last max rfid added to database from excel file to avoid duplication
		int i = 0;
		String sql = null;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {
				Connection conn = db.getConnection(sInfo);

				if (flag.equalsIgnoreCase("importRFIDData")) {
					sql = "select max(item_ID) from rfiddetails";
				} else if (flag.equalsIgnoreCase("importItemData")) {
					sql = "select max(item_ID) from items";
				}
				PreparedStatement pst = conn.prepareStatement(sql);
				// System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					i = rs.getInt(1);
					System.out.println(i);
				}
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TrolleyLogs.AppendToLog(e);
		}
		return i;
	}

	public int updateDiscountDetails(discountModel d) { // update discount details
		int i = 0;
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			Transaction tr = session.beginTransaction();
			session.update(d);
			tr.commit();
			session.close();
			i = 1;
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			i = 0;
		}
		return i;
	}

	public int updateTaxDetails(itemTaxPlanModel tm) { // update tax plan
		int i = 0;
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			Transaction tr = session.beginTransaction();
			session.update(tm);
			tr.commit();
			session.close();
			i = 1;
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			i = 0;
		}
		return i;
	}

	public int updateCatDetails(itemCategoryModel icm) { // update item category
		int i = 0;
		try {
			db = new DBConnection();
			session = db.getHiberConnection();
			Transaction tr = session.beginTransaction();
			session.update(icm);
			tr.commit();
			session.close();
			i = 1;
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			i = 0;
		}
		return i;
	}

	public String newRFIDRequest(String trolleyID, String rfid) { // handle rfid request from rfid reader 
		String status = "";
		transactionModel tm = new transactionModel();
		try {
			int isValidTrolley = validateTrolley(trolleyID);
			int isValidRFID = validateRFID(rfid);
			if (isValidTrolley == 1 && isValidRFID == 1) { // if trolley id and rfid is valid check for item
				int itemID = getitemIDfromRFID(rfid);
				if (itemID == 0) {
					status = "Item Not Found";
				} else { // if item id is found then check for existing item in trolley
					int checkItemInTrolley = checkItemInTrolley(itemID);
					long transactionID = getTransactionID(trolleyID);
					if (checkItemInTrolley == 1) { // if item is already in trolley means user read item again to remove it from cart
						int i = deleteFromTransaction(itemID, transactionID, "delete"); // remove item from cart
						if (i > 0) {
							status = "Item Deleted from Trolley";
						} else {
							status = "Something went wrong! Please try again";
						}
					} else { // if item is not already in cart check if item is sold or not
						int isSold = checkItemStatus(itemID);
						if (isSold != 1) { // if item is not sold add it to transaction temp table and trolley
							tm.setItem_ID(itemID);
							tm.setTransaction_id(transactionID);
							tm.setTimeStamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
							int i = insertTransaction(tm);
							if (i > 0) {
								status = "Item Added to Trolley";
							} else {
								status = "Something went wrong! Please try again.";
							}
						} else { // if item is already sold send an error
							status = "Item Already sold.";
						}
					}
				}
			}
		} catch (Exception e) {
			status = "Something went wrong! Please try again.";
			TrolleyLogs.AppendToLog(e);
			e.printStackTrace();
		}
		return status;
	}

	private int checkItemStatus(int itemID) {
		int status = 0;
		parser = new XMLParser(); 
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "select issold from items where itemID=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, itemID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) == 1) {
						status = 1;
					} else {
						status = 0;
					}
				}
			}
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			status = 0;
		}
		return status;
		// TODO Auto-generated method stub
	}

	public int checkItemInTrolley(int itemID) {
		int status = 0;
		parser = new XMLParser();
		serverInfo sInfo = null;
		db = new DBConnection();
		try {
			sInfo = parser.getServerInfoByKey("Local");
			if (sInfo != null) {

				Connection conn = db.getConnection(sInfo);

				String sql = "select itemID from transaction_details where itemID=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, itemID);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					status = 1;
				}
			}
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			status = 0;
		}
		return status;
	}
}