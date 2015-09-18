import java.util.*;

import static io.lingnanlu.github.Print.*;
class Shape {
	  public void draw() {}
	  public void erase() {}
	}

class Circle extends Shape {
	  public void draw() { print("Circle.draw()"); }
	  public void erase() { print("Circle.erase()"); }
}


class Square extends Shape {
  public void draw() { print("Square.draw()"); }
  public void erase() { print("Square.erase()"); }
} ///:~


class Triangle extends Shape {
  public void draw() { print("Triangle.draw()"); }
  public void erase() { print("Triangle.erase()"); }
} ///:~

class RandomShapeGenerator implements Iterable<Shape>{
	  private Random rand = new Random(47);
	  int max = 0;
	  RandomShapeGenerator(int max){
		  this.max = max;
	  }
	  public Shape next() {
	    switch(rand.nextInt(3)) {
	      default:
	      case 0: return new Circle();
	      case 1: return new Square();
	      case 2: return new Triangle();
	    }
	  }
	@Override
	public Iterator<Shape> iterator() {
		// TODO �Զ����ɵķ������
		return new Iterator<Shape>(){
			
			//i��ʾ�����ɵ�Shape�ĸ���
			int count = 0;
			@Override
			public boolean hasNext() {
				// TODO �Զ����ɵķ������
				return count != max;
			}

			@Override
			public Shape next() {
				// TODO �Զ����ɵķ������
				count++;
				return RandomShapeGenerator.this.next();
			}
			
		};
	}
} 
public class p11_31 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		RandomShapeGenerator gen = new RandomShapeGenerator(10);
		for(Shape shape : gen){
			shape.draw();
		}
	}

}
