package concurrency;
import static io.lingnanlu.github.Print.*;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class TaskPortion implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;
	
	public TaskPortion(CountDownLatch latch) {
		// TODO 自动生成的构造函数存根
		this.latch = latch;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		print(this + "completed");
	}
	
	public String toString() {
		return String.format("%1$-3d ", id);
	}
	
}

class WaitingTask implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;
	
	public WaitingTask(CountDownLatch latch) {
		// TODO 自动生成的构造函数存根
		this.latch = latch;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			latch.await();
			print("Latch barrier passed for " + this);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			print(this + " interrupted");
		}
	}
	
}
public class CountDownLatchDemo {
	
	static final int SIZE = 100;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for(int i = 0; i < 10; i++){
			exec.execute(new WaitingTask(latch));
		}
		
		for(int i = 0; i < SIZE; i++){
			exec.execute(new TaskPortion(latch));
		}
		
		print("Launched all tasks");
		exec.shutdown();
	}

}
