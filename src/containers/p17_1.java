package containers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.lingnanlu.github.*;


public class p17_1 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		List<String> countrynames = new ArrayList<String>(Countries.names(10));
		
		System.out.println(countrynames);
		
		Collections.sort(countrynames);
		
		System.out.println(countrynames);
		
		for(int i = 0; i < 5; i++){
			Collections.shuffle(countrynames);
			System.out.println(countrynames);
		}
	}

}
