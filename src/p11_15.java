

import java.util.LinkedList;
class Stack<T> {
	private LinkedList<T> storage = new LinkedList<T>();
	
	public void push(T v){
		storage.addFirst(v);
	}
	
	public T peek(){
		return storage.getFirst();
	}
	
	public T pop(){
		return storage.removeFirst();
	}
	
	public boolean empty(){
		return storage.isEmpty();
	}
	
	public String toString(){
		return storage.toString();
	}
}

public class p11_15 {
	public static void main(String[] args){
		String str = "+U+n+c-+e+r+t-+a-+i-+n+t+y-+-+r+u-+l+e+s-";
		Stack<Character> charStack = new Stack<Character>();
		char c = 0;
		for(int i = 0; i < str.length(); i++){
			c = str.charAt(i);
			
			if (c == '+'){
				i++;
				charStack.push(str.charAt(i));
			}else if (c == '-'){
				System.out.println(charStack.pop());
			}else{
				continue;
			}
			
		}
	}
}
