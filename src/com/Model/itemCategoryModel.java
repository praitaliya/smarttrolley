package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="itemCategory")
public class itemCategoryModel {
	private int catID;
	private String itemName;
	private String itemDesc;
	private double price;
	
	@Id
	@Column(name="catID")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public int getCatID() {
		return catID;
	}
	public void setCatID(int catID) {
		this.catID = catID;
	}
	@Column(name="itemName")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name="itemDesc")
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	@Column(name="itemPrice")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}
