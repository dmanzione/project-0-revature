package com.revature.pirateRev.ui;

import java.util.Scanner;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.dao.OrderDAO;
import com.revature.pirateRev.dao.ProductDAO;
import com.revature.pirateRev.dao.StoreFrontDAO;
import com.revature.pirateRev.models.LineItem;
import com.revature.pirateRev.models.Order;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.models.Product;
import com.revature.pirateRev.models.StoreFront;
import com.revature.pirateRev.util.CaptainsLogger;
import com.revature.pirateRev.util.CaptainsLogger.LogLevel;

public class PirateHome {

	private static String pirateInput;
	private static Scanner sc = new Scanner(System.in);
	private static StoreFrontDAO storeFrontDAO = new StoreFrontDAO();
	private static ProductDAO productDAO = new ProductDAO();
	private static Pirate pirate;
	private static OrderDAO orderDAO = new OrderDAO();
	private static CaptainsLogger logger;
	private static Order order;
	private static LineItem lineItem;
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
		System.out.println("\n\nOld habits die hard...\n\nAnd so do pirates!\n\nNow get out of here!");
		Menu.exit();

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
		
	}

	private static void changeName() {
		

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

		while (true) {
			System.out.println("\n\nWould you like to purchase an item?");
			System.out.println("\n\n(1) Yes");
			System.out.println("(2) No");
			System.out.println("(x) Exit");
			pirateInput = sc.nextLine();
			switch (pirateInput) {
			case "1":
				System.out.println("Which one?\n\nSelect by number:\n\n");
				products.print();
				String choice = sc.nextLine();

				Integer choiceNum = Integer.valueOf(choice);
				if (products.getElementAtIndex(choiceNum) == null) {
					System.out.println("\n\nWrong input! Please try again");
					logger.log(LogLevel.ERROR, "\n\nWrong input! Please try again");
					continue;
				}else {
					Product myProduct = products.getElementAtIndex(Integer.valueOf(choice));
					System.out.println("\n\nThis is the item you chose:");
					
					System.out.println("\n\n"+myProduct);
					
					System.out.println("\n\nHow many would you like?");
					
					String numOfProducts = sc.nextLine();
					int numOfItemsCustomer = Integer.valueOf(numOfProducts);
					
					int numOfItemsInStock = 0;
					for(Product product : products) {
						if(product.getName().equals(myProduct.getName())) {
							numOfItemsInStock++;
						}
					}
					if(numOfItemsInStock<numOfItemsCustomer) {
						System.out.println("\n\nI am sorry, but we only have " + numOfItemsInStock + " left.");
						System.out.println("\n\nLet me go ahead and add it to your order...");
						
						LineItem lineI = new LineItem();
						lineI.setProduct(myProduct);
						lineI.setQuantity(numOfItemsCustomer);
						
						System.out.println("\n\nThis is your order so far:\n\n"+ order);
						
					}
					
					
					Menu.exit();
				}
				break;
			case "2":
				
				System.out.println("\n\nMaybe you would like an item from another store...");
				return;
			case "x":
				Menu.exit();
				break;
				
			default:
				
				System.out.println("\n\nWrong input!\n\nPlease try again");
				break; 
				
			}
		}
	}
}