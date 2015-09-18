import java.util.*;

abstract class Shape {
  void draw() { System.out.println(this + ".draw()"); }
  abstract public String toString();
}

class Circle extends Shape {
  public String toString() { return "Circle"; }
}

class Square extends Shape {
  public String toString() { return "Square"; }
}

class Triangle extends Shape {
  public String toString() { return "Triangle"; }
}

class Rhomboid extends Shape {
	public String toString() { return "Rhomboid"; }
}

public class p14_3 {
  public static void main(String[] args) {
	  Shape shape = new Rhomboid();
	  if (shape instanceof Circle){
		  Circle cir = (Circle)shape;
	  } else {
		  System.out.println("(shape is not a Circle");
	  }
	  
  }
}
