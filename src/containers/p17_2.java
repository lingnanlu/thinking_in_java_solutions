package containers;
import io.lingnanlu.github.*;

import java.util.*;
import java.util.Map.Entry;


public class p17_2 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Map<String, String> countriesStartWithA = new HashMap<String, String>();
		Map<String, String> map = Countries.capitals();
		for(Entry<String, String> entry : map.entrySet()){
			String country = entry.getKey();
			if (country.startsWith("A")){
				countriesStartWithA.put(country, entry.getValue());
			}
		}
		
		System.out.println(countriesStartWithA);
	}

}
