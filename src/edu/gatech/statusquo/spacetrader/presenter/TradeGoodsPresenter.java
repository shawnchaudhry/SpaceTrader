package edu.gatech.statusquo.spacetrader.presenter;

import java.text.DecimalFormat;
import java.util.HashMap;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

import edu.gatech.statusquo.spacetrader.driver.*;
import edu.gatech.statusquo.spacetrader.model.Good;
import edu.gatech.statusquo.spacetrader.model.SolarSystem;
import edu.gatech.statusquo.spacetrader.view.*;

public class TradeGoodsPresenter {
	Shell shell;
	Driver driver;
	TradeGoodsView tradeGoodsView;
	HashMap<String, Good> market;
	DecimalFormat df;

	public TradeGoodsPresenter(Shell s, Driver d, TradeGoodsView tgv) {
		this.shell = s;
		this.driver = d;
		this.tradeGoodsView = tgv;
		df = new DecimalFormat("#.##");
		setListeners();
		market = driver.retrieveSelectedSolarSystem().getMarket();
		setTable(market);
	}
	
	/**
	 * Sets the listeners.
	 */
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
					if (tradeGoodsView.table_1.getSelection()[0].getText(0)
							.equals("Fuel")) {
						if (driver.player.getCurrency() < (Integer
								.parseInt(tradeGoodsView.text.getText()) * Double
								.parseDouble(tradeGoodsView.table_1
										.getSelection()[0].getText(1)))) {
							driver.notificationsPresenter
									.addToList("Sorry, you do not have enough currency to make this purchase");
						} else if ((Integer.parseInt(tradeGoodsView.text
								.getText()) > Integer
								.parseInt(tradeGoodsView.table_1.getSelection()[0]
										.getText(3)))) {
							driver.notificationsPresenter
									.addToList("Sorry, there is not enough of this item in the market.");
						} else {
							driver.player.setCurrency(driver.player.getCurrency()
									- (Integer.parseInt(tradeGoodsView.text
											.getText()) * Double
											.parseDouble(tradeGoodsView.table_1
													.getSelection()[0]
													.getText(1))));
							double currentFuel = driver.player.getFuel();
							int previousAmt = driver
									.getByCoordinate(
											driver.getCurrentLocation())
									.getMarket().get("Fuel").getQuantity();
							driver.player.setFuel(currentFuel
									+ Double.parseDouble(tradeGoodsView.text
											.getText()));
							driver.getByCoordinate(driver.getCurrentLocation())
									.setMarketItem(
											"Fuel",
											new Good(
													Double.parseDouble(tradeGoodsView.table_1
															.getSelection()[0]
															.getText(1)),
													previousAmt
															- Integer
																	.parseInt(tradeGoodsView.text
																			.getText())));
							setTable(driver.getByCoordinate(
									driver.getCurrentLocation()).getMarket());
							driver.vitalsPresenter.setTable();
						}
					} else {
						int prevAmount = driver.player.getCargo().get(
								tradeGoodsView.table_1.getSelection()[0]
										.getText(0));
						driver.player.getCargo().put(
								tradeGoodsView.table_1.getSelection()[0]
										.getText(0),
								prevAmount
										+ Integer.parseInt(tradeGoodsView.text
												.getText()));
						if (driver.player.getCurrency() < (Integer
								.parseInt(tradeGoodsView.text.getText()) * Double
								.parseDouble(tradeGoodsView.table_1
										.getSelection()[0].getText(1)))) {
							driver.notificationsPresenter
									.addToList("Sorry, you do not have enough currency to make this purchase");
						} else if ((Integer.parseInt(tradeGoodsView.text
								.getText()) > Integer
								.parseInt(tradeGoodsView.table_1.getSelection()[0]
										.getText(3)))) {
							driver.notificationsPresenter
									.addToList("Sorry, there is not enough of this item in the market.");
						} else if (driver.player.cargoSize() > driver.player
								.getShip().capacity) {
							driver.player.getCargo().put(
									tradeGoodsView.table_1.getSelection()[0]
											.getText(0), prevAmount);
							driver.notificationsPresenter
									.addToList("Sorry there is not enough space in your cargo");
						} else {
							/*
							 * Sets the player's currency after purchase.
							 */
							driver.player.setCurrency(driver.player.getCurrency()
									- (Integer.parseInt(tradeGoodsView.text
											.getText()) * Double
											.parseDouble(tradeGoodsView.table_1
													.getSelection()[0]
													.getText(1))));
							/*
							 * Updates the view.
							 */
							int previousAmt = driver
									.getByCoordinate(
											driver.getCurrentLocation())
									.getMarket()
									.get(tradeGoodsView.table_1.getSelection()[0]
											.getText(0)).getQuantity();
							driver.getByCoordinate(driver.getCurrentLocation())
									.setMarketItem(
											tradeGoodsView.table_1
													.getSelection()[0]
													.getText(0),
											new Good(
													Double.parseDouble(tradeGoodsView.table_1
															.getSelection()[0]
															.getText(1)),
													previousAmt
															- Integer
																	.parseInt(tradeGoodsView.text
																			.getText())));
							setTable(driver.getByCoordinate(
									driver.getCurrentLocation()).getMarket());
							driver.vitalsPresenter.setTable();
						}
					}
				} catch (NumberFormatException f) {
					driver.notificationsPresenter
							.addToList("Sorry, incorrect input detected");
				} catch (ArrayIndexOutOfBoundsException a) {
					driver.notificationsPresenter
							.addToList("Sorry, please select something from the market.");
				}
			}
		});

		tradeGoodsView.btnSell.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				try {
					if ((Integer.parseInt(tradeGoodsView.text_1.getText()) > driver.player
							.getCargo().get(
									tradeGoodsView.table_1.getSelection()[0]
											.getText(0)))
							|| ((Integer.parseInt(tradeGoodsView.text_1
									.getText()) > driver.player.getFuel()) && tradeGoodsView.table_1
									.getSelection()[0].getText(0)
									.equals("Fuel"))) {
						driver.notificationsPresenter
								.addToList("Sorry you do not have enough of this item to sell");
						setTable(driver.getByCoordinate(
								driver.getCurrentLocation()).getMarket());
					} else {
						if (tradeGoodsView.table_1.getSelection()[0].getText(0)
								.equals("Fuel")) {
							double previousAmt = driver.player.getFuel();
							driver.player.setFuel(previousAmt
									- Integer.parseInt(tradeGoodsView.text_1
											.getText()));
							driver.getByCoordinate(driver.getCurrentLocation())
									.getMarket()
									.put("Fuel",
											new Good(
													Double.parseDouble(tradeGoodsView.table_1
															.getSelection()[0]
															.getText(1)),
													Integer.parseInt(tradeGoodsView.table_1
															.getSelection()[0]
															.getText(3))
															+ Integer
																	.parseInt(tradeGoodsView.text_1
																			.getText())));
							double previousAmtOfCurrency = driver.player.getCurrency();
							driver.player.setCurrency(previousAmtOfCurrency
									+ (Integer.parseInt(tradeGoodsView.text_1
											.getText()) * Integer
											.parseInt(tradeGoodsView.table_1
													.getSelection()[0]
													.getText(3))));
							driver.vitalsPresenter.setTable();
						} else {
							int amountToSell = Integer
									.parseInt(tradeGoodsView.text_1.getText());
							double price = Double
									.parseDouble(tradeGoodsView.table_1
											.getSelection()[0].getText(1));
							double currentCurrency = driver.player.getCurrency();
							int previousAmt = driver
									.getByCoordinate(
											driver.getCurrentLocation())
									.getMarket()
									.get(tradeGoodsView.table_1.getSelection()[0]
											.getText(0)).getQuantity();
							driver.player.setCurrency(currentCurrency
									+ (amountToSell * price));
							driver.getByCoordinate(driver.getCurrentLocation())
									.setMarketItem(
											tradeGoodsView.table_1
													.getSelection()[0]
													.getText(0),
											new Good(
													Double.parseDouble(tradeGoodsView.table_1
															.getSelection()[0]
															.getText(1)),
													previousAmt
															+ Integer
																	.parseInt(tradeGoodsView.text_1
																			.getText())));
							int amountOfItem = driver.player.getCargo().get(
									tradeGoodsView.table_1.getSelection()[0]
											.getText(0));
							driver.player
									.getCargo()
									.put(tradeGoodsView.table_1.getSelection()[0]
											.getText(0),
											amountOfItem
													- Integer
															.parseInt(tradeGoodsView.text_1
																	.getText()));
							driver.vitalsPresenter.setTable();
							setTable(driver.getByCoordinate(
									driver.getCurrentLocation()).getMarket());
						}
					}
				} catch (NumberFormatException n) {
					driver.notificationsPresenter
							.addToList("Sorry, incorrect input detected");
				} catch (ArrayIndexOutOfBoundsException a) {
					driver.notificationsPresenter
							.addToList("Sorry, please select something from the market.");
				}
			}
		});

	}
	
	/**
	 * Sets the table.
	 * @param market HashMap that contains the market.
	 */
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
				df.format(driver.player.getFuel()),
				Integer.toString(market.get("Fuel").getQuantity()) };
		tradeGoodsView.fuelItem.setText(fuel);
	}

	/**
	 * Updates the market with the selected solarSystem.
	 * @param selected the selected solar system.
	 */
	public void updateMarketView(SolarSystem selected) {
		market = selected.getMarket();
		setTable(market);
	}
}
