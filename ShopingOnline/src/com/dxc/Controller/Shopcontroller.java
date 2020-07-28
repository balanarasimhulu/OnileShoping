package com.dxc.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Product;
import com.dxc.services.Services;
import com.dxc.services.IServices;

@Controller
@RequestMapping("/views")
public class Shopcontroller 
{
	IServices ias=new Services();
	List<Product> Plist=new ArrayList<>();
	String message;
	@RequestMapping("/signup")
	public String signUp(@ModelAttribute Admin a,Model m,HttpSession session)
	{
		
		
		String s=ias.signUp(a);
		m.addAttribute("message",s);
		return "result";
	}
	@RequestMapping("/signin")
	public String signIn(@ModelAttribute Admin a,Model m)
	{
		String s= ias.signIn(a);
	
		return "adminoperation";
	}
	@RequestMapping("/addproduct")
	public String addProduct(@ModelAttribute Product p,Model m)
	{
		if(p.getPprice()>50)
		{
			p.setPdiscount(p.getPprice()/5);
		}
		else
		{
			p.setPdiscount(0.0);
		}
		String s=ias.addProduct(p);
		m.addAttribute("message",s);
		return "view";
		
	}
	@RequestMapping("/display")
	public String displayProduct(Model m)
	{
		System.out.println("hai");
		Plist=ias.displayProduct();
		if(Plist.isEmpty())
		{
			message ="Their are no items in shop to display";
			m.addAttribute("message",message);
			return "view";
		}
		else
		{
		m.addAttribute("list",Plist);
		return "tableproduct";
		}
	}
	@RequestMapping("/addcustomer")
	public String addCustomer(@RequestParam int cnumber,@ModelAttribute Customer c,Model m)
	{
		c.setId(cnumber);
		
		System.out.println(c);
		String s=ias.addCustomer(c);
		m.addAttribute("message",s);
		return "view";
	}
	@RequestMapping("/removecustomer")
	public String removeCustomer(@RequestParam int cnumber,Model m)
	{
		String s=ias.removeCustomer(cnumber);
		m.addAttribute("message",s);
		return "view";
	}
	@RequestMapping("/password")
	public String createPassword(@RequestParam int cnumber,@RequestParam String password,@RequestParam String password1,Model m)
	{
		System.out.println(password);
		System.out.println(password1);
		System.out.println(password.equals(password1));
		if(password.equals(password1))
		{
			String s=ias.createPassword(cnumber,password);
			m.addAttribute("message", s);
			return "view";
		}
		else
		{

			message="reenter password incorrect";
			m.addAttribute("message",message);
			return "view";
		
		}
	}

	
	@RequestMapping("/findpassword")
	public String findCustomer(@RequestParam int cnumber,Model m,HttpSession session)
	{
		session.setAttribute("customerid", cnumber);
		boolean s=ias.findCustomer(cnumber);
		if(s==false)
		{
			return "createpassword";
		}
		else
		{
			return "enterpassword";
		}
	}

	@RequestMapping("/clogin")
	public String enterPassword(@RequestParam String cname,@RequestParam String password,Model m,HttpSession session)
	{
		
		
		boolean s=ias.verify(cname,password);
		if(s==true)
		{
			return "customeroperation";
		}
		else
		{
			message="invalid details";
			m.addAttribute("message",message);
			return "view";
		}
	}
	@RequestMapping("/select")
	public  String returnProducts(Model m)
	{
		Plist =	ias.returnProduct();
		if(Plist.isEmpty())
		{
			message ="Their are no items in shop to display";
			m.addAttribute("message",message);
			return "view";
		}
		else
		{
		m.addAttribute("list",Plist);
		return "producttable";
		}
	
		
	}
	@RequestMapping("/takeproduct")

	public String takeProducts(Model m,@RequestParam(value="cquantity") int[] cq,HttpSession session)
	{
		int[] pn=(int[])session.getAttribute("cunum");
		int cid=(int)session.getAttribute("customerid");
		session.setAttribute("cusquan", cq);
		for(int i=0;i<cq.length;i++)
		{			
			System.out.println( "product number" +"  "+cq[i]);
		}	
		
		String s=ias.addTocart(pn,cid,cq);
		m.addAttribute("message",s);
		return "view";
	}
	@RequestMapping("/takequantity")
	public String takeQuantities(Model m,@RequestParam(value="checknumber") int[] pn,
			HttpSession session)
	{
		session.setAttribute("cunum", pn);
		for(int i=0;i<pn.length;i++)
		{			
			System.out.println( "product number" +"  "+pn[i]);
		}	
		Plist=ias.giveQuantity(pn);
		if(Plist.isEmpty())
		{
			message ="Their are no items in shop to purchase";
			m.addAttribute("message",message);
			return "view";
		}
		else
		{
		m.addAttribute("list",Plist);
		return "addtocart";
		}	
	}
	@RequestMapping("/displaycart")
	public String displayCart(HttpSession session,Model m)
	{
		int cid=(int)session.getAttribute("customerid");
		Plist=ias.displayCart(cid);
		session.setAttribute("checklist", Plist);
		if(Plist.isEmpty())
		{
			message ="Their are no items in cart to display";
			m.addAttribute("message",message);
			return "view";
		}
		else
		{
		m.addAttribute("list",Plist);
		return "displaycart";
		}
	}
	@RequestMapping("/displaybill")
	public String displayBill(HttpSession session,Model m)
	{
		
		int[] cq=(int[])session.getAttribute("cusquan");
		int[] pn=(int[])session.getAttribute("cunum");
		int cid=(int)session.getAttribute("customerid");
		List<Product> li=(List<Product>)session.getAttribute("checklist");
		if(li.isEmpty())
		{
			message ="Their are no items in cart to displaybill";
			m.addAttribute("message",message);
			return "view";
		}
		else
		{
			Plist=ias.displayBill(cid,cq,pn);
		m.addAttribute("list",Plist);
		return "displaybill";
		}
	}
	@RequestMapping("/paybill")
	public String payBill(HttpSession session,Model m)
	{
		int[] cq=(int[])session.getAttribute("cusquan");
		int[] pn=(int[])session.getAttribute("cunum");
		int cid=(int)session.getAttribute("customerid");
		List<Product> li=(List<Product>)session.getAttribute("checklist");
		
		if(li.isEmpty())
		{
			message ="Their are no items in cart to purchase";
			m.addAttribute("message",message);
			return "view";
		}
		else
		{
			Plist=ias.payBill(cid,cq,pn);
		m.addAttribute("list",Plist);
		return "paybill";
		}
	}
	@RequestMapping("/addamount")
	public String addAmount(HttpSession session,Model m,@RequestParam(value="cbalance") double am)
	{
		System.out.println(am);
		int cid=(int)session.getAttribute("customerid");
		message=ias.addAmount(cid,am);
		m.addAttribute("message",message);
		return "view";
	}
	@RequestMapping("getbill")
	public String getBill(HttpSession session,Model m)
	{

		int[] cq=(int[])session.getAttribute("cusquan");
		int[] pn=(int[])session.getAttribute("cunum");
		int cid=(int)session.getAttribute("customerid");
		message=ias.getBill(cid,cq,pn);
		m.addAttribute("message",message);
		
		return "view";
		
		
	}
	
	
	
	

}
