/**
 * This class is used to encapsulate the behaviour of the chefs. 
 * Each chef has an unlimited quantity of only one ingredient
 * 
 * @author Mariam Almalki
 *
 */
public class Chef implements Runnable {
	
	/**
	 * The table the chef is at 
	 */
	private Table table;
	
	/**
	 * The ingredient that this chef has 
	 */
	private int item; 
	
	/**
	 * Construct a new chef 
	 * @param table the Table object they are on
	 * @param item an integer representing the ingredient that the chef has
	 */
	public Chef(Table table, int item) {
		this.table = table;
		this.item = item;
	}

	@Override
	public void run() {
		while (!table.done) {
			this.table.makeAndEat(item);
		}

	}
}

