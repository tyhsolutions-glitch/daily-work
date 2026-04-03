package day5;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {
		streamWithChain();
	}

	private static void stream1() {
		// TODO Auto-generated method stub
	}

	private static void streamWithChain() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);//tank
		
		Stream<Integer> finalStream = numbers.stream().map(number -> {
			System.out.println(number);
			return number; 
		}).map(number ->{
			
			if (number % 2 == 1) {
				return number;
			}else {
				return number;
			}
		}).filter((number) -> {
 			System.out.println(number);
 			return number % 2 != 0; //exclude even numbers
 		});

		List<String> names = Arrays.asList("Ram","Krishna","Shiva","Sai");

		names.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		System.out.println(names);

		List<Integer> finalList = finalStream.collect(Collectors.toList());

		finalList.forEach(x -> System.out.println(x));
		finalList.forEach(System.out::println);

		System.out.println(finalList.size()); 
	}
}
