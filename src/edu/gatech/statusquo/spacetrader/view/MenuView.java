package edu.gatech.statusquo.spacetrader.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class MenuView {
	Shell shell;
	public Menu menu;
	public MenuItem mntmFile;
	public Menu menu_1;
	public MenuItem mntmNewGame;
	public MenuItem mntmSaveGame;
	public MenuItem mntmLoadGame;
	
	public MenuView(Shell shell)
	{
		this.shell = shell;
		createMenuView();
	}

	private void createMenuView() {
		menu = new Menu(shell, SWT.BAR);
		
		shell.setMenuBar(menu);
		
		mntmFile = new MenuItem(menu, SWT.CASCADE);
		
		menu_1 = new Menu(mntmFile);
		
		mntmNewGame = new MenuItem(menu_1, SWT.NONE);
		mntmSaveGame = new MenuItem(menu_1, SWT.NONE);
		mntmLoadGame = new MenuItem(menu_1, SWT.NONE);
	}
}
