package borromeo.pos.model;
import java.io.*;
import java.util.*;

public class DepartmentList extends Vector {
	
	public DepartmentList()
	{
		super();
	}
	
	public void removeDepartment(String searchName) throws IOException
	{	
			
		File theFile = new File("department.bin");
		FileOutputStream out = new FileOutputStream(theFile);
			
		int pos = searchDepartment(searchName);
					
		if(pos == -1)
			System.out.println("\nCompany has no Department with this name");
		
		else{
			removeElementAt(pos);
			saveDeptList(out);
			System.out.println(searchName+" Department has been removed");
			}
		
	}//end removeDepartment
	
	public void displayDepartment() {
		
		if(isEmpty())
			System.out.println("\nCompany has no Departments");
		else{
			System.out.println("\nCODE        DEPARTMENT\n");
			for(int i=0; i<size(); i++) 
				System.out.println((ReceivingDepartment)elementAt(i));
		}
		
    } //end-display
    
    public int searchDepartment(String searchName){
	  
		for(int i=0; i<size(); i++){
			ReceivingDepartment deptName = (ReceivingDepartment)elementAt(i);
			if(searchName.toUpperCase().equals(deptName.getDepartmentName().toUpperCase().toString()))
				return i;
		}//end for
			
		return -1;
	}//end search
	
	public void sortDepartment() 
	{
		int j;
	
		for(int i=1; i<size(); i++){
		
			ReceivingDepartment temp = (ReceivingDepartment)elementAt(i);
			String index = temp.getDepartmentName().toString();
			j=i;
			while((j>0) && ((ReceivingDepartment)elementAt(j-1)).getDepartmentName().toString().compareTo(index) > 0){
				set(j,elementAt(j-1));
				j=j-1;
			}
			
			set(j,temp);	
		}
		
	} //end-sort
	
	public void save(String filename) throws IOException {
	    FileWriter fout = new FileWriter(filename);
	    fout.write("CODE        DEPARTMENT\n\n");
	    for(int i=0; i<size(); i++){
	    	ReceivingDepartment dept = (ReceivingDepartment)elementAt(i);
			fout.write(dept.getDepartmentCode()+"        "+dept.getDepartmentName()+"\n");
		}	
 		fout.close();
    } //end-save

	 ///////////////////////////////// Saving by Serialization ////////////////////////////////
    
    public void saveDeptList(FileOutputStream outStream) throws IOException 
	{
		ObjectOutputStream ooStream = new ObjectOutputStream(outStream);
		
		ooStream.writeObject(this);
		ooStream.flush();				
	}//end saveDeptList 
	
	public DepartmentList loadDeptList(FileInputStream inStream) 
			throws IOException, ClassNotFoundException {
		ObjectInputStream ioStream = new ObjectInputStream(inStream);
			
		return ((DepartmentList) ioStream.readObject()) ;
				
	}//end loadDeptList 

}//end class
	
	
