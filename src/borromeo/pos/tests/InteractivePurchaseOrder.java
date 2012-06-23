package borromeo.pos.tests;

//Receiving PurchaseOrder Tester


import java.io.*;
import java.util.*;
import java.text.*;

import borromeo.pos.model.DepartmentList;
import borromeo.pos.model.Item;
import borromeo.pos.model.ItemList;
import borromeo.pos.model.ItemPO;
import borromeo.pos.model.ItemPOList;
import borromeo.pos.model.PurchaseOrder;
import borromeo.pos.model.PurchaseOrderList;
import borromeo.pos.model.ReceivingDepartment;
import borromeo.pos.model.Supplier;
import borromeo.pos.model.SupplierList;

public class InteractivePurchaseOrder{
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
	/////////////////////////////////////	Serialization of Department	//////////////////////////////////
		File theFile=null;
		FileInputStream in = null;  
	
		try {
			theFile = new File("department.bin");
			in = new FileInputStream(theFile);
		} catch (FileNotFoundException e) {
			System.out.println("No Departments yet!");
		}
		
		DepartmentList dept = new DepartmentList();
		
		try {
			dept = dept.loadDeptList(in);
			System.out.println("Loading Department List....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (NullPointerException n) {		
		
		}
		
		
	/////////////////////////////////////	Serialization of Supplier	//////////////////////////////////
		
		File theFile1=null;
		FileInputStream in1 = null;  
	
		try {
			theFile1 = new File("supplier.bin");
			in1 = new FileInputStream(theFile1);
		} catch (FileNotFoundException e) {
			System.out.println("No Suppliers yet!");
		}
		
		SupplierList vendor = new SupplierList();
		
		try {
			vendor = vendor.loadSupplierList(in1);
			System.out.println("Loading Supplier List....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (NullPointerException n) {		
		
		}
		
		
	/////////////////////////////////////	Serialization of Purchase Order	//////////////////////////////////
		
		File theFile2=null;
		FileInputStream in2 = null;  
	
		try {
			theFile2 = new File("purchaseOrder.bin");
			in2 = new FileInputStream(theFile2);
		} catch (FileNotFoundException e) {
			System.out.println("No Purchase Orders yet!");
		}
		
		PurchaseOrderList PO = new PurchaseOrderList();
		
		try {
			PO = PO.loadPOList(in2);
			System.out.println("Loading Purchase Order List....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (NullPointerException n) {		
		
		}
		
		
	/////////////////////////////////////	Serialization of Item 	//////////////////////////////////
		
		File theFile3=null;
		FileInputStream in3 = null;  
	
		try {
			theFile3 = new File("item.bin");
			in3 = new FileInputStream(theFile3);
		} catch (FileNotFoundException e) {
			System.out.println("No Items yet!");
		}
		
		ItemList prod = new ItemList();
			
		try {
			prod = prod.loadItemList(in3);
			System.out.println("Loading Item List...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (NullPointerException n) {		
		
		}
		
	
		
	/////////////////////////////////////	Serialization of Item Purchase	//////////////////////////////////
		
		File theFile4=null;
		FileInputStream in4 = null;  
	
		try {
			theFile4 = new File("itemPO.bin");
			in4 = new FileInputStream(theFile4);
		} catch (FileNotFoundException e) {
			
		}
		
		ItemPOList itemPO = new ItemPOList();
		
		try {
			itemPO = itemPO.loadItemPOList(in4);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (NullPointerException n) {		
		
		}
		
		/////////////////////////////////////	END Serialization 	//////////////////////////////////
		
		char choice = '*';
		int num = 0; 
	
		do {
		System.out.println("\nA - Add Purchase Order ");
		System.out.println("B - Add Supplier ");
		System.out.println("C - Add Item ");
		System.out.println("D - Add Department ");
		System.out.println("E - Remove Purchase Order ");
		System.out.println("F - Remove Supplier ");
		System.out.println("G - Remove Item ");
		System.out.println("H - Remove Department ");
		System.out.println("I - Show all Purchase Orders ");
		System.out.println("J - Show all Suppliers ");
		System.out.println("K - Show all Items ");
		System.out.println("L - Show all Departments ");
		System.out.println("M - Display Sorted Date ");
		System.out.println("N - PO Summary ");
		System.out.println("Q - Quit the Program ");
		
		String input;
		System.out.print("\nYour choice : ");
		input = stdin.readLine().toUpperCase();
		boolean ok;
		int month=0,day=0,year=0;
	
		do{
			ok = true;
			try{
				choice = input.charAt(0);
			}catch(Exception e){
				System.out.println("Enter a valid choice");
			}
		}while(!ok);
		
		String search = "";
			
		switch (choice)  {
			
			case 'A' : 	
				addPO(PO);
				//addPOItem(itemPO,itemList,num);
				break;
			
			case 'B' :
				addSupplier(vendor);
				break;
				
			case 'C' :
				addItem(prod);
				break;
				
			case 'D' :
				addDepartment(dept);
				break;
			
			case 'E' :
				System.out.print("Enter PO Number: ");
				search = stdin.readLine();
				PO.removePurchaseOrder(search);
				break;
				
			case 'F' :
				System.out.print("Enter Supplier: ");
				search = stdin.readLine();
				vendor.removeSupplier(search);
				break;
				
			case 'G' :
				System.out.print("Enter Item: ");
				search = stdin.readLine();
				prod.removeItem(search);
				break;
				
			case 'H' :
				System.out.print("Enter Department: ");
				search = stdin.readLine();
				dept.removeDepartment(search);
				break;
			
			case 'I' :
				PO.sortPurchaseOrder();
				PO.displayPurchaseOrder();
				break;
				
			case 'J' :
				vendor.sortSupplier();
				vendor.displaySupplier();
				break;
				
			case 'K' :
				prod.sortItem();
				prod.displayItem();
				break;
				
			case 'L' :
				dept.sortDepartment();
				dept.displayDepartment();
				break;
				
			case 'M' :	
				PO.sortMonth();
				PO.displayPurchaseOrder();
				break;
			
			case 'N' :
				PO.sortMonth();
				System.out.println("Enter First Date ");		//enter the first date
				do{
					System.out.print("Month: ");
					ok = true;
					try{
						month = Integer.parseInt(stdin.readLine());
					}catch(Exception e){
						ok =false;
						System.out.println("Please input correct month number");
					}	
				}while(!(month>0 && month <13));
		
				do{
					System.out.print("Day: ");
					ok = true;
					try{
						day = Integer.parseInt(stdin.readLine());
					}catch(Exception e){
						ok =false;
						System.out.println("Please input correct day of the month");
					}	
				}while(!(day>0 && day<32));
		
				do{
					System.out.print("Year: ");
						ok = true;
					try{
						year = Integer.parseInt(stdin.readLine());
					}catch(Exception e){
						ok =false;
						System.out.println("Please input correct year");
					}	
				}while(!(year>=2000 && year<=2007));
				int first = PO.searchFirstDate(month,day,year);
				
				System.out.println("Enter Second Date ");		//enter the second date
				do{
					System.out.print("Month: ");
					ok = true;
					try{
						month = Integer.parseInt(stdin.readLine());
					}catch(Exception e){
						ok =false;
						System.out.println("Please input correct month number");
					}	
				}while(!(month>0 && month <13));
		
				do{
					System.out.print("Day: ");
					ok = true;
					try{
						day = Integer.parseInt(stdin.readLine());
					}catch(Exception e){
						ok =false;
						System.out.println("Please input correct day of the month");
					}	
				}while(!(day>0 && day<32));
		
				do{
					System.out.print("Year: ");
						ok = true;
					try{
						year = Integer.parseInt(stdin.readLine());
					}catch(Exception e){
						ok =false;
						System.out.println("Please input correct year");
					}	
				}while(!(year>=2000 && year<=2007));
				int second = PO.searchSecondDate(month,day,year);
				PO.displayPOSummary(first,second);
				break;
																	
			case 'Q' : 	System.out.println("\nGOODBYE..."); 
					   	System.exit(0);
					   	break;
			
			default : System.out.println("\nWrong choice!");
			
		}
				
		} while (choice !='Q');
		
					
	}//end - main

	public static void addPO(PurchaseOrderList PO) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
			
		File theFile = new File("purchaseOrder.bin");
		FileOutputStream out = new FileOutputStream(theFile);
		
		String supCode,vendorName,streetNum,street,placeInCity,city,country,deptCode,deptName,itemCode,itemName;
		String unit,PONum,referenceNum,term;
		int month =0,day=0,year=0;
		boolean ok;
		
		System.out.println("\n----COMPANY----\n");
		System.out.print("Code: ");
		supCode = stdin.readLine();
		System.out.print("Vendor's Name: ");
		vendorName = stdin.readLine();
		System.out.println("\n----ADDRESS----\n");
		System.out.print("StreetNum: ");
		streetNum = stdin.readLine();
		System.out.print("Street: ");
		street = stdin.readLine();
		System.out.print("Place in city: ");
		placeInCity = stdin.readLine();
		System.out.print("City: ");
		city = stdin.readLine();
		System.out.print("Country: ");
		country = stdin.readLine();
		System.out.println("\n----DEPARTMENT----\n");
		System.out.print("Code: ");
		deptCode = stdin.readLine();
		System.out.print("Department Name: ");
		deptName = stdin.readLine();
		System.out.println("\n----PO DETAILS----\n");
		System.out.print("PO Number: ");
		PONum = stdin.readLine();
		System.out.print("Reference Number: ");
		referenceNum = stdin.readLine();
		System.out.print("Term: ");
		term = stdin.readLine();
		do{
			System.out.print("Month: ");
			ok = true;
			try{
				month = Integer.parseInt(stdin.readLine());
			}catch(Exception e){
					ok =false;
					System.out.println("Please input correct month number");
			}	
		}while(!(month>0 && month <13));
		
		do{
			System.out.print("Day: ");
			ok = true;
			try{
				day = Integer.parseInt(stdin.readLine());
			}catch(Exception e){
					ok =false;
					System.out.println("Please input correct day of the month");
			}	
		}while(!(day>0 && day<32));
		
		do{
			System.out.print("Year: ");
			ok = true;
			try{
				year = Integer.parseInt(stdin.readLine());
			}catch(Exception e){
					ok =false;
					System.out.println("Please input correct year");
			}	
		}while(!(year>=2000 && year<=2007));
		
		System.out.println("---ITEM---\n");
		System.out.print("Enter number of Items: ");
		int num = Integer.parseInt(stdin.readLine());
		
		int qty=0;
		double unitP=0.0;
		Vector list = new Vector(); 		
		
		for(int i=0; i<num; i++){
		
			System.out.print("Code: ");
			String iCode = stdin.readLine();
			System.out.print("Item Name: ");
			String iName = stdin.readLine();
			System.out.print("Unit: ");
			String unt = stdin.readLine();
			do{
				System.out.print("Quantity: ");
				ok = true;
				try{
					qty = Integer.parseInt(stdin.readLine());
				}catch(Exception e){
					ok = false;
					System.out.println("Please input a plausible quantity");
				}	
			}while(!ok);
				
			do{
				System.out.print("Unit Price: ");
				ok = true;
				try{
					unitP = Double.parseDouble(stdin.readLine());
				}catch(Exception e){
					ok =false;
					System.out.println("Please input a unit price");
				}	
			}while(!ok);
			
			ItemPO product = new ItemPO(iName,iCode,unt,qty,unitP,num);
			list.addElement(product);
		}//end for
		
		PurchaseOrder POTrans = new PurchaseOrder(list,supCode,vendorName,streetNum,street,placeInCity,city,country,
													deptCode,deptName,PONum,referenceNum,term,month,day,year);
													
		
		PO.addElement(POTrans);
		PO.savePOList(out);
	}// end addToList
		
	public static void addItem(ItemList list) throws IOException 
	{
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		File theFile = new File("item.bin");
		FileOutputStream out = new FileOutputStream(theFile);
		
		System.out.println("---ITEM---\n");
		System.out.print("Code: ");
		String itemCode = stdin.readLine();
		System.out.print("Item Name: ");
		String itemName = stdin.readLine();
		System.out.print("Unit: ");
		String unit = stdin.readLine();
		
		Item prod = new Item(itemName,itemCode,unit);
		
		list.addElement(prod);
		list.saveItemList(out);
		
	}//end addITem
	
	public static void addSupplier(SupplierList list) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		File theFile = new File("supplier.bin");
		FileOutputStream out = new FileOutputStream(theFile);
		
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
		list.saveSupplierList(out);
	
	}// end addSupplier
	
	public static void addDepartment(DepartmentList list) throws IOException {
		
		BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
		
		File theFile = new File("department.bin");
		FileOutputStream out = new FileOutputStream(theFile);
		
		System.out.println("---DEPARTMENT---\n");
		System.out.print("CODE: ");
		String deptCode = stdin.readLine();
		System.out.print("Department: ");
		String deptName = stdin.readLine();
		
		ReceivingDepartment entry = new ReceivingDepartment(deptCode,deptName);
		
		list.addElement(entry);
		list.saveDeptList(out);
	
	}// end addDepartment

}//end class
	
	
