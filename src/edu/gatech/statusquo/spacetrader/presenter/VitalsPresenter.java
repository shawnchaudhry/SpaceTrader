package edu.gatech.statusquo.spacetrader.presenter;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.eclipse.swt.widgets.Shell;
import edu.gatech.statusquo.spacetrader.driver.*;
import edu.gatech.statusquo.spacetrader.view.*;

public class VitalsPresenter {
	Shell shell;
	Driver driver;
	VitalsView vitalsView;

	public VitalsPresenter(Shell s, Driver d, VitalsView vv) {
		this.shell = s;
		this.driver = d;
		this.vitalsView = vv;
		setTable();
	}

	public void setTable() {
		DecimalFormat df = new DecimalFormat("#.##");
		String[] vitals = {df.format(driver.player.getCurrency()), df.format(driver.player.getFuel())};
		vitalsView.tableItem.setText(vitals);
		Iterator<String> it = driver.player.getCargo().keySet().iterator();
		int i = 0;
		while (it.hasNext()){
			String item = it.next();
			String[] tableItemString = {item, Integer.toString(driver.player.getCargo().get(item))};
			vitalsView.tableItems[i].setText(tableItemString);
			i++;
		}
	}
}