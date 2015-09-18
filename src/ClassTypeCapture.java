import java.util.*;


public class ClassTypeCapture {
	private Map<String, Class<?>> type = new HashMap<String, Class<?>>();
	
	public void addType(String typename, Class<?> kind){
		type.put(typename, kind);
	}
	
	public void createNew(String typename){
		Class<?> kind = type.get(typename);
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
