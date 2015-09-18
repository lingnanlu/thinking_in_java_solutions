package test;

import java.util.concurrent.*;


class Task implements Runnable {
	
	private int i = 0;
	public Task() {

	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while (true) {
			// System.out.println();
			++i;
		}

	}

}


public class hang {


	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Task());
		try {
			System.out.println("hehe");
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			System.out.println("Sleep interrupted");
		}

		System.out.println("finished");
		System.exit(0);
	}

}
