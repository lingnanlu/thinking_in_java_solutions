import java.util.ArrayList;
import java.util.List;

class CountedInteger{
	private static int counter;
	private final long id = counter++;
	public String toString(){
		return Long.toString(id);
	}
}
public class FilledList<T> {
	private Class<T> type;
	public FilledList(Class<T> type){this.type = type;}
	public List<T> create(int nElements){
		List<T> result = new ArrayList<T>();
			try {
				for (int i = 0; i < nElements; ++i){
					result.add(type.newInstance());
				}
			} catch (InstantiationException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return result;
		}
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		FilledList<CountedInteger> fc = new FilledList<CountedInteger>(CountedInteger.class);
		List<CountedInteger> list = fc.create(16);
		System.out.println(list);
	}

}
