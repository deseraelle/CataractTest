//**************************************************************************************************
//FILE:        CataractPanel.java
//DESCRIPTION: CataractPanel generates a pair of images of the retina, one of which is affected
//			   by a cataract, and one of which is clear of disease, and asks the user to click on
//			   that contains the disease. The images are selected at random from a database, and 
//			   are randomly positioned when generated. Once the user has finished all of the 
//			   questions, their score is displayed along with the time it took them to complete the test.
//AUTHOR:      David Hjelmstad (dhjelmst@asu.edu)
//**************************************************************************************************


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

public class CataractPanel extends JPanel{
	
	//SETS NUMBER OF IMAGES IN DATABASE:
	private static int SAMPLE_SIZE=10;
	
	//SETS THE NUMBER OF QUESTIONS (must be greater than 0 and less than SAMPLE_SIZE squared)
	private static int NUMBER_OF_QUESTIONS=10;
	
	//GUI PARAMETERS
	private static int WINDOW_WIDTH = 900;
	private static int WINDOW_HEIGHT = 400;
	private static int INSETS=20;
	private static int VERT_GAP=20;
	private static int HORIZ_GAP=0;
	
	// instantiates title labels, start button, and the two image buttons
	private JLabel begintitle,title,endtitle;
	private JButton start,image1,image2;
	
	// defines the image pathname variables. The images are stored in the resources/ folder 
	// in separate transparent/ and cataract/ folders. All images are of size 400x267 pixels
	private String cataract1="BuceyOD3.JPG";
	private String cataract2="DAVIS_OS.JPG";
	private String cataract3="FONNER_OS.JPG";
	private String cataract4="JONES OD.JPG";
	private String cataract5="MAGAHA_OS.JPG";
	private String cataract6="nekola_OD2.JPG";
	private String cataract7="ODEH_OD.JPG";
	private String cataract8="riggs_OD.JPG";
	private String cataract9="RIVERA OD.JPG";
	private String cataract10="ROLLA_OD.JPG";
	private String transparent1="BICHAY OD.JPG";
	private String transparent2="DAVIS_OD.JPG";
	private String transparent3="FONNER_OD.JPG";
	private String transparent4="HJELMSTAD OS.JPG";
	private String transparent5="JUSTUS_OS.JPG";
	private String transparent6="Mapson_OD1.JPG";
	private String transparent7="PHILLIPS OD.JPG";
	private String transparent8="STEFFEY OS.JPG";
	private String transparent9="TAIBL OS.JPG";
	private String transparent10="WESTER OD.JPG";
	private int answer, numberCorrect;
	
	// The enumArray is a numbered list of all possible combinations of images, represented as numerals (converted to image URLs once queue is set)
	private int[][] enumArray;
	
	// The randomized order of pairings, which initializes the queue of questions
	private int[] random;
	
	private String[] cataractArray, transparentArray;
	private int index;
	private double startTime;
	
	
	
	public CataractPanel() {
		
		// Sets up the window dimensions, and displays the initial title
		
		setPreferredSize(new Dimension (WINDOW_WIDTH,WINDOW_HEIGHT));
		this.setLayout(null); //Layout must be set to 'null' so that elements may be assigned a location explicitly
		begintitle= new JLabel("Click \"Start\" to begin", JLabel.CENTER); //sets initial title's text and centers title
		begintitle.setFont(new Font("Serif", Font.BOLD,30)); //styles title
		begintitle.setBounds(0,3*INSETS,WINDOW_WIDTH,50); //sizes title
		add(begintitle);
		
		// creates the 'start' button and assigns the action to take when it is clicked.
		start = new JButton(new AbstractAction("Start") {
			public void actionPerformed(ActionEvent e){
				// remove title and start button and refresh window:
				remove(begintitle);
				remove(start);
				revalidate();
				repaint();
				
				// sets up next graphical setup with instructional message
				setLayout(new BorderLayout(HORIZ_GAP, VERT_GAP)); //Sets layout to BorderLayout to facilitate the placement of elements
				title = new JLabel("Choose the image of the patient with a cataract:", JLabel.CENTER); //assigns instructional text and centers
				title.setFont(new Font("Serif",Font.PLAIN, 20)); //styles text
				add(title, BorderLayout.PAGE_START); //places text at top of window
				
				// sets up private array variables by calling the corresponding class
				enumArray = enumerationArray();
				random = randomArray();
				cataractArray = cataractArray();
				transparentArray = transparentArray();
				numberCorrect = 0;
				index=0;
				startTime=new Date().getTime();
				run(index);
			}
			
		});
		
		// The start button is created, sized, and added in the center of the window
		start.setSize(new Dimension(80, 40));
		start.setLocation(WINDOW_WIDTH/2-start.getWidth()/2, WINDOW_HEIGHT/2-start.getHeight()/2);
		add(start);
		
	}
	
	
	// The run() method takes in the index of the pair to add, adds them, and refreshes the window
	
	public void run(int i) {
		int ind = random[i];
		addImages(cataractArray[enumArray[ind][1]],transparentArray[enumArray[ind][2]]);
		revalidate();
		repaint();
	}
	
	
	// The addImages() method takes in two pathnames, adds the images they point to to the window randomly while keeping
	// track of which is the correct answer, and sets up the ButtonListener for each button
	
	public void addImages(String pathname1, String pathname2) {
		
		//instantiate ImageIcon variables
		ImageIcon icon1 = new ImageIcon();
		ImageIcon icon2 = new ImageIcon();

		//randomly orders the two images
		if (Math.random()>.5){
			icon1 = new ImageIcon(getClass().getResource(pathname1));
			icon2 = new ImageIcon(getClass().getResource(pathname2));
			answer = 1;
		} else {
			answer = 2;
			icon1 = new ImageIcon(getClass().getResource(pathname2));
			icon2 = new ImageIcon(getClass().getResource(pathname1));
			
		}
		image1 = new JButton(icon1);
		image2 = new JButton(icon2);
		image1.setActionCommand("1"); //when this button is pressed, the value of 1 will be returned
		image2.setActionCommand("2"); // same as ^^ except 2 is returned
		image1.addActionListener(new ButtonListener());
		image2.addActionListener(new ButtonListener());
		
		add(image1, BorderLayout.LINE_START);
		add(image2, BorderLayout.LINE_END);
		
	}
	
	
	// the enumerationArray() method creates a complete list of all possible iterations of images for a sample size of
	// size SAMPLE_SIZE. Images are represented by a numeral
	
	public int[][] enumerationArray(){
		int[][] enumerationArray = new int[SAMPLE_SIZE*SAMPLE_SIZE][3];
		int index=0;
		for(int j=0; j<=SAMPLE_SIZE-1; j++){
			for(int h=0; h<=SAMPLE_SIZE-1; h++){
				enumerationArray[index][1]=j;
				enumerationArray[index][2]=h;
				index++;
			}
		}
		
		return enumerationArray;
	}
	
	
	 // stores the image pathnames of the cataract images in an array
	
	public String[] cataractArray(){
		String[] cat = new String[] {cataract1,cataract2,cataract3,cataract4,cataract5,cataract6,cataract7,cataract8,cataract9,cataract10};
		return cat;
	}
	
	
	// stores the image pathnames of the transparent images in an array
	
	public String[] transparentArray(){
		String[] trans = new String[] {transparent1,transparent2,transparent3,transparent4,transparent5,transparent6,transparent7,transparent8,transparent9,transparent10};
		return trans;
	}
	
	
	// sets up the queue for the presentation of image pairs. An array of length NUMBER_OF_QUESTIONS is created with random
	// integers from 0 to NUMBER_OF_QUESTIONS-1. Each integer corresponds to a specific pair of images
	
	public int[] randomArray(){
		//integers added to a List
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i=0; i<SAMPLE_SIZE*SAMPLE_SIZE; i++){
			numbers.add(i);
		}
		java.util.Collections.shuffle(numbers); //The list is shuffled/randomized
		Object[] rand = numbers.toArray();
		
		//The elements of the randomized list are added to the array random of length NUMBER_OF_QUESTIONS
		int[] random = new int[NUMBER_OF_QUESTIONS];
		for(int i=0; i<NUMBER_OF_QUESTIONS; i++){
			random[i]=(Integer) rand[i];
		}
		return random;
	}
	
	
	// Assigns the action to take when a button is pushed
	
	private class ButtonListener implements ActionListener{
			
			public void actionPerformed(ActionEvent event) {
				int response = Integer.parseInt(event.getActionCommand()); //gives the value of the image (either 1 or 2) that was clicked
				
				// if the selection was correct, numberCorrect is augmented by 1. If incorrect, numberCorrect is not changed
				// In either case, index is augmented and the images are removed and the window is refreshed.
				if (response==answer){
					numberCorrect++;
				}
				index++;
				remove(image1);
				remove(image2);
				revalidate();
				
				//if the index is less than the number of questions another pair is generated
				if(index<NUMBER_OF_QUESTIONS){	
					run(index);
				} 
				// if not, the program is terminated: images are removed, and a message is displayed that gives
				// the number of correct responses and the amount of time it took.
				else {
					remove(title);
					double endTime = new Date().getTime();
					setLayout(new FlowLayout());
					DecimalFormat dec = new DecimalFormat("0.0");
					endtitle = new JLabel("You correctly identified "+dec.format(numberCorrect*100/NUMBER_OF_QUESTIONS)+"% ("+numberCorrect+" out of "+NUMBER_OF_QUESTIONS+") of the cases.\n" +
							"It took "+dec.format((endTime-startTime)/1000)+" seconds to complete "+index+" cases.", JLabel.CENTER);
					add(endtitle, BorderLayout.CENTER);
					revalidate();
					repaint();
				}
			}
	}	
	
	//sets the insets of the window to INSETS, which is a private variable defined above
	public Insets getInsets()
	   {
	      return new Insets(INSETS,INSETS,INSETS,INSETS);
	   }
	

}	