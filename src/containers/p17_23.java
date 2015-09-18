package containers;

import io.lingnanlu.github.Countries;

import java.util.*;

import static io.lingnanlu.github.Print.*;

class CustomMap<K, V> implements Map<K, V>{
	
	
	 static final int SIZE = 997;
	  // You can't have a physical array of generics,
	  // but you can upcast to one:
	  @SuppressWarnings("unchecked")
	  LinkedList<MapEntry<K,V>>[] buckets =
	    new LinkedList[SIZE];
	  
	  private Set<K> keyset = new KeySet();
	  private Set<Map.Entry<K, V>> entrySet = new EntrySet();
	  private Collection<V> valueCollection = new ValueCollection();
	@Override
	public int size() {
		// TODO 自动生成的方法存根
		int size = 0;
		for(LinkedList<MapEntry<K, V>> bucket : buckets){
			if(bucket != null){
				for(MapEntry<K, V> pair : bucket){
					size++;
				}
			}
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO 自动生成的方法存根
		return size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO 自动生成的方法存根
		  int index = Math.abs(key.hashCode()) % SIZE;
		  LinkedList<MapEntry<K, V>> bucket = buckets[index];
		  if (bucket != null){
			  for(MapEntry<K, V> ipair : bucket){
				  if(ipair.getKey().equals(key)){
					return true;
				  }
			  }
		  }
		  return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO 自动生成的方法存根
		for(LinkedList<MapEntry<K, V>> bucket : buckets){
			if(bucket != null){
				for(MapEntry<K, V> pair : bucket){
					if(pair.getValue().equals(value)){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO 自动生成的方法存根
		int index = Math.abs(key.hashCode()) % SIZE;
	    if(buckets[index] == null) return null;
	    for(MapEntry<K,V> iPair : buckets[index])
	      if(iPair.getKey().equals(key))
	        return iPair.getValue();
	    return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO 自动生成的方法存根
		 V oldValue = null;
		    int index = Math.abs(key.hashCode()) % SIZE;
		    if(buckets[index] == null)
		      buckets[index] = new LinkedList<MapEntry<K,V>>();
		    LinkedList<MapEntry<K,V>> bucket = buckets[index];
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
		  LinkedList<MapEntry<K, V>> bucket = buckets[index];
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
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO 自动生成的方法存根
		for(Map.Entry<? extends K, ? extends V> me : m.entrySet()){
			put(me.getKey(), me.getValue());
		}
	}

	@Override
	public void clear() {
		// TODO 自动生成的方法存根
		for(LinkedList<MapEntry<K, V>> bucket : buckets){
			if(bucket != null)
				bucket.clear();
		}
	}

	@Override
	public Set<K> keySet() {
		// TODO 自动生成的方法存根
		return keyset;
	}

	@Override
	public Collection<V> values() {
		// TODO 自动生成的方法存根
		return valueCollection;
	}
	
	private class ValueCollection extends AbstractCollection<V>{

		
		@Override
		public Iterator<V> iterator() {
			// TODO 自动生成的方法存根
			return new Iterator<V>(){
				private int bucketIndex = 0;
				private int listIndex = 0;
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return count < CustomMap.this.size() - 1;
				}

				@Override
				public V next() {
					// TODO 自动生成的方法存根
					while(buckets[bucketIndex] == null || buckets[bucketIndex].size() == 0
							|| listIndex == buckets[bucketIndex].size()){
						bucketIndex++;
						listIndex = 0;
					}
					
					V result =  buckets[bucketIndex].get(listIndex).getValue();
					listIndex++;
					count++;
					return result;
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return CustomMap.this.size();
		}
		
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO 自动生成的方法存根
		return entrySet;
	}
	
	private class EntrySet extends AbstractSet<Map.Entry<K, V>>{

		@Override
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			// TODO 自动生成的方法存根
			return new Iterator<java.util.Map.Entry<K, V>>(){
				private int bucketIndex = 0;
				private int listIndex = 0;
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return count < CustomMap.this.size() - 1;
				}

				@Override
				public java.util.Map.Entry<K, V> next() {
					// TODO 自动生成的方法存根
					while(buckets[bucketIndex] == null || buckets[bucketIndex].size() == 0
							|| listIndex == buckets[bucketIndex].size()){
						bucketIndex++;
						listIndex = 0;
					}
					
					java.util.Map.Entry<K, V> result =  buckets[bucketIndex].get(listIndex);
					listIndex++;
					count++;
					return result;
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return CustomMap.this.size();
		}
		
	}
	private class KeySet extends AbstractSet<K>{

		@Override
		public Iterator<K> iterator() {
			// TODO 自动生成的方法存根
			return new Iterator<K>(){
				private int bucketIndex = 0;
				private int listIndex = 0;
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return count < CustomMap.this.size() - 1;
				}

				@Override
				public K next() {
					// TODO 自动生成的方法存根
					while(buckets[bucketIndex] == null || buckets[bucketIndex].size() == 0
							|| listIndex == buckets[bucketIndex].size()){
						bucketIndex++;
						listIndex = 0;
					}
					
					K result =  buckets[bucketIndex].get(listIndex).getKey();
					listIndex++;
					count++;
					return result;
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return CustomMap.this.size();
		}
		
	}
	
	public String toString(){
		return entrySet.toString();
	}
	
	public boolean equals(Object o) {
		if(o instanceof CustomMap) {
			if(this.entrySet().equals(((CustomMap)o).entrySet()))
				return true;
		}
		return false;
	}
}
public class p17_23 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		CustomMap<String,String> map =
				new CustomMap<String,String>();
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
			CustomMap<String,String> map2 = 
				new CustomMap<String,String>();
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

}
