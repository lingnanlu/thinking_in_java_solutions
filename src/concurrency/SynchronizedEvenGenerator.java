package concurrency;

public class SynchronizedEvenGenerator extends IntGenerator{

	private int currentEvenValue = 0;
	
	public synchronized int next(){
		++currentEvenValue;
		Thread t = Thread.currentThread();
		t.yield();
		++currentEvenValue;
		return currentEvenValue;
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		EvenChecker.test(new SynchronizedEvenGenerator());
	}

}
