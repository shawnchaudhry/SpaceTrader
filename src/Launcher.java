import java.io.IOException;

import edu.gatech.statusquo.spacetrader.driver.Driver;


public class Launcher {
	/**
	 * Main, launches the application.
	 * @param args
	 */
	 public static void main(String[] args) 
	 {
			try 
			{
			   new Driver();
			} 
			
			catch (IOException e) 
			{
			    e.printStackTrace();
			}
	 }
}
