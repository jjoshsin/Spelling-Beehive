// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * The UnsortedWordList class that will store all the solutions in the input file
 * 
 * 
 */
package project3;

public class UnsortedWordList extends WordList{
	/**
	 * A no argument constructor class instantiating
	 * the super class.
	 */
	public UnsortedWordList() {
		super();
	}
	/**
	 * The add method for the UnsortedWordList that
	 * simply appends every word using the method from
	 * its super class.
	 * @param word the word being added to the list
	 */
	public void add(Word w) {
		append(w);
	}
	/**
	 * A method that checks to see if a word is 
	 * in the list.
	 * @param s the word that is being checked
	 * @return return true or false 
	 */
	public boolean found(Word s) {
		WordNode curr = first.next;
		boolean found = false;
		while(curr.next != null) { // while the list is not empty
			if((curr.data).equals(s)) { // if the current data value equals the word
				found = true;
				break;
			}
			curr = curr.next;
		}
		return found;
	}
	/**
	 * Overriding the toString() method to display 
	 * the data values in every line.
	 */
	public String toString() {
		WordNode p = first.next;
		String returnString = "";
		while (p != null) { // while the list is not empty
			returnString += p.data + " ";
			p = p.next;
		}
		return returnString;
	}
}