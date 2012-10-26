package edu.gatech.statusquo.spacetrader.driver;

import edu.gatech.statusquo.spacetrader.model.*;
import edu.gatech.statusquo.spacetrader.presenter.*;
import edu.gatech.statusquo.spacetrader.view.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Driver {
    Player player;
    public Display display;
    public Shell shell;
    private static Driver driver;
    public static int currency = 1000;
    static ArrayList<Integer> totalSkills;
    ArrayList<Integer> partySkills;
    static Random rand;
    private static Map<Integer, Integer> xAndYCoordinates = new HashMap<Integer, Integer>();
    public static ArrayList<String> listOfNames;

    private static ArrayList<SolarSystem> listOfSystems;

    public Driver() throws IOException {
	rand = new Random();
	player = new Player();
	listOfSystems = new ArrayList<SolarSystem>();
	generateUniverse();
    }

    public static void main(String[] args) {
	driver = null;
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
	new CreatePlayerPresenter(driver, createPlayerView, player);
    }

    public void generateMainGame() {
	display = Display.getDefault();
	shell = new Shell(display, SWT.TITLE | SWT.CLOSE);

	shell.open();
	shell.layout();

	shell.setSize(1024, 768);
	shell.setText("Space Trader");
	shell.setLayout(null);

	ShipStatisticsView shipStatisticsView = new ShipStatisticsView(shell);
	new ShipStatisticsPresenter(shell, driver, shipStatisticsView);

	TeamStatisticsView teamStatisticsView = new TeamStatisticsView(shell,
		player);
	new TeamStatisticsPresenter(shell, driver, teamStatisticsView);

	SolarSystemListView solarSystemListView = new SolarSystemListView(shell);
	new SolarSystemListPresenter(shell, driver, solarSystemListView);

	TradeGoodsView tradeGoodsView = new TradeGoodsView(shell);
	new TradeGoodsPresenter(shell, driver, tradeGoodsView);

	NotificationsView notificationsView = new NotificationsView(shell,
		player);
	new NotificationsPresenter(shell, driver, notificationsView);

	VitalsView vitalsView = new VitalsView(shell, player);
	new VitalsPresenter(shell, driver, vitalsView);

	LocalPlanetView localPlanetView = new LocalPlanetView(shell);
	new LocalPlanetPresenter(shell, driver, localPlanetView);

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
    public static void generateUniverse() throws IOException {
	generateCoordinates();
	generateNames();
	Iterator<Integer> it = xAndYCoordinates.keySet().iterator();
	while (it.hasNext()) {
	    for (int i = 0; i < xAndYCoordinates.size(); i++) {
		Planet planetName = new Planet(listOfNames.get(i));
		int xCoordinate = it.next();
		SolarSystem system = new SolarSystem(listOfNames.get(i),
			planetName, rand.nextInt(8), rand.nextInt(11),
			xCoordinate, xAndYCoordinates.get(xCoordinate));
		generateMarket(system);
		System.out.println(system);
		System.out.println(xAndYCoordinates.size());
		listOfSystems.add(system);
	    }
	}
    }

    public static void generateMarket(SolarSystem s) {

    }

    /**
     * Creates list of usable X and Y coordinates for solar systems. That are at
     * least 10 units away from any other coordinate.
     */
    public static void generateCoordinates() {
	xAndYCoordinates.put(0, 0);
	while (xAndYCoordinates.size() != 150) 
	{
	    int testX = (rand.nextInt(201));
	    int testY = (rand.nextInt(201));
    	    Iterator<Integer> it = xAndYCoordinates.keySet().iterator();
    	    while (it.hasNext()) 
    	    {
    		int testCoordinateX = it.next();
    		int testCoordinateY = xAndYCoordinates.get(testCoordinateX);
    		if (testX != testCoordinateX) 
    		{
    		    if ((Math.sqrt(Math.pow((testCoordinateX - testX), 2.0)
    			    + Math.pow((testCoordinateY - testY), 2.0))) > 10.0) 
    		    {
    			xAndYCoordinates.put(testX, testY);
    		    }
    		}
    	    }
	}
    }

    /**
     * Gets the entire party's skills.
     * 
     * @return an ArrayList<Integer> that contains all the skills.
     */
    public static ArrayList<Integer> getSkills() {
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

    public static void generateNames() throws IOException {
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
	    while (check == true) {
		int random = (rand.nextInt(planetNames.size()));
		String hold = planetNames.get(random);
		if (chosenNames.contains(hold) == false) {
		    chosenNames.add(hold);
		    check = false;
		}
	    }
	}
	listOfNames = chosenNames;
	s.close();
    }
}
