package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		throw new RuntimeException();
	}

}
