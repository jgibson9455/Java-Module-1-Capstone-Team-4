package com.techelevator;

public class Slot {
	
	private int quantity;
	private Item vendingMachineItem;
	
	public Slot (Item vendingMachineItem, int quantity) {
		
		this.vendingMachineItem = vendingMachineItem;
		this.quantity = 5;
	}

	public int getQuantity() {
		return quantity;
	}

	public Item getVendingMachineItem() {
		return vendingMachineItem;
	}

	

	
	
	
	
	
	
	
	
	
}
