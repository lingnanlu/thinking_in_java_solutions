package io.lingnanlu.github;

import java.util.AbstractList;

public class CountingIntegerList extends AbstractList<Integer>{

	private int size;
	public CountingIntegerList(int size){
		this.size = size < 0 ? 0 : size;
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.print(new CountingIntegerList(30));
	}

	@Override
	public Integer get(int index) {
		// TODO �Զ����ɵķ������
		return Integer.valueOf(index);
	}

	@Override
	public int size() {
		// TODO �Զ����ɵķ������
		return size;
	}

}
