package concurrency;

public class p21_2 implements Runnable {

	private static int i = 0;
	private final int id = i++;
	private int n;
	private int count = 0;
	public p21_2(int n){
		this.n = n;
	}
	
	
	public Integer next(){
		return fib(count++);
	}
	
	private int fib(int n){
		if(n < 2)
			return 1;
		return fib(n - 2) + fib(n - 1);
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		for(int i = 0; i < 5; i++){
			new Thread(new p21_2(i)).start();
		}
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		for(int i = 0; i < n; i++)
			System.out.print(next() + "(" + id + ")" + " ");
	}

}
