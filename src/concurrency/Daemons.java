package concurrency;


import java.util.concurrent.*;

class DaemonSpawn implements Runnable{

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			Thread.yield();
		}
	}
	
}
public class Daemons implements Runnable{
	private Thread[] t = new Thread[10];
	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		Thread d = new Thread(new Daemons());
		d.setDaemon(true);
		d.start();
		System.out.print("d.isDaemon() = " + d.isDaemon() + ". ");
		TimeUnit.SECONDS.sleep(1);
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		for(int i = 0; i < t.length; i++){
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.print("DaemonSpawn " + i + " started , ");
		}
		
		for(int i = 0; i < t.length; i++){
			System.out.print("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		}
		while(true)
			Thread.yield();
	}

}
