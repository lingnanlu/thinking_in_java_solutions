package concurrency;

import io.lingnanlu.github.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
class ExchangerProducer<T> implements Runnable {

	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	public ExchangerProducer(Exchanger<List<T>> exchg, 
			Generator<T> gen, List<T> holder) {
		// TODO 自动生成的构造函数存根
		
		exchanger = exchg;
		generator = gen;
		this.holder = holder;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				for(int i = 0; i < ExchangerDemo.size; i++){
					holder.add(generator.next());
				}
				
				holder = exchanger.exchange(holder);
			}
		} catch (InterruptedException e) {
			
		}
	}
	
}

class ExchangerConsumer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	private int i = 0;
	public ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
		// TODO 自动生成的构造函数存根
		
		exchanger = ex;
		this.holder = holder;
	}
	
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			while(!Thread.interrupted()) {
				holder = exchanger.exchange(holder);
				for(T x : holder) {
					value = x;
					//System.out.println(value);
					holder.remove(x);
				}
				 
				System.out.println("Consume finished " + i++);
			}
		} catch(InterruptedException e) {
			
		}
		
		System.out.println("Final value: " + value);
	}
	
	
}
public class ExchangerDemo {

	static int size = 10;
	static int delay = 5;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
		List<Fat> 
			producerList = new CopyOnWriteArrayList<Fat>(),
			consumerList = new CopyOnWriteArrayList<Fat>();
		
		exec.execute(new ExchangerProducer<Fat>(xc, BasicGenerator.create(Fat.class), producerList));
		exec.execute(new ExchangerConsumer<Fat>(xc, consumerList));
		
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}

}
