package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="customers")
public class CustomerDetails
{
  private int custID;
  private String name;
  private String contact;
  private String password;
  
  @Id
  @Column(name="customerID")
  @GenericGenerator(name="increment", strategy="increment")
  @GeneratedValue(generator="increment")
  public int getCustID()
  {
    return this.custID;
  }
  
  public void setCustID(int custID)
  {
    this.custID = custID;
  }
  
  @Column(name="name")
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  @Column(name="password")
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  @Column(name="contact")
  public String getContact()
  {
    return this.contact;
  }
  
  public void setContact(String contact)
  {
    this.contact = contact;
  }
}
