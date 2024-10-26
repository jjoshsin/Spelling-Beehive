// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * The SortedWordList class that will add every word guessed correctly
 * in a sorted manner
 * 
 */
package project3;

public class SortedWordList extends WordList{
	/**
	 * A no argument constructor class instantiating
	 * the super class.
	 */
	public SortedWordList() {
		super();
	}
	/**
	 * The add method for SortedWordList that adds
	 * new data in a sorted manner.
	 * @param word the word to be added
	 */
	public void add(Word word) {
		WordNode node = new WordNode(word);
		WordNode previous = first;
		while(previous.next != null && (previous.next).data.compareTo(word) < 0) { // while the list is not empty and checks 
			previous = previous.next;                                              // if the data values alphabetically
		}
		node.next = previous.next;
		previous.next = node;
		length++;
	}
	/**
	 * Overriding the toString() method to display
	 * the data values in every line.
	 */
	public String toString() {
		WordNode p = first.next;
		String returnString = "";
		while (p != null) { // while the list is not empty
			returnString += p.data + "\n";
			p = p.next;
		}
		return returnString;
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
		while(curr != null) { // while the list is not empty
			if((curr.data).equals(s)) { // if the current data value equals the word
				found = true;
				break;
			}
			curr = curr.next;
		}
		return found;
	}
}