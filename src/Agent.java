import java.util.Random;

public class Agent implements Runnable {

	private Table table;
	public boolean finished;

	private static final int ITERATIONS = 20;

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
			} while (ing1 == ing2); // until different value

			this.table.put(ing1 + ing2);

		}

		table.done = true;

	}

}
