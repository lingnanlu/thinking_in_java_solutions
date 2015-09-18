package containers;

import static io.lingnanlu.github.Print.print;
import static io.lingnanlu.github.Print.printnb;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.lingnanlu.github.*;


class SlowMapA<K,V> extends AbstractMap<K,V> {
	 private List<MapEntry<K, V>> entrys = new ArrayList<MapEntry<K, V>>();
	 
	  private Set<Map.Entry<K, V>> entryset = new EntrySet();
	  
	  public V put(K key, V value) {
		  V oldValue = null;
		  boolean found = false;
		  for(MapEntry<K, V> entry : entrys){
			  if(entry.getKey().equals(key)){
				  oldValue = entry.getValue();
				  found = true;
				  entry.setValue(value);
				  return oldValue;
			  }
		  }
		  if(!found){
			  MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
			  entrys.add(entry);
		  }
		  return oldValue;
	  }
	  
	  public V get(Object key) { 
		  V value = null;
		  for(MapEntry<K, V> entry : entrys){
			  if(entry.getKey().equals(key)){
				  value = entry.getValue();
			  }
		  }
		  
		  return value;
	  }
	  
	  
	  public Set<Map.Entry<K,V>> entrySet() {
		return entryset;
	  }
	  
	  class EntrySet extends AbstractSet<Map.Entry<K, V>>{

		@Override
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			// TODO 自动生成的方法存根
			return new Iterator<java.util.Map.Entry<K, V>>(){
				private int index = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return index < size() - 1;
				}

				@Override
				public java.util.Map.Entry<K, V> next() {
					// TODO 自动生成的方法存根
					index++;
					return new MapEntry<K, V>(entrys.get(index).getKey(), entrys.get(index).getValue());
				}
				
				public void remove(){
					entrys.remove(index--);
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return entrys.size();
		}
		  
	  }
}

class SlowMapB<K,V> extends AbstractMap<K,V> {
	 private List<MapEntry<K, V>> entrys = new ArrayList<MapEntry<K, V>>();
	 
	  private Set<Map.Entry<K, V>> entryset = new EntrySet();
	  
	  //用在get方法中
	  private Class<K> type;
	  public SlowMapB(Class<K> type){
		  super();
		  this.type = type;
	  }
	  
	  public V put(K key, V value) {
		  V oldValue = null;
		  boolean found = false;
		  MapEntry<K, V> entry = new MapEntry<K, V>(type.cast(key), null);
		  int index = Collections.binarySearch(entrys, entry);
		  
		  if(index >= 0){
			  oldValue = entrys.get(index).getValue();
			  entrys.get(index).setValue(value);
			  Collections.sort(entrys);
		  } else{
			  MapEntry<K, V> newentry = new MapEntry<K, V>(key, value);
			  entrys.add(newentry);
			  Collections.sort(entrys);
		  }
	
		  return oldValue;
	  }
	  
	  
	
	  public V get(Object key) { 
		  V value = null;
		  MapEntry<K, V> entry = new MapEntry<K, V>(type.cast(key), null);
		  int index = Collections.binarySearch(entrys, entry);
		  
		  if (index >= 0){
			  value = entrys.get(index).getValue();
		  }
		  
		  return value;
	  }
	  
	  
	  public Set<Map.Entry<K,V>> entrySet() {
		return entryset;
	  }
	  
	  class EntrySet extends AbstractSet<Map.Entry<K, V>>{

		@Override
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			// TODO 自动生成的方法存根
			return new Iterator<java.util.Map.Entry<K, V>>(){
				private int index = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return index < size() - 1;
				}

				@Override
				public java.util.Map.Entry<K, V> next() {
					// TODO 自动生成的方法存根
					index++;
					return new MapEntry<K, V>(entrys.get(index).getKey(), entrys.get(index).getValue());
				}
				
				public void remove(){
					entrys.remove(index--);
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return entrys.size();
		}
		  
	  }
}

public class p17_36 {

	@SuppressWarnings("unchecked")
	static void maptest(Class<?> type) throws InstantiationException, IllegalAccessException{
			
			
		SlowMapB<String, String> map = new SlowMapB<String, String>(String.class);
			map.putAll(Countries.capitals(3));
			print("map = " + map);
			print("map.entrySet(): " + map.entrySet());
			print("map.keySet(): " + map.keySet());
			print("map.values() = " + map.values());
			print("map.isEmpty(): " + map.isEmpty());
			print("map.containsKey(\"ALGERIA\"): " + map.containsKey("ALGERIA"));
			print("map.containsValue(\"Algiers\"): " + map.containsValue("Algiers"));
			print("map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
			print("map.remove(\"ALGERIA\"): " + map.remove("ALGERIA"));
			print("After map.remove(\"ALGERIA\"), map.containsKey(\"ALGERIA\"): " +
				map.containsKey("ALGERIA"));
			print(" and map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
			print(" and map: = " + map);	
			map.clear();
			print("After map.clear(), map = " + map);
			print(" and map.isEmpty(): " + map.isEmpty());
			map.putAll(Countries.capitals(3));
			print("After map.putAll(Countries.capitals(3)), map = " + map);
			SlowMapB<String, String> map2 = 
					new SlowMapB<String, String>(String.class);
			map2.putAll(Countries.capitals(4));
			print("After map2.putAll(Countries.capitals(4)), map2 = " + map2);
			print(" and map.equals(map2): " + map.equals(map2));
			map2.remove("BOTSWANA");	
			print("After map2.remove(\"BOTSWANT\"), map.equals(map2): " + map.equals(map2));
			//map.entrySet().clear();
			//print("After map.entrySet().clear, map = " + map);
			map.putAll(Countries.capitals(3));
			print("After map.putAll(Countries.capitals(3)), map = " + map);
			//map.keySet().clear();
			//print("After map.keySet().clear(), map = " + map);	
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO 自动生成的方法存根
	//	maptest(SlowMapA.class);
		
		
		SlowMapB<String, String> map = new SlowMapB<String, String>(String.class);
		map.putAll(Countries.capitals(3));
		print("map = " + map);
		print("map.entrySet(): " + map.entrySet());
		print("map.keySet(): " + map.keySet());
		print("map.values() = " + map.values());
		print("map.isEmpty(): " + map.isEmpty());
		print("map.containsKey(\"ALGERIA\"): " + map.containsKey("ALGERIA"));
		print("map.containsValue(\"Algiers\"): " + map.containsValue("Algiers"));
		print("map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
		print("map.remove(\"ALGERIA\"): " + map.remove("ALGERIA"));
		print("After map.remove(\"ALGERIA\"), map.containsKey(\"ALGERIA\"): " +
			map.containsKey("ALGERIA"));
		print(" and map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
		print(" and map: = " + map);	
		map.clear();
		print("After map.clear(), map = " + map);
		print(" and map.isEmpty(): " + map.isEmpty());
		map.putAll(Countries.capitals(3));
		print("After map.putAll(Countries.capitals(3)), map = " + map);
		SlowMapB<String, String> map2 = 
				new SlowMapB<String, String>(String.class);
		map2.putAll(Countries.capitals(4));
		print("After map2.putAll(Countries.capitals(4)), map2 = " + map2);
		print(" and map.equals(map2): " + map.equals(map2));
		map2.remove("BOTSWANA");	
		print("After map2.remove(\"BOTSWANT\"), map.equals(map2): " + map.equals(map2));
		//map.entrySet().clear();
		//print("After map.entrySet().clear, map = " + map);
		map.putAll(Countries.capitals(3));
		print("After map.putAll(Countries.capitals(3)), map = " + map);
		//map.keySet().clear();
		//print("After map.keySet().clear(), map = " + map);	
		
		 TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		 
		 
		
		// Tester.run(params,new SlowMap<Integer,Integer>(), MapPerformance.tests);
		 Tester.run(params,new SlowMapA<Integer,Integer>(), MapPerformance.tests);
		 Tester.run(params,new SlowMapB<Integer,Integer>(Integer.class), MapPerformance.tests);
	}

}
