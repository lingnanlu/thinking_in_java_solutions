
abstract class Shape {
	boolean flag = false;
  void draw() { System.out.println(this + ".draw()"); }
  void setflag (){flag = true;}
  abstract public String toString();
}

class Circle extends Shape {
  public String toString() { return "Circle " + flag; }
}

class Square extends Shape {
  public String toString() { return "Square " + flag; }
}

class Triangle extends Shape {
  public String toString() { return "Triangle " + flag; }
}

class Rhomboid extends Shape {
	public String toString() { return "Rhomboid " + flag; }
}

public class p14_6 {
	public static void pickoutSquare(Shape[] shapes){
		for(Shape shape : shapes){
			if ( shape instanceof Triangle){
				shape.setflag();
			}
		}
	}
  public static void main(String[] args) {
	  Shape[] shapes = {new Circle(), new Square(), new Triangle(), new Square(),
			  new Rhomboid(), new Triangle()};
	  for(Shape shape : shapes){
		  System.out.print(shape);
	  }
  	
  	
	  pickoutSquare(shapes);
	  System.out.println();
	  for(Shape shape : shapes){
		  System.out.print(shape);
	  }
	  
  }
}
