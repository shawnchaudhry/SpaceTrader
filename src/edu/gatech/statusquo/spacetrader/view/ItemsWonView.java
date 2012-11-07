package edu.gatech.statusquo.spacetrader.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ItemsWonView {

	public Shell shell;
	public Table table;
	public Display display;
	private Text text;
	private Text text_1;

	public ItemsWonView()
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
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
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(104, 218, 103, 35);
		btnNewButton.setText("Take Amount");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 220, 85, 33);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(257, 220, 85, 33);
		
		Button btnPutBack = new Button(shell, SWT.NONE);
		btnPutBack.setBounds(344, 218, 94, 35);
		btnPutBack.setText("Put Back");
		
		TableItem[] tableItems;

	}
}
