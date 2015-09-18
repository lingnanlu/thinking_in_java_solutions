package containers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.mindview.util.Countries;

class SimpleHashMapB<K,V> extends AbstractMap<K,V> {
	  // Choose a prime number for the hash table
	  // size, to achieve a uniform distribution:
	  static final int SIZE = 997;
	  // You can't have a physical array of generics,
	  // but you can upcast to one:
	  @SuppressWarnings("unchecked")
	  ArrayList<MapEntry<K,V>>[] buckets =
	    new ArrayList[SIZE];
	  
	  public V put(K key, V value) {
	    V oldValue = null;
	    int index = Math.abs(key.hashCode()) % SIZE;
	    if(buckets[index] == null)
	      buckets[index] = new ArrayList<MapEntry<K,V>>();
	    ArrayList<MapEntry<K,V>> bucket = buckets[index];
	    MapEntry<K,V> pair = new MapEntry<K,V>(key, value);
	    boolean found = false;
	    ListIterator<MapEntry<K,V>> it = bucket.listIterator();
	    while(it.hasNext()) {
	      MapEntry<K,V> iPair = it.next();
	      if(iPair.getKey().equals(key)) {
	        oldValue = iPair.getValue();
	        it.set(pair); // Replace old with new
	        found = true;
	        break;
	      }
	    }
	    if(!found)
	      buckets[index].add(pair);
	    return oldValue;
	  }
	  @Override
	  public V remove(Object key) {
		// TODO 自动生成的方法存根
		  V value = null;
		  int index = Math.abs(key.hashCode()) % SIZE;
		  ArrayList<MapEntry<K, V>> bucket = buckets[index];
		  for(MapEntry<K, V> ipair : bucket){
			  if(ipair.getKey().equals(key)){
				  value = ipair.getValue();
				  bucket.remove(bucket.indexOf(ipair));
				  return value;
			  }
		  }
		  return null;
	  }
	  @Override
	  public void clear() {
		// TODO 自动生成的方法存根
		for(ArrayList<MapEntry<K, V>> bucket : buckets){
			if(bucket != null)
				bucket.clear();
		}
	  }
	public V get(Object key) {
	    int index = Math.abs(key.hashCode()) % SIZE;
	    if(buckets[index] == null) return null;
	    for(MapEntry<K,V> iPair : buckets[index])
	      if(iPair.getKey().equals(key))
	        return iPair.getValue();
	    return null;
	  }
	  public Set<Map.Entry<K,V>> entrySet() {
	    Set<Map.Entry<K,V>> set= new HashSet<Map.Entry<K,V>>();
	    for(ArrayList<MapEntry<K,V>> bucket : buckets) {
	      if(bucket == null) continue;
	      for(MapEntry<K,V> mpair : bucket)
	        set.add(mpair);
	    }
	    return set;
	  }
	} 
public class p17_37 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		
		 Tester.run(params,new SimpleHashMapB<Integer,Integer>(), MapPerformance.tests);
		 
		 Tester.run(params,new SimpleHashMap<Integer,Integer>(), MapPerformance.tests);
	}

}
