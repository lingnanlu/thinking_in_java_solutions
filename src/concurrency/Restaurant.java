/*
 * 
 * 
 * TIJ4 涓殑浠ｇ爜姣旇緝鑷冭偪锛岃繖閲屾槸鏀硅繘鐗�
 

package concurrency;

import java.util.concurrent.*;

import static io.lingnanlu.github.Print.*;

class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable {
	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
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

class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant r) {
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


public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String[] args) throws InterruptedException {
		
		new Restaurant();
	}
} 
*/