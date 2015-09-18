package containers;

import java.util.HashMap;
import java.util.TreeMap;

public class p17_38 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		 TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		 Tester.run(params, new HashMap<Integer,String>(10), MapPerformance.lookupTests);
		 Tester.run(params, new HashMap<Integer,String>(200), MapPerformance.lookupTests);
	}

}
