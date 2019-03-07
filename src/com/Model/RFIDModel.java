package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="RFIDDetails")
public class RFIDModel {
	private int item_ID;
	private String RFID;
	
	@Id
	@Column(name="item_ID")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public int getItem_ID() {
		return item_ID;
	}
	public void setItem_ID(int item_ID) {
		this.item_ID = item_ID;
	}
	public String getRFID() {
		return RFID;
	}
	public void setRFID(String rFID) {
		RFID = rFID;
	}
	
	
}
