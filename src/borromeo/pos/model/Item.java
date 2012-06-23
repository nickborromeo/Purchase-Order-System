package borromeo.pos.model;
import java.text.*;
import java.io.*;

public class Item implements Serializable
{
	
	private String itemName;
	private String itemCode;
	private String unit;
	private double unitP;
	
	public Item()
	{
		itemName = "";
		itemCode = "";
		unit = "";
		unitP = 0.0;
		
	}
	
	public Item(String iName, String iCode, String unt)
	{
		setItem(iName,iCode,unt);
	}
	
	public Item(String iName, String iCode, String unt,double untP)
	{
		setItem(iName,iCode,unt,untP);
	}
	
	public void setItem(String iName,String iCode,String unt)
	{
		itemName = iName;
		itemCode = iCode;
		unit = unt;
	}
	
	public void setItem(String iName,String iCode,String unt,double untP)
	{
		itemName = iName;
		itemCode = iCode;
		unit = unt;
		unitP = untP;
	}
	
	
	public String getItemName()
	{
		return itemName;
	}
	
	public String getItemCode()
	{
		return itemCode;
	}
	
	public String getUnit()
	{
		return unit;
	}
	
	public double getUnitPrice()
	{
		return unitP;
	}
	
	public Item uppercaseItemName()
	{	
		Item temp = new Item();
		
		temp.itemName = itemName.toUpperCase();
		temp.itemCode = itemCode;
		temp.unit = unit;
		
		return temp;
	}
	
	public String toString() 
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		return (itemCode+"                                                 "
				+itemName+"                                                "
				+unit+"                                           "
				+twoDec.format(unitP));
	}
	
}//end class
	
	
	
	
	
	
	
