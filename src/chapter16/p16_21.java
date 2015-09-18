package chapter16;

import java.util.Arrays;
import java.util.Comparator;

import io.lingnanlu.github.*;


//Ϊʲô�������ŵ�һ���������ļ��л����
//�޷��ƶ����ͱ����Ĵ���
class BerylliumSphereComparator implements Comparator<BerylliumSphere>{

	@Override
	public int compare(BerylliumSphere o1, BerylliumSphere o2) {
		// TODO �Զ����ɵķ������
		return o1.id > o2.id ? -1 : (o1.id == o2.id ? 0 : 1);
	}
	
}

public class p16_21 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		BerylliumSphere[] a = Generated.array(new BerylliumSphere[10], BerylliumSphere.generator()) ;
		
		System.out.println(Arrays.toString(a));
		System.out.println(a.getClass().getSimpleName());
		Arrays.sort(a, new BerylliumSphereComparator());
		
		System.out.println(Arrays.toString(a));
		

		
		
	}

}
