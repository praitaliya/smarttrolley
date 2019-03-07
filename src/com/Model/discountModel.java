package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="discountDetails")
public class discountModel {
	private int discountID;
	private String discontDescription;
	private double discount;
	
	@Id
	@Column(name="discountID")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public int getDiscountID() {
		return discountID;
	}
	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}
	public String getDiscontDescription() {
		return discontDescription;
	}
	public void setDiscontDescription(String discontDescription) {
		this.discontDescription = discontDescription;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}
