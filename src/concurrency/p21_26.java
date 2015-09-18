/*package concurrency;

import java.util.concurrent.*;

import static io.lingnanlu.github.Print.*;

class Meal2 {
	private final int orderNum;

	public Meal2(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson2 implements Runnable {
	private p21_26 restaurant;

	public WaitPerson2(p21_26 r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (restaurant) {
					while (restaurant.meal == null)
						restaurant.wait(); // ... for the chef to produce a meal
				}
				print("Waitperson got " + restaurant.meal);
				synchronized (restaurant) {
					restaurant.meal = null;
					restaurant.notifyAll(); // Ready for another
				}
			}
		} catch (InterruptedException e) {
			print("WaitPerson interrupted");
		}
	}
}

class Chef2 implements Runnable {
	private p21_26 restaurant;
	private int count = 0;

	public Chef2(p21_26 r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (restaurant) {
					while (restaurant.meal != null)
						restaurant.wait(); // ... for the meal to be taken
				}
				if (++count == 10) {
					print("Out of food, closing");
					restaurant.exec.shutdownNow();
					//p21_25
					//return;
				}
				printnb("Order up! ");
				synchronized (restaurant) {
					restaurant.meal = new Meal(count);
					restaurant.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			print("Chef interrupted");
		}
	}
}


public class p21_26 {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson2 waitPerson = new WaitPerson2(this);
	Chef2 chef = new Chef2(this);

	public p21_26() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String[] args) throws InterruptedException {
		
		new p21_26();
	}
} 
*/