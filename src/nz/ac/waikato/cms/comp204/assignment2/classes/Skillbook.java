package nz.ac.waikato.cms.comp204.assignment2.classes;
/**
 * This class represents a skillbook item that the player can collect through out the game.
 * 
 * <p>
 * 		A skillbook item is used to learn skills. Each skill requires a number of skillbook
 * 		items to be able to be learnt.
 * </p>
 * 
 * @author A Mildly Funny Group Name
 *
 */
public class Skillbook extends InventoryItem {

	public Skillbook(String name) {
		super(name);
	}
	
	public Skillbook(String name, int quantity) {
		super(name, quantity);
	}
}
