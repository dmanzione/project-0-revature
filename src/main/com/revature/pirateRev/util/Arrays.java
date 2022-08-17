package com.revature.pirateRev.util;

import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.models.Product;
import com.revature.pirateRev.models.StoreFront;

public class Arrays {

	public static enum PrintType {
		JUST_NAME
	}

	public static enum PrintObject {
		PIRATE, STORE_FRONT, LINE_ITEM, PRODUCT
	}

	public static void printArray(Object[] array) {
		System.out.println("\t\t*******");
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null)
				break;
			System.out.print(array[i]);
			if (i < array.length - 1)
				System.out.println();

		}
		System.out.println("\t\t*******");
	}

	public static void printArray(Object[] array, PrintType type, PrintObject printObject) {

		if (type.equals(PrintType.JUST_NAME)) {

			System.out.println("\t\t*******");

			for (int i = 0; i < array.length; i++) {

				if (printObject.equals(PrintObject.PIRATE)) {
					if (array[0] == null) {
						System.out.println("There are no pirates registered!");
						break;
					}
					if (array[i] == null) {
						break;
					}
					Pirate pirate = (Pirate) array[i];

					System.out.print("\t\t " + pirate.getName());

				} else if (printObject.equals(PrintObject.STORE_FRONT)) {

					if (array[i] == null) {
						break;
					}
					StoreFront store = (StoreFront) array[i];

					System.out.println(store.getName());

				} else {
					Product product = (Product) array[i];
					if (array[0] == null) {
						System.out.println("There are no products!");
						break;
					}
					if (array[i] == null) {
						break;
					}
					System.out.println(product.getName());
				}

				if (i < array.length - 1)
					System.out.println();
			}
			System.out.println("\t\t*******");
		}

	}

	public static void printArrayNumbered(Object[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null)
				continue;
			System.out.println(i + 1 + ") " + arr[i] + "\n");
		}
	}

}