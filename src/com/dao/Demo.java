package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.Model.CustomerModel;
// command line tool for data import from excel file and testing 
public class Demo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		interfaceTrolley dao = new DAOController();
		
		boolean mainloop = true;
		while(mainloop)
		{
			System.out.println("");
			System.out.println("Enter 1 ->> Customer Use");
			System.out.println("Enter 2->> Admin Use");
			System.out.println("Enter 3 ->> Exit");
			System.out.println("Please Enter Your Choice :");
			int ch = sc.nextInt();
			switch (ch) {
				case 1:
					boolean firstloop = true;
					while(firstloop)
					{
						System.out.println("");
						System.out.println("Enter 1 ->> Enter Mall");
						System.out.println("Enter 2->> Generate Bill");
						System.out.println("Enter 3 ->> Exit");
						System.out.println("Please Enter Your Choice :");
						int innerch = sc.nextInt();
						switch (innerch) {
							case 1:
								System.out.println("Please Enter First Name : ");
								String fname = sc.next();
								System.out.println("Please Enter Last Name : ");
								String lname = sc.next();
								int isValidTrolley = 0;
								do{
									System.out.println("Please Enter Trolley ID : ");
									String trolley_id = sc.next();
									interfaceTrolley dao1 = new DAOController();
									isValidTrolley = dao1.validateTrolley(trolley_id);
									if(isValidTrolley==0){
										int i = dao.updateTrolley(trolley_id);
										if(i==1){
											CustomerModel cm = new CustomerModel();
											cm.setFname(fname);
											cm.setLname(lname);
											cm.setTrolley_id(trolley_id);
											cm.setTimeStamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
											int j = dao.insertCustomer(cm);
											if(j==1){
												System.out.println("Hello " + fname + " "  + lname + "! Please Enjoy Your Shopping with Us.");
												isValidTrolley = 3;
											}
											else{
												System.out.println("Please try again! Something went wrong.");
												isValidTrolley = 0;
												break;
											}
										}
										else{
											System.out.println("Please try again! Something went wrong.");
											isValidTrolley = 0;
											break;
										}
									}	
									else if(isValidTrolley==1){
										System.out.println("Trolley is already in use, please enter correct trollet id : ");
										isValidTrolley = 0;
									}
									else{
										System.out.println("Please try again! Something went wrong.");
										isValidTrolley = 0;
										break;
									}
								}while(isValidTrolley!=3);
							break; 
							case 2:
								System.out.println("Please Enter Trolley ID : ");
								String trolley_id = sc.next();
								int i = dao.billGenerate(trolley_id);
								if(i>0){
									System.out.println("Success");
								}
								else{
									System.out.println("Something went wrong");
								}
							break;
							case 3:
								firstloop = false;
							break;
						}
					}
				break; 
				case 2:
					boolean secondloop = true;
					while(secondloop)
					{
						System.out.println("");
						System.out.println("Enter 1 ->> Import Data");
						System.out.println("Enter 2->> Display Data");
						System.out.println("Enter 3 ->> Exit");
						System.out.println("Please Enter Your Choice :");
						int innerch = sc.nextInt();
						switch (innerch) {
							case 1:
								dao.importitemCatData();
								dao.importdiscountData();
								dao.importTaxData();
								dao.importRFIDData();
								dao.importitemDetailsData();
								dao.importItemsData();
							break; 
							case 2:
								//dao.get
							break;
							case 3:
								secondloop = false;
							break;
						}
					}
				break;
				case 3:
					mainloop = false;
				break;
			}
		}
		//td.addtoDatabase(im,idm,dm,ivm,rfm);
	}
}
