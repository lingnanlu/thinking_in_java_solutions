package concurrency;
import java.util.concurrent.*;
public class SimpleDeamons implements Runnable{

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		for(int i = 0; i < 10; i++){
			Thread daemon = new Thread(new SimpleDeamons());
			daemon.setDaemon(true);
			daemon.start();
		}
		
		System.out.println("All daemons started");
		//TimeUnit.MILLISECONDS.sleep(175);
	}

}
