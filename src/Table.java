/**
 * This class acts as the bounded buffer.
 * It controls what threads can run and which have to wait until a condition is satisfied. 
 *
 * @author Mariam Almalki
 *
 */
public class Table {

	// attributes
	public static final int PB = 0;
	public static final int JAM = 1;
	public static final int BREAD = 2;

	/**
	 * All the ingredients together
	 */
	private static final int EVERYTHING = PB + JAM + BREAD; // 3

	/**
	 * Signals when the agent is done
	 */
	public boolean done;

	/**
	 * Keeps track of the number of sandwiches made
	 */
	private int count = 1;

	/**
	 * The two ingredients on the table 
	 */
	private int onTable;

	/**
	 * Whether the table is empty
	 */
	private boolean empty;

	/**
	 * Constructs a table
	 */
	public Table() {
		empty = true;
		onTable = 0;
		done = false;
	}

	/**
	 * Handles the functionality of putting items on the table. Is called by the agent. 
	 * @param items the sum of the two ingredients to be put on the table 
	 */
	public synchronized void put(int items) {

		// If the table is not empty, make the agent wait before putting the items on it
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		switch (items) {
		case 1:
			System.out.println(count + " putting : PB + JAM");
			break;
		case 2:
			System.out.println(count + " putting : PB + BREAD");
			break;
		case 3:
			System.out.println(count + " putting : JAM + BREAD");
			break;
		}

		empty = false; // after the agent puts something on the table, it is no longer empty
		onTable = items; // Update the ingredients on the table 
		notifyAll();

	}

	/**
	 * To be called by the chefs to let them make and eat the sandwich
	 * @param item the integer representing the item that the chef has
	 */
	public synchronized void makeAndEat(int item) {
		
		// If the table is empty or the chef doesn't have the missing ingredient make them wait. 
		while (empty || onTable + item != EVERYTHING) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(Thread.currentThread().getName() + " is making and eating his/her sandwich");
		try {
			Thread.sleep(500); // give the chef time to make and eat,, block other threads
		} catch (InterruptedException e) {

		}
		System.out.println(Thread.currentThread().getName() + " finished eating his/her sandwich\n");

		empty = true; // Once the chef makes and eats the sandwich, the table is empty
		onTable = 0; // Update the ingredients on the table to 0
		count++; //update the number of ingredients on the table 
		notifyAll();

	}

}
