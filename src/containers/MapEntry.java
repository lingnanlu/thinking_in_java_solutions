package containers;

//: containers/MapEntry.java
// A simple Map.Entry for sample Map implementations.
import java.util.*;

public class MapEntry<K,V> implements Map.Entry<K,V>, Comparable<MapEntry<K, V>>{
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

	@Override
	public int compareTo(MapEntry<K, V> o) {
		// TODO 自动生成的方法存根
		
		//这里的比较不一定有什么意义 ，比如说从小到大，之类的，这里的比较只是
		//对于不同对象，给出一个Int值，从而方便二分查找
		//二分查找只要求有序，这里的有序是更广义的概念，不一定是从小到大，或从大到小。
		return getKey().hashCode() - o.getKey().hashCode();
	}
	  
} ///:~
