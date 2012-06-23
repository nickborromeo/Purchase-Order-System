package borromeo.pos.model;
import java.io.*;
import java.util.*;


public class ItemPOList extends Vector {
	
	public ItemPOList()
	{
		super();
	}
	
	public Vector itemSubList(int startElement, int howMany)
	{
		int endElement = (startElement+howMany);
		
		Vector temp = new Vector();
		for(int i=startElement; i<endElement; i++)
		{
			temp.addElement((ItemPO)elementAt(i));
		}
		return temp;
	}
	
	public void removeItem(String searchName) throws IOException
	{	
	
		File theFile = new File("itemPO.bin");
		FileOutputStream out = new FileOutputStream(theFile);
		
		int pos = searchItem(searchName);
				
		if(pos == -1)
			System.out.println("\nCompany has no Item with this name");
		
		else{
			removeElementAt(pos);
			saveItemPOList(out);
			System.out.println(searchName+" Item has been removed");
		}
		
	}//end removeItem
    
    public int searchItem(String searchName){
	  
		for(int i=0; i<size(); i++){
			ItemPO itemName = (ItemPO)elementAt(i);
			if(searchName.toUpperCase().equals(itemName.getItemName().toUpperCase().toString()))
				return i;
		}//end for
			
		return -1;
	}//end search
	
	public void sortItem() 
	{
		int j;
	
		for(int i=1; i<size(); i++){
		
			ItemPO temp = (ItemPO)elementAt(i);
			String index = temp.getItemName().toString();
			j=i;
			while((j>0) && ((ItemPO)elementAt(j-1)).getItemName().toString().compareTo(index) > 0){
				set(j,elementAt(j-1));
				j=j-1;
			}
			
			set(j,temp);	
		}
		
	} //end-sort
	
	public void save(String filename) throws IOException {
	    FileWriter fout = new FileWriter(filename);
	    fout.write("CODE        DESCRIPTION        UNIT        COST\n\n");
	    for(int i=0; i<size(); i++){
	    	ItemPO item = (ItemPO)elementAt(i);
			fout.write(item.getItemCode()+"        "+item.getItemName()+item.getUnit()+
						"        "+item.getItemCost()+"\n");
		}	
 		fout.close();
    } //end-save
    
    ///////////////////////////////// Saving by Serialization  ////////////////////////////////
    
    public void saveItemPOList(FileOutputStream outStream) throws IOException 
	{
		ObjectOutputStream ooStream = new ObjectOutputStream(outStream);
		
		ooStream.writeObject(this);
		ooStream.flush();				
	}//end saveItemPOList 
	
	public ItemPOList loadItemPOList(FileInputStream inStream) 
			throws IOException, ClassNotFoundException {
		ObjectInputStream ioStream = new ObjectInputStream(inStream);
			
		return ((ItemPOList) ioStream.readObject()) ;
				
	}//end loadItemPOList 

	
}//end class
	
	
