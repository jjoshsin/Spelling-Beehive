// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * The abstract WordList class that sets up a wordlist empty by default
 * 
 * 
 */
package project3;


public abstract class WordList {
	protected WordNode first; // the first node of the list
	protected WordNode last; // the last node of the list
	protected int length;
	/**
	 * The constructor that instantiates a WordList.
	 */
	public WordList() {
		WordNode ln = new WordNode(null);
		first = ln;
		last = ln;
		length = 0;
	}
	/**
	 * Adds a new WordNode to the WordList.
	 * @param w the Word being added 
	 */
	public void append(Word w) {
		WordNode n = new WordNode(w);
		last.next = n;
		last = n;
		length++;
	}
}
