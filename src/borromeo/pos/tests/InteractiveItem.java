package borromeo.pos.tests;
//Receiving Item Tester


import java.io.*;
import java.util.*;

import borromeo.pos.model.Item;
import borromeo.pos.model.ItemList;

public class InteractiveItem{
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		ItemList list = new ItemList();
		char choice = '*'; 
	
		System.out.println("\n\n**************Main Menu**************");
		System.out.println("A - Add an Item ");
		System.out.println("R - Remove an Item ");
		System.out.println("D - Show all Items ");
		System.out.println("S - Search for an Item ");
		//System.out.println("E - Edit a Item ");
		System.out.println("Q - Quit the Program \n");
			
	    do {
		 
		String input;
		System.out.print("Your choice : ");
		input = stdin.readLine().toUpperCase();
		choice = input.charAt(0);
			
		switch (choice)  {
			
			case 'A' : 	addToList(list);
					   	break;
			
			case 'R' : 	System.out.print("\nEnter the Item Name: ");
						String searchName = stdin.readLine();
						removeItem(list,searchName);			
				       	break;
			
			case 'D' : 
						list.sortItem();
						list.displayItem(); 
					   	break;
					   	
			case 'S' :	list.sortItem();
						System.out.print("\nPlease enter Item to be searched: ");
						searchName = stdin.readLine();
						int pos = list.searchItem(searchName);
						if (pos == -1)
							System.out.println("\nEntry is not in your Item List");
						else
							System.out.println("\n" + searchName + " is listed as number " + (pos+1));
						break;
						
						
			/*case 'E' :
						System.out.print("\nPlease enter lastname of Entry to be Edited: ");
						searchName = stdin.readLine();
						editEntry(list,searchName);
						break;*/
												
			case 'Q' : 	System.out.println("\nGOODBYE..."); 
					   	System.exit(0);
					   	break;
			
			default : System.out.println("\nWrong choice!");
			
		}
				
		} while (choice !='Q');
		
					
	}//end - main
	
	public static void addToList(ItemList list) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		System.out.println("---ITEM---\n");
		
		System.out.print("Code: ");
		String itemCode = stdin.readLine();
		
		System.out.print("Item Name: ");
		String itemName = stdin.readLine();
		
		System.out.print("Unit: ");
		String unit = stdin.readLine();
		
		//Quantity is not a factor is this test
		System.out.print("Quantity: ");
		int qty = Integer.parseInt(stdin.readLine());
		
		System.out.print("Unit Price: ");
		double unitP = Double.parseDouble(stdin.readLine());
		
		Item product = new Item(itemName,itemCode,unit,unitP);
		
		list.addElement(product);
	
	}// end addToList
	
	public static void removeItem(ItemList list, String searchName)
	{	
			int pos = list.searchItem(searchName);
					
			if(pos == -1)
				System.out.println("\nCompany has no Item with this name");
		
			else{
				list.removeElementAt(pos);
				System.out.println(searchName+" Item has been removed");
				}
		
	}//end removeItem

}//end class
	
	
