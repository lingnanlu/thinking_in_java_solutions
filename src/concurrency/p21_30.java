package concurrency;

import static io.lingnanlu.github.Print.*;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


class Sender implements Runnable {

	private BlockingQueue<Character> charQueue;
	private Random rand = new Random(47);
	
	
	public Sender(BlockingQueue<Character> queue){
		charQueue = queue;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(true){
				for(char c = 'A'; c <= 'z'; c++){
					charQueue.put(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
			}
		} catch (InterruptedException e){
			print(e + " Sender sleep interrupted");
		}
	}
	
}

class Receiver implements Runnable {
	
	private BlockingQueue<Character> charQueue;
	public Receiver(BlockingQueue<Character> queue) {
		charQueue = queue;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(true) {
				printnb("Read: " + charQueue.take() + ", ");
			}
		} catch (InterruptedException e) {
			print(e + " Receiver interrupted");
		}
	}
	
}

public class p21_30 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO 自动生成的方法存根
		BlockingQueue<Character> queue = new LinkedBlockingQueue<Character>();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Sender(queue));
		exec.execute(new Receiver(queue));
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}

}
