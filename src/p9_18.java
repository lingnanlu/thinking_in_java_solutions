interface Cycle{
	void show();
}

interface CycleFactory{
	Cycle getCycle();
}

class Unicycle implements Cycle{

	private Unicycle(){}
	@Override
	public void show() {
		// TODO 自动生成的方法存根
		System.out.println("Unicycle");
	}
	
	public static CycleFactory unicycleFactory = new CycleFactory(){

		@Override
		public Cycle getCycle() {
			// TODO 自动生成的方法存根
			return new Unicycle();
		}
		
	};
}

class Bicycle implements Cycle{

	private Bicycle(){}
	@Override
	public void show() {
		// TODO 自动生成的方法存根
		System.out.println("Bicycle");
	}
	
	public static CycleFactory bicycleFactory = new CycleFactory(){

		@Override
		public Cycle getCycle() {
			// TODO 自动生成的方法存根
			return new Bicycle();
		}
		
	};
}

class Tricycle implements Cycle{
	private Tricycle(){}
	@Override
	public void show() {
		// TODO 自动生成的方法存根
		System.out.println("Tricycle");
	}
	
	public static CycleFactory tricycleFactory = new CycleFactory(){

		@Override
		public Cycle getCycle() {
			// TODO 自动生成的方法存根
			return new Tricycle();
		}
		
	};
}


public class p9_18 {

	static void showCycle(CycleFactory factory){
		Cycle c = factory.getCycle();
		c.show();
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		showCycle(Bicycle.bicycleFactory);
		showCycle(Unicycle.unicycleFactory);
		showCycle(Tricycle.tricycleFactory);
	}

}
