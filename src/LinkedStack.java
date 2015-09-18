
public class LinkedStack<T> {
	
	//Node对客户端程序员是透明不可见的
	private class Node{
		T item;
		Node next;
		Node(){
			item = null;
			next = null;
		}
		Node(T item, Node next){
			this.item = item;
			this.next = next;
		}
		
		boolean end(){
			return item == null && next == null;
		}
	}
	
	private Node top = new Node();
	
	public void push(T item){
		top = new Node(item, top);
	}
	
	public T pop(){
		T result = null;
		if(!top.end()){
			result = top.item;
			top = top.next;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		LinkedStack<String> lss = new LinkedStack<String>();
		for(String s : "Phasers on stun!".split(" ")){
			lss.push(s);
		}
		
		String s;
		while((s = lss.pop()) != null){
			System.out.println(s);
		}
	}

}
