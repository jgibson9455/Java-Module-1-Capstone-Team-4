package com.techelevator;

public class Gum extends Item{
	
	private String yumMessage;
	
	public Gum(String name, double price, String id, int quantity) {
		super(name, price, id, quantity);
	}

	@Override
	public String getYumMessage () {
		
		yumMessage = "Chew Chew, Yum!";
		return yumMessage;
	}
	
}
