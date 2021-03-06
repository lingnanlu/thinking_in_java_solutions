package generics;

//: generics/TupleTest2.java
import net.mindview.util.*;
import static net.mindview.util.Tuple.*;


class Vehicle{
	
}

class Amphibian{
	
}


public class TupleTest2 {
  static TwoTuple<String,Integer> f() {
    return tuple("hi", 47);
  }
  static TwoTuple f2() { return tuple("hi", 47); }
  static ThreeTuple<Amphibian,String,Integer> g() {
    return tuple(new Amphibian(), "hi", 47);
  }
  static
  FourTuple<Vehicle,Amphibian,String,Integer> h() {
    return tuple(new Vehicle(), new Amphibian(), "hi", 47);
  }
  static
  FiveTuple<Vehicle,Amphibian,String,Integer,Double> k() {
    return tuple(new Vehicle(), new Amphibian(),
      "hi", 47, 11.1);
  }
  
  static
  SixTuple<Vehicle, Amphibian, String, Integer, Double, Character> p() {
	  return tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1, 'c');
  }
  public static void main(String[] args) {
    TwoTuple<String,Integer> ttsi = f();
    TwoTuple<Double,Integer> ttsi2 = f2();
    System.out.println(f());
    System.out.println(ttsi2);	//此时依然正确，为什么？
    System.out.println(g());
    System.out.println(h());
    System.out.println(k());
    System.out.println(p());
  }
} /* Output: (80% match)
(hi, 47)
(hi, 47)
(Amphibian@7d772e, hi, 47)
(Vehicle@757aef, Amphibian@d9f9c3, hi, 47)
(Vehicle@1a46e30, Amphibian@3e25a5, hi, 47, 11.1)
*///:~
