package edu.gatech.statusquo.spacetrader.presenter;

import java.text.DecimalFormat;
import java.util.Random;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

import edu.gatech.statusquo.spacetrader.driver.*;
import edu.gatech.statusquo.spacetrader.model.SolarSystem;
import edu.gatech.statusquo.spacetrader.view.*;

public class SolarSystemListPresenter {
	Shell shell;
	Driver driver;
	SolarSystemListView solarSystemListView;
	public static SolarSystem selected;
	
	public SolarSystemListPresenter(Shell s, Driver d, SolarSystemListView sslv) {
		this.shell = s;
		this.driver = d;
		this.solarSystemListView = sslv;
		setTable();
		setListeners();
		selected = driver.getByName(solarSystemListView.table_5.getItem(0).getText());
	}
	
	/**
	 * Sets the table on the solar system list.
	 */
	public void setTable() {
		driver.sortSolarSystemListSort();
	    for (int i = 0; i < driver.listOfSystems.size(); i++)
	    {
			double distance = driver.calculateDist(driver.listOfSystems.get(i).getCoordinates());
			DecimalFormat df = new DecimalFormat("#.##");
			String[] nameAndDist = {driver.listOfSystems.get(i).getSystemName(), df.format(distance)};
			solarSystemListView.tableItems[i].setText(nameAndDist);
	    }
	}
	
	/**
	 * Sets the solar system listeners.
	 */
	public void setListeners()
	{
	    solarSystemListView.btnTravel.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseUp(MouseEvent e) 
		{
			if(driver.player.getFuel() < Double.parseDouble(solarSystemListView.table_5.getSelection()[0].getText(1)))
			{
				driver.notificationsPresenter.addToList("Sorry, you do not have enough fuel");
			}
			else
			{
				Random rand = new Random();
				int n = rand.nextInt(3);
				if (n == 2)
				{
					RandomEvent randomEvent = new RandomEvent(driver);
				}
				double currentFuel = driver.player.getFuel();
				driver.player.setFuel(currentFuel - Double.parseDouble(solarSystemListView.table_5.getSelection()[0].getText(1)));
			    driver.vitalsPresenter.setTable();
				selected = driver.getByName(solarSystemListView.table_5.getSelection()[0].getText(0));
			    driver.tradeGoodsPresenter.updateMarketView(selected);
			    driver.currentLocation = selected.getCoordinates();
			    driver.sortSolarSystemListSort();
			    setTable();
			    driver.notificationsPresenter.addToList("You have arrived at " + selected.getSystemName());
			}
		}
	});
	}
}
