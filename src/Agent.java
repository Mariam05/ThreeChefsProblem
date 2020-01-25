import java.util.Random;

/**
 * The agent is the producer. It provides the table with two of the three ingredients.
 * It controls the number of iterations (i.e. the number of sandwiches made)
 * 
 * @author Mariam Almalki
 *
 */
public class Agent implements Runnable {

	/**
	 * The table object that the agent is at 
	 */
	private Table table;
	
	/**
	 * Whether the table is finished or not
	 */
	public boolean finished;

	/**
	 * The number of iterations / sandwiches made 
	 */
	private static final int ITERATIONS = 20;

	/**
	 * Create a new agent object
	 * @param table the Table object that the agent is at
	 */
	public Agent(Table table) {
		this.table = table;
		finished = false;
	}

	@Override
	public void run() {
		Random random = new Random();
		int ing1, ing2;
		for (int i = 0; i < ITERATIONS; i++) {

			do {
				ing1 = Math.abs(random.nextInt()) % 3; // 0-2
				ing2 = Math.abs(random.nextInt()) % 3; // 0-2
			} while (ing1 == ing2); // continue until we have 2 different ingredients 

			this.table.put(ing1 + ing2);

		}

		table.done = true; // signal the finishing of the table 
	
	}

}
