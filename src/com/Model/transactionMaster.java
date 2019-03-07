package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="transaction_master")
public class transactionMaster {
	private int id;
	private long transaction_id;
	private String trolleyID;
	private String item_Name;
	private double item_price;
	private double VATPercentage;
	private double discount;
	private String RFID;
	private String timeStamp;
	
	@Id
	@Column(name="id")
	@GenericGenerator(name="abc",strategy="increment")
	@GeneratedValue(generator="abc")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="transaction_id")
	public long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}
	@Column(name="timeStamp")
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Column(name="itemName")
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}
	@Column(name="itemPrice")
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	@Column(name="VAT")
	public double getVATPercentage() {
		return VATPercentage;
	}
	public void setVATPercentage(double vATPercentage) {
		VATPercentage = vATPercentage;
	}
	@Column(name="discount")
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@Column(name="RFID")
	public String getRFID() {
		return RFID;
	}
	public void setRFID(String rFID) {
		RFID = rFID;
	}
	@Column(name="trolleyID")
	public String getTrolleyID() {
		return trolleyID;
	}
	public void setTrolleyID(String trolleyID) {
		this.trolleyID = trolleyID;
	}
	
}
