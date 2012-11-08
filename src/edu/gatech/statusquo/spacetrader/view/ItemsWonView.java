package edu.gatech.statusquo.spacetrader.view;

import java.util.HashMap;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class ItemsWonView {

	public Shell shell;
	public Table table;
	public Display display;
	public Text text;
	public Text text_1;
	public Button btnNewButton;
	public Button btnPutBack;
	public TableItem[] tableItems;
	HashMap<String, Integer> wonItems;
	
	public ItemsWonView(HashMap<String, Integer> wI)
	{
		wonItems = wI;
		createContents();
		shell.open();
		shell.layout();
		display = Display.getDefault();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("You Win!");
		
		Label lblCongratulationsChooseYour = new Label(shell, SWT.NONE);
		lblCongratulationsChooseYour.setBounds(104, 10, 255, 17);
		lblCongratulationsChooseYour.setText("Congratulations! Choose Your Rewards!");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 33, 428, 181);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(134);
		tblclmnNewColumn.setText("Loot");
		
		TableColumn tblclmnQuantity = new TableColumn(table, SWT.NONE);
		tblclmnQuantity.setWidth(141);
		tblclmnQuantity.setText("Quantity");
		
		TableColumn tblclmnPersonalAmount = new TableColumn(table, SWT.NONE);
		tblclmnPersonalAmount.setWidth(100);
		tblclmnPersonalAmount.setText("Personal Amount");
		
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(104, 218, 103, 35);
		btnNewButton.setText("Take Amount");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 220, 85, 33);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(257, 220, 85, 33);
		
		btnPutBack = new Button(shell, SWT.NONE);
		
		btnPutBack.setBounds(344, 218, 94, 35);
		btnPutBack.setText("Put Back");
		
		tableItems = new TableItem[wonItems.size()];
		for (int i = 0; i < tableItems.length; i++)
		{
		    tableItems[i] = new TableItem(table, SWT.NONE);
		}
		table.setSelection(0);
	}
}
