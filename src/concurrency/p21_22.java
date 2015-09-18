package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class p21_22 {

	static volatile boolean flag = false;
	
	static Object obj = new Object();
	
	
	static class A implements Runnable {
		private int count = 0;
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			
			//忙等
			while(flag == false){
				count++;
			}
			
			flag = false;
			System.out.println("waiting time = " + count);
		}
		
	}
	
	static class B implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				TimeUnit.SECONDS.sleep(1);
				flag = true;
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
	
	
	static class C implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			synchronized (obj) {
				
				try {
					obj.wait();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
				
			}
		}
		
	}
	
	static class D implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			
		}
		
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new A());
		exec.execute(new B());
		TimeUnit.SECONDS.sleep(5);
		exec.shutdown();
	}

}
