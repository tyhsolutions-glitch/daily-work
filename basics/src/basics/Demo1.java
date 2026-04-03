package basics;

public class Demo1 {
	public static void main(String[] args) {
		int age = 10;
		System.out.println("Hello World");
		System.out.println("Age:" + age);
		boolean result = oddOrEven(age);
		System.out.println(result);
		printPattern(5,'*');
	}
    static boolean oddOrEven(int number) {
    	return number % 2 == 0;
    }
    
    static void printPattern(int n, char character) {
    	for(int i = 0; i <= n; i++) {
    		for (int j=0; j < n - i; j++)
    		System.out.print(character);
    	  System.out.println();	
    	}
    }
}
