package concurrency;
import static io.lingnanlu.github.Print.*;
import static java.util.concurrent.TimeUnit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Runnable, Delayed {

	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();
	
	public DelayedTask(int delayInMilliseconds) {
		// TODO 自动生成的构造函数存根
		
		delta = delayInMilliseconds;
		trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
		sequence.add(this);
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		printnb(this + " ");
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO 自动生成的方法存根
		DelayedTask that = (DelayedTask)o;
		
		
		//这里的大小顺序是如何规定的。
		return trigger < that.trigger ? 1 : (trigger > that.trigger ? -1 : 0);
	}

	
	@Override
	public long getDelay(TimeUnit unit) {
		// TODO 自动生成的方法存根
		return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
	}
	
	public String toString() {
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}
	
	public String summary() {
		return "(" + id + ":" + delta + ")";
	}
	
	
	public static class EndSentinel extends DelayedTask {

		private ExecutorService exec;
		public EndSentinel(int delay, ExecutorService e) {
			super(delay);
			// TODO 自动生成的构造函数存根
			exec = e;
		}
		
		public void run() {
			for(DelayedTask pt : sequence) {
				printnb(pt.summary() + " ");
			}
			
			print();
			print(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}

class DelayedTaskConsumer implements Runnable {

	private DelayQueue<DelayedTask> q;
	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		// TODO 自动生成的构造函数存根
		this.q = q;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try{
			while(!Thread.interrupted()){
				q.take().run();
			}
		} catch (InterruptedException e){
			
		}
		
		print("Finished DelayedTaskConsumer");
		
	}
	
	
	
}
public class DelayQueueDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		
		for(int i = 0; i < 20; i++){
			queue.put(new DelayedTask(rand.nextInt(5000)));
		}
		
		queue.add(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(queue));
	}

}
