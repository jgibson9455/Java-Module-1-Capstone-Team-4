package com.techelevator;

public class Beverage extends Item{
	
	private String yumMessage;
	
	public Beverage(String name, double price, String id, int quantity) {
		super(name, price, id, quantity);
		
	}
	
	@Override
	public String getYumMessage () {
		
		yumMessage = "Glug Glug, Yum!";
		return yumMessage;
	}
	
	
}
