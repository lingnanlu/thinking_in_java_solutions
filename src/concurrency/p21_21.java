package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class p21_21 {

	static class A implements Runnable{

		@Override
		public synchronized void run() {
			// TODO 自动生成的方法存根
			try {
				//线程在A对象上排队
				System.out.println(Thread.currentThread());
				//该方法只能在synchronized方法中调用
				wait();
				System.out.println("hehehehehe");
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
	
	
	static class B implements Runnable{

		private A a;
		
		public B(A a){
			this.a = a;
		}
		
		
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			System.out.println("Running for several seconds");
			try {
				TimeUnit.SECONDS.sleep(3);
				synchronized (a) {
					//该方法只能在synchronized方法中调用
					a.notifyAll();
				}
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		
		A a = new A();
		for(int i = 0; i < 5; i++){
			exec.execute(a);
		}
		
		exec.execute(new B(a));
		TimeUnit.SECONDS.sleep(6);
		exec.shutdown();
	}

}
