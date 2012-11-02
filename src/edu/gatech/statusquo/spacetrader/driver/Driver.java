package edu.gatech.statusquo.spacetrader.driver;

import edu.gatech.statusquo.spacetrader.model.*;
import edu.gatech.statusquo.spacetrader.presenter.*;
import edu.gatech.statusquo.spacetrader.view.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Driver {
    public Player player;
    public Display display;
    public Shell shell;
    public int currency = 1000;
    ArrayList<Integer> totalSkills;
    ArrayList<Integer> partySkills;
    Random rand;
    public ArrayList<Point> xAndYCoordinates;
    public ArrayList<String> listOfNames;
    public HashMap<String, Good> marketPlace;
    public Point currentLocation;
    public ShipStatisticsView shipStatisticsView;
    public TeamStatisticsView teamStatisticsView;
    public SolarSystemListView solarSystemListView;
    public TradeGoodsView tradeGoodsView;
    public NotificationsView notificationsView;
    public VitalsView vitalsView;
    public LocalPlanetView localPlanetView;
    public ShipStatisticsPresenter shipStatisticsPresenter;
    public TeamStatisticsPresenter teamStatisticsPresenter;
    public SolarSystemListPresenter solarSystemListPresenter;
    public TradeGoodsPresenter tradeGoodsPresenter;
    public NotificationsPresenter notificationsPresenter;
    public VitalsPresenter vitalsPresenter;
    public LocalPlanetPresenter localPlanetPresenter;
    
    
    public static ArrayList<SolarSystem> listOfSystems;

    public Driver() throws IOException {
	rand = new Random();
	player = new Player();
	listOfSystems = new ArrayList<SolarSystem>();
	xAndYCoordinates = new ArrayList<Point>();
	generateUniverse();
    }

    public static void main(String[] args) {
	Driver driver = null;
	try {
	    driver = new Driver();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	WelcomeView welcomeView = new WelcomeView();
	new WelcomePresenter(driver, welcomeView);
    }

    public void generateCreatePlayer() {
	CreatePlayerView createPlayerView = new CreatePlayerView();
	new CreatePlayerPresenter(this, createPlayerView, player);
    }

    public void generateMainGame() {
	display = Display.getDefault();
	shell = new Shell(display, SWT.TITLE | SWT.CLOSE);

	shell.open();
	shell.layout();

	shell.setSize(1024, 768);
	shell.setText("Space Trader");
	shell.setLayout(null);
	
	currentLocation = new Point(0, 0);
	
	shipStatisticsView = new ShipStatisticsView(shell);
	shipStatisticsPresenter = new ShipStatisticsPresenter(shell, this, shipStatisticsView);

	teamStatisticsView = new TeamStatisticsView(shell,
		player);
	teamStatisticsPresenter = new TeamStatisticsPresenter(shell, this, teamStatisticsView);

	solarSystemListView = new SolarSystemListView(shell);
	solarSystemListPresenter = new SolarSystemListPresenter(shell, this, solarSystemListView);

	tradeGoodsView = new TradeGoodsView(shell);
	tradeGoodsPresenter = new TradeGoodsPresenter(shell, this, tradeGoodsView);

	notificationsView = new NotificationsView(shell,
		player);
	notificationsPresenter = new NotificationsPresenter(shell, this, notificationsView);

	vitalsView = new VitalsView(shell, player);
	vitalsPresenter = new VitalsPresenter(shell, this, vitalsView);

	localPlanetView = new LocalPlanetView(shell);
	localPlanetPresenter = new LocalPlanetPresenter(shell, this, localPlanetView);

	while (!shell.isDisposed()) {
	    if (!display.readAndDispatch()) {
		display.sleep();
	    }
	}
    }

    /**
     * Creates the universe that the game will be played in.
     * 
     * @throws IOException
     */
    public void generateUniverse() throws IOException {
	generateCoordinates();
	generateNames();
	for (int i = 0; i < listOfNames.size(); i++) 
	{
	    SolarSystem system = new SolarSystem(listOfNames.get(i),
		    new Planet(listOfNames.get(i)), rand.nextInt(8), rand.nextInt(11),
		    xAndYCoordinates.get(i).getXcoord(), xAndYCoordinates.get(i).getYcoord());
	    generateMarket(system);
	    listOfSystems.add(system);
	}
    }

    public void generateMarket(SolarSystem s) {
	marketPlace = new HashMap<String, Good>();
	double waterCoeff = 1;
	double furCoeff = 1;
	double foodCoeff = 1;
	double oreCoeff = 1;
	double gamesCoeff = 1;
	double firearmsCoeff = 1;
	double medicineCoeff = 1;
	double machinesCoeff = 1;
	double narcoticsCoeff = 1;
	double robotsCoeff = 1;
	
	switch (s.getTechLevel())
	{
		case 0:
		{
		    firearmsCoeff = 0;
		    machinesCoeff = 0;
		    robotsCoeff = 0;
		    medicineCoeff = .8;
		    break;
		}
		case 1:
		{
		    firearmsCoeff = 0;
		    machinesCoeff = 0;
		    robotsCoeff = 0;
		    medicineCoeff = .8;
		    break;
		}
		case 2:
		{
		    machinesCoeff = 0;
		    robotsCoeff = 0;
		    medicineCoeff = .8;
		    break;
		}
		case 3:
		{
		    robotsCoeff = 0;
		    break;
		}
		default:
		    break;
	}
	switch (s.getResourceLevel())
	{
        	case 0:
        	{
        	    break;
        	}
        	
        	case 1:
        	{
        	    oreCoeff = 2;
        	    break;
        	}
        	case 2:
        	{
        	    oreCoeff = .5;
        	    break;
        	}
        	case 3:
        	{
        	    waterCoeff = .5;
        	    foodCoeff = .5;
        	    furCoeff = .5;
        	    break;
        	}
        	case 4:
        	{
        	    waterCoeff = 2;
        	    break;
        	}
        	case 5:
        	{
        	    foodCoeff = 2;
        	    break;
        	}
        	case 6:
        	{
        	    foodCoeff = .5;
        	    break;
        	}
        	case 7:
        	{
        	    foodCoeff = 2;
        	    furCoeff = 2;
        	    break;
        	}
        	case 8:
        	{
        	    furCoeff = 0;
        	    foodCoeff = 0;
        	    break;
        	}
        	case 9:
        	{
        	    narcoticsCoeff = 2;
        	    break;
        	}
        	case 10:
        	{
        	    foodCoeff = 1.5;
        	    narcoticsCoeff = 1.5;
        	    break;
        	}
        	default:
        	    break;
	}
	marketPlace.put("Water", new Good(30 * waterCoeff, 100));
	marketPlace.put("Furs", new Good(250 * furCoeff, 100));
	marketPlace.put("Food", new Good(100 * foodCoeff, 100));
	marketPlace.put("Ore", new Good(350 * oreCoeff, 100));
	marketPlace.put("Games", new Good(250 * gamesCoeff, 100));
	marketPlace.put("Firearms", new Good(1250 * firearmsCoeff, 100));
	marketPlace.put("Medicine", new Good(650 * medicineCoeff, 100));
	marketPlace.put("Machines", new Good(900 * machinesCoeff, 100));
	marketPlace.put("Narcotics", new Good(3500 * narcoticsCoeff, 100));
	marketPlace.put("Robots", new Good(5000 * robotsCoeff, 100));
	marketPlace.put("Fuel", new Good(10, 100));
	s.setMarket(marketPlace);
    }

    /**
     * Creates list of usable X and Y coordinates for solar systems. That are at
     * least 10 units away from any other coordinate.
     */
    public void generateCoordinates() 
    {
	xAndYCoordinates.add(new Point(0, 0));
	while (xAndYCoordinates.size() < 150)
	{
	    boolean validPoint = true;
	    int testX = (rand.nextInt(201));
	    int testY = (rand.nextInt(201));
	    for (int i = 0; i < xAndYCoordinates.size(); i++)
	    {
		Point testPoint = xAndYCoordinates.get(i);
		if ((Math.sqrt(Math.pow((testPoint.getXcoord() - testX), 2.0)
			    + Math.pow((testPoint.getYcoord() - testY), 2.0))) < 10.0) 
		    {
		    	validPoint = false;
		    }
		
	    }
	    if(validPoint)
		xAndYCoordinates.add(new Point(testX, testY));
	}
    }
    /**
     * Gets the entire party's skills.
     * 
     * @return an ArrayList<Integer> that contains all the skills.
     */
    public ArrayList<Integer> getSkills() {
	return totalSkills;
    }

    /**
     * This method is to boost the player's statistics in case he/she purchases
     * a mercenary.
     * 
     * @param tr
     *            number of trader points.
     * @param en
     *            number of engineer points.
     * @param pi
     *            number of pilot points.
     * @param fi
     *            number of fighter points.
     */
    public void boostSkillPoints(int tr, int en, int pi, int fi) {
	int[] boostPoints = { tr, en, pi, fi };
	ArrayList<Integer> newTotalSkills = new ArrayList<Integer>();
	for (int i = 0; i < boostPoints.length; i++) {
	    newTotalSkills.add(totalSkills.get(i) + boostPoints[i]);
	}
		totalSkills = newTotalSkills;
    }

    /**
     * Generates a list of 150 planet names from an existing text file.
     * 
     */

    public void generateNames() throws IOException {
	Scanner s = new Scanner(new File("PlanetNames.txt"));
	ArrayList<String> planetNames = new ArrayList<String>();
	while (s.hasNextLine()) {
	    String temp = s.nextLine();
	    planetNames.add(temp);
	}
	ArrayList<String> chosenNames = new ArrayList<String>();
	int amtOfStrings = 150;
	for (int i = 0; i < amtOfStrings; i++) {
	    boolean check = true;
	    while (check == true) 
	    {
			int random = (rand.nextInt(planetNames.size()));
			String hold = planetNames.get(random);
			if (chosenNames.contains(hold) == false) 
			{
			    chosenNames.add(hold);
			    check = false;
			}
	    }
	}
	listOfNames = chosenNames;
	s.close();
    }
    
    public SolarSystem getByName(String s)
    {
	for (int i = 0 ; i < listOfSystems.size(); i++)
	{
	    if(s.equals(listOfSystems.get(i).getSystemName()))
	    {
		return listOfSystems.get(i);
	    }
	}
	return null;
    }
    
    public SolarSystem getByCoordinate(Point p)
    {
	for (int i = 0; i < listOfSystems.size(); i++)
	{
	    if(p.compareTo(listOfSystems.get(i).getCoordinates()) == 0)
	    {
		return listOfSystems.get(i);
	    }
	}
		return null;
    }
    
    public SolarSystem retrieveSelectedSolarSystem()
    {
    	return getByName(solarSystemListView.table_5.getSelection()[0].getText(0));
    }
    
    public Point getCurrentLocation()
    {
    	return currentLocation;
    }
    
    public double calculateDist(Point p)
    {
    	return Math.sqrt(Math.pow((p.getXcoord() - currentLocation.getXcoord()), 2.0)
		    + Math.pow((p.getYcoord() - currentLocation.getYcoord()), 2.0));
    }
}
