package borromeo.pos.view;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

import borromeo.pos.model.Supplier;
import borromeo.pos.model.SupplierList;

import java.awt.event.*;
import java.text.*;

public class SupplierListDialog extends JDialog
{
	
	//////////////////////MODEL////////////////////
	
	/////////////////////VIEW//////////////////////
	JFrame parent;
	
	DefaultTableModel tbSupplierModel = new DefaultTableModel();
	JTable tbSupplier = new JTable(tbSupplierModel);
	JScrollPane spSupplier = new JScrollPane(tbSupplier);
	JButton btSelected = new JButton("Choose this Supplier");
	
	public SupplierListDialog(JFrame parent,SupplierList supplier)
	{
		super(parent,true);
		this.parent = parent;
		btSelected.addActionListener(new ChooseSupplierListener());
		this.setResizable(false);
		this.setTitle("Company Supplier List");
		this.setBounds(250,250,450,330);
		setLayout();
		setTable(supplier);
		show();
		this.pack();
		
		btSelected.addActionListener(new ChooseSupplierListener());
	}
	
	public void setLayout()
	{
		int col = tbSupplierModel.getColumnCount();
		this.getContentPane().setLayout(null);
		Container panel = this.getContentPane();
		spSupplier.setBounds(20,10,400,250);
		btSelected.setBounds(150,265,150,25);
		
		if(col < 2){
			tbSupplierModel.addColumn("CODE");
			tbSupplierModel.addColumn("DESCRIPTION");
			tbSupplierModel.addColumn("ADDRESS");
		}
		
		panel.add(spSupplier);
		panel.add(btSelected);
	}
	
	public void setTable(SupplierList supplier)
	{
		while(tbSupplierModel.getRowCount() > 0)
	  		tbSupplierModel.removeRow(0);
		
		for(int i=0; i<supplier.size();i++){
  			Supplier vendor = (Supplier)supplier.elementAt(i);
  			tbSupplierModel.addRow(new String []{vendor.getSupplierCode(),vendor.getCompanyName(),""+vendor.getAddress()});
  		}//end for*/
		
	}
	
	////////////////////////CONTROL/////////////////////////
	private class ChooseSupplierListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			try{
				parent.dispose();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "NO Supplier Chosen!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
 			
			
		}
	}
	//////////////////////////////Methods////////////////////////////////////

	public String getVendor()
	{
		int sel = tbSupplier.getSelectedRow();
		String vendor = tbSupplier.getModel().getValueAt(sel,1).toString();
		
		return vendor;
		
	}
	
	public String getAddress()
	{
		int sel = tbSupplier.getSelectedRow();
		String address = tbSupplier.getModel().getValueAt(sel,2).toString();
		
		return address;
		
	}
	
  }