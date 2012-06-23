package borromeo.pos.model;
import java.io.*;
import java.util.*;

public class SupplierList extends Vector {
	
	public SupplierList()
	{
		super();
	}
	
	public void removeSupplier(String searchName) throws IOException
	{
		File theFile = new File("supplier.bin");
		FileOutputStream out = new FileOutputStream(theFile);
		
		for(int i=0; i<size(); i++){
			
			int pos = searchSupplier(searchName);
					
			if(pos == -1)
				System.out.println("\nCompany has no Supplier with this name");
		
			else{
				removeElementAt(pos);
				saveSupplierList(out);
				System.out.println(searchName+" has been removed");
				}
		}//end for
		
	}//end removeSupplier

	
	public void displaySupplier() {
		
		if(isEmpty())
			System.out.println("\nCompany has no Suppliers");
		else{
			System.out.println("\nCODE        SUPPLIER        ADDRESS\n");
			for(int i=0; i<size(); i++) 
				System.out.println((Supplier)elementAt(i));
		}
		
    } //end-display
    
    public int searchSupplier(String searchName){
	  
		for(int i=0; i<size(); i++){
			Supplier vendorName = (Supplier)elementAt(i);
			if(searchName.toUpperCase().equals(vendorName.getCompanyName().toUpperCase().toString()))
				return i;
		}//end for
			
		return -1;
	}//end search
	
	//sort by Supplier
	public void sortSupplier() 
	{
		int j;
	
		for(int i=1; i<size(); i++){
		
			Supplier temp = (Supplier)elementAt(i);
			String index = temp.getCompanyName().toString();
			j=i;
			while((j>0) && 
				((Supplier)elementAt(j-1)).getCompanyName().toString().compareTo(index) > 0){
				set(j,elementAt(j-1));
				j=j-1;
			}
			
			set(j,temp);	
		}
		
	} //end-sortSupplier
	
	public void save(String filename) throws IOException {
	    FileWriter fout = new FileWriter(filename);
	    fout.write("CODE        SUPPLIER\n\n");
	    for(int i=0; i<size(); i++){
	    	Supplier vendor = (Supplier)elementAt(i);
			fout.write(vendor.getSupplierCode()+"        "+vendor.getCompanyName()+"\n");
		}	
 		fout.close();
    } //end-save
    
    ///////////////////////////////// Saving by Serialization ////////////////////////////////
    
    public void saveSupplierList(FileOutputStream outStream) throws IOException 
	{
		ObjectOutputStream ooStream = new ObjectOutputStream(outStream);
		
		ooStream.writeObject(this);
		ooStream.flush();				
	}//end saveSupplierList 
	
	public SupplierList loadSupplierList(FileInputStream inStream) 
			throws IOException, ClassNotFoundException {
		ObjectInputStream ioStream = new ObjectInputStream(inStream);
			
		return ((SupplierList) ioStream.readObject()) ;
				
	}//end loadSupplierList 

	
}//end class
	
	
