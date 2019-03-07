package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="transaction_details")
public class transactionModel {
	private int id;
	private long transaction_id;
	private int Item_ID;
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
	@Column(name="itemID")
	public int getItem_ID() {
		return Item_ID;
	}
	public void setItem_ID(int item_ID) {
		Item_ID = item_ID;
	}
	@Column(name="timeStamp")
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
