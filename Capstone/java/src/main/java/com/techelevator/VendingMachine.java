package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

	// PrintWriter pw = new PrintWriter(logFile);

	List<String> logList = new ArrayList<String>();

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

			}
			if (items[3].contains("Gum")) {

				Gum gum = new Gum(items[1], Double.parseDouble(items[2]), items[0], 5);
				inventory.put(items[0], gum);

			}
			if (items[3].contains("Drink")) {

				Beverage drink = new Beverage(items[1], Double.parseDouble(items[2]), items[0], 5);
				inventory.put(items[0], drink);

			}
			if (items[3].contains("Candy")) {

				Candy candy = new Candy(items[1], Double.parseDouble(items[2]), items[0], 5);
				inventory.put(items[0], candy);

			}

		}
		theInventory.close();

	}

	public double feedMoney() throws IOException {
		System.out.print("How much money would you like to feed? ($1, $2, $5, $10) : ");
		String input = userInput.nextLine();

		if (input.equals("1")) {
			logContent("FEED MONEY:", currentAmountOfMoney, currentAmountOfMoney + 1.00);
			currentAmountOfMoney += 1.0;

		} else if (input.equals("2")) {
			logContent("FEED MONEY:", currentAmountOfMoney, currentAmountOfMoney + 2.00);
			currentAmountOfMoney += 2.0;

		} else if (input.equals("5")) {
			logContent("FEED MONEY:", currentAmountOfMoney, currentAmountOfMoney + 5.00);
			currentAmountOfMoney += 5.0;
		} else if (input.equals("10")) {
			logContent("FEED MONEY:", currentAmountOfMoney, currentAmountOfMoney + 10.00);
			currentAmountOfMoney += 10.0;
		} else {

			System.out.println("****Invalid Amount Inputed****");
		}

		return currentAmountOfMoney;

	}

	public void purchaseItems() throws IOException {
		// Scanner theKeyboard = new Scanner(System.in);
		System.out.print("Which Product Would You Like? : ");
		String aLine = userInput.nextLine();
		
		Item anItem = inventory.get(aLine);
		
		if(anItem == null) {
		
			System.out.println("You have selected an Invalid Item. Please Select a Valid Item");
			
		}
		
		for (Map.Entry<String, Item> aKey : inventory.entrySet()) { // going through map
			
			if (aLine.equalsIgnoreCase(aKey.getKey())) {
				
				if (aKey.getValue().getQuanity() != 0 && currentAmountOfMoney >= aKey.getValue().getPrice()) { 
																												
				
					aKey.getValue().setQuanity(aKey.getValue().getQuanity() - 1); // set new quantity
					logContent(aKey.getValue().getName() + " " + aKey.getValue().getId(), currentAmountOfMoney,
							currentAmountOfMoney - aKey.getValue().getPrice());
					currentAmountOfMoney -= aKey.getValue().getPrice(); // subtract purchase price from money

					System.out.println(aKey.getValue().getYumMessage());
				}
				if (aKey.getValue().getQuanity() != 0 && currentAmountOfMoney < aKey.getValue().getPrice()) {

					System.out.println("You have insufficient funds. Please feed me more money");
				}

				if (aKey.getValue().getQuanity() == 0) {
					System.out.println("****SOLD OUT****");

				}

			} 
			

		}
		
		

	}

	public void dispenseChange() throws IOException {
		int quarterCount = 0;
		int dimeCount = 0;
		int nickelCount = 0;
		logContent("GIVE CHANGE: ", currentAmountOfMoney, 0.00);

		while (currentAmountOfMoney > 0) {

			String formatDouble = String.format("%.2f", currentAmountOfMoney);
			currentAmountOfMoney = Double.parseDouble(formatDouble);

			// System.out.println(currentAmountOfMoney);
			if (currentAmountOfMoney >= quarter) {
				currentAmountOfMoney = currentAmountOfMoney - quarter;
				quarterCount++;

			} else if (currentAmountOfMoney >= dime) {
				currentAmountOfMoney = currentAmountOfMoney - dime;
				dimeCount++;

			} else if (currentAmountOfMoney >= nickel) {
				currentAmountOfMoney = currentAmountOfMoney - nickel;
				nickelCount++;

			}

		}

		// writeToLog("");
		System.out.println("Quarters: " + quarterCount + " Dimes: " + dimeCount + " Nickels: " + nickelCount);

	}

	public void displayItems() {
		for (Map.Entry<String, Item> aKey : inventory.entrySet()) {

			System.out.println(aKey.getValue().getId() + " | " + aKey.getValue().getName() + " | "
					+ aKey.getValue().getPrice() + " | " + aKey.getValue().getQuanity());

		}
	}
	
	public void generateSalesReport() throws IOException {
		
		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("MM_dd_yyyy_hh_mm_ss_a");
		
		File salesReport = new File(timeStamp.format(timeStampFormatter) + "_SalesReport.txt");
		try (FileWriter reportWriter = new FileWriter(salesReport)) {
		
			
			double totalSales = 0;
		
			for (Map.Entry<String, Item> aKey : inventory.entrySet()) {
				
				
				
		
			totalSales = totalSales + ((5- aKey.getValue().getQuanity()) * aKey.getValue().getPrice());
		
		reportWriter.write(aKey.getValue().getName() + "|"+ (5 - aKey.getValue().getQuanity()));
		reportWriter.write("\n");
			
		}
		String formatDouble = String.format("%.2f", totalSales);
		reportWriter.write("\n");
		reportWriter.write("Total Sales: " + "$" + formatDouble);
		
		
		}
	}

	public void writeLog() throws IOException {

		File logFile = new File("log.txt");
		List<String> logList = getLogList();
		try (FileWriter logWriter = new FileWriter(logFile, true)) {

			for (String str : logList) {

				logWriter.write(str);
				logWriter.write("\n");
			}
		}

	}

	public List<String> logContent(String name, double startAmount, double endAmount) {

		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

		DecimalFormat ourFormat = new DecimalFormat("0.00");
		String str = timeStamp.format(timeStampFormatter) + " " + name + " " + "$" + ourFormat.format(startAmount) + " "
				+ "$" + ourFormat.format(endAmount);
		logList.add(str);
		return logList;

	}

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

	public List<String> getLogList() {
		return this.logList;
	}

}
