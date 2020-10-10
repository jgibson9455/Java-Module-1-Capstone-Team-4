package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	private Map<String, Item> inventory = new LinkedHashMap<>();
	private double currentAmountOfMoney = 0.0;
	private final double quarter = .25;
	private final double nickel = .05;
	private final double dime = .10;
	
	Scanner userInput = new Scanner(System.in);
	File logFile = new File("log.txt");
	//PrintWriter pw = new PrintWriter(logFile);
	
	
	/*public VendingMachine() throws FileNotFoundException  {
		
		logFile = new File("log.txt");
		pw = new PrintWriter(logFile);
			
	}*/
		
			
			
		
			
			
		
		
		
	
	
	
	
	
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
		System.out.print("How much money would you like to feed? : ");
		String input = userInput.nextLine();
		
		if (input.equals("1")) { 
			currentAmountOfMoney += 1.0; 
			
		}
		else if (input.equals("2")) { 
			currentAmountOfMoney += 2.0; 
		
	}
		else if (input.equals("5")) { 
			currentAmountOfMoney += 5.0; 
		}
		else if (input.equals("10")) { 
			currentAmountOfMoney += 10.0; 
		} else {
			
			System.out.println("****Invalid Amount Inputed****");
		}
		
		return currentAmountOfMoney;
		
	}
	
	public void purchaseItems() {
		//Scanner theKeyboard = new Scanner(System.in);
		System.out.print("Which Product Would You Like? : ");
		String aLine = userInput.nextLine();
		for(Map.Entry<String, Item> aKey : inventory.entrySet() ) { //going through map
			if(aLine.equalsIgnoreCase(aKey.getKey())) {
				if(aKey.getValue().getQuanity() != 0 && currentAmountOfMoney >= aKey.getValue().getPrice()) { //value of Key needs to not be zero
				   aKey.getValue().setQuanity(aKey.getValue().getQuanity() - 1); //set new quantity
				   currentAmountOfMoney -= aKey.getValue().getPrice(); //subtract purchase price from money
				  
				   System.out.println(aKey.getValue().getYumMessage());
				}
				if (aKey.getValue().getQuanity() == 0) {
					System.out.println("****SOLD OUT****");
					
				}
				
				
				
			}
			
		}
		
		
		
	}
	
	public void dispenseChange() {
		int quarterCount = 0;
		int dimeCount = 0;
		int nickelCount = 0;
		
		while(currentAmountOfMoney > 0) {
			
			String formatDouble = String.format("%.2f", currentAmountOfMoney);
			currentAmountOfMoney = Double.parseDouble(formatDouble);
			
			//System.out.println(currentAmountOfMoney);
			if (currentAmountOfMoney >= quarter) {
				currentAmountOfMoney = currentAmountOfMoney - quarter;
				quarterCount++;
				
			}
			else if(currentAmountOfMoney >= dime) {
				currentAmountOfMoney = currentAmountOfMoney - dime;
				dimeCount++;
			
			}
			else if (currentAmountOfMoney >= nickel) {
				currentAmountOfMoney = currentAmountOfMoney - nickel;
				nickelCount++;
			
			}
			
			 
		}
		//writeToLog("");
		System.out.println("Quarters: " + quarterCount + " Dimes: " + dimeCount + " Nickels: " + nickelCount); 
		
	}
	public void displayItems() {
		for(Map.Entry<String, Item> aKey : inventory.entrySet() ) {
			
		System.out.println(aKey.getValue().getId() + " | " + aKey.getValue().getName() + " | " + aKey.getValue().getPrice() + " | " + aKey.getValue().getQuanity());
		
		}
	}
	

	/*public void writeToLog() throws FileNotFoundException  {
		
		PrintWriter pw = new PrintWriter(logFile);
		pw.println("hello");
		
	}*/

	public Map<String, Item> getInventory() {
		return inventory;
	}

	public double getCurrentAmountOfMoney() {
		return currentAmountOfMoney;
	}

	public double getQuarter() {
		return quarter;
	}

	public double getNickel() {
		return nickel;
	}

	public double getDime() {
		return dime;
	}

	public Scanner getUserInput() {
		return userInput;
	}

	public File getLogFile() {
		return logFile;
	}
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
