// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * The Word class that behaves as a String class
 * 
 * 
 */
package project3;

public class Word {
	private String word;
	/**
	 * One argument constructor for class Word.
	 * @param w the word that is being declared
	 */
	public Word(String w) {
		for(int i = 0; i < w.length(); i++) {
			char currchar = w.charAt(i);
			if(Character.toUpperCase(currchar) == currchar) { // if the current letter is an uppercase letter
				throw new IllegalWordException(w + " should be all lowercase");
			}
		}
		this.word = w;
	}
	/**
	 * Overriding the toString() method to make the
	 * Word class = String class.
	 */
	public String toString() {
		return word;
	}
	/**
	 * A Word.equals() method that acts like a 
	 * String.equals() method.
	 * @param other the other Word being compared to
	 * @return true or false
	 */
	public boolean equals(Word other) {
		return word.equals(other.toString());
	}
	/**
	 * A Word.compareTo() method that acts like a
	 * String.compareTo() method.
	 * @param other the other Word being compared to
	 * @return true or false
	 */
	public int compareTo(Word other) {
		return word.compareTo(other.toString());
	}
}
