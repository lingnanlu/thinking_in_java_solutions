package concurrency;


import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.*;
public class p21_9 implements Runnable{

	private int countDown = 5;
	private volatile double d;
	private int priority = Thread.MIN_PRIORITY;
	
	public p21_9(int priority){
		this.priority = priority;
	}
	
	public p21_9(){
	}
	
	public String toString(){
		return Thread.currentThread() + ": " + countDown;
	}
	
	
	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory(){

			@Override
			public Thread newThread(Runnable r) {
				
				Thread t = new Thread(r);
				t.setPriority(MAX_PRIORITY);
				
				return t;
			}

	/*	//	Random rand = new Random();
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setPriority(MAX_PRIORITY);
				return t;	
			}*/
		});
		
		//ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new p21_9(Thread.MIN_PRIORITY));
		}
		
		exec.execute(new p21_9());
		exec.shutdown();
	}

	@Override
	public void run() {
		
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