package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.ConnectionDB;
import Pojo.AuthPojo;
import Pojo.AuthPojo2;
import Pojo.MyCartPojo;

public class MyCartDao {

	public static boolean isAdminValid(AuthPojo userDetails)
	{
		boolean validStatus = false;
		try
		{
			Connection conn = ConnectionDB.getConnection();
			Statement st= conn.createStatement();

			ResultSet rs= st.executeQuery("SELECT * FROM MyCart.Adminlogin WHERE AdminId "
					+ "= '"+userDetails.getAdminId()
			+"' AND AdminPass = '"+userDetails.getAdminPass()+"'");
			while(rs.next())
			{
				validStatus = true;
			}
			
			ConnectionDB.closeConnection(conn);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return validStatus;
	}
	
	public static boolean isUserValid(AuthPojo2 userDetails)
	{
		boolean validStatus = false;
		try
		{
			Connection conn = ConnectionDB.getConnection();
			Statement st= conn.createStatement();

			ResultSet rs= st.executeQuery("SELECT * FROM MyCart.Userlogin WHERE UserId "
					+ "= '"+userDetails.getUserId()
			+"' AND Password = '"+userDetails.getPassword()+"'");
			while(rs.next())
			{
				validStatus = true;
			}
			
			ConnectionDB.closeConnection(conn);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return validStatus;
	}
	
	
	
	public static List<MyCartPojo> getAllProducts()
	{
		List<MyCartPojo> prodList = new ArrayList<MyCartPojo>();
		try
		{
			
			Connection conn = ConnectionDB.getConnection();
			
			Statement st= conn.createStatement();
			ResultSet rs= st.executeQuery("SELECT prodId,prodName,prodCategory,prodPrice FROM ProductList");
			while(rs.next())
			{
				MyCartPojo prod = new MyCartPojo(rs.getInt("prodId"),rs.getString("prodName"),
				rs.getString("prodCategory"),rs.getString("prodPrice"));
				prodList.add(prod);
			}
			
			ConnectionDB.closeConnection(conn);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return prodList;
	}
	
	public static MyCartPojo getProdById(int prodId)
	{
		MyCartPojo product = null;
		try
		{
			Connection conn = ConnectionDB.getConnection();
			PreparedStatement ps= conn.prepareStatement("SELECT * FROM ProductList WHERE prodId = ?");
			ps.setInt(1, prodId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				 product = new MyCartPojo(rs.getInt("prodId"),rs.getString("prodName"),
						rs.getString("prodCategory"),rs.getString("prodPrice"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return product;
	}
	public static List<MyCartPojo> readCart()
	{
		
		System.out.println("Items in Cart :");
		
		List<MyCartPojo> prodList = new ArrayList<MyCartPojo>();
		try
		{
			Connection conn = ConnectionDB.getConnection();
			PreparedStatement ps= conn.prepareStatement("SELECT * FROM UserCart");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				MyCartPojo prod = new MyCartPojo(rs.getInt("prodId"),rs.getString("prodName"),
						rs.getString("prodCategory"),rs.getString("prodPrice"));
					prodList.add(prod);
			}
			System.out.println("Id  Name  Category  Price");
			for(MyCartPojo product:prodList ) {
				
				System.out.println( product.getProdId() +" , "+
						product.getProdName() +" , "+
						product.getProdCategory() +" , "+
						product.getProdPrice() );
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return prodList;
	}
	public static boolean delProdByIdFromCart(int prodId)
	{
		
		try
		{
			Connection conn = ConnectionDB.getConnection();
			PreparedStatement ps= conn.prepareStatement("delete FROM UserCart WHERE prodId = ? limit 1");
			ps.setInt(1, prodId);
			boolean status= ps.execute();
			if(status) {
				System.out.println("Product removed from cart");
			}else {
				System.out.println("plz select valid product id from Cart");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	public static void emptyCart() {
		try
		{
			Connection conn = ConnectionDB.getConnection();
			 Statement stmt = conn.createStatement();
			 stmt.executeUpdate("delete  from UserCart");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Failed  to empty cart");
		}
	}
	public static void addIntoCart(MyCartPojo product)
	{
		String status = "No Product added";
		try
		{
			Connection conn = ConnectionDB.getConnection();
			PreparedStatement ps= conn.prepareStatement("insert into UserCart values (?,?,?,?)");
			ps.setInt(1, product.getProdId());
			ps.setString(2, product.getProdName());
			ps.setString(3, product.getProdCategory());
			ps.setString(4, product.getProdPrice());
			int res = ps.executeUpdate();
			
			if(res==1) {
			status = "Product added";}
			else {
				status = "No Product added";
				}
			System.out.println(status);
		}
		catch(Exception e)
		{
			System.out.println("plz select valid product id");
		}
	}
	
	public static void billing(String Uname) {
		
		try
		{
			int discount=0;
			Connection conn = ConnectionDB.getConnection();
			PreparedStatement ps= conn.prepareStatement("select sum(prodprice) as total from userCart");
			ResultSet rs = ps.executeQuery();
			Double TotalBilling=0.0;
			while(rs.next()) {
			TotalBilling=Double.parseDouble(rs.getString("total"));
			}
			ps= conn.prepareStatement("select prodId from userCart");
			rs=ps.executeQuery();
			
			String prodIds="";
			
			while(rs.next()) {
			prodIds=prodIds.concat(", "+rs.getString("prodId"));
			}		
			if(TotalBilling>=10000) {
				discount=500;
				TotalBilling -= 500;
			}
			
			System.out.println("Total Payable amount is rs.: " + TotalBilling + " and you have saved rs."+discount);
			
			ps= conn.prepareStatement("Insert into billing_backup (User,billing_items,toatl_bill) values (?,?,?)");
			ps.setString(1, Uname);
			ps.setString(2, prodIds);
			ps.setDouble(3, TotalBilling);
			int res = ps.executeUpdate();
			
			if(res==1) {
			System.out.println("Thank you for Shopping");
			}
			else {
				System.out.println("Failed to update Billing Backup table ");
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
