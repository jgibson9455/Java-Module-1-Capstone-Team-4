package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	private Map<String, Item> inventory = new HashMap<>();
	private double currentAmountofMoney = 0.0;
	private final double quarter = .25;
	private final double nickel = .05;
	private final double dime = .10;

	public void stockVendingMachine() throws FileNotFoundException {

		File myInventory = new File("vendingmachine.csv");
		Scanner theInventory = new Scanner(myInventory);
		String theLine = "";

		while (theInventory.hasNextLine()) {

			theLine = theInventory.nextLine();
			String[] items = theLine.split("\\|");

			if (items[3].contains("Chip")) {

				Chip chip = new Chip(items[1], Double.parseDouble(items[2]), items[0], 5);
				inventory.put(items[0], chip);

			} if (items[3].contains("Gum")) {

				Gum gum = new Gum(items[1], Double.parseDouble(items[2]), items[0], 5);
				inventory.put(items[0], gum);

			} if (items[3].contains("Drink")) {

				Beverage drink = new Beverage(items[1], Double.parseDouble(items[2]), items[0], 5);
				inventory.put(items[0], drink);

			} if(items[3].contains("Candy")) {
				
				Candy candy = new Candy(items[1], Double.parseDouble(items[2]), items[0], 5);
				inventory.put(items[0], candy);
			
		}

		}
		theInventory.close();
	}

}
