package borromeo.pos.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.util.*;

public class ViewMode extends JFrame
{

	///////////////////////////////////////THE VIEW////////////////////////////////////////
	JTabbedPane tabbedPane;
	JPanel topPanel;
	JPanel	panel1;
	JPanel	panel2;
	JPanel	panel3;
	JPanel	panel4;
	JPanel	panel5;
	
	//---------- PAGE 1 ----------// 
	
	JLabel lbPO = new JLabel( "PO #:" );
	JLabel lbName = new JLabel( "Vendor's Name:" );
	JLabel lbAddress = new JLabel("Address:");
	JLabel lbDept = new JLabel( "Receiving Department:" );
	JLabel lbDate = new JLabel("PO Date");
	JLabel lbMonth = new JLabel( "Month:" );
	JLabel lbDay = new JLabel( "Day:" );
	JLabel lbYear = new JLabel( "Year:" );
	JLabel lbRef = new JLabel( "Reference #:" );
	JLabel lbTerm = new JLabel( "Terms:" );		
	JLabel lbQuantity = new JLabel("Quantity");
	JLabel lbParticulars = new JLabel("Particulars");
	JLabel lbUnit = new JLabel("Unit");
	JLabel lbUnitP = new JLabel("Unit Price");
	JLabel lbAmount = new JLabel("Amount");
	JLabel lbItem = new JLabel("Item");
	JLabel lbQuantity1 = new JLabel("Quantity:");
	JLabel lbParticulars1 = new JLabel("Particulars:");
	JLabel lbUnit1 = new JLabel("Unit:");
	JLabel lbUnitP1 = new JLabel("Unit Price:");
	JTextField tfPONum = new JTextField("");
	JTextField tfName = new JTextField("");
	JTextField tfAddress = new JTextField("");
	JTextField tfDept = new JTextField("");
	JTextField tfRefNum = new JTextField("");
	JTextField tfTerm = new JTextField("");
	JTextField tfQty = new JTextField("");
	JTextField tfItem = new JTextField("");
	JTextField tfUnit = new JTextField("");
	JTextField tfUnitP = new JTextField("");
	final String[] years = {"2000","2001","2002","2003","2004","2005","2006","2007"};	
	JComboBox year = new JComboBox(years);
	final String[] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
					"20","21","22","23","24","25","26","27","28","29","30","31"};
	JComboBox day = new JComboBox(days);
	final String[] months = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	JComboBox month = new JComboBox(months);
	JButton btAddItem = new JButton ("Add Item");
	JButton btRemoveItem = new JButton ("Remove Item");
	JButton btEditItem = new JButton ("Edit Item");
	JButton btClearList = new JButton ("Clear List");
	JButton btAddPO = new JButton ("Add Purchase Order");
	JButton btShowDept =  new JButton("Show Departments");
	JButton btShowItem = new JButton("Show Items");
	JButton btShowSupplier = new JButton("Show Suppliers");
	public static DefaultTableModel tbItemPOModel = new DefaultTableModel()
	{
		public boolean isCellEditable(int row, int col) 
	    {
	      return false;
	    }
	};
	JTable tbItemPO = new JTable(tbItemPOModel);
	JScrollPane spItems = new JScrollPane(tbItemPO);
	
	
	
	//---------- PAGE 2 ----------// 
	
 	JLabel lbTitle = new JLabel("COMPANY SUPPLIER LIST");
 	JLabel lbBorder = new JLabel("========================================================================"+
								"==========================");
 	JLabel lbCode = new JLabel("CODE");
	JLabel lbDescription = new JLabel("DESCRIPTION");
	JLabel lbAddress1 = new JLabel( "ADDRESS" );
	JLabel lbSupplierDetails = new JLabel("Supplier Details");
	JLabel lbLine = new JLabel("_____________________________________");
	JLabel lbCode2 = new JLabel("Code:");
	JLabel lbDescription1 = new JLabel("Description:");
	JLabel lbAddress2 = new JLabel( "Address:" );
 	JTextField tfCode2 = new JTextField("");
	JTextField tfAddress2 = new JTextField("");
	JTextField tfDescription = new JTextField("");
 	JButton btEditSupplier = new JButton("Edit Supplier");
	JButton btRemoveSupplier = new JButton("Remove Supplier");
	JButton btAddSupplier = new JButton("Add Supplier");
 	public static DefaultTableModel tbSupplierModel = new DefaultTableModel()
 	{
 		public boolean isCellEditable(int row, int col) 
	    {
	      return false;
	    }
 	};
	JTable tbSupplier = new JTable(tbSupplierModel);
	JScrollPane spSupplier = new JScrollPane(tbSupplier);
 	
 	
 	
 	//---------- PAGE 3 ----------// 
 	
 	JLabel lbTitle1 = new JLabel("COMPANY ITEM LIST");
 	JLabel lbBorder1 = new JLabel("======================================================================="+
								"===========================");
	JLabel lbCode3 = new JLabel("CODE");
	JLabel lbDescription2 = new JLabel("DESCRIPTION");
	JLabel lbUnit2 = new JLabel("UNIT");
	JLabel lbUnitP2 = new JLabel("UNIT PRICE");
	JLabel lbItemDetails = new JLabel("Item Details");
	JLabel lbLine1 = new JLabel("_____________________________________");
	JLabel lbCode4 = new JLabel("Code:");
	JLabel lbDescription3 = new JLabel("Description:");
	JLabel lbUnit3 = new JLabel("Unit:");
	JLabel lbUnitP3 = new JLabel("Unit Price:");
 	JTextField tfCode4 = new JTextField("");
	JTextField tfDescription3 = new JTextField("");
	JTextField tfUnit3 = new JTextField("");
	JTextField tfUnitP3 = new JTextField("");
 	JButton btAddItem1 = new JButton ("Add Item");
	JButton btRemoveItem1 = new JButton ("Remove Item");
	JButton btEditItem1 = new JButton ("Edit Item");
	public static DefaultTableModel tbItemModel = new DefaultTableModel()
	{
		public boolean isCellEditable(int row, int col) 
	    {
	      return false;
	    }
	};
	JTable tbItem = new JTable(tbItemModel);
	JScrollPane spItem = new JScrollPane(tbItem);
 	
 	
 	
 	//---------- PAGE 4 ----------//
 	
 	
	JLabel lbTitle2 = new JLabel("COMPANY DEPARTMENT LIST");
	JLabel lbBorder2=new JLabel("=================================================================================");
	JLabel lbDeptDetails = new JLabel("Department Details");
	JLabel lbLine2 = new JLabel("_____________________________________");
	JLabel lbCode6 = new JLabel("Code:");
	JLabel lbDescription5 = new JLabel("Description:");
 	JTextField tfCode6 = new JTextField("");
	JTextField tfDescription5 = new JTextField("");
	JButton btEditDept = new JButton("Edit Department");
	JButton btRemoveDept = new JButton("Remove Department");
	JButton btAddDept = new JButton("Add Department");
	public static DefaultTableModel tbDeptModel = new DefaultTableModel()
	{
		public boolean isCellEditable(int row, int col) 
	    {
	      return false;
	    }
	};
	JTable tbDept = new JTable(tbDeptModel);
	JScrollPane spDepartment = new JScrollPane(tbDept);
	
	
	
	//---------- PAGE 5 ----------//
	JLabel lbTitle3 = new JLabel("PURCHASE ORDER SUMMARY");
	JLabel lbBorder3=new JLabel("================================================================================="
								+"==================");
	JLabel lbTitle4 = new JLabel("DEPARTMENT PURCHASES");
	JLabel lbBorder4=new JLabel("================================================================================="
								+"==================");
	JLabel lbTitle5 = new JLabel("SUPPLIER TRANSACTIONS");
	JLabel lbBorder5=new JLabel("================================================================================="
								+"==================");
	JTabbedPane innerTabbedPane;
	JPanel innerPanel;
	JPanel passwordPanel;
	JPanel innerPanel1;
	JPanel innerPanel2;
	JPanel innerPanel3;
	JLabel lbPassword = new JLabel("Password:");
	JPasswordField pfPassword = new JPasswordField("");
	JButton btPassword = new JButton("Enter");
	JButton btLogOut1 = new JButton("Log Out");
	JButton btLogOut2 = new JButton("Log Out");
	JButton btLogOut3 = new JButton("Log Out");
	JButton btSortPO = new JButton("Sort");
	JButton btSortSupplier = new JButton("Sort");
	JButton btSortDepartment = new JButton("Sort");
	JButton btDateRange = new JButton("Enter Date Range");
	JButton btIsolateSupplier = new JButton("Search for a Supplier");
	JButton btIsolateDepartment = new JButton("Department Search");
	
	JTextArea taPOSummary = new JTextArea();
	JScrollPane spPOSummary = new JScrollPane(taPOSummary);
	JTextArea taSummaryReport = new JTextArea();
	JScrollPane spSummaryReport = new JScrollPane(taSummaryReport);
	JTextArea taSupplierReport = new JTextArea();
	JScrollPane spSupplierReport = new JScrollPane(taSupplierReport);
	

	/////////////////////METHODS//////////////////////
	
	public void createPage1()
	{
		panel1 = new JPanel();
		panel1.setLayout( null );
		
		///////////////////////////////LEFT SIDE//////////////////////////////
		
		
		lbPO.setBounds( 10, 15, 150, 20 );
		tfPONum.setBounds( 50, 15, 150, 20 );
		lbName.setBounds( 10, 40, 150, 20 );
		tfName.setBounds( 120, 40, 180, 20 );
		tfName.setEditable(false);
		lbAddress.setBounds( 10, 70, 150, 20 );
		tfAddress.setBounds(70 , 70, 230, 20 );
		tfAddress.setEditable(false);
		lbDept.setBounds( 10, 100, 150, 20 );
		tfDept.setBounds( 150, 100, 150, 20 );
		tfDept.setEditable(false);
		
		
		panel1.add( lbPO );
		panel1.add( tfPONum );
		panel1.add( lbName );
		panel1.add( tfName );
		panel1.add( lbAddress );
		panel1.add( tfAddress );
		panel1.add( lbDept );
		panel1.add( tfDept );
		
		//////////////////////////////////RIGHT SIDE //////////////////////////
		
		
		lbDate.setBounds(370,15,150,20);
		lbMonth.setBounds( 370, 40, 150, 20 );
		month.setBounds( 420, 40, 45, 20 );
		lbDay.setBounds( 480, 40, 150, 20 );
		day.setBounds( 510, 40,45, 20 );
		lbYear.setBounds( 570, 40, 150, 20 );
		year.setBounds( 610, 40, 60, 20 );
		lbRef.setBounds( 370, 70, 150, 20 );
		tfRefNum.setBounds(450, 70, 70, 20 );
		lbTerm.setBounds( 370, 100, 150, 20 );
		tfTerm.setBounds(420, 100, 70, 20 );
		
		
		panel1.add(lbDate);
		panel1.add( lbMonth );
		panel1.add( month );
		panel1.add( lbDay );
		panel1.add( day );
		panel1.add( lbYear );
		panel1.add( year );
		panel1.add( lbRef );
		panel1.add( tfRefNum );
		panel1.add( lbTerm );
		panel1.add( tfTerm );
		
		////////////////////////// BOTTOM /////////////////////////////////////
		
	
		spItems.setBounds(30,160,625,250);
		tbItemPO.setRowHeight(20);
		lbItem.setBounds(10,420,150,20);
		lbQuantity1.setBounds(10,450,150,20);
		tfQty.setBounds(70,450,30,20);
		lbParticulars1.setBounds(10,480,150,20);
		tfItem.setBounds(90,480,230,20);
		tfItem.setEditable(false);
		lbUnit1.setBounds(110,450,150,20);
		tfUnit.setBounds(140,450,40,20);
		tfUnit.setEditable(false);
		lbUnitP1.setBounds(190,450,150,20);
		tfUnitP.setBounds(260,450,60,20);
		tfUnitP.setEditable(false);
		
		
		panel1.add(spItems);
		panel1.add(lbItem);
		panel1.add(lbQuantity1);	
		panel1.add(tfQty);
		panel1.add(lbParticulars1);
		panel1.add(tfItem);
		panel1.add(lbUnit1);
		panel1.add(tfUnit);
		panel1.add(lbUnitP1);
		panel1.add(tfUnitP);
		
		//////////////////////////////////BUTTONS///////////////////////////////
		
		btAddItem.setBounds(380,420,150,20);
		btRemoveItem.setBounds(380,450,150,20);
		btEditItem.setBounds(380,480,150,20);
		btClearList.setBounds(380,510,150,20);
		btAddPO.setBounds(538,420,150,20);
		btShowSupplier.setBounds(538,450,150,20);
		btShowDept.setBounds(538,510,150,20);
		btShowItem.setBounds(538,480,150,20);
		
		
		
		panel1.add(btAddItem);
		panel1.add(btRemoveItem);
		panel1.add(btEditItem);
		panel1.add(btClearList);
		panel1.add(btAddPO);
		panel1.add(btShowDept);
		panel1.add(btShowItem);
		panel1.add(btShowSupplier);
		
	}//end PAGE 1


	public void createPage2()
	{
		panel2 = new JPanel();
		panel2.setLayout( null );
		
		Font font1 = new Font("SansSerif",Font.PLAIN,18);
		
		
		lbTitle.setBounds(220,10,300,30);
		lbTitle.setFont(font1);
		lbBorder.setBounds(50,22,700,30);
		spSupplier.setBounds(50,68,590,300);
		tbSupplier.setRowHeight(20);
		tbSupplier.setRowMargin(1);
		lbSupplierDetails.setBounds(50,380,150,20);
		lbLine.setBounds(50,382,300,20);
		lbCode2.setBounds(50,410, 150,20);
		tfCode2.setBounds(90,410,80,20);
		lbDescription1.setBounds(50,440,150,20);
		tfDescription.setBounds(125,440,170,20);
		lbAddress2.setBounds(50,470,150,20);
		tfAddress2.setBounds(110,470,200,20);
		btAddSupplier.setBounds(400,410,150,30);
		btRemoveSupplier.setBounds(400,450,150,30);
		btEditSupplier.setBounds(400,490,150,30);
		
		panel2.add(lbTitle);
		panel2.add(lbBorder);
		panel2.add(spSupplier); 
		panel2.add(lbSupplierDetails);
		panel2.add(lbLine);
		panel2.add(lbCode2);
		panel2.add(tfCode2);
		panel2.add(lbDescription1);
		panel2.add(tfDescription);
		panel2.add(lbAddress2);
		panel2.add(tfAddress2);
		panel2.add(btAddSupplier);
		panel2.add(btRemoveSupplier);
		panel2.add(btEditSupplier);
	
	}//end PAGE 2

	public void createPage3()
	{
		panel3 = new JPanel();
		panel3.setLayout( null );
		
		Font font1 = new Font("SansSerif",Font.PLAIN,18);
		
		
		lbTitle1.setBounds(240,10,300,30);
		lbTitle1.setFont(font1);
		lbBorder1.setBounds(50,22,700,30);
		spItem.setBounds(50,68,590,300);
		tbItem.setRowHeight(20);
		lbItemDetails.setBounds(50,380,150,20);
		lbLine1.setBounds(50,382,300,20);
		lbCode4.setBounds(50,410, 150,20);
		tfCode4.setBounds(90,410,80,20);
		lbDescription3.setBounds(50,440,150,20);
		tfDescription3.setBounds(125,440,185,20);
		lbUnit3.setBounds(50,470,150,20);
		tfUnit3.setBounds(85,470,80,20);
		lbUnitP3.setBounds(50,500,150,20);
		tfUnitP3.setBounds(115,500,100,20);
		btAddItem1.setBounds(400,410,150,30);
		btRemoveItem1.setBounds(400,450,150,30);
		btEditItem1.setBounds(400,490,150,30);
		
		
		panel3.add(lbTitle1);
		panel3.add(lbBorder1);
		panel3.add(spItem); 
		panel3.add(lbItemDetails);
		panel3.add(lbLine1);
		panel3.add(lbCode4);
		panel3.add(tfCode4);
		panel3.add(lbDescription3);
		panel3.add(tfDescription3);
		panel3.add(lbUnit3);
		panel3.add(tfUnit3);	
		panel3.add(lbUnitP3);
		panel3.add(tfUnitP3);
		panel3.add(btAddItem1);
		panel3.add(btRemoveItem1);
		panel3.add(btEditItem1);
		
	}//end PAGE 3
	
	
		public void createPage4()
	{
		panel4 = new JPanel();
		panel4.setLayout(null);
		
		
		Font font1 = new Font("SansSerif",Font.PLAIN,18);
		
		
		lbTitle2.setBounds(210,10,300,30);
		lbTitle2.setFont(font1);
		lbBorder2.setBounds(100,22,600,30);
		spDepartment.setBounds(100,68,490,300);
		tbDept.setRowHeight(20);
		lbDeptDetails.setBounds(50,380,150,20);
		lbLine2.setBounds(50,382,300,20);
		lbCode6.setBounds(50,410, 150,20);
		tfCode6.setBounds(90,410,80,20);
		lbDescription5.setBounds(50,440,150,20);
		tfDescription5.setBounds(125,440,185,20);
		btAddDept.setBounds(400,410,150,30);
		btRemoveDept.setBounds(400,450,150,30);
		btEditDept.setBounds(400,490,150,30);
		
		
		panel4.add(lbTitle2);
		panel4.add(lbBorder2);
		panel4.add(spDepartment); 
		panel4.add(lbDeptDetails);
		panel4.add(lbLine2);
		panel4.add(lbCode6);
		panel4.add(tfCode6);
		panel4.add(lbDescription5);
		panel4.add(tfDescription5);
		panel4.add(btAddDept);
		panel4.add(btRemoveDept);
		panel4.add(btEditDept);

	}//end PAGE 4
	
	public void createPage5()
	{
		panel5 = new JPanel();
		panel5.setLayout(null); //page for the PO SUMMARY
		char echo = 164;
		
		passwordPanel = new JPanel();
		passwordPanel.setLayout(null);
		
		innerPanel1 = new JPanel();		innerPanel1.setLayout(null);		innerPanel1.setBounds(0,0,700,600);
		innerPanel2 = new JPanel();		innerPanel2.setLayout(null);		innerPanel2.setBounds(0,0,700,600);
		innerPanel3 = new JPanel();		innerPanel3.setLayout(null);		innerPanel3.setBounds(0,0,700,600);
		
		// Create a tabbed pane
		innerTabbedPane = new JTabbedPane();
		innerTabbedPane.addTab( "PO Summary", new ImageIcon(""),innerPanel1,"Purchase Order Summary");
		innerTabbedPane.addTab( "Summary Report ",new ImageIcon(""), innerPanel2,"Purchases of all Departments" );
		innerTabbedPane.addTab( "Supplier Summary ",new ImageIcon(""), innerPanel3, "Transactions with Suppliers" );
		
		innerTabbedPane.setBounds(0,0,700,600);
		innerTabbedPane.setVisible(true);
		
		//--------------- CHECK PASSWORD----------------//
		passwordPanel.setBounds(0,0,700,600);
		
		lbPassword.setBounds(200,200,150,25);
		pfPassword.setBounds(270,200,150,25);
		pfPassword.setEchoChar(echo);
		btPassword.setBounds(200,240,100,25);
		
		passwordPanel.add(pfPassword);
		passwordPanel.add(lbPassword);
		passwordPanel.add(btPassword);
		
		passwordPanel.setVisible(true);
		
		//---------------- PO SUMMARY --------------------//
		Font font1 = new Font("SansSerif",Font.PLAIN,18);
		lbTitle3.setBounds(220,10,300,30);
		lbTitle3.setFont(font1);
		lbBorder3.setBounds(50,22,700,30);
		spPOSummary.setBounds(45,68,600,300);
		taPOSummary.setEditable(false);
		taPOSummary.setLineWrap(true);
		btLogOut1.setBounds(495,460,150,30);
		btSortPO.setBounds(495,420,150,30);
		btDateRange.setBounds(495,380,150,30);
		
		innerPanel1.add(spPOSummary);
		innerPanel1.add(lbTitle3);
		innerPanel1.add(lbBorder3);
		innerPanel1.add(btLogOut1);
		innerPanel1.add(btSortPO);
		innerPanel1.add(btDateRange);
		
		
		//---------------- SUMMARY REPORT --------------------//
		font1 = new Font("SansSerif",Font.PLAIN,18);
		lbTitle4.setBounds(220,10,300,30);
		lbTitle4.setFont(font1);
		lbBorder4.setBounds(50,22,700,30);
		spSummaryReport.setBounds(45,68,600,300);
		taSummaryReport.setEditable(false); 
		taSummaryReport.setLineWrap(true); 
		btLogOut2.setBounds(495,460,150,30);
		btSortDepartment.setBounds(495,420,150,30);
		btIsolateDepartment.setBounds(495,380,150,30);
		
		innerPanel2.add(spSummaryReport);
		innerPanel2.add(lbTitle4);
		innerPanel2.add(lbBorder4);
		innerPanel2.add(btLogOut2);
		innerPanel2.add(btSortDepartment);
		innerPanel2.add(btIsolateDepartment);
		
		
		//---------------- SUPPLIER REPORT --------------------//
		font1 = new Font("SansSerif",Font.PLAIN,18);
		lbTitle5.setBounds(220,10,300,30);
		lbTitle5.setFont(font1);
		lbBorder5.setBounds(50,22,700,30);
		spSupplierReport.setBounds(45,68,600,300);
		taSupplierReport.setEditable(false); 
		taSupplierReport.setLineWrap(true); 
		btLogOut3.setBounds(495,460,150,30);
		btSortSupplier.setBounds(495,420,150,30);
		btIsolateSupplier.setBounds(495,380,150,30);
		
		innerPanel3.add(spSupplierReport);
		innerPanel3.add(lbTitle5);
		innerPanel3.add(lbBorder5);
		innerPanel3.add(btLogOut3);
		innerPanel3.add(btSortSupplier);
		innerPanel3.add(btIsolateSupplier);
		
		panel5.add(passwordPanel);
		panel5.add( innerTabbedPane );
		
	}//end PAGE 5
	
	public ViewMode()
	{
		super();
		setVisible(true);
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
		
		// Create the tab pages
		createPage1();
		createPage2();
		createPage3();
		createPage4();
		createPage5();
		
		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Purchase Order", new ImageIcon(""),panel1,"Update PO Transaction");
		tabbedPane.addTab( "Suppliers",new ImageIcon(""), panel2,"Update Supplier" );
		tabbedPane.addTab( "Items ",new ImageIcon(""), panel3, "Update Items" );
		tabbedPane.addTab( "Departments ",new ImageIcon(""), panel4, "Update Department" );
		tabbedPane.addTab( "Reports ",new ImageIcon(""), panel5);
		topPanel.add( tabbedPane, BorderLayout.CENTER );

		//Create Table
		Table();
		
	}//end ViewMode
	
	
	public void Table()
	{
		/*---------------PAGE 1----------------*/
		
		tbItemPOModel.addColumn("QUANTITY");
		tbItemPOModel.addColumn("PARTICULARS");
		tbItemPOModel.addColumn("UNIT");
		tbItemPOModel.addColumn("UNIT PRICE");
		tbItemPOModel.addColumn("AMOUNT");
		
		/*---------------PAGE 2----------------*/
		
		tbSupplierModel.addColumn("CODE");
		tbSupplierModel.addColumn("DESCRIPTION");
		tbSupplierModel.addColumn("ADDRESS");
		
		/*---------------PAGE 3----------------*/
		
		tbItemModel.addColumn("CODE");
		tbItemModel.addColumn("DESCRIPTION");
		tbItemModel.addColumn("UNIT");
		tbItemModel.addColumn("UNIT PRICE");
		
		/*---------------PAGE 4----------------*/
		
		tbDeptModel.addColumn("CODE");
		tbDeptModel.addColumn("DESCRIPTION");
		
	}//end Table
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	


	
	
	

	
	
	
	

	
	
}