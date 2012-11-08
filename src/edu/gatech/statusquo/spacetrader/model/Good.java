package edu.gatech.statusquo.spacetrader.model;

import java.io.Serializable;

public class Good implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int quantity;
    private double price;
    	
    /**
     * Gets the price
     * @return double that is the price.
     */
    public double getPrice() {
		return price;
	}
    
    /**
     * Sets the price.
     * @param price the price to set.
     */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Gets the quantity of an item.
	 * @return the quantity.
	 */
	public int getQuantity() {
	return quantity;
    }
	
	/**
	 * Sets the quantity of the item.
	 * @param quantity - the quantity.
	 */
    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    /**
     * This is the constructor
     * 
     * @param pr
     *            - price
     * @param leg
     *            - legality
     */
    public Good(double p, int qty) {
    price = p;
	quantity = qty;
    }
}
