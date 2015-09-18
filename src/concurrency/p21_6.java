package concurrency;
import java.util.Random;
import java.util.concurrent.*;
public class p21_6 implements Runnable{

	public static Random rand = new Random(47);
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new p21_6());
		}
		
		exec.shutdown();
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		int sleepingTime = rand.nextInt(10) + 1;
		try {
			TimeUnit.SECONDS.sleep(sleepingTime);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		System.out.println(sleepingTime);
	}

}
