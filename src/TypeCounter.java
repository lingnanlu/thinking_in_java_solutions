import java.util.*;

import typeinfo.pets.Pet;

import generics.coffee.*;

public class TypeCounter extends HashMap<Class<?>, Integer>{
	private Class<?> baseType;
	
	public TypeCounter(Class<?> baseType){
		this.baseType = baseType;
	}
	
	public void count(Object obj){
		Class<?> type = obj.getClass();
		if(!baseType.isAssignableFrom(type))
			throw new RuntimeException(obj + " incorrent type: "
					+ type + ", should be type or subtype of "
					+ baseType);
		countClass(type);
		
	}
	
	private void countClass(Class<?> type){
		Integer quantity = get(type);
		put(type, quantity == null ? 1 : quantity + 1);
		Class<?> superType = type.getSuperclass();
		if (superType != null && baseType.isAssignableFrom(superType))
			countClass(superType);
		
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder("{");
		for(Map.Entry<Class<?>, Integer> pair : entrySet()){
			result.append(pair.getKey().getSimpleName());
			result.append("=");
			result.append(pair.getValue());
			result.append(", ");
		}
		
		result.delete(result.length() - 2, result.length());
		result.append("}");
		return result.toString();
		
	}
	
	public static void main(String[] args){
		TypeCounter tp = new TypeCounter(Coffee.class);
		for(Coffee c : new CoffeeGenerator(20)){
			System.out.print(c + " ");
			tp.count(c);
		}
		System.out.println();
		System.out.print(tp);
	}
}
