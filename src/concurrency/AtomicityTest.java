package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {

	private int i = 0;
	public int getValue(){
		return i;
	}
	
	private synchronized void evenIncrement(){
		i++;
		i++;
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while(true){
			int val = at.getValue();
			if(val % 2 != 0){
				System.out.println(val);
				System.exit(0);
			}
		}
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			evenIncrement();
		}
	}

}
