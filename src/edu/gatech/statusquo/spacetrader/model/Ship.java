package edu.gatech.statusquo.spacetrader.model;

import java.io.Serializable;

public class Ship implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ShipType type;
    public int capacity;
    public int fuelCapacity;
    SolarSystem location;
    
    public enum ShipType {
	FLEA, GNAT, FIREFLY, MOSQUITO, BUMBLEBEE
    }
    
    public Ship(ShipType t)
    {
	this.type = t;
	switch(t)
	{
	case FLEA:
	    capacity = 10;
	    fuelCapacity = 100;
	    break;
	case GNAT:
	    capacity = 15;
	    fuelCapacity = 200;
	    break;
	case FIREFLY:
	    capacity = 20;
	    fuelCapacity = 300;
	    break;
	case MOSQUITO:
	    capacity = 15;
	    fuelCapacity = 400;
	    break;
	case BUMBLEBEE:
	    capacity = 25;
	    fuelCapacity = 500;
	    break;
	}
    }
}
