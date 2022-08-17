package com.revature.pirateRev.ui;

import java.util.Scanner;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.dao.OrderDAO;
import com.revature.pirateRev.dao.ProductDAO;
import com.revature.pirateRev.dao.StoreFrontDAO;
import com.revature.pirateRev.models.Order;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.models.Product;
import com.revature.pirateRev.models.StoreFront;

public class PirateHome {

	private static String pirateInput;
	private static Scanner sc = new Scanner(System.in);
	private static StoreFrontDAO storeFrontDAO = new StoreFrontDAO();
	private static ProductDAO productDAO = new ProductDAO();
	private static Pirate pirate;
	private static OrderDAO orderDAO = new OrderDAO();

	public static void start(Pirate p) {
		pirate = p;
		System.out.println("\nWelcome, " + pirate.getName() + "\n\n");

		do {

			System.out.println("What would you like to do?\n");
			System.out.println("(1) Choose Store");
			System.out.println("(2) Edit Profile");
			System.out.println("(3) Loot Village");
			System.out.println("(4) See past orders");
			System.out.println("(o) Log out");
			System.out.println("(x) Exit\n");

			pirateInput = sc.nextLine();
			switch (pirateInput.trim().toLowerCase()) {
			case "1":
			case "one":
				pickStore();
				break;
			case "2":
			case "two":
			case "edit":
				editProfile();
				break;
			case "3":
			case "three":
			case "loot":
				lootVillage();
				break;
			case "4":
			case "four":
			case "orders":
				showPastOrders();
				break;
			case "o":
				System.out.println("\n\nLogging out...");
				return;
			case "x":
				Menu.exit();
				break;
			default:
				System.out.println("\n\nWrong Input!");
				break;
			}

		} while (!pirateInput.equals("x"));
	}

	private static void showPastOrders() {
		ArrayList<Order> myOrders = orderDAO.readAllByPirateName(pirate.getName());
		myOrders.print();

	}

	private static void lootVillage() {
		// TODO Auto-generated method stub

	}

	private static void editProfile() {
		System.out.println("\n\nHere is your information:\n\n" + pirate);
		do {

			System.out.println("What would you like to do?\n");
			System.out.println("(1) Change name");
			System.out.println("(2) Change address");
			System.out.println("(3) Change email");
			System.out.println("(3) Loot Village");

			pirateInput = sc.nextLine();
			switch (pirateInput.trim().toLowerCase()) {
			case "1":
			case "one":
				changeName();
				break;
			case "2":
			case "register":
				changeAddress();
				break;
			case "o":
				System.out.println("\n\nLogging out...");
				return;
			case "x":
				Menu.exit();
				break;
			default:
				System.out.println("\n\nWrong Input!");
				break;

			}
		} while (!pirateInput.equals("x"));
	}

	private static void changeAddress() {
		// TODO Auto-generated method stub
	}

	private static void changeName() {
		// TODO Auto-generated method stub

	}

	private static void pickStore() {
		System.out.println("\nPlease select one of our stores: ");
		ArrayList<StoreFront> stores = storeFrontDAO.readAll();
		stores.print();
		System.out.println("\n\nPlease choose which store you want to check out");

		System.out.println("\n\nType in the number:");

		pirateInput = sc.nextLine();

		ArrayList<Product> products = null;
		StoreFront store = null;
		switch (pirateInput.trim().toLowerCase()) {
		case "1":
		case "one":
			store = storeFrontDAO.readByName("Captain Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "2":
		case "two":
			store = storeFrontDAO.readByName("First Mate Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);
			break;
		case "3":

			store = storeFrontDAO.readByName("Quartermaster Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "4":

			store = storeFrontDAO.readByName("Sailing Master Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "5":
			store = storeFrontDAO.readByName("Gunner Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "6":
			store = storeFrontDAO.readByName("Powder Monkey Branch");
			products = productDAO.readAllByStoreName(store.getName());
			chooseProduct(store, products);

		case "7":
			store = storeFrontDAO.readByName("Boatswain Branch");
			products = productDAO.readAllByStoreName(store.getName());
			chooseProduct(store, products);

			break;
		case "8":
			store = storeFrontDAO.readByName("Surgeon Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "9":
			store = storeFrontDAO.readByName("Cook Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "o":
			System.out.println("\n\nLogging out...");
			return;

		case "x":
			Menu.exit();

			break;
		default:
			System.out.println("\n\nWrong input! Please try again!\n\n");
			break;
		}

	}

	private static void chooseProduct(StoreFront store, ArrayList<Product> products) {

		if (products == null)
			System.out.println("\n\n" + store.getName() + " has been looted!\n\nThere are no more products in here!");

		products.print();

		System.out.println("\n\nWould you like to purchase an item?");
		System.out.println("\n\n(1) Yes");
		System.out.println("\n\n(2) No");
		System.out.println("(o) Log out");
		System.out.println("(x) Exit");
		pirateInput = sc.nextLine();
		switch (pirateInput) {
		case "1":
			System.out.println("Which one?\n\nSelect by number:\n\n");
			String choice = sc.nextLine();
			switch (choice) {
			}
		}
	}
}