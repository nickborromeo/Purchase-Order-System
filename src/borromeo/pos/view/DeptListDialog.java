package borromeo.pos.view;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

import borromeo.pos.model.DepartmentList;
import borromeo.pos.model.ReceivingDepartment;

import java.awt.event.*;

public class DeptListDialog extends JDialog
{
	
	//////////////////////MODEL////////////////////
	
	/////////////////////VIEW//////////////////////
	JFrame parent;
	
	DefaultTableModel tbDeptModel = new DefaultTableModel();
	JTable tbDept = new JTable(tbDeptModel);
	JScrollPane spDepartment = new JScrollPane(tbDept);
	JButton btSelected = new JButton("Choose this Department");
	
	public DeptListDialog(JFrame parent,DepartmentList department)
	{
		super(parent,true);
		this.parent = parent;
		btSelected.addActionListener(new ChooseDeptListener());
		this.setResizable(false);
		this.setTitle("Company Department List");
		this.setBounds(250,250,300,330);
		setLayout();
		setTable(department);
		show();
		this.pack();
	}
	
	public void setLayout()
	{
		int col = tbDeptModel.getColumnCount();
		this.getContentPane().setLayout(null);
		Container panel = this.getContentPane();
		spDepartment.setBounds(20,10,250,250);
		btSelected.setBounds(70,265,150,25);
		
		if(col < 2){
			tbDeptModel.addColumn("CODE");
			tbDeptModel.addColumn("DESCRIPTION");
		}
		
		panel.add(spDepartment);
		panel.add(btSelected);
	}
	
	public void setTable(DepartmentList department)
	{
		while(tbDeptModel.getRowCount() > 0)
	  		tbDeptModel.removeRow(0);
		
		for(int i=0; i<department.size();i++){
  			ReceivingDepartment dept = (ReceivingDepartment)department.elementAt(i);
  			tbDeptModel.addRow(new String []{dept.getDepartmentCode(),dept.getDepartmentName()});
  		}//end for*/
		
	}
	
	////////////////////////CONTROL/////////////////////////
	private class ChooseDeptListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
				dispose();		
		}
	}
	//////////////////////////////Methods////////////////////////////////////

	public String getDept()
	{
		int sel = tbDept.getSelectedRow();
		String dept = tbDept.getModel().getValueAt(sel,1).toString();
		
		return dept;
		
	}
	
  }