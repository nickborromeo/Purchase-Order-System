package borromeo.pos.model;
import java.io.*;

public class ReceivingDepartment implements Serializable
{
	
	private String departmentCode;
	private String departmentName;
	
	public ReceivingDepartment()
	{
		departmentCode = "";
		departmentName = "";
	}
	
	public ReceivingDepartment(String deptCode, String deptName)
	{
		setDepartment(deptCode, deptName);
	}
	
	public ReceivingDepartment(String deptName)
	{
		setDepartment(deptName);
	}
	
	public void setDepartment(String deptCode, String deptName)
	{
		departmentCode = deptCode;
		departmentName = deptName;
	}
	
	public void setDepartment(String deptName)
	{
		departmentName = deptName;
	}
	
	public String getDepartmentCode()
	{
		return departmentCode;
	}
	
	public String getDepartmentName()
	{
		return departmentName;
	}
	
	public ReceivingDepartment uppercaseDepartmentName()
	{
		ReceivingDepartment temp = new ReceivingDepartment();
		
		temp.departmentCode = departmentCode;
		temp.departmentName = departmentName.toUpperCase();
		
		return temp;
	}
	
	public String toString()
	{
		return (departmentCode+"                                           "+departmentName);
	}
	
}//end class
	
	
