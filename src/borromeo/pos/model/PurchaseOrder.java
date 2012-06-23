package borromeo.pos.model;

import java.text.*;
import java.io.*;
import java.util.*;

public class PurchaseOrder implements Serializable
{
	
	private Supplier vendor;
	private ReceivingDepartment department;
	private Vector itemPO;
	private String PONum;
	private String referenceNum;
	private String term;
	private int month;
	private int day;
	private int year;
	public double totalPO;

	public PurchaseOrder()
	{
		vendor = new Supplier();
		department = new ReceivingDepartment();
		itemPO = null;
		PONum = "";
		referenceNum = "";
		term = "";
		month = 0;
		day = 0;
		year = 0;
		totalPO = 0.0;
	}
	
	public PurchaseOrder(Vector iPO,String supCode, String compName,String stNum,String st, String location, String ct, 
						String cntry,String deptCode,String deptName,String PO, String refNum, String POTerm, 
						int m, int d, int y)
	{
		vendor = new Supplier(supCode,compName,stNum,st,location,ct,cntry);
		department = new ReceivingDepartment(deptCode,deptName);
		itemPO = iPO;
		PONum = PO;
		referenceNum = refNum;
		term = POTerm;
		month = m;
		day = d;
		year = y;
		//setTotalPO(iPO);
	}
	
	public PurchaseOrder(Vector iPO,String compName,String stNum,String st, String location, String ct, 
						String cntry,String deptName,String PO, String refNum, String POTerm, 
						int m, int d, int y)
	{
		vendor = new Supplier(compName,stNum,st,location,ct,cntry);
		department = new ReceivingDepartment(deptName);
		itemPO = iPO;
		PONum = PO;
		referenceNum = refNum;
		term = POTerm;
		month = m;
		day = d;
		year = y;
		//setTotalPO(iPO);
		
	}
	
	public void setPurchaseOrder(Vector iPO,String supCode, String compName,String stNum,String st, String location, String ct, 
						String cntry,String deptCode,String deptName,String PO, String refNum, String POTerm, 
						int m, int d, int y)
	{
		vendor.setSupplier(supCode,compName,stNum,st,location,ct,cntry);
		department.setDepartment(deptCode, deptName);
		itemPO = iPO;
		PONum = PO;
		referenceNum = refNum;
		term = POTerm;
		month = m;
		day = d;
		year = y;
	}
	
	public Supplier getSupplier()
	{
		return vendor;
	}
	
	public ReceivingDepartment getDepartment()
	{
		return department;
	}
	
	public String getPONumber()
	{
		return PONum;
	}
	
	public String getReferenceNum()
	{
		return referenceNum;
	}
	
	public String getTerm()
	{
		return term;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public double getTotalPO()
	{
		return totalPO;
	}
	
	public Vector getItemPO()
	{
		return itemPO;
	}
	
	public String toString()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		return ("\nPO#: "+PONum+"\t\tPO Date: "+month+"-"+day+"-"+year+"\n"+
				"Vendor's Name: "+vendor.getCompanyName()+"\t\tRef #: "+referenceNum+"\n"+
				"Receiving Department: "+department.getDepartmentName()+"\t\tTerm: "+term+"\n\n");
	}
	
	public String toString1()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		return ("PO#: "+PONum+"\t\tPO Date: "+month+"-"+day+"-"+year+"\n"+
				"Vendor's Name: "+vendor.getCompanyName()+"\t\tRef #: "+referenceNum+"\n"+
				"Receiving Department: "+department.getDepartmentName()+"\t\tTerm: "+term+"\n"+"\n\nTotal:"
				+twoDec.format(getTotalPO())+"\n");
	}
	
	public String toString2()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		return ("PO#: "+PONum+"\t\tPO Date: "+month+"-"+day+"-"+year+"\n"+
				"Vendor's Name: "+vendor.getCompanyName()+"\t\tRef #: "+referenceNum+"\n"+
				"Receiving Department: "+department.getDepartmentName()+"\t\tTerm: "+term+"\n");
	}
}//end class
	
	
