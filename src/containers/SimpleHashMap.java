package containers;

//: containers/SimpleHashMap.java
// A demonstration hashed Map.
import java.util.*;

import net.mindview.util.*;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	// Choose a prime number for the hash table
	// size, to achieve a uniform distribution:
	private int size = 10;
	private int count = 0;
	// You can't have a physical array of generics,
	// but you can upcast to one:
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[size];

	public V put(K key, V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % size;
		if (buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		while (it.hasNext()) {
			MapEntry<K, V> iPair = it.next();
			if (iPair.getKey().equals(key)) {
				oldValue = iPair.getValue();
				it.set(pair); // Replace old with new
				found = true;
				break;
			}
		}
		if (!found){
			buckets[index].add(pair);
			count++;
			if(count > size * 0.75){
				rehash();
				System.out.println(count + " Rehash");
				
			}
				
			
		}
		return oldValue;
	}

	
	private void rehash(){
		/*
		 * 当负载因子大于0.75时，进行再散列
		 * 新的buckets数为大于旧的buckets数的2倍的第一个质数
		 *
		 */
		
		//寻找大于旧的buckets数的2倍的第一个质数
		int newsize = 0;
		for(int i = 2 * size; ; i++){
			if(isPrimeNumber(i)){
				newsize = i;
				break;
			}
		}
		size = newsize;
		
		LinkedList<MapEntry<K, V>>[] newbuckets = new LinkedList[size];
		
		for(LinkedList<MapEntry<K, V>> bucket : buckets){
			if(bucket == null)
				continue;
			for(MapEntry<K, V> pair : bucket){
				int index = Math.abs(pair.getKey().hashCode()) % size;
				if (newbuckets[index] == null)
					newbuckets[index] = new LinkedList<MapEntry<K, V>>();
				LinkedList<MapEntry<K, V>> newbucket = newbuckets[index];
				newbucket.add(pair);
			}
		}
		
		buckets = newbuckets;
	}
	
	private boolean isPrimeNumber(int n){
		for(int i = 2; i < n; i++){
			if (n % i == 0)
				return false;
		}
		return true;
	}
	@Override
	public V remove(Object key) {
		// TODO 自动生成的方法存根
		V value = null;
		int index = Math.abs(key.hashCode()) % size;
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		for (MapEntry<K, V> ipair : bucket) {
			if (ipair.getKey().equals(key)) {
				value = ipair.getValue();
				bucket.remove(bucket.indexOf(ipair));
				count--;
				return value;
			}
		}
		return null;
	}

	@Override
	public void clear() {
		// TODO 自动生成的方法存根
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			if (bucket != null){
				bucket.clear();
				count = 0;
			}
				
			
		}
	}

	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % size;
		if (buckets[index] == null)
			return null;
		for (MapEntry<K, V> iPair : buckets[index])
			if (iPair.getKey().equals(key))
				return iPair.getValue();
		return null;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			if (bucket == null)
				continue;
			for (MapEntry<K, V> mpair : bucket)
				set.add(mpair);
		}
		return set;
	}

	public static void main(String[] args) {
		SimpleHashMap<String, String> m = new SimpleHashMap<String, String>();
		m.putAll(Countries.capitals(25));
		System.out.println(m);
		System.out.println(m.get("ERITREA"));
		System.out.println(m.entrySet());
	}
} /*
 * Output: {CAMEROON=Yaounde, CONGO=Brazzaville, CHAD=N'djamena, COTE D'IVOIR
 * (IVORY COAST)=Yamoussoukro, CENTRAL AFRICAN REPUBLIC=Bangui, GUINEA=Conakry,
 * BOTSWANA=Gaberone, BISSAU=Bissau, EGYPT=Cairo, ANGOLA=Luanda, BURKINA
 * FASO=Ouagadougou, ERITREA=Asmara, THE GAMBIA=Banjul, KENYA=Nairobi,
 * GABON=Libreville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni,
 * EQUATORIAL GUINEA=Malabo, BURUNDI=Bujumbura, BENIN=Porto-Novo,
 * BULGARIA=Sofia, GHANA=Accra, DJIBOUTI=Dijibouti, ETHIOPIA=Addis Ababa} Asmara
 * [CAMEROON=Yaounde, CONGO=Brazzaville, CHAD=N'djamena, COTE D'IVOIR (IVORY
 * COAST)=Yamoussoukro, CENTRAL AFRICAN REPUBLIC=Bangui, GUINEA=Conakry,
 * BOTSWANA=Gaberone, BISSAU=Bissau, EGYPT=Cairo, ANGOLA=Luanda, BURKINA
 * FASO=Ouagadougou, ERITREA=Asmara, THE GAMBIA=Banjul, KENYA=Nairobi,
 * GABON=Libreville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni,
 * EQUATORIAL GUINEA=Malabo, BURUNDI=Bujumbura, BENIN=Porto-Novo,
 * BULGARIA=Sofia, GHANA=Accra, DJIBOUTI=Dijibouti, ETHIOPIA=Addis Ababa]
 */// :~
