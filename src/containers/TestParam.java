package containers;

public class TestParam {
	public final int size;
	public final int loops;
	public TestParam(int size, int loops){
		this.size = size;
		this.loops = loops;
	}
	
	public static TestParam[] array(int... values){
		int size = values.length / 2;
		TestParam[] params = new TestParam[size];
		
		int n = 0;
		for(int i = 0; i < size; i++){
			params[i] = new TestParam(values[n++], values[n++]);
		}
		
		return params;
	}
	
	public static TestParam[] array(String[] values){
		int[] vals = new int[values.length];
		for(int i = 0; i < values.length; i++){
			vals[i] = Integer.decode(values[i]);
		}
		
		return array(vals);
	}
}
