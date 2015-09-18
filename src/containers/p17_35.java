package containers;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.WeakHashMap;

public class p17_35 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		 Tester.run(params, new TreeMap<Integer,Integer>(), MapPerformance.tests);
		 Tester.run(params, new HashMap<Integer,Integer>(), MapPerformance.tests);
		 Tester.run(params,new LinkedHashMap<Integer,Integer>(),MapPerformance.tests);
		 Tester.run(params,
		   new IdentityHashMap<Integer,Integer>(), MapPerformance.tests);
		 Tester.run(params, new WeakHashMap<Integer,Integer>(), MapPerformance.tests);
		 Tester.run(params,new Hashtable<Integer,Integer>(), MapPerformance.tests);
		 Tester.run(params,new SlowMap<Integer,Integer>(), MapPerformance.tests);
	}

}
