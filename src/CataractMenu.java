import java.awt.*;
import java.awt.List;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.lang.Object;

public class CataractMenu extends JPanel
 {
 	
 	private JButton Training, Test;
 	private JLabel header;
 	
 	private static int WINDOW_WIDTH = 900;
	private static int WINDOW_HEIGHT = 400;
 	
 	public CataractMenu()
 	 {
 	 	setPreferredSize(new Dimension (WINDOW_WIDTH,WINDOW_HEIGHT));
 	 	setLayout(new BorderLayout());
 	 	header = new JLabel("Choose evaluation mode:",JLabel.CENTER);
 	 	header.setFont(new Font("Serif", Font.BOLD,30));
		add(header, BorderLayout.NORTH);
 	 	
 	 	
 	 	Training = new JButton(new AbstractAction("Training") {
 	 		public void actionPerformed (ActionEvent e)
 	 		 {
 	 		 	remove(header);
 	 		 	remove(Training);
 	 		 	remove(Test);

 	 		 	JFrame frame1 = new JFrame("Cataract Test");
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				CataractTrainingPanel panel = new CataractTrainingPanel();
				frame1.getContentPane().add(panel);
		
				frame1.pack();
				frame1.setVisible(true);
 	 		 }
 	 	});
 	 	
 	 	Test = new JButton(new AbstractAction("Test") {
 	 		public void actionPerformed (ActionEvent e)
 	 		 {
 	 		 	remove(header);
 	 		 	remove(Training);
 	 		 	remove(Test);
 	 		 	
 	 		 	JFrame frame2 = new JFrame("Cataract Test");
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				CataractPanel panel = new CataractPanel();
				frame2.getContentPane().add(panel);
		
				frame2.pack();
				frame2.setVisible(true);
 	 		 }
 	 	});
 	 	
 	 	Training.setSize(new Dimension(160, 80));
 	 	Test.setSize(new Dimension(160, 80));
 	 	
 	 	JPanel buttonpanel = new JPanel();
 	 	buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.Y_AXIS));
 	 	Training.setAlignmentX(Component.CENTER_ALIGNMENT);
 	 	Test.setAlignmentX(Component.CENTER_ALIGNMENT);
 	 	buttonpanel.add(Box.createVerticalGlue());
 	 	buttonpanel.add(Training);
 	 	buttonpanel.add(Test);
 	 	buttonpanel.add(Box.createVerticalGlue());
 	 	add(buttonpanel, BorderLayout.CENTER);
 	 	
 	 	
 	 		 
 	 	
 	 		 
 	 		 	
 	 }

}