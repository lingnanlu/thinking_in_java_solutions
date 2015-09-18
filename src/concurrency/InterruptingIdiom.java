package concurrency;

import java.util.concurrent.*;

import static io.lingnanlu.github.Print.*;

class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int ident){
		id = ident;
		print("NeedsCleanup " + id);
	}
	
	public void cleanup(){
		print("Cleaning up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d = 0.0;

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while (!Thread.interrupted()) {
				// point 1
				NeedsCleanup n1 = new NeedsCleanup(1);
				// Start try-finally immediately after definition of n1, to
				// guarantee proper cleanup of n1;
				try {
					print("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					// point2
					NeedsCleanup n2 = new NeedsCleanup(2);
					// Guarantee proper cleanup of n2;
					try {
						print("Calculating");
						for(int i = 1; i < 2500000; i++)
							d = d + (Math.PI + Math.E) / d;
						print("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
		} catch (InterruptedException e) {
			print("Exiting via InterruptedException");
		}

	}
}

public class InterruptingIdiom {

	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		// TODO 自动生成的方法存根
		if (args.length != 1) {
			print("usage: java InterrputingIdiom delay-in-mS");
			System.exit(1);
		}
		
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		t.interrupt();
	}

}
