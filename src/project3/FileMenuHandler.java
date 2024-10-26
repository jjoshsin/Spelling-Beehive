// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * The FileMenuHandler class that processes other files as Project2
 * 
 */
package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileMenuHandler implements ActionListener {
	PuzzleGUI2 GUI;
	private static JFileChooser fd;
	private static File file;
	private static String filePath;
	private static UnsortedWordList uwl;
	private static SortedWordList swl;
	private static TextFileInput text;
	public static JTextArea solutions = new JTextArea();
	private static int points = 0;
	/**
	 * The constructor for FileMenuHandler
	 * @param jf the PuzzleGUI2 that is using actions
	 */
    public FileMenuHandler (PuzzleGUI2 jf) {
    GUI = jf;
    }
    /**
     * The method that handles the events performed
     * @param event the event being performed
     */
    public void actionPerformed(ActionEvent event) {
    	String menuName = event.getActionCommand();
        if (menuName.equals("Open")) { // if the user clicks on Open
        	fd = new JFileChooser();
			if(fd.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { // if the user chooses a file
				file = fd.getSelectedFile();
				filePath = file.getAbsolutePath();
				text = new TextFileInput(filePath);
				String firstline = text.readLine();
				uwl = fillList();
				swl = new SortedWordList();
				PuzzleGUI2 myGUI = new PuzzleGUI2();
				PuzzleWord(myGUI, firstline);
				guessWords(myGUI, swl, firstline);
				showScore(myGUI, swl);
			}
        else if (menuName.equals("Quit")) { // if the user clicks on Quit
        	JOptionPane.showMessageDialog(null,"System is now exiting"); 
        	System.exit(0);
        	}
        }
    }
    /**
	 * Fills up every cell of the UnsortedWordList using the lines
	 * from the given file.
	 * @return result the UnsortedWordList filled with all the words in the text file
	 */
	public static UnsortedWordList fillList() {
		UnsortedWordList result = new UnsortedWordList();
		String line = text.readLine();
		while(line != null) { // while the file is not finished being read
			Word x = new Word(line);
			result.add(x);
			line = text.readLine();
		}
		return result;
	}
	/**
	 * Implements the improving on word games part of the project.
	 * @param word the string the user inputted that is being checked
	 * @param GUI the gameGUI being used
	 * @param swl the SortedWordList that is being checked
	 * @param letters the letters of the first line of the file
	 * @return isnotvalid return true or false depending on if the word passes every test
	 */
	public static boolean isImproved(String word, PuzzleGUI2 GUI, SortedWordList swl, String letters) {
		boolean isnotvalid = false;
		char[] letters1 = letters.toCharArray();
		String firstletter = String.valueOf(letters.charAt(0));
		if(!word.contains(firstletter)) { // if the word does not contain the first letter
			JOptionPane.showMessageDialog(null, "The word must have the letter "+ firstletter);
			isnotvalid = true;
			return isnotvalid;
		}
		int wordlength = word.length();
		int j = 0;
		for(int i = 0; i < letters1.length; i++) {
			System.out.println(letters1[i]);
			String currletter = String.valueOf(letters1[i]);
			if(word.contains(currletter)) { // if the word contains a letter from the first line of letters
				j++;
			}
			if(j == wordlength) { // if the word has every letter in the first line, the points worth is +3
				points += 2; // program +1 for every word guessed correctly so 2 + 1 = 3 points
			}
		}
		Word w = new Word(word);
		if(swl.found(w)) { // if the word is on the list already
			JOptionPane.showMessageDialog(null, w+" is already on the list");
			isnotvalid = true;
			return isnotvalid;
		}
		return isnotvalid;
	}
	/**
	 * Shows the user's score after playing
	 * @param GUI the GUI that will add the score
	 * @param swl the SortedWordList that is appended into the  GUI
	 */
	public static void showScore(PuzzleGUI2 GUI, SortedWordList swl) {
		solutions.setText(swl +"Score: " + points);
		GUI.add(solutions);
	}
	/**
	 * Displays a JOptionPane that allows the user to type in
	 * their guesses with the given letters.
	 * @param GUI the gameGUI being used
	 * @param swl the SortedWordList that will have the user inputs 
	 * @param letters the letters the user must use to guess words
	 */
	public static void guessWords(PuzzleGUI2 GUI, SortedWordList swl, String letters) {
		while(true) { // while the user does not type stop
			String word = JOptionPane.showInputDialog("Type as many words as you can with the following letters: " + letters);
			try {
				if(word.equalsIgnoreCase("stop")) { // if word equals stop, stop the loop
					break;
				}
				if(!isValid(word, uwl)) { // if it passes every data validation test
					if(!isImproved(word, GUI, swl, letters)) { // if it also passes the improvement section
						Word w = new Word(word);
						swl.add(w);
						solutions.setText(swl + "");
						GUI.add(solutions);
						points++;
					}
				}
			}
			catch(IllegalWordException iwe) {
				System.out.println(word + " is an invalid input!");
			}
		}
	}
	/**
	 * Checks to validate that the data given by 
	 * the user is a valid word
	 * @param word the word that the user typed into the JOptionPane
	 * @param uwl the UnsortedWordList that is being checked 
	 * @return isnotvalid return true or false depending on if the word passes every test
	 */
	public static boolean isValid(String word, UnsortedWordList uwl) {
		boolean isnotvalid = false;
		
		if(word.length() < 5 && !word.equalsIgnoreCase("Stop")) { // if the word length is < 5, show message dialog saying it is less than 5
			JOptionPane.showMessageDialog(null, word + " is less than length 5");
			isnotvalid = true;
			return isnotvalid;
		}
		Word w = new Word(word);
		if(!uwl.found(w)) { // if the word is found in list
			JOptionPane.showMessageDialog(null, "Your input is not on the solutions list");
			isnotvalid = true;
			return isnotvalid;
		}
		return isnotvalid;
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
		solutions.setEditable(false);
		mycontainer.add(mytextarea);
		mycontainer.add(solutions);
		f.add(mytextarea);
		f.setVisible(true);
	}
} 