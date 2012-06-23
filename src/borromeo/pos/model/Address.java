package borromeo.pos.model;

import java.io.*;

public class Address implements Serializable{
	
	private String streetNum;
	private String street;
	private String placeInCity;
	private String city;
	private String country;
	
	public Address()
	{
		streetNum = "";
		street = "";
		placeInCity = "";
		city = "";
		country = "";
	}
	
	public Address(String stNum, String st, String location, String ct, String cntry)
	{
		setAddress(stNum,st,location,ct,cntry);
	}
	
	public void setAddress(String stNum, String st, String location, String ct, String cntry)
	{
		streetNum = stNum;
		street = st;
		placeInCity = location;
		city = ct;
		country = cntry;
	}
	
	public String getStreetNum()
	{
		return streetNum;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public String getPlaceInCity()
	{
		return placeInCity;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getCountry()
	{
		return country;
	}	
	
	public String toString()
	{
		return (streetNum+", "+street+", "+placeInCity+", "+city+", "+country);
	}
}//end class
	
	
