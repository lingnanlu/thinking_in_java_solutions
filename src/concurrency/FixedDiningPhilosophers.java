package concurrency;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedDiningPhilosophers {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO 自动生成的方法存根
		int ponder = 5;
		if(args.length > 0)
			ponder = Integer.parseInt(args[0]);
		int size = 5;
		if(args.length > 1)
			size = Integer.parseInt(args[1]);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Chopstick[] sticks = new Chopstick[size];
		for(int i = 0; i < size; i++)
			sticks[i] = new Chopstick();
		for(int i = 0; i < size; i++){
			//改变循环等待的条件
			if (i < (size - 1)){
				exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, ponder));
			} else {
				//最后一个哲学家拿筷子的顺序和其它人相反
				exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
			}
			
		}
		
		if(args.length == 3 && args[2].equals("timeout"))
			TimeUnit.SECONDS.sleep(5);
		else {
			System.out.println("Press 'Enter' to quit");
			System.in.read();
		}
		
		exec.shutdownNow();
	}

}
