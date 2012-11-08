package edu.gatech.statusquo.spacetrader.driver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import edu.gatech.statusquo.spacetrader.presenter.ItemsWonPresenter;
import edu.gatech.statusquo.spacetrader.view.ItemsWonView;

public class RandomEvent {
	Driver driver;
	Random rand;
	Iterator<String> it;
	HashMap<String, Integer> wonItems;

	public RandomEvent(Driver d) {
		driver = d;
		rand = new Random();
		it = driver.player.getCargo().keySet().iterator();
		int n = rand.nextInt(2);
		if (n == 1)
		{
			piratesEncounter();
		}
		else
		{
			policeEncounter();
		}
	}

	/**
	 * This control's a pirate encounter
	 * @return a hashmap that contains the cargo.
	 */
	public HashMap<String, Integer> piratesEncounter() {
		wonItems = new HashMap<String, Integer>();
		int n = rand.nextInt(15);
		if (driver.player.getFighterSkills() > n) {
			driver.notificationsPresenter
					.addToList("After a tough battle with some pirates, you win some loot!");
			while (it.hasNext()) {
				String item = it.next();
				boolean add = rand.nextBoolean();
				if (add) {
					int amount = rand.nextInt(15);
					wonItems.put(item, amount);
				}
			}
			if (wonItems.size() > 0){
				ItemsWonView itemsWonView = new ItemsWonView(wonItems);
				new ItemsWonPresenter(driver, itemsWonView, wonItems);
			}
			return driver.player.getCargo();
		} else if (driver.player.getFighterSkills() == n) {
			driver.notificationsPresenter
					.addToList("After a tough battle with some pirates, you managed to make it out unharmed!");
			return driver.player.getCargo();
		} else {
			driver.notificationsPresenter
					.addToList("After a tough battle with some pirates, you barely make it out alive, and the pirates have managed to take off with some of your cargo!");
			while (it.hasNext()) {
				String item = it.next();
				boolean remove = rand.nextBoolean();
				if (remove) {
					int amount = rand.nextInt(15);
					if (driver.player.getCargo().get(item) <= amount) {
						driver.player.getCargo().put(item, 0);
					} else {
						driver.player.getCargo().put(item,
								driver.player.getCargo().get(item) - amount);
					}
				}
			}
			return driver.player.getCargo();
		}
	}
	
	/**
	 * This is a police encounter.
	 * @return Hashmap that contains the cargo.
	 */
	public HashMap<String, Integer> policeEncounter() {
		int n = rand.nextInt(15);
		if (driver.player.getPilotSkills() > n) {
			driver.notificationsPresenter
					.addToList("Nice job! You outran the police!");
			return driver.player.getCargo();
		} else if (driver.player.getPilotSkills() == n) {
			driver.notificationsPresenter
					.addToList("The police checked your cargo, but luckily you didnt have to pay anything!");
			return driver.player.getCargo();
		} else {
			driver.notificationsPresenter
					.addToList("The police are on your case! They have taken some of your stuff!");
			while (it.hasNext()) {
				String item = it.next();
				boolean remove = rand.nextBoolean();
				if (remove) {
					int amount = rand.nextInt(101);
					if (driver.player.getCargo().get(item) <= amount) {
						driver.player.getCargo().put(item, 0);
					} else {
						driver.player.getCargo().put(item,
								driver.player.getCargo().get(item) - amount);
					}
				}
			}
			return driver.player.getCargo();
		}
	}
}
