package edu.gatech.statusquo.spacetrader.presenter;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import edu.gatech.statusquo.spacetrader.driver.Driver;
import edu.gatech.statusquo.spacetrader.view.ItemsWonView;

public class ItemsWonPresenter {
	HashMap<String, Integer> wonItems = new HashMap<String, Integer>();
	Driver driver;
	ItemsWonView itemsWonView;

	public ItemsWonPresenter(Driver d, ItemsWonView itemWV, HashMap<String, Integer> wI)
	{
		wonItems = wI;
		driver = d;
		itemsWonView = itemWV;
		setTable();
		setListeners();
		while (!itemsWonView.shell.isDisposed()) 
		{
			if (!itemsWonView.display.readAndDispatch()) 
			{
				itemsWonView.display.sleep();
			}
		}
	}
	
	/**
	 * Sets the table.
	 */
	public void setTable()
	{
		Iterator<String> it = wonItems.keySet().iterator();
		int i = 0;
		while (it.hasNext()){
			String item = it.next();
			String[] tableItemString = {item, Integer.toString(wonItems.get(item)), Integer.toString(driver.player.getCargo().get(item))};
			itemsWonView.tableItems[i].setText(tableItemString);
			i++;
		}
	}
	
	/**
	 * Sets the listeners.
	 */
	private void setListeners() {
		itemsWonView.btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String itemName = itemsWonView.table.getSelection()[0].getText(0);
				int lootQty = Integer.parseInt(itemsWonView.table.getSelection()[0].getText(1));
				int personalQty = Integer.parseInt(itemsWonView.table.getSelection()[0].getText(2));
				int takeAmt = Integer.parseInt(itemsWonView.text.getText());
				if(takeAmt > lootQty)
				{
					JOptionPane.showMessageDialog(null, "Sorry, there is not enough of this item to take!");
				}
				else
				{
					driver.player.insertCargo(itemName, takeAmt);
					if (driver.player.cargoSize() > driver.player.getShip().capacity)
					{
						driver.player.insertCargo(itemName, personalQty);
						JOptionPane.showMessageDialog(null, "Sorry, there is not enough room in your cargo!");
					}
				}
			}
		});
		
		itemsWonView.btnPutBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String itemName = itemsWonView.table.getSelection()[0].getText(0);
				int lootQty = Integer.parseInt(itemsWonView.table.getSelection()[0].getText(1));
				int personalQty = Integer.parseInt(itemsWonView.table.getSelection()[0].getText(2));
				int putAmt = Integer.parseInt(itemsWonView.text_1.getText());
				if(putAmt > lootQty)
				{
					JOptionPane.showMessageDialog(null, "Sorry there wasn't enough of this item to take");
				}
			}
		});
	}
}
