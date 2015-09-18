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
		// TODO 自动生成的方法存根
		List<String> sl = makeList("ABCDFEFSAF".split(""));
		System.out.print(sl);
	}

}
