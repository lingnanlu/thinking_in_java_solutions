package concurrency;

import static io.lingnanlu.github.Print.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		// TODO 自动生成的构造函数存根
		this.rockets = queue;
	}
	
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			print("Interrupted during put()");
		}
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try{
			while(!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			print("Waking from take()");
		}
		
		print("Exiting LiftOffRunner");

	}
	
}
public class TestBlockingQueues {
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
	
	static void test(String msg, BlockingQueue<LiftOff> queue) {
		print(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for(int i = 0; i < 5; i++)
			runner.add(new LiftOff(5));
		
		getkey("Press 'Enter' (" + msg + ")");
		t.interrupt();
		print("Finished " + msg + " test");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		test("LinkedBlockingQueue", new LinkedBlockingDeque<LiftOff>());
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());
		
	}

}
