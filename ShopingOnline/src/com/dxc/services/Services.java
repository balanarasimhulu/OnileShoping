package com.dxc.services;

import java.util.List;

import com.dxc.dao.Dao;
import com.dxc.dao.IDao;
import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;

public class Services implements IServices
{
	IDao iad=new Dao();
	public String signUp(Admin a)
	{
		return iad.signUp(a);
	}
	public String signIn(Admin a)
	{
		return iad.signIn(a);
	}
	public String addProduct(Product p)
	{
		return iad.addProduct(p);
	}
	public List<Product> displayProduct()
	{
		return iad.displayProduct();
	}
	public String addCustomer(Customer c)
	{
		return iad.addCustomer(c);
	}
	public String removeCustomer(int cnumber)
	{
		return iad.removeCustomer(cnumber);
	}
	public String createPassword(int cnumber,String pass)
	{
		return iad.createPassword(cnumber,pass);
	}
	public boolean findCustomer(int num)
	{
		return iad.findCustomer(num);
	}
	public boolean verify(String cname,String password)
	{
		return iad.verify(cname,password);
	}
	public List<Product> returnProduct()
	{
		return iad.returnProduct();
	}
	public String addTocart(int[] pn, int cid,int[] cq)
	{
		return iad.addTocart(pn,cid,cq);
	}
	public List<Product> giveQuantity(int[] pn)
	{
		return iad.giveQuantity(pn);
	}
	public List<Product> displayCart(int cid)
	{
		return iad.displayCart(cid);
	}
	public List<Product> displayBill(int cid,int[] pn,int[] cq)
	{
		return iad.displayBill(cid,pn,cq);
	}
	public List<Product> payBill(int cid,int[] cq,int[] pn)
	{
		return iad.payBill(cid,cq,pn);
	}
	public String addAmount(int cid,double am)
	{
		return iad.addAmount(cid,am);
	}
	public String getBill(int cid,int[] cq,int[] pn)
	{
		return iad.getBill(cid,cq,pn);
	}

}
