package edu.gatech.statusquo.spacetrader.presenter;

import java.text.DecimalFormat;

import org.eclipse.swt.widgets.Shell;

import edu.gatech.statusquo.spacetrader.driver.*;
import edu.gatech.statusquo.spacetrader.model.Player;
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
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("#.##");
		String[] vitals = {df.format(Player.getCurrency()), Double.toString(Player.getFuel())};
		vitalsView.tableItem.setText(vitals);
	}
}