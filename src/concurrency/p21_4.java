package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class p21_4 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		ExecutorService exec = Executors.newFixedThreadPool(1);
		
		for(int i = 0; i < 10; i++){
			exec.execute(new p21_2(i));
		}
		
		exec.shutdown();
		
		//System.out.println("----------------------------------");
	}

}
