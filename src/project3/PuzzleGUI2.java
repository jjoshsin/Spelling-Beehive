// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * The GUI program that the mock spelling beehive game is ran on
 * 
 * 
 */
package project3;
import java.awt.*;
import javax.swing.*;

public class PuzzleGUI2 extends JFrame{
	public static JTextArea mytextarea2 = new JTextArea(); // the text area of the GUI that will be appended to
	/**
	 * The constructor for PuzzleGUI2
	 */
	public PuzzleGUI2() {
		setTitle("PuzzleGUI");
		setSize(400,500);
	    setLocation(900,100);
	    createFileMenu();
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
	}
	/**
	 * Adds the word given into one of the text areas while leaving the
	 * other to be filled and adds into the GUI.
	 * @param f the JFrame object that will now hold these text areas
	 * @param word the letters in the text file being added to the GUI
	 */
	public static void PuzzleWord(JFrame f, String word) {
		f.setLayout(new GridLayout(1,2));
		JPanel mycontainer = new JPanel();
		JTextArea mytextarea = new JTextArea(word);
		mytextarea.setEditable(false);
		mytextarea2.setEditable(false);
		mycontainer.add(mytextarea);
		mycontainer.add(mytextarea2);
		f.add(mytextarea);
		f.setVisible(true);
	}
	/**
	 * Adds a file menu to the GUI
	 */
	public void createFileMenu( ) {
	      JMenuItem   item;
	      JMenuBar    menuBar  = new JMenuBar();
	      JMenu       fileMenu = new JMenu("File");
	      FileMenuHandler fmh  = new FileMenuHandler(this);

	      item = new JMenuItem("Open");    
	      item.addActionListener(fmh);
	      fileMenu.add(item);

	      fileMenu.addSeparator();          
	    
	      item = new JMenuItem("Quit");      
	      item.addActionListener(fmh);
	      fileMenu.add(item);
	      
	      this.setJMenuBar(menuBar);
	      menuBar.add(fileMenu);
	} 
}
