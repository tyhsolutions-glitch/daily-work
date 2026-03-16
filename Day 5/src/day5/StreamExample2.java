package day5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample2 {

    public static void main(String[] args) {
        //stream1();
        streamWithChain();
    }

    private static void streamWithChain() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5); // tank

        Stream<Integer> finalStream = numbers.stream()   // pipe
                .map(number -> number * number)          // square
                .filter(number -> {                      // filter odd numbers
                    System.out.println(number);
                    return number % 2 != 0;
                });

        System.out.println(finalStream.count());
    }

    private static void stream1() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5); // tank
        Stream<Integer> stream = numbers.stream();        // pipe

        Stream<Integer> squaredStream = stream.map(number -> number * number);

        Stream<Integer> filteredStream = squaredStream.filter(number -> {
            System.out.println(number);
            return number % 2 != 0;
        });

        System.out.println(filteredStream.count());
    }
}
