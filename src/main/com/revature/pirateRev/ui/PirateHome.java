package com.revature.pirateRev.ui;

import java.util.Scanner;

import com.revature.pirateRev.data.OrderDAO;
import com.revature.pirateRev.data.ProductDAO;
import com.revature.pirateRev.data.StoreFrontDAO;
import com.revature.pirateRev.models.LineItem;
import com.revature.pirateRev.models.Order;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.models.Product;
import com.revature.pirateRev.models.StoreFront;
import com.revature.pirateRev.utils.ArrayList;
import com.revature.pirateRev.utils.CaptainsLogger;
import com.revature.pirateRev.utils.CaptainsLogger.LogLevel;

public class PirateHome {

	private static String pirateInput;
	private static Scanner sc = new Scanner(System.in);
	private static StoreFrontDAO storeFrontDAO = new StoreFrontDAO();
	private static ProductDAO productDAO = new ProductDAO();
	private static Pirate pirate;
	private static OrderDAO orderDAO = new OrderDAO();
	private static CaptainsLogger logger;
	private static Order order = new Order();
	private static LineItem lineItem;
	private static ArrayList<Order> orders;

	public static void start(Pirate p) {
		pirate = p;
		print("\nWelcome, " + pirate.getName() + "\n\n");
		pirate.setOrders(orders = orderDAO.readAllByPirateUsername(pirate.getUsername()));
		do {

			print("What would you like to do?\n");
			print("(1) Choose Store");
			print("(2) Edit Profile");
			print("(3) Loot Village");
			print("(4) See past orders");
			print("(o) Log out");
			print("(x) Exit\n");

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
				print("\n\nLogging out...");
				return;
			case "x":
				Menu.exit();
				break;
			default:
				print("\n\nWrong Input!");
				break;
			}

		} while (!pirateInput.equals("x"));
	}

	private static void showPastOrders() {

		if (orders.isEmpty()) {
			print("You have no past orders!");
		} else {
			orders.print();
		}

	}

	private static void lootVillage() {
		print("\n\nOld habits die hard...\n\nAnd so do pirates!\n\nNow get out of here!");
		Menu.exit();

	}

	private static void editProfile() {
		print("\n\nHere is your information:\n\n" + pirate);
		do {

			print("What would you like to do?\n");
			print("(1) Change name");
			print("(2) Change address");
			print("(3) Change email");
			print("(3) Loot Village");

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
				print("\n\nLogging out...");
				return;
			case "x":
				Menu.exit();
				break;
			default:
				print("\n\nWrong Input!");
				break;

			}
		} while (!pirateInput.equals("x"));
	}

	private static void changeAddress() {

	}

	private static void changeName() {

	}

	private static void pickStore() {
		print("\nPlease select one of our stores: ");
		ArrayList<StoreFront> stores = storeFrontDAO.readAll();
		stores.print();
		print("\n\nPlease choose which store you want to check out");

		print("\n\nType in the number:");

		pirateInput = sc.nextLine();

		ArrayList<Product> products = null;
		StoreFront store = null;
		switch (pirateInput.trim().toLowerCase()) {
		case "1":
		case "one":
			store = storeFrontDAO.readBySomeColumnValue("Captain Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "2":
		case "two":
			store = storeFrontDAO.readBySomeColumnValue("First Mate Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);
			break;
		case "3":

			store = storeFrontDAO.readBySomeColumnValue("Quartermaster Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "4":

			store = storeFrontDAO.readBySomeColumnValue("Sailing Master Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "5":
			store = storeFrontDAO.readBySomeColumnValue("Gunner Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "6":
			store = storeFrontDAO.readBySomeColumnValue("Powder Monkey Branch");
			products = productDAO.readAllByStoreName(store.getName());
			chooseProduct(store, products);

		case "7":
			store = storeFrontDAO.readBySomeColumnValue("Boatswain Branch");
			products = productDAO.readAllByStoreName(store.getName());
			chooseProduct(store, products);

			break;
		case "8":
			store = storeFrontDAO.readBySomeColumnValue("Surgeon Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "9":
			store = storeFrontDAO.readBySomeColumnValue("Cook Branch");

			products = productDAO.readAllByStoreName(store.getName());

			chooseProduct(store, products);

			break;
		case "o":
			print("\n\nLogging out...");
			return;

		case "x":
			Menu.exit();

			break;
		default:
			print("\n\nWrong input! Please try again!\n\n");
			break;
		}

	}

	private static void chooseProduct(StoreFront store, ArrayList<Product> products) {

		if (products == null)
			print("\n\n" + store.getName() + " has been looted!\n\nThere are no more products in here!");

		products.print();

		while (true) {
			print("\n\nWould you like to purchase an item?");
			print("\n\n(1) Yes");
			print("(2) No");
			print("(x) Exit");
			pirateInput = sc.nextLine();
			switch (pirateInput) {
			case "1":
				print("Which one?\n\nSelect by number:\n\n");
				products.print();
				String choice = sc.nextLine();

				Integer choiceNum = Integer.valueOf(choice);
				if (products.getElementAtIndex(choiceNum) == null) {
					print("\n\nWrong input! Please try again");
					logger.log(LogLevel.ERROR, "\n\nWrong input! Please try again");
					continue;
				} else {
					Product myProduct = products.getElementAtIndex(Integer.valueOf(choice));
					print("\n\nThis is the item you chose:");

					print("\n\n" + myProduct);

					print("\n\nHow many would you like?");

					String numOfProducts = sc.nextLine();
					int numOfItemsCustomer = Integer.valueOf(numOfProducts);

					int numOfItemsInStock = 0;
					for (Product product : products) {
						if (product.getName().equals(myProduct.getName())) {
							numOfItemsInStock++;
						}
					}
					if (numOfItemsInStock < numOfItemsCustomer) {
						print("\n\nI am sorry, but we only have " + numOfItemsInStock + " left.");
						print("\n\nLet me go ahead and add it to your order...");

						LineItem lineI = new LineItem();
						lineI.setProduct(myProduct);
						lineI.setQuantity(numOfItemsCustomer);

						print("\n\nThis is your order so far:\n\n" + order);

					}

					Menu.exit();
				}
				break;
			case "2":

				print("\n\nMaybe you would like an item from another store...");
				return;
			case "x":
				Menu.exit();
				break;

			default:

				print("\n\nWrong input!\n\nPlease try again");
				break;

			}
		}
	}

	private static void print(String string) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println("\n" + string);

	}
}