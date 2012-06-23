package borromeo.pos.tests;
//Interactive Supplier Tester


import java.io.*;
import java.util.*;

import borromeo.pos.model.Supplier;
import borromeo.pos.model.SupplierList;

public class InteractiveSupplier{
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		SupplierList list = new SupplierList();
		char choice = '*'; 
	
		System.out.println("\n\n**************Main Menu**************");
		System.out.println("A - Add a Supplier ");
		System.out.println("R - Remove a Supplier ");
		System.out.println("D - Show all Suppliers ");
		System.out.println("S - Search for a Supplier ");
		//System.out.println("E - Edit a Supplier ");
		System.out.println("Q - Quit the Program \n");
			
	    do {
		 
		String input;
		System.out.print("Your choice : ");
		input = stdin.readLine().toUpperCase();
		choice = input.charAt(0);
			
		switch (choice)  {
			
			case 'A' : 	addToList(list);
					   	break;
			
			case 'R' : 	System.out.print("\nEnter the Company Name: ");
						String searchName = stdin.readLine();
						removeSupplier(list,searchName);			
				       	break;
			
			case 'D' : 
						list.sortSupplier();
						list.displaySupplier(); 
					   	break;
					   	
			case 'S' :	list.sortSupplier();
						System.out.print("\nPlease enter lastname of Entry to be Searched: ");
						searchName = stdin.readLine();
						int pos = list.searchSupplier(searchName);
						if (pos == -1)
							System.out.println("\nEntry is not in your Address Book");
						else
							System.out.println("\nEntry with lastname " + searchName + 
												" is listed as number " + (pos+1));
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
	
	public static void addToList(SupplierList list) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		System.out.println("---SUPPLIER---\n");
		
		System.out.print("CODE: ");
		String supCode = stdin.readLine();
		
		System.out.print("SUPPLIER: ");
		String compName = stdin.readLine();
		
		System.out.println("---ADDRESS---\n");
		
		System.out.print("Street Number: ");
		String streetNum = stdin.readLine();
		
		System.out.print("Street: ");
		String street = stdin.readLine();
		
		System.out.print("Location: ");
		String location = stdin.readLine();
		
		System.out.print("City: ");
		String city = stdin.readLine();
		
		System.out.print("Country: ");
		String country = stdin.readLine();
		
		Supplier entry = new Supplier(supCode,compName,streetNum,street,location,city,country);
		
		list.addElement(entry);
	
	}// end addToList
	
	public static void removeSupplier(SupplierList list, String searchName)
	{
		for(int i=0; i<list.size(); i++){
			
			int pos = list.searchSupplier(searchName);
					
			if(pos == -1)
				System.out.println("\nCompany has no Supplier with this name");
		
			else{
				list.removeElementAt(pos);
				System.out.println(searchName+" has been removed");
				}
		}//end for
		
	}//end removeSupplier

}//end class
	
	
