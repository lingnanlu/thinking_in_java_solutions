package concurrency;
import static io.lingnanlu.github.Print.*;

class Sleeper extends Thread{
	private int duration;
	public Sleeper(String name, int sleepTime){
		super(name);
		duration = sleepTime;
		start();
	}
	
	public void run(){
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			print(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
			return ;
		}
		
		print(getName() + " has awakened");
	}
}

class Joiner extends Thread{
	private Sleeper sleeper;
	
	public Joiner(String name, Sleeper sleeper){
		super(name);
		this.sleeper = sleeper;
		start();
	}
	
	public void run(){
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			print("Interrupted");
		}
		print(getName() + " join completed");
	}
}
public class Joining {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Sleeper
			sleepy = new Sleeper("Sleepy", 1500),
			grumpy = new Sleeper("Grumpy", 1500);
		
		Joiner
			dopey = new Joiner("Doepy", sleepy),
			doc = new Joiner("Doc", grumpy);
		
		grumpy.interrupt();
	}

}
