package containers;
import io.lingnanlu.github.Countries;

import java.util.*;

import static io.lingnanlu.github.Print.*;

class SimpleHashMap2<K, V> extends AbstractMap<K,V>{
	
	private class MapEntry<K, V> implements Map.Entry<K, V>{

		private MapEntry<K, V> nextEntry = null;
		  private K key;
		  private V value;
		  public MapEntry(K key, V value) {
		    this.key = key;
		    this.value = value;
		  }
		  public K getKey() { return key; }
		  public V getValue() { return value; }
		  public V setValue(V v) {
		    V result = value;
		    value = v;
		    return result;
		  }
		  
		  public void setNextEntry(MapEntry<K, V> next){
			  nextEntry = next;
		  }
		  
		  public MapEntry<K, V> getNextEntry(){
			  return nextEntry;
		  }
		  public int hashCode() {
		    return (key==null ? 0 : key.hashCode()) ^
		      (value==null ? 0 : value.hashCode());
		  }
		  public boolean equals(Object o) {
		    if(!(o instanceof MapEntry)) return false;
		    MapEntry me = (MapEntry)o;
		    return
		      (key == null ?
		       me.getKey() == null : key.equals(me.getKey())) &&
		      (value == null ?
		       me.getValue()== null : value.equals(me.getValue()));
		  }
		  public String toString() { return key + "=" + value; }
		
	}
	 static final int SIZE = 997;
	  // You can't have a physical array of generics,
	  // but you can upcast to one:
	  @SuppressWarnings("unchecked")
	  MapEntry<K,V>[] buckets =
	    new MapEntry[SIZE];
	  
	  private Set<K> keyset = new KeySet();
	  private Set<Map.Entry<K, V>> entrySet = new EntrySet();
	  private Collection<V> valueCollection = new ValueCollection();
	@Override
	public int size() {
		// TODO 自动生成的方法存根
		int size = 0;
		for(MapEntry<K, V> bucket : buckets){
			while(bucket != null){
				size++;
				bucket = bucket.getNextEntry();
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
		  MapEntry<K, V> bucket = buckets[index];
		  
		  while(bucket != null){
			  if(bucket.getKey().equals(key)){
				  return true;
			  } else {
				  bucket = bucket.getNextEntry();
			  }
		  }
		  return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO 自动生成的方法存根
		for(MapEntry<K, V> bucket : buckets){	
			  while(bucket != null){
				  if(bucket.getValue().equals(value)){
					  return true;
				  } else {
					  bucket = bucket.getNextEntry();
				  }
			  }
		}
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO 自动生成的方法存根
		int index = Math.abs(key.hashCode()) % SIZE;
	    if(buckets[index] == null) 
	    	return null;
	    
	    MapEntry<K, V> bucket = buckets[index];
	    while(bucket != null){
	    	if(bucket.getKey().equals(key)){
	    		return bucket.getValue();
	    	} else{
	    		bucket = bucket.getNextEntry();
	    	}
	    }
	    return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO 自动生成的方法存根
		 V oldValue = null;
		 int index = Math.abs(key.hashCode()) % SIZE;
		 if(buckets[index] == null){
			  buckets[index] = new MapEntry<K,V>(key, value);
			  return null;
		 } else {
			 MapEntry<K, V> bucket = buckets[index];
			 MapEntry<K, V> preBucket = buckets[index];
			 	 
			 while(bucket != null){
				 if(bucket.getKey().equals(key)){
					 oldValue = bucket.getValue();
					 bucket.setValue(value);
					 break;
				 } else {
					 preBucket = bucket; 
					 bucket = bucket.getNextEntry();
				 }
			 }
			 
			 if(bucket == null){
				 preBucket.setNextEntry(new MapEntry<K, V>(key, value));
			 }
			 return oldValue;
		 }
		
	}

	@Override
	public V remove(Object key) {
		// TODO 自动生成的方法存根
		  V value = null;
		  int index = Math.abs(key.hashCode()) % SIZE;
		  MapEntry<K, V> bucket = buckets[index];
		  MapEntry<K, V> preBucket = bucket;
		  while(bucket != null){
			  if(!bucket.getKey().equals(key)){
				preBucket = bucket;
				bucket = bucket.getNextEntry();
			  } else{
				  break;
			  }
		  }
		  
		  if(preBucket == bucket){
			  buckets[index] = null;
			  return bucket.getValue();
		  } else {
			  value = bucket.getValue();
			  preBucket.setNextEntry(bucket.getNextEntry());
			  return value;
		  }
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
		for(int i = 0; i < SIZE; i++){
			buckets[i] = null;
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
				private MapEntry<K, V> bucket = buckets[0];
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return count < SimpleHashMap2.this.size() - 1;
				}

				@Override
				public V next() {
					// TODO 自动生成的方法存根
					while(buckets[bucketIndex] == null || bucket == null){
						bucketIndex++;
						bucket = buckets[bucketIndex];
					}
					
					V result =  bucket.getValue();
					bucket = bucket.getNextEntry();
					count++;
					return result;
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return SimpleHashMap2.this.size();
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
				private MapEntry<K, V> bucket = buckets[0];
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return count < SimpleHashMap2.this.size() - 1;
				}

				@Override
				public java.util.Map.Entry<K, V> next() {
					// TODO 自动生成的方法存根
					while(buckets[bucketIndex] == null || bucket == null){
						bucketIndex++;
						bucket = buckets[bucketIndex];
					}
					
					java.util.Map.Entry<K, V> result =  bucket;
					bucket = bucket.getNextEntry();
					count++;
					return result;
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return SimpleHashMap2.this.size();
		}
		
	}
	private class KeySet extends AbstractSet<K>{

		@Override
		public Iterator<K> iterator() {
			// TODO 自动生成的方法存根
			return new Iterator<K>(){
				private int bucketIndex = 0;
				private MapEntry<K, V> bucket = buckets[0];
				private int count = -1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return count < SimpleHashMap2.this.size() - 1;
				}

				@Override
				public K next() {
					// TODO 自动生成的方法存根
					while(buckets[bucketIndex] == null || bucket == null){
						bucketIndex++;
						bucket = buckets[bucketIndex];
					}
					
					K result =  bucket.getKey();
					bucket = bucket.getNextEntry();
					count++;
					return result;
				}
				
			};
		}

		@Override
		public int size() {
			// TODO 自动生成的方法存根
			return SimpleHashMap2.this.size();
		}
		
	}
	
	public String toString(){
		return entrySet.toString();
	}
	
	public boolean equals(Object o) {
		if(o instanceof SimpleHashMap2) {
			if(this.entrySet().equals(((SimpleHashMap2)o).entrySet()))
				return true;
		}
		return false;
	}
	
}
public class p17_25 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SimpleHashMap2<String,String> map =
				new SimpleHashMap2<String,String>();
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
			SimpleHashMap2<String,String> map2 = 
				new SimpleHashMap2<String,String>();
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
