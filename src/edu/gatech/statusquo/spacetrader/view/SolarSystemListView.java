package edu.gatech.statusquo.spacetrader.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import edu.gatech.statusquo.spacetrader.driver.Driver;

public class SolarSystemListView {
	Shell shell;
	Driver driver;
	Label lblNewLabel;
	public Button btnTravel;
	public Table table_5;
	public TableItem[] tableItems;
	
	public SolarSystemListView(Shell s, Driver d) {
		this.shell = s;
		this.driver = d;
		lblNewLabel = new Label(shell, SWT.NONE);
		btnTravel = new Button(shell, SWT.NONE);
		table_5 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table_5.setHeaderVisible(true);
		table_5.setLinesVisible(true);
		TableColumn tblclmnSolarSystem = new TableColumn(table_5, SWT.NONE);
		tblclmnSolarSystem.setWidth(86);
		tblclmnSolarSystem.setText("Solar System");
		
		TableColumn tblclmnDistance = new TableColumn(table_5, SWT.NONE);
		tblclmnDistance.setWidth(90);
		tblclmnDistance.setText("Distance");
		
		tableItems = new TableItem[driver.listOfSystems.size()];
		for (int i = 0; i < tableItems.length; i++)
		{
		    tableItems[i] = new TableItem(table_5, SWT.NONE);
		}
		table_5.setSelection(0);

		try {
			createView();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		createView();
	}
	
	public void createView() {
		lblNewLabel.setBounds(53, 423, 94, 15);
		lblNewLabel.setText("Solar System List");
		btnTravel.setBounds(55, 695, 75, 25);
		btnTravel.setText("Travel");
		table_5.setBounds(10, 444, 180, 245);
	}
}
