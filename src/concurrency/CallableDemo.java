package concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String>{

	
	private int id;
	public TaskWithResult(int id){
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		// TODO 自动生成的方法存根
		return "result of TaskWithResult" + id;
	}
	
}
public class CallableDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ExecutorService exec = Executors.newCachedThreadPool();
		
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		
		for(int i = 0; i < 10; i++){
			results.add(exec.submit(new TaskWithResult(i)));
		}
		
		for(Future<String> result : results){
			try {
				System.out.println(result.get());
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally{
				
				//不再接受新任务的提交
				exec.shutdown();
			}
		}
	}

}
