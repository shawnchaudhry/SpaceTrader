package edu.gatech.statusquo.spacetrader.model;

import java.util.HashMap;

public class SolarSystem {

	private  String systemName;
	private int techLevel;
	private int resourceLevel;
	private int xLocation;
	private int yLocation;
	private Point location;
	private Planet planet;
	private HashMap<String, Good> market;
	
	/**
	 * Class constructor using listed parameters
	 * @param systemName
	 * @param planet
	 * @param techLevel
	 * @param resourceLevel
	 * @param xLocation
	 * @param yLocation
	 */
	public SolarSystem(String systemName, Planet planet, int techLevel,
			int resourceLevel, int xLocation, int yLocation) {

		this.systemName = systemName;
		this.planet = planet;
		this.techLevel = techLevel;
		this.resourceLevel = resourceLevel;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.location = new Point(xLocation, yLocation);
		market = new HashMap<String, Good>();
	}
	
	public int getTechLevel() {
	    return techLevel;
	}

	public void setTechLevel(int techLevel) {
	    this.techLevel = techLevel;
	}

	public int getResourceLevel() {
	    return resourceLevel;
	}

	public void setResourceLevel(int resourceLevel) {
	    this.resourceLevel = resourceLevel;
	}

	public String getSystemName() {
	    return systemName;
	}

	public HashMap<String, Good> getMarket() {
		return market;
	}
	
	public void setMarket(HashMap<String, Good> marketPlace) {
		market = marketPlace;
	}

	/**
	 * @return Information of the SolarSystem in a String.
	 */
	public String toString() {
		return ("SystemName: " + systemName + "\n" + planet.toString()
				+ "TechLevel: " + techLevel + "\n" + "ResourceLevel: "
				+ resourceLevel + "\n" + "X-Position: " + xLocation + "\n"
				+ "Y-Position: " + yLocation + "\n");
	}
	
	public Point getCoordinates()
	{
	    return location;
	}
}
