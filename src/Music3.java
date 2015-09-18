//: polymorphism/music3/Music3.java
// An extensible program.
import static io.lingnanlu.github.Print.*;

import java.util.Random;

enum Note{
	MIDDLE_C, C_SHARP, B_FLAT;
}

class Instrument {
  void play(Note n) { print("Instrument.play() " + n); }
  public String toString() { return "Instrument"; }
  void adjust() { print("Adjusting Instrument"); }
}

class Wind extends Instrument {
  void play(Note n) { print("Wind.play() " + n); }
  public String toString() { return "Wind"; }
  void adjust() { print("Adjusting Wind"); }
}	

class Percussion extends Instrument {
  void play(Note n) { print("Percussion.play() " + n); }
  public String toString() { return "Percussion"; }
  void adjust() { print("Adjusting Percussion"); }
}

class Stringed extends Instrument {
  void play(Note n) { print("Stringed.play() " + n); }
  public String toString() { return "Stringed"; }
  void adjust() { print("Adjusting Stringed"); }
}

class Brass extends Wind {
  void play(Note n) { print("Brass.play() " + n); }
  void adjust() { print("Adjusting Brass"); }
}

class Woodwind extends Wind {
  void play(Note n) { print("Woodwind.play() " + n); }
  public String toString() { return "Woodwind"; }
}	

class RandomGenInstrument{
	private Random random = new Random(47);
	public Instrument next(){
		switch(random.nextInt(5)){
		case 0: return new Woodwind();
		case 1: return new Brass();
		case 2: return new Stringed();
		case 3: return new Percussion();
		case 4: return new Wind();
		default:
			return null;
		}
	}
}
public class Music3 {
  // Doesn't care about type, so new types
  // added to the system still work right:
  public static void tune(Instrument i) {
    // ...
    i.play(Note.MIDDLE_C);
  }
  
  public static void tuneAll(Instrument[] e) {
    for(Instrument i : e)
      tune(i);
  }	
  public static void main(String[] args) {
    // Upcasting during addition to the array:
	  RandomGenInstrument gen = new RandomGenInstrument();
	Instrument[] instruments = new Instrument[5];
	for(int i = 0; i < instruments.length; ++i){
		instruments[i] = gen.next();
	}
    Instrument[] orchestra = {
      new Wind(),
      new Percussion(),
      new Stringed(),
      new Brass(),
      new Woodwind()
    };
    tuneAll(instruments);
    
    for(Instrument i : instruments){
    	System.out.println(i);
    }
  }
} /* Output:
Wind.play() MIDDLE_C
Percussion.play() MIDDLE_C
Stringed.play() MIDDLE_C
Brass.play() MIDDLE_C
Woodwind.play() MIDDLE_C
*///:~
