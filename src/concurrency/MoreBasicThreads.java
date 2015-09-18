package concurrency;

public class MoreBasicThreads {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		for(int i = 0; i < 5; i++)
			new Thread(new LiftOff()).start();
		System.out.println("Waiting for LiftOff");
	}

}
