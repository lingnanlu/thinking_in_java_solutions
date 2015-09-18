package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try{
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (RuntimeException ue){
			System.out.println("Exception has been handled!");
		}
	
	}

}
