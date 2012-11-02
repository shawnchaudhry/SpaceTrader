package edu.gatech.statusquo.spacetrader.model;

public class Ship {
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
	case GNAT:
	    capacity = 15;
	    fuelCapacity = 200;
	case FIREFLY:
	    capacity = 20;
	    fuelCapacity = 300;
	case MOSQUITO:
	    capacity = 15;
	    fuelCapacity = 400;
	case BUMBLEBEE:
	    capacity = 25;
	    fuelCapacity = 500;
	}
    }
}
