package Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import Pojo.AuthPojo;
import Pojo.AuthPojo2;
import Pojo.MyCartPojo;

public class MyCartMain {

	public static void main(String[] args) throws NumberFormatException, IOException {

		String role = "";
		String admin = "Admin";
		String user = "User";
		while (!(role == admin) && !(role == user)) {

			System.out.println("Welcome to MyCart");
			System.out.print("Login (User/Admin) :");
			Scanner sc = new Scanner(System.in);
			role = sc.nextLine();

			if (role.equalsIgnoreCase(admin)) {
				System.out.println("Admin Authentication ");
				System.out.print("Enter Login Id :");
				Scanner sc1 = new Scanner(System.in);
				String loginId = sc1.nextLine();
				System.out.print("Enter Password :");
				Scanner sc2 = new Scanner(System.in);
				String password = sc2.nextLine();

				if (MyCartDao.isAdminValid(new AuthPojo(loginId, password))) {
					adminContent();
				}

			} else if (role.equalsIgnoreCase(user)) {

				System.out.println("User Authentication ");
				System.out.println("Login Id=Ajay, and Password=Ajay123 Just for Referance");
				System.out.print("Enter Login Id :");
				Scanner Uname = new Scanner(System.in);
				String userId = Uname.nextLine();
				System.out.print("Enter Password :");
				Scanner Upass = new Scanner(System.in);
				String password = Upass.nextLine();
				if (MyCartDao.isUserValid(new AuthPojo2(userId, password))) {
					userContent(userId);
				}

			}
			else {
				System.out.println("Choose from options");
			}

		}
	}

	public static void userContent(String userId) {
		System.out.println("User Window");
		MyCartDao.emptyCart();
		boolean flag = true;
		int prodId;
		while (flag) {
			showContent();
			MyCartDao.readCart();
			System.out.println("Add items in Cart");
			Scanner read = new Scanner(System.in);
			System.out.println("Select Id to Add Product or 0 for billing or -1 to modify cart");
			prodId = read.nextInt();
			if (prodId != 0 && prodId != -1) {
				MyCartPojo product = MyCartDao.getProdById(prodId);
				MyCartDao.addIntoCart(product);
			} else if (prodId == -1) {
				showContent();
				MyCartDao.readCart();
				System.out.println("Enter product Id to Remove from cart");
				prodId = read.nextInt();
				MyCartDao.delProdByIdFromCart(prodId);
			} else if (prodId == 0) {
				MyCartDao.billing(userId);
				flag = false;
			}

		}
	}

	public static void adminContent() throws NumberFormatException, IOException {
		System.out.println("Show Admin Window");
		boolean flag = true;
		int choice;
		String category;
		while (flag) {
			showContent();
			MyCartDao.readCart();
			Scanner read = new Scanner(System.in);
			System.out.println("Select 1 to Add Product or -1 to delet category or 0 to See Total Bills");
			choice = read.nextInt();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			if (choice == 1) {
				System.out.print("Enter Product Id :");
				int prodId = Integer.parseInt(br.readLine());
				System.out.print("Enter Product Name :");
				String prodName = br.readLine();
				System.out.print("Enter Product Category :");
				String prodCate = br.readLine();
				System.out.print("Enter Product Price :");
				String prodPrice = br.readLine();
				MyCartPojo product = new MyCartPojo(prodId, prodName, prodCate, prodPrice);
				MyCartDao.addProd(product);
			} else if (choice == -1) {
				showContent();
				System.out.println("Enter Category to Remove from cart :");
				category = br.readLine();
				MyCartDao.delCartegory(category);
			} else if (choice == 0) {
				
				MyCartDao.readBill();
			}
			
			else {
				System.out.println("Enter from valid options");
			}
		}
	}

	public static void showContent() {
		System.out.println("Items Available in Shop : ");
		List<MyCartPojo> prodList = MyCartDao.getAllProducts();
		System.out.println("Id  Name  Category  Price");
		for (MyCartPojo p : prodList) {
			System.out.println(
					p.getProdId() + ", " + p.getProdName() + ", " + p.getProdCategory() + ", " + p.getProdPrice());
		}
	}

}
