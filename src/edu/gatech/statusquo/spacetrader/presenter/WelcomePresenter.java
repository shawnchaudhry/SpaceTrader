package edu.gatech.statusquo.spacetrader.presenter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import edu.gatech.statusquo.spacetrader.model.Player;
import edu.gatech.statusquo.spacetrader.model.Point;
import edu.gatech.statusquo.spacetrader.model.SolarSystem;
import edu.gatech.statusquo.spacetrader.view.*;
import edu.gatech.statusquo.spacetrader.driver.*;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class WelcomePresenter {
	WelcomeView welcomeView;
	Driver driver;

	public WelcomePresenter(Driver d, WelcomeView wv) {
		this.driver = d;
		this.welcomeView = wv;
		setListeners();
		while (!welcomeView.shell.isDisposed()) {
			if (!welcomeView.display.readAndDispatch()) {
				welcomeView.display.sleep();
			}
		}
	}

	private void setListeners() {
		welcomeView.btnStartGame.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				welcomeView.display.dispose();
				driver.generateCreatePlayer();
			}
		});

		welcomeView.btnLoadGame.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseDown(MouseEvent e) {

				try {
					welcomeView.shell.dispose();
					FileInputStream inStream;
					ObjectInputStream objectInputFile;
					inStream = new FileInputStream("spaceTrader.dat");
					objectInputFile = new ObjectInputStream(inStream);
					driver.player = (Player) objectInputFile.readObject();
					System.out.println(driver.player.getTraderSkills());
					driver.listOfSystems = (ArrayList<SolarSystem>) objectInputFile
							.readObject();
					driver.currentLocation = (Point) objectInputFile
							.readObject();
					objectInputFile.close();
					driver.generateMainGame();
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
