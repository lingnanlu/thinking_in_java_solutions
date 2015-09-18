	package concurrency;
	
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;
	import java.util.concurrent.ExecutorService;
	import java.util.concurrent.Executors;
	import java.util.concurrent.TimeUnit;
	import java.util.concurrent.atomic.AtomicInteger;
	
	
	class Pair{
		private int x, y;
		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public Pair(){
			this(0, 0);
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public void incrementX(){
			x++;
		}
		
		public void incrementY(){
			y++;
		}
		
		public String toString(){
			return "x: " + x + ", y: " + y;
		}
		
		public class PairValuesNotEqualException extends RuntimeException{
			public PairValuesNotEqualException(){
				super("Pair values not equal: " + Pair.this);
			}
		}
		
		public void checkState(){
			if(x != y)
				throw new PairValuesNotEqualException();
		}
	}
	
	
	abstract class PairManager{
		AtomicInteger checkCounter = new AtomicInteger(0);
		protected Pair p = new Pair();
		private List<Pair> storage = 
				Collections.synchronizedList(new ArrayList<Pair>());
		
		public synchronized Pair getPair(){
			return new Pair(p.getX(), p.getY());
		}
		
		protected void store(Pair p){
			storage.add(p);
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
		public abstract void increment();
	
	}
	
	class PairManager1 extends PairManager{
	
		@Override
		public synchronized void increment() {
			// TODO 自动生成的方法存根
			p.incrementX();
			p.incrementY();
			store(getPair());
		}
		
	}
	
	class PairManager2 extends PairManager{
	
		@Override
		public void increment() {
			// TODO 自动生成的方法存根
			Pair temp;
			synchronized(this){
				p.incrementX();
				p.incrementY();
				temp = getPair();
			}
			
			store(temp);
		}
		
	}
	
	class PairManipulator implements Runnable{
		private PairManager pm;
		private String name;
		public PairManipulator(PairManager pm, String name){
			this.pm = pm;
			this.name = name;
		}
		
		
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true){
				Thread.yield();
				//System.out.println(name);
				pm.increment();
				
			}
				
		}
		
		public String toString(){
			return "Pair: " + pm.getPair() + " checkCounter  = " + pm.checkCounter.get();
		}
		
	}

	class PairChecker implements Runnable{
		private PairManager pm;
		private String name;
		public PairChecker(PairManager pm, String name){
			this.pm = pm;
			this.name = name;
		}
		
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true){
				Thread.yield();
				//System.out.println(name);
				pm.checkCounter.incrementAndGet();
				pm.getPair().checkState();
			}
		}
	}
	
	public class CriticalSection {
	
		static void testApproaches(PairManager pman1, PairManager pman2){
			ExecutorService exec = Executors.newCachedThreadPool();
			PairManipulator
				pm1 = new PairManipulator(pman1, "PairManipulator 1"),
				pm2 = new PairManipulator(pman2, "PairManipulator 2");
			
			PairChecker
				pcheck1 = new PairChecker(pman1, "PairChecker 1"),
				pcheck2 = new PairChecker(pman2, "PairChecker 2");
			
			
			exec.execute(pm1);
			exec.execute(pm2);
			exec.execute(pcheck1);
			exec.execute(pcheck2);
			
			
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.println("hehe");
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				System.out.println("Sleep interrupted");
			}
			
			System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
			//System.out.flush();
			System.exit(0);
		}
		
		public static void main(String[] args) {
			// TODO 自动生成的方法存根
			
			PairManager
				pman1 = new PairManager1(),
				pman2 = new PairManager2();
			
			testApproaches(pman1, pman2);
			
		}
	
	}
	