package concurrency;

import java.util.concurrent.*;

public class SleepingTask extends LiftOff{

	public void run(){
		while(countDown-- > 0){
			System.out.print(status());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new SleepingTask());
		}
		
		exec.shutdown();
	}

}
