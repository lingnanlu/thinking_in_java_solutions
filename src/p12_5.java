
public class p12_5 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
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
