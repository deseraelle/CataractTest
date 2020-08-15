//**************************************************************************************************
//FILE:        CataractTest.java
//DESCRIPTION: Main class. Sets up the GUI and starts the main program         
//AUTHOR:      David Hjelmstad (dhjelmst@asu.edu)
//**************************************************************************************************

import java.io.IOException;
import javax.swing.JFrame;


public class CataractTest {

	public static void main (String[] args) throws IOException{
		JFrame frame = new JFrame("Cataract Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CataractMenu menu = new CataractMenu();
		frame.getContentPane().add(menu);
		
		frame.pack();
		frame.setVisible(true);
	}	
}
