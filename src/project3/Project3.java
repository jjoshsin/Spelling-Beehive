// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * An improved program of a mock version of the Spelling Beehive game
 * that uses linked lists.
 * 
 */

package project3;

import javax.swing.*;
public class Project3 extends PuzzleGUI2{
	private static String filename = "P3input.txt"; 
	private static TextFileInput file = new TextFileInput(filename); 
	private static String letters = file.readLine(); 
	private static UnsortedWordList myList = fillList(); 
	private static SortedWordList mySortedList = new SortedWordList();
	private static int points = 0;
	
	public static void main(String[] args) {
		while(true) { // while the user does not put in a valid option
			String option = JOptionPane.showInputDialog("Would you like to play the original Spelling Beehive Game or use a different file? (play/different)");
			if(option.equalsIgnoreCase("play")) { // if the user wants to play the Spelling Beehive Game
				PuzzleGUI2 gameGUI = new PuzzleGUI2();
				PuzzleWord(gameGUI, letters);
				guessWords(gameGUI, mySortedList);
				showScore(gameGUI, mySortedList);
				break;
			}
			if(option.equalsIgnoreCase("different")) { // if the user just wants to use a different file
				PuzzleGUI2 myGUI = new PuzzleGUI2();
				break;
			}
			System.out.println(option + " is not a valid option!");
		}
	}
	/**
	 * Fills up every cell of the UnsortedWordList using the lines
	 * from the given file.
	 * @return result the UnsortedWordList filled with all the words in the text file
	 */
	public static UnsortedWordList fillList() {
		UnsortedWordList result = new UnsortedWordList();
		String line = file.readLine();
		while(line != null) { // while the file is not finished being read
			Word x = new Word(line);
			result.add(x);
			line = file.readLine();
		}
		String x = "total";
		result.add(new Word(x));
		return result;
	}
	/**
	 * Implements the improving on word games part of the project.
	 * @param word the string the user inputted that is being checked
	 * @param GUI the gameGUI being used
	 * @param swl the SortedWordList that is being checked
	 * @return isnotvalid return true or false depending on if the word passes every test
	 */
	public static boolean isImproved(String word, PuzzleGUI2 GUI, SortedWordList swl) {
		boolean isnotvalid = false;
		String[] letters1 = {"l","a","p","t","i","m","o"};
		if(!word.contains("l")) { // if the word does not contain an "l"
			JOptionPane.showMessageDialog(null, "The word must have the letter l");
			isnotvalid = true;
			return isnotvalid;
		}
		int wordlength = word.length();
		int j = 0;
		for(int i = 0; i < letters1.length; i++) {
			if(word.contains(letters1[i])) { // if the word contains a letter from "laptimo"
				j++;
			}
			if(j == wordlength) { // if the word has every letter in "laptimo", the points worth is +3
				points += 2; // program +1 for every word guessed correctly so 2 + 1 = 3 points
			}
		}
		Word w = new Word(word);
		if(swl.found(w)) { // if the word is on the list already
			JOptionPane.showMessageDialog(null, w+" is already on the list");
			isnotvalid = true;
			return isnotvalid;
		}
		if(swl.length == myList.length-2) { // if the user has guessed every soluton word
			showScore(GUI, swl);
			while(true) { // while the user does not put a valid option
				String x = JOptionPane.showInputDialog("Congratulations! You have guessed all words! Would you like to play again? (yes/no)");
				if(x.equalsIgnoreCase("yes")) { // if they want to play again, restart the program
					JOptionPane.showMessageDialog(null, "Restart the program!");
					System.exit(0);
				}
				if(x.equalsIgnoreCase("no")) System.exit(0); // if no, exit the program
				JOptionPane.showMessageDialog(null, x+" is not a valid option!");
			}
		}
		return isnotvalid;
	}
	/**
	 * Shows the user's score after playing
	 * @param GUI the GUI that will have a score added to
	 * @param swl the SortedWordList that is appended into the GUI
	 */
	public static void showScore(PuzzleGUI2 GUI, SortedWordList swl) {
		mytextarea2.setText(swl +"Score: " + points);
		GUI.add(mytextarea2);
	}
	/**
	 * Displays a JOptionPane that allows the user to type in
	 * their guesses with the given letters.
	 * @param GUI the gameGUI being used
	 * @param swl the SortedWordList that will hold the user's inputs
	 */
	public static void guessWords(PuzzleGUI2 GUI, SortedWordList swl) {
		while(true) { // while the user does not type stop
			String word = JOptionPane.showInputDialog("Type as many words as you can with the following letters: " + letters);
			try {
				if(word.equalsIgnoreCase("stop")) { // if word equals stop, stop the loop
					break;
				}
				if(!isValid(word)) { // if it passes every data validation test
					if(!isImproved(word, GUI, swl)) { // if it also passes the improvement section
						Word w = new Word(word);
						swl.add(w);
						mytextarea2.setText(swl + "");
						GUI.add(mytextarea2);
						points++;
					}
				}
			}
			catch(IllegalWordException iwe) {
				System.out.println(iwe.getMessage());
			}
		}
	}
	/**
	 * Checks to validate that the data given by 
	 * the user is a valid word
	 * @param word the word that the user typed into the JOptionPane
	 * @return isnotvalid return true or false depending on if the word passes every test
	 */
	public static boolean isValid(String word) {
		String[] laptimo = {"b","c","d","e","f","g","h","j","k","n","q","r","s","u","v","w","x","y","z"};
		boolean isnotvalid = false;
		
		if(word.length() < 5 && !word.equalsIgnoreCase("Stop")) { // if the word length is < 5, show message dialog saying it is less than 5
			JOptionPane.showMessageDialog(null, word + " is less than length 5");
			isnotvalid = true;
			return isnotvalid;
		}
		
		for(int i = 0; i < laptimo.length; i++) {
			if(word.contains(laptimo[i]) && !word.equalsIgnoreCase("Stop")) { // if the word contains a letter besides "laptimo"
				JOptionPane.showMessageDialog(null, "Input should not contain " + laptimo[i]); 
				isnotvalid = true;                                                             
				return isnotvalid;
			}
		}
		Word w = new Word(word);
		if(!myList.found(w)) { // if the word is found in list
			JOptionPane.showMessageDialog(null, "Your input is not on the solutions list");
			isnotvalid = true;
			return isnotvalid;
		}
		return isnotvalid;
	}
}
