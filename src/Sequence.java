interface Selector{
	boolean end();
	Object current();
	void next();
}

class stringObj{
	private static int count = 0;
	private String str;
	stringObj(){
		str = "stringObj" + count++;
	}
	public String toString(){
		return str;
	}
}
public class Sequence {

	private Object[] items;
	private int next = 0;
	public Sequence(int size){
		items = new Object[size];
	}
	
	public void add(Object x){
		if (next < items.length){
			items[next++] = x;
		}
	}
	
	private class SequenceSelector implements Selector{
		private int i = 0;
		public boolean end(){
			return i == items.length;
		}
		public Object current(){
			return items[i];
		}
		public void next(){
			if(i < items.length){
				i++;
			}
		}
	}
	public Selector reverseSelector(){
		return new Selector(){
			
			private int i = items.length - 1;
			@Override
			public boolean end() {
				// TODO 自动生成的方法存根
				return i == -1;
			}

			@Override
			public Object current() {
				// TODO 自动生成的方法存根
				return items[i];
			}

			@Override
			public void next() {
				// TODO 自动生成的方法存根
				if (i > -1){
					i--;
				}
			}
			
		};
	}
	public Selector selector(){
		return new SequenceSelector();
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Sequence sequence = new Sequence(10);
		for(int i = 0; i < 10; i++){
			sequence.add(new stringObj());
		}
		
		Selector sel = sequence.reverseSelector();
		while(!sel.end()){
			System.out.println(sel.current());
			sel.next();
		}
		
	
	}

}
