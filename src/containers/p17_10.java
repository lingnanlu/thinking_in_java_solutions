package containers;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;


class MySortedSet<T> implements SortedSet<T>{
	
	private LinkedList<T> list = new LinkedList<T>();
	@Override
	public int size() {
		// TODO �Զ����ɵķ������
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO �Զ����ɵķ������
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO �Զ����ɵķ������
		return list.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO �Զ����ɵķ������
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO �Զ����ɵķ������
		return list.toArray(a);
	}

	@Override
	public boolean add(T e) {
		// TODO �Զ����ɵķ������
		if (contains(e)){
			return false;
		} else{
			return list.add(e);
		}
		
	}

	@Override
	public boolean remove(Object o) {
		// TODO �Զ����ɵķ������
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO �Զ����ɵķ������
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO �Զ����ɵķ������
		return list.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO �Զ����ɵķ������
		return list.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public void clear() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Comparator<? super T> comparator() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public SortedSet<T> headSet(T toElement) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public SortedSet<T> tailSet(T fromElement) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public T first() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public T last() {
		// TODO �Զ����ɵķ������
		return null;
	}
	
}
public class p17_10 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

}
