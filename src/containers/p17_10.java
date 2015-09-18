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
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO 自动生成的方法存根
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO 自动生成的方法存根
		return list.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO 自动生成的方法存根
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO 自动生成的方法存根
		return list.toArray(a);
	}

	@Override
	public boolean add(T e) {
		// TODO 自动生成的方法存根
		if (contains(e)){
			return false;
		} else{
			return list.add(e);
		}
		
	}

	@Override
	public boolean remove(Object o) {
		// TODO 自动生成的方法存根
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO 自动生成的方法存根
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO 自动生成的方法存根
		return list.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO 自动生成的方法存根
		return list.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void clear() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Comparator<? super T> comparator() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public SortedSet<T> headSet(T toElement) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public SortedSet<T> tailSet(T fromElement) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public T first() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public T last() {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
public class p17_10 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
