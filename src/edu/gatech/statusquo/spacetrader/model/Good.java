package edu.gatech.statusquo.spacetrader.model;

import java.io.Serializable;

public class Good implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int quantity;
    private double price;
    
    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
	return quantity;
    }

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
