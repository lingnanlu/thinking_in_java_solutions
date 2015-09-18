package containers;

//: containers/SlowMap.java
// A Map implemented with ArrayLists.
import java.util.*;

import net.mindview.util.*;

public class SlowMap<K,V> extends AbstractMap<K,V> {
  private List<K> keys = new ArrayList<K>();
  private List<V> values = new ArrayList<V>();
  private Set<Map.Entry<K, V>> entryset = new EntrySet();
  public V put(K key, V value) {
    V oldValue = get(key); // The old value or null
    if(!keys.contains(key)) {
      keys.add(key);
      values.add(value);
    } else
      values.set(keys.indexOf(key), value);
    return oldValue;
  }
  public V get(Object key) { // key is type Object, not K
    if(!keys.contains(key))
      return null;
    return values.get(keys.indexOf(key));
  }
  public Set<Map.Entry<K,V>> entrySet() {
	return entryset;
  }
  
  private class EntrySet extends AbstractSet<Map.Entry<K, V>>{

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
				return new MapEntry<K, V>(keys.get(index), values.get(index));
			}
			
			public void remove(){
				keys.remove(index);
				values.remove(index--);
			}
			
		};
	}

	@Override
	public int size() {
		// TODO 自动生成的方法存根
		return keys.size();
	}
	  
  }
  public static void main(String[] args) {
    SlowMap<String,String> m= new SlowMap<String,String>();
    m.putAll(Countries.capitals(15));
    System.out.println(m);
    System.out.println(m.get("BULGARIA"));
    System.out.println(m.entrySet());
  }
} /* Output:
{CAMEROON=Yaounde, CHAD=N'djamena, CONGO=Brazzaville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, CENTRAL AFRICAN REPUBLIC=Bangui, BOTSWANA=Gaberone, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, DJIBOUTI=Dijibouti}
Sofia
[CAMEROON=Yaounde, CHAD=N'djamena, CONGO=Brazzaville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, CENTRAL AFRICAN REPUBLIC=Bangui, BOTSWANA=Gaberone, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, DJIBOUTI=Dijibouti]
*///:~
