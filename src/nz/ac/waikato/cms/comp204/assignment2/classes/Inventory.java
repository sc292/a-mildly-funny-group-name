package nz.ac.waikato.cms.comp204.assignment2.classes;

import java.util.ArrayList;

/**
 * This class represents the player's inventory.
 * <p>
 * 		It holds all the items that the player gathers through the game
 * 		which can be used on players.
 * </p>
 * 
 * @author A Mildly Funny Group Name
 *
 */
public class Inventory {
	ArrayList<InventoryItem> items;
	
	/**
	 * Creates a new Inventory object
	 */
	public Inventory() {
		items = new ArrayList<InventoryItem>();
	}
	
	/**
	 * Creates a new Inventory object with a given list of items
	 * 
	 * @param items the items to instantiate the inventory with
	 */
	public Inventory(ArrayList<InventoryItem> items) {
		if (items == null)
			throw new IllegalArgumentException("items cannot be null");
		
		this.items = items;
	}
	
	/**
	 * Adds a given item to the inventory
	 * <p>
	 * 		If the item of the same type (ie. name) has already been added
	 * 		to the inventory, the quantity is incremented by the quantity
	 * 		of the given item, otherwise the object is added to the inventory.
	 * </p>
	 *  
	 * @param item the item to add to the inventory
	 * 
	 */
	public void add(InventoryItem item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).ofType() == item.ofType() ) {
				items.get(i).changeQuantity(item.getQuantity());
				return;
			}
		}
		
		items.add(item);
	}
}
