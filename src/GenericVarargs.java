import java.util.ArrayList;
import java.util.List;


public class GenericVarargs {
	public static <T> List<T> makeList(T... args){
		List<T> list = new ArrayList<T>();
		for(T item : args){
			list.add(item);
		}
		
		return list;
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		List<String> sl = makeList("ABCDFEFSAF".split(""));
		System.out.print(sl);
	}

}
