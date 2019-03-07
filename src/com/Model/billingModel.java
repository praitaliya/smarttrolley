package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="billing_master")
public class billingModel {
	private long billID;
	private long transactionID;
	private String trolleyID;
	private int numItems;
	private double totalAmount;
	private double totalDiscount;
	private double totalTax;
	private double finalAmount;
	private String timeStamp;
	
	@Id
	@Column(name="billID")
	@GenericGenerator(name="abc",strategy="increment")
	@GeneratedValue(generator="abc")
	public long getBillID() {
		return billID;
	}
	public void setBillID(long billID) {
		this.billID = billID;
	}
	
	@Column(name="transactionID")
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	
	@Column(name="trolleyID")
	public String getTrolleyID() {
		return trolleyID;
	}
	public void setTrolleyID(String trolleyID) {
		this.trolleyID = trolleyID;
	}
	
	@Column(name="numItems")
	public int getNumItems() {
		return numItems;
	}
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	@Column(name="totalAmount")
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="totalDiscount")
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	@Column(name="totalTax")
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	@Column(name="finalAmount")
	public double getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
	@Column(name="timeStamp")
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
