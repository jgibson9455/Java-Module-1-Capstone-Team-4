package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {

    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String MAIN_MENU_OPTION_REPORT          = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT,
													    MAIN_MENU_OPTION_REPORT  
													    };
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT      = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION          = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
														PURCHASE_MENU_OPTION_SELECT_PRODUCT,
														PURCHASE_MENU_OPTION_FINISH_TRANSACTION
													    };
	
	
	
	
	VendingMachine aVendingMachine = new VendingMachine();
		
	
	
	
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	private Menu purchaseMenu;
	
	
	
	public VendingMachineCLI(Menu menu, Menu purchaseMenu) {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
		this.purchaseMenu = purchaseMenu;
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	 * @throws IOException 
	*
	***************************************************************************************************************************/

	public void run() throws IOException {
//VendingMachine aVendingMachine = new VendingMachine(); //create vending machine
aVendingMachine.stockVendingMachine();  //stock's vending machine


		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					aVendingMachine.writeLog();
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
				
				case MAIN_MENU_OPTION_REPORT:
					aVendingMachine.generateSalesReport();
					break;
					
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {      // static attribute used as method is not associated with specific object instance
	aVendingMachine.displayItems();	
	}
	
	public void purchaseItems() throws IOException {	
		
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String formatDouble = String.format("%.2f", aVendingMachine.getCurrentAmountOfMoney());
			
			String currentBalance = "Current Money Provided: " +"$" + formatDouble;
			System.out.println("\n" + currentBalance);
			
			String choice = (String)purchaseMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);  // Display menu and get choice
			
			
			switch(choice) {                  // Process based on user menu choice
			
				case PURCHASE_MENU_OPTION_FEED_MONEY:
					aVendingMachine.feedMoney();          // invoke method to display items in Vending Machine
					
					break;                    // Exit switch statement
			
				case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
					aVendingMachine.displayItems();
					aVendingMachine.purchaseItems();        // invoke method to purchase items from Vending Machine
					
					break;                    // Exit switch statement
			
				case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
					aVendingMachine.dispenseChange();
					//endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}	
		}
		return;                               // End method and return to caller
		
		
		
		
	}
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
	
	
	
	
	
	
	
	
	
	
}
