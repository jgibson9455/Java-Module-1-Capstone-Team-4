package com.techelevator;

public class Chip extends Item {
	
	private String yumMessage;
	
	public Chip(String name, double price, String id, int quantity) {
		super(name, price, id, quantity);
		
		
	}

	@Override
	public String getYumMessage () {
		
		yumMessage = "Crunch Crunch, Yum!";
		return yumMessage;
	}

	
	
	

}
