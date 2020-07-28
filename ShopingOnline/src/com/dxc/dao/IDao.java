package com.dxc.dao;

import java.util.List; 


import com.dxc.pojos.*;


public interface IDao 
{
	public String signUp(Admin a);
	public String signIn(Admin a);
	public String addProduct(Product p);
	public List<Product> displayProduct();
	public String addCustomer(Customer c);
	public String removeCustomer(int cnumber);
	public String createPassword(int cnumber,String pass);
	public boolean findCustomer(int num);
	public boolean verify(String cname,String password);
	public List<Product> returnProduct();
	public String addTocart(int[] pn,int cid,int[] cq);
	public List<Product> giveQuantity(int[] pn);
	public List<Product> displayCart(int cid);
	public List<Product> displayBill(int cid,int[] pn,int[] cq);
	public List<Product> payBill(int cid,int[] cq,int[] pn);
	public String addAmount(int cid,double am);
	public String getBill(int cid,int[] cq,int[] pn);

}
