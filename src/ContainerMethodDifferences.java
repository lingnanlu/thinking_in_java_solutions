import java.lang.reflect.*;
import java.util.*;

import net.mindview.util.*;


class XJM{
	private void foobar(){
		
	}
}
public class ContainerMethodDifferences {
	
	static Set<String> methodSet(Class<?> type){
		Set<String> result = new TreeSet<String>();
		for(Method m : type.getMethods()){
			result.add(m.getName());
		}
		
		return result;
	}
	
	static void interfaces(Class<?> type){
		System.out.print("Interfaces in " + type.getSimpleName() + ": ");
		List<String> result = new ArrayList<String>();
		for(Class<?> c : type.getInterfaces()){
			result.add(c.getSimpleName());
		}
		System.out.println(result);
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.println("Collection : " + methodSet(Collection.class));
		
		interfaces(Collection.class);
		System.out.println();
		
		System.out.println(methodSet(Set.class));
		System.out.println();
	
		System.out.println(methodSet(HashSet.class));
		difference(HashSet.class, Set.class);
		
		System.out.println(EnumSet.class.getSimpleName());
		
	}

	static Set<String> objectMethod = methodSet(Object.class);
	
	static void difference(Class<?> superset, Class<?> subset){
		System.out.print(superset.getSimpleName() + " extends " + 
	subset.getSimpleName() + ", adds : ");
		Set<String> comp = Sets.difference(methodSet(superset), methodSet(subset));
		comp.removeAll(objectMethod);
		System.out.println(comp);
	}
}
