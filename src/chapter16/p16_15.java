package chapter16;

//: arrays/ContainerComparison.java
import java.util.*;
import static net.mindview.util.Print.*;
import io.lingnanlu.github.*;

class BerylliumSphereGenerator implements Generator<BerylliumSphere>{

	@Override
	public BerylliumSphere next() {
		// TODO 自动生成的方法存根
		return new BerylliumSphere();
	}
	
}
public class p16_15 {
  public static void main(String[] args) {
    BerylliumSphere[] spheres = new BerylliumSphere[10];
    BerylliumSphereGenerator bg = new BerylliumSphereGenerator();
    for(int i = 0; i < 5; i++)
      spheres[i] = bg.next();
    print(Arrays.toString(spheres));
    print(spheres[4]);

    List<BerylliumSphere> sphereList =
      new ArrayList<BerylliumSphere>();
    for(int i = 0; i < 5; i++)
      sphereList.add(bg.next());
    print(sphereList);
    print(sphereList.get(4));

  }
} /* Output:
[Sphere 0, Sphere 1, Sphere 2, Sphere 3, Sphere 4, null, null, null, null, null]
Sphere 4
[Sphere 5, Sphere 6, Sphere 7, Sphere 8, Sphere 9]
Sphere 9
[0, 1, 2, 3, 4, 5]
4
[0, 1, 2, 3, 4, 5, 97]
4
*///:~
