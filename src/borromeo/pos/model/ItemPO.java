package borromeo.pos.model;
import java.text.*;
import java.io.*;

public class ItemPO implements Serializable
{
	
	private String itemName;
	private String itemCode;
	private String unit;
	private int quantity;
	private int firstNum;
	private int secondNum;
	private double unitPrice;
	private double itemCost;
	private double itemTotal;
	
	public ItemPO()
	{
		itemName = "";
		itemCode = "";
		unit = "";
		quantity = 0;
		unitPrice = 0.0;
		itemCost = 0.0;
		firstNum = 0;
		secondNum = 0;
	}
	
	public ItemPO(String iName, String iCode, String unt, int qty, double unitP,int first)
	{
		setItemPO(iName, iCode, unt, qty, unitP,first);
		setSecondNum(first);
		computeItemCost(qty,unitP);
	}
	
	public ItemPO(String iName, String iCode, String unt)
	{
		setItemPO(iName,iCode,unt);
	}
	
	public ItemPO(String iName, String unt, int qty, double unitP)
	{
		setItemPO(iName,unt,qty,unitP);
		computeItemCost(qty,unitP);
		
	}
	
	public void setItemPO(String iName,String unt,int qty,double unitP)
	{
		itemName = iName;
		unit = unt;
		quantity = qty;
		unitPrice = unitP;
	}
	
	public void setItemPO(String iName,String iCode,String unt,int qty,double unitP,int first)
	{
		itemName = iName;
		itemCode = iCode;
		unit = unt;
		quantity = qty;
		unitPrice = unitP;
		firstNum = first;
	}
	
	public void setItemPO(String iName,String iCode,String unt)
	{
		itemName = iName;
		itemCode = iCode;
		unit = unt;
		
	}
	
	public void computeItemCost(int qty, double unitP)
	{
		itemCost = (qty * unitP);
	}
	
	public void setSecondNum(int first)
	{
		secondNum += first;
	}
	
	public int getFirstNum()
	{
		return firstNum;
	}
	
	public int getSecondNum()
	{
		return secondNum;
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
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public double getUnitPrice()
	{
		return unitPrice;
	}
	
	public double getItemCost()
	{
		return itemCost;
	}
	
	public ItemPO uppercaseItemName()
	{	
		ItemPO temp = new ItemPO();
		
		temp.quantity = quantity;
		temp.itemName = itemName.toUpperCase();
		temp.itemCode = itemCode;
		temp.unit = unit;
		temp.unitPrice = unitPrice;
		temp.itemCost = itemCost;
		
		return temp;
	}
	
	public String toString()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		return (quantity+"    "+itemName+"    "+unit+"     "+twoDec.format(unitPrice)+"     "+twoDec.format(itemCost));
	}
	
	public String toString1()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		return (itemCode+"        "+itemName+"        "+unit+"        "+twoDec.format(itemCost));
	}
	
	public String toString2()
	{ 	
	
		DecimalFormat twoDec = new DecimalFormat("#.00");
		return ("        "+quantity+"                          "+
				itemName+"                              "+
				unit+"                       "+
				twoDec.format(unitPrice)+"                          "+
				twoDec.format(itemCost));
	}

}//end class
	
	
