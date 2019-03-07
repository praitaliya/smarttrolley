package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TrolleyDetails")
public class TrolleyModel {
	private String TrolleyID;
	private int isTrolleyused;
	private int isTransactionCompelted;
	
	
	@Id
	@Column(name="TrolleyID")
	@GenericGenerator(name="abc",strategy="assigned")
	@GeneratedValue(generator="abc")
	public String getTrolleyID() {
		return TrolleyID;
	}
	public void setTrolleyID(String trolleyID) {
		TrolleyID = trolleyID;
	}
	
	@ColumnDefault("0")
	@Column(name="isUsed")
	public int isTrolleyused() {
		return isTrolleyused;
	}
	public void setTrolleyused(int isTrolletyused) {
		this.isTrolleyused = isTrolletyused;
	}
	
	@ColumnDefault("1")
	@Column(name="isTransactionCompelted")
	public int isTransactionCompelted() {
		return isTransactionCompelted;
	}
	public void setTransactionCompelted(int isTransactionCompelted) {
		this.isTransactionCompelted = isTransactionCompelted;
	}
}