package concurrency;


import io.lingnanlu.github.DaemonThreadFactory;

import java.util.concurrent.*;
public class DaemonFromFactory implements Runnable{

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		ExecutorService exec = 
				Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i = 0; i < 10; i++){
			exec.execute(new DaemonFromFactory());
		}
		
		System.out.println("All daemons started");
		//TimeUnit.MILLISECONDS.sleep(500);
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
	}

}
