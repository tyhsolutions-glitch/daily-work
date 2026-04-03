package day5;
import java.util.function.Predicate;
public class PredicateExample {
	public static void main(String[] args) {
// 		Predicate<Integer> isEven = n -> n % 2 == 0; //return is implicit
  		Predicate<Integer> isEven = (Integer n ) -> n % 2 == 0; //return is implicit
// 		Predicate<Integer> isEven = (int n) -> n % 2 == 0; //return is implicit
// 		Predicate<Integer> isEven = n ->{
//  		return n % 2 == 0;
//		};
	System.out.println(isEven.test(10));
	}
}
