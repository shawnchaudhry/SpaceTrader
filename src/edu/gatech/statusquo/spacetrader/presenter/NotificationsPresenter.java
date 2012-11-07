package edu.gatech.statusquo.spacetrader.presenter;

import org.eclipse.swt.widgets.Shell;

import edu.gatech.statusquo.spacetrader.driver.*;
import edu.gatech.statusquo.spacetrader.view.*;

public class NotificationsPresenter {

	Shell shell;
	Driver driver;
	public NotificationsView notificationsView;

	public NotificationsPresenter(Shell s, Driver d, NotificationsView nv) {
		this.shell = s;
		this.driver = d;
		this.notificationsView = nv;
		createInput();
	}

	private void createInput() {
	    // TODO Auto-generated method stub
	    notificationsView.list_1.add("Welcome to Space Trader " + driver.player.getName());
	    notificationsView.list_1.add("You are currently in " + driver.getByCoordinate(driver.getCurrentLocation()).getSystemName());
	    notificationsView.list_1.add("You have enough fuel to travel 200 parsecs");
	}
	
	public void addToList(String s)
	{
	    notificationsView.list_1.add(s);
	    notificationsView.list_1
		.select(notificationsView.list_1
				.getItemCount() - 1);
	    notificationsView.list_1.showSelection();
	}
}
