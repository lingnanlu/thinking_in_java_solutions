package concurrency;

import static io.lingnanlu.github.Print.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		// TODO 自动生成的构造函数存根
		this.rockets = queue;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while (!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			print("Waking from take()");
		}

		print("Exiting LiftOffRunner");

	}

}

class LiftOffAdder implements Runnable {

	private BlockingQueue<LiftOff> rockets;

	public LiftOffAdder(BlockingQueue<LiftOff> queue) {
		// TODO 自动生成的构造函数存根
		this.rockets = queue;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			for (int i = 0; i < 5; i++)
				rockets.put(new LiftOff(5));
		} catch (InterruptedException e) {
			print("Waking from take()");
		}

		print("Exiting LiftOffAdder");
	}

}

public class p21_28 {
	static void getkey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			throw new RuntimeException(e);
		}
	}

	static void getkey(String message) {
		print(message);
		getkey();
	}

	static void test(String msg, BlockingQueue<LiftOff> queue) throws InterruptedException {
		print(msg);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new LiftOffRunner(queue));
		exec.execute(new LiftOffAdder(queue));

		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
		//getkey("Press 'Enter' (" + msg + ")");
		print("Finished " + msg + " test");
		
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		test("LinkedBlockingQueue", new LinkedBlockingDeque<LiftOff>());
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());

	}

}
