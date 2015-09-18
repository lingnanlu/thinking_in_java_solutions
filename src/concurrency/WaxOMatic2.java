

/*
 * 
 * 
 * TIJ4 中的代码比较臃肿，这里是改进版
 */
package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Car {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;
	
	public void wax() throws InterruptedException {
		lock.lock();
		try {
			
			//如果已经上蜡了，就等待
			while(waxOn == true){
				condition.await();
			}
			
			//否则上蜡，并通过等待抛光的
			waxOn = true;
			System.out.println("wax on! ");
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void buff() throws InterruptedException {
		lock.lock();
		try {
			
			//如果已经抛光了，就等待
			while(waxOn == false){
				condition.await();
			}
			
			//否则抛光，并通知等待上蜡的
			waxOn = false;
			System.out.println("wax off! ");
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn implements Runnable {

	private Car car;
	
	public WaxOn(Car car){
		this.car = car;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(true) {
				car.wax();
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch (InterruptedException e) {
			System.out.println("WaxOn interrupted");
		}

	}
	
}

class WaxOff implements Runnable {
	
	private Car car;
	
	public WaxOff(Car car){
		this.car = car;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(true) {
				car.buff();
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch (InterruptedException e) {
			System.out.println("WaxOn interrupted");
		}
	}
	
}
public class WaxOMatic2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn(car));
		exec.execute(new WaxOff(car));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}

}
