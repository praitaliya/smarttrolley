package com.dao;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dao.interfaceTrolley;
import com.dao.DAOController;
import com.Model.RFIDModel;
import com.Model.discountModel;
import com.Model.itemCategoryModel;
import com.Model.itemDetailsModel;
import com.Model.itemModel;
import com.Model.itemTaxPlanModel;
 
/**
 * A dirty simple program that reads an Excel file.
 * @author Prashant Italiya
 *
 */
public class ExcelReader {
        String excelFilePath = "E:\\Study\\UWin\\TrolleyWeb\\Smart-Trolley\\src\\SampleData.xlsx";
        FileInputStream inputStream = null;
        Workbook workbook = null;
        Sheet dataSheet = null;
        Iterator<Row> iterator = null;
        interfaceTrolley dao = null;
        
        public ArrayList<itemModel> getitemData() throws IOException{
        	inputStream = new FileInputStream(new File(excelFilePath));
        	System.out.println("get stream done");
        	workbook = new XSSFWorkbook(inputStream);
        	dataSheet = workbook.getSheetAt(0); // worksheet 0 for item details
        	iterator = dataSheet.iterator();
        	System.out.println("iteratoe done");
        	dao = new DAOController();
        	ArrayList<itemModel> data = new ArrayList<itemModel>();
        	itemModel im = null;
        	System.out.println(dataSheet.getLastRowNum());
        	int counter= dao.getLastMaxRFID("importItemData")+1; // check for previously inserted items from excel file
        	while (iterator.hasNext()) {
        		Row nextRow = iterator.next();
        		if(nextRow.getRowNum()==0){
        			continue;
        		}
        		else{
	                Iterator<Cell> cellIterator = nextRow.cellIterator();
	                while (cellIterator.hasNext()) {
	                	int itemDetailsID = (int) cellIterator.next().getNumericCellValue();
	                	//System.out.println(itemDetailsID);
	                	int itemQuantity = (int) cellIterator.next().getNumericCellValue();
	                	//System.out.println(itemRFID);
	                	for(int i=0;i<itemQuantity;i++){
	                		System.out.println("Counter : " + counter);
	                		im = new itemModel();
	                		im.setItemDetailsModel(dao.getitemDetailsByID(itemDetailsID));
	                		im.setRfidModel(dao.getRFIDByID(counter));
	                		im.setTimeStamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
	                		data.add(im);
	                		counter++;
	                	}
	                	//System.out.println("data size : " +iterator.hasNext());
	                }
        		}
            }
            workbook.close();
            inputStream.close();
            return data;
        }
        
        public ArrayList<itemDetailsModel> getitemDetailsData() throws IOException{
        	inputStream = new FileInputStream(new File(excelFilePath));
        	workbook = new XSSFWorkbook(inputStream);
        	dataSheet = workbook.getSheetAt(1);
        	iterator = dataSheet.iterator();
        	dao = new DAOController();
        	ArrayList<itemDetailsModel> data = new ArrayList<itemDetailsModel>();
        	itemDetailsModel idm = null;
        	while (iterator.hasNext()) {
        		Row nextRow = iterator.next();
        		if(nextRow.getRowNum()==0){
        			continue; // skip header row
        		}
        		else{
	                Iterator<Cell> cellIterator = nextRow.cellIterator();
	                
	                while (cellIterator.hasNext()) {
	                	idm = new itemDetailsModel();
	                	int itemDiscountID = (int) cellIterator.next().getNumericCellValue();
	                	int itemVATPlanID = (int) cellIterator.next().getNumericCellValue();
	                	int itemCatID = (int) cellIterator.next().getNumericCellValue();
	                	idm.setDiscountModel(dao.getdicountByID(itemDiscountID));
	                	idm.setItemVATPlan(dao.getitemVATPlanByID(itemVATPlanID));
	                	idm.setItemCategory(dao.getitemCategory(itemCatID));
	                	data.add(idm);
	                }
        		}
            }
            workbook.close();
            inputStream.close();
            return data;
        }
        public ArrayList<itemTaxPlanModel> getVATPlanData() throws IOException{
        	inputStream = new FileInputStream(new File(excelFilePath));
        	workbook = new XSSFWorkbook(inputStream);
        	dataSheet = workbook.getSheetAt(3);
        	iterator = dataSheet.iterator();
        	ArrayList<itemTaxPlanModel> data = new ArrayList<itemTaxPlanModel>();
        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        	itemTaxPlanModel ivm = null;
        	while (iterator.hasNext()) {
        		Row nextRow = iterator.next();
        		if(nextRow.getRowNum()==0){
        			continue;
        		}
        		else{
	                Iterator<Cell> cellIterator = nextRow.cellIterator();
	                
	                while (cellIterator.hasNext()) {
	                	ivm = new itemTaxPlanModel();
	                	String taxPlanName =  cellIterator.next().getStringCellValue();
	                    double taxPerct =  cellIterator.next().getNumericCellValue();
	                    ivm.settaxPlanName(taxPlanName);
	                    ivm.settaxPercentage((double) taxPerct);
	                    data.add(ivm);
	                }
        		}
            }
            workbook.close();
            inputStream.close();
            return data;
        }
        public ArrayList<discountModel> getDiscountData() throws IOException{
        	inputStream = new FileInputStream(new File(excelFilePath));
        	workbook = new XSSFWorkbook(inputStream);
        	dataSheet = workbook.getSheetAt(2);
        	iterator = dataSheet.iterator();
        	ArrayList<discountModel> data = new ArrayList<discountModel>();
        	discountModel dm = null;
        	while (iterator.hasNext()) {
        		Row nextRow = iterator.next();
        		if(nextRow.getRowNum()==0){
        			continue;
        		}
        		else{
	                Iterator<Cell> cellIterator = nextRow.cellIterator();
	                
	                while (cellIterator.hasNext()) {
	                	dm = new discountModel();
	                	String discountDesc =  cellIterator.next().getStringCellValue();
	                    double discPerct =  cellIterator.next().getNumericCellValue();
	                    dm.setDiscontDescription(discountDesc);
	                    dm.setDiscount((double) discPerct);
	                    data.add(dm);
	                }
        		}
            }
            workbook.close();
            inputStream.close();
            return data;
        }
        public ArrayList<RFIDModel> getRFIDData() throws IOException{
        	inputStream = new FileInputStream(new File(excelFilePath));
        	workbook = new XSSFWorkbook(inputStream);
        	dataSheet = workbook.getSheetAt(4);
        	iterator = dataSheet.iterator();
        	dao = new DAOController();
        	ArrayList<RFIDModel> data = new ArrayList<RFIDModel>();
        	int rfidCount = dao.getLastMaxRFID("importRFIDData")+1;
        	System.out.println(rfidCount);
        	RFIDModel rfid = null;
        	while (iterator.hasNext()) {
        		Row nextRow = iterator.next();
        		if(nextRow.getRowNum()==0){
        			continue;
        		}
        		else if(nextRow.getRowNum() > rfidCount){
	                Iterator<Cell> cellIterator = nextRow.cellIterator();
	                while (cellIterator.hasNext()) {
	                	rfid = new RFIDModel();
	                    String rfidNum =  cellIterator.next().getStringCellValue();
	                    rfid.setRFID(rfidNum);
	                    data.add(rfid);
	                }
        		}
        		else{
        			continue;
        		}
            }
            workbook.close();
            inputStream.close();
            return data;
        }
        public ArrayList<itemCategoryModel> getCategoryData() throws IOException{
        	inputStream = new FileInputStream(new File(excelFilePath));
        	workbook = new XSSFWorkbook(inputStream);
        	dataSheet = workbook.getSheetAt(5);
        	iterator = dataSheet.iterator();
        	ArrayList<itemCategoryModel> data = new ArrayList<itemCategoryModel>();
        	itemCategoryModel ic = null;
        	while (iterator.hasNext()) {
        		Row nextRow = iterator.next();
        		if(nextRow.getRowNum()==0){
        			continue;
        		}
        		else{
	                Iterator<Cell> cellIterator = nextRow.cellIterator();
	                
	                while (cellIterator.hasNext()) {
	                	ic = new itemCategoryModel();
	                	String itemName =  cellIterator.next().getStringCellValue();
	                    String itemDesc =  cellIterator.next().getStringCellValue();
	                    double itemPrice = (double) cellIterator.next().getNumericCellValue();
	                	ic.setItemDesc(itemDesc);
	                	ic.setItemName(itemName);
	                	ic.setPrice(itemPrice);
	                    data.add(ic);
	                }
        		}
            }
            workbook.close();
            inputStream.close();
            return data;
        }
}
