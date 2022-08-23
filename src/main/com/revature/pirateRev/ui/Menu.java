package com.revature.pirateRev.ui;

import java.util.Scanner;

import com.revature.pirateRev.data.PirateDAO;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.utils.ArrayList;
import com.revature.pirateRev.utils.CaptainsLogger;
import com.revature.pirateRev.utils.NoSuchElementException;
import com.revature.pirateRev.utils.CaptainsLogger.LogLevel;

public class Menu {
	private static PirateDAO pirateDAO = new PirateDAO();
	private static Scanner sc = new Scanner(System.in);
	private static CaptainsLogger logger = CaptainsLogger.getLogger();
	private static String pirateInput;
	private static Pirate pirate;

	public static void open() {
		logger.log(LogLevel.INFO, "Main menu options displayed...");
		print("Welcome to the Pirate Supply Stores!\n");
		do {

			print("What would you like to do?\n");
			print("(1) Log In");
			print("(2) Register");
			print("(x) Exit\n");
			pirateInput = sc.nextLine();
			switch (pirateInput.trim().toLowerCase()) {
			case "1":
			case "one":
				logIn();
				break;
			case "2":
			case "register":
				register();
				break;

			case "x":
				exit();
				break;
			default:
				print("\n\nWrong Input!");
				break;

			}

		} while (!pirateInput.equals("x"));

		sc.close();
	}

	public static void exit() {
		print("Thank you for choosing the Pirate Supply Store\n\nas"
				+ " your choice of looted products for the modern scoundrel!!\n\n");
		print("\n\n\tReveal our location and you're dead...");

		logger.log(LogLevel.INFO, "...Exiting system...");
		System.exit(0);

	}

	private static void register() {

		pirate = new Pirate();
		print("\n\nWhat is your name?\t\t\t(x) Exit");
		pirateInput = sc.nextLine();
		if (pirateInput.equalsIgnoreCase("x")) {
			logger.log(LogLevel.INFO, "User is exiting account creation. Going back to main menu...");
			print("Going back to main menu...");
			return;
		}

		pirate.setName(pirateInput);

		print("What is your address?\t\t\t(x) Exit");
		pirateInput = sc.nextLine();
		if (pirateInput.equalsIgnoreCase("x")) {
			print("Going back to main menu...");
			logger.log(LogLevel.INFO, "User is exiting account creation. Going back to main menu...");

			return;
		}
		pirate.setAddress(pirateInput);
		print("What is your email?\t\t\t(x) Exit");
		pirateInput = sc.nextLine();
		if (pirateInput.equalsIgnoreCase("x")) {
			print("Going back to main menu...");
			logger.log(LogLevel.INFO, "User is exiting account creation. Going back to main menu...");
			return;
		}
		pirate.setEmail(pirateInput);

		while (true) {
			print("Choose a username between 8-16 characters (case doesn't matter):\t\t\t(x) Exit");
			pirateInput = sc.nextLine();

			if (pirateInput.equals("x")) {
				print("Going back to main menu...");
				logger.log(LogLevel.INFO, "User is exiting account creation. Going back to main menu...");
				return;
			} else if (pirateInput.length() > 16 || pirateInput.length() < 8) {
				print("Please choose a username that is 8-16 characters in length");
				continue;
			} else {
				Pirate existingPirate = pirateDAO.readBySomeColumnValue(pirateInput);
				if (existingPirate != null) {

					print("\n\nSorry! That username is already taken.");
					print("\n\nPlease try again");
					continue;

				} else {
					pirate.setUsername(pirateInput);
					break;
				}

			}
		}

		while (true) {

			print("Choose a password between 8-16 characters in length (case matters, spaces at edges removed):\t\t\t(x) Exit");

			pirateInput = sc.nextLine();

			if (pirateInput.toLowerCase().trim().equals("x")) {
				print("Going back to main menu...");
				logger.log(LogLevel.INFO, "User is exiting account creation. Going back to main menu...");
				return;
			} else if (pirateInput.trim().length() < 8 || pirateInput.trim().length() > 16) {
				print("Please choose a password that is 8-16 characters in length");
				continue;
			} else {

				pirate.setPassword(pirateInput);
				break;
			}

		}

		pirateDAO.create(pirate);

		PirateHome.start(pirate);

	}

	

	private static void logIn() {

		while (true) {
			print("username:\t\t\t(x) Exit");
			pirateInput = sc.nextLine();
			if (pirateInput.equalsIgnoreCase("x")) {
				print("Going back to main menu...");
				logger.log(LogLevel.INFO, "User is exiting login. Going back to main menu...");
				return;
			} else {
				pirate = pirateDAO.readBySomeColumnValue(pirateInput);
				if (pirate == null) {

					print("No pirates registered with that username");
					print("Beware of deceiving us, scoundrel");
					print("I suppose you may try again");
					continue;
				} else {
					break;
				}
			}

		}

		for (int i = 0; i < 3; i++) {
			print("password: \t\t(x) Exit");
			pirateInput = sc.nextLine();

			if (pirateInput.equalsIgnoreCase("x")) {
				print("Going back to main menu...");
				logger.log(LogLevel.INFO, "User is exiting login. Going back to main menu...");
				return;
			} else if (!pirateInput.trim().equalsIgnoreCase(pirate.getPassword())) {
				if(i==2) {
					print("You had enough tries, impostor!");
					print("Consider yourself lucky for escaping with your life!");
					logger.log(LogLevel.WARNING, "User " + pirate.getUsername() + " unsuccessful at providing credentials. Going back to main menu");
					return;
				}
				print("Wrong password. You have " + (3-i-1) + " more tries");
				continue;
			} else {
				logger.log(LogLevel.INFO, "Successful user authentication. Logging into user homepage...");
				print("Logging in...");
				PirateHome.start(pirate);
				return;
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
