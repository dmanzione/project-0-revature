package com.revature.pirateRev.ui;

import java.util.Scanner;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.dao.PirateDAO;
import com.revature.pirateRev.exceptions.NoSuchElementException;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.util.CaptainsLogger;
import com.revature.pirateRev.util.CaptainsLogger.LogLevel;

public class Menu {
	private static PirateDAO pirateDAO = new PirateDAO();
	private static Scanner sc = new Scanner(System.in);
	private static String pirateInput;
	private static CaptainsLogger logger = CaptainsLogger.getLogger();

	public static void open() {
		logger.log(LogLevel.INFO, "Main menu options displayed...");
		System.out.println("Welcome to the Pirate Supply Stores!\n");
		do {

			System.out.println("What would you like to do?\n");
			System.out.println("(1) Log In");
			System.out.println("(2) Register");
			System.out.println("(x) Exit\n");
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
				System.out.println("\n\nWrong Input!");
				break;

			}

		} while (!pirateInput.equals("x"));

		sc.close();
	}

	public static void exit() {
		System.out.println("Thank you for choosing the Pirate Supply Store\n\nas"
				+ " your choice of looted products for the modern scoundrel!!\n\n");
		System.out.println("\n\n\tReveal our location and you're dead...");

		logger.log(LogLevel.INFO, "...Exiting system...");
		System.exit(0);

	}

	private static void register() {
		Pirate newPirate = new Pirate();
		System.out.println("\nWhat is your name?");
		newPirate.setName(sc.nextLine());
		System.out.println("What is your address?");
		newPirate.setAddress(sc.nextLine());
		System.out.println("What is your email?");
		newPirate.setEmail(sc.nextLine());
		pirateDAO.create(newPirate);
		System.out.println("\nThis is your info:\n" + newPirate + "\n");

		PirateHome.start(newPirate);

	}

	private static void logIn() {

		ArrayList<Pirate> pirates = pirateDAO.readAll();
		if (pirates.getElementAtIndex(0) == null) {
			System.out.println("\nNo pirates registered!");
			return;
		}

		System.out.println("\nWhich pirate are you?\n");

		pirates.print();

		System.out.println("\nEnter name: \n");

		pirateInput = sc.nextLine();
		Pirate pirate;
		try {
			pirate = pirateDAO.readByName(pirateInput);
		} catch (NoSuchElementException e) {
			logger.log(LogLevel.ERROR, "User input incorrect for pirate name");
			System.out.println("There is no such pirate\n\nTry again");

			return;
		}

		PirateHome.start(pirate);
	}

}
