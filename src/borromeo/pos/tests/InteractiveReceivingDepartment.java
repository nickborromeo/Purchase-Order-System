package borromeo.pos.tests;
//Receiving Department Tester


import java.io.*;
import java.util.*;

import borromeo.pos.model.DepartmentList;
import borromeo.pos.model.ReceivingDepartment;

public class InteractiveReceivingDepartment{
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		DepartmentList list = new DepartmentList();
		char choice = '*'; 
	
		System.out.println("\n\n**************Main Menu**************");
		System.out.println("A - Add a Department ");
		System.out.println("R - Remove a Department ");
		System.out.println("D - Show all Departments ");
		System.out.println("S - Search for a Department ");
		//System.out.println("E - Edit a Department ");
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
						removeDepartment(list,searchName);			
				       	break;
			
			case 'D' : 
						list.sortDepartment();
						list.displayDepartment(); 
					   	break;
					   	
			case 'S' :	list.sortDepartment();
						System.out.print("\nPlease enter lastname of Entry to be Searched: ");
						searchName = stdin.readLine();
						int pos = list.searchDepartment(searchName);
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
	
	public static void addToList(DepartmentList list) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		System.out.println("---DEPARTMENT---\n");
		
		System.out.print("CODE: ");
		String deptCode = stdin.readLine();
		
		System.out.print("Department: ");
		String deptName = stdin.readLine();
		
		ReceivingDepartment entry = new ReceivingDepartment(deptCode,deptName);
		
		list.addElement(entry);
	
	}// end addToList
	
	public static void removeDepartment(DepartmentList list, String searchName)
	{	
			int pos = list.searchDepartment(searchName);
					
			if(pos == -1)
				System.out.println("\nCompany has no Department with this name");
		
			else{
				list.removeElementAt(pos);
				System.out.println(searchName+" Department has been removed");
				}
		
	}//end removeDepartment

}//end class
	
	
