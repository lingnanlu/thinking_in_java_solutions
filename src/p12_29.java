//: exceptions/StormyInning.java
// Overridden methods may throw only the exceptions
// specified in their base-class versions, or exceptions
// derived from the base-class exceptions.

class BaseballException extends RuntimeException {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}



abstract class Inning {
  public Inning()  {}
  public void event()  {
    // Doesn't actually have to throw anything
  }
  public abstract void atBat() throws Strike, Foul;
  public void walk() {} // Throws no checked exceptions

  
}

class StormException extends RuntimeException {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}

interface Storm {
  public void event() ;
  public void rainHard() ;
}

public class p12_29 extends Inning implements Storm {
  // OK to add new exceptions for constructors, but you
  // must deal with the base constructor exceptions:
  public p12_29()
     {}
  public p12_29(String s)
     {}
  public // Regular methods must conform to base class:
  void walk() throws PopFoul {} //Compile error
  // Interface CANNOT add exceptions to existing
  // methods from the base class:

  // If the method doesn't already exist in the
  // base class, the exception is OK:
  public void rainHard()  {}
  // You can choose to not throw any exceptions,
  // even if the base version does:
  
  public void event() {}
  // Overridden methods can throw inherited exceptions:
  public void atBat()  {}
  public static void main(String[] args) {

      p12_29 si = new p12_29();
      si.atBat();
      

      // What happens if you upcast?
      Inning i = new p12_29();
      i.atBat();
      // You must catch the exceptions from the
      // base-class version of the method:
  
  }
} ///:~
