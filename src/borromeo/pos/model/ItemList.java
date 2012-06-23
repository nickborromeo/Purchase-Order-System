package borromeo.pos.model;
import java.io.*;
import java.util.*;

public class ItemList extends Vector {
	
	public ItemList()
	{
		super();
	}
	
	public void removeItem(String searchName) throws IOException
	{	
	
		File theFile = new File("item.bin");
		FileOutputStream out = new FileOutputStream(theFile);
		
		int pos = searchItem(searchName);
				
		if(pos == -1)
			System.out.println("\nCompany has no Item with this name");
	
		else{
			removeElementAt(pos);
			//saveItemList(out);
			System.out.println(searchName+" Item has been removed");
			}
		
	}//end removeItem
	
	public void displayItem() {
		
		if(isEmpty())
			System.out.println("\nCompany has no Items");
		else{
			System.out.println("\nCODE        ITEM        UNIT\n");
			for(int i=0; i<size(); i++) 
				System.out.println((Item)elementAt(i));
		}
		
    } //end-display
   
    
    public int searchItem(String searchName){
	  
		for(int i=0; i<size(); i++){
			Item itemName = (Item)elementAt(i);
			if(searchName.toUpperCase().equals(itemName.getItemName().toUpperCase().toString()))
				return i;
		}//end for
			
		return -1;
	}//end search
	
	public void sortItem() 
	{
		int j;
	
		for(int i=1; i<size(); i++){
		
			Item temp = (Item)elementAt(i);
			String index = temp.getItemName().toString();
			j=i;
			while((j>0) && ((Item)elementAt(j-1)).getItemName().toString().compareTo(index) > 0){
				set(j,elementAt(j-1));
				j=j-1;
			}
			
			set(j,temp);	
		}
		
	} //end-sort
	
	public void save(String filename) throws IOException {
	    FileWriter fout = new FileWriter(filename);
	   // fout.write("CODE        DESCRIPTION        UNIT\n\n");
	    for(int i=0; i<size(); i++){
	    	Item item = (Item)elementAt(i);
			fout.write(item.getItemCode()+"    "+item.getItemName()+"      "+item.getUnit()+"     "+item.getUnitPrice());
		}	
 		fout.close();
    } //end-save
    
    ///////////////////////////////// Saving by Serialization  ////////////////////////////////
    
    public void saveItemList(FileOutputStream itemOut) throws IOException 
	{
		
		ObjectOutputStream ooStream = new ObjectOutputStream(itemOut);
		
		ooStream.writeObject(this);
		ooStream.flush();				
	}//end saveItemList 
	
	public ItemList loadItemList(FileInputStream inStream) 
			throws IOException, ClassNotFoundException {
		ObjectInputStream ioStream = new ObjectInputStream(inStream);
			
		return ((ItemList) ioStream.readObject()) ;
				
	}//end loadItemList 
}//end class
	
	
