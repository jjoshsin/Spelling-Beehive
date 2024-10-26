// Joshua Sin Lab Section: 111E(34438)
/************************************************************************************
 * @author J. Sin
 * The WordNode class that sets up a WordNode
 * 
 * 
 */
package project3;

public class WordNode {
	protected Word data; // the data value of the node
	protected WordNode next; // the part of the node that points to the address of the next node
	/**
	 * The constructor to set up a WordNode.
	 * @param d the data value of the WordNode
	 */
	public WordNode(Word d) {
		this.data = d;
		this.next = null;
	}
}