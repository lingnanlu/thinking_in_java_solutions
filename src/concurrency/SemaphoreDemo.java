package concurrency;


import static io.lingnanlu.github.Print.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class CheckoutTask<T> implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private Pool<T> pool;
	public CheckoutTask(Pool<T> pool) {
		// TODO 自动生成的构造函数存根
		this.pool = pool;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			T item = pool.checkOut();
			print(this + "checked out " + item);
			TimeUnit.SECONDS.sleep(1);
			print(this + "checking in " + item);
			pool.checkIn(item);
		} catch(InterruptedException e) {
			
		}

	}
	
	public String toString() {
		return "CheckoutTask " + id + " ";
	}
	
}


public class SemaphoreDemo {

	final static int SIZE = 25;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i = 0; i < SIZE; i++)
			exec.execute(new CheckoutTask<Fat>(pool));
		
		print("All CheckoutTasks created");
		
		List<Fat> list = new ArrayList<Fat>();
		
		for(int i = 0; i < SIZE; i++) {
			Fat f = pool.checkOut();
			printnb(i + ": main() thread checked out ");
			f.operation();
			list.add(f);
		}
		
		Future<?> blocked = exec.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				try {
					pool.checkOut();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					print("checkOut() Interrupted");
				}
			}
		});
		
		
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);
		print("Checking in objects in " + list);
		
		for(Fat f : list)
			pool.checkIn(f);
		
		for(Fat f : list)
			pool.checkIn(f);
		
		
		exec.shutdown();
	}

}
