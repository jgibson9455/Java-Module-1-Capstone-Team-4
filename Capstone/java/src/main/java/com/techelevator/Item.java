package com.techelevator;

public abstract class Item {
	private String name;
	private String id;
	private double price;
	private int quanity;
	
	public Item(String name, double price, String id, int quantity) {
		this.name = name;
		this.id = id;
		this.price = price;
		quanity = 5;
		
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public String getName() {
		return name;
	}


	public double getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}

	
	public String getYumMessage() {
		
		return "";
	}
	
	
	

}
