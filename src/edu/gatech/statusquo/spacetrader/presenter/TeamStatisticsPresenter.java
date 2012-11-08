package edu.gatech.statusquo.spacetrader.presenter;

import org.eclipse.swt.widgets.Shell;

import edu.gatech.statusquo.spacetrader.driver.Driver;
import edu.gatech.statusquo.spacetrader.view.*;

public class TeamStatisticsPresenter {
	Shell shell;
	Driver driver;
	TeamStatisticsView teamStatisticsView;

	public TeamStatisticsPresenter(Shell s, Driver d, TeamStatisticsView tsv) {
		this.shell = s;
		this.driver = d;
		this.teamStatisticsView = tsv;
		setTable();
	}
	
	/**
	 * Sets the table with the statistics.
	 */
	public void setTable()
	{
		teamStatisticsView.lblTeamStatistics.setText("Team Statistics");

		teamStatisticsView.tblclmnAttribute_1.setText("Attribute");

		teamStatisticsView.tblclmnPoints.setText("Points");

		String[] traderArray = { "Trader", Integer.toString(driver.player.getTraderSkills()) };
		teamStatisticsView.tableItem_1.setText(traderArray);

		String[] engineerArray = { "Engineer", Integer.toString(driver.player.getEngineerSkills()) };
		teamStatisticsView.tableItem_2.setText(engineerArray);

		String[] pilotArray = { "Pilot", Integer.toString(driver.player.getPilotSkills()) };
		teamStatisticsView.tableItem_3.setText(pilotArray);

		String[] fighterArray = { "Fighter", Integer.toString(driver.player.getFighterSkills()) };
		teamStatisticsView.tableItem_4.setText(fighterArray);
	}
}
