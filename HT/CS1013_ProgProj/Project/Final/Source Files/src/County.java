import java.util.ArrayList;

public class County {

	String name;
	double averagePrice;
	double maxPrice;
	double minPrice;
	int minYear;
	int maxYear;
	double detachedCount;
	double semiDetachedCount;
	double terracedCount;
	double flatsCount;
	double otherCount;
	double totalCount;
	int detachedPercentage;
	int semiDetachedPercentage;
	int terracedPercentage;
	int flatsPercentage;
	int otherPercentage;
	String averagePropertyType;
	ArrayList<DataPoint> memberDataPointsArray;
	
	County(String countyName)
	{
		this.name = countyName;
		this.averagePrice = 0.0;
		this.maxPrice = 0.0;
		this.minPrice = 0.0;
		this.detachedCount = 0;
		this.semiDetachedCount = 0;
		this.terracedCount = 0;
		this.flatsCount = 0;
		this.totalCount = 0;
		this.otherCount = 0;
		this.detachedPercentage = 0;
		this.semiDetachedPercentage = 0;
		this.terracedPercentage = 0;
		this.otherPercentage = 0;
		this.flatsPercentage = 0;
		
		this.memberDataPointsArray = new ArrayList<DataPoint>();
	}
	
	County()
	{
		
	}
	
	public void addDataPoint(DataPoint inputDatapoint)
	{
		memberDataPointsArray.add(inputDatapoint);
	}
	
	public void calculateMaxPrice()
	{
		int maxPrice = 0;
		for(int i=0;i<memberDataPointsArray.size();i++)
		{
			DataPoint testPoint = memberDataPointsArray.get(i);
			if(testPoint.price > maxPrice)
				maxPrice = testPoint.price;
		}
		this.maxPrice = maxPrice;	
	}
	
	public void calculateMinPrice()
	{
		int minPrice = 0;
		for(int i=0;i<memberDataPointsArray.size();i++)
		{
			DataPoint testPoint = memberDataPointsArray.get(i);
			if(testPoint.price < minPrice || minPrice == 0)
				minPrice = testPoint.price;
		}
		this.minPrice = minPrice;	
	}
	
	public void findMinMaxYears()
	{
		int maxYear = 0;
		int minYear = maxYear;
		
		DataPoint testPoint1 = memberDataPointsArray.get(1);
		int year1 = Integer.parseInt(testPoint1.dateOfSale.substring(testPoint1.dateOfSale.length() - 3));
		maxYear = year1;
		minYear = year1;

		
		for(int i=1;i<memberDataPointsArray.size();i++)
		{
			DataPoint testPoint = memberDataPointsArray.get(i);
			int year = Integer.parseInt(testPoint.dateOfSale.substring(testPoint.dateOfSale.length() - 4));
			{
				if(year > maxYear)
					maxYear = year;
				
				else if(year<minYear)
					minYear = year;
			}
		}
	}
	
	public void findPropertyTypePercentages()
	{
		if(totalCount!=0)
		{
			this.detachedPercentage = (int)((this.detachedCount/this.totalCount)*360);
			this.semiDetachedPercentage = (int)((this.semiDetachedCount/this.totalCount)*360);
			this.terracedPercentage = (int)((this.terracedCount/this.totalCount)*360);
			this.flatsPercentage = (int)((this.flatsCount/this.totalCount)*360);
			this.otherPercentage = (int)((this.otherCount/this.totalCount)*360);
		}
	}
	
	public void updateCounters()
	{
		for(int i=0; i<memberDataPointsArray.size(); i++)
		{
			DataPoint testPoint = memberDataPointsArray.get(i);
			char propertyType = testPoint.propertyType;
			this.totalCount++;
			
			switch(propertyType)
			{
			case 'D':
				this.detachedCount++;
				break;
			case 'S':
				this.semiDetachedCount++;
				break;
			case 'T':
				this.terracedCount++;
				break;
			case 'F':
				this.flatsCount++;
				break;
			default:
				this.otherCount++;
				break;
			}
		}
	}
	
	public double averagePriceForYear(int inputYear)
	{
		double result = 0.0;
		int count = 0;
		
		String inputYearString = Integer.toString(inputYear);
		inputYearString = inputYearString.substring(2, 4);
		
		for(int i=0;i<memberDataPointsArray.size();i++)
		{
			DataPoint testPoint = memberDataPointsArray.get(i);
			String testPointYear = testPoint.dateOfSale.substring(6,8);
			if(testPointYear.equals(inputYearString))
			{
				result+=testPoint.price;
				count++;
			}
		}
		
		result/=count;
		return result;
	}
	public void calculateAveragePrice()
	{
		int count = 0;
		for(int i=0;i<memberDataPointsArray.size();i++)
		{
			DataPoint testPoint = memberDataPointsArray.get(i);
			this.averagePrice+= testPoint.price;
			count++;
		}
		if(count!=0)
			this.averagePrice/=count;
	}
}
