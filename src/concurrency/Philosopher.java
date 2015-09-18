package concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.lingnanlu.github.Print.*;

public class Philosopher implements Runnable {

	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);
	
	public Philosopher(Chopstick left, Chopstick right, int ident, int ponder) {
		this.left = left;
		this.right = right;
		id = ident;
		ponderFactor = ponder;
		// TODO 自动生成的构造函数存根
	}
	
	private void pause() throws InterruptedException {
		if(ponderFactor == 0)
			return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()){
				print(this + " " + "thinking");
				pause();
				print(this + " " + "grabbing right");
				right.take();
				print(this + " " + "grabbing left");
				left.take();
				print(this + " " + "eating");
				pause();
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			print(this + " " + "exiting via interrupt");
		}
	}
	
	public String toString() {
		return "Philosopher " + id;
	}

}
