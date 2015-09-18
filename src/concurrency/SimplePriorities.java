package concurrency;


import java.util.concurrent.*;
public class SimplePriorities implements Runnable{

	private int countDown = 5;
	private volatile double d;
	private int priority;
	
	public SimplePriorities(int priority){
		this.priority = priority;
	}
	
	public String toString(){
		return Thread.currentThread() + ": " + countDown;
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		Thread.currentThread().setPriority(priority);
		while(true){
			for(int i = 1; i < 100000; i++){
				d += (Math.PI + Math.E) / (double)i;
				if(i % 1000 == 0){
					Thread.yield();
				}
			}
			System.out.println(this);
			if(--countDown == 0)
				return;
		}
	}

}
