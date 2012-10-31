package edu.gatech.statusquo.spacetrader.model;

import edu.gatech.statusquo.spacetrader.driver.Driver;

public class Ship {
    ShipType type;
    int cargoSize;
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
	    cargoSize = 10;
	case GNAT:
	    cargoSize = 15;
	case FIREFLY:
	    cargoSize = 20;
	case MOSQUITO:
	    cargoSize = 15;
	case BUMBLEBEE:
	    cargoSize = 25;
	}
    }
}
