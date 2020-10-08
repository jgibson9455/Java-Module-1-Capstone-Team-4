package com.techelevator;

public class Item {
	private String name;
	private String id;
	private double price;
	private String type;
	
	public Item(String name, double price, String id, String type) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.type = type;
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

	public String getType() {
		return type;
	}
	
	
	

}
