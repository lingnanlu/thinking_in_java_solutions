package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static io.lingnanlu.github.Print.*;

class SleepBlocked implements Runnable{

	@Override
	public void run() {
		
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			
			print("InterruptedException");
		}
		
		print("Exiting SleepBlocked.run()");
	}
	
}


class IOBlocked implements Runnable{
	private InputStream in;

	@Override
	public void run() {
		
		try {
			print("Waiting for read():");
			in.read();
		} catch (IOException e) {
			print("reach here");
			
			if(Thread.currentThread().isInterrupted()){
				print("Interrupted from blocked I/O");
			} else{
				throw new RuntimeException(e);
			}
		} 
		print("Exiting IOBlocked.run()");
	}
	public IOBlocked(InputStream is){
		in = is;
	}
	
	
}
public class Interrupting {
	
	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	static void test(Runnable r) throws InterruptedException{
		
		Future<?> f = exec.submit(r);
		
		TimeUnit.MILLISECONDS.sleep(100);
		
		print("Interrupting " + r.getClass().getName());
		f.cancel(true);
		print("Interrupt sent to " + r.getClass().getName());
	}

	public static void main(String[] args) throws Exception {
		
		//test(new SleepBlocked());
		test(new IOBlocked(System.in));
		TimeUnit.SECONDS.sleep(3);
		print("Aborting with system.exit(0)");
		//System.exit(0);
	}

}
