package edu.gatech.statusquo.spacetrader.presenter;

import java.text.DecimalFormat;

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
	
	public void setTable() {
		Driver.sortSolarSystemListSort();
	    for (int i = 0; i < Driver.listOfSystems.size(); i++)
	    {
			double distance = Driver.calculateDist(Driver.listOfSystems.get(i).getCoordinates());
			DecimalFormat df = new DecimalFormat("#.##");
			String[] nameAndDist = {Driver.listOfSystems.get(i).getSystemName(), df.format(distance)};
			solarSystemListView.tableItems[i].setText(nameAndDist);
	    }
	}

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
				double currentFuel = driver.player.getFuel();
				driver.player.setFuel(currentFuel - Double.parseDouble(solarSystemListView.table_5.getSelection()[0].getText(1)));
			    driver.vitalsPresenter.setTable();
				selected = driver.getByName(solarSystemListView.table_5.getSelection()[0].getText(0));
			    driver.tradeGoodsPresenter.updateMarketView(selected);
			    Driver.currentLocation = selected.getCoordinates();
			    Driver.sortSolarSystemListSort();
			    setTable();
			    driver.notificationsPresenter.addToList("You have arrived at " + selected.getSystemName());
			}
		}
	});
	}
}
