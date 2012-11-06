package edu.gatech.statusquo.spacetrader.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import edu.gatech.statusquo.spacetrader.model.*;

public class TeamStatisticsView {
	Player player;
	Shell shell;
	Table table;
	public TableColumn tblclmnAttribute_1;
	public TableColumn tblclmnPoints;
	public TableItem tableItem_1;
	public TableItem tableItem_2;
	public TableItem tableItem_3;
	public TableItem tableItem_4;
	public Label lblTeamStatistics;
	
	public TeamStatisticsView (Shell s, Player p) {
		this.shell = s;
		this.player = p;
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tblclmnAttribute_1 = new TableColumn(table, SWT.NONE);
		tblclmnPoints = new TableColumn(table, SWT.NONE);
		tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_4 = new TableItem(table, SWT.NONE);

		try {
			createView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createView() {
		lblTeamStatistics = new Label(shell, SWT.NONE);
		lblTeamStatistics.setBounds(53, 231, 94, 15);

		table.setBounds(0, 252, 190, 165);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tblclmnAttribute_1.setWidth(85);

		tblclmnPoints.setWidth(100);
		
	}
}
