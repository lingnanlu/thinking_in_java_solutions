package containers;

interface SListIterator<T>{
	boolean add(T item);
	T remove();
}

class SList<T>{
	private T item;
	private SList<T> next;
	
	
	public SList(){
		item = null;
		next = null;
			
	}
	
	public SListIterator iterator(){
		return new SListIterator<T>(){
			private SList<T> last = SList.this;
			private SList<T> first = SList.this;
			@Override
			public boolean add(T item) {
				// TODO �Զ����ɵķ������
				SList<T> a = new SList<T>();
				a.item = item;
				a.next = null;
				last.next = a;
				last = a;
				return true;
			}
			@Override
			public T remove() {
				// TODO �Զ����ɵķ������
				if (first.next != null){
					SList<T> result = first.next;
					first.next = result.next;
					return result.item;
				}
				return null;
			}
			
		};
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		SList<T> cursor = next;
		while(cursor != null){
			sb.append(cursor.item + ", ");
			cursor = cursor.next;
		}
		
		sb.delete(sb.length() - 2, sb.length());
		
		sb.append("]");
		
		return sb.toString();
	}
}
public class p17_8 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		SList<String> sl = new SList<String>();
		SListIterator<String> sliter = sl.iterator();
		sliter.add("If");
		sliter.add("the");
		sliter.add("order");
		
		System.out.println(sl);
		
		System.out.println(sliter.remove());
		System.out.println(sliter.remove());
		
		System.out.println(sl);
		
		
		sliter.add("hehe");
		sliter.add("heihei");
		
		System.out.println(sliter.remove());
		
		System.out.println(sl);
	}

}
