package borromeo.pos.view;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

import borromeo.pos.model.Item;
import borromeo.pos.model.ItemList;

import java.awt.event.*;
import java.text.*;

public class ItemListDialog extends JDialog
{
	
	//////////////////////MODEL////////////////////
	
	/////////////////////VIEW//////////////////////
	JFrame parent;
	public static DefaultTableModel tbItemModel = new DefaultTableModel();
	JTable tbItem = new JTable(tbItemModel);
	JScrollPane spItem = new JScrollPane(tbItem);
	JButton btClose = new JButton("Choose this Item");
	
	public ItemListDialog(JFrame parent,ItemList item)
	{
		super(parent,true);
		this.parent = parent;
		btClose.addActionListener(new ChooseItemListener());
		this.setResizable(false);
		this.setTitle("Company Item List");
		this.setBounds(250,250,400,400);
		setLayout();
		setTable(item);
		show();
		this.pack();
	}
	
	private void setLayout()
	{
		int col = tbItemModel.getColumnCount();
		this.getContentPane().setLayout(null);
		Container panel = this.getContentPane();
		spItem.setBounds(20,10,350,300);
		btClose.setBounds(120,330,150,25);
		if(col < 4){
			tbItemModel.addColumn("CODE");
			tbItemModel.addColumn("DESCRIPTION");
			tbItemModel.addColumn("UNIT");
			tbItemModel.addColumn("UNIT PRICE");
		}
		
		panel.add(spItem);
		panel.add(btClose);
	}
	
	public void setTable(ItemList item)
	{
		DecimalFormat twoDec = new DecimalFormat("#.00");
		
		while(tbItemModel.getRowCount() > 0)
	  		tbItemModel.removeRow(0);
			
		for(int i=0; i<item.size();i++){
  			Item prod = (Item)item.elementAt(i);
  			tbItemModel.addRow(new String []{prod.getItemCode(),prod.getItemName(),prod.getUnit(),
  												""+twoDec.format(prod.getUnitPrice())});
		}//end for
		
	}
	
	//////////////////////////////////CONTROL///////////////////////////////////
	private class ChooseItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			try{
				dispose();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "NO Item Chosen!",
												"Error", JOptionPane.WARNING_MESSAGE);
			}
 			
			
		}
	}
	
	///////////////////Methods////////////////////
	public String getItem()
	{
		int sel = tbItem.getSelectedRow();
		String item = tbItem.getModel().getValueAt(sel,1).toString();
		
		return item;
		
	}
	
	public String getUnit()
	{
		int sel = tbItem.getSelectedRow();
		String unit = tbItem.getModel().getValueAt(sel,2).toString();
		
		return unit;
		
	}
	
	public String getUnitPrice()
	{
		int sel = tbItem.getSelectedRow();
		String unitPrice = tbItem.getModel().getValueAt(sel,3).toString();
		
		return unitPrice;
		
	}
}