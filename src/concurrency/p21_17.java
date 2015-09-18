package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static io.lingnanlu.github.Print.*;

public class p21_17 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}



class Count{
	private int count = 0;
	private Random rand = new Random(47);
	
	public synchronized int increment(){
		int temp = count;
		if(rand.nextBoolean())
			Thread.yield();
		return (count = ++temp);
	}
	
	
	public synchronized int value(){
		return count;
	}
}

class Entrance implements Runnable{
	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	
	private int number = 0;
	private final int id;
	private static volatile boolean canceled = false;
	public static void cancel(){
		canceled = true;
	}
	
	public Entrance(int id) {
		// TODO 自动生成的构造函数存根
		this.id = id;
		entrances.add(this);
	}
	
	
/*	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(!canceled){
			synchronized (this) {
				++number;
			}
			print(this + " Total: " + count.increment());
			try{
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e){
				print("sleep interrupted");
			}
		}
		
		print("Stopping " + this);
	}
	
	public synchronized int getValue(){
		return number;
	}*/
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(!canceled){
			++number;	
			print(this + " Total: " + count.increment());
			try{
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e){
				print("sleep interrupted");
			}
		}
		
		print("Stopping " + this);
	}
	
	public int getValue(){
		return number;
	}
	
	public String toString(){
		return "Entrance " + id + ": " + getValue();
	}
	
	public static int getTotalCount(){
		return count.value();
	}
	
	public static int sumEntrances(){
		int sum = 0;
		for(Entrance entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

public class OrnamentalGarden {

	public static void main(String[] args) throws Exception {
		
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 5; i++){
			exec.execute(new Entrance(i));
			
		}
		
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS)){
			print("Some tasks were not terminated!");
		}
		
		print("Total: " + Entrance.getTotalCount());
		print("Sum of Entrances: " + Entrance.sumEntrances());
	}

}

