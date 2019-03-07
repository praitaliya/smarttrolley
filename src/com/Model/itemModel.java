package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="items")
public class itemModel {
	private int item_ID;
	private itemDetailsModel itemDetailsModel;
	private RFIDModel rfidModel;
	private int isSold;
	private String timeStamp;
	
	@Id
	@Column(name="itemID")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public int getItem_ID() {
		return item_ID;
	}
	public void setItem_ID(int item_ID) {
		this.item_ID = item_ID;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="itemDetailsID")
	public itemDetailsModel getItemDetailsModel() {
		return itemDetailsModel;
	}
	public void setItemDetailsModel(itemDetailsModel itemDetailsModel) {
		this.itemDetailsModel = itemDetailsModel;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="item_ID")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public RFIDModel getRfidModel() {
		return rfidModel;
	}
	public void setRfidModel(RFIDModel rfidModel) {
		this.rfidModel = rfidModel;
	}
	
	@Column(name="isSold")
	@ColumnDefault("0")
	public int getIsSold() {
		return isSold;
	}
	public void setIsSold(int isSold) {
		this.isSold = isSold;
	}
	
	@Column(name="timeStamp")
	@ColumnDefault("NULL")
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
