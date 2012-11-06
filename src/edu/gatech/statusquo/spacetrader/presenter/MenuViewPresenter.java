package edu.gatech.statusquo.spacetrader.presenter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import edu.gatech.statusquo.spacetrader.driver.Driver;
import edu.gatech.statusquo.spacetrader.model.Player;
import edu.gatech.statusquo.spacetrader.model.Point;
import edu.gatech.statusquo.spacetrader.view.MenuView;
import edu.gatech.statusquo.spacetrader.model.SolarSystem;

public class MenuViewPresenter {
	Shell shell;
	Driver driver;
	MenuView menuView;

	public MenuViewPresenter(Shell shell, Driver driver, MenuView menuView) {
		this.shell = shell;
		this.driver = driver;
		this.menuView = menuView;
		setMenu();
	}

	private void setMenu() {
		shell.setMenuBar(menuView.menu);

		menuView.mntmFile.setText("File");

		menuView.mntmFile.setMenu(menuView.menu_1);

		menuView.mntmNewGame.setText("New Game");
		menuView.mntmNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		menuView.mntmSaveGame.setText("Save Game");
		menuView.mntmSaveGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					FileOutputStream outStream;
					ObjectOutputStream objectOutputFile;
					
					outStream = new FileOutputStream("spaceTrader.dat");
					objectOutputFile = new ObjectOutputStream(
							outStream);
					objectOutputFile.writeObject(driver.player);
					objectOutputFile.writeObject(Driver.listOfSystems);
					objectOutputFile.writeObject(Driver.currentLocation);
					objectOutputFile.close();
				} catch (IOException io) {
					// TODO Auto-generated catch block
					io.printStackTrace();
				}
			}
		});
		menuView.mntmLoadGame.setText("Load Game");
		menuView.mntmLoadGame.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					FileInputStream inStream;
					ObjectInputStream objectInputFile;
					inStream = new FileInputStream("spaceTrader.dat");
					objectInputFile = new ObjectInputStream(
							inStream);
					Player p = new Player();
					p = (Player) objectInputFile
							.readObject();
					System.out.println(p.getTraderSkills());
					driver.player = p;
					Driver.listOfSystems = (ArrayList<SolarSystem>) objectInputFile
							.readObject();
					Driver.currentLocation = (Point) objectInputFile
							.readObject();
					objectInputFile.close();
					driver.solarSystemListPresenter.setTable();
					driver.solarSystemListPresenter.solarSystemListView.table_5
							.setSelection(0);
					driver.vitalsPresenter.setTable();
					driver.teamStatisticsPresenter.setTable();
					driver.tradeGoodsPresenter.updateMarketView(driver
							.getByName(driver.solarSystemListView.table_5
									.getSelection()[0].getText(0)));
				} catch (IOException io) {
					// TODO Auto-generated catch block
					io.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
