package day5;

import java.util.*;
import java.util.function.Consumer;

public class LambdaWithCollection {

    public static void main(String[] args) {
        comparator1();
        consumer();
    }

    private static void comparator1() {
        List<String> names = Arrays.asList("Java", "Python", "Django Framework");

      
        names.sort((str1, str2) -> str2.length() - str1.length());

        System.out.println(names);
    }

    private static void consumer() {
        List<String> names = Arrays.asList("Java", "Python", "Django Framework");

        Consumer<String> consumer = name -> {
            System.out.print("name=");
            System.out.println(name);
        };

        names.forEach(consumer);
    }
}
