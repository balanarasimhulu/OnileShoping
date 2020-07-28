package com.dxc.dao;


import java.util.*; 

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dxc.pojos.*;


public class Dao implements IDao
{
	List<Admin> Alist=new ArrayList<>();
	List<Customer> Clist= new ArrayList<>();
	Product product=new Product();
	Customer customer=new Customer();
	List<Product> plist=new ArrayList<>();
	List<Product> list=new ArrayList<>();
	private static SessionFactory sessionfactory;
	static 
	{
		Configuration configuration=new Configuration().configure();
		sessionfactory=configuration.buildSessionFactory();	
	}

	public String signUp(Admin a)
	{

		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(a);
		session.getTransaction().commit();
		session.close();
		return "account added";
		
	}
	public String signIn(Admin a)
	{
		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from Admin");
		Alist=query.getResultList();
		for (Admin alist : Alist) 
		{
			if(a.getUsername().equalsIgnoreCase(alist.getUsername())&&a.getPassword().equalsIgnoreCase(alist.getPassword()))
			{
				return "Admin login Successful";	
			}
		}
		session.close();
		return "Admin login failed";
		
	}
	public String addProduct(Product p)
	{
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		return "product added";
		
		
	}
	public List<Product> displayProduct()
	{

		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from Product");
		return query.getResultList();
		
	}
	public String addCustomer(Customer c)
	{
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		System.out.println(c);
		session.save(c);
		session.getTransaction().commit();
		session.close();
		return "CUstomer added";
		
	}
	public String removeCustomer(int id)
	{
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("delete from Customer where cnumber=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return "Customer removed";
		
	}
	public String createPassword(int cnumber,String password)
	{
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("update Customer set password=:password where cnumber=:cnumber");
		query.setParameter("password", password);
		query.setParameter("cnumber", cnumber);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return "password created";
		
	}
	public boolean findCustomer(int cnumber)
	{
		System.out.println(cnumber);

		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from Customer where cnumber=:cnumber");
		query.setParameter("cnumber", cnumber);
		Clist=query.getResultList();
		System.out.println(Clist.get(0).getPassword());
		for(int i=0;i<Clist.size();i++)
		{
			if(Clist.get(i).getPassword()==null)
			{
				return false;
			}
		}
		session.close();
		return true;
	}
	public boolean verify(String cname,String password)
	{
		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from Customer");
		Clist=query.getResultList();
		for (Customer c: Clist) 
		{
			if(c.getCname().equals(cname)&&c.getPassword().equals(password))
			{
				return true;	
			}
		}
		session.close();
		return false;
		
	}
	public List<Product> returnProduct()
	{
		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from Product");
		plist=query.getResultList();
		session.close();
		return plist;
		
	}
	public String addTocart(int[] pn,int cid,int[] cq)
	{
		List<Product> list=new ArrayList<>();
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		customer=session.get(Customer.class, cid);
		
		System.out.println(customer);
		
		product.getClist().add(customer);
		for(int i=0;i<pn.length;i++)
		{
		Product p=(Product)session.get(Product.class,pn[i]);
		System.out.println(p);
			boolean b=customer.getPlist().add(p);
			System.out.println(b);	
		}
		for(int i=0;i<pn.length;i++)
		{
			int a=pn[i];
			
		Query query=session.createQuery("from Product where pnumber=:a");
		query.setParameter("a",a);
		list=query.getResultList();
		int b=list.get(0).getPquantities()-cq[i];
		System.out.println(b);
		Query query1=session.createQuery("update Product set pquantities=:b where pnumber=:a");
		query1.setParameter("a",a);		
		query1.setParameter("b",b);		
		query1.executeUpdate();
		}		
		session.getTransaction().commit();
		session.close();
		System.out.println(list);
		return "Added to cart...";
	}
	public List<Product> giveQuantity(int[] pn)
	{
		List<Product> list=new ArrayList<>();
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		for(int i=0;i<pn.length;i++)
		{
		Product p=(Product)session.get(Product.class,pn[i]);
		System.out.println(p);
			list.add(p);	
		}
		session.getTransaction().commit();
		session.close();
			return list;
	}
	public List<Product> displayCart(int cid)
	{
		List<Product> list=new ArrayList<>();
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		customer=session.get(Customer.class, cid);
		System.out.println(customer);
		if(customer!=null)
		{
			list=customer.getPlist();
			System.out.println(list);
		}
		session.close();
		return list;
	}
	public List<Product> displayBill(int cid,int[] pn,int[] cq)
	{
		List<Product> list=new ArrayList<>();
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		customer=session.get(Customer.class, cid);
		
		if(customer!=null)
		{
			list=customer.getPlist();
		}
		//session.detach(list);
		for(int i=0;i<pn.length;i++)
		{
			int a=list.get(i).getPnumber();
			if(a==cq[i])
			{
				
				
				System.out.println(list);
			}
			
		}
		session.getTransaction().commit();
		session.close();
		return list;
		
	}
	public List<Product> payBill(int cid,int[] cq,int[] pn)
	{
		double total=0;
		List<Product> list=new ArrayList<>();
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		customer=session.get(Customer.class, cid);
	
		if(customer!=null)
		{
			list=customer.getPlist();
		}
		
		for(int i=0;i<pn.length;i++)
		{
			int a=list.get(i).getPnumber();
			if(a==pn[i])
			{
				double cash=list.get(i).getPprice();
				System.out.println(cash);
				double disc=list.get(i).getPdiscount();
				cash=cash-disc;
				cash=cash*cq[i];
				total=total+cash;
				
				
				System.out.println(cash);
				System.out.println(total);
			}
		}
		
		
		System.out.println(total);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public String addAmount(int cid,double am)
	{
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Customer where cnumber=:a");
		query.setParameter("a",cid);
		Clist=query.getResultList();
		double a=Clist.get(0).getCbalance();
		am=a+am;
		Query query1=session.createQuery("update Customer  set cbalance=:am where cnumber=:cnumber");
		query1.setParameter("cnumber", cid);
		query1.setParameter("am", am);
		query1.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		return "moneyadded";
		
	}
	public String getBill(int cid,int[] cq,int[] pn)
	{
		double total=0;
		double cbal;
		List<Product> list=new ArrayList<>();
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		customer=session.get(Customer.class, cid);
		System.out.println(pn[0]);
		if(customer!=null)
		{
			list=customer.getPlist();
		}

		for(int i=0;i<pn.length;i++)
		{
			int a=list.get(i).getPnumber();
			System.out.println(a);
			
			if(a==pn[i])
			{
				double cash=list.get(i).getPprice();
				System.out.println(cash);
				double disc=list.get(i).getPdiscount();
				cash=cash-disc;
				cash=cash*cq[i];
				total=total+cash;
				System.out.println(cash);
				System.out.println(total);
			}
		}
		cbal=customer.getCbalance();
		System.out.println(cbal);
		if(cbal<total)
		{
			list.clear();
			session.getTransaction().commit();
			session.close();
			return "Insufficient Balance";
		}
		else
		{
		cbal=cbal-total;
		System.out.println(cbal);
		customer.setCbalance(cbal);
		System.out.println(total);
		list.clear();
		session.getTransaction().commit();
		session.close();
		return "Billpaid";
		}
		
		
	}
	
	
	

}
