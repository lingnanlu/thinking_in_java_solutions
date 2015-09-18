
public class p12_5 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int i = 0;
		while(true){
			try{
				if(i != 5){
					throw new Exception("i = " + i);
				}
			} catch(Exception e){
				System.out.println(e);
				i++;
			}
		}
	}

}
