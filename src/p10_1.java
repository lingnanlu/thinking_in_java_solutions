
public class p10_1 {
	private String str;
	p10_1(){
		str = "p10_1";
	}
	class Inner{
		private int i = 5;
		void foobar(){
			System.out.println("hehe");
		}
		public String toString(){
			return str;
		}
	}
	
	
	public int returninner(){
		return new Inner().i;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		p10_1 t = new p10_1();
		System.out.println(t.returninner());
	}

}
