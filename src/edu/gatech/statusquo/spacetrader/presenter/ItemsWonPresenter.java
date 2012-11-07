package edu.gatech.statusquo.spacetrader.presenter;

import java.util.HashMap;

import edu.gatech.statusquo.spacetrader.driver.Driver;
import edu.gatech.statusquo.spacetrader.view.ItemsWonView;

public class ItemsWonPresenter {
	HashMap<String, Integer> wonItems = new HashMap<String, Integer>();
	Driver driver;
	ItemsWonView itemsWonView;
	public ItemsWonPresenter(Driver d, ItemsWonView itemWV, HashMap<String, Integer> wI)
	{
		wonItems = wI;
		driver = d;
		itemsWonView = itemWV;
		while (!itemsWonView.shell.isDisposed()) 
		{
			if (!itemsWonView.display.readAndDispatch()) 
			{
				itemsWonView.display.sleep();
			}
		}
		setListeners();
	}
	private void setListeners() {
		
	}
}
