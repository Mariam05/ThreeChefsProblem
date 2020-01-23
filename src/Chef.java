
public class Chef implements Runnable {
	private Table table; // the table the chef is at
	private int item; // the ingredient that this chef has

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

