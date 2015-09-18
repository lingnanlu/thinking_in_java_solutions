package concurrency;

import java.util.concurrent.*;

public class SleepingTask extends LiftOff{

	public void run(){
		while(countDown-- > 0){
			System.out.print(status());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new SleepingTask());
		}
		
		exec.shutdown();
	}

}
