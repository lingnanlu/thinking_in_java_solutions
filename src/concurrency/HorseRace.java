package concurrency;
import static io.lingnanlu.github.Print.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Horse implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private int strides = 0;
	private static Random rand = new Random(47);
	
	//为什么是static
	private static CyclicBarrier barrier;
	public Horse(CyclicBarrier barrier) {
		// TODO 自动生成的构造函数存根
		this.barrier = barrier;
	}
	
	public synchronized int getStrides() {
		return strides;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()){
				synchronized (this) {
					strides += rand.nextInt(3);
				}
				barrier.await();
			}
		} catch (InterruptedException e) {
			
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String toString() {
		return "Horse " + id + " ";
	}
	
	public String tracks() {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < getStrides(); i++){
			s.append("*");
		}
		
		s.append(id);
		return s.toString();
	}
	
}
public class HorseRace {

	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	
	private CyclicBarrier barrier;
	public HorseRace(int nHorses , final int pause) {
		// TODO 自动生成的构造函数存根
		barrier = new CyclicBarrier(nHorses, new Runnable(){

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				StringBuilder s = new StringBuilder();
				for(int i = 0; i < FINISH_LINE; i++)
					s.append("=");
				print(s);
				for(Horse horse :horses){
					print(horse.tracks());
				}
				
				for(Horse horse : horses){
					if(horse.getStrides() >= FINISH_LINE) {
						print(horse + "won!");
						exec.shutdownNow();
						return;
					}
				}
				
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					print("barrier-action sleep interrupted");
				}
			}
			
		});
		
		for(int i = 0; i < nHorses; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int nHorses = 7;
		int pause = 200;
		if(args.length > 0) {
			int n = new Integer(args[0]);
			nHorses = n > 0 ? n : nHorses;
		}
		
		if(args.length > 1) {
			int p = new Integer(args[1]);
			pause = p > -1 ? p : pause;
		}
		
		new HorseRace(nHorses, pause);
	}

}

