package testing;

import java.util.stream.Stream;

public class Math {
	 int add(int a,int b) {
		return a + b;
	}
	 int addWithArray(Integer[] numbers) {
		 return Stream.of(numbers)
				 .reduce(0,(current, element)-> current+element);
	 }
	 int divide(int a, int b) {
		 return a/b;
	 }
}
