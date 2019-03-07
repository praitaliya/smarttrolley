package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="itemDetails")
public class itemDetailsModel {
	private int itemDetailsID;
	private discountModel discountModel;
	private itemTaxPlanModel itemTaxPlan;
	private itemCategoryModel itemCategory;
	
	@Id
	@Column(name="itemDetailsID")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public int getItemDetailsID() {
		return itemDetailsID;
	}
	public void setItemDetailsID(int itemDetailsID) {
		this.itemDetailsID = itemDetailsID;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="catID")
	public itemCategoryModel getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(itemCategoryModel itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="discountID")
	public discountModel getDiscountModel() {
		return discountModel;
	}
	public void setDiscountModel(discountModel discountModel) {
		this.discountModel = discountModel;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="taxPlanId")
	public itemTaxPlanModel getItemVATPlan() {
		return itemTaxPlan;
	}
	public void setItemVATPlan(itemTaxPlanModel itemVATPlan) {
		this.itemTaxPlan = itemVATPlan;
	}
	
	
}
