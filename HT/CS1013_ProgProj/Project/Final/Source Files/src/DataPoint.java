
public class DataPoint {

	public int price;
	public String dateOfSale;
	private String postcode;
	public char propertyType;
	private char oldNew;
	private String numName;
	private String street;
	private String locality;
	private String town;
	private String district;
	public String county;
	
	DataPoint(String[] pointInfo){
		
		this.price = Integer.parseInt(pointInfo[0]);
		this.dateOfSale = pointInfo[1];
		this.postcode = pointInfo[2];
		this.propertyType = pointInfo[3].charAt(0);
		this.oldNew = pointInfo[4].charAt(0);
		this.numName = pointInfo[5];
		this.street = pointInfo[6];
		this.locality = pointInfo[7];
		this.town = pointInfo[8];
		this.district = pointInfo[9];
		this.county = pointInfo[10];
	}
	
	DataPoint(){
		
	}
	
	public void setPrice()
	{
		
	}
	
	public String dataToString()
	{
		return ("" + this.price + ", " + this.postcode + ", " + this.propertyType
				+ ", " + this.oldNew + ", " + this.numName + ", " + this.street + ", " + this.locality
				 + ", " + this.town + ", " + this.district + ", " + this.county + "\n");
	}
}
