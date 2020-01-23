
public class Table {

	// attributes
	public static final int PB = 0;
	public static final int JAM = 1;
	public static final int BREAD = 2;

	private static final int EVERYTHING = PB + JAM + BREAD; // 3

	public boolean done;

	private int count = 1;

	private int onTable;

	private boolean empty;

	public Table() {
		empty = true;
		onTable = 0;
		done = false;
	}

	public synchronized void put(int items) {

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

		empty = false;
		onTable = items;
		notifyAll();

	}

	public synchronized void makeAndEat(int item) {
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

		empty = true;
		onTable = 0;
		count++;
		notifyAll();

	}

}
