package containers;


import io.lingnanlu.github.*;

import java.util.*;


public class MapPerformance {
static List<Test<Map<Integer,Integer>>> tests =
 new ArrayList<Test<Map<Integer,Integer>>>();


static List<Test<Map<Integer,String>>> lookupTests =
new ArrayList<Test<Map<Integer,String>>>();

static {
	
	lookupTests.add(new Test<Map<Integer, String>>("lookup"){

		@Override
		void initialize(Map<Integer, String> container, TestParam tp) {
			// TODO 自动生成的方法存根
			container.clear();
			container.putAll(MapData.map(new CountingGenerator.Integer(), "value", 10000));
		}

		@Override
		int test(Map<Integer, String> container, TestParam tp) {
			// TODO 自动生成的方法存根
			int loops = 1000000;
			for(int i = 0; i < loops; i++){
				container.get(i);
			}
			
			return loops;
		}
		
	});
	
	
	tests.add(new Test<Map<Integer,Integer>>("put") {
	   int test(Map<Integer,Integer> map, TestParam tp) {
	    int loops = tp.loops;
	     int size = tp.size;
	     for(int i = 0; i < loops; i++) {
	       map.clear();
	       for(int j = 0; j < size; j++)
	         map.put(j, j);
	     }
	     return loops * size;
	   }
	
	@Override
	void initialize(Map<Integer, Integer> container, TestParam tp) {
		// TODO 自动生成的方法存根
		
	}
	 });
 tests.add(new Test<Map<Integer,Integer>>("get") {
   int test(Map<Integer,Integer> map, TestParam tp) {
     int loops = tp.loops;
     int span = tp.size * 2;
     for(int i = 0; i < loops; i++)
       for(int j = 0; j < span; j++)
         map.get(j);
     return loops * span;
   }

@Override
void initialize(Map<Integer, Integer> container, TestParam tp) {
	// TODO 自动生成的方法存根
	
}
 });
 tests.add(new Test<Map<Integer,Integer>>("iterate") {
   int test(Map<Integer,Integer> map, TestParam tp) {
     int loops = tp.loops * 10;
     for(int i = 0; i < loops; i ++) {
       Iterator it = map.entrySet().iterator();
       while(it.hasNext())
         it.next();
     }
     return loops * map.size();
   }

@Override
void initialize(Map<Integer, Integer> container, TestParam tp) {
	// TODO 自动生成的方法存根
	
}
 });
}
public static void main(String[] args) {
	
	TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
	
	 Tester.run(params, new TreeMap<Integer,Integer>(), tests);
	 Tester.run(params, new HashMap<Integer,Integer>(), tests);
	 Tester.run(params,new LinkedHashMap<Integer,Integer>(),tests);
	 Tester.run(params,
	   new IdentityHashMap<Integer,Integer>(), tests);
	 Tester.run(params, new WeakHashMap<Integer,Integer>(), tests);
	 Tester.run(params,new Hashtable<Integer,Integer>(), tests);
}
} /* Output: (Sample)
---------- TreeMap ----------
size     put     get iterate
10     748     168     100
100     506     264      76
1000     771     450      78
10000    2962     561      83
---------- HashMap ----------
size     put     get iterate
10     281      76      93
100     179      70      73
1000     267     102      72
10000    1305     265      97
------- LinkedHashMap -------
size     put     get iterate
10     354     100      72
100     273      89      50
1000     385     222      56
10000    2787     341      56
------ IdentityHashMap ------
size     put     get iterate
10     290     144     101
100     204     287     132
1000     508     336      77
10000     767     266      56
-------- WeakHashMap --------
size     put     get iterate
10     484     146     151
100     292     126     117
1000     411     136     152
10000    2165     138     555
--------- Hashtable ---------
size     put     get iterate
10     264     113     113
100     181     105      76
1000     260     201      80
10000    1245     134      77
*///:~

