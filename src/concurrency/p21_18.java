package concurrency;

import java.util.concurrent.TimeUnit;



public class p21_18 {

	static class NonTask{
		public void runlong() throws InterruptedException{
		
				TimeUnit.SECONDS.sleep(10);

		}
	}
	
	static class Task implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				//分配一些资源，如打开文件
				
				
				
				new NonTask().runlong();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				// 这里负责打印一些异常信息
				System.out.println("InterruptedException captured");
			} finally {
				// 这里负责做一些清理工作，如关闭打开的资源等等。
			}
		}

	}
	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		Thread t = new Thread(new Task());
		t.start();
		TimeUnit.SECONDS.sleep(3);
		t.interrupt();
	}

}
