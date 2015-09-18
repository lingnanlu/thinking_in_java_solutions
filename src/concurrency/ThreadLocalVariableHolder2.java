package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Accessor implements Runnable{
	private ThreadLocal<Integer> localVar;
	private final int id;
	public Accessor(int idn, ThreadLocal<Integer> localVar){
		id = idn;
		this.localVar = localVar;
	}
	
	public void run(){
		while(!Thread.currentThread().isInterrupted()){
			localVar.set(localVar.get() + 1);
			System.out.println(this);
			Thread.yield();
		}
	}
	
	public String toString(){
		return "#" + id + ": " + localVar.get();
	}
}

class CustomThreadLocal extends ThreadLocal<Integer>{
	private Random rand = new Random(47);

	@Override
	protected Integer initialValue() {
		// TODO 自动生成的方法存根
		return rand.nextInt(10000);
	
	}
	
}
public class ThreadLocalVariableHolder2 {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomThreadLocal localvar = new CustomThreadLocal();
		for(int i = 0; i < 5; i++){
			exec.execute(new Accessor(i, localvar));

		}
		
		TimeUnit.SECONDS.sleep(3);
		exec.shutdownNow();
	}

}
