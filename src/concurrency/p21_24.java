package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Item {
	private static int counter;
	private final int id = counter++;
	public String toString() {
		return "Item " + id;
	}
}


class FlowQueue<T>{
	private Queue<T> queue = new LinkedList<T>();
	
	private int maxSize;
	public FlowQueue(int maxSize){
		this.maxSize = maxSize;
	}
	
	public synchronized void put(T item) throws InterruptedException{
		while(queue.size() >= maxSize){
			wait();
		}
		queue.offer(item);
		notifyAll();
	}
	
	public synchronized T get() throws InterruptedException{
		while(queue.isEmpty()){
			wait();
		}
		T returnVal = queue.poll();
		notifyAll();
		return returnVal;
	}
}

class Consumer implements Runnable {

	private FlowQueue<Item> queue;
	private int delaytime;
	public Consumer(FlowQueue<Item> queue, int delaytime){
		this.delaytime = delaytime;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(true){
				TimeUnit.MILLISECONDS.sleep(delaytime);
				Item result = queue.get();
				System.out.println(result);
			}
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			System.out.println("Consumer interrupted");
		}
	}
}

class Producer implements Runnable {

	private FlowQueue<Item> queue;
	private int delaytime;
	public Producer(FlowQueue<Item> queue, int delaytime){
		this.delaytime = delaytime;
		this.queue = queue;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(true){
				TimeUnit.MILLISECONDS.sleep(delaytime);
				queue.put(new Item());
			}
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			System.out.println("Producer interrupted");
		}
	}
	
}
public class p21_24 {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		
		if(args.length < 2) {
			System.err.println("Usage java p21_24 <producer sleep time> <consumer sleep time>");
			System.exit(1);
		}
		FlowQueue<Item> queue = new FlowQueue<Item>(100);
		int producerSleepTime = Integer.parseInt(args[0]);
		int consumerSleepTime = Integer.parseInt(args[1]);
		ExecutorService exec = Executors.newFixedThreadPool(2);
		exec.execute(new Producer(queue, producerSleepTime));
		exec.execute(new Consumer(queue, consumerSleepTime));
		
		TimeUnit.SECONDS.sleep(3);
		exec.shutdownNow();
	}

}
