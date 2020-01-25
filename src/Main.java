/**
 * Main is the producerConsumer class
 * 
 * @author Mariam Almalki
 *
 */
public class Main {

	public static void main(String[] args) {
		Table table = new Table();

		Thread agent = new Thread(new Agent(table), "Agent");

		Thread c1 = new Thread(new Chef(table, Table.PB), "Gordon Ramsey");
		Thread c2 = new Thread(new Chef(table, Table.JAM), "Martha Stewart");
		Thread c3 = new Thread(new Chef(table, Table.BREAD), "Aunt Jemima");

		agent.start();
		c1.start();
		c2.start();
		c3.start();

	}
}
