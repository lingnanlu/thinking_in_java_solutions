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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		return size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		int index = Math.abs(key.hashCode()) % SIZE;
	    if(buckets[index] == null) return null;
	    for(MapEntry<K,V> iPair : buckets[index])
	      if(iPair.getKey().equals(key))
	        return iPair.getValue();
	    return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		for(Map.Entry<? extends K, ? extends V> me : m.entrySet()){
			put(me.getKey(), me.getValue());
		}
	}

	@Override
	public void clear() {
		// TODO �Զ����ɵķ������
		for(LinkedList<MapEntry<K, V>> bucket : buckets){
			if(bucket != null)
				bucket.clear();
		}
	}

	@Override
	public Set<K> keySet() {
		// TODO �Զ����ɵķ������
		return keyset;
	}

	@Override
	public Collection<V> values() {
		// TODO �Զ����ɵķ������
		return valueCollection;
	}
	
	private class ValueCollection extends AbstractCollection<V>{

		
		@Override
		public Iterator<V> iterator() {
			// TODO �Զ����ɵķ������
			return new Iterator<V>(){
				private int bucketIndex = 0;
				private int listIndex = 0;
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO �Զ����ɵķ������
					return count < CustomMap.this.size() - 1;
				}

				@Override
				public V next() {
					// TODO �Զ����ɵķ������
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
			// TODO �Զ����ɵķ������
			return CustomMap.this.size();
		}
		
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO �Զ����ɵķ������
		return entrySet;
	}
	
	private class EntrySet extends AbstractSet<Map.Entry<K, V>>{

		@Override
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			// TODO �Զ����ɵķ������
			return new Iterator<java.util.Map.Entry<K, V>>(){
				private int bucketIndex = 0;
				private int listIndex = 0;
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO �Զ����ɵķ������
					return count < CustomMap.this.size() - 1;
				}

				@Override
				public java.util.Map.Entry<K, V> next() {
					// TODO �Զ����ɵķ������
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
			// TODO �Զ����ɵķ������
			return CustomMap.this.size();
		}
		
	}
	private class KeySet extends AbstractSet<K>{

		@Override
		public Iterator<K> iterator() {
			// TODO �Զ����ɵķ������
			return new Iterator<K>(){
				private int bucketIndex = 0;
				private int listIndex = 0;
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO �Զ����ɵķ������
					return count < CustomMap.this.size() - 1;
				}

				@Override
				public K next() {
					// TODO �Զ����ɵķ������
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
			// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
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
