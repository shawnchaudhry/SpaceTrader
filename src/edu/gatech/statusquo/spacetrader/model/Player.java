package edu.gatech.statusquo.spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

import edu.gatech.statusquo.spacetrader.model.Ship.ShipType;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ship ship;
	private String name;
	private double currency;
	private int trader;
	private int engineer;
	private int pilot;
	private int fighter;
	private HashMap<String, Integer> cargo;
	private static double fuel;

	public Player() {
		ship = new Ship(ShipType.GNAT);
		fuel = 200;
		name = "Player";
		currency = 1000;
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
	public double getCurrency() {
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
	public String getName() {
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
	
	/**
	 * Sets the trader skill points.
	 * @param i the integer of the skill points.
	 */
	public void setTraderSkills(int i) {
		trader = i;
	}
	
	/**
	 * Sets the engineer skill points.
	 * @param i the integer of the engineer's skill points.
	 */
	public void setEngineerSkills(int i) {
		engineer = i;
	}
	
	public void setPilotSkills(int i) {
		pilot = i;
	}
	
	public void setFighterSkills(int i) {
		fighter = i;
	}
	
	public int getTraderSkills() {
		return trader;
	}
	
	public int getEngineerSkills() {
		return engineer;
	}
	
	public int getPilotSkills() {
		return pilot;
	}
	
	public int getFighterSkills() {
		return fighter;
	}
	
	/**
	 * returns the player's cargo.
	 * @return the player's cargo.
	 */
	public HashMap<String, Integer> getCargo() {
	    return cargo;
	}

	/**
	 * Inserts into the cargo a particular item.
	 * @param s - the item name.
	 * @param i - the item amount;
	 */
	public void insertCargo(String s, Integer i) {
		int prevAmt = cargo.get(s);
		cargo.put(s, prevAmt + i);
	}
	
	/**
	 * returns the player's cargo size.
	 * @return int that is the player's cargo size.
	 */
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
	
	/**
	 * Returns the amount of fuel.
	 * @return the amount of fuel.
	 */
	public double getFuel() {
		return fuel;
	}
	
	/**
	 * Sets the amount of fuel.
	 * @param d - the amount of fuel.
	 */
	public void setFuel(double d)
	{
		fuel = d;
	}
}
