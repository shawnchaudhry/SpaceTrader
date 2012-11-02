package edu.gatech.statusquo.spacetrader.model;

public class Good {
    private double price;
    private int quantity;

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
	quantity = qty;
	price = p;

    }

    /**
     * Gets the price
     * 
     * @return int price
     */
    public double getPrice() {
	return price;
    }

    /**
     * Sets the price
     * 
     * @param price
     *            - sets the price of the good.
     */
    public void setPrice(int price) {
	this.price = price;
    }
}
