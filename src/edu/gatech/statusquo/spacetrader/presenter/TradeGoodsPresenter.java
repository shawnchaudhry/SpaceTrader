package edu.gatech.statusquo.spacetrader.presenter;

import java.util.HashMap;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

import edu.gatech.statusquo.spacetrader.driver.*;
import edu.gatech.statusquo.spacetrader.model.Good;
import edu.gatech.statusquo.spacetrader.model.Player;
import edu.gatech.statusquo.spacetrader.model.SolarSystem;
import edu.gatech.statusquo.spacetrader.view.*;

public class TradeGoodsPresenter {
	Shell shell;
	Driver driver;
	TradeGoodsView tradeGoodsView;
	HashMap<String, Good> market;

	public TradeGoodsPresenter(Shell s, Driver d, TradeGoodsView tgv) {
		this.shell = s;
		this.driver = d;
		this.tradeGoodsView = tgv;
		setListeners();
		market = driver.retrieveSelectedSolarSystem().getMarket();
		setTable(market);
	}

	public void setListeners() {
		tradeGoodsView.text_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tradeGoodsView.text_1.setText("");
			}
		});

		tradeGoodsView.text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tradeGoodsView.text.setText("");
			}
		});
		tradeGoodsView.btnBuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				try {
					if (Player.getCurrency() < (Integer
							.parseInt(tradeGoodsView.text.getText()) * Double
							.parseDouble(tradeGoodsView.table_1.getSelection()[0]
									.getText(1)))) {
						driver.notificationsView.list_1
								.add("Sorry, you do not have enough currency to make this purchase");
						driver.notificationsView.list_1
								.select(driver.notificationsView.list_1
										.getItemCount() - 1);
						driver.notificationsView.list_1.showSelection();
					} else if ((Integer.parseInt(tradeGoodsView.text.getText()) > Integer
							.parseInt(tradeGoodsView.table_1.getSelection()[0]
									.getText(3)))) {
						driver.notificationsView.list_1
								.add("Sorry, there is not enough of this item in the market.");
						driver.notificationsView.list_1
								.select(driver.notificationsView.list_1
										.getItemCount() - 1);
						driver.notificationsView.list_1.showSelection();
					} else if (driver.player.getShip().capacity < (Integer
							.parseInt(tradeGoodsView.text.getText()))
							|| driver.player.cargoSize() >= Integer
									.parseInt(tradeGoodsView.text.getText())) {
						driver.notificationsView.list_1
								.add("Sorry there is not enough space in your cargo");
					} else {
						driver.player.setCurrency(Player.getCurrency()
								- (Integer.parseInt(tradeGoodsView.text
										.getText()) * Double
										.parseDouble(tradeGoodsView.table_1
												.getSelection()[0].getText(1))));
						driver.player.insertCargo(tradeGoodsView.table_1
								.getSelection()[0].getText(0), Integer
								.parseInt(tradeGoodsView.text.getText()));
						tradeGoodsView.table_1.getSelection()[0]
								.setText(2, Integer.toString(driver.player
										.getCargo().get(
												tradeGoodsView.table_1
														.getSelection()[0]
														.getText(0))));
						int previousAmt = Integer
								.parseInt(tradeGoodsView.table_1.getSelection()[0]
										.getText(3));
						tradeGoodsView.table_1.getSelection()[0].setText(
								3,
								Integer.toString(previousAmt
										- Integer.parseInt(tradeGoodsView.text
												.getText())));
						driver.getByCoordinate(driver.getCurrentLocation())
								.getMarket()
								.put((tradeGoodsView.table_1.getSelection()[0]
										.getText(0)),
										driver.getByCoordinate(
												driver.getCurrentLocation())
												.getMarket()
												.get((tradeGoodsView.table_1
														.getSelection()[0]
														.getText(0))));
						driver.getByCoordinate(driver.getCurrentLocation())
								.setMarketItem(
										tradeGoodsView.table_1.getSelection()[0]
												.getText(0),
										new Good(
												Double.parseDouble(tradeGoodsView.table_1
														.getSelection()[0]
														.getText(1)) * 1.5,
														previousAmt
														- Integer
																.parseInt(tradeGoodsView.text
																		.getText())));
						tradeGoodsView.table_1
								.getSelection()[0].setText(1, Double.toString(driver
								.getByCoordinate(driver.getCurrentLocation())
								.getMarket().get(tradeGoodsView.table_1
										.getSelection()[0].getText(0)).getPrice()));
					}
				} catch (NumberFormatException f) {
					driver.notificationsView.list_1
							.add("Sorry, incorrect input detected.");
					driver.notificationsView.list_1.showSelection();
				} catch (ArrayIndexOutOfBoundsException a) {
					driver.notificationsView.list_1
							.add("Sorry, please select something from the market.");
					driver.notificationsView.list_1.showSelection();
				}
			}
		});

		tradeGoodsView.btnSell.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				try {

				} catch (NumberFormatException n) {

				}
			}
		});

	}

	public void setTable(HashMap<String, Good> market) {

		String[] water = { "Water",
				Double.toString(market.get("Water").getPrice()),
				Integer.toString(driver.player.getCargo().get("Water")),
				Integer.toString(market.get("Water").getQuantity()) };
		tradeGoodsView.waterItem.setText(water);

		String[] furs = { "Furs",
				Double.toString(market.get("Furs").getPrice()),
				Integer.toString(driver.player.getCargo().get("Furs")),
				Integer.toString(market.get("Furs").getQuantity()) };
		tradeGoodsView.furItem.setText(furs);

		String[] food = { "Food",
				Double.toString(market.get("Food").getPrice()),
				Integer.toString(driver.player.getCargo().get("Food")),
				Integer.toString(market.get("Food").getQuantity()) };
		tradeGoodsView.foodItem.setText(food);

		String[] ore = { "Ore", Double.toString(market.get("Ore").getPrice()),
				Integer.toString(driver.player.getCargo().get("Ore")),
				Integer.toString(market.get("Ore").getQuantity()) };
		tradeGoodsView.oreItem.setText(ore);

		String[] firearms = { "Firearms",
				Double.toString(market.get("Firearms").getPrice()),
				Integer.toString(driver.player.getCargo().get("Firearms")),
				Integer.toString(market.get("Firearms").getQuantity()) };
		tradeGoodsView.firearmsItem.setText(firearms);

		String[] medicine = { "Medicine",
				Double.toString(market.get("Medicine").getPrice()),
				Integer.toString(driver.player.getCargo().get("Medicine")),
				Integer.toString(market.get("Medicine").getQuantity()) };
		tradeGoodsView.medicineItem.setText(medicine);

		String[] machines = { "Machines",
				Double.toString(market.get("Machines").getPrice()),
				Integer.toString(driver.player.getCargo().get("Machines")),
				Integer.toString(market.get("Machines").getQuantity()) };
		tradeGoodsView.machinesItem.setText(machines);

		String[] narcotics = { "Narcotics",
				Double.toString(market.get("Narcotics").getPrice()),
				Integer.toString(driver.player.getCargo().get("Narcotics")),
				Integer.toString(market.get("Narcotics").getQuantity()) };
		tradeGoodsView.narcoticsItem.setText(narcotics);

		String[] robots = { "Robots",
				Double.toString(market.get("Robots").getPrice()),
				Integer.toString(driver.player.getCargo().get("Robots")),
				Integer.toString(market.get("Robots").getQuantity()) };
		tradeGoodsView.robotsItem.setText(robots);
		
		String[] fuel = { "Fuel",
				Double.toString(market.get("Fuel").getPrice()),
				Integer.toString(driver.player.getFuel()),
				Integer.toString(market.get("Fuel").getQuantity()) };
		tradeGoodsView.fuelItem.setText(fuel);
		}

	public void updateMarketView(SolarSystem selected) {
		// TODO Auto-generated method stub
		market = selected.getMarket();
		setTable(market);
	}
}
