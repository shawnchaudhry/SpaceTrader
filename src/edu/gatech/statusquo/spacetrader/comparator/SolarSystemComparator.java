package edu.gatech.statusquo.spacetrader.comparator;

import edu.gatech.statusquo.spacetrader.driver.Driver;
import edu.gatech.statusquo.spacetrader.model.Point;
import edu.gatech.statusquo.spacetrader.model.SolarSystem;
import java.util.Comparator;

public class SolarSystemComparator implements Comparator<SolarSystem> {
	
	@Override
	public int compare(SolarSystem s1, SolarSystem s2)
	{
		Point point1 = s1.getCoordinates();
		Point point2 = s2.getCoordinates();
		
		double distance1 = (int) Driver.calculateDist(point1);
		double distance2 = (int) Driver.calculateDist(point2);
		
		if (distance1 > distance2)
		{
			return 1;
		}
		else if (distance2 > distance1)
		{
			return -1;
		}
		return 0;
	}

}
