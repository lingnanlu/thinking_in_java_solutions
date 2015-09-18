import java.util.*;



public class p11_24 {
	public static void main(String[] args){
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		
		String[] strs = {"queue", "typically", "first", "that", "put", "at"};
		
		for(String word : strs){
			map.put(word, word.length());
		}
		
		System.out.println(map);
		
		
		List<String> list = new ArrayList<String>(map.keySet());
		Collections.sort(list);
		
		
		System.out.println(list);
		for(String word : list){
			Integer length = map.get(word);
			map.remove(word);
			map.put(word, length);
		}
		
		
		System.out.println(map);
	}
}
