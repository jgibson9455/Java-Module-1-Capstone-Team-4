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
	private double currentAmountOfMoney = 0.0;
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
	
	public double feedMoney() {
		Scanner userInput = new Scanner(System.in);
		String input = userInput.nextLine();
	
		if (input.equals("1")) { 
			currentAmountOfMoney += 1.0; 
			
			
		}
		if (input.equals("2")) { 
			currentAmountOfMoney += 2.0; 
		
	}
		if (input.equals("5")) { 
			currentAmountOfMoney += 5.0; 
		}
		if (input.equals("10")) { 
			currentAmountOfMoney += 10.0; 
		}
		userInput.close();
		return currentAmountOfMoney;
		
	}
	
	public void purchaseItems() {
		Scanner theKeyboard = new Scanner(System.in);
		String aLine = theKeyboard.nextLine();
		for(Map.Entry<String, Item> aKey : inventory.entrySet() ) { //going through map
			if(aLine.equals(aKey.getKey())) {
				if(aKey.getValue().getQuanity() != 0 && currentAmountOfMoney >= aKey.getValue().getPrice()) { //value of Key needs to not be zero
				   aKey.getValue().setQuanity(aKey.getValue().getQuanity() - 1); //set new quantity
				   currentAmountOfMoney -= aKey.getValue().getPrice(); //subtract purchase price from money
				   
				}
				
				
				
			}
			
		}
		theKeyboard.close();
	}
	
	public  dispenseChange() {
		
	}
}
