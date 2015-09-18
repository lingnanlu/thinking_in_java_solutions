package concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

public class p21_5 implements Callable<Integer>{

	private int n;
	public p21_5(int n){
		this.n = n;
	}
	
	private int fib(int n){
		if (n < 2)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		
		for(int i = 0; i < 9; i++){
			results.add(exec.submit(new p21_5(i)));
		}
		
		for(Future<Integer> result : results){
			System.out.println(result.get());
		}
		
		exec.shutdown();
	}

	@Override
	public Integer call() throws Exception {
		// TODO 自动生成的方法存根
		int sum = 0;
		for(int i = 0; i < n; i++){
			//System.out.print(fib(i) + " ");
			sum += fib(i);
		}
		return sum;
	}

}
