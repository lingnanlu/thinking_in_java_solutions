package concurrency;



public class p21_1 implements Runnable{

	
	public p21_1(){
		System.out.println("constructing p21_1");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		for(int i = 0; i < 5; i++){
			new Thread(new p21_1()).start();
		}
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		for(int i = 0; i < 3; i++){
			System.out.println("Hello world");
			Thread.yield();
		}
		
		System.out.println("p21_1 task complete");
		return;
	}

}
