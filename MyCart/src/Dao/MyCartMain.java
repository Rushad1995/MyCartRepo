package Dao;

import java.util.List;
import java.util.Scanner;

import Pojo.AuthPojo;
import Pojo.AuthPojo2;
import Pojo.MyCartPojo;

public class MyCartMain {

	public static void main(String[] args) {
		
		String role="";
		String admin="Admin";
		String user="User";
		while (!(role==admin) && !(role==user)) {
			
		
		System.out.println("Welcome to MyCart");
		System.out.print("Login (User/Admin) :");
		Scanner sc = new Scanner(System.in);
		 role = sc.nextLine();
		 System.out.println("Check"+role);
		
		if (role.equalsIgnoreCase(admin)){
			
			System.out.println("Admin Authentication ");
			System.out.println("Admin window is not devloped yet");
			System.out.print("Enter Login Id :");
			Scanner sc1 = new Scanner(System.in);
			String loginId = sc1.nextLine();
			System.out.print("Enter Password :");
			Scanner sc2 = new Scanner(System.in);
			String password = sc2.nextLine();
			
			if(MyCartDao.isAdminValid(new AuthPojo(loginId,password))) {
				System.out.println("Show Admin Window");
			} else {
				System.out.println(" Admin Login Failed");
				System.out.println("Please try again");
			}
			sc1.close();
			sc2.close();
			
		} else if (role.equalsIgnoreCase(user)) {
			
			System.out.println("User Authentication ");
			System.out.println("Login Id=Ajay, and Password=Ajay123 Just for Referance");
			System.out.print("Enter Login Id :");
			Scanner Uname = new Scanner(System.in); 
			String userId = Uname.nextLine();
			System.out.print("Enter Password :");
			Scanner Upass = new Scanner(System.in);
			String password = Upass.nextLine();
			if (MyCartDao.isUserValid(new AuthPojo2(userId,password))) { 		
			MyCartDao.emptyCart();		
			boolean flag=true;
			int prodId;
			while(flag)
			{
				showContent();
				MyCartDao.readCart();
				System.out.println("Add items in Cart");
				@SuppressWarnings("resource")
				Scanner read = new Scanner(System.in);
				System.out.println("Select Product Id: or 0 for billing and -1 to modify cart");
				prodId =read.nextInt();
				if(prodId!=0 && prodId!=-1) {
				MyCartPojo product = MyCartDao.getProdById(prodId);
				MyCartDao.addIntoCart(product);
				}else if(prodId==-1) {
					showContent();
					MyCartDao.readCart();
					System.out.println("Enter product Id to Remove from cart");
					prodId =read.nextInt();
					MyCartDao.delProdByIdFromCart(prodId);
				}else if(prodId==0) {
					MyCartDao.billing(userId);
					flag=false;
				}
				
			}	
			}
			else {
				System.out.println(" User Login Failed");
				System.out.println("Please try again");
				
			}
		}
		
	else {
			System.out.println("Incorrect choice");
		}
		}	
	}
	public static void showContent() {
		System.out.println("Items Available in Shop : ");
		List<MyCartPojo> prodList = MyCartDao.getAllProducts();
		System.out.println("Id  Name  Category  Price");
		for (MyCartPojo p : prodList) {
			System.out.println( p.getProdId() +" , "+
			p.getProdName() +" , "+
			p.getProdCategory() +" , "+
			p.getProdPrice() );
		}
	}

}
