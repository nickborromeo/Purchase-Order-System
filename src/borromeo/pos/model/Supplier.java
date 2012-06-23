package borromeo.pos.model;
import java.io.*;

public class Supplier implements Serializable{
	
	private Address place;
	private String supplierCode;
	private String companyName;
	
	public Supplier()
	{
		place = new Address();
		supplierCode = "";
		companyName = "";
	}
	
	public Supplier(String supCode, String compName,String stNum, 
					String st, String location, String ct, String cntry)
	{
		place = new Address(stNum,st,location,ct,cntry);
		supplierCode = supCode;
		companyName = compName;
	}
	
	public Supplier(String compName,String stNum,String st, String location, String ct, String cntry)
	{
		place = new Address(stNum,st,location,ct,cntry);
		companyName = compName;
	}
	
	public void setSupplier(String supCode, String compName,String stNum, 
							String st, String location, String ct, String cntry)
	{
		place.setAddress(stNum,st,location,ct,cntry);
		supplierCode = supCode;
		companyName = compName;
	}
	
	public String getSupplierCode()
	{
		return supplierCode;
	}
	
	public String getCompanyName()
	{
		return companyName;
	}
	
	public Address getAddress()
	{
		return place;
	}
	
	/*public Supplier uppercaseSupplier()
	{
		Supplier temp = new Supplier();
		
		temp.supplierCode = supplierCode;
		temp.companyName = companyName.toUpperCase();
		
		return temp;
	}*/
	
	public String toString()
	{
		return (supplierCode+"       "+companyName+"        "+place.toString());
	}
}//end class
	
	
