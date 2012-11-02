package edu.gatech.statusquo.spacetrader.model;

import java.util.HashMap;

import edu.gatech.statusquo.spacetrader.model.Ship.ShipType;

public class Player {

	private Ship ship;
	private static String name;
	private static double currency;
	private static int trader;
	private static int engineer;
	private static int pilot;
	private static int fighter;
	private HashMap<String, Integer> cargo;
	private static int fuel;

	public Player() {
		ship = new Ship(ShipType.GNAT);
		fuel = 200;
		name = "Player";
		currency = 100000000;
		trader = 0;
		engineer = 0;
		pilot = 0;
		fighter = 0;
		cargo = new HashMap<String, Integer>();
		cargo.put("Water", 0);
		cargo.put("Furs", 0);
		cargo.put("Food", 0);
		cargo.put("Ore", 0);
		cargo.put("Firearms", 0);
		cargo.put("Medicine", 0);
		cargo.put("Machines", 0);
		cargo.put("Narcotics", 0);
		cargo.put("Robots", 0);
	}

	/**
	 * Sets the ship type.
	 * 
	 * @param sh
	 *            the ship type.
	 */
	public void setShip(Ship sh) {
		ship = sh;
	}

	/**
	 * Gets the ship type.
	 * 
	 * @return the ship type.
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * Gets the amount of money the player has currently.
	 * 
	 * @return an integer that is the amount of currency.
	 */
	public static double getCurrency() {
		return currency;
	}

	/**
	 * Set the currency to be a particular value.
	 * 
	 * @param curr an integer that is the amount of money.
	 */
	public void setCurrency(double curr) {
		currency = curr;
	}

	/**
	 * Get the player's name.
	 * 
	 * @return String that contains the player's name.
	 */
	public static String getName() {
		return name;
	}

	/**
	 * Set the player's name.
	 * 
	 * @param nm
	 *            - String - set the player's name to this.
	 */
	public void setName(String nm) {
		name = nm;
	}
	
	public void setTraderSkills(int i) {
		trader = i;
	}
	
	public void setEngineerSkills(int i) {
		engineer = i;
	}
	
	public void setPilotSkills(int i) {
		pilot = i;
	}
	
	public void setFighterSkills(int i) {
		fighter = i;
	}
	
	public static int getTraderSkills() {
		return trader;
	}
	
	public static int getEngineerSkills() {
		return engineer;
	}
	
	public static int getPilotSkills() {
		return pilot;
	}
	
	public static int getFighterSkills() {
		return fighter;
	}

	public HashMap<String, Integer> getCargo() {
	    return cargo;
	}

	public void insertCargo(String s, Integer i) {
	    	cargo.put(s, i);
	}

	public int cargoSize() {
	    int size = 0;
		size += cargo.get("Water");
		size += cargo.get("Furs");
		size += cargo.get("Food");
		size += cargo.get("Ore");
		size += cargo.get("Firearms");
		size += cargo.get("Medicine");
		size += cargo.get("Machines");
		size += cargo.get("Narcotics");
		size += cargo.get("Robots");
	    return size;
	}

	public static int getFuel() {
		return fuel;
	}
}
