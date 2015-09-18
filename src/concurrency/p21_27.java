
package concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static io.lingnanlu.github.Print.*;

class Meal3 {
	private final int orderNum;

	public Meal3(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson3 implements Runnable {
	private p21_27 restaurant;

	public WaitPerson3(p21_27 r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				restaurant.lock.lock();
				try{
					while (restaurant.meal == null)
						restaurant.condition.await(); // ... for the chef to produce a meal
				} finally {
					restaurant.lock.unlock();
				}
				print("Waitperson got " + restaurant.meal);
				restaurant.lock.lock();
				try{
					restaurant.meal = null;
					restaurant.condition.signalAll(); // Ready for another
				} finally {
					restaurant.lock.unlock();
				}
			}
		} catch (InterruptedException e) {
			print("WaitPerson interrupted");
		}
	}
}

class Chef3 implements Runnable {
	private p21_27 restaurant;
	private int count = 0;

	public Chef3(p21_27 r) {
		restaurant = r;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				restaurant.lock.lock();
				try{
					while (restaurant.meal != null)
						restaurant.condition.await(); // ... for the meal to be taken
				} finally {
					restaurant.lock.unlock();
				}
				if (++count == 10) {
					print("Out of food, closing");
					restaurant.exec.shutdownNow();
					//p21_25
					//return;
				}
				printnb("Order up! ");
				
				restaurant.lock.lock();
				try{
					restaurant.meal = new Meal3(count);
					restaurant.condition.signalAll();
				} finally {
					restaurant.lock.unlock();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			print("Chef interrupted");
		}
	}
}


public class p21_27 {
	Meal3 meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson3 waitPerson = new WaitPerson3(this);
	Chef3 chef = new Chef3(this);
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	public p21_27() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String[] args) throws InterruptedException {
		
		new p21_27();
	}
} 





