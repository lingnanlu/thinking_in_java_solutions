package concurrency;

public class EvenGenerator extends IntGenerator{

	private int currentEvenValue = 0;
	@Override
	public int next() {
		// TODO 自动生成的方法存根
		++currentEvenValue;
		Thread t = Thread.currentThread();
		t.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args){
		EvenChecker.test(new EvenGenerator());
	}
}
