package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TaxPlan")
public class itemTaxPlanModel {
	private int taxPlanId;
	private String taxPlanName;
	private double taxPercentage;
	
	@Id
	@Column(name="taxPlanId")
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	public int gettaxPlanId() {
		return taxPlanId;
	}
	public void settaxPlanId(int taxPlanId) {
		this.taxPlanId = taxPlanId;
	}
	public String gettaxPlanName() {
		return taxPlanName;
	}
	public void settaxPlanName(String taxPlanName) {
		this.taxPlanName = taxPlanName;
	}
	public double gettaxPercentage() {
		return taxPercentage;
	}
	public void settaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
}
