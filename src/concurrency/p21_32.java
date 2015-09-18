package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static io.lingnanlu.github.Print.*;

/*
 * 与OrnamentalGarden的不同
 * 每个Entrance只统计自己的人数，并不知道总的人数
 */

class Entrance implements Runnable{
	public static List<Entrance> entrances = new ArrayList<Entrance>();
	
	private final CountDownLatch latch;
	private int number = 0;
	private final int id;
	private static volatile boolean canceled = false;
	public static void cancel(){
		canceled = true;
	}
	
	public Entrance(int id, CountDownLatch latch) {
		// TODO 自动生成的构造函数存根
		this.id = id;
		this.latch = latch;
		entrances.add(this);
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(!canceled){
			++number;	
			try{
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e){
				print("sleep interrupted");
			}
		}
		
		latch.countDown();
		print(this + " finished");
	}
	
	public int getValue(){
		return number;
	}
	
	public String toString(){
		return "Entrance " + id + ": " + getValue();
	}
	
	public static int sumEntrances(){
		int sum = 0;
		for(Entrance entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

class SumTask implements Runnable {

	private int sum;
	private final CountDownLatch latch;
	public SumTask(CountDownLatch latch) {
		// TODO 自动生成的构造函数存根
		this.latch = latch;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			latch.await();
			for(Entrance entrance : Entrance.entrances){
				sum += entrance.getValue();
			}
			//print("total number == " + sum);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public int totalNumber(){
		return sum;
	}
}


public class p21_32 {

	private static int SIZE = 20;
	public static void main(String[] args) throws Exception {
		
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for(int i = 0; i < SIZE; i++){
			exec.execute(new Entrance(i, latch));
		}
		
		SumTask sumTask = new SumTask(latch);
		exec.execute(sumTask);
		
		
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS)){
			print("Some tasks were not terminated!");
		}
		
		
		print("Sum of Entrances: " + sumTask.totalNumber());
	}

}
