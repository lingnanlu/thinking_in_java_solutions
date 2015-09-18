package concurrency;

import static io.lingnanlu.github.Print.print;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

class Toast2 {
	public enum Status {
		DRY, BUTTERED, JELLYED
	}
	
	private Status status = Status.DRY;
	private final int id;
	public Toast2(int idn) {
		id = idn;
	}
	
	public void butter() {
		status = Status.BUTTERED;
	}
	
	public void jelly() {
		status = Status.JELLYED;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue2 extends LinkedBlockingDeque<Toast2> {}

class Toaster implements Runnable {
	private ToastQueue2 toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue2 tq) {
		toastQueue = tq;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100);
				Toast2 t = new Toast2(count++);
//				print(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			print("Toaster interrupted");
		}
		
		print("Toaster off");
	}
}

class Butterer implements Runnable {
	private ToastQueue2 dryQueue, finishedQueue;
	
	public Butterer(ToastQueue2 dry, ToastQueue2 finished) {
		dryQueue = dry;
		finishedQueue = finished;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(200);
				Toast2 t = dryQueue.take();
				t.butter();
//				print(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			print("Butterer interrupted");
		}
		
		print("Butterer off");
	}
	
	
}

class Jelly implements Runnable {
	private ToastQueue2 dryQueue, finishedQueue;
	
	public Jelly(ToastQueue2 dry, ToastQueue2 finished) {
		dryQueue = dry;
		finishedQueue = finished;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(300);
				Toast2 t = dryQueue.take();
				t.jelly();
//				print(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			print("Jammer interrupted");
		}
		
		print("Jammer off");
	}
	
	
}

class Eater implements Runnable {
	private ToastQueue2 finishedQueue;
	private int counter = 0;
	
	public Eater(ToastQueue2 finished) {
		finishedQueue = finished;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(400);
				Toast2 t = finishedQueue.take();
				//Verify that the toast is coming in order, and that all pieces are getting jammed:
				if(t.getId() != counter++ ){
					print(">>>> Error: " + t);
					System.exit(1);
				} else{
					print("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			print("Eater interrupted");
		}
		print("Eater off");
	}
}

public class p21_29 {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		ToastQueue2 dryQueue = new ToastQueue2(),
				finishedQueue = new ToastQueue2();
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, finishedQueue));
		exec.execute(new Jelly(dryQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
