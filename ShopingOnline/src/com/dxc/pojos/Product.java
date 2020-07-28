package com.dxc.pojos;

import java.util.ArrayList; 
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.*;



@Entity
@Table(name="producttable")

public class Product 
{
	@Id
	private int pnumber;
	private String pname;
	private double pprice;
	private int pquantities;
	private double pdiscount;
	
	
	
	@ManyToMany(mappedBy = "Plist")
	private List<Customer> Clist =new ArrayList<>();
	
	public Product()
	{
		
	}
	
	public Product(int pnumber, String pname, double pprice, int pquantities, double pdiscount, int pnumber1,
			int quantity) {
		super();
		this.pnumber = pnumber;
		this.pname = pname;
		this.pprice = pprice;
		this.pquantities = pquantities;
		this.pdiscount = pdiscount;
	}
	
	

	public int getPnumber() {
		return pnumber;
	}
	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPprice() {
		return pprice;
	}
	public void setPprice(double pprice) {
		this.pprice = pprice;
	}
	public int getPquantities() {
		return pquantities;
	}
	public void setPquantities(int pquantities) {
		this.pquantities = pquantities;
	}
	public double getPdiscount() {
		return pdiscount;
	}
	public void setPdiscount(double pdiscount) {
		this.pdiscount = pdiscount;
	}
	public List<Customer> getClist() {
		return Clist;
	}

	public void setClist(List<Customer> clist) {
		Clist = clist;
	}

	@Override
	public String toString() {
		return "Product [pnumber=" + pnumber + ", pname=" + pname + ", pprice=" + pprice + ", pquantities="
				+ pquantities + ", pdiscount=" + pdiscount + "]";
	}
	
	
	

}
