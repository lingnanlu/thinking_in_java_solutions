
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

public class p14_5 {
	public static void rotate(Shape shape){
		if ( shape instanceof Circle ){
			System.out.println(shape + "can't rotate");
		} else {
			System.out.println("rotate " + shape);
		}
	}
  public static void main(String[] args) {
	  Shape shape = new Circle();
	  rotate(shape);
	  
  }
}