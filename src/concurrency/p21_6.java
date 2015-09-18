package concurrency;
import java.util.Random;
import java.util.concurrent.*;
public class p21_6 implements Runnable{

	public static Random rand = new Random(47);
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new p21_6());
		}
		
		exec.shutdown();
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		int sleepingTime = rand.nextInt(10) + 1;
		try {
			TimeUnit.SECONDS.sleep(sleepingTime);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(sleepingTime);
	}

}
