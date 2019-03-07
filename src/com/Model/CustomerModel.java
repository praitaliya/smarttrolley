package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="transcation_customer_details")
public class CustomerModel {
	private long transaction_id;
	private String fname;
	private String lname;
	private String trolley_id;
	private String timeStamp;
	private int isBillGenerated;
	
	@Id
	@Column(name="TransactionID")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}
	@Column(name="fname")
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	@Column(name="lname")
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Column(name="TrolleyID")
	public String getTrolley_id() {
		return trolley_id;
	}
	public void setTrolley_id(String trolley_id) {
		this.trolley_id = trolley_id;
	}
	
	@Column(name="timeStamp")
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@ColumnDefault("0")
	@Column(name="isBilled")
	public int isBillGenerated() {
		return isBillGenerated;
	}
	public void setBillGenerated(int isBillGenerated) {
		this.isBillGenerated = isBillGenerated;
	}
}
