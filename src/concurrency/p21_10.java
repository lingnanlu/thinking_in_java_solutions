package concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

public class p21_10 implements Callable<Integer>{

	private int n;
	public p21_10(){
	}
	
	
	private int fib(int n){
		if (n < 2)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}
	private ExecutorService exec = Executors.newCachedThreadPool();
	
	public Future<Integer> runTask(int n){
		
		
		/*
		 * 这样做有风险，假如所有的线程都使用同一个p21_10的n时，
		 * 当一个线程没运行完，就切换到另一个线程，而此时，n会改变，
		 * 再切换回来时，n就变了，先前线程运行的结果就会出错。
		 * 所以，n要局部于task内部
		 */
		//this.n = n;
		
		
		//define the task that the thread will exec.
		/*Callable<Integer> task = new Callable<Integer>(){

			private int count = n;
			@Override
			public Integer call() throws Exception {
				int sum = 0;
				for(int i = 0; i < n; i++){
					sum += fib(i);
				}
				return sum;
			}
			
		};
		
		return exec.submit(task);*/
		
		return exec.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				// TODO 自动生成的方法存根
				int sum = 0;
				for(int i = 0; i < n; i++){
					sum += fib(i);
				}
				return sum;
			}
			
		});
		
		
		//how to shutdown exec?
		
		/*
		 * 打印出来的结果是
		 * 1
		 * 4
		 * 4
		 * 20
		 * 20
		 * 20
		 * 54
		 * 54
		 * 54
		 * 这所以结果出错，是因为这里，exec会为每一个新任务分配
		 * 一个线程，但是，这里只有一个任务，只是在一个线程中运行
		 * 所以结果出错。
		 * 
		 * 参考p21_5
		 * 	results.add(exec.submit(new p21_5(i)));
		 * 每次都提交一个新任务
		 */
		//return exec.submit(this);
	}
	
	//shutdown exec
	public void shutdown(){
		exec.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		p21_10 task = new p21_10();
		for(int i = 0; i < 9; i++){
			results.add(task.runTask(i));
		}
		
		for(Future<Integer> result : results){
			System.out.println(result.get());
		}
		
		//TimeUnit.SECONDS.sleep(5);
		task.shutdown();

	}

	@Override
	public Integer call() throws Exception {
			int sum = 0;
			for(int i = 0; i < n; i++){
				sum += fib(i);
			}
			return sum;
	}

}
