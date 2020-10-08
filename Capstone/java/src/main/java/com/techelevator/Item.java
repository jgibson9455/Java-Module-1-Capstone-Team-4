package com.techelevator;

public abstract class Item {
	private String name;
	private int quantity;
	private double price;
	
	public Item(String name, double price, int quantity) {
		this.name = name;
		this.quantity = 5;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}
	
	

}
