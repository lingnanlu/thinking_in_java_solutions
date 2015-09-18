import java.util.ArrayList;
import java.util.List;


interface Selector {
  boolean end();
  Object current();
  void next();
}	

public class p15_4<T> {
	private List<T> items;
	private int size;
  //private T[] items;
  private int next = 0;
  public p15_4(int size) { items = new ArrayList<T>(size);
  this.size = size;}
  public void add(T x) {
    if(next < size)
    	items.add(x);
      //items[next++] = x;
  }
  private class SequenceSelector implements Selector {
    private int i = 0;
    public boolean end() { return i == items.size(); }
    public T current() { return items.get(i); }
    public void next() { if(i < items.size()) i++; }
  }
  public Selector selector() {
    return new SequenceSelector();
  }
  
  public String toString(){
	  return items.toString();
  }
  public static void main(String[] args) {
    p15_4<String> sequence = new p15_4<String>(10);
    System.out.println("1");
    for(int i = 0; i < 10; i++)
      sequence.add(Integer.toString(i));
    System.out.println("2");
    System.out.println(sequence);
    Selector selector = sequence.selector();
    while(!selector.end()) {
      System.out.print(selector.current() + " ");
      selector.next();
    }
  }
} 