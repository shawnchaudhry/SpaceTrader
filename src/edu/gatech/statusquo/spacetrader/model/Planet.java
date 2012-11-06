package edu.gatech.statusquo.spacetrader.model;

import java.io.Serializable;

public class Planet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String planetName;

	/**
	 * Class constructor using listed parameters.
	 * 
	 * @param planetName
	 */

	public Planet(String planetName) {
		Planet.planetName = planetName;
	}

	/**
	 * @return Information about Planet in a String
	 * 
	 */
	public String toString() {
		return ("PlanetName: " + planetName + "\n");
	}

}
