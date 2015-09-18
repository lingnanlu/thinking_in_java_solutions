package concurrency;

import static io.lingnanlu.github.Print.print;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED
	}
	
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) {
		id = idn;
	}
	
	public void butter() {
		status = Status.BUTTERED;
	}
	
	public void jam() {
		status = Status.JAMMED;
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

class ToastQueue extends LinkedBlockingDeque<Toast> {}

class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue tq) {
		toastQueue = tq;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100);
				Toast t = new Toast(count++);
				print(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			print("Toaster interrupted");
		}
		
		print("Toaster off");
	}
}

class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;
	
	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(200);
				Toast t = dryQueue.take();
				t.butter();
				print(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			print("Butterer interrupted");
		}
		
		print("Butterer off");
	}
	
	
}

class Jammer implements Runnable {
	private ToastQueue butteredQueue, finishedQueue;
	
	public Jammer(ToastQueue buttered, ToastQueue finished) {
		butteredQueue = buttered;
		finishedQueue = finished;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(300);
				Toast t = butteredQueue.take();
				t.jam();
				print(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			print("Jammer interrupted");
		}
		
		print("Jammer off");
	}
	
	
}

class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;
	
	public Eater(ToastQueue finished) {
		finishedQueue = finished;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(400);
				Toast t = finishedQueue.take();
				//Verify that the toast is coming in order, and that all pieces are getting jammed:
				if(t.getId() != counter++ ||
						t.getStatus() != Toast.Status.JAMMED){
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

public class ToastOMatic {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		ToastQueue dryQueue = new ToastQueue(),
				butteredQueue = new ToastQueue(),
				finishedQueue = new ToastQueue();
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
