package com.tek.list;
import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
	public static void main (String[] args) {
		List<String> cities = new LinkedList<>();
		System.out.println(cities.contains("Delhi"));
//      System.out.println(cities.remove("Delhi");
		System.out.println(cities);
		System.out.println(cities.contains("Delhi"));
		
	}
	  private static void defensiveDowncasting1(List<String> cities) {
		  if (cities instanceof LinkedList<String>) { //safe  coding
			  LinkedList linkedlist = ({LinkedList) cities);//downcasting
			  linkedlist.addFirst("Chennai")
			  }
		  }
	  private static List<String> createListofCities() {
				  List<String> cities = new ArrayList<>();
			  }
		
		cities.add("Delhi");
		cities.add("Mumbai");
		cities.add("Bangalore");
		if (cities instanceof LinkedList<String>) {//safe coding
		LinkedList<String> linkedList =( (LinkedList)cities);//downcasting
		linkedList.addFirst("Chennai");
		}
		System.out.println(cities);
	}

	private static void defensiveDowncasting(List<String> cities) {
		// TODO Auto-generated method stub
		
	}

}
