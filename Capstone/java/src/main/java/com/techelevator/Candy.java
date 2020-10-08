package com.techelevator;

public class Candy extends Item {
	
	private String yumMessage;
	
	public Candy(String name, double price, String id, int quantity) {
		super(name, price, id, quantity);
	
	}
	
	@Override
	public String getYumMessage () {
		
		yumMessage = "Munch Munch, Yum!";
		return yumMessage;
	}

}
