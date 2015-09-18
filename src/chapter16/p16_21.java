package chapter16;

import java.util.Arrays;
import java.util.Comparator;

import io.lingnanlu.github.*;


//为什么将这个类放到一个单独的文件中会出现
//无法推断类型变量的错误？
class BerylliumSphereComparator implements Comparator<BerylliumSphere>{

	@Override
	public int compare(BerylliumSphere o1, BerylliumSphere o2) {
		// TODO 自动生成的方法存根
		return o1.id > o2.id ? -1 : (o1.id == o2.id ? 0 : 1);
	}
	
}

public class p16_21 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		BerylliumSphere[] a = Generated.array(new BerylliumSphere[10], BerylliumSphere.generator()) ;
		
		System.out.println(Arrays.toString(a));
		System.out.println(a.getClass().getSimpleName());
		Arrays.sort(a, new BerylliumSphereComparator());
		
		System.out.println(Arrays.toString(a));
		

		
		
	}

}
