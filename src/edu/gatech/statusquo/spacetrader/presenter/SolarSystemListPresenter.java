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
		selected = driver.getByName(solarSystemListView.table_5.getItem(0).getText());
	}
	
	private void setTable() {
	    for (int i = 0; i < Driver.listOfSystems.size(); i++)
	    {
		System.out.println(Driver.listOfSystems.get(i).toString());
		double distance = driver.calculateDist(Driver.listOfSystems.get(i).getCoordinates());
		DecimalFormat df = new DecimalFormat("#.##");
		String[] nameAndDist = {Driver.listOfSystems.get(i).getSystemName(), df.format(distance)};
		solarSystemListView.tableItems[i].setText(nameAndDist);
	    }
	}

	public void setListeners()
	{
	    solarSystemListView.btnTravel.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseUp(MouseEvent e) {
		    selected = driver.getByName(solarSystemListView.table_5.getItem(0).getText(solarSystemListView.table_5.getSelectionIndex()));
		}
	});
	}
}
