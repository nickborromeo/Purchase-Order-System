package borromeo.pos.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

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

import java.util.*;
import java.io.*;
import java.text.*;

public class TabbedMenu extends ViewMode implements Serializable
{ 	

	//////////////////////////////MAIN///////////////////////////////////////	
		
	public static void main( String args[] ) throws IOException
	{
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		}catch(Exception e){
		}//*/
		
		TabbedMenu mainFrame = new TabbedMenu();
		mainFrame.setBounds(150,100,700,600);
		mainFrame.setVisible( true );
		mainFrame.setResizable(false);
		
		
	}//end main
	
	///////////////////////////////MODEL///////////////////////////////////////
	
	public static PurchaseOrderList POList = new PurchaseOrderList();
	public static ItemPOList itemPOList = new ItemPOList();
	public static SupplierList vendor = new SupplierList(); 
	public static DepartmentList department = new DepartmentList(); 
	public static ItemList item = new ItemList();
	public static PurchaseOrder purchase = new PurchaseOrder();
	
	////////////////////////////////VIEW///////////////////////////////////////
		//-------> view can be found in the class ViewMode.java
		
	//CONSTRUCTOR
	public TabbedMenu() 
	{
		super();
		setTitle( "Purchase Order System" );
		setBackground( Color.gray );
		
		//load Data
		loadFiles();
		
		//sort the Tables
		vendor.sortSupplier();
		item.sortItem();
		department.sortDepartment();
	
		
		//table data
		setSupplierTable();
		setItemTable();
		setDeptTable();
		
		//PO data
		setPOPane();
		setSupplierPane();
		setDepartmentPane();
		
		/////////////////////////LISTENERS////////////////////////////////////
		
		/////////////PAGE 1///////////////
		btAddItem.addActionListener(new AddPOItemListener());
		btRemoveItem.addActionListener(new RemovePOItemListener());
		btEditItem.addActionListener(new ReplacePOItemListener());
		btClearList.addActionListener(new ClearPOItemListener());
		btShowSupplier.addActionListener(new ShowSupplierListener());
		btShowDept.addActionListener(new ShowDeptListener());
		btShowItem.addActionListener(new ShowItemListener());
		btAddPO.addActionListener(new AddPOListener());
		
		/////////////PAGE 2////////////////
		btAddSupplier.addActionListener(new AddSupplierListener());
		btRemoveSupplier.addActionListener(new RemoveSupplierListener());
		btEditSupplier.addActionListener(new ReplaceSupplierListener());//*/
		
		
		/////////////PAGE 3////////////////
		btAddItem1.addActionListener(new AddItemListener());
		btRemoveItem1.addActionListener(new RemoveItemListener());
		btEditItem1.addActionListener(new ReplaceItemListener());//*/
		
		/////////////PAGE 4//////////////// 
		btAddDept.addActionListener(new AddDeptListener());
		btRemoveDept.addActionListener(new RemoveDeptListener());
		btEditDept.addActionListener(new ReplaceDeptListener());//*/
		
		/////////////PAGE 5//////////////// 
		btPassword.addActionListener(new LoginListener());
		btLogOut1.addActionListener(new LogOutListener());
		btLogOut2.addActionListener(new LogOutListener());
		btLogOut3.addActionListener(new LogOutListener());
		btSortPO.addActionListener(new SortReportsListener());
		btSortSupplier.addActionListener(new SortReportsListener());
		btSortDepartment.addActionListener(new SortReportsListener());
		btDateRange.addActionListener(new DateRangeListener());
		btIsolateSupplier.addActionListener(new IsolateSupplierListener());
		btIsolateDepartment.addActionListener(new IsolateDepartmentListener());
		

		addWindowListener(new MyWindowAdapter());
	}//end TabbedMenu
	
	
	//////////////////////////////CONTROLLERS/////////////////////////////////
	
	/*-------------------------- PURCHASE ORDER ------------------------------------*/
	
	private class AddPOListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			DecimalFormat twoDec = new DecimalFormat("#.00");
			try{
				String vendorName = tfName.getText().trim();
				
				String tokens[] = tfAddress.getText().trim().split(",");
				String streetNum = tokens[0].trim(); 
				String street = tokens[1].trim();
				String placeInCity = tokens[2].trim();
				String city = tokens[3].trim();
				String country = tokens[4].trim();
				
				String deptName = tfDept.getText().trim();
				String PONum = tfPONum.getText().trim();
				String referenceNum = tfRefNum.getText().trim();
				String term = tfTerm.getText().trim();
				int m = Integer.parseInt(""+month.getSelectedItem());
				int d = Integer.parseInt(""+day.getSelectedItem());
				int y = Integer.parseInt(""+year.getSelectedItem());
				
				Vector items = new Vector();
				for(int i=0; i<tbItemPOModel.getRowCount(); i++)
				{
					Vector val = new Vector();
					for(int j=0; j<tbItemPO.getColumnCount(); j++)
					{
						val.addElement(tbItemPO.getValueAt(i,j));
					}//end for
					items.addElement(val);
				}//end for
				
				PurchaseOrder POTrans = new PurchaseOrder(items,vendorName,streetNum,street,placeInCity,city,country,
															deptName,PONum,referenceNum,term,m,d,y);
				
				POList.addElement(POTrans);
					
				taPOSummary.append(POTrans.toString());
				taSupplierReport.append(POTrans.toString());
				taSummaryReport.append(POTrans.toString());
				
				for(int i=0; i<(POTrans.getItemPO()).size(); i++){
					taPOSummary.append(POTrans.getItemPO().elementAt(i).toString()+"\n");
					taSupplierReport.append(POTrans.getItemPO().elementAt(i).toString()+"\n");
					taSummaryReport.append(POTrans.getItemPO().elementAt(i).toString()+"\n");
				
				}//end for
				
				taPOSummary.append("\nPO Total:  "+twoDec.format(totalPO(items))+"n");
				taSupplierReport.append("\nPO Total:  "+twoDec.format(totalPO(items))+"n");
				taSummaryReport.append("\nPO Total:  "+twoDec.format(totalPO(items))+"\n");
					
				savePO();
				resetFields(1);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "INVALID INPUT!\nPlease enter appropriate"+ 
													" input in the fields!","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/*-------------------------- ITEM PO ------------------------------------*/
	private class AddPOItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			DecimalFormat twoDec = new DecimalFormat("#.00");
			
			try{
				
				String itemName = tfItem.getText().trim();					//Adds an item to a PO
				String unit = tfUnit.getText().trim();
				int qty = Integer.parseInt(tfQty.getText().trim());
				double unitP = Double.parseDouble(tfUnitP.getText().trim());
			
				ItemPO product = new ItemPO(itemName,unit,qty,unitP);
				itemPOList.addElement(product);
				//ADD TO TABLE
				tbItemPOModel.addRow(new String[]{""+qty,itemName,unit,""+twoDec.format(unitP),""+
								twoDec.format(product.getItemCost())});
				resetFields(6);
			}catch(Exception err){
					JOptionPane.showMessageDialog(null, "INVALID INPUT!\nPlease enter appropriate"+ 
														" input in the fields!","Error", JOptionPane.ERROR_MESSAGE);
			}				
		}
	}
	
	private class RemovePOItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {			//removes an item for a PO --- page 1
			try{
				int index = tbItemPO.getSelectedRow();
				tbItemPOModel.removeRow(index);
				itemPOList.removeElementAt(index);
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Item to be removed\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}	
		}
	}
	
	private class ClearPOItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {			//simply clears the list.... --- page 1
			itemPOList.clear();	
		}
	}
	
	private class ReplacePOItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			DecimalFormat twoDec = new DecimalFormat("#.00");
			try{
				String itemName = tfItem.getText().trim();				
				String unit = tfUnit.getText().trim();					//replaces an item in the table -- page 1
				int qty = Integer.parseInt(tfQty.getText().trim());
				double unitP = Double.parseDouble(tfUnitP.getText().trim());
				int index = tbItemPO.getSelectedRow();
				tbItemPOModel.removeRow(index);
				ItemPO product = new ItemPO(itemName,unit,qty,unitP);
				itemPOList.addElement(product);
				//ADD TO TABLE
				tbItemPOModel.addRow(new String[]{""+qty,itemName,unit,""+twoDec.format(unitP),""+
								twoDec.format(product.getItemCost())});
								
				resetFields(6);
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Item to be edited\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}//*/
	
	/*-------------------------- SUPPLIER ------------------------------------*/
	
	private class AddSupplierListener implements ActionListener{ 
		public void actionPerformed(ActionEvent event){
		
		try{
			
			String supCode = tfCode2.getText().trim();
			String compName = tfDescription.getText().trim();
			String tokens[] = tfAddress2.getText().split(",");		//output format error for Address element
																
			String streetNum = tokens[0].trim();	
			String street = tokens[1].trim();
			String location = tokens[2].trim();
			String city = tokens[3].trim();
			String country = tokens[4].trim();
			
			Supplier entry = new Supplier(supCode,compName,streetNum,street,location,city,country);
			vendor.addElement(entry);
			//add to table
			tbSupplierModel.addRow(new String[]{supCode,compName,""+entry.getAddress()});
			saveSupplier();
			resetFields(2);
			
		}catch(Exception err){
					JOptionPane.showMessageDialog(null, "INVALID INPUT!\nPlease enter appropriate"+ 
														" input in the fields!","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class RemoveSupplierListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {				//removes a supplier ---page 2
			try{
				int index = tbSupplier.getSelectedRow();
				tbSupplierModel.removeRow(index);
				vendor.removeElementAt(index);
				saveSupplier();
				
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Supplier to be removed\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
					
		}
	}
	
	private class ReplaceSupplierListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {			
			try{
				String supCode = tfCode2.getText().trim();
				String compName = tfDescription.getText().trim();
				String tokens[] = tfAddress2.getText().split(",");
																
				String streetNum = tokens[0].trim();	
				String street = tokens[1].trim();							//Edit supplier Table --- page 2
				String location = tokens[2].trim();							
				String city = tokens[3].trim();
				String country = tokens[4].trim();
			
				int index = tbSupplier.getSelectedRow();
				tbSupplierModel.removeRow(index);
				vendor.removeElementAt(index);
				Supplier entry = new Supplier(supCode,compName,streetNum,street,location,city,country);
				vendor.addElement(entry);
				//ADD TO TABLE
				tbItemPOModel.insertRow(index,new String[]{supCode,compName,""+entry.getAddress()});
				saveSupplier();
				
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Item to be edited\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}
	
	/*-------------------------- DEPARTMENT ------------------------------------*/
	
	private class AddDeptListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
		
		try{
			String deptCode = tfCode6.getText().trim();					//add a department --- page 4
			String deptName = tfDescription5.getText().trim(); 	
			ReceivingDepartment entry = new ReceivingDepartment(deptCode,deptName);	
			department.addElement(entry);
			//add to table
			tbDeptModel.addRow(new String[]{deptCode,deptName});
			saveDepartment();
			resetFields(4);
			
		}catch(Exception err){
					JOptionPane.showMessageDialog(null, "INVALID INPUT!\nPlease enter appropriate"+ 
													" input in the fields!","Error", JOptionPane.ERROR_MESSAGE);	
			
			}
		}
	}
	
	private class RemoveDeptListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {				//removes a department ---page 4
			try{
				int index = tbDept.getSelectedRow();
				tbDeptModel.removeRow(index);
				department.removeElementAt(index);
				saveDepartment();
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Department to be removed\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
					
		}
	}
	
	private class ReplaceDeptListener implements ActionListener {//temporary edit....will be using dialog box
		public void actionPerformed(ActionEvent event) {
			try{
				String deptCode = tfCode6.getText().trim();
				String deptName = tfDescription5.getText().trim(); 	
				int index = tbDept.getSelectedRow();
				tbDeptModel.removeRow(index);
				department.removeElementAt(index);
				ReceivingDepartment entry = new ReceivingDepartment(deptCode,deptName);
				department.addElement(entry);
				//ADD TO TABLE
				tbDeptModel.insertRow(index,new String[]{deptCode,deptName});
				saveDepartment();
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Department to be edited\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}//*/
	
	/*-------------------------- ITEM ------------------------------------*/
	
	private class AddItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			
			DecimalFormat twoDec = new DecimalFormat("#.00");
			try{
				String iCode = tfCode4.getText().trim();
				String iName = tfDescription3.getText().trim();					//adds ang item --- page 3
				String unit = tfUnit3.getText().trim();
				double unitP = Double.parseDouble(tfUnitP3.getText().trim());
				Item entry = new Item(iName,iCode,unit,unitP);
				item.addElement(entry);
				//add to table
				tbItemModel.addRow(new String[]{iCode,iName,unit,""+twoDec.format(unitP)});
				
				saveItem();
				resetFields(3);
				
			}catch(NumberFormatException err){
				JOptionPane.showMessageDialog(null, "INVALID INPUT!\nPlease enter appropriate"+ 
													" input in the fields!","Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
	
	private class RemoveItemListener implements ActionListener {	//removing an item in page 3
		public void actionPerformed(ActionEvent event) {
			try{
				int index = tbItem.getSelectedRow();
				tbItemModel.removeRow(index);
				item.removeElementAt(index);
				saveItem();	
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null, "Item to be removed\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
				
		}
	}
	
	private class ReplaceItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			DecimalFormat twoDec = new DecimalFormat("#.00");
			try{
				String iCode = tfCode4.getText().trim();
				String iName = tfDescription3.getText().trim();			//Replaces an Item in the Company Item List
				String unit = tfUnit3.getText().trim();
				double unitP = Double.parseDouble(tfUnitP3.getText().trim());
				int index = tbItem.getSelectedRow();
				tbItemModel.removeRow(index);
				item.removeElementAt(index);
				Item product = new Item(iName,iCode,unit,unitP);
				item.addElement(product);
				//ADD TO TABLE
				tbItemModel.insertRow(index,new String[]{iCode,iName,unit,""+twoDec.format(unitP)});
				saveItem();
				
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Item to be edited\nNOT SELECTED!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}
	
	private class LoginListener implements ActionListener {	
		public void actionPerformed(ActionEvent event) {
			String password = pfPassword.getText().trim();		//checks the password
			
			if(password.equals("a")){
				passwordPanel.setVisible(false);
				resetFields(5);
				btLogOut1.setVisible(true);
				btLogOut2.setVisible(true);
				btLogOut3.setVisible(true);
				innerTabbedPane.setSelectedIndex(0);
			}
				
			else
				JOptionPane.showMessageDialog(null,"ACCESS DENIED!","Invalid Password",JOptionPane.ERROR_MESSAGE);
				
		}
	}
	
	private class LogOutListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();
			
			if(source == btLogOut1 || source == btLogOut2 || source == btLogOut3){
				passwordPanel.setVisible(true);
				btLogOut1.setVisible(false);
				btLogOut2.setVisible(false);
				btLogOut3.setVisible(false);
			}
				
		}
	}
	
	/*-------------------------- REPORTS ------------------------------------*/
	private class SortReportsListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();
			
			if(source == btSortPO){
				POList.sortPurchaseOrder();
				taPOSummary.setText("");
				setPOPane();
			}
				
			else if(source == btSortSupplier){
				vendor.sortSupplier();
				taSupplierReport.setText("");
				setSupplierPane();
			}
				
			else if(source == btSortDepartment){
				department.sortDepartment();
				taSummaryReport.setText("");
				setDepartmentPane();
			}
		}	
	}
	
	private class DateRangeListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			try{
				DecimalFormat twoDec = new DecimalFormat("#.00");
				POList.sortMonth();
				String firstDate = "",secondDate = "";
			
				firstDate = JOptionPane.showInputDialog("Enter First Date: (Month,Day,Year)");
				secondDate = JOptionPane.showInputDialog("Enter Second Date: (Month,Day,Year)");
			
				String tokens[] = firstDate.trim().split(",");
				int month = Integer.parseInt(tokens[0].trim());
				int day = Integer.parseInt(tokens[1].trim());
				int year = Integer.parseInt(tokens[2].trim());
			
				int first = POList.searchFirstDate(month,day,year);
			
				String tokens1[] = secondDate.trim().split(",");
				int month1 = Integer.parseInt(tokens1[0].trim());
				int day1 = Integer.parseInt(tokens1[1].trim());
				int year1 = Integer.parseInt(tokens1[2].trim());
			
				int second = POList.searchSecondDate(month1,day1,year1);
			
				taPOSummary.setText("");
				setSortedPOPane(first,second);
				taPOSummary.append("\nTotal Expenditures:  "+twoDec.format(totalExpenses(first,second))+"\n");
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null,"INVALID RANGE!","Invalid Range",JOptionPane.ERROR_MESSAGE);
			} 
			
		}
	}
	
	private class IsolateSupplierListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			
			DecimalFormat twoDec = new DecimalFormat("#.00");
			int first = 0,second = 0;		
			try{
				POList.sortPurchaseOrderSupplier();
					
				String searchName = JOptionPane.showInputDialog("Enter Supplier Name:");
				String name = searchName.trim();
			
				first = POList.searchPurchaseOrderSupplier(name);
			
				for(int i=0; i<POList.size();i++)
				{
					purchase = (PurchaseOrder)POList.elementAt(i);
					if(name.toUpperCase().equals(purchase.getSupplier().getCompanyName().toUpperCase().toString()))
						second = i;
				}
			
				taSupplierReport.setText("");
				if(second == 0){
					purchase = (PurchaseOrder)POList.elementAt(first);
					taSupplierReport.append(purchase.toString());
					for(int i=0; i<(purchase.getItemPO()).size(); i++)
						taSupplierReport.append(purchase.getItemPO().elementAt(i).toString()+"\n");
						
					taSupplierReport.append("\nPO Total:  "+twoDec.format(totalPO(purchase.getItemPO()))+"\n");
					taSupplierReport.append("\nTotal Expenditures:  "+twoDec.format(totalPO(purchase.getItemPO()))+"\n");
				}
				else {
					setIsolatedSupplierPane(first,second);
					taSupplierReport.append("\nTotal Expenditures:  "+twoDec.format(totalExpenses(first,second))+"\n");
				}	
					
					
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null,"Supplier NOT in the list!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class IsolateDepartmentListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			DecimalFormat twoDec = new DecimalFormat("#.00");
			int first =0, second = 0;
			try{
				POList.sortPurchaseOrderDepartment();
					
				String searchName = JOptionPane.showInputDialog("Enter Department Name:");
				String name = searchName.trim();
			
				first = POList.searchPurchaseOrderDept(name);
			
				for(int i=0; i<POList.size();i++)
				{
					purchase = (PurchaseOrder)POList.elementAt(i);
					if(name.toUpperCase().equals(purchase.getDepartment().getDepartmentName().toUpperCase().toString()))
						second = i;
				}
			
				taSummaryReport.setText("");
				if(second == 0){
					purchase = (PurchaseOrder)POList.elementAt(first);
					taSummaryReport.append(purchase.toString());
					for(int i=0; i<(purchase.getItemPO()).size(); i++)
						taSummaryReport.append(purchase.getItemPO().elementAt(i).toString()+"\n");
						
					taSummaryReport.append("\nPO Total:  "+twoDec.format(totalPO(purchase.getItemPO()))+"\n");
					taSummaryReport.append("\nTotal Expenditures:  "+twoDec.format(totalPO(purchase.getItemPO()))+"\n");
				}
				else {
					setIsolatedDepartmentPane(first,second);
					taSummaryReport.append("\nTotal Expenditures:  "+twoDec.format(totalExpenses(first,second))+"\n");
				}
			}catch(ArrayIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null,"Department does not EXIST!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	/*-------------------------- DIALOG BOXES ------------------------------------*/
	
	private class ShowDeptListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			
			try{
				DeptListDialog dlg = new DeptListDialog(new JFrame(),department);
				tfDept.setText(dlg.getDept());
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "NO Department Chosen!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}
	
	private class ShowItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			DecimalFormat twoDec = new DecimalFormat ("#.00");
			try{
				ItemListDialog dlg = new ItemListDialog(new JFrame(),item);
				tfItem.setText(dlg.getItem());
				tfUnit.setText(dlg.getUnit());
				tfUnitP.setText(twoDec.format(Double.parseDouble(dlg.getUnitPrice())));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "NO Item Chosen!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
 			
			
		}
	}
	
	private class ShowSupplierListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			try{
				SupplierListDialog dlg = new SupplierListDialog(new JFrame(),vendor);
				tfName.setText(dlg.getVendor());
				tfAddress.setText(dlg.getAddress());
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "NO Supplier Chosen!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
 			
			
		}
	}
	
	/*-------------------------- WINDOW ADAPTER ------------------------------------*/

	private class MyWindowAdapter extends WindowAdapter 
	{
		public void windowClosing(WindowEvent we) {
			System.out.println("Ending Purchase Order System...");
			System.exit(0);
		}
	}
	
	/*-----------------------------OTHER METHODS------------------------------*/
	
	public void setPOPane()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		POList.sortPurchaseOrder();
		for(int i=0; i<POList.size(); i++)
		{
			PurchaseOrder PO = (PurchaseOrder)POList.elementAt(i);
			taPOSummary.append(PO.toString());	
			
			for(int j=0; j<(PO.getItemPO()).size(); j++)
				taPOSummary.append(PO.getItemPO().elementAt(j).toString()+"\n");
				
			taPOSummary.append("\nPO Total:  "+twoDec.format(totalPO(PO.getItemPO()))+"\n");
					
		}//end for
	}//end setPOPane
	
	public void setSortedPOPane(int first, int second)
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		for(int i=first; i<=second; i++)
		{
			PurchaseOrder PO = (PurchaseOrder)POList.elementAt(i);
			taPOSummary.append(PO.toString());
			
			for(int j=0; j<(PO.getItemPO()).size(); j++)
				taPOSummary.append(PO.getItemPO().elementAt(j).toString()+"\n");
				
			taPOSummary.append("\nPO Total:  "+twoDec.format(totalPO(PO.getItemPO()))+"\n");
					
		}//end for
	}//end setSortendPOPane
	
	public void setDepartmentPane()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		POList.sortPurchaseOrderDepartment();
		for(int i=0;i<POList.size();i++)
		{
			PurchaseOrder PO = (PurchaseOrder)POList.elementAt(i);
			taSummaryReport.append(PO.toString());
			
			for(int j=0; j<((PO.getItemPO()).size()); j++)
				taSummaryReport.append(PO.getItemPO().elementAt(j).toString()+"\n");
				
			taSummaryReport.append("\nPO Total:  "+twoDec.format(totalPO(PO.getItemPO()))+"\n");
			
		}//end for
	}//end setDepartmentPane
	
	public void setIsolatedDepartmentPane(int first, int second)
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		for(int i=first;i<=second;i++)
		{
			PurchaseOrder PO = (PurchaseOrder)POList.elementAt(i);
			taSummaryReport.append(PO.toString());
			
			for(int j=0; j<((PO.getItemPO()).size()); j++)
				taSummaryReport.append(PO.getItemPO().elementAt(j).toString()+"\n");
				
			taSummaryReport.append("\nPO Total:  "+twoDec.format(totalPO(PO.getItemPO()))+"\n");
			
		}//end for
	}//end setIsolatedDepartmentPane
	
	public void setSupplierPane()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		POList.sortPurchaseOrderSupplier();
		for(int i=0;i<POList.size();i++)
		{
			PurchaseOrder PO = (PurchaseOrder)POList.elementAt(i);
			taSupplierReport.append(PO.toString());
			
			for(int j=0; j<(PO.getItemPO()).size(); j++)
				taSupplierReport.append(PO.getItemPO().elementAt(j).toString()+"\n");
				
			taSupplierReport.append("\nPO Total:  "+twoDec.format(totalPO(PO.getItemPO()))+"\n");
			
		}//end for
	}//setSupplierPane
	
	public void setIsolatedSupplierPane(int first, int second)
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		for(int i=first;i<=second;i++)
		{
			PurchaseOrder PO = (PurchaseOrder)POList.elementAt(i);
			taSupplierReport.append(PO.toString());
			
			for(int j=0; j<(PO.getItemPO()).size(); j++)
				taSupplierReport.append(PO.getItemPO().elementAt(j).toString()+"\n");
			
			taSupplierReport.append("\nPO Total:  "+twoDec.format(totalPO(PO.getItemPO()))+"\n");
			
		}//end for
	}//setIsolatedSupplierPane
	
	public void setItemTable()
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		
		for(int i=0; i<item.size();i++)
		{
			Item prod = (Item)item.elementAt(i);
			tbItemModel.addRow(new String []{prod.getItemCode(),prod.getItemName(),prod.getUnit(),
								""+twoDec.format(prod.getUnitPrice())});		
		}
	}//end setItemTable
	
	public void setDeptTable()
	{
		for(int i=0; i<department.size();i++)
		{
			ReceivingDepartment dept = (ReceivingDepartment)department.elementAt(i);
			tbDeptModel.addRow(new String []{dept.getDepartmentCode(),dept.getDepartmentName()});		
		}
	}//end setDeptTable
	
	public void setSupplierTable()
	{
		for(int i=0; i<vendor.size();i++)
		{
			Supplier company = (Supplier)vendor.elementAt(i);
			tbSupplierModel.addRow(new String []{company.getSupplierCode(),company.getCompanyName(),
												""+company.getAddress()});		
		}
	}//end setSupplierTable
	
	public double totalPO(Vector iPO)
	{
		double totalPO = 0.0;
		for(int i=0; i<iPO.size(); i++)
		{
			Vector item = (Vector)iPO.elementAt(i);
			totalPO += Double.parseDouble(""+item.get(4));
		}//end for
		
		return totalPO;	 
	}//end setTotalPO
	
	public double totalExpenses(int first, int second)
	{
		double totalExpense = 0.0;
		for(int i=first; i<=second; i++)
		{
			purchase = (PurchaseOrder)POList.elementAt(i);
			totalExpense += Double.parseDouble(""+totalPO(purchase.getItemPO())); 
		}
		return totalExpense;
	}
	
	static void loadFiles(){
		try{
			
			FileInputStream supplierInStream = new FileInputStream("supplier.bin");
			ObjectInputStream supplierIoStream = new ObjectInputStream(supplierInStream);
      
			vendor = (SupplierList) supplierIoStream.readObject();
      
			supplierInStream.close();
			
			FileInputStream itemInStream = new FileInputStream("item.bin");
			ObjectInputStream itemIoStream = new ObjectInputStream(itemInStream);
      
			item = (ItemList)itemIoStream.readObject();
      
			itemInStream.close();
			
			FileInputStream deptInStream = new FileInputStream("department.bin");
			ObjectInputStream deptIoStream = new ObjectInputStream(deptInStream);
      
			department = (DepartmentList) deptIoStream.readObject();
      
			deptInStream.close();
			
			FileInputStream purchaseOrderInStream = new FileInputStream("purchaseOrder.bin");
			ObjectInputStream purchaseOrderIoStream = new ObjectInputStream(purchaseOrderInStream);
      
			POList = (PurchaseOrderList) purchaseOrderIoStream.readObject();
		
			purchaseOrderInStream.close();
			
    }
    catch (FileNotFoundException exc){
			System.err.println("Some file/s have not yet been loaded...");
    }catch (IOException exc){
    	System.err.println("error");
    	}catch(ClassNotFoundException exc){
			System.err.println("FILES DO NOT EXSIST!");
    	}

	}//end loadFiles
	
	public void savePO()
	{
		try{
			FileOutputStream purchaseOut = new FileOutputStream("purchaseOrder.bin");	
			ObjectOutputStream ooStream = new ObjectOutputStream(purchaseOut);
		
			ooStream.writeObject(POList);
			ooStream.flush();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Problem Encountered!\nFile NOT SAVED!","Error", 
										JOptionPane.ERROR_MESSAGE);
		}
	}//end 
	
	public void saveItem()  
	{
		try{
			FileOutputStream itemOut = new FileOutputStream("item.bin");	
			ObjectOutputStream ooStream = new ObjectOutputStream(itemOut);
		
			ooStream.writeObject(item);
			ooStream.flush();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Problem Encountered!\nFile NOT SAVED!","Error", 
										JOptionPane.ERROR_MESSAGE);
		}
						
	}//end saveItem
	
	public void saveDepartment()  
	{
		try{
			FileOutputStream deptOut = new FileOutputStream("department.bin");	
			ObjectOutputStream ooStream = new ObjectOutputStream(deptOut);
		
			ooStream.writeObject(department);
			ooStream.flush();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Problem Encountered!\nFile NOT SAVED!","Error", 
										JOptionPane.ERROR_MESSAGE);
		}
						
	}//end saveDepartment
	
	public void saveSupplier()  
	{
		try{
			FileOutputStream supplierOut = new FileOutputStream("supplier.bin");	
			ObjectOutputStream ooStream = new ObjectOutputStream(supplierOut);
		
			ooStream.writeObject(vendor);
			ooStream.flush();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Problem Encountered!\nFile NOT SAVED!","Error", 
										JOptionPane.ERROR_MESSAGE);
		}
						
	}//end saveSupplier
    
    public void resetFields(int x)
    {
    	switch(x)
    	{
    		case 1:
    			tfPONum.setText("");		month.setSelectedIndex(0); day.setSelectedIndex(0);
    			tfName.setText("");		 	year.setSelectedIndex(0);		
    			tfAddress.setText("");		tfRefNum.setText("");
    			tfDept.setText("");			tfTerm.setText("");
    			
    			while(tbItemPOModel.getRowCount() > 0)
	  				tbItemPOModel.removeRow(0);
	  				
	  			tfQty.setText("");		tfUnit.setText("");
				tfItem.setText("");		tfUnitP.setText("");
    			break;	
    		case 2:
    			tfCode2.setText("");
    			tfDescription.setText("");
    			tfAddress2.setText("");
    			break;
    		case 3:
    			tfCode4.setText("");
    			tfDescription3.setText("");
    			tfUnit3.setText("");
    			tfUnitP3.setText("");
    			break;
    		case 4:
    			tfCode6.setText("");
    			tfDescription5.setText("");
    			break;
    		case 5:
    			pfPassword.setText("");
    			break;
    		case 6:
    			tfQty.setText("");		tfUnit.setText("");
				tfItem.setText("");		tfUnitP.setText("");
				break;
    	}//end switch
    }//end resetFields
        
}//end class
