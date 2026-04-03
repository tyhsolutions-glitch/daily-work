package com.tek.list;
import java.util.*;

public class CollectionExample {
	public static void main(String[] args) {
		
		List<String> fruits = new LinkedList<>();
		
		fruits.add("Apple");
		fruits.add("Mango");
		fruits.add(new String("Mango"));
		fruits.add("");
		
		System.out.println(fruits);
		System.out.println(fruits.get(2) == fruits.get(3));

	}

}
