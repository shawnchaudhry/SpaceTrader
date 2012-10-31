package edu.gatech.statusquo.spacetrader.model;

public class Point implements Comparable {
    private int xcoord;
    private int ycoord;
    
    public Point(int x, int y)
    {
	xcoord = x;
	ycoord = y;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    @Override
    public int compareTo(Object o) {
	Point other = (Point) o;
	if(this.xcoord == other.xcoord && this.ycoord == other.ycoord)
	{
	    return 0;
	}
	else
	{
	    return 1;
	}
    }
    
}
