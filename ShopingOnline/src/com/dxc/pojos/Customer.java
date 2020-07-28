package com.dxc.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

@Entity

public class Customer 
{
	@Id
	private int cnumber;
	private String cname;
	private String password;
	private double cbalance;
	@ManyToMany
	@JoinTable(name="Cart_details",
	joinColumns = @JoinColumn(name="Customer_id"),
	inverseJoinColumns = @JoinColumn(name="Product_id"))
	private List<Product> Plist=new ArrayList<>();
	
		
	public Customer()
	{
		
	}

	public Customer(int id, String cname, String password, double cbalance) {
		super();
		this.cnumber = id;
		this.cname = cname;
		this.password = password;
		this.cbalance = cbalance;
	}
	

	public int getcnumber() {
		return cnumber;
	}

	public void setId(int id) {
		this.cnumber = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCbalance() {
		return cbalance;
	}

	public void setCbalance(double cbalance) {
		this.cbalance = cbalance;
	}
	

	public int getCnumber() {
		return cnumber;
	}

	public void setCnumber(int cnumber) {
		this.cnumber = cnumber;
	}
	public List<Product> getPlist() {
		return Plist;
	}

	public void setPlist(List<Product> plist) {
		Plist = plist;
	}
	@Override
	public String toString() {
		return "Customer [cnumber=" + cnumber + ", cname=" + cname + ", password=" + password + ", cbalance=" + cbalance
				+ "]";
	}
	
	
	

}
