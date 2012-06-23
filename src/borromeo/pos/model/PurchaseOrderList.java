package borromeo.pos.model;
import java.io.*;
import java.util.*;
import java.text.*;

public class PurchaseOrderList extends Vector {
	
	public PurchaseOrderList()
	{
		super();
	}	

	
	/////////////////////////////////      DISPLAY      ////////////////////////////////////////
    
    public void displayPOSummary(int first, int second) {
		
		if(isEmpty())
			System.out.println("Company has no PO's");
		else{
			for(int i=first; i<(second+1); i++){
				PurchaseOrder PO = (PurchaseOrder)elementAt(i);
				System.out.println("\n"+PO.toString1());
			} 
				
		}
		
    } //end-displayPurchaseOrder

    public void displayPurchaseOrder() {
		
		DecimalFormat twoDec = new DecimalFormat("#.00");
		int count = 0;
		if(isEmpty())
			System.out.println("Company has no PO's");
		else{
			for(int i=0; i<size(); i++){
				PurchaseOrder PO = (PurchaseOrder)elementAt(i);
				System.out.println("\n"+PO.toString1());
				for(int j=0; j<(PO.getItemPO()).size(); j++)
					System.out.println(PO.getItemPO().elementAt(j));
				
				System.out.println("\n"+twoDec.format(PO.getTotalPO()));
			}//end for
				
		}//end else
		
    } //end-displayPurchaseOrder
    
    /////////////////////////////////      SEARCHING      ////////////////////////////////////////
    
    //search by Purchase Order
    public int searchPurchaseOrder(String searchName){
	  
		for(int i=0; i<size(); i++){
			PurchaseOrder PO = (PurchaseOrder)elementAt(i);
			if(searchName.toUpperCase().equals(PO.getPONumber().toUpperCase().toString()))
				return i;
		}//end for
			
		return -1;
	}//end search
	
	public int searchPurchaseOrderSupplier(String searchName){
	  
		for(int i=0; i<size(); i++){
			PurchaseOrder PO = (PurchaseOrder)elementAt(i);
			if(searchName.toUpperCase().equals(PO.getSupplier().getCompanyName().toUpperCase().toString()))
				return i;
		}//end for
			
		return -1;
	}//end search
	
	public int searchPurchaseOrderDept(String searchName){
	  
		for(int i=0; i<size(); i++){
			PurchaseOrder PO = (PurchaseOrder)elementAt(i);
			if(searchName.toUpperCase().equals(PO.getDepartment().getDepartmentName().toUpperCase().toString()))
				return i;
		}//end for
			
		return -1;
	}//end search
	
	public int searchFirstDate(int month,int day, int year)
	{
		for(int i=0; i<size(); i++){
			PurchaseOrder PO = (PurchaseOrder)elementAt(i);
			if(month == (PO.getMonth()) && day == (PO.getDay()) && year == (PO.getYear()))
				return i;
		}//end for
			
		return -1;
		
	}//end searchFirstDate
	
	public int searchSecondDate(int month,int day, int year)
	{
		for(int i=0; i<size(); i++){
			PurchaseOrder PO = (PurchaseOrder)elementAt(i);
			if(month == (PO.getMonth()) && day == (PO.getDay()) && year == (PO.getYear()))
				return i;
		}//end for
			
		return -1;
		
	}//end searchFirstDate
	

	/////////////////////////////////      SORTING      //////////////////////////////////////////
	
	//sort by Purchase Order
	public void sortPurchaseOrder() 
	{
		int j;
	
		for(int i=1; i<size(); i++){
		
			PurchaseOrder temp = (PurchaseOrder)elementAt(i);
			String index = temp.getPONumber().toString();
			j=i;
			while((j>0) && ((PurchaseOrder)elementAt(j-1)).getPONumber().toString().compareTo(index) > 0){
				set(j,elementAt(j-1));
				j=j-1;
			}
			
			set(j,temp);	
		}
		
	} //end-sortPurchaseOrder
	
	public void sortPurchaseOrderSupplier() 
	{
		int j;
	
		for(int i=1; i<size(); i++){
		
			PurchaseOrder temp = (PurchaseOrder)elementAt(i);
			String index = temp.getSupplier().getCompanyName().toString();
			j=i;
			while((j>0) && ((PurchaseOrder)elementAt(j-1)).getSupplier().getCompanyName().toString().compareTo(index) > 0){
				set(j,elementAt(j-1));
				j=j-1;
			}
			
			set(j,temp);	
		}
		
	} //end-sortPurchaseOrder
	
	public void sortPurchaseOrderDepartment() 
	{
		int j;
	
		for(int i=1; i<size(); i++){
		
			PurchaseOrder temp = (PurchaseOrder)elementAt(i);
			String index = temp.getDepartment().getDepartmentName().toString();
			j=i;
			while((j>0) && ((PurchaseOrder)elementAt(j-1)).getDepartment().getDepartmentName().toString().compareTo(index) > 0){
				set(j,elementAt(j-1));
				j=j-1;
			}
			
			set(j,temp);	
		}
		
	} //end-sortPurchaseOrder
	
	//sort by date
	public void sortYear()
	{
		int j;
		
		for(int i=0; i<size(); i++)
		{
			PurchaseOrder temp = (PurchaseOrder)elementAt(i);
			int index = temp.getYear();
			j=i;
			while((j>0) && ((PurchaseOrder)elementAt(j-1)).getYear() > index)
			{			
				set(j,elementAt(j-1));
				j=j-1;
				
			}
			set(j,temp);
			
			
		}
	}//end sortDate
	
	//sort by day
	public void sortDay()
	{
		int j;
		
		for(int i=0; i<size(); i++)
		{
			PurchaseOrder temp = (PurchaseOrder)elementAt(i);
			int index = temp.getDay();
			j=i;
			while((j>0) && ((PurchaseOrder)elementAt(j-1)).getDay() > index)
			{			
				set(j,elementAt(j-1));
				j=j-1;
			}
			set(j,temp);
		}
		
	}//end sortDay
	
	//sort by month
	public void sortMonth()
	{
		int j;
		
		for(int i=0; i<size(); i++)
		{
			PurchaseOrder temp = (PurchaseOrder)elementAt(i);
			int index = temp.getMonth();
			j=i;
			while((j>0) && ((PurchaseOrder)elementAt(j-1)).getMonth() > index)
			{			
				set(j,elementAt(j-1));
				j=j-1;
			}
			set(j,temp);
			
			sortDay();
			sortYear();
		}
		
	}//end sortMonth
	
	
	/////////////////////////////////      COMPUTATIONS      //////////////////////////////////////////
	
	//compute total amount for supplier
	public double computeTotalSupplier(String supplier)
	{
		double totalPO = 0.0;
		
		for(int i=0; i<size(); i++)
		{
			PurchaseOrder PO = (PurchaseOrder)elementAt(i);
			if(supplier.equals(PO.getSupplier().getCompanyName()))
			{
				//totalPO += PO.getItem().getItemCost();
			}
			
		}//end for
		
		return totalPO;	
	}//end computeTotalPO
	
	/////////////////////////////////      SAVING      //////////////////////////////////////////
	
	//save PO Transactions
	public void savePOTrans(String filename) throws IOException {
	    FileWriter fout = new FileWriter(filename);
	    for(int i=0; i<size(); i++){
	    	PurchaseOrder PO = (PurchaseOrder)elementAt(i);
			fout.write("PO#: "+PO.getPONumber()+"\t\tPO Date: "+PO.getMonth()+"-"+PO.getDay()+"-"+PO.getYear()+"\n"+
				"Vendor's Name: "+PO.getSupplier().getCompanyName()+"\t\tRef #: "+PO.getReferenceNum()+"\n"+
				"Receiving Department: "+PO.getDepartment().getDepartmentName()+"\t\tTerm: "+PO.getTerm()+"\n");
		}	
 		fout.close();
    } //end-savePOTransctions
	
	////////////////////////////////////  PO TRANSACTIONS ///////////////////////////////////
	
	public void removePurchaseOrder(String searchName)
	{	
			int pos = searchPurchaseOrder(searchName);
					
			if(pos == -1)
				System.out.println("\nCompany has no Purchase Order with this number");
		
			else{
				removeElementAt(pos);
				System.out.println(searchName+" Purchase Order has been deleted");
				}
		
	}//end removePurchaseOrder
	
	public void savePOList(FileOutputStream outStream) throws IOException {
		ObjectOutputStream ooStream = new ObjectOutputStream(outStream);
		
		ooStream.writeObject(this);
		ooStream.flush();				
	} 
	
	public PurchaseOrderList loadPOList(FileInputStream inStream) 
			throws IOException, ClassNotFoundException {
		ObjectInputStream ioStream = new ObjectInputStream(inStream);
			
		return ((PurchaseOrderList) ioStream.readObject()) ;
				
	}
	
}//end class
	
	
