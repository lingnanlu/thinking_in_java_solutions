package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class p21_39 {

	static final int N_ELEMENTS = 100000;
	static final int N_GENES = 30;
	static final int N_EVOLVERS = 50;
	static final AtomicInteger[][] GRID1 = new AtomicInteger[N_ELEMENTS][N_GENES];
	static final int[][] GRID2 = new int[N_ELEMENTS][N_GENES];
	static Random rand = new Random(47);

	static final ReentrantLock[] lock = new ReentrantLock[N_ELEMENTS];
	static final AtomicInteger counter1 = new AtomicInteger();
	static final AtomicInteger counter2 = new AtomicInteger();

	static class Evolver1 implements Runnable {
		public void run() {
			while (!Thread.interrupted()) {

				int element = rand.nextInt(N_ELEMENTS);
				for (int i = 0; i < N_GENES; i++) {
					int previous = element - 1;
					if (previous < 0) {
						previous = N_ELEMENTS - 1;
					}

					int next = element + 1;
					if (next >= N_ELEMENTS)
						next = 0;
					int oldvalue = GRID1[element][i].get();
					int newvalue = oldvalue + GRID1[previous][i].get()
							+ GRID1[next][i].get();

					newvalue /= 3;

					if (!GRID1[element][i].compareAndSet(oldvalue, newvalue)) {
						//System.out.println("Old value changed from " + oldvalue);
					}
				}

				counter1.incrementAndGet();
			}
		}
	}

	static class Evolver2 implements Runnable {

		@Override
		public void run() {

			while (!Thread.interrupted()) {

				int element = rand.nextInt(N_ELEMENTS);
				lock[element].lock();
				try{
					for (int i = 0; i < N_GENES; i++) {
						int previous = element - 1;
						if (previous < 0) {
							previous = N_ELEMENTS - 1;
						}

						int next = element + 1;
						if (next >= N_ELEMENTS)
							next = 0;
						int oldvalue = GRID2[element][i];
						int newvalue = oldvalue + GRID2[previous][i]
								+ GRID2[next][i];

						newvalue /= 3;
						GRID2[element][i] = newvalue;
					}
				} finally {
					lock[element].unlock();
				}
			
				counter2.incrementAndGet();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {

		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < N_ELEMENTS; i++)
			for (int j = 0; j < N_GENES; j++)
				GRID1[i][j] = new AtomicInteger(rand.nextInt(1000));
		
		for (int i = 0; i < N_ELEMENTS; i++)
			for (int j = 0; j < N_GENES; j++)
				GRID2[i][j] = rand.nextInt(1000);

		for(int i = 0; i < N_ELEMENTS; i++)
			lock[i] = new ReentrantLock();
		
		for (int i = 0; i < N_EVOLVERS; i++){
			exec.execute(new Evolver1());
			exec.execute(new Evolver2());
		}
			

		TimeUnit.SECONDS.sleep(5);

		exec.shutdownNow();
		
		System.out.println("Variant 1:" + counter1.get());
		
		/*exec = Executors.newCachedThreadPool();
		
		for (int i = 0; i < N_EVOLVERS; i++){
			exec.execute(new Evolver2());
		}
			

		TimeUnit.SECONDS.sleep(5);
		
		exec.shutdownNow();*/
		
		System.out.println("Variant 2:" + counter2.get());
	}

}
