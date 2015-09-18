public class Pair<X,Y>  { 
  private X first;
  private Y second;

  public Pair(X a1, Y a2) {
    first  = a1;
    second = a2;
  }
  public X getFirst()  { return first; }
  public Y getSecond() { return second; }
  public void setFirst(X arg)  { first = arg; }
  public void setSecond(Y arg) { second = arg; }
  
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Pair<Integer,Integer>[] intPairArr = new Pair<Integer,Integer>[10]; // illegal
		Object[] objArr = intPairArr; 
		objArr[0] = new Pair<String,String>("",""); // should fail, but would succeed
	}
} 

